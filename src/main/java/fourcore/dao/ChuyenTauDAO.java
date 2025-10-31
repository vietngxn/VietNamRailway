package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.Tau;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class ChuyenTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public ArrayList<ChuyenTau> listChuyenTau =  new ArrayList<>();
    Tau_DAO tau_DAO = new Tau_DAO();
    ArrayList<Tau> listTau;
    HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
    ArrayList<HanhTrinh> listHanhTrinh;
    Statement myStmt = databaseConnector.connect();
    public void goiDAO(){
        System.out.println("chuyen tau dao");
    }
    public ChuyenTauDAO() throws SQLException {
        goiDAO();
        getListChuyenTau();
    }

    public ArrayList<ChuyenTau> getListChuyenTau() throws SQLException {
        String query = "select * from ChuyenTau";
        ResultSet rs = myStmt.executeQuery(query);
        listHanhTrinh = hanhTrinh_DAO.getList();
        while (rs.next()) {
            String maChuyenTau = rs.getString(1);
            String maTau = rs.getString(2);
            String maHanhTrinh = rs.getString(3);
            LocalDateTime ngayGioDi = rs.getTimestamp(4).toLocalDateTime();
            LocalDateTime ngayGioDen =  rs.getTimestamp(5).toLocalDateTime();
            double giaCuocTrenChuyen = rs.getDouble(6);
            Tau tau = tau_DAO.getTauByMaTau(maTau);
            HanhTrinh hanhTrinh = hanhTrinh_DAO.getById(maHanhTrinh);
            ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau,tau,hanhTrinh,ngayGioDi,ngayGioDen,giaCuocTrenChuyen);
            listChuyenTau.add(chuyenTau);
        }
        return listChuyenTau;
    }
    public String getMaChuyenTauCuoiCung() {
        String maChuyenTau = null;
        try {
            String sql = "Select top 1 maChuyenTau From ChuyenTau Order By maChuyenTau DESC";
            ResultSet rs = myStmt.executeQuery(sql);
            if(rs.next()) {
                maChuyenTau = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maChuyenTau;
    }
    public ChuyenTau getChuyenTauBangMa(String maChuyenTauInput) throws SQLException {
        for (ChuyenTau c : listChuyenTau) {
            if (c.getMaChuyenTau().equals(maChuyenTauInput)) {
                return c;
            }
        }
        return null;
    }
    public List<String> getListMaHanhTrinhTheoNgay(LocalDate ngayKhoiHanh) {
        Date ngayGioDi = Date.valueOf(ngayKhoiHanh);

        List<String> danhSachMaHanhTrinh = new ArrayList<>();
        String sql = "SELECT maHanhTrinh FROM ChuyenTau WHERE CAST(ngayGioDi AS DATE) = ?";

        try {
            PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection().prepareStatement(sql);
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
    public boolean addChuyenTauVaoDB(ChuyenTau chuyenTau) {
        String sql = "Insert Into ChuyenTau Values(?, ?, ?, ?, ?, ?,?)";
        int n = 0;
        try {
            PreparedStatement ps = (PreparedStatement) databaseConnector.connect().getConnection().prepareStatement(sql);
            ps.setString(1, chuyenTau.getMaChuyenTau());
            ps.setString(2, chuyenTau.getTau().getMaTau());
            ps.setString(3, chuyenTau.getHanhTrinh().getMaHanhTrinh());
            ps.setTimestamp(4, Timestamp.valueOf(chuyenTau.getNgayGioDi()));
            ps.setTimestamp(5, Timestamp.valueOf(chuyenTau.getNgayGioDen()));
            ps.setDouble(6, chuyenTau.getGiaCuocTrenChuyenTau());
            ps.setBoolean(7,false);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
