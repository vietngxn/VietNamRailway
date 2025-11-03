package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GheNgoiDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    ArrayList<GheNgoi> listGheNgoi =  new ArrayList<>();
    ArrayList<GheNgoi> listGheNgoiTrenChuyen =   new ArrayList<>();
    ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau = new ArrayList<>();
    GheNgoi gn;
    TangDAO tangDAO = new TangDAO();
    LoaiGheDAO loaiGheDAO = new LoaiGheDAO();
    KhoangTauDAO khoangTauDAO = new KhoangTauDAO();
    ToaTauDAO toaTauDAO = new ToaTauDAO();
    ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
    Statement myStmt;
    public GheNgoiDAO() throws SQLException {
        goiDAO();
        myStmt = databaseConnector.connect();
        getListGheNgoi();
        getListTrenChuyenTau();
    }
    public void goiDAO(){
        System.out.println("Ghe dao");
    }
    public ArrayList<GheNgoi> getListGheNgoi() throws SQLException {
        String query = "select * from GheNgoi";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maGheNgoi = rs.getString(1);
            String maTang = rs.getString(2);
            String maLoaiGhe =  rs.getString(3);
            String maKhoang =  rs.getString(4);
            String maToa =   rs.getString(5);
            int soGhe =   rs.getInt(6);
            double giaTriTangThem =  rs.getDouble(7);
            int luuDongInt =  rs.getInt(8);
            boolean luuDong = false;
            if(luuDongInt == 1){
                luuDong = true;
            }

            Tang t = tangDAO.getTang(maTang);
            LoaiGhe loaiGhe = loaiGheDAO.getLoaiGhe(maLoaiGhe);
            KhoangTau kt = khoangTauDAO.getKhoang(maKhoang);
            ToaTau tt = toaTauDAO.getToaTauByMa(maToa);

            GheNgoi gheNgoi = new GheNgoi(maGheNgoi,loaiGhe, t,kt,tt, soGhe,giaTriTangThem,luuDong);
            listGheNgoi.add(gheNgoi);
        }
        return listGheNgoi;
    }

    public ArrayList<GheTrenChuyenTau> getListGheTrenChuyenTau() {
        return listGheTrenChuyenTau;
    }
    public int tongSoGheTrenChuyen(String maChuyenTau) throws SQLException {
        getListTrenChuyenTau();
        int count=0;
        for (int i = 0; i < listGheTrenChuyenTau.size(); i++) {
            if(listGheTrenChuyenTau.get(i).getChuyenTau().getMaChuyenTau().equals(maChuyenTau)){
                count++;
            }
        }
        return count;
    }
    public int soGheTrongTrenChuyen(String maChuyenTau) throws SQLException {
        int tongSoGhe = tongSoGheTrenChuyen(maChuyenTau);
        int count=0;
        for (int i = 0; i < listGheTrenChuyenTau.size(); i++) {
            if(listGheTrenChuyenTau.get(i).getChuyenTau().getMaChuyenTau().equals(maChuyenTau)){
                if(listGheTrenChuyenTau.get(i).getTrangThaiGhe().equals("Đã bán")){
                    count++;
                }
            }
        }
        return tongSoGhe-count;
    }

    public ArrayList<GheTrenChuyenTau> getListTrenChuyenTau() throws SQLException {
        listGheTrenChuyenTau.clear();
        String query = "select * from GheTrenChuyenTau";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maGheTrenChuyenTau = rs.getString(1);
            String maChuyenTau = rs.getString(2);
            String maGheNgoi = rs.getString(3);
            double giaTienGhe = rs.getDouble(4);
            String trangThaiGhe = rs.getString(5);

            ChuyenTau ct = chuyenTauDAO.getChuyenTauBangMa(maChuyenTau);
            GheNgoi gn = getGheBangMaGhe(maGheNgoi);

            GheTrenChuyenTau gheTrenChuyenTau = new GheTrenChuyenTau(maGheTrenChuyenTau, trangThaiGhe, giaTienGhe, ct, gn);
            listGheTrenChuyenTau.add(gheTrenChuyenTau);
        }
        return listGheTrenChuyenTau;
    }


    public GheNgoi getGheBangMaGhe(String maGheInput) throws SQLException {
        for (GheNgoi g : listGheNgoi) {
            if (g.getMaGheNgoi().equals(maGheInput)) {
                return g;
            }
        }
        return null;
    }

    public ArrayList<GheNgoi> getListGheTrenChuyenByMaChuyen(String maChuyenTauInput) throws SQLException {
        String query = "select * from GheTrenChuyenTau where maChuyenTau ='" +maChuyenTauInput+"'";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maGheTrenChuyenTau = rs.getString(1);
            String maChuyenTau = rs.getString(2);
            String maGheNgoi = rs.getString(3);
            double giaTienGhe = rs.getDouble(4);
            String trangThaiGhe = rs.getString(5);

        }
        return  listGheNgoiTrenChuyen;
    }
    public GheNgoi getGheBangMaToaVaSoGhe(String maToaInput, int soGheInput) throws SQLException {
        String query = "  select * from GheNgoi where maToaTau = '" +maToaInput+"' and soGhe = "+soGheInput+ ";";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            String maGheNgoi = rs.getString(1);
            String maTang = rs.getString(2);
            String maLoaiGhe =  rs.getString(3);
            String maKhoang =  rs.getString(4);
            String maToa =   rs.getString(5);
            int soGhe =   rs.getInt(6);
            double giaTriTangThem =  rs.getDouble(7);
            int luuDongInt =  rs.getInt(8);
            boolean luuDong = false;
            if(luuDongInt == 1){
                luuDong = true;
            }
            TangDAO tangDAO = new TangDAO();
            Tang t = tangDAO.getTang(maTang);
            LoaiGheDAO loaiGheDAO = new LoaiGheDAO();
            LoaiGhe loaiGhe = loaiGheDAO.getLoaiGhe(maLoaiGhe);
            KhoangTauDAO khoangTauDAO = new KhoangTauDAO();
            KhoangTau kt = khoangTauDAO.getKhoang(maKhoang);
            ToaTauDAO toaTauDAO = new ToaTauDAO();
            ToaTau tt = toaTauDAO.getToaTauByMa(maToa);

            GheNgoi gheNgoi = new GheNgoi(maGheNgoi,loaiGhe, t,kt,tt, soGhe,giaTriTangThem,luuDong);
            return gheNgoi;
        }
        return null;
    }

    public GheTrenChuyenTau getGheTrenChuyenTau(int soGheInput,String maToaInput, String maChuyenTauInput) throws SQLException {
        for (GheTrenChuyenTau gtc : listGheTrenChuyenTau){
            if(gtc.getGheNgoi().getSoGhe()==soGheInput){
                if(gtc.getGheNgoi().getToaTau().getMaToaTau().equals(maToaInput)){
                    if (gtc.getChuyenTau().getMaChuyenTau().equals(maChuyenTauInput)){
                        return gtc;
                    }
                }
            }
        }
        return null;
    }
    //-------------------------------------------------------tien
    public Map<String, ArrayList<GheNgoi>> getMapGheTheoToa(String maToaTauSQl) throws SQLException {
        Map<String, ArrayList<GheNgoi>> mapGheTheoToa = new HashMap<>();
        String query = "Select * from GheNgoi where maToaTau in (" + maToaTauSQl + ")";
        ResultSet rs = myStmt.executeQuery(query);
        while(rs.next()) {

            String maGheNgoi = rs.getString(1);

            String maTang = rs.getString(2);
            Tang tang = tangDAO.getTang(maTang);

            String maLoaiGhe = rs.getString(3);
            LoaiGhe loaiGhe = loaiGheDAO.getLoaiGhe(maLoaiGhe);

            String maKhoangTau = rs.getString(4);
            KhoangTau khoangTau = khoangTauDAO.getKhoang(maKhoangTau);

            String maToaTau = rs.getString(5);
            ToaTau toaTau = toaTauDAO.getToaTauTheoMa(maToaTau);

            int soGhe = rs.getInt(6);
            double giaTriTangThem = rs.getDouble(7);
            boolean luuDong = rs.getBoolean(8);
            GheNgoi gheNgoi = new GheNgoi(maGheNgoi, loaiGhe, tang, khoangTau, toaTau, soGhe, giaTriTangThem, luuDong);
            mapGheTheoToa.computeIfAbsent(maToaTau, ma -> new ArrayList<>()).add(gheNgoi);
        }
        return mapGheTheoToa;
    }
    public ArrayList<GheNgoi> getAllGheCuaToa(String maToaTau) throws SQLException {

        ArrayList<GheNgoi> danhSachGhe = getListGheNgoi();
        ArrayList<GheNgoi> danhSachGheCuaToa = new ArrayList<GheNgoi>();
        for( GheNgoi gn : danhSachGhe) {
            if(gn.getToaTau().getMaToaTau().equalsIgnoreCase(maToaTau)) danhSachGheCuaToa.add(gn);
        }
        return danhSachGheCuaToa;

    }
    public boolean capNhatGiaTriTangThem(String maGhe, double giaTriTangThem) {
        int n = 0;
        String sql = "Update GheNgoi set giaTriTangThem = ? where maGheNgoi = ?";
        try {
            PreparedStatement ps = (PreparedStatement) myStmt.getConnection().prepareStatement(sql);
            ps.setDouble(1, giaTriTangThem);
            ps.setString(2, maGhe);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;

    }
    public boolean capNhatGiaTriTangThemChoMap(Map<String, Double> mapUpdateGhe) {
        int n = 0;
        String sql = "Update GheNgoi set giaTriTangThem = ? where maGheNgoi = ?";
        try {
            PreparedStatement ps = (PreparedStatement)myStmt.getConnection().prepareStatement(sql);
            for (Map.Entry<String, Double> entry : mapUpdateGhe.entrySet()) {
                ps.setDouble(1, entry.getValue());
                ps.setString(2, entry.getKey());
                ps.addBatch();
            }
            int[] result = ps.executeBatch();
            for(int num : result) {
                n += num;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}


