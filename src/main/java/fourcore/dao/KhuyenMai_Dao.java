package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;

public class KhuyenMai_Dao {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<KhuyenMai> list = getList();
    Statement myStmt = databaseConnector.connect();

    public KhuyenMai_Dao() throws SQLException {
        getList();
	}


	public ArrayList<KhuyenMai> getList() throws SQLException {
		String query = "select * from KhuyenMai";
		ResultSet rs = myStmt.executeQuery(query);
		ArrayList<KhuyenMai> list1 = new ArrayList<>();
		while (rs.next()) {
			String ma = rs.getString("maKhuyenMai");
			String ten = rs.getString("tenChuongTrinh");
			String trangThai = rs.getString("trangThaiKhuyenMai");
			String dieuKien = rs.getString("dieuKienApDungKhuyenMai");
			double phanTram = rs.getDouble("giaTriPhanTramKhuyenMai");
			LocalDateTime ngayBD = rs.getTimestamp("ngayBatDau").toLocalDateTime();
			LocalDateTime ngayKT = rs.getTimestamp("ngayKetThuc").toLocalDateTime();

			list1.add(new KhuyenMai(ma, ten, trangThai, dieuKien, phanTram, ngayBD, ngayKT));
//			System.out.println("Lấy dữ liệu thành công");
		}
		return list1;
	}

	public KhuyenMai getKhuyenMaiBangMa(String maKhuyenMai) throws SQLException {
		for (KhuyenMai khuyenMai : list) {
            if (khuyenMai.getMaKhuyenMai().equals(maKhuyenMai)) {
                return khuyenMai;
            }
        }
		return null;
	}
	
	public void capNhatTrangThaiTatCa() throws SQLException {
	    String sql = "SELECT maKhuyenMai, ngayBatDau, ngayKetThuc FROM KhuyenMai";
	    ResultSet rs = myStmt.executeQuery(sql);

	    LocalDateTime now = LocalDateTime.now();

	    while (rs.next()) {
	        String ma = rs.getString("maKhuyenMai");
	        LocalDateTime ngayBD = rs.getTimestamp("ngayBatDau").toLocalDateTime();
	        LocalDateTime ngayKT = rs.getTimestamp("ngayKetThuc").toLocalDateTime();

	        String trangThai;

	        if (now.isBefore(ngayBD)) {
	            trangThai = "Chưa kích hoạt";
	        } else if (now.isAfter(ngayKT)) {
	            trangThai = "Kết thúc";
	        } else {
	            trangThai = "Kích hoạt";
	        }

	        String update = "UPDATE KhuyenMai SET trangThaiKhuyenMai = N'" + trangThai + 
	                        "' WHERE maKhuyenMai = '" + ma + "'";
	        myStmt.executeUpdate(update);
	    }

	}
}
