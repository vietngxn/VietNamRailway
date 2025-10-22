package fourcore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.Ga;

public class GaTauDao {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public GaTauDao() {
	}

	public ArrayList<Ga> getList() {
		ArrayList<Ga> list = new ArrayList<>();

		try {
			Statement myStmt = databaseConnector.connect();
			String query = String.format("""
					Select * from Ga
					""");

			ResultSet rs = myStmt.executeQuery(query);
			while (rs.next()) {
				String maGa = rs.getString("maGa");
				String tenGa = rs.getString("tenGa");
				LocalDateTime thoiGian = rs.getTimestamp("thoiGian").toLocalDateTime();
				double cuLy = rs.getDouble("cuLy");
				Ga ga = new Ga(maGa, tenGa, cuLy, thoiGian);
				list.add(ga);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Ga> getListGaChoHanhTrinh(String maHanhTrinh) {
		ArrayList<Ga> list = new ArrayList<>();

		try {
			Statement myStmt = databaseConnector.connect();
			String query = String.format("""
					Select * from Ga
					""");

			ResultSet rs = myStmt.executeQuery(query);
			while (rs.next()) {
				String maGa = rs.getString("maGa");
				String tenGa = rs.getString("tenGa");
				LocalDateTime thoiGian = rs.getTimestamp("thoiGian").toLocalDateTime();
				double cuLy = rs.getDouble("cuLy");
				Ga ga = new Ga(maGa, tenGa, cuLy, thoiGian);
				list.add(ga);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
