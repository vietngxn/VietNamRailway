package fourcore.Entity;

import java.time.LocalDateTime;

public class ChuyenTau {
    private String maChuyenTau;
    private String maTau;
    private String maHanhTrinh;
    private LocalDateTime ngayGioDi;
    private LocalDateTime ngayGioDen;
    private double giaCuocTrenChuyenTau;
    private LocalDateTime ngaySuaDoi;

    public ChuyenTau() {
    }

    public ChuyenTau(String maChuyenTau, String maTau, String maHanhTrinh,
                     LocalDateTime ngayGioDi, LocalDateTime ngayGioDen,
                     double giaCuocTrenChuyenTau, LocalDateTime ngaySuaDoi) {
        setMaChuyenTau(maChuyenTau);
        setMaTau(maTau);
        setMaHanhTrinh(maHanhTrinh);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setGiaCuocTrenChuyenTau(giaCuocTrenChuyenTau);
        setNgaySuaDoi(ngaySuaDoi);
    }

    public String getMaChuyenTau() {
        return maChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.maChuyenTau = maChuyenTau;
    }

    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public String getMaHanhTrinh() {
        return maHanhTrinh;
    }

    public void setMaHanhTrinh(String maHanhTrinh) {
        this.maHanhTrinh = maHanhTrinh;
    }

    public LocalDateTime getNgayGioDi() {
        return ngayGioDi;
    }

    public void setNgayGioDi(LocalDateTime ngayGioDi) {
        this.ngayGioDi = ngayGioDi;
    }

    public LocalDateTime getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(LocalDateTime ngayGioDen) {
        this.ngayGioDen = ngayGioDen;
    }

    public double getGiaCuocTrenChuyenTau() {
        return giaCuocTrenChuyenTau;
    }

    public void setGiaCuocTrenChuyenTau(double giaCuocTrenChuyenTau) {
        this.giaCuocTrenChuyenTau = giaCuocTrenChuyenTau;
    }

    public LocalDateTime getNgaySuaDoi() {
        return ngaySuaDoi;
    }

    public void setNgaySuaDoi(LocalDateTime ngaySuaDoi) {
        this.ngaySuaDoi = ngaySuaDoi;
    }
}
