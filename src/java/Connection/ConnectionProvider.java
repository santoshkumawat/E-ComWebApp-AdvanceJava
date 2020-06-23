package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName(Data.DRIVER_NAME);
            con = DriverManager.getConnection(Data.URL, Data.USERNAME, Data.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
