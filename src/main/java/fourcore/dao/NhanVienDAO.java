package fourcore.dao;
import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class NhanVienDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<NhanVien> listKhachHang = new ArrayList<NhanVien>();
	
	public ArrayList<NhanVien> getListNhanVien() throws SQLException
	{
		
		Statement st = databaseConnector.connect();
		String q = "Select * from NhanVien";
		ResultSet rs = st.executeQuery(q);
		while(rs.next())
		{
			String maNhanVien = rs.getString(1);
			String hoTen = rs.getString(2);
			String sdt = rs.getString(7);
			String gioiTinh = rs.getString(10);
			String email = rs.getString(6);
			String cccd = rs.getString(11);
			String tinhTrang = rs.getString(9);
			NhanVien nv1 = new NhanVien(maNhanVien, hoTen, sdt, gioiTinh, email, cccd, tinhTrang);
			listKhachHang.add(nv1);
		}
		
		return listKhachHang;
	}
}
