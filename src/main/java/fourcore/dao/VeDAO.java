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
	ArrayList<Ve> listVe = getListVe();

	public VeDAO() throws SQLException {
		getListVe();
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

		Statement st = database.connect();
		String q = "select * from Ve";
		ResultSet rs = st.executeQuery(q);
		ArrayList<Ve> listVe2 = new ArrayList<Ve>();
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

			Ve ve = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh, km, dt);

			listVe2.add(ve);
		}
		return listVe2;
	}

	public ArrayList<Ve> getListHoanVe() throws SQLException {
		ArrayList<Ve> listVe3 = new ArrayList<Ve>();

		Statement st = database.connect();
		String q = """
				SELECT v.*
				FROM Ve AS v
				JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau
				JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon
				JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon
				WHERE v.trangThaiVe = N'hoạt động'
				  AND v.ngayGioDi > GETDATE()
				  AND (
				        (lhd.tenLoaiHoaDon = N'Vé tập thể' AND v.ngayGioDi > DATEADD(HOUR, 24, GETDATE()))
				     OR (lhd.tenLoaiHoaDon = N'Vé cá nhân'  AND v.ngayGioDi > DATEADD(HOUR, 4, GETDATE()))
				);
								""";

		ResultSet rs = st.executeQuery(q);
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

			System.out.println(km.toString());
			Ve v = new Ve();
			v = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh, km, dt);

			listVe3.add(v);
		}
		return listVe3;
	}

	public ArrayList<Ve> getListHoanVeTheoCCCDKhachHang(String cccd) throws SQLException {
		Statement st = database.connect();
		ArrayList<Ve> listVe4 = new ArrayList<Ve>();
		String q = """
				SELECT v.*
				FROM Ve AS v
				JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau
				JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon
				JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon
				WHERE v.trangThaiVe = N'hoạt động'
				  AND v.ngayGioDi > GETDATE()
				  AND (
				        (lhd.tenLoaiHoaDon = N'Vé tập thể' AND v.ngayGioDi > DATEADD(HOUR, 24, GETDATE()))
				     OR (lhd.tenLoaiHoaDon = N'Vé cá nhân'  AND v.ngayGioDi > DATEADD(HOUR, 4, GETDATE()))
				      )
				  AND hd.cccdKhachHangThanhToan = """ + "'" + cccd + "'";
		;

		ResultSet rs = st.executeQuery(q);
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
		return listVe4;
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
		Statement myStmt = database.connect();
		String query = "SELECT v.*\r\n" + "FROM Ve v\r\n" + "JOIN ChiTietHoaDon ct ON v.maVeTau = ct.maVeTau\r\n"
				+ "WHERE v.trangThaiVe = N'hoạt động'\r\n" + "  AND v.ngayGioDi > GETDATE()\r\n" + "  AND v.maVeTau = '"
				+ maVe + "'";
		ResultSet rs = myStmt.executeQuery(query);
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
			System.out.println("lấy dữ liệu vé thành công");
		}

		return v;
	}

	public boolean ThayDoiTrangThaiVe(String mave, String trangthaimoi) throws SQLException {
		Statement st = database.connect();
		String q = "UPDATE Ve SET trangThaiVe = N'" + trangthaimoi + "' WHERE maVeTau = '" + mave + "'";
		int rows = st.executeUpdate(q);
		return rows > 0;
	}
}