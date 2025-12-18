package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChucVu;
import fourcore.Entity.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NhanVienDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
    Statement st = databaseConnector.connect();

    public NhanVienDAO() throws SQLException {
        goiDao();
    }
    public void goiDao(){
        System.out.println("Nhan vien dao");
    }

    public ArrayList<NhanVien> getListNhanVien() throws SQLException {

    	ArrayList<NhanVien> listKhachHang = new ArrayList<NhanVien>();
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
    public NhanVien getNhanVienByMa1(String manv) throws SQLException {
        String q = "SELECT nv.maNhanVien, nv.hoTen, nv.maChucVu, nv.ngaySinh, nv.diaChi, nv.email, " +
                "nv.sdt, nv.ngayVaoLam, nv.tinhTrangLamViec, nv.gioiTinh, nv.cccd, nv.isRemove, cv.tenChucVu " +
                "FROM NhanVien nv JOIN ChucVu cv ON nv.maChucVu = cv.maChucVu " +
                "WHERE nv.maNhanVien = '" + manv + "'";

        Statement st = databaseConnector.connect();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            String maNhanVien = rs.getString(1);
            String hoTen = rs.getString(2);
            String maChucVu = rs.getString(3);
            LocalDate ngaySinh = rs.getDate(4).toLocalDate();
            String diaChi = rs.getString(5);
            String email = rs.getString(6);
            String sdt = rs.getString(7);
            LocalDate ngayVaoLam = rs.getDate(8).toLocalDate();
            String tinhTrang = rs.getString(9);
            String gioiTinh = rs.getString(10);
            String cccd = rs.getString(11);
            boolean isRemove = rs.getBoolean(12);
            String tenChucVu = rs.getString(13);

            ChucVu cv = new ChucVu(maChucVu, tenChucVu);

            // Gọi constructor mới có đủ tham số
            NhanVien nv = new NhanVien(maNhanVien, hoTen, cv, ngaySinh, diaChi, email, sdt,ngayVaoLam, tinhTrang, gioiTinh, cccd, isRemove);
            return nv;
        }

        return null;
    }
	
	public NhanVien getNhanVienByCCCD(String cccd) throws SQLException {
	    String q = "SELECT * FROM NhanVien WHERE cccd = '" + cccd + "'";
	    ResultSet rs = st.executeQuery(q);

	    while (rs.next()) {
	        String maNhanVien = rs.getString(1);
	        String hoTen = rs.getString(2);
	        ChucVu cv = new ChucVu(rs.getString(3));
	        LocalDate ngaySinh = rs.getDate(4).toLocalDate();
	        String diaChi = rs.getString(5);
	        String email = rs.getString(6);
	        String sdt = rs.getString(7);
	        LocalDate ngayVaoLam = rs.getDate(8).toLocalDate();
	        String tinhTrang = rs.getString(9);
	        String gioiTinh = rs.getString(10);
	        String cccdNhanVien = rs.getString(11);

	        NhanVien nv = new NhanVien(
	            maNhanVien,
	            hoTen,
	            cv,
	            ngaySinh,
	            diaChi,
	            email,
	            sdt,
	            ngayVaoLam,
	            tinhTrang,
	            gioiTinh,
	            cccdNhanVien
	        );
	        return nv;
	    }

	    return null;
	}
	
	public boolean themNhanVien(NhanVien nv) throws SQLException {
	    String q = "INSERT INTO NhanVien (maNhanVien, hoTen, maChucVu, ngaySinh, diaChi, email, sdt, ngayVaoLam, tinhTrangLamViec, gioiTinh, cccd, isRemove) VALUES (" +
	            "'" + nv.getMaNhanVien() + "', " +
	            "N'" + nv.getHoTen() + "', " +
	            "'" + nv.getChucVu().getMaChucVu() + "', " +
	            "'" + nv.getNgaySinh().toString() + "', " +
	            "N'" + nv.getDiaChi() + "', " +
	            "'" + nv.getEmail() + "', " +
	            "'" + nv.getSdt() + "', " +
	            "'" + nv.getNgayVaoLam().toString() + "', " +
	            "N'" + nv.getTinhTrangLamViec() + "', " +
	            "N'" + nv.getGioiTinh() + "', " +
	            "'" + nv.getCccd() + "', " +
	            "0" + 
	            ")";

	    int rows = st.executeUpdate(q);
	    return rows > 0;
	}


	
	public boolean suaNhanVien(NhanVien nv) throws SQLException {
	    String q = "UPDATE NhanVien SET " +
	            "hoTen = N'" + nv.getHoTen() + "', " +
	            "maChucVu = '" + nv.getChucVu().getMaChucVu() + "', " +
	            "ngaySinh = " + (nv.getNgaySinh() == null ? "NULL" : "'" + nv.getNgaySinh() + "'") + ", " +
	            "diaChi = " + (nv.getDiaChi() == null ? "NULL" : "N'" + nv.getDiaChi() + "'") + ", " +
	            "email = " + (nv.getEmail() == null ? "NULL" : "'" + nv.getEmail() + "'") + ", " +
	            "sdt = " + (nv.getSdt() == null ? "NULL" : "'" + nv.getSdt() + "'") + ", " +
	            "ngayVaoLam = " + (nv.getNgayVaoLam() == null ? "NULL" : "'" + nv.getNgayVaoLam() + "'") + ", " +
	            "tinhTrangLamViec = N'" + nv.getTinhTrangLamViec() + "', " +
	            "gioiTinh = N'" + nv.getGioiTinh() + "', " +
	            "cccd = " + (nv.getCccd() == null ? "NULL" : "'" + nv.getCccd() + "'") +
	            " WHERE maNhanVien = '" + nv.getMaNhanVien() + "'";

	    int rows = st.executeUpdate(q);
	    return rows > 0;
	}

	public ArrayList<String> getListChucVu() throws SQLException
	{
		
		String q ="select cv.tenChucVu\r\n"
				+ "from ChucVu cv";
		ResultSet rs = st.executeQuery(q);
		ArrayList<String> listcv = new ArrayList<String>();
		while(rs.next())
		{
			String a = rs.getString(1);
			listcv.add(a);
		}
		return listcv;
	}
	public boolean capNhatTrangThaiNghiLam(String maNhanVien) throws SQLException {
	    
	    String q = "UPDATE NhanVien " +
	               "SET tinhTrangLamViec = N'đã nghỉ' " +
	               "WHERE maNhanVien = '" + maNhanVien + "'";
	    
	    int rows = st.executeUpdate(q);
	    return rows > 0;
	}
	
	public boolean capNhatTrangThaidiLam(String maNhanVien) throws SQLException {
	    
	    String q = "UPDATE NhanVien " +
	               "SET tinhTrangLamViec = N'còn làm' " +
	               "WHERE maNhanVien = '" + maNhanVien + "'";
	    
	    int rows = st.executeUpdate(q);
	    return rows > 0;
	}
	
	public boolean checkCCCD(String cccd) throws SQLException
	{
		String  q = "select * \r\n"
				+ "from NhanVien nv\r\n"
				+ "where nv.cccd = '"+cccd+"'";
		ResultSet rs = st.executeQuery(q);
		return rs.next();
	}
	
	
}
