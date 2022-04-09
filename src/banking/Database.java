package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

    private final String url;

    public Database(String url) {
        this.url = url;
        initializeDatabase();
    }

    private void initializeDatabase() {
        var dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                    + "     id INTEGER PRIMARY KEY,\n"
                    + "     number TEXT,\n"
                    + "     pin TEXT,\n"
                    + "     balance INTEGER DEFAULT 0"
                    + ");";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection connect() throws ConnectionException {
        try {
            return DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            throw new ConnectionException("test");
        }
    }

}
