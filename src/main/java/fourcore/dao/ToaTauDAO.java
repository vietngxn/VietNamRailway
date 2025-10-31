package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.ToaTau;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToaTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<ToaTau> listToaTau =  new ArrayList<>();
    LoaiToaTauDAO loaiToaTauDAO = new LoaiToaTauDAO();

    Statement myStmt;
    public ToaTauDAO() throws SQLException {
        myStmt = databaseConnector.connect();
        goiDAO();

    }
    public void goiDAO(){
        System.out.println("toa tau dao");
    }
    public ArrayList<ToaTau> getListToaTau() throws SQLException {

        String query = "select * from ToaTau";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maToaTau = rs.getString(1);
            String maLoaiToa = rs.getString(2);
            String tenToaTau = rs.getString(3);
            int soToaTau = Integer.parseInt(rs.getString(4));
            LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
            ToaTau toaTau = new ToaTau(maToaTau, tenToaTau,soToaTau, loaiToaTau);
            listToaTau.add(toaTau);
//            	maKhuyenMai,tenChuongTrinh,trangThaiKhuyenMai,dieuKienApDung,iaTriPhanTramKhuyenMai,ngayBatDau,ngayKetThuc)
        }

        return listToaTau;
    }
    public ArrayList<ToaTau> getListToaTauByMaCT(String maCT) throws SQLException {
    ArrayList<ToaTau> listToaTheoChuyen =  new ArrayList<>();
    String query = "SELECT DISTINCT tt.maToaTau,tt.tenToaTau,tt.soToa,ltt.maLoaiToaTau\n" +
            "FROM GheTrenChuyenTau gtc\n" +
            "JOIN GheNgoi g ON gtc.maGheNgoi = g.maGheNgoi\n" +
            "JOIN ToaTau tt ON g.maToaTau = tt.maToaTau\n" +
            "JOIN LoaiToaTau ltt ON tt.maLoaiToaTau = ltt.maLoaiToaTau\n" +
            "WHERE gtc.maChuyenTau ='" + maCT +"';";
    ResultSet rs = myStmt.executeQuery(query);
    while (rs.next()) {
        String maToaTau = rs.getString(1);
        String tenToaTau = rs.getString(2);
        int soToaTau = Integer.parseInt(rs.getString(3));
        String maLoaiToa = rs.getString(4);
        LoaiToaTau ltt = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
        System.out.println("Loại tàu import: " + ltt.getTenLoaiToaTau());
        ToaTau tt = new ToaTau(maToaTau, tenToaTau,soToaTau, ltt);
        listToaTheoChuyen.add(tt);
    }
    return listToaTheoChuyen;
    }

    public ToaTau getToaTauByMa(String maToaTau) throws SQLException {
        getListToaTau();
        for (ToaTau toaTau : listToaTau) {
            if (toaTau.getMaToaTau().equals(maToaTau)) {
                return toaTau;
            }
        }
        return null;
    }
    public ToaTau getToaTauTheoMa(String maToaTau) throws SQLException {
        listToaTau = getListToaTau();
        for(ToaTau tt : listToaTau) {
            if(tt.getMaToaTau().equalsIgnoreCase(maToaTau)) return tt;
        }
        return null;
    }

    public ArrayList<ToaTau> getListToaTauTheoLoaiTau(String tenLoaiTau) throws SQLException {
        String maLoaiToa = null;
        if(tenLoaiTau.equalsIgnoreCase("SE1") || tenLoaiTau.equalsIgnoreCase("SE9")) maLoaiToa = "LTToa01";
        LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
        ArrayList<ToaTau> listToaTheoLoaiTau = new ArrayList<>();
        String sql = "Select * From ToaTau where maLoaiToaTau = ?";
        try {
            PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
            ps.setString(1, maLoaiToa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maToa = rs.getString(1);
                String tenToa = rs.getString(3);
                int soGhe = rs.getInt(4);
                ToaTau toaTau = new ToaTau(maToa, tenToa, soGhe, loaiToaTau);
                listToaTheoLoaiTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listToaTheoLoaiTau;

    }

    public ArrayList<ToaTau> getListToaTauTenToaTau(String tenLoaiTau) throws SQLException {
	
		String tenLoaiToa = null;
		if(tenLoaiTau.equalsIgnoreCase("SE1") || tenLoaiTau.equalsIgnoreCase("SE9")) tenLoaiToa = "Toa thường";
		ArrayList<ToaTau> listToaTheoLoaiTau = new ArrayList<>();
		String sql = "Select * From ToaTau where tenToaTau = ?";
		try {
			PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
			ps.setString(1, tenLoaiToa);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maToa = rs.getString(1);
				String maLoaiToaTau = rs.getString(2);
				LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToaTau);
				String tenToa = rs.getString(3);
				int soGhe = rs.getInt(4);
				ToaTau toaTau = new ToaTau(maToa, tenToa, soGhe, loaiToaTau);
				listToaTheoLoaiTau.add(toaTau);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listToaTheoLoaiTau;
		
	}
	public ToaTau getToaTauTheoMaAndList(String maToaTau, ArrayList<ToaTau> listToaTau) {
		for(ToaTau tt: listToaTau) {
			if(tt.getMaToaTau().equalsIgnoreCase(maToaTau)) return tt;
		}
		return null;
		
	}

}
