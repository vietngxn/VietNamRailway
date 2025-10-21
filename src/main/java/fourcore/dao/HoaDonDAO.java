package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChiTietHoaDon;
import fourcore.Entity.HoaDon;
import fourcore.Entity.KhachHang;

public class HoaDonDAO {
	DatabaseConnector database = new DatabaseConnector();
	ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
	public ArrayList<HoaDon> getListHoaDon() throws SQLException
	{
		Statement st = database.connect();
		String q ="SELECT hd.maHoaDon, kh.hoTen , kh.sdt , hd.ngayThanhToan , hd.tongTien\r\n"
				+ "FROM [dbo].[HoaDon] hd, [dbo].[ChiTietHoaDon] chd , [dbo].[Ve] ve , [dbo].[KhachHang] kh\r\n"
				+ "WHERE hd.MaHoaDon = chd.MaHoaDon\r\n"
				+ "and\r\n"
				+ "chd.maVeTau = ve.maVeTau\r\n"
				+ "and\r\n"
				+ "ve.maKhachHang = kh.maKhachHang";
		ResultSet rs = st.executeQuery(q);
		while(rs.next())
		{
			String mahd = rs.getString(1);
			LocalDateTime ngaythanhtoan = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double TongTien = rs.getDouble(5);
			HoaDon hd = new HoaDon(mahd, ngaythanhtoan, TongTien);
			listHoaDon.add(hd);
		}
		return listHoaDon;
	}
	
	
	public KhachHang getKH(String mahd) throws SQLException 
	{
		
		ChiTietHoaDonDAO cthddao = new ChiTietHoaDonDAO();
		ArrayList<ChiTietHoaDon> ct2 = cthddao.getHD(mahd);		
		String s = ct2.get(0).getVeTau().getMaVeTau().toString();
		KhachHang kh1 = cthddao.getKhachHang(s);
		return kh1;
	}
	

}
