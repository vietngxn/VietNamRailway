package fourcore.Entity;

import java.time.LocalDateTime;

public class LoaiTuongTacVe {
    private String maLoaiTuongTac;
    private String tenLoaiTuongTac;
    private LocalDateTime ngaySuaDoi;

    // Constructor mặc định
    public LoaiTuongTacVe() {
    }

    // Constructor có tham số (không dùng this)
    public LoaiTuongTacVe(String maLoaiTuongTac, String tenLoaiTuongTac, LocalDateTime ngaySuaDoi) {
        setMaLoaiTuongTac(maLoaiTuongTac);
        setTenLoaiTuongTac(tenLoaiTuongTac);
        setNgaySuaDoi(ngaySuaDoi);
    }

    // Getter và Setter
    public String getMaLoaiTuongTac() {
        return maLoaiTuongTac;
    }

    public void setMaLoaiTuongTac(String maLoaiTuongTac) {
        this.maLoaiTuongTac = maLoaiTuongTac;
    }

    public String getTenLoaiTuongTac() {
        return tenLoaiTuongTac;
    }

    public void setTenLoaiTuongTac(String tenLoaiTuongTac) {
        this.tenLoaiTuongTac = tenLoaiTuongTac;
    }

    public LocalDateTime getNgaySuaDoi() {
        return ngaySuaDoi;
    }

    public void setNgaySuaDoi(LocalDateTime ngaySuaDoi) {
        this.ngaySuaDoi = ngaySuaDoi;
    }
}
