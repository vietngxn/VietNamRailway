package fourcore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.GheTrenChuyenTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.ToaTau;

public class GheTrenChuyenTau_dao {
	ChuyenTauDAO chuyentaudao = new ChuyenTauDAO();
	GheNgoiDAO ghengoidao = new GheNgoiDAO();
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

	public Integer getMaGheTrenChuyenTauCuoiCung() {
		int maGTCT = 0;
		try {

			String sql = "select count(*) as TongSoGheTrenChuyenTau from GheTrenChuyenTau";
			ResultSet rs = myStmt.executeQuery(sql);
			if (rs.next()) {
				maGTCT = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public ArrayList<GheTrenChuyenTau> getListGheTrenChuyenTauByMaCT(String maChuyenTau) {

	    ArrayList<GheTrenChuyenTau> list = new ArrayList<>();

	    String sql =
	        "SELECT maGheTrenChuyenTau, maChuyenTau, maGheNgoi, trangThaiGhe, giaTienGhe \n" +
	        "FROM GheTrenChuyenTau \n" +
	        "WHERE maChuyenTau = ?";

	    try {
	        PreparedStatement ps = databaseConnector
	                .connect()
	                .getConnection()
	                .prepareStatement(sql);

	        ps.setString(1, maChuyenTau);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String maGheTrenChuyenTau = rs.getString("maGheTrenChuyenTau");
	            String maCT = rs.getString("maChuyenTau");
	            String maGheNgoi = rs.getString("maGheNgoi");
	            String trangThaiGhe = rs.getString("trangThaiGhe");
	            double giaTienGhe = rs.getDouble("giaTienGhe");

	            GheTrenChuyenTau ghe = new GheTrenChuyenTau(
	                maGheTrenChuyenTau,
	                trangThaiGhe,
	                giaTienGhe,
	                chuyentaudao.getChuyenTauBangMa(maChuyenTau),
	                ghengoidao.getGheBangMaGhe(maGheNgoi)
	            );

	            list.add(ghe);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	public boolean deleteGheTrenChuyenTauByMa(String maGheTrenChuyenTau) {

	    String sql =
	        "DELETE FROM GheTrenChuyenTau \n" +
	        "WHERE maGheTrenChuyenTau = ?";

	    int n = 0;

	    try {
	        PreparedStatement ps = databaseConnector
	                .connect()
	                .getConnection()
	                .prepareStatement(sql);

	        ps.setString(1, maGheTrenChuyenTau);

	        n = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return n > 0;
	}


}
