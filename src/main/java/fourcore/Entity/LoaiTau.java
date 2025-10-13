package fourcore.Entity;

import java.time.LocalDateTime;

public class LoaiTau {
    private String maLoaiTau;
    private String tenLoaiTau;
    private double giaCuoc;
    private LocalDateTime ngaySuaDoi;

    public LoaiTau() {
    }

    public LoaiTau(String maLoaiTau, String tenLoaiTau, double giaCuoc, LocalDateTime ngaySuaDoi) {
        setMaLoaiTau(maLoaiTau);
        setTenLoaiTau(tenLoaiTau);
        setGiaCuoc(giaCuoc);
        setNgaySuaDoi(ngaySuaDoi);
    }

    public String getMaLoaiTau() {
        return maLoaiTau;
    }

    public void setMaLoaiTau(String maLoaiTau) {
        this.maLoaiTau = maLoaiTau;
    }

    public String getTenLoaiTau() {
        return tenLoaiTau;
    }

    public void setTenLoaiTau(String tenLoaiTau) {
        this.tenLoaiTau = tenLoaiTau;
    }

    public double getGiaCuoc() {
        return giaCuoc;
    }

    public void setGiaCuoc(double giaCuoc) {
        this.giaCuoc = giaCuoc;
    }

    public LocalDateTime getNgaySuaDoi() {
        return ngaySuaDoi;
    }

    public void setNgaySuaDoi(LocalDateTime ngaySuaDoi) {
        this.ngaySuaDoi = ngaySuaDoi;
    }
}
