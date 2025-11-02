package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.Ga;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GaTauDao {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<Ga> danhSachGaTau = new ArrayList<>();
    ArrayList<String> danhSachTenGa = new ArrayList<>();
    Statement myStmt = databaseConnector.connect();
    public void goiDAO(){
        System.out.println("ga tau dao");
    }
    public GaTauDao() throws SQLException {
        goiDAO();
    }

    public String getGaDi() throws SQLException {
        String query = "select tenGa from ga where cuLy=0";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String tenGa = rs.getString(1);
            return tenGa;
        }
        return null;
    }
    public String getMaGaTuTenGa(String tenGa) throws SQLException {
        String query = "select maGa from ga where tenGa=N'"+tenGa+"'";
        ResultSet rs = myStmt.executeQuery(query);
        String maGa = "";
        while (rs.next()) {
            maGa = rs.getString(1);
        }
        return maGa;
    }

    public ArrayList<String> getDanhSachTenGaTau() throws SQLException {


        String query = "select tenGa from ga";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String tenGa = rs.getString(1);
            danhSachTenGa.add(tenGa);
        }
        return danhSachTenGa;
    }
    public Time getGioDiKeHoach(String maHanhTrinh, String maGa) throws SQLException {
        Time gioDenKeHoach = null;
        String query = "select maHanhTrinh, maGa, gioDiKeHoach,soNgayDiQua \n" +
                "from HanhTrinhGa\n" +
                "where maHanhTrinh = '"+maHanhTrinh+"' and maGa = '"+maGa+"'";
        ResultSet rs = myStmt.executeQuery(query);
        while(rs.next()) {
            gioDenKeHoach = rs.getTime(3);
        }
        return gioDenKeHoach;
    }
    public int getSoNgayDiQua(String maHanhTrinh, String maGa) throws SQLException {
        int soNgayDiQua = 0;
        String query = "select maHanhTrinh, maGa, gioDiKeHoach,soNgayDiQua \n" +
                "from HanhTrinhGa\n" +
                "where maHanhTrinh = '"+maHanhTrinh+"' and maGa = '"+maGa+"'";
        ResultSet rs = myStmt.executeQuery(query);
        while(rs.next()) {
            soNgayDiQua = rs.getInt(4);
        }
        return soNgayDiQua;
    }
    public double getCuLiBangTenGa(String tenGa) throws SQLException {
        double cuLy = 0;
        String query = "select  cuLy\n" +
                "from Ga\n" +
                "where  tenGa = N'"+tenGa+"'";

        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            cuLy = rs.getDouble(1);
        }
        return cuLy;
    }
}
