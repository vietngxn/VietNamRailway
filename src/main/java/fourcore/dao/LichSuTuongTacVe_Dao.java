package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LichSuTuongTacVe;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.LoaiTuongTacVe;
import fourcore.Entity.Tau;
import fourcore.Entity.Ve;

public class LichSuTuongTacVe_Dao {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public LichSuTuongTacVe_Dao() {
	}

	ArrayList<LichSuTuongTacVe> list = new ArrayList<>();
	Ve_Dao daoVe = new Ve_Dao();
	ArrayList<Ve> listVeEx = daoVe.themNhieuVeTau();
	
	public ArrayList<LichSuTuongTacVe> getList() throws SQLException {
		LoaiTuongTac_Dao lttDao = new LoaiTuongTac_Dao();
		ArrayList<LoaiTuongTacVe> listLoaiTuongTac = lttDao.getList();
		Statement myStmt = databaseConnector.connect();
		String query = "select * from LichSuTuongTacVe";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maTuongTac = rs.getString(1);
			String maLoaiTuongTac = rs.getString(2);
			String maVeTau = rs.getString(3);
			double giaTriChenhLech = rs.getDouble(4);
			LocalDateTime ngayTuongTac = rs.getTimestamp(5).toLocalDateTime();
			
			LoaiTuongTacVe tt = new LoaiTuongTacVe();
			
			for (LoaiTuongTacVe x : listLoaiTuongTac) {
				if (x.getMaLoaiTuongTac().equalsIgnoreCase(maLoaiTuongTac)) {
					tt = x;
				}
			}
			
			LichSuTuongTacVe ls = new LichSuTuongTacVe(maTuongTac, tt,listVeEx.get(0), giaTriChenhLech, ngayTuongTac);
			list.add(ls);
			System.out.println("Lấy dữ liệu thành công");
		}
		return list;
	}
}