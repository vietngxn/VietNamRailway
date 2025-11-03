package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.NhanVien;

public class ChucVuDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	private Statement st;
	
	public ChucVuDAO() throws SQLException
	{
		st = databaseConnector.connect();
	}
	
	public String getTenChucVu(String maChucVu) throws SQLException
	{
		String tenChucVu = null;
	    String sql = "SELECT tenChucVu FROM ChucVu WHERE maChucVu = '" + maChucVu + "'";
	    ResultSet rs = st.executeQuery(sql);
	    
	    if (rs.next()) {
	        tenChucVu = rs.getString(1);
	    }
	    
	    return tenChucVu;
	}
	

	
}
