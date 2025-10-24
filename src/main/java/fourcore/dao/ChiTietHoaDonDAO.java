package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChiTietHoaDon;
import fourcore.Entity.HoaDon;
import fourcore.Entity.KhachHang;
import fourcore.Entity.Ve;

public class ChiTietHoaDonDAO {
	DatabaseConnector db = new DatabaseConnector();

	public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() throws SQLException {
		Statement st = db.connect();
		String q = "select * from ChiTietHoaDon";
		ResultSet rs = st.executeQuery(q);
		ArrayList<ChiTietHoaDon> listCTHoaDon = new ArrayList<ChiTietHoaDon>();
		while (rs.next()) {
			String maCTHoaDon = rs.getString(1);
			String maHoaDon = rs.getString(2);
			String maveTau = rs.getString(3);
			String moTa = rs.getString(4);
			double donGia = rs.getDouble(5);
			double thueVAT = rs.getDouble(6);
			double thanhTien = rs.getDouble(7);

			HoaDonDAO hdDao = new HoaDonDAO();
			HoaDon hd = hdDao.getHoaDonBangMaVe(maveTau);
			listCTHoaDon.add(new ChiTietHoaDon(maCTHoaDon, hd, new Ve(maveTau), moTa, donGia, thueVAT, thanhTien));
		}

		return listCTHoaDon;
	}

	public KhachHang getKhachHang(String maveTau) throws SQLException {
		VeDAO vedao = new VeDAO();
		KhachHang kh1 = vedao.getKhachHang(maveTau);

		return kh1;
	}

	public ArrayList<ChiTietHoaDon> getHD(String maHoaDon) throws SQLException {
		ArrayList<ChiTietHoaDon> cthd1 = new ArrayList<ChiTietHoaDon>();
		ArrayList<ChiTietHoaDon> listCTHoaDon = getListChiTietHoaDon();
		for (ChiTietHoaDon cthd : listCTHoaDon) {
			if (cthd.getHoaDon().getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
				cthd1.add(cthd);
			}
		}

		return cthd1;
	}

}
