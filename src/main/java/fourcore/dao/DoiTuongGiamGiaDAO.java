package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.DoiTuongGiamGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoiTuongGiamGiaDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    Statement myStmt = databaseConnector.connect();

    ArrayList<DoiTuongGiamGia> listDoiTuongGiamGia = new ArrayList<>();

    public DoiTuongGiamGiaDAO() throws SQLException {
    }

    public ArrayList<DoiTuongGiamGia> getListDoiTuongGiamGia() throws SQLException {
        String query = "select * from DoiTuongGiamGia";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maDoiTuong =  rs.getString(1);
            String tenDoiTuong =  rs.getString(2);
            double phanTramGiamGia =  rs.getDouble(3);
            String trangThai =   rs.getString(4);
            DoiTuongGiamGia dt = new DoiTuongGiamGia(maDoiTuong,tenDoiTuong,trangThai,phanTramGiamGia);
            listDoiTuongGiamGia.add(dt);
        }
        return listDoiTuongGiamGia;
    }

}
