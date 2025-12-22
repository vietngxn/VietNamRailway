package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.KhachHang;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;

public class ThongKeDAO {
	DatabaseConnector db = new DatabaseConnector();
    Statement st = db.connect();

    public ThongKeDAO() throws SQLException {
		goiDAO();
	}
    public void goiDAO(){
        System.out.println("Ve dao");
    }
    public Map<Tau, Integer> getTauVaSoLanDiTheoNam(int year) throws SQLException {
        Map<Tau, Integer> list = new LinkedHashMap<>();
        
        String sql =
                "SELECT ct.maTau, COUNT(ct.maChuyenTau) AS SoLanDi " +
                "FROM ChuyenTau ct " +
                "WHERE YEAR(ct.ngayGioDen) = " + year + " " +
                "AND ct.isRemove = 0 " +
                "GROUP BY ct.maTau " +
                "ORDER BY SoLanDi DESC";
        
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Tau tau = new Tau(rs.getString(1), rs.getString(2), null);
            int soLanDi = rs.getInt("soLanDi");
            list.put(tau, soLanDi);
        }
        return list;
    }
    
    
    public Map<Tau, Integer> getTauVaSoLanDiTheoThangNam(int month, int year) throws SQLException {
        Map<Tau, Integer> list = new LinkedHashMap<>();
        
        String sql =
                "SELECT ct.maTau, COUNT(ct.maChuyenTau) AS SoLanDi " +
                "FROM ChuyenTau ct " +
                "WHERE MONTH(ct.ngayGioDen) = " + month + " " +
                "AND YEAR(ct.ngayGioDen) = " + year + " " +
                "AND ct.isRemove = 0 " +
                "GROUP BY ct.maTau " +
                "ORDER BY SoLanDi DESC";
        
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Tau tau = new Tau(rs.getString(1), rs.getString(2), null);
            int soLanDi = rs.getInt("soLanDi");
            list.put(tau, soLanDi);
        }
        return list;
    }
	
	
	
	public Map<KhachHang,Integer> getKhachHangvaSoLanDi() throws SQLException
	{
		Map<KhachHang,Integer> list = new LinkedHashMap<KhachHang, Integer>();
		 String q = "SELECT " +
	               "    kh.maKhachHang, " +
	               "    kh.hoTen, " +
	               "    COUNT(v.maVeTau) AS SoLanDi " +
	               "FROM Ve v " +
	               "JOIN KhachHang kh ON v.maKhachHang = kh.maKhachHang " +
	               "GROUP BY kh.maKhachHang, kh.hoTen " +
	               "ORDER BY SoLanDi DESC;";
		 ResultSet rs = st.executeQuery(q);
		 while(rs.next())
		 {
			 KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), null, null, null, null, null);
			 int soLan = rs.getInt(3);
			 list.put(kh, soLan);
		 }
		 return list;
	}
	
	public Map<Integer,Double> getDoanhThuTheoThang(int nam) throws SQLException{
	    Map<Integer,Double> list = new LinkedHashMap<Integer, Double>();
	    String q = "SELECT \r\n"
	        + " MONTH(ngayThanhToan) AS Thang,\r\n"
	        + " SUM(tongTien) AS DoanhThuThang\r\n"
	        + "FROM HoaDon\r\n"
	        + "WHERE YEAR(ngayThanhToan) = " + nam + "\r\n"
	        + "GROUP BY MONTH(ngayThanhToan)\r\n"
	        + "ORDER BY Thang;";
	    
	    ResultSet rs = st.executeQuery(q);
	    while(rs.next()) {
	        int thang = rs.getInt(1);
	        double doanhthu = rs.getDouble(2);
	        list.put(thang, doanhthu);
	    }
	    return list;
	}
	
	public Map<Tau,Double> getDoanhThutheoChuyenTau(int nam) throws SQLException
	{
		Map<Tau,Double> list = new LinkedHashMap<Tau, Double>();
		String q = "select ct.maChuyenTau,lt.tenLoaiTau,sum(hd.tongTien) as thanhTien\r\n"
				+ "from ChiTietHoaDon cthd, Ve v,ChuyenTau ct,Tau t, LoaiTau lt,HoaDon hd\r\n"
				+ "where cthd.maVeTau = v.maVeTau\r\n"
				+ "and\r\n"
				+ "ct.maChuyenTau = v.maChuyenTau\r\n"
				+ "and\r\n"
				+ "t.maTau = ct.maTau\r\n"
				+ "and\r\n"
				+ "lt.maLoaiTau = t.maLoaiTau\r\n"
				+ "and \r\n"
				+ "hd.maHoaDon = cthd.maHoaDon\r\n"
				+ "and\r\n"
				+ "Year(hd.ngayThanhToan) = "+nam+"\r\n"
				+ "group by ct.maChuyenTau,lt.tenLoaiTau\r\n"
				+ "order by thanhTien DESC";
		ResultSet rs = st.executeQuery(q);
		while(rs.next())
		{
			Tau tau =  new Tau(rs.getString(1),rs.getString(2), null);
			double doanhthu = rs.getDouble(3);
			list.put(tau, doanhthu);
		}
			return list;
	}
	
	
	public Map<Tau,Double> getDoanhThutheoChuyenTautheoThang(int thang, int nam) throws SQLException
	{
	    Map<Tau,Double> list = new LinkedHashMap<Tau, Double>();
	    String q = "select ct.maChuyenTau, lt.tenLoaiTau, sum(hd.tongTien) as thanhTien\r\n"
	            + "from ChiTietHoaDon cthd, Ve v, ChuyenTau ct, Tau t, LoaiTau lt, HoaDon hd\r\n"
	            + "where cthd.maVeTau = v.maVeTau\r\n"
	            + "and ct.maChuyenTau = v.maChuyenTau\r\n"
	            + "and t.maTau = ct.maTau\r\n"
	            + "and lt.maLoaiTau = t.maLoaiTau\r\n"
	            + "and hd.maHoaDon = cthd.maHoaDon\r\n"
	            + "and YEAR(hd.ngayThanhToan) = " + nam + "\r\n"
	            + "and MONTH(hd.ngayThanhToan) = " + thang + "\r\n"
	            + "group by ct.maChuyenTau, lt.tenLoaiTau\r\n"
	            + "order by thanhTien DESC";

	    ResultSet rs = st.executeQuery(q);
	    while (rs.next()) {
	        Tau tau = new Tau(rs.getString(1), rs.getString(2), null);
	        double doanhthu = rs.getDouble(3);
	        list.put(tau, doanhthu);
	    }
	    return list;
	}
	
	
	
	public Tau getTauTheoMa(String maTau) throws SQLException {

	    String sql =
	        "SELECT * " +
	        "FROM Tau t, LoaiTau lt " +
	        "WHERE t.maLoaiTau = lt.maLoaiTau " +
	        "AND t.maTau = '" + maTau + "'";

	    ResultSet rs = st.executeQuery(sql);

	    if (rs.next()) {
	        // Loại tàu
	        LoaiTau loaiTau = new LoaiTau(
	            rs.getString("maLoaiTau"),
	            rs.getString("tenLoaiTau"),
	            rs.getInt("giaCuoc")  
	        );

	        // Tàu
	        Tau tau = new Tau(
	            rs.getString("maTau"),
	            rs.getString("bienSoTau"),
	            loaiTau
	        );

	        return tau;
	    }

	    return null;
	}



	public String getMaChuyenTauTheoMaTau(String maTau) throws SQLException {

	    StringBuilder result = new StringBuilder();

	    String sql =
	        "SELECT ct.maChuyenTau " +
	        "FROM Tau t, ChuyenTau ct " +
	        "WHERE t.maTau = ct.maTau " +
	        "AND t.maTau = '" + maTau + "'";

	    ResultSet rs = st.executeQuery(sql);

	    while (rs.next()) {
	        if (result.length() > 0) {
	            result.append(","); 
	        }
	        result.append(rs.getString("maChuyenTau"));
	    }

	    return result.toString(); 
	}

	
	
}
