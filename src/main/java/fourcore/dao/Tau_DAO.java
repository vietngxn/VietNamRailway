package fourcore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;

public class Tau_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    LoaiTau_DAO loaitaudao = new LoaiTau_DAO();
    ArrayList<Tau> listTau = new ArrayList<>();
    Statement myStmt = databaseConnector.connect();
    public Tau_DAO() throws SQLException {
        goiDAO();
	}
    public void goiDAO(){
        System.out.println("Tau dao");
    }
	ArrayList<Tau> list = new ArrayList<>();

	public ArrayList<Tau> getList() throws SQLException {
		String sql = """
				      SELECT t.maTau, t.bienSoTau, lt.maLoaiTau, lt.tenLoaiTau, lt.giaCuoc
                      FROM Tau t
                      JOIN LoaiTau lt ON t.maLoaiTau = lt.maLoaiTau
				""";
		ResultSet rs = myStmt.executeQuery(sql);
		while (rs.next()) {
			String maTau = rs.getString("maTau");
            String tenTau = rs.getString("bienSoTau");
            
            LoaiTau lt = new LoaiTau(
                    rs.getString("maLoaiTau"),
                    rs.getString("tenLoaiTau"),
                    rs.getDouble("giaCuoc")
                );
            Tau tau = new Tau(maTau, tenTau, lt);
			System.out.println("Lấy dữ liệu thành công");
		}
		return list;
	}
    public Tau getTauByMaTau(String maTauInput) throws SQLException {
        ArrayList<Tau> listTau = getList();
        String sql = """
				    SELECT t.maTau, t.bienSoTau, lt.maLoaiTau, lt.tenLoaiTau, lt.giaCuoc
				    FROM Tau t
				    JOIN LoaiTau lt ON t.maLoaiTau = lt.maLoaiTau
				""";
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            String maTau = rs.getString("maTau");
            String tenTau = rs.getString("bienSoTau");

            LoaiTau lt = new LoaiTau(
                    rs.getString("maLoaiTau"),
                    rs.getString("tenLoaiTau"),
                    rs.getDouble("giaCuoc")
            );
            Tau tau = new Tau(maTau, tenTau, lt);
            System.out.println("Lấy dữ liệu thành công");
            listTau.add(tau);
        }
        for(Tau t : listTau){
            if(t.getMaTau().equals(maTauInput)){
                return t;
            }
        }
        return null;
    }
    public ArrayList<Tau> getListTauTheoHanhTrinh(String maHanhTrinh) {
        ArrayList<Tau> listTauTheoHanhTrinh = new ArrayList<>();
        String sql = "Select * From Tau where maLoaiTau = ?";
        try {
            PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
            if(maHanhTrinh.equalsIgnoreCase("HT01")) ps.setString(1, "LT01");
            else if(maHanhTrinh.equalsIgnoreCase("HT02")) ps.setString(1, "LT02");
            else if(maHanhTrinh.equalsIgnoreCase("HT03")) ps.setString(1, "LT03");
            else if(maHanhTrinh.equalsIgnoreCase("HT09")) ps.setString(1, "LT09");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String maLoaiTau = rs.getString(3);
                LoaiTau loaiTau = loaitaudao.getLoaiTauTheoMa(maLoaiTau);
                Tau tau = new Tau(maTau, tenTau, loaiTau);
                listTauTheoHanhTrinh.add(tau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTauTheoHanhTrinh;
    }
    public ArrayList<Tau> getListTau() throws SQLException {
        try {

            listTau.removeAll(listTau);
            String query = "select * from Tau";
            ResultSet rs = myStmt.executeQuery(query);
            while (rs.next()) {
                String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String maLoaiTau = rs.getString(3);
                LoaiTau loaiTau = loaitaudao.getLoaiTauTheoMa(maLoaiTau);
                Tau Tau = new Tau(maTau, tenTau, loaiTau);
                listTau.add(Tau);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTau;
    }

    public Tau getTauTheoMa(String maTau) throws SQLException {
        listTau = getListTau();
        for(Tau t : listTau) {
            if(t.getMaTau().equalsIgnoreCase(maTau)) return t;
        }
        return null;
    }
}
