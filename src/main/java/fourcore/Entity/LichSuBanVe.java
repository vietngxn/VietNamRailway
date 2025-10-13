package fourcore.Entity;

import java.time.LocalDate;

public class LichSuBanVe {
    private String maVe;
    private String ten;
    private LocalDate ngayMua;
    public LichSuBanVe(String maVe, String ten, LocalDate ngayMua) {
        this.maVe = maVe;
        this.ten = ten;
        this.ngayMua = ngayMua;
    }
    public String getMaVe() {
        return maVe;
    }
    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }
    public String getTen() {
        return ten;
    }
    public LocalDate getNgayMua() {
        return ngayMua;
    }
}
