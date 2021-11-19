package Task_3_3.SQL_Lib;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:sqlite:ClientDB.db");
        } catch (SQLException e) {
            throw new RuntimeException("SWW during a connect to DB", e);
        }
    }
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
