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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToaTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<ToaTau> listToaTau =  new ArrayList<>();
    LoaiToaTauDAO loaiToaTauDAO = new LoaiToaTauDAO();
    Statement myStmt;
//    ArrayList<String> listToaSE = new ArrayList<>(Arrays.asList("Ngồi mềm chất lượng cao", "Giường nằm khoang 6 điều hòa"));
//    ArrayList<String> listToaSE2122 = new ArrayList<>(Arrays.asList("Ngồi mềm điều hòa ghế xoay", "Giường nằm khoang 6 điều hòa"));
//    ArrayList<String> listToaSE34 = new ArrayList<>(Arrays.asList("Ngồi mềm chất lượng cao", "Giường nằm khoang 6 điều hòa VIP"));
//    ArrayList<String> listToaSPT = new ArrayList<>(Arrays.asList("Ngồi mềm công nghệ Nhật Bản", "Giường nằm khoang 6 điều hòa"));
    Map<String, List<String>> mapLoaiToaTheoLoaiTau = new HashMap<>();

    


	
    public ToaTauDAO() throws SQLException {
        myStmt = databaseConnector.connect();
        goiDAO();
        List<String> dsSE = Arrays.asList(
        	     "SE1", "SE2", "SE5", "SE6", "SE7", "SE8",
        	     "SE9", "SE10", "SE19", "SE20",
        	     "HĐ1", "HĐ2", "HĐ3", "HĐ4",
        	     "SNT1", "SNT2"
        	    );
        	    List<String> toaSE = Arrays.asList("Ngồi mềm chất lượng cao", "Giường nằm khoang 6 điều hòa");
        	    for (String loaiTau : dsSE) {
        	     mapLoaiToaTheoLoaiTau.put(loaiTau, toaSE);
        	    };


        	    List<String> dsSE2122 = Arrays.asList("SE21", "SE22");
        		List<String> toaSE2122 = Arrays.asList("Ngồi mềm điều hòa ghế xoay", "Giường nằm khoang 6 điều hòa");
        		for (String loaiTauSE2122 : dsSE2122) {
        			mapLoaiToaTheoLoaiTau.put(loaiTauSE2122, toaSE2122);
        		};


        		List<String> dsSE34 = Arrays.asList("SE3", "SE4");
        		List<String> toaSE34 = Arrays.asList("Ngồi mềm chất lượng cao", "Giường nằm khoang 6 điều hòa VIP");
        		for (String loaiTauSE34 : dsSE34) {
        			mapLoaiToaTheoLoaiTau.put(loaiTauSE34, toaSE34);
        		};

        		List<String> dsSPT = Arrays.asList("SPT1", "SPT2");
        		List<String> toaSPT = Arrays.asList("Ngồi mềm công nghệ Nhật Bản", "Giường nằm khoang 6 điều hòa");
        		for (String loaiTauSPT : dsSPT) {
        			mapLoaiToaTheoLoaiTau.put(loaiTauSPT, toaSPT);
        		}

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
    String query = "SELECT DISTINCT tt.maToaTau,tt.tenToaTau,tt.soLuongGheTrongToa,ltt.maLoaiToaTau\n" +
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
    public ArrayList<ToaTau> getListToaTauByMaCT1(String maCT) throws SQLException {
        ArrayList<ToaTau> listToaTheoChuyen =  new ArrayList<>();
        String query = "SELECT DISTINCT tt.maToaTau,tt.tenToaTau,tt.soLuongGheTrongToa,ltt.maLoaiToaTau\n" +
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
            boolean isRemove = false;
            ToaTau tt = new ToaTau(maToaTau, tenToaTau,soToaTau, ltt, isRemove);
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

//    public ArrayList<ToaTau> getListToaTauTheoLoaiTau(String tenLoaiTau) throws SQLException {
//        String maLoaiToa = null;
//        if(tenLoaiTau.equalsIgnoreCase("SE1") || tenLoaiTau.equalsIgnoreCase("SE9")) maLoaiToa = "LTToa01";
//        LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
//        ArrayList<ToaTau> listToaTheoLoaiTau = new ArrayList<>();
//        String sql = "Select * From ToaTau where maLoaiToaTau = ?";
//        try {
//            PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
//            ps.setString(1, maLoaiToa);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String maToa = rs.getString(1);
//                String tenToa = rs.getString(3);
//                int soGhe = rs.getInt(4);
//                ToaTau toaTau = new ToaTau(maToa, tenToa, soGhe, loaiToaTau);
//                listToaTheoLoaiTau.add(toaTau);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listToaTheoLoaiTau;
//
//    }

    public ArrayList<ToaTau> getListToaTauTenToaTau(String tenLoaiTau) throws SQLException {
	
    	
    	List<String> listTenToa = mapLoaiToaTheoLoaiTau.get(tenLoaiTau);
		ArrayList<ToaTau> listToaTheoLoaiTau = new ArrayList<>();
		String sql = "Select * From ToaTau where tenToaTau = ?";
		try {
			for(String tt : listTenToa) {
				PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
				ps.setString(1, tt);
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
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listToaTheoLoaiTau;
		
	}
public ArrayList<ToaTau> getListToaTauTenToaTau1(String tenLoaiTau) throws SQLException {
	
    	
    	List<String> listTenToa = mapLoaiToaTheoLoaiTau.get(tenLoaiTau);
		ArrayList<ToaTau> listToaTheoLoaiTau = new ArrayList<>();
		String sql = "Select * From ToaTau where tenToaTau = ?";
		try {
			for(String tt : listTenToa) {
				PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
				ps.setString(1, tt);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String maToa = rs.getString(1);
					String maLoaiToaTau = rs.getString(2);
					LoaiToaTau loaiToaTau = loaiToaTauDAO.getLoaiToaTau(maLoaiToaTau);
					String tenToa = rs.getString(3);
					int soGhe = rs.getInt(4);
					boolean isRemove = false;
					ToaTau toaTau = new ToaTau(maToa, tenToa, soGhe, loaiToaTau, isRemove);
					listToaTheoLoaiTau.add(toaTau);
				}
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
	public ArrayList<ToaTau> getListToaTauTheoListMaChuyenTau(ArrayList<String> listMaChuyenTau) throws SQLException {
	    ArrayList<ToaTau> listToaTheoNgay =  new ArrayList<>();
	    if(listMaChuyenTau.size() == 0) return listToaTheoNgay;
	    String sql = "SELECT DISTINCT tt.maToaTau, tt.tenToaTau, tt.soLuongGheTrongToa, ltt.maLoaiToaTau " +
	    	    "FROM GheTrenChuyenTau gtc " +
	    	    "JOIN GheNgoi g ON gtc.maGheNgoi = g.maGheNgoi " +
	    	    "JOIN ToaTau tt ON g.maToaTau = tt.maToaTau " +
	    	    "JOIN LoaiToaTau ltt ON tt.maLoaiToaTau = ltt.maLoaiToaTau " +
	    	    "WHERE gtc.maChuyenTau IN (" + 
	    	        String.join(",", Collections.nCopies(listMaChuyenTau.size(), "?")) +
	    	    ")";
	    PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection().prepareStatement(sql);
	    for(int i = 0; i < listMaChuyenTau.size(); i++) {
	    	ps.setString(i+1, listMaChuyenTau.get(i));
	    }
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        String maToaTau = rs.getString(1);
	        String tenToaTau = rs.getString(2);
	        int soToaTau = Integer.parseInt(rs.getString(3));
	        String maLoaiToa = rs.getString(4);
	        LoaiToaTau ltt = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
	        System.out.println("Loại tàu import: " + ltt.getTenLoaiToaTau());
	        ToaTau tt = new ToaTau(maToaTau, tenToaTau,soToaTau, ltt);
	        listToaTheoNgay.add(tt);
	    }
	    return listToaTheoNgay;
	    }
	public ArrayList<ToaTau> getListToaTauTheoListMaChuyenTau1(ArrayList<String> listMaChuyenTau) throws SQLException {
	    ArrayList<ToaTau> listToaTheoNgay =  new ArrayList<>();
	    if(listMaChuyenTau.size() == 0) return listToaTheoNgay;
	    String sql = "SELECT DISTINCT tt.maToaTau, tt.tenToaTau, tt.soLuongGheTrongToa, ltt.maLoaiToaTau " +
	    	    "FROM GheTrenChuyenTau gtc " +
	    	    "JOIN GheNgoi g ON gtc.maGheNgoi = g.maGheNgoi " +
	    	    "JOIN ToaTau tt ON g.maToaTau = tt.maToaTau " +
	    	    "JOIN LoaiToaTau ltt ON tt.maLoaiToaTau = ltt.maLoaiToaTau " +
	    	    "WHERE gtc.maChuyenTau IN (" + 
	    	        String.join(",", Collections.nCopies(listMaChuyenTau.size(), "?")) +
	    	    ")";
	    PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection().prepareStatement(sql);
	    for(int i = 0; i < listMaChuyenTau.size(); i++) {
	    	ps.setString(i+1, listMaChuyenTau.get(i));
	    }
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        String maToaTau = rs.getString(1);
	        String tenToaTau = rs.getString(2);
	        int soToaTau = Integer.parseInt(rs.getString(3));
	        String maLoaiToa = rs.getString(4);
	        LoaiToaTau ltt = loaiToaTauDAO.getLoaiToaTau(maLoaiToa);
	        System.out.println("Loại tàu import: " + ltt.getTenLoaiToaTau());
	        boolean isRemove = false;
	        ToaTau tt = new ToaTau(maToaTau, tenToaTau,soToaTau, ltt, isRemove);
	        listToaTheoNgay.add(tt);
	    }
	    return listToaTheoNgay;
	    }
	
}
