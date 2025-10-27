package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.ToaTau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToaTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<ToaTau> listToaTau =  new ArrayList<>();
    LoaiToaTauDAO loaiToaTauDAO = new LoaiToaTauDAO();

    public ArrayList<ToaTau> getListToaTau() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "select * from ToaTau";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maToaTau = rs.getString(1);
            String maLoaiToa = rs.getString(2);
            String tenToaTau = rs.getString(3);
            int soToaTau = Integer.parseInt(rs.getString(4));
            LoaiToaTauDAO loaiToaTauDAO = new LoaiToaTauDAO();
            LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
            ToaTau toaTau = new ToaTau(maToaTau, tenToaTau,soToaTau, loaiToaTau);
            listToaTau.add(toaTau);
//            	maKhuyenMai,tenChuongTrinh,trangThaiKhuyenMai,dieuKienApDung,iaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc)
        }

        return listToaTau;
    }
    public ArrayList<ToaTau> getListToaTauByMaCT(String maCT) throws SQLException {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<ToaTau> listToaTheoChuyen =  new ArrayList<>();
    Statement myStmt = databaseConnector.connect();
    String query = "SELECT DISTINCT tt.maToaTau,tt.tenToaTau,tt.soToa,ltt.maLoaiToaTau\n" +
            "FROM GheTrenChuyenTau gtc\n" +
            "JOIN GheNgoi g ON gtc.maGheNgoi = g.maGheNgoi\n" +
            "JOIN ToaTau tt ON g.maToaTau = tt.maToaTau\n" +
            "JOIN LoaiToaTau ltt ON tt.maLoaiToaTau = ltt.maLoaiToaTau\n" +
            "WHERE gtc.maChuyenTau ='" + maCT +"';";
    ResultSet rs = myStmt.executeQuery(query);
    while (rs.next()) {
        String maToaTau = rs.getString(1);
        String tenToaTau = rs.getString(2);
        int soToaTau = Integer.parseInt(rs.getString(3));
        String maLoaiToa = rs.getString(4);
        LoaiToaTau ltt = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
        System.out.println("Loại tàu import: " + ltt.getTenLoaiToaTau());
        ToaTau tt = new ToaTau(maToaTau, tenToaTau,soToaTau, ltt);
        listToaTheoChuyen.add(tt);
    }
    return listToaTheoChuyen;
    }

    public ToaTau getToaTauByMa(String maToaTau) throws SQLException {
        getListToaTau();
        for (ToaTau toaTau : listToaTau) {
            if (toaTau.getMaToaTau().equals(maToaTau)) {
                return toaTau;
            }
        }
        return null;
    }
}
