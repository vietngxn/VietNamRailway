package fourcore.dao;

import java.sql.*;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.ToaTau;

public class LoaiToaTauDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    public ArrayList<LoaiToaTau> listLoaiToaTau = new ArrayList<>();

	public LoaiToaTauDAO() {
	}

	public ArrayList<LoaiToaTau> getListLoaiToaTau() {
		try {
			Statement myStmt = databaseConnector.connect();
			String query = "SELECT * FROM LoaiToaTau";
			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {
				String maLoaiToaTau = rs.getString(1);
				String tenLoaiToaTau = rs.getString(2);
				LoaiToaTau ltt = new LoaiToaTau(maLoaiToaTau, tenLoaiToaTau);
                listLoaiToaTau.add(ltt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listLoaiToaTau;
	}
    public LoaiToaTau getLoaiToaTau(String maLoaiToaTau) {
        getListLoaiToaTau();
        for (int i = 0; i < listLoaiToaTau.size(); i++) {
            if(listLoaiToaTau.get(i).getMaLoaiToaTau().equals(maLoaiToaTau)) {
                return listLoaiToaTau.get(i);
            }
        }
        return null;
    }

}
