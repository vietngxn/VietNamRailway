package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.DoiTuongGiamGia;

public class DoiTuongGiamGia_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    Statement myStmt = databaseConnector.connect();

	public DoiTuongGiamGia_DAO() throws SQLException {
	}

	ArrayList<DoiTuongGiamGia> list = new ArrayList<>();

	public ArrayList<DoiTuongGiamGia> getList() throws SQLException {
		String query = "select * from DoiTuongGiamGia";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			DoiTuongGiamGia dt = new DoiTuongGiamGia(rs.getString("maDoiTuongGiamGia"),
					rs.getString("tenDoiTuongGiamGia"), rs.getString("trangThaiGiamGia"),
					rs.getDouble("giaTriPhanTramGiamGia"));
//			System.out.println("Lấy dữ liệu thành công");
			list.add(dt);
		}
		return list;
	}

	public DoiTuongGiamGia getDoiTuongGiamGiaBangMaDT(String maDoiTuong) throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from DoiTuongGiamGia WHERE maDoiTuongGiamGia = " + "'" + maDoiTuong + "'";
		DoiTuongGiamGia dt = null;
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			dt = new DoiTuongGiamGia(rs.getString("maDoiTuongGiamGia"), rs.getString("tenDoiTuongGiamGia"),
					rs.getString("trangThaiGiamGia"), rs.getDouble("giaTriPhanTramGiamGia"));

		}
		return dt;
	}
}
