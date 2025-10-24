package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiGhe;
import fourcore.Entity.Tang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoaiGheDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<LoaiGhe> listLoaiGhe = new ArrayList<>();

    public LoaiGheDAO() throws SQLException {
        getListLoaiGhe();
    }

    public ArrayList<LoaiGhe> getListLoaiGhe() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = " SELECT * FROM LoaiGhe";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maLoaiGhe = rs.getString(1);
            String tenLoaiGhe =  rs.getString(2);
            double giaTang = rs.getDouble(3);
            LoaiGhe loaiGhe = new LoaiGhe(maLoaiGhe,tenLoaiGhe,giaTang);
            listLoaiGhe.add(loaiGhe);
        }
        return listLoaiGhe;
    }

    public LoaiGhe getLoaiGhe(String maLoaiGheInput) throws SQLException {
        for (int i = 0; i < listLoaiGhe.size(); i++) {
            if (listLoaiGhe.get(i).getMaLoaiGhe().equals(maLoaiGheInput)) {
                return listLoaiGhe.get(i);
            }
        }
        return null;
    }
}
