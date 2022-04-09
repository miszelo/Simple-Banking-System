package banking;

import java.sql.SQLException;

public class TransferValidator {

    private static final String url = "jdbc:sqlite:card.s3db";
    private static final Database db = new Database(url);
    private static final DatabaseQuery query = new DatabaseQuery(db);

    public static boolean PossibilityToTransfer(String senderCardNumber, String receiverCardNumber) throws SQLException, ConnectionException {
        if (!CardGenerator.checkIfCardNumberIsValid(receiverCardNumber)) {
            OutputPrinter.printMistakeInCardNumber();
            return false;
        }
        if (senderCardNumber.equals(receiverCardNumber)) {
            OutputPrinter.printCanNotTransfer();
            return false;
        }
        if (!query.getCardNumber(receiverCardNumber)) {
            OutputPrinter.printNotExistingCard();
            return false;
        }
        return true;
    }
}
