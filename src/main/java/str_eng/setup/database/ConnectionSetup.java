package str_eng.setup.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSetup {

    private static Connection c = null;

    public static Connection getConnection() throws SQLException {

        if (c == null || c.isClosed()) {
            String url = System.getenv("DB_URL");
            c = DriverManager.getConnection(url);
        }

        return c;
    }

}
