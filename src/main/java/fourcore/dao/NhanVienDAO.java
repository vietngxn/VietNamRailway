package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NhanVienDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<NhanVien> listKhachHang = new ArrayList<NhanVien>();
    Statement st = databaseConnector.connect();

    public NhanVienDAO() throws SQLException {
        goiDao();
    }
    public void goiDao(){
        System.out.println("Nhan vien dao");
    }

    public ArrayList<NhanVien> getListNhanVien() throws SQLException {

		String q = "Select * from NhanVien";
		ResultSet rs = st.executeQuery(q);
		while (rs.next()) {
			String maNhanVien = rs.getString(1);
			String hoTen = rs.getString(2);
			String sdt = rs.getString(7);
			String gioiTinh = rs.getString(10);
			String email = rs.getString(6);
			String cccd = rs.getString(11);
			String tinhTrang = rs.getString(9);
			NhanVien nv1 = new NhanVien(maNhanVien, hoTen, sdt, gioiTinh, email, cccd, tinhTrang);
			listKhachHang.add(nv1);
		}

		return listKhachHang;
	}

	public NhanVien getNhanVienByMa(String manv) throws SQLException {
        String q = "Select * from NhanVien where maNhanVien = " + "'" + manv + "'";
		ResultSet rs = st.executeQuery(q);
		while (rs.next()) {
			String maNhanVien = rs.getString(1);
			String hoTen = rs.getString(2);
			String sdt = rs.getString(7);
			String gioiTinh = rs.getString(10);
			String email = rs.getString(6);
			String cccd = rs.getString(11);
			String tinhTrang = rs.getString(9);
			NhanVien nv1 = new NhanVien(maNhanVien, hoTen, sdt, gioiTinh, email, cccd, tinhTrang);
			return nv1;
		}

		return null;
	}
}
