package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.Ga;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.LoaiToaTau;

public class HanhTrinh_DAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    Statement myStmt;
    public void goiDAO(){
        System.out.println("Hanh trinh dao");
    }
	public HanhTrinh_DAO() throws SQLException {
        goiDAO();
        myStmt = databaseConnector.connect();
	}

	public ArrayList<HanhTrinh> getList() {
		ArrayList<HanhTrinh> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM HanhTrinh";
			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {
				String maHanhTrinh = rs.getString("maHanhTrinh");
				String tenHanhTrinh = rs.getString("tenHanhTrinh");
				
				HanhTrinh ht = new HanhTrinh(maHanhTrinh, tenHanhTrinh, null);
				list.add(ht);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    public HanhTrinh getById(String maHanhTrinh) throws SQLException
    {
        for(HanhTrinh ht : getList()){
            if(ht.getMaHanhTrinh().equals(maHanhTrinh)){
                return ht;
            }
        }
        return null;
    }
    public ArrayList<Ga> getListGaByMaHanhTrinh(String maHanhTrinhInput){
        ArrayList<Ga> listGa = new ArrayList<>();
        try {
            String query = "select htg.maGa, g.tenGa from HanhTrinhGa htg, Ga g where htg.maGa = g.maGa and htg.maHanhTrinh = '" + maHanhTrinhInput + "'";
            ResultSet rs = myStmt.executeQuery(query);

            while (rs.next()) {
                String maGa = rs.getString("maGa");
                String tenGa = rs.getString("tenGa");

                Ga ga = new Ga(maGa, tenGa);
                listGa.add(ga);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGa;
    }


	
	
}