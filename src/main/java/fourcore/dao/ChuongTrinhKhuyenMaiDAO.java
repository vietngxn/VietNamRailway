package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuongTrinhKhuyenMaiDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public ChuongTrinhKhuyenMaiDAO() {
	}

	ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>();

	public ArrayList<KhuyenMai> getListKhuyenMai() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from KhuyenMai";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maKhuyenMai = rs.getString(1);
			String tenChuongTrinh = rs.getString(2);
			double giaTriPhanTramKhuyenMai = rs.getDouble(3);
			LocalDateTime ngayBatDau = rs.getTimestamp(4).toLocalDateTime();
			LocalDateTime ngayKetThuc = rs.getTimestamp(5).toLocalDateTime();
			String trangThai = rs.getString(6);
			String dieuKienApDung = rs.getString(7);
			KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenChuongTrinh, trangThai, dieuKienApDung,
					giaTriPhanTramKhuyenMai, ngayBatDau, ngayKetThuc);
			listKhuyenMai.add(khuyenMai);
			// maKhuyenMai,tenChuongTrinh,trangThaiKhuyenMai,dieuKienApDung,iaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc)
		}

		return listKhuyenMai;
	}

	public KhuyenMai getKhuyenMaiBangMa(String maKhuyenMai) throws SQLException {
		Statement myStmt = databaseConnector.connect();
		KhuyenMai km = new KhuyenMai();
		String query = "select * from KhuyenMai WHERE maKhuyenMai = " + "'" + maKhuyenMai + "'";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String ma = rs.getString("maKhuyenMai");
			String ten = rs.getString("tenChuongTrinh");
			String trangThai = rs.getString("trangThaiKhuyenMai");
			String dieuKien = rs.getString("dieuKienApDungKhuyenMai");
			double phanTram = rs.getDouble("giaTriPhanTramKhuyenMai");
			LocalDateTime ngayBD = rs.getTimestamp("ngayBatDau").toLocalDateTime();
			LocalDateTime ngayKT = rs.getTimestamp("ngayKetThuc").toLocalDateTime();

			km = new KhuyenMai(ma, ten, trangThai, dieuKien, phanTram, ngayBD, ngayKT);
			System.out.println("Lấy dữ liệu thành công");
		}
		return km;
	}
}
