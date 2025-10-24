package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;

public class Tau_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

	public Tau_DAO() {
	}
	ArrayList<Tau> list = new ArrayList<>();

	public ArrayList<Tau> getList() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String sql = """
				    SELECT t.maTau, t.tenTau, lt.maLoaiTau, lt.tenLoaiTau, lt.giaCuoc
				    FROM Tau t
				    JOIN LoaiTau lt ON t.maLoaiTau = lt.maLoaiTau
				""";
		ResultSet rs = myStmt.executeQuery(sql);
		while (rs.next()) {
			String maTau = rs.getString("maTau");
            String tenTau = rs.getString("tenTau");
            
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
        Statement myStmt = databaseConnector.connect();
        ArrayList<Tau> listTau = getList();
        String sql = """
				    SELECT t.maTau, t.tenTau, lt.maLoaiTau, lt.tenLoaiTau, lt.giaCuoc
				    FROM Tau t
				    JOIN LoaiTau lt ON t.maLoaiTau = lt.maLoaiTau
				""";
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            String maTau = rs.getString("maTau");
            String tenTau = rs.getString("tenTau");

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
}
