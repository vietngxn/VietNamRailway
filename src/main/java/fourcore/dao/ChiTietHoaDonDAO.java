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
    Statement st;
    public ChiTietHoaDonDAO() throws SQLException {
        st = db.connect();
    }
	public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() throws SQLException {
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

			listCTHoaDon.add(new ChiTietHoaDon(maCTHoaDon, new HoaDon(maHoaDon, null, null, maHoaDon, maHoaDon, maHoaDon, maHoaDon, null, 0.0), new Ve(maveTau), moTa, donGia, thueVAT,
					thanhTien));
		}

		return listCTHoaDon;
	}


	public String getLoaiHoaDonChoVeTau(String mave) throws SQLException {
		String q = "select loaiHoaDonChoVeTau from ChiTietHoaDon where maVeTau = '" + mave + "'";
		ResultSet rs = st.executeQuery(q);
		ArrayList<ChiTietHoaDon> listCTHoaDon = new ArrayList<ChiTietHoaDon>();
		while (rs.next()) {
			String loai = rs.getString("loaiHoaDonChoVeTau");
			return loai;
		}
		return null;
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

	  public void themChiTietHoaDon(ChiTietHoaDon cthd) throws SQLException {

	        String countQuery = "SELECT COUNT(*) AS soLuong FROM ChiTietHoaDon";
	        int soLuong = 0;
	        ResultSet rs = st.executeQuery(countQuery);
	        if (rs.next()) {
	            soLuong = rs.getInt("soLuong");
	        }
	        rs.close();

	        String newMa = String.format("CTHD%04d", soLuong + 1);

	        String insertQuery =
	            "INSERT INTO ChiTietHoaDon (" +
	            "maChiTietHoaDon, maHoaDon, maVeTau, moTa, donGia, giaTriThueVAT, thanhTien, loaiHoaDonChoVeTau" +
	            ") VALUES (" +
	            "N'" + newMa + "', " +
	            "N'" + cthd.getHoaDon().getMaHoaDon() + "', " +
	            "N'" + cthd.getVeTau().getMaVeTau() + "', " +
	            "N'" + cthd.getMoTa() + "', " +
	            cthd.getDonGia() + ", " +
	            cthd.getThueVAT() + ", " +
	            cthd.getThanhTien() + ", " +
	            "N'" + cthd.getLoaiHoaDonChoVe() + "'" +
	            ")";

	        int rows = st.executeUpdate(insertQuery);
	        System.out.println("Đã thêm " + rows + " chi tiết hóa đơn thành công.");
	        st.close();
	    }
}
