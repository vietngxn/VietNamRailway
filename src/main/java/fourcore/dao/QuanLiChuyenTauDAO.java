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

    public QuanLiChuyenTauDAO() {}
    ArrayList listThongTinChuyenTau =  new ArrayList<>();
    public ArrayList getListThongTinChuyenTau() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query =    "SELECT " +
                "    ct.maChuyenTau AS [Mã Chuyến], " +
                "    t.tenTau AS [Đầu Tàu], " +
                "    COUNT(DISTINCT tt.maToaTau) AS [Số lượng toa], " +
                "    SUM(CASE WHEN gtct.trangThaiGhe = N'còn trống' THEN 1 ELSE 0 END) AS [Vé Trống], " +
                "    ct.ngayGioDi AS [Thời Gian Khởi Hành], " +
                "    gaDi.tenGa AS [Ga đi], " +
                "    gaDen.tenGa AS [Ga đến] " +
                "FROM ChuyenTau ct " +
                "JOIN Tau t ON ct.maTau = t.maTau " +
                "LEFT JOIN GheTrenChuyenTau gtct ON ct.maChuyenTau = gtct.maChuyenTau " +
                "LEFT JOIN GheNgoi gn ON gtct.maGheNgoi = gn.maGheNgoi " +
                "LEFT JOIN ToaTau tt ON gn.maToaTau = tt.maToaTau " +
                "LEFT JOIN ( " +
                "    SELECT htg.maHanhTrinh, g.tenGa " +
                "    FROM HanhTrinhGa htg " +
                "    JOIN Ga g ON htg.maGa = g.maGa " +
                "    WHERE htg.thuTuDung = ( " +
                "        SELECT MIN(thuTuDung) " +
                "        FROM HanhTrinhGa htg2 " +
                "        WHERE htg.maHanhTrinh = htg2.maHanhTrinh " +
                "    ) " +
                ") gaDi ON gaDi.maHanhTrinh = ct.maHanhTrinh " +
                "LEFT JOIN ( " +
                "    SELECT htg.maHanhTrinh, g.tenGa " +
                "    FROM HanhTrinhGa htg " +
                "    JOIN Ga g ON htg.maGa = g.maGa " +
                "    WHERE htg.thuTuDung = ( " +
                "        SELECT MAX(thuTuDung) " +
                "        FROM HanhTrinhGa htg2 " +
                "        WHERE htg.maHanhTrinh = htg2.maHanhTrinh " +
                "    ) " +
                ") gaDen ON gaDen.maHanhTrinh = ct.maHanhTrinh " +
                "GROUP BY ct.maChuyenTau, t.tenTau, ct.ngayGioDi, gaDi.tenGa, gaDen.tenGa;";

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
