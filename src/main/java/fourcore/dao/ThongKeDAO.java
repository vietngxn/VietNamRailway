package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.KhachHang;
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
	public Map<Tau, Integer> getTauVaSoLanDi() throws SQLException {
	    Map<Tau, Integer> list = new LinkedHashMap<>();
	    String q = "SELECT \r\n"
	    		+ "	               t.maTau,  \r\n"
	    		+ "				   lt.tenLoaiTau, \r\n"
	    		+ "	               COUNT(v.maVeTau) AS SoVeBan \r\n"
	    		+ "	               FROM Ve v, ChuyenTau ct, Tau t , LoaiTau lt\r\n"
	    		+ "	               WHERE v.maChuyenTau = ct.maChuyenTau \r\n"
	    		+ "	               AND ct.maTau = t.maTau \r\n"
	    		+ "				   and t.maLoaiTau = lt.maLoaiTau\r\n"
	    		+ "	               GROUP BY t.maTau , lt.tenLoaiTau\r\n"
	    		+ "	               ORDER BY SoVeBan DESC;";
	    ResultSet rs = st.executeQuery(q);
	    while (rs.next()) {
	        Tau tau = new Tau(rs.getString(1), rs.getString(2), null);
	        int soLanDi = rs.getInt("SoVeBan");
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
}
