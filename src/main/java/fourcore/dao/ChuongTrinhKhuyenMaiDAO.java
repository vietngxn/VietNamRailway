package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuongTrinhKhuyenMaiDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public ChuongTrinhKhuyenMaiDAO() {}
    ArrayList<KhuyenMai> listKhuyenMai =  new ArrayList<>();
    public ArrayList<KhuyenMai> getListKhuyenMai() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "select * from KhuyenMai";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maKhuyenMai = rs.getString(1);
            String tenChuongTrinh = rs.getString(2);
            double giaTriPhanTramKhuyenMai = rs.getDouble(3);
            LocalDateTime ngayBatDau = rs.getTimestamp(4).toLocalDateTime();
            LocalDateTime ngayKetThuc =  rs.getTimestamp(5).toLocalDateTime();
            String trangThai = rs.getString(6);
            String dieuKienApDung = rs.getString(7);
            KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenChuongTrinh,trangThai,dieuKienApDung, giaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc);
            listKhuyenMai.add(khuyenMai);
//            	maKhuyenMai,tenChuongTrinh,trangThaiKhuyenMai,dieuKienApDung,iaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc)
        }

        return listKhuyenMai;
    }
}
