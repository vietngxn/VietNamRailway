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
import fourcore.Entity.LoaiHoaDon;
import fourcore.Entity.NhanVien;

public class HoaDonDAO {
	DatabaseConnector database = new DatabaseConnector();
	ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();

	public ArrayList<HoaDon> getListHoaDon() throws SQLException {
		Statement st = database.connect();
		String q = "SELECT hd.maHoaDon, hd.tenKhachHangThanhToan, hd.sdtKhachHangThanhToan, " +
	               "hd.ngayThanhToan, hd.tongTien " +
	               "FROM HoaDon hd";
		ResultSet rs = st.executeQuery(q);
		while (rs.next()) {
			String mahd = rs.getString(1);
			String tenKh = rs.getString(2);
			String sdt = rs.getString(3);
			LocalDateTime ngaythanhtoan = rs.getTimestamp(4).toLocalDateTime();
			double TongTien = rs.getDouble(5);
			HoaDon hd = new HoaDon(mahd, tenKh, sdt, ngaythanhtoan, TongTien);
			listHoaDon.add(hd);
		}

		return listHoaDon;
	}

	public HoaDon getHoaDonBangMaVe(String maVe) throws SQLException {
		Statement st = database.connect();
		String q = "select hd.*\r\n" + "from HoaDon as hd\r\n"
				+ "join ChiTietHoaDon as ct on hd.maHoaDon = ct.maHoaDon\r\n"
				+ "join Ve as v on ct.maVeTau = v.maVeTau\r\n" + "where v.maVeTau = " + "'" + maVe + "'";
		ResultSet rs = st.executeQuery(q);
		HoaDon hd = null;
		while (rs.next()) {
			String mahd = rs.getString("maHoaDon");
			String maloaihd = rs.getString("maLoaiHoaDon");
			String manv = rs.getString("maNhanVien");
			String tenkh = rs.getString("tenKhachHangThanhToan");
			String email = rs.getString("emailKhachHangThanhToan");
			String cccd = rs.getString("cccdKhachHangThanhToan");
			String sdt = rs.getString("sdtKhachHangThanhToan");
			LocalDateTime ngaytt = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double tongtien = rs.getDouble("tongTien");
			NhanVienDAO nvDao = new NhanVienDAO();
			NhanVien nv = nvDao.getNhanVienByMa(manv);
			LoaiHoaDonDAO loaihdDao = new LoaiHoaDonDAO();
			LoaiHoaDon loaiHD = loaihdDao.getLoaiHoaDonTheoMa(maloaihd);
			hd = new HoaDon(mahd, loaiHD, nv, tenkh, email, cccd, sdt, ngaytt, tongtien);
			System.out.println("lấy thông tin hóa đơn " + mahd);
		}
		return hd;
	}

	public void themHoaDonHoanTraVe(HoaDon hd) throws SQLException {
	    Statement st = database.connect();

	    String countQuery = "SELECT COUNT(*) AS soLuong FROM HoaDon";
	    int soLuongHoaDon = 0;

	    ResultSet rs = st.executeQuery(countQuery);
	    if (rs.next()) {
	        soLuongHoaDon = rs.getInt("soLuong");
	    }
	    rs.close();
	    st.close();

	    String newMa = String.format("HD%04d", soLuongHoaDon + 1);

	    String insertQuery =
	        "INSERT INTO HoaDon (" +
	        "maHoaDon, maLoaiHoaDon, maNhanVien, tenKhachHangThanhToan, " +
	        "emailKhachHangThanhToan, cccdKhachHangThanhToan, " +
	        "sdtKhachHangThanhToan, diaChiKhachHangThanhToan, tongTien) VALUES (" +
	        "'" + newMa + "', " +
	        "'" + "LHD02" + "', " +
	        "'" + hd.getMaNhanVien().getMaNhanVien() + "', " +
	        "N'" + hd.getTenKhachHangThanhToan() + "', " +
	        "'" + hd.getEmailKhachHangThanhToan() + "', " +
	        "'" + hd.getCccdKhachHangThanhToan() + "', " +
	        "'" + hd.getSdtKhachHangThanhToan() + "', " +
	        "N'" + hd.getDiaChiKhachHangThanhToan() + "', " +
	        hd.getTongTien() +
	        ")";
	    hd.setMaHoaDon(newMa);
	    Statement insertST = database.connect();
	    int rows = insertST.executeUpdate(insertQuery);
	    insertST.close();

	    System.out.println("Số hàng được thêm thành công: " + rows);
	}

	public KhachHang getKH(String mahd) throws SQLException {

		ChiTietHoaDonDAO cthddao = new ChiTietHoaDonDAO();
		ArrayList<ChiTietHoaDon> ct2 = cthddao.getHD(mahd);
		String s = ct2.get(0).getVeTau().getMaVeTau().toString();
		KhachHang kh1 = cthddao.getKhachHang(s);
		return kh1;
	}

}
