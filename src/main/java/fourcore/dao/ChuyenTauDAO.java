package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.Tau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenTauDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public ArrayList<ChuyenTau> listChuyenTau =  new ArrayList<>();
    Tau_DAO tau_DAO = new Tau_DAO();
    ArrayList<Tau> listTau;
    HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
    ArrayList<HanhTrinh> listHanhTrinh;
    public ChuyenTauDAO() throws SQLException {
        getListChuyenTau();
    }

    public ArrayList<ChuyenTau> getListChuyenTau() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "select * from ChuyenTau";
        ResultSet rs = myStmt.executeQuery(query);
        listTau = tau_DAO.getList();
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
    public ChuyenTau getChuyenTauBangMa(String maChuyenTauInput) throws SQLException {
        for (ChuyenTau c : listChuyenTau) {
            if (c.getMaChuyenTau().equals(maChuyenTauInput)) {
                return c;
            }
        }
        return null;
    }
}
