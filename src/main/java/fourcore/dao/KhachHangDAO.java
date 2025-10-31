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
	public boolean themKhachHang(KhachHang kh) throws SQLException {
	    String q = "INSERT INTO KhachHang VALUES ('"
	            + kh.getMaKhachHang() + "', N'" 
	            + kh.getHoten() + "', '" 
	            + kh.getSdt() + "', '" 
	            + kh.getEmail() + "', '" 
	            + kh.getCccd() + "', '" 
	            + kh.getPassport() + "', N'" 
	            + kh.getDoiTuong() + "')";
	    
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
}
