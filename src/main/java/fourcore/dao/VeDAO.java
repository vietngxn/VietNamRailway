package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.Ve;

public class VeDAO {

	DatabaseConnector database = new DatabaseConnector();
	Statement st = database.connect();;
	ArrayList<Ve> listVe = getListVe();

	public void goiDAO() {
		System.out.println("Ve dao");
	}

	public VeDAO() throws SQLException {
		goiDAO();
		getListVe();
	}

	public VeDAO(int x) throws SQLException {
		st = database.connect();
	}

	public double getGiaVe(String maVe) throws SQLException {
		double GiaVe = 0;
		String sql = "SELECT giaVe FROM Ve WHERE maVeTau = '" + maVe + "'";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			GiaVe = rs.getDouble(1);
		}
		return GiaVe;

	}

	public Ve getVeBangMaVe(String maVe) throws SQLException {
		for (Ve x : listVe) {
			if (x.getMaVeTau().equalsIgnoreCase(maVe)) {
				return x;
			}
		}
		return null;
	}

	private ArrayList<Ve> listVe1;

	public ArrayList<Ve> getListVe() throws SQLException {
		String query = "SELECT \r\n" + "    ve.maVeTau, ve.gaDi, ve.gaDen, ve.tenTau, ve.ngayGioDi, ve.ngayGioDen,\r\n"
				+ "    ve.soToa, ve.soKhoang, ve.soTang, ve.soGhe, ve.loaiVe, ve.maGiayTo,\r\n"
				+ "    ve.giaVe, ve.ghiChu, ve.maVeDuocDoi, ve.trangThaiVe,\r\n" + "    ct.maChuyenTau,\r\n"
				+ "    kh.maKhachHang, kh.hoTen, kh.sdt, kh.email, kh.cccd, kh.passport, kh.doiTuong,\r\n"
				+ "    km.maKhuyenMai, km.tenChuongTrinh, km.trangThaiKhuyenMai, \r\n"
				+ "    km.dieuKienApDungKhuyenMai, km.giaTriPhanTramKhuyenMai, km.ngayBatDau, km.ngayKetThuc,\r\n"
				+ "    dt.maDoiTuongGiamGia, dt.tenDoiTuongGiamGia, dt.giaTriPhanTramGiamGia, dt.trangThaiGiamGia\r\n"
				+ "FROM Ve ve\r\n" + "JOIN KhachHang kh ON ve.maKhachHang = kh.maKhachHang\r\n"
				+ "JOIN ChuyenTau ct ON ve.maChuyenTau = ct.maChuyenTau\r\n"
				+ "LEFT JOIN DoiTuongGiamGia dt ON ve.maDoiTuongGiamGia = dt.maDoiTuongGiamGia\r\n"
				+ "LEFT JOIN KhuyenMai km ON ve.maKhuyenMai = km.maKhuyenMai;";

		ResultSet rs = st.executeQuery(query);
		ArrayList<Ve> listVe = new ArrayList<>();

		while (rs.next()) {
			// Lấy dữ liệu Ve
			String maVeTau = rs.getString("maVeTau");
			String gaDi = rs.getString("gaDi");
			String gaDen = rs.getString("gaDen");
			String tenTau = rs.getString("tenTau");
			LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioDi").toLocalDateTime();
			LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
			int soToa = rs.getInt("soToa");
			int soKhoang = rs.getInt("soKhoang");
			int soTang = rs.getInt("soTang");
			int soGhe = rs.getInt("soGhe");
			String loaiVe = rs.getString("loaiVe");
			String maGiayTo = rs.getString("maGiayTo");
			double giaVe = rs.getDouble("giaVe");
			String ghiChu = rs.getString("ghiChu");
			String trangThaiDoiVe = rs.getString("maVeDuocDoi");
			String trangThaiVe = rs.getString("trangThaiVe");
			String maChuyenTau = rs.getString("maChuyenTau");
			String maKhachHang = rs.getString("maKhachHang");
			String maKhuyenMai = rs.getString("maKhuyenMai");
			String maDoiTuongGiamGia = rs.getString("maDoiTuongGiamGia");

			// Tạo object ChuyenTau
			ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau);

			KhachHang kh = new KhachHang(maKhachHang, rs.getString("hoTen") != null ? rs.getString("hoTen") : "",
					rs.getString("sdt") != null ? rs.getString("sdt") : "",
					rs.getString("email") != null ? rs.getString("email") : "",
					rs.getString("cccd") != null ? rs.getString("cccd") : "",
					rs.getString("passport") != null ? rs.getString("passport") : "",
					rs.getString("doiTuong") != null ? rs.getString("doiTuong") : "");

			// Tạo object KhuyenMai
			KhuyenMai km = new KhuyenMai(maKhuyenMai,
					rs.getString("tenChuongTrinh") != null ? rs.getString("tenChuongTrinh") : "",
					rs.getString("trangThaiKhuyenMai") != null ? rs.getString("trangThaiKhuyenMai") : "",
					rs.getString("dieuKienApDungKhuyenMai") != null ? rs.getString("dieuKienApDungKhuyenMai") : "",
					rs.getDouble("giaTriPhanTramKhuyenMai"),
					rs.getTimestamp("ngayBatDau") != null ? rs.getTimestamp("ngayBatDau").toLocalDateTime()
							: LocalDateTime.now(),
					rs.getTimestamp("ngayKetThuc") != null ? rs.getTimestamp("ngayKetThuc").toLocalDateTime()
							: LocalDateTime.now());

			// Tạo object DoiTuongGiamGia
			DoiTuongGiamGia dt = new DoiTuongGiamGia(maDoiTuongGiamGia,
					rs.getString("tenDoiTuongGiamGia") != null ? rs.getString("tenDoiTuongGiamGia") : "",
					rs.getString("trangThaiGiamGia") != null ? rs.getString("trangThaiGiamGia") : "",
					rs.getDouble("giaTriPhanTramGiamGia"));

			// Tạo object Ve
			Ve ve = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, chuyenTau, kh, km, dt);
			listVe.add(ve);
		}

		return listVe;
	}

	public KhachHang getKhachHang(String maVeTau) throws SQLException {
		KhachHang kh = new KhachHang();
		for (Ve ve : listVe) {
			if (ve.getMaVeTau().equalsIgnoreCase(maVeTau)) {
				kh = ve.getKhachHang();
				break;
			}
		}
		return kh;
	}

	public Ve getHoanVeBangMaVe(String maVe) throws SQLException {
		Ve v = new Ve();
		String sql = "SELECT DISTINCT v.* " + "FROM Ve AS v " + "JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau "
				+ "JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
				+ "JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon "
				+ "WHERE v.trangThaiVe = N'hoạt động' " + "AND v.ngayGioDi > GETDATE() " + "AND ( "
				+ "    (ct.loaiHoaDonChoVeTau = N'Vé tập thể' AND v.ngayGioDi > DATEADD(HOUR, 24, GETDATE())) "
				+ " OR (ct.loaiHoaDonChoVeTau = N'Vé cá nhân' AND v.ngayGioDi > DATEADD(HOUR, 4, GETDATE())) " + ") "
				+ "AND v.maVeTau = '" + maVe + "'";

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String maVeTau = rs.getString(1);
			String gaDi = rs.getString(2);
			String gaDen = rs.getString(3);
			String tenTau = rs.getString(4);
			// Timestamp -> LocalDateTime
			LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioDi").toLocalDateTime();
			LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
			int soToa = rs.getInt(7);
			int soKhoang = rs.getInt(8);
			int soTang = rs.getInt(9);
			int soGhe = rs.getInt(10);
			String loaiVe = rs.getString(11);
			String maGiayTo = rs.getString(12);
			double giaVe = rs.getDouble(13);
			String ghiChu = rs.getString(14);
			String trangThaiDoiVe = rs.getString(15);
			String trangThaiVe = rs.getString(16);
			String maChuyenTau = rs.getString(17);
			String maKhachHang = rs.getString(18);
			String maKhuyenMai = rs.getString(19);
			String maDoiTuongGiamGia = rs.getString(20);

			KhachHangDAO khDAO = new KhachHangDAO();
			KhachHang kh = khDAO.getKhachHangByMa(maKhachHang);

			DoiTuongGiamGia_DAO dtDAO = new DoiTuongGiamGia_DAO();
			DoiTuongGiamGia dt = dtDAO.getDoiTuongGiamGiaBangMaDT(maDoiTuongGiamGia);

			ChuongTrinhKhuyenMaiDAO kmDAO = new ChuongTrinhKhuyenMaiDAO();
			KhuyenMai km = kmDAO.getKhuyenMaiBangMa(maKhuyenMai);

			System.out.println(maChuyenTau);
			v = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh, km, dt);
		}

		System.out.println("lấy dữ liệu vé theo mã hoàn thành công");
		return v;
	}

	public ArrayList<Ve> getListHoanVe() throws SQLException {
		ArrayList<Ve> listVe3 = new ArrayList<Ve>();

		String sql = "SELECT DISTINCT v.* " + "FROM Ve AS v " + "JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau "
				+ "JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
				+ "JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon "
				+ "WHERE v.trangThaiVe = N'hoạt động' " + "AND v.ngayGioDi > GETDATE() " + "AND ( "
				+ "    (ct.loaiHoaDonChoVeTau = N'Vé tập thể' AND v.ngayGioDi > DATEADD(HOUR, 24, GETDATE())) "
				+ " OR (ct.loaiHoaDonChoVeTau = N'Vé cá nhân' AND v.ngayGioDi > DATEADD(HOUR, 4, GETDATE())) " + ") ";

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String maVeTau = rs.getString(1);
			String gaDi = rs.getString(2);
			String gaDen = rs.getString(3);
			String tenTau = rs.getString(4);
			// Timestamp -> LocalDateTime
			LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioDi").toLocalDateTime();
			LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
			int soToa = rs.getInt(7);
			int soKhoang = rs.getInt(8);
			int soTang = rs.getInt(9);
			int soGhe = rs.getInt(10);
			String loaiVe = rs.getString(11);
			String maGiayTo = rs.getString(12);
			double giaVe = rs.getDouble(13);
			String ghiChu = rs.getString(14);
			String trangThaiDoiVe = rs.getString(15);
			String trangThaiVe = rs.getString(16);
			String maChuyenTau = rs.getString(17);
			String maKhachHang = rs.getString(18);
			String maKhuyenMai = rs.getString(19);
			String maDoiTuongGiamGia = rs.getString(20);

			KhachHangDAO khDAO = new KhachHangDAO();
			KhachHang kh = khDAO.getKhachHangByMa(maKhachHang);

			DoiTuongGiamGia_DAO dtDAO = new DoiTuongGiamGia_DAO();
			DoiTuongGiamGia dt = dtDAO.getDoiTuongGiamGiaBangMaDT(maDoiTuongGiamGia);

			ChuongTrinhKhuyenMaiDAO kmDAO = new ChuongTrinhKhuyenMaiDAO();
			KhuyenMai km = kmDAO.getKhuyenMaiBangMa(maKhuyenMai);

//			System.out.println(km.toString());
			Ve v = new Ve();
			v = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh, km, dt);

			listVe3.add(v);
		}
		System.out.println("lấy dữ liệu hoàn vé thành công");
		return listVe3;

	}

	public ArrayList<Ve> getListHoanVeTheoCCCDKhachHang(String cccd) throws SQLException {

		ArrayList<Ve> listVe4 = new ArrayList<Ve>();
		String sql = "SELECT DISTINCT v.* " + "FROM Ve AS v " + "JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau "
				+ "JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
				+ "JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon "
				+ "WHERE v.trangThaiVe = N'hoạt động' " + "AND v.ngayGioDi > GETDATE() " + "AND ( "
				+ "    (ct.loaiHoaDonChoVeTau = N'Vé tập thể' AND v.ngayGioDi > DATEADD(HOUR, 24, GETDATE())) "
				+ " OR (ct.loaiHoaDonChoVeTau = N'Vé cá nhân' AND v.ngayGioDi > DATEADD(HOUR, 4, GETDATE())) " + ") "
				+ "AND hd.cccdKhachHangThanhToan = '" + cccd + "'";

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String maVeTau = rs.getString(1);
			String gaDi = rs.getString(2);
			String gaDen = rs.getString(3);
			String tenTau = rs.getString(4);
			// Timestamp -> LocalDateTime
			LocalDateTime ngayGioDi = rs.getTimestamp("ngayGioDi").toLocalDateTime();
			LocalDateTime ngayGioDen = rs.getTimestamp("ngayGioDen").toLocalDateTime();
			int soToa = rs.getInt(7);
			int soKhoang = rs.getInt(8);
			int soTang = rs.getInt(9);
			int soGhe = rs.getInt(10);
			String loaiVe = rs.getString(11);
			String maGiayTo = rs.getString(12);
			double giaVe = rs.getDouble(13);
			String ghiChu = rs.getString(14);
			String trangThaiDoiVe = rs.getString(15);
			String trangThaiVe = rs.getString(16);
			String maChuyenTau = rs.getString(17);
			String maKhachHang = rs.getString(18);
			String maKhuyenMai = rs.getString(19);
			String maDoiTuongGiamGia = rs.getString(20);

			KhachHangDAO khDAO = new KhachHangDAO();
			KhachHang kh = khDAO.getKhachHangByMa(maKhachHang);

			DoiTuongGiamGia_DAO dtDAO = new DoiTuongGiamGia_DAO();
			DoiTuongGiamGia dt = dtDAO.getDoiTuongGiamGiaBangMaDT(maDoiTuongGiamGia);

			ChuongTrinhKhuyenMaiDAO kmDAO = new ChuongTrinhKhuyenMaiDAO();
			KhuyenMai km = kmDAO.getKhuyenMaiBangMa(maKhuyenMai);
			Ve v = new Ve();
			v = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh, km, dt);

			listVe4.add(v);
		}
		System.out.println("lấy dữ liệu vé theo cccd thành công");
		return listVe4;
	}

	public Double layGiaTienGheTheoMaVe(String maVeTau) throws SQLException {
		Double giaTienGhe = 0.0;
		String sql = "SELECT DISTINCT gtc.giaTienGhe " + "FROM Ve AS v "
				+ "JOIN ChuyenTau AS ct ON v.maChuyenTau = ct.maChuyenTau "
				+ "JOIN GheTrenChuyenTau AS gtc ON gtc.maChuyenTau = ct.maChuyenTau "
				+ "JOIN GheNgoi AS gn ON gn.maGheNgoi = gtc.maGheNgoi " + "WHERE v.maVeTau = N'" + maVeTau + "' "
				+ "AND v.soGhe = gn.soGhe";
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			giaTienGhe = rs.getDouble("giaTienGhe");
		}
		return giaTienGhe;
	}

	public boolean ThayDoiTrangThaiVe(String mave, String trangthaimoi) throws SQLException {
		String q = "UPDATE Ve SET trangThaiVe = N'" + trangthaimoi + "' WHERE maVeTau = '" + mave + "'";
		int rows = st.executeUpdate(q);
		return rows > 0;
	}
}