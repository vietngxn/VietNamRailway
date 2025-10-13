package fourcore.VietNamRailwayInterface;


import java.time.LocalDate;
import java.time.LocalTime;

public class VeTau {
    private String maVe;
    private String chuyen;
    private String gaDi;
    private String gaDen;
    private LocalDate ngayKhoiHanh;
    private LocalTime gioKhoiHanh;
    private String viTriGhe;
    private String trangThai; // ví dụ: "Đã đặt", "Đã hủy", "Chưa thanh toán"

    // Constructor không tham số
    public VeTau() {}

    // Constructor đầy đủ
    public VeTau(String maVe, String chuyen, String gaDi, String gaDen,
                 LocalDate ngayKhoiHanh, LocalTime gioKhoiHanh,
                 String viTriGhe, String trangThai) {
        this.maVe = maVe;
        this.chuyen = chuyen;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.gioKhoiHanh = gioKhoiHanh;
        this.viTriGhe = viTriGhe;
        this.trangThai = trangThai;
    }

    // Getters và setters
    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getChuyen() {
        return chuyen;
    }

    public void setChuyen(String chuyen) {
        this.chuyen = chuyen;
    }

    public String getGaDi() {
        return gaDi;
    }

    public void setGaDi(String gaDi) {
        this.gaDi = gaDi;
    }

    public String getGaDen() {
        return gaDen;
    }

    public void setGaDen(String gaDen) {
        this.gaDen = gaDen;
    }

    public LocalDate getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public LocalTime getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(LocalTime gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    public String getViTriGhe() {
        return viTriGhe;
    }

    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "VeTau{" +
                "maVe='" + maVe + '\'' +
                ", chuyen='" + chuyen + '\'' +
                ", gaDi='" + gaDi + '\'' +
                ", gaDen='" + gaDen + '\'' +
                ", ngayKhoiHanh=" + ngayKhoiHanh +
                ", gioKhoiHanh=" + gioKhoiHanh +
                ", viTriGhe='" + viTriGhe + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
