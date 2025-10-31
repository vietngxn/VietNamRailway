package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhoangTau;
import fourcore.Entity.LoaiGhe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KhoangTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public ArrayList<KhoangTau> listKhoangTau = new ArrayList<>();
    Statement myStmt = databaseConnector.connect();

    public KhoangTauDAO() throws SQLException {
        getListKhoangTau();
    }
    public ArrayList<KhoangTau> getListKhoangTau() throws SQLException {
        String query = "  SELECT * FROM KhoangTau";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maLoaiGhe = rs.getString(1);
            int soKhoang = rs.getInt(2);
            KhoangTau kt = new KhoangTau(maLoaiGhe, soKhoang);
            listKhoangTau.add(kt);
        }
        return listKhoangTau;
    }

    public KhoangTau getKhoang(String maKhoangInput) throws SQLException {
        for (int i = 0; i < listKhoangTau.size(); i++) {
            if (listKhoangTau.get(i).getMaKhoang().equals(maKhoangInput)) {
                return listKhoangTau.get(i);
            }
        }
        return null;
    }
}
