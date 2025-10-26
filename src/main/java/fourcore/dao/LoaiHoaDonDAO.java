package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.LoaiHoaDon;
import fourcore.Entity.NhanVien;

public class LoaiHoaDonDAO {
	DatabaseConnector databaseConnector = new DatabaseConnector();
	ArrayList<LoaiHoaDon> list = new ArrayList<>();

	public ArrayList<LoaiHoaDon> getList() throws SQLException {

		String sql = "SELECT * FROM LoaiHoaDon";
		Statement st = databaseConnector.connect();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String maLoai = rs.getString("maLoaiHoaDon");
			String tenLoai = rs.getString("tenLoaiHoaDon");
			String ghiChu = rs.getString("ghiChu");

			LoaiHoaDon loai = new LoaiHoaDon();
			loai = new LoaiHoaDon(maLoai, tenLoai, ghiChu);
			list.add(loai);
		}

		rs.close();
		st.close();
		return list;
	}

	public LoaiHoaDon getLoaiHoaDonTheoMa(String maLoaiHoaDon) throws SQLException {
		LoaiHoaDon loai = null;
		String sql = "SELECT * FROM LoaiHoaDon WHERE maLoaiHoaDon = '" + maLoaiHoaDon + "'";

		Statement st = databaseConnector.connect();
		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			String maLoai = rs.getString("maLoaiHoaDon");
			String tenLoai = rs.getString("tenLoaiHoaDon");
			String ghiChu = rs.getString("ghiChu");

			loai = new LoaiHoaDon(maLoai, tenLoai, ghiChu);
		}

		rs.close();
		st.close();
		return loai;
	}
	
	public String getLoaiHoaDonTheoMaVe(String maVe) throws SQLException {
	    String loai = null;

	    String sql = "SELECT lhd.tenLoaiHoaDon "
	               + "FROM Ve AS v "
	               + "JOIN ChiTietHoaDon AS ct ON v.maVeTau = ct.maVeTau "
	               + "JOIN HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
	               + "JOIN LoaiHoaDon AS lhd ON hd.maLoaiHoaDon = lhd.maLoaiHoaDon "
	               + "WHERE v.trangThaiVe = N'hoạt động' "
	               + "AND v.ngayGioDi > GETDATE() "
	               + "AND v.maVeTau = '" + maVe + "'";

	    Statement st = databaseConnector.connect();
	    ResultSet rs = st.executeQuery(sql);
	    if (rs.next()) {
	        loai = rs.getString("tenLoaiHoaDon");
	    }
	    rs.close();
	    st.close();
	    return loai;
	}

	

}
