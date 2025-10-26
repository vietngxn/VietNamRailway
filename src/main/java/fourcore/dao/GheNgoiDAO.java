package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    public GheNgoiDAO() throws SQLException {
        getListGheNgoi();
        getListTrenChuyenTau();
    }

    public ArrayList<GheNgoi> getListGheNgoi() throws SQLException {
        Statement myStmt = databaseConnector.connect();
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
    public ArrayList<GheTrenChuyenTau> getListTrenChuyenTau() throws SQLException {
        Statement myStmt = databaseConnector.connect();
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
        Statement myStmt = databaseConnector.connect();
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
        Statement myStmt = databaseConnector.connect();
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
}

//
//
//String query =
//        "  DECLARE \n" +
//                "    @soGhe INT =" + soGheInput + ","+
//                "    @maChuyenTau NVARCHAR(20) = N'"+maChuyenTauInput+"'," +
//                "    @maToaTau NVARCHAR(20) = N'"+maToaInput+"';" +
//                "SELECT \n" +
//                "    gtc.maGheTrenChuyenTau,\n" +
//                "    gtc.maChuyenTau,\n" +
//                "    gn.maGheNgoi,\n" +
//                "    gtc.trangThaiGhe,\n" +
//                "    gtc.giaTienGhe\n" +
//                "FROM GheTrenChuyenTau AS gtc\n" +
//                "INNER JOIN GheNgoi AS gn ON gtc.maGheNgoi = gn.maGheNgoi\n" +
//                "WHERE \n" +
//                "    gtc.maChuyenTau = @maChuyenTau\n" +
//                "    AND gn.maToaTau = @maToaTau\n" +
//                "    AND gn.soGhe = @soGhe;\n" +
//                "\n" +
//                "\n";
//ResultSet rs = myStmt.executeQuery(query);
//        while (rs.next()) {
//String maGheTrenChuyenTau = rs.getString(1);
//String maChuyenTau = rs.getString(2);
//String maGheNgoi = rs.getString(3);
//String trangThaiGhe = rs.getString(4);
//double giaTienGhe = rs.getDouble(5);
//ChuyenTau ct = chuyenTauDAO.getChuyenTauBangMa(maChuyenTau);
//GheNgoi gn = getGheBangMaGhe(maGheNgoi);
//
//GheTrenChuyenTau gheTrenChuyenTau = new GheTrenChuyenTau(maGheTrenChuyenTau,trangThaiGhe, giaTienGhe,ct,gn);
//            return gheTrenChuyenTau;
//        }
