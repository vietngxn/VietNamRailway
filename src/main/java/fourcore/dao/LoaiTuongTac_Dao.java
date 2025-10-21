package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LoaiTuongTacVe;

public class LoaiTuongTac_Dao {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public LoaiTuongTac_Dao() {
	}

	ArrayList<LoaiTuongTacVe> list = new ArrayList<>();

	public ArrayList<LoaiTuongTacVe> getList() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from LoaiTuongTacVe";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maLoai = rs.getString(1);
			String tenLoai = rs.getString(2);
			LoaiTuongTacVe lt = new LoaiTuongTacVe(maLoai, tenLoai);
			list.add(lt);
		}
		return list;
	}
}
