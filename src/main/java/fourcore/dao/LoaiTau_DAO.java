package fourcore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiTau;

public class LoaiTau_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<LoaiTau> listLoaiTau =  new ArrayList<>();
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
    public ArrayList<LoaiTau> getListLoaiTau() throws SQLException {
        try {

            listLoaiTau.removeAll(listLoaiTau);
            Statement myStmt = databaseConnector.connect();
            String query = "select * from LoaiTau";
            ResultSet rs = myStmt.executeQuery(query);
            while (rs.next()) {
                String maLoaiTau = rs.getString(1);
                String tenLoaiTau = rs.getString(2);
                double giaCuoc = rs.getDouble(3);
                LoaiTau loaiTau = new LoaiTau(maLoaiTau, tenLoaiTau, giaCuoc);
                listLoaiTau.add(loaiTau);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listLoaiTau;
    }
    public LoaiTau getLoaiTauTheoMa(String maLoaiTau) throws SQLException {
        listLoaiTau = getListLoaiTau();
        for(LoaiTau lt : listLoaiTau) {
            if(lt.getMaLoaiTau().equalsIgnoreCase(maLoaiTau)) return lt;
        }
        return null;
    }

    public boolean capNhatGiaCuocLoaiTau(String maLoaiTau, double giaCuoc) {
        int n = 0;
        String sql = "Update LoaiTau set giaCuoc = ? where maLoaiTau = ?";
        try {
            PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection().prepareStatement(sql);
            ps.setDouble(1, giaCuoc);
            ps.setString(2, maLoaiTau);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
