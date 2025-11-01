package fourcore.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnector {
    DBConfig dbConfig = new DBConfig();
    static Connection con = null;
    private static DatabaseConnector instance = new DatabaseConnector();
    public static DatabaseConnector getInstance() {
        return instance;
    }
    public Statement connect() throws SQLException {


        con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());

        Statement myStmt = con.createStatement();
        System.out.println("Connected to database successfully");
        return myStmt;
    }
    
    public static Connection getConnection() {
        return con;
    }

    //Database test connection
    public static void main(String[] args) throws SQLException {
        DatabaseConnector dbC = new DatabaseConnector();
        ResultSet myRs = dbC.connect().executeQuery("Select * from ChucVu");

        while(myRs.next()) {
            System.out.print("Ma chuc vu: " + myRs.getString(1)+"  -  ");
            System.out.println("Ten chuc vu: " + myRs.getString(2));
        }
    }
}
