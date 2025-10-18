package fourcore.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    static Connection con = null;
    private static DatabaseConnector instance = new DatabaseConnector();
    public static DatabaseConnector getInstance() {
        return instance;
    }
    public Statement connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QuanLyVeTauDB;encrypt=false;trustServerCertificate=true";
        String user = "sa";
        String password = "sapassword";
        con = DriverManager.getConnection(url,user,password);

        Statement myStmt = con.createStatement();

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
