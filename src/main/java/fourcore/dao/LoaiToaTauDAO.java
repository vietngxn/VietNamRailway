package fourcore.dao;

import java.sql.*;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiToaTau;

public class LoaiToaTauDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public LoaiToaTauDAO() {
	}

	public ArrayList<LoaiToaTau> getList() {
		ArrayList<LoaiToaTau> list = new ArrayList<>();
		try {
			Statement myStmt = databaseConnector.connect();
			String query = "SELECT * FROM LoaiToaTau";
			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {
				String maLoaiToaTau = rs.getString("maLoaiToaTau");
				String tenLoaiToaTau = rs.getString("tenLoaiToaTau");
				double giaCuoc = rs.getDouble("giaCuoc");
				LoaiToaTau ltt = new LoaiToaTau(maLoaiToaTau, tenLoaiToaTau, giaCuoc);
				list.add(ltt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
