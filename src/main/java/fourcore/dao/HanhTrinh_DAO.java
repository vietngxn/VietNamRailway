package fourcore.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.LoaiToaTau;

public class HanhTrinh_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public HanhTrinh_DAO() {
	}

	public ArrayList<HanhTrinh> getList() {
		ArrayList<HanhTrinh> list = new ArrayList<>();
		try {
			Statement myStmt = databaseConnector.connect();
			String query = "SELECT * FROM HanhTrinh";
			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {
				String maHanhTrinh = rs.getString("maHanhTrinh");
				String tenHanhTrinh = rs.getString("tenHanhTrinh");
				
				HanhTrinh ht = new HanhTrinh(maHanhTrinh, tenHanhTrinh, null);
				list.add(ht);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
