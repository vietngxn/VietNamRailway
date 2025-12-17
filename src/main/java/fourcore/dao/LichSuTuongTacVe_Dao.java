package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LichSuTuongTacVe;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.LoaiTuongTacVe;
import fourcore.Entity.Tau;
import fourcore.Entity.Ve;

public class LichSuTuongTacVe_Dao {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	Statement myStmt = databaseConnector.connect();
	ArrayList<LichSuTuongTacVe> list = getList();

	public LichSuTuongTacVe_Dao() throws SQLException {
		goiDAO();
		getList();

	}

	public void goiDAO() {
		System.out.println("lich su tuong tac dao");
	}

	public LichSuTuongTacVe_Dao(int x) throws SQLException {
		goiDAO();
	}

	public ArrayList<LichSuTuongTacVe> getList() throws SQLException {
		LoaiTuongTac_Dao lttDao = new LoaiTuongTac_Dao();
		VeDAO veDao = new VeDAO();
		ArrayList<LichSuTuongTacVe> list1 = new ArrayList<LichSuTuongTacVe>();
		String query = "select * from LichSuTuongTacVe";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maTuongTac = rs.getString(1);
			String maLoaiTuongTac = rs.getString(2);
			String maVeTau = rs.getString(3);
			double giaTriChenhLech = rs.getDouble(4);
			LocalDateTime ngayTuongTac = rs.getTimestamp(5).toLocalDateTime();
			LoaiTuongTacVe tt = lttDao.getLoaiTheoMa(maLoaiTuongTac);
			Ve v = veDao.getVeBangMaVe(maVeTau);
			LichSuTuongTacVe ls = new LichSuTuongTacVe(maTuongTac, tt, v, giaTriChenhLech, ngayTuongTac);
			list1.add(ls);
//			System.out.println("Lấy dữ liệu thành công");
		}

		return list1;
	}

	public int getSoLuongLichSuTuongTacVe() throws SQLException {
		String countQuery = "SELECT COUNT(*) AS soLuong FROM LichSuTuongTacVe";
		int soLuongHoaDon = 0;
		ResultSet rs = myStmt.executeQuery(countQuery);
		if (rs.next()) {
			soLuongHoaDon = rs.getInt("soLuong");
		}
		return soLuongHoaDon;
	}

	public boolean themLichSuTuongTacVe(LichSuTuongTacVe lstt) throws SQLException {
		LocalDateTime ngay = lstt.getNgayTuongTac();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String ngayFormatted = ngay.format(formatter);
		String query = "INSERT INTO LichSuTuongTacVe (maTuongTac, maLoaiTuongTac, maVeTau, giaTriChenhLech, ngayTuongTac) VALUES (N'"
				+ lstt.getMaTuongTac() + "', N'" + lstt.getLoaiTuongTacVe().getMaLoaiTuongTac() + "', N'"
				+ lstt.getVeTau().getMaVeTau() + "', " + lstt.getGiaTriChenhLech() + ", '" + ngayFormatted + "')";
		boolean check = myStmt.execute(query);
		return check;
	}

}
