package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuanLiChuyenTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public String maChuyen;
    public String dauTau;
    public int soLuongToa;
    public int soLuongVeTrong;
    LocalDateTime thoiGianKhoiHanh;
    String gaDi;
    String gaDen;
    Statement myStmt;

    public QuanLiChuyenTauDAO() throws SQLException {
        myStmt = databaseConnector.connect();
        goiDAO();
    }
    public void goiDAO(){
        System.out.println("quan li chuyen tau dao");
    }
    ArrayList listThongTinChuyenTau =  new ArrayList<>();
    public ArrayList getListThongTinChuyenTau() throws SQLException {
        String query =  "SELECT \n" +
                "    ct.maChuyenTau AS [Mã Chuyến],\n" +
                "    t.maTau AS [Đầu Tàu],\n" +
                "    COUNT(DISTINCT tt.maToaTau) AS [Số lượng toa],\n" +
                "    SUM(CASE WHEN gtct.trangThaiGhe = N'còn trống' THEN 1 ELSE 0 END) AS [Vé Trống],\n" +
                "    ct.ngayGioDi AS [Thời Gian Khởi Hành],\n" +
                "    gaDi.tenGa AS [Ga đi],\n" +
                "    gaDen.tenGa AS [Ga đến]\n" +
                "FROM ChuyenTau AS ct\n" +
                "JOIN Tau AS t ON ct.maTau = t.maTau\n" +
                "LEFT JOIN GheTrenChuyenTau AS gtct ON ct.maChuyenTau = gtct.maChuyenTau\n" +
                "LEFT JOIN GheNgoi AS gn ON gtct.maGheNgoi = gn.maGheNgoi\n" +
                "LEFT JOIN ToaTau AS tt ON gn.maToaTau = tt.maToaTau\n" +
                "LEFT JOIN (\n" +
                "    SELECT \n" +
                "        htg.maHanhTrinh, \n" +
                "        g.tenGa\n" +
                "    FROM HanhTrinhGa AS htg\n" +
                "    JOIN Ga AS g ON htg.maGa = g.maGa\n" +
                "    WHERE htg.thuTuDung = (\n" +
                "        SELECT MIN(htg2.thuTuDung)\n" +
                "        FROM HanhTrinhGa AS htg2\n" +
                "        WHERE htg.maHanhTrinh = htg2.maHanhTrinh\n" +
                "    )\n" +
                ") AS gaDi ON gaDi.maHanhTrinh = ct.maHanhTrinh\n" +
                "LEFT JOIN (\n" +
                "    SELECT \n" +
                "        htg.maHanhTrinh, \n" +
                "        g.tenGa\n" +
                "    FROM HanhTrinhGa AS htg\n" +
                "    JOIN Ga AS g ON htg.maGa = g.maGa\n" +
                "    WHERE htg.thuTuDung = (\n" +
                "        SELECT MAX(htg2.thuTuDung)\n" +
                "        FROM HanhTrinhGa AS htg2\n" +
                "        WHERE htg.maHanhTrinh = htg2.maHanhTrinh\n" +
                "    )\n" +
                ") AS gaDen ON gaDen.maHanhTrinh = ct.maHanhTrinh\n" +
                "GROUP BY \n" +
                "    ct.maChuyenTau, \n" +
                "    t.maTau, \n" +
                "    ct.ngayGioDi, \n" +
                "    gaDi.tenGa, \n" +
                "    gaDen.tenGa;";

        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            Map<String, Object> chuyenTauInfo = new HashMap<>();

            maChuyen = rs.getString(1);
            dauTau = rs.getString(2);
            soLuongToa = rs.getInt(3);
            soLuongVeTrong = rs.getInt(4);
            thoiGianKhoiHanh =  rs.getTimestamp(5).toLocalDateTime();
            gaDi = rs.getString(6);
            gaDen = rs.getString(7);
            chuyenTauInfo.put("maChuyen" , maChuyen);
            chuyenTauInfo.put("dauTau" , dauTau);
            chuyenTauInfo.put("soLuongToa" , soLuongToa);
            chuyenTauInfo.put("soLuongVeTrong", soLuongVeTrong);
            chuyenTauInfo.put("thoiGianKhoiHanh", thoiGianKhoiHanh);
            chuyenTauInfo.put("gaDi", gaDi);
            chuyenTauInfo.put("gaDen", gaDen);
            listThongTinChuyenTau.add(chuyenTauInfo);
        }

        return listThongTinChuyenTau;
    }
}
