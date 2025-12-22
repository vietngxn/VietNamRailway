package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.Tau;

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
    ArrayList listThongTinChuyenTau2 =  new ArrayList<>();
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
    
    public boolean xoaMemChuyenTau(String maChuyenTau) throws SQLException {
        String sql = 
            "UPDATE ChuyenTau " +
            "SET isRemove = 1 " +
            "WHERE maChuyenTau = '" + maChuyenTau + "'";

        int row = myStmt.executeUpdate(sql);
        return row > 0;
    }

    public ArrayList<ChuyenTau> getListChuyenTauDeleted() throws SQLException {
        ArrayList<ChuyenTau> list = new ArrayList<>();

        String sql = "SELECT * FROM ChuyenTau WHERE isRemove = 1";
        ResultSet rs = myStmt.executeQuery(sql);

        while (rs.next()) {
            ChuyenTau ct = new ChuyenTau();

            ct.setMaChuyenTau(rs.getString("maChuyenTau"));

            // set Tau (chỉ mã)
            Tau tau = new Tau();
            tau.setMaTau(rs.getString("maTau"));
            ct.setTau(tau);

            // set Hành trình (chỉ mã)
            HanhTrinh ht = new HanhTrinh();
            ht.setMaHanhTrinh(rs.getString("maHanhTrinh"));
            ct.setHanhTrinh(ht);

            ct.setNgayGioDi(rs.getTimestamp("ngayGioDi").toLocalDateTime());
            ct.setNgayGioDen(rs.getTimestamp("ngayGioDen").toLocalDateTime());
            ct.setGiaCuocTrenChuyenTau(rs.getDouble("giaCuocTrenChuyenTau"));
            ct.setRemove(rs.getBoolean("isRemove"));

            list.add(ct);
        }

        return list;
    }

    public boolean khoiPhucChuyenTau(String maChuyenTau) throws SQLException {
        String sql = 
            "UPDATE ChuyenTau " +
            "SET isRemove = 0 " +
            "WHERE maChuyenTau = '" + maChuyenTau + "'";

        int row = myStmt.executeUpdate(sql);
        return row > 0;
    }
    
    
    public ArrayList getListThongTinChuyenTau2() throws SQLException {
        String query =  "SELECT\r\n"
        		+ "    ct.maChuyenTau      AS N'Mã Chuyến',\r\n"
        		+ "    t.maTau             AS N'Đầu Tàu',\r\n"
        		+ "    ht.tenHanhTrinh     AS hanhTrinhDi,\r\n"
        		+ "    COUNT(DISTINCT tt.maToaTau) AS N'Số lượng toa',\r\n"
        		+ "    SUM(\r\n"
        		+ "        CASE \r\n"
        		+ "            WHEN gtct.trangThaiGhe = N'còn trống' THEN 1\r\n"
        		+ "            ELSE 0\r\n"
        		+ "        END\r\n"
        		+ "    ) AS N'Vé Trống',\r\n"
        		+ "    ct.ngayGioDi        AS N'Thời Gian Khởi Hành',\r\n"
        		+ "    gaDi.tenGa          AS N'Ga đi',\r\n"
        		+ "    gaDen.tenGa         AS N'Ga đến',\r\n"
        		+ "	ct.isRemove\r\n"
        		+ "FROM ChuyenTau ct\r\n"
        		+ "JOIN Tau t\r\n"
        		+ "    ON ct.maTau = t.maTau\r\n"
        		+ "JOIN HanhTrinh ht\r\n"
        		+ "    ON ct.maHanhTrinh = ht.maHanhTrinh\r\n"
        		+ "LEFT JOIN GheTrenChuyenTau gtct\r\n"
        		+ "    ON ct.maChuyenTau = gtct.maChuyenTau\r\n"
        		+ "LEFT JOIN GheNgoi gn\r\n"
        		+ "    ON gtct.maGheNgoi = gn.maGheNgoi\r\n"
        		+ "LEFT JOIN ToaTau tt\r\n"
        		+ "    ON gn.maToaTau = tt.maToaTau\r\n"
        		+ "LEFT JOIN (\r\n"
        		+ "    SELECT\r\n"
        		+ "        htg.maHanhTrinh,\r\n"
        		+ "        g.tenGa\r\n"
        		+ "    FROM HanhTrinhGa htg\r\n"
        		+ "    JOIN Ga g\r\n"
        		+ "        ON htg.maGa = g.maGa\r\n"
        		+ "    WHERE htg.thuTuDung = (\r\n"
        		+ "        SELECT MIN(htg2.thuTuDung)\r\n"
        		+ "        FROM HanhTrinhGa htg2\r\n"
        		+ "        WHERE htg2.maHanhTrinh = htg.maHanhTrinh\r\n"
        		+ "    )\r\n"
        		+ ") gaDi\r\n"
        		+ "    ON gaDi.maHanhTrinh = ct.maHanhTrinh\r\n"
        		+ "LEFT JOIN (\r\n"
        		+ "    SELECT\r\n"
        		+ "        htg.maHanhTrinh,\r\n"
        		+ "        g.tenGa\r\n"
        		+ "    FROM HanhTrinhGa htg\r\n"
        		+ "    JOIN Ga g\r\n"
        		+ "        ON htg.maGa = g.maGa\r\n"
        		+ "    WHERE htg.thuTuDung = (\r\n"
        		+ "        SELECT MAX(htg2.thuTuDung)\r\n"
        		+ "        FROM HanhTrinhGa htg2\r\n"
        		+ "        WHERE htg2.maHanhTrinh = htg.maHanhTrinh\r\n"
        		+ "    )\r\n"
        		+ ") gaDen\r\n"
        		+ "    ON gaDen.maHanhTrinh = ct.maHanhTrinh\r\n"
        		+ "GROUP BY\r\n"
        		+ "    ct.maChuyenTau,\r\n"
        		+ "    t.maTau,\r\n"
        		+ "    ht.tenHanhTrinh,\r\n"
        		+ "    ct.ngayGioDi,\r\n"
        		+ "    gaDi.tenGa,\r\n"
        		+ "    gaDen.tenGa,\r\n"
        		+ "	ct.isRemove;";

        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            Map<String, Object> chuyenTauInfo = new HashMap<>();

            maChuyen = rs.getString(1);
            dauTau = rs.getString(2);
            hanhTrinhDi = rs.getString(3);
            soLuongToa = rs.getInt(4);
            soLuongVeTrong = rs.getInt(5);
            thoiGianKhoiHanh =  rs.getTimestamp(6).toLocalDateTime();
            boolean isremove = rs.getBoolean(9);
            chuyenTauInfo.put("maChuyen" , maChuyen);
            chuyenTauInfo.put("dauTau" , dauTau);
            chuyenTauInfo.put("soLuongToa" , soLuongToa);
            chuyenTauInfo.put("soLuongVeTrong", soLuongVeTrong);
            chuyenTauInfo.put("thoiGianKhoiHanh", thoiGianKhoiHanh);
            chuyenTauInfo.put("hanhTrinhDi", hanhTrinhDi);
            chuyenTauInfo.put("isRemove", isremove);
            listThongTinChuyenTau2.add(chuyenTauInfo);
        }

        return listThongTinChuyenTau2;
    }
    
    
}
