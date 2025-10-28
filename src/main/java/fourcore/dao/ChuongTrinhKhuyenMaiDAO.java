package fourcore.dao;

import fourcore.DatabaseConnector.DBConfig;
import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuongTrinhKhuyenMaiDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();

    

	public ChuongTrinhKhuyenMaiDAO() throws SQLException {

	}

	public ArrayList<KhuyenMai> getListKhuyenMai() throws SQLException {
		Statement myStmt = databaseConnector.connect();
		String query = "select * from KhuyenMai";
		ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>();
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String maKhuyenMai = rs.getString(1);
			String tenChuongTrinh = rs.getString(2);
			double giaTriPhanTramKhuyenMai = rs.getDouble(3);
			LocalDateTime ngayBatDau = rs.getTimestamp(4).toLocalDateTime();
			LocalDateTime ngayKetThuc = rs.getTimestamp(5).toLocalDateTime();
			String trangThai = rs.getString(6);
			String dieuKienApDung = rs.getString(7);
			KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenChuongTrinh, trangThai, dieuKienApDung,
					giaTriPhanTramKhuyenMai, ngayBatDau, ngayKetThuc);
			listKhuyenMai.add(khuyenMai);
			// maKhuyenMai,tenChuongTrinh,trangThaiKhuyenMai,dieuKienApDung,iaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc)
		}

		return listKhuyenMai;
	}


    public KhuyenMai getKhuyenMaiBangMa(String maKhuyenMai) throws SQLException {
		Statement myStmt = databaseConnector.connect();
		KhuyenMai km = new KhuyenMai();
		String query = "select * from KhuyenMai WHERE maKhuyenMai = " + "'" + maKhuyenMai + "'";
		ResultSet rs = myStmt.executeQuery(query);
		while (rs.next()) {
			String ma = rs.getString("maKhuyenMai");
			String ten = rs.getString("tenChuongTrinh");
			String trangThai = rs.getString("trangThaiKhuyenMai");
			String dieuKien = rs.getString("dieuKienApDungKhuyenMai");
			double phanTram = rs.getDouble("giaTriPhanTramKhuyenMai");
			LocalDateTime ngayBD = rs.getTimestamp("ngayBatDau").toLocalDateTime();
			LocalDateTime ngayKT = rs.getTimestamp("ngayKetThuc").toLocalDateTime();

			km = new KhuyenMai(ma, ten, trangThai, dieuKien, phanTram, ngayBD, ngayKT);
			System.out.println("Lấy dữ liệu thành công");
		}
		return km;
	}
    
    public boolean themKhuyenMai(KhuyenMai km) throws SQLException
    {
    	Statement st = databaseConnector.connect();
    	String q = "INSERT INTO KhuyenMai VALUES ('" 
    		    + km.getMaKhuyenMai() + "', N'" 
    		    + km.getTenChuongTrinh() + "', " 
    		    + km.getGiaTriPhanTramKhuyenMai() + ", '" 
    		    + Timestamp.valueOf(km.getNgayBatDau()) + "', '" 
    		    + Timestamp.valueOf(km.getNgayKetThuc()) + "', N'" 
    		    + km.getTrangThaiKhuyenMai() + "', N'" 
    		    + km.getDieuKienApDung() + "')";
    	int kq = st.executeUpdate(q);
    	return kq > 0;
    }
    
    public boolean xoaKhuyenMai(String maKhuyenMai) throws SQLException {
        Statement st = databaseConnector.connect();

        String q = "DELETE FROM KhuyenMai WHERE maKhuyenMai = '" + maKhuyenMai + "'";

        int rows = st.executeUpdate(q);
        return rows > 0;
    }

    public boolean capNhatKhuyenMai(KhuyenMai km) throws SQLException {
        Statement st = databaseConnector.connect();

        String q = "UPDATE KhuyenMai SET "
            + "tenChuongTrinh = N'" + km.getTenChuongTrinh() + "', "
            + "giaTriPhanTramKhuyenMai = " + km.getGiaTriPhanTramKhuyenMai() + ", "
            + "ngayBatDau = '" + Timestamp.valueOf(km.getNgayBatDau()) + "', "
            + "ngayKetThuc = '" + Timestamp.valueOf(km.getNgayKetThuc()) + "', "
            + "trangThaiKhuyenMai = N'" + km.getTrangThaiKhuyenMai() + "', "
            + "dieuKienApDungKhuyenMai = N'" + km.getDieuKienApDung() + "' "
            + "WHERE maKhuyenMai = '" + km.getMaKhuyenMai() + "'";

        int rows = st.executeUpdate(q);
        return rows > 0;
    }
    
    public double getPhanTramKhuyenMai(String makm) throws SQLException
    {
    	double phanTram = 0;
    	Statement st = databaseConnector.connect();
    	String q = "SELECT km.giaTriPhanTramKhuyenMai " +
                "FROM KhuyenMai km " +
                "WHERE km.maKhuyenMai = '" + makm + "'";;
    	ResultSet rs = st.executeQuery(q);
    	while(rs.next())
    	{
    		phanTram = rs.getDouble(1);
    	}
    	return phanTram;
    	
    }

}
