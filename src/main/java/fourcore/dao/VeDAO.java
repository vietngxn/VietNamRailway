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
	ArrayList<Ve> listVe = new ArrayList<Ve>();
	private ArrayList<Ve> listVe1;

	public ArrayList<Ve> getListVe() throws SQLException {
		Statement st = database.connect();
		String q = "select * from Ve";
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

			Ve ve = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh,
					new KhuyenMai(maKhuyenMai), new DoiTuongGiamGia(maDoiTuongGiamGia));

			listVe.add(ve);
		}
		return listVe;
	}

	public KhachHang getKhachHang(String maVeTau) throws SQLException {
		KhachHang kh = new KhachHang();
		listVe1 = getListVe();
		for (Ve ve : listVe1) {
			if (ve.getMaVeTau().equalsIgnoreCase(maVeTau)) {
				kh = ve.getKhachHang();
				break;
			}
		}
		return kh;
	}

	public Ve getVeBangMaVe(String maVe) throws SQLException {
		Ve v = new Ve();
		Statement myStmt = database.connect();
		String query = "SELECT * FROM Ve WHERE maVeTau = '" + maVe + "'";
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
			
			KhuyenMai_Dao kmDAO = new KhuyenMai_Dao();
			KhuyenMai km = kmDAO.getKhuyenMaiBangMa(maKhuyenMai);
			v = new Ve(maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe,
					maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau), kh,
					km, dt);
			System.out.println("lấy dữ liệu vé thành công");
		}

		return v;
	}

}
