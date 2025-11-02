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
    Statement myStmt = databaseConnector.connect();
	ArrayList<LoaiTuongTacVe> list = getList();
	
	public LoaiTuongTac_Dao() throws SQLException {
        goiDAO();
		getList();
	}

    public void goiDAO(){
        System.out.println("loai tuong tac dao");
    }
    
	public ArrayList<LoaiTuongTacVe> getList() throws SQLException {
		ArrayList<LoaiTuongTacVe> list1 = new ArrayList<LoaiTuongTacVe>();
		String query = "select * from LoaiTuongTacVe";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maLoai = rs.getString(1);
			String tenLoai = rs.getString(2);
			LoaiTuongTacVe lt = new LoaiTuongTacVe(maLoai, tenLoai);
			list1.add(lt);
		}
		return list1;
	}

	public LoaiTuongTacVe getLoaiTheoMa(String maTuongTac) throws SQLException {
		LoaiTuongTacVe ltt = new LoaiTuongTacVe();
		String query = "select * from LoaiTuongTacVe where maLoaiTuongTac = " + "'" + maTuongTac + "'";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maLoai = rs.getString(1);
			String tenLoai = rs.getString(2);
			ltt = new LoaiTuongTacVe(maLoai, tenLoai);
		}
		return ltt;
	}
}
