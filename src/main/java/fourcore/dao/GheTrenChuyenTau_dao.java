package fourcore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.GheTrenChuyenTau;

public class GheTrenChuyenTau_dao {

	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau = new ArrayList<>();
	Statement myStmt;

	public GheTrenChuyenTau_dao() throws SQLException {
		myStmt = databaseConnector.connect();
		goiDAO();
		listGheTrenChuyenTau = new ArrayList<>();
	}

	public void goiDAO() {
		System.out.println("ghe tren chuyen tau dao");
	}

	public String getMaGheTrenChuyenTauCuoiCung() {
		String maGTCT = null;
		try {

			String sql = "Select top 1 maGheTrenChuyenTau From GheTrenChuyenTau Order By maGheTrenChuyenTau DESC";
			ResultSet rs = myStmt.executeQuery(sql);
			if (rs.next()) {
				maGTCT = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (maGTCT == null)
			maGTCT = "GTCT0000";
		return maGTCT;
	}

	public boolean addGheTrenChuyenTauVaoDB(ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau) {
		String sql = "Insert Into GheTrenChuyenTau Values (?, ?, ?, ?, ?)";
		int n = 0;
		try {
			PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection()
					.prepareStatement(sql);
			for (GheTrenChuyenTau gtct : listGheTrenChuyenTau) {
				ps.setString(1, gtct.getMaGheTrenChuyenTau());
				ps.setString(2, gtct.getChuyenTau().getMaChuyenTau());
				ps.setString(3, gtct.getGheNgoi().getMaGheNgoi());
				ps.setDouble(4, gtct.getGiaTienGhe());
				ps.setString(5, gtct.getTrangThaiGhe());
				ps.addBatch();
			}
			int[] result = ps.executeBatch();
			for (int num : result) {
				n += num;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean thayDoiTrangThaiGheConTrong(String maVe, int soGhe) throws SQLException {
		String q = """
				    UPDATE gtc
				    SET gtc.trangThaiGhe = N'Còn trống'
				    FROM Ve v
				    JOIN ChuyenTau ct ON v.maChuyenTau = ct.maChuyenTau
				    JOIN GheTrenChuyenTau gtc ON ct.maChuyenTau = gtc.maChuyenTau
				    JOIN GheNgoi gn ON gtc.maGheNgoi = gn.maGheNgoi
				    WHERE v.maVeTau = N'%s' AND v.soGhe = N'%s'
				""".formatted(maVe, soGhe);

		int rows = myStmt.executeUpdate(q);
		return rows > 0;
	}

}
