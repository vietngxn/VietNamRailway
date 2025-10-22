package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.HoaDon;
import fourcore.Entity.NhanVien;

public class QuanLiDoanhThuTheoNamDAO {
	DatabaseConnector databaseconnector = new DatabaseConnector();
	public ArrayList<HoaDon> listHoaDon = new ArrayList<>();
	
	public ArrayList<HoaDon> getListHoaDon() throws SQLException{
		Statement st = databaseconnector.connect();
		String q = "select * from hoadon";
		ResultSet rs = st.executeQuery(q);
		while(rs.next())
		{
			String mahd = rs.getString(1);
			String manv = rs.getString(2);
			LocalDateTime ngay = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double 	tongTien = rs.getDouble(4);;
			NhanVien nv = new NhanVien(manv);	
			HoaDon hd = new HoaDon(mahd,  nv, ngay, tongTien);
			listHoaDon.add(hd);
			
		}

		return listHoaDon;
	}
}
