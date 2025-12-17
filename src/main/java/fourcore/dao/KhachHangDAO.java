package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class KhachHangDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
    Statement st;
    public ArrayList<String> getDoiTuong() throws SQLException
    {
        String q = "select  dt.tenDoiTuongGiamGia\r\n"
                + "			 from DoiTuongGiamGia dt";

        ArrayList<String> listdoituong = new ArrayList<>();
        ResultSet  rs = st.executeQuery(q);
        while(rs.next())
        {
            String a = rs.getString(1);
            listdoituong.add(a);
        }
        return listdoituong;

    }
    public KhachHangDAO() throws SQLException {
        st = databaseConnector.connect();
    }

    public ArrayList<KhachHang> getListKhachHang() throws SQLException {
		String q = "select * from KhachHang";
		ResultSet rs = st.executeQuery(q);
		while (rs.next()) {
			String makh = rs.getString(1);
			String hoten = rs.getString(2);
			String sdt = rs.getString(3);
			String email = rs.getString(4);
			String cccd = rs.getString(5);
			String pp = rs.getString(6);
			String doiTuong = rs.getString(7);
			KhachHang kh = new KhachHang(makh, hoten, sdt, email, cccd, pp, doiTuong);
			listKhachHang.add(kh);
		}
		return listKhachHang;
	}

	public KhachHang getKhachHangByMa(String maKhachHang) throws SQLException {
		String q = "select * from KhachHang where maKhachHang = '" + maKhachHang + "'";
		ResultSet rs = st.executeQuery(q);

		KhachHang kh = null;
		if (rs.next()) {
			String makh = rs.getString(1);
			String hoten = rs.getString(2);
			String sdt = rs.getString(3);
			String email = rs.getString(4);
			String cccd = rs.getString(5);
			String pp = rs.getString(6);
			String doiTuong = rs.getString(7);

			kh = new KhachHang(makh, hoten, sdt, email, cccd, pp, doiTuong);
		}

		return kh;
	}
    public String getMaKH() throws SQLException {
        String query = "SELECT COUNT(*) AS soLuong FROM KhachHang";
        ResultSet rs = st.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String maKH = String.format("KH%04d", soLuong+1);
        return maKH;
    }
	public boolean themKhachHang(KhachHang kh) throws SQLException {
	    String q = "INSERT INTO KhachHang VALUES ('"
	            + kh.getMaKhachHang() + "', N'" 
	            + kh.getHoten() + "', '" 
	            + kh.getSdt() + "', '" 
	            + kh.getEmail() + "', '" 
	            + kh.getCccd() + "', '" 
	            + null + "', N'"
	            + kh.getDoiTuong()
                + "','false')";
	    
	    int kq = st.executeUpdate(q);
	    return kq > 0; 
	}
	
	public boolean xoaKhachHang(String maKH) throws SQLException {
	    String sql = "DELETE FROM KhachHang WHERE maKhachHang = '" + maKH + "'";
	    int kq = st.executeUpdate(sql);
	    return kq > 0;
	}
	public boolean capNhatKhachHang(KhachHang kh) throws SQLException {
	    String q = "UPDATE KhachHang SET "
	            + "hoTen = N'" + kh.getHoten() + "', "
	            + "sdt = '" + kh.getSdt() + "', "
	            + "email = '" + kh.getEmail() + "', "
	            + "cccd = '" + kh.getCccd() + "', "
	            + "passport = '" + kh.getPassport() + "', "
	            + "doiTuong = N'" + kh.getDoiTuong() + "' "
	            + "WHERE maKhachHang = '" + kh.getMaKhachHang() + "'";
	    
	    int kq = st.executeUpdate(q);
	    return kq > 0; 
	}
	
	
	public KhachHang getKhachHangTheoCCCD(String cccd) throws SQLException {
	    
	    String q = "SELECT * FROM KhachHang WHERE cccd = '" + cccd + "'";
	    ResultSet rs = st.executeQuery(q);

	    if (rs.next()) {
	        KhachHang kh = new KhachHang();
	        kh.setMaKhachHang(rs.getString(1));  
	        kh.setHoten(rs.getString(2));
	        kh.setSdt(rs.getString(3));          
	        kh.setEmail(rs.getString(4));        
	        kh.setCccd(rs.getString(5));         
	        kh.setPassport(rs.getString(6));     
	        kh.setDoiTuong(rs.getString(7));     
	        return kh;
	    }
	    return null;
	}

	
	public boolean checkCCCD(String cccd) throws SQLException
	{
		String  q = "select * \r\n"
				+ "from KhachHang kh\r\n"
				+ "where kh.cccd = '"+cccd+"'";
		ResultSet rs = st.executeQuery(q);
		return rs.next();
	}
	
	public boolean checkPP(String pp) throws SQLException
	{
		String  q = "select * \r\n"
				+ "from KhachHang kh\r\n"
				+ "where kh.passport = '"+pp+"'";
		ResultSet rs = st.executeQuery(q);
		return rs.next();
	}

}
