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
	private String hanhTrinhDi;
    public ArrayList getListThongTinChuyenTau() throws SQLException {
        String query =  "SELECT \n" +
        	    "    ct.maChuyenTau AS N'Mã Chuyến',\n" +
        	    "    t.maTau AS N'Đầu Tàu',\n" +
        	    "    ht.tenHanhTrinh AS hanhTrinhDi,\n" +
        	    "    COUNT(DISTINCT tt.maToaTau) AS N'Số lượng toa',\n" +
        	    "    SUM(CASE \n" +
        	    "            WHEN gtct.trangThaiGhe = N'còn trống' THEN 1 \n" +
        	    "            ELSE 0 \n" +
        	    "        END) AS N'Vé Trống',\n" +
        	    "    ct.ngayGioDi AS N'Thời Gian Khởi Hành',\n" +
        	    "    gaDi.tenGa AS N'Ga đi',\n" +
        	    "    gaDen.tenGa AS N'Ga đến'\n" +
        	    "FROM ChuyenTau AS ct\n" +
        	    "JOIN Tau AS t \n" +
        	    "    ON ct.maTau = t.maTau\n" +
        	    "JOIN HanhTrinh AS ht\n" +
        	    "    ON ct.maHanhTrinh = ht.maHanhTrinh\n" +
        	    "LEFT JOIN GheTrenChuyenTau AS gtct \n" +
        	    "    ON ct.maChuyenTau = gtct.maChuyenTau\n" +
        	    "LEFT JOIN GheNgoi AS gn \n" +
        	    "    ON gtct.maGheNgoi = gn.maGheNgoi\n" +
        	    "LEFT JOIN ToaTau AS tt \n" +
        	    "    ON gn.maToaTau = tt.maToaTau\n" +
        	    "LEFT JOIN (\n" +
        	    "    SELECT \n" +
        	    "        htg.maHanhTrinh,\n" +
        	    "        g.tenGa\n" +
        	    "    FROM HanhTrinhGa AS htg\n" +
        	    "    JOIN Ga AS g \n" +
        	    "        ON htg.maGa = g.maGa\n" +
        	    "    WHERE htg.thuTuDung = (\n" +
        	    "        SELECT MIN(htg2.thuTuDung)\n" +
        	    "        FROM HanhTrinhGa AS htg2\n" +
        	    "        WHERE htg2.maHanhTrinh = htg.maHanhTrinh\n" +
        	    "    )\n" +
        	    ") AS gaDi \n" +
        	    "    ON gaDi.maHanhTrinh = ct.maHanhTrinh\n" +
        	    "LEFT JOIN (\n" +
        	    "    SELECT \n" +
        	    "        htg.maHanhTrinh,\n" +
        	    "        g.tenGa\n" +
        	    "    FROM HanhTrinhGa AS htg\n" +
        	    "    JOIN Ga AS g \n" +
        	    "        ON htg.maGa = g.maGa\n" +
        	    "    WHERE htg.thuTuDung = (\n" +
        	    "        SELECT MAX(htg2.thuTuDung)\n" +
        	    "        FROM HanhTrinhGa AS htg2\n" +
        	    "        WHERE htg2.maHanhTrinh = htg.maHanhTrinh\n" +
        	    "    )\n" +
        	    ") AS gaDen \n" +
        	    "    ON gaDen.maHanhTrinh = ct.maHanhTrinh\n" +
        	    "GROUP BY\n" +
        	    "    ct.maChuyenTau,\n" +
        	    "    t.maTau,\n" +
        	    "    ht.tenHanhTrinh,\n" +
        	    "    ct.ngayGioDi,\n" +
        	    "    gaDi.tenGa,\n" +
        	    "    gaDen.tenGa;";

        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            Map<String, Object> chuyenTauInfo = new HashMap<>();

            maChuyen = rs.getString(1);
            dauTau = rs.getString(2);
            hanhTrinhDi = rs.getString(3);
            soLuongToa = rs.getInt(4);
            soLuongVeTrong = rs.getInt(5);
            thoiGianKhoiHanh =  rs.getTimestamp(6).toLocalDateTime();
            chuyenTauInfo.put("maChuyen" , maChuyen);
            chuyenTauInfo.put("dauTau" , dauTau);
            chuyenTauInfo.put("soLuongToa" , soLuongToa);
            chuyenTauInfo.put("soLuongVeTrong", soLuongVeTrong);
            chuyenTauInfo.put("thoiGianKhoiHanh", thoiGianKhoiHanh);
            chuyenTauInfo.put("hanhTrinhDi", hanhTrinhDi);
            listThongTinChuyenTau.add(chuyenTauInfo);
        }

        return listThongTinChuyenTau;
    }
}
