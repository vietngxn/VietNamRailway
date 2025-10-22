package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

public class KhuyenMai_Dao {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public KhuyenMai_Dao() {
	}

	ArrayList<KhuyenMai> list = new ArrayList<>();

	public ArrayList<KhuyenMai> getList() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from KhuyenMai";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String ma = rs.getString("maKhuyenMai");
			String ten = rs.getString("tenChuongTrinh");
			String trangThai = rs.getString("trangThaiKhuyenMai");
			String dieuKien = rs.getString("dieuKienApDungKhuyenMai");
			double phanTram = rs.getDouble("giaTriPhanTramKhuyenMai");
			LocalDateTime ngayBD = rs.getTimestamp("ngayBatDau").toLocalDateTime();
			LocalDateTime ngayKT = rs.getTimestamp("ngayKetThuc").toLocalDateTime();

			list.add(new KhuyenMai(ma, ten, trangThai, dieuKien, phanTram, ngayBD, ngayKT));
			System.out.println("Lấy dữ liệu thành công");
		}
		return list;
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
