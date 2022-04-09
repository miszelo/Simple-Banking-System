package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {
    private final Database db;


    public DatabaseQuery(Database db) {
        this.db = db;
    }

    public boolean isPossibleToLogin(String cardNumber, String pin) {
        String sql = "SELECT COUNT(*) FROM card WHERE number LIKE ? AND pin LIKE ?";
        boolean found = false;
        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cardNumber);
            statement.setString(2, pin);

            ResultSet rs = statement.executeQuery();
            found = rs.getBoolean(1);

        } catch (SQLException | ConnectionException e) {
            OutputPrinter.printExceptionMsg();
        }
        return found;
    }

    public void insert(Card card) {
        String sql = "INSERT INTO card(number,pin) VALUES(?,?)";
        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, card.getCardNumber());
            statement.setString(2, card.getPIN());
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    public double getBalance(String cardNumber) throws ConnectionException, SQLException {
        String sql = "SELECT balance FROM card WHERE number LIKE ?";

        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, cardNumber);

            ResultSet rs = statement.executeQuery();
            return rs.getDouble(1);

        } catch (ConnectionException e) {
            OutputPrinter.printExceptionMsg();
            throw new ConnectionException("Ups. Something gone wrong. Try again later.");
        }
    }

    public boolean getCardNumber(String cardNumber) throws SQLException, ConnectionException {
        String sql = "SELECT number FROM card WHERE number LIKE ?";
        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cardNumber);
            ResultSet rs = statement.executeQuery();
            return rs.getBoolean(1);

        } catch (ConnectionException e) {
            OutputPrinter.printExceptionMsg();
            throw new ConnectionException("Ups. Something gone wrong. Try again later.");
        }

    }

    public void addIncome(String cardNumber, double value) {
        String sql = "UPDATE card SET balance = balance + ?" +
                "WHERE number LIKE ?";

        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDouble(1, value);
            statement.setString(2, cardNumber);

            statement.executeUpdate();


        } catch (SQLException | ConnectionException e) {
            OutputPrinter.printExceptionMsg();
            throw new ConnectionException("Ups. Something gone wrong. Try again later.");
        }
    }

    public void doTransfer(String senderCardNumber, String receiverCardNumber, double value) throws ConnectionException, SQLException {
        if (getBalance(senderCardNumber) < value) {
            System.out.println("Not enough money!\n");
        } else {
            String sql = "UPDATE card SET balance = balance + ?" +
                    "WHERE number LIKE ?";

            try (Connection conn = db.connect();
                 PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setDouble(1, value);
                statement.setString(2, receiverCardNumber);

                statement.executeUpdate();
                reduceSenderBalance(senderCardNumber, value);
                OutputPrinter.printSuccessfulTransfer();

            } catch (ConnectionException e) {
                OutputPrinter.printExceptionMsg();
            }
        }
    }

    public void reduceSenderBalance(String cardNumber, double value) {
        String sql = "UPDATE card SET balance = balance - ?" +
                "WHERE number LIKE ?";

        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDouble(1, value);
            statement.setString(2, cardNumber);

            statement.executeUpdate();

        } catch (SQLException | ConnectionException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    public void closeAccount(String cardNumber) throws SQLException {
        String sql = "DELETE FROM card WHERE number LIKE ?";
        try (Connection conn = db.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, cardNumber);
            statement.executeUpdate();

        } catch (ConnectionException e) {
            OutputPrinter.printExceptionMsg();
        }
    }
}
