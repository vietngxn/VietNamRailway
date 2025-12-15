package fourcore.Entity;

public class ThongTinCtHoaDon {
    String maVe;
    String tenLoaiGhe;
    String doiTuong;
    double donGia;
    double thanhTien;
    boolean isKhuHoi = false;
    public ThongTinCtHoaDon(String maVe, String tenLoaiGhe, String doiTuong, double donGia, double thanhTien){
        this.maVe = maVe;
        this.tenLoaiGhe = tenLoaiGhe;
        this.doiTuong = doiTuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    public ThongTinCtHoaDon(String tenLoaiGhe, String doiTuong, double donGia, double thanhTien){
        this.maVe = maVe;
        this.tenLoaiGhe = tenLoaiGhe;
        this.doiTuong = doiTuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    public void setKhuHoi(boolean isKhuHoi){
        this.isKhuHoi = isKhuHoi;
    }
    public boolean isKhuHoi(){
        return isKhuHoi;
    }
    public String getMaVe() {
        return maVe;
    }

    public String getTenLoaiGhe() {
        return tenLoaiGhe;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }
}

