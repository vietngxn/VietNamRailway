package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiTau;

public class LoaiTau_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public LoaiTau_DAO() {}

	ArrayList<LoaiTau> list = new ArrayList<>();

	public ArrayList<LoaiTau> getList() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from LoaiTau";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String ma = rs.getString("maLoaiTau");
			String ten = rs.getString("tenLoaiTau");
			double gia = rs.getDouble("giaCuoc");
			list.add(new LoaiTau(ma, ten, gia));
		}
		return list;
	}
}
