package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class KhachHangDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
	public ArrayList<KhachHang> getListKhachHang() throws SQLException
	{
		Statement st = databaseConnector.connect();
		String q = "select * from KhachHang";
		ResultSet rs = st.executeQuery(q);
		while(rs.next())
		{
			String makh = rs.getString(1);
			String hoten = rs.getString(2);
			String sdt = rs.getString(3);
			String email = rs.getString(4);
			String cccd = rs.getString(5);
			String pp = rs.getString(6);
			String doiTuong = rs.getString(7);
			KhachHang kh = new KhachHang(makh, hoten, sdt, email, cccd, pp, doiTuong);
			listKhachHang.add(kh);
		}
		return listKhachHang;
	}
}
