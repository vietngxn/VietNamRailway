package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.Tang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TangDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public ArrayList<Tang> listTang = new ArrayList<>();

    TangDAO() throws SQLException {
        getListTang();
    }
    public ArrayList<Tang> getListTang() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "SELECT * FROM Tang";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maTang = rs.getString(1);
            int soTang =  rs.getInt(2);
            double giaTang = rs.getDouble(3);
            Tang t = new Tang(maTang,soTang,giaTang);
            listTang.add(t);
        }
        return listTang;
    }

    public Tang getTang(String maTangInput) throws SQLException {

            for (Tang t : listTang){
                if(t.getMaTang().equals(maTangInput)){
                    return t;
                }
            }
        return null;
    }
}
