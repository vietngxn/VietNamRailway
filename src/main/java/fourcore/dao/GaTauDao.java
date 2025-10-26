package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.Ga;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GaTauDao {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<Ga> danhSachGaTau = new ArrayList<>();
    ArrayList<String> danhSachTenGa = new ArrayList<>();

    public String getGaDi() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "select tenGa from ga where cuLy=0";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String tenGa = rs.getString(1);
            return tenGa;
        }
        return null;
    }

    public ArrayList<String> getDanhSachTenGaTau() throws SQLException {
        Statement myStmt = databaseConnector.connect();

        String query = "select tenGa from ga";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String tenGa = rs.getString(1);
            danhSachTenGa.add(tenGa);
        }
        return danhSachTenGa;
    }
}
