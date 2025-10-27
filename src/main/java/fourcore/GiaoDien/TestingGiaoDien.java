package fourcore.GiaoDien;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestingGiaoDien {
    public static void main(String[] args) throws SQLException {
        LoaiTau loaiTau1 = new LoaiTau("SE1", "TauHoaBacNam", 12000);
        Tau tau1 = new Tau("Hihi", "TauHi", loaiTau1);
        ChuyenTau chuyen1 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 14, 15, 30),LocalDateTime.of(2025, 10, 14, 15, 30),tau1, "TauHihi");
        ArrayList<ChuyenTau> chuyenTauArrayList = new ArrayList<>();
        chuyenTauArrayList.add(chuyen1);
        ChonVe chonVeGiaoDien = new ChonVe();

    }
}
