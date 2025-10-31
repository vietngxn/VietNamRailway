package fourcore.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.Ga;
import fourcore.Entity.HanhTrinhGa;
import fourcore.Entity.ToaTau;

public class HanhTrinhGa_dao {
	
	DatabaseConnector databaseConnector = new DatabaseConnector();
	private ArrayList<HanhTrinhGa> listHanhTrinhGa = new ArrayList<HanhTrinhGa>();
    Statement myStmt;
    public void goiDAO(){
        System.out.println("hanh trinh ga dao");
    }
	public HanhTrinhGa_dao() throws SQLException {
        goiDAO();
        myStmt = databaseConnector.connect();
		listHanhTrinhGa = new ArrayList<>();
	}
	
	public ArrayList<HanhTrinhGa> getListHanhTrinhGaTheoMaHanhTrinh(String maHanhTrinh) {
		ArrayList<HanhTrinhGa> listHanhTrinhGa = new ArrayList<>();
		String sql = "Select * From HanhTrinhGa where maHanhTrinh = ?";
		try {
			PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
			ps.setString(1, maHanhTrinh);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maHanhTrinhGa = rs.getString(1);
				int thuTuDung = rs.getInt(4);
				LocalTime gioDiKeHoach = rs.getTime(5).toLocalTime();
				int soNgayDiQua = rs.getInt(6);
				HanhTrinhGa hanhTrinhGa = new HanhTrinhGa(maHanhTrinhGa, thuTuDung, gioDiKeHoach, soNgayDiQua);
				listHanhTrinhGa.add(hanhTrinhGa);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return listHanhTrinhGa;
		
	}
	public List<String> getListMaHanhTrinhTheoNgay(LocalDate ngayKhoiHanh) {
		Date ngayGioDi = Date.valueOf(ngayKhoiHanh);
		
		List<String> danhSachMaHanhTrinh = new ArrayList<>();
		String sql = "SELECT maHanhTrinh FROM ChuyenTau WHERE CAST(ngayGioDi AS DATE) = ?";

		try {
			PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
			ps.setDate(1, ngayGioDi);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				danhSachMaHanhTrinh.add(rs.getString("maHanhTrinh"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachMaHanhTrinh;
	}
}
