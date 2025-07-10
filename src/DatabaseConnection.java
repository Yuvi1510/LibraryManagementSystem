import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class DatabaseConnection {
    private final static String dbName = "librarydb";
    private final static String path = "jdbc:mysql://localhost:3306/"+dbName ;
    private final static String username = "root";
    private final static String password = "root";

    public static Connection connect() throws SQLException, ClassNotFoundException {
//        class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(path, username, password);
        return  connection;
    }

}
