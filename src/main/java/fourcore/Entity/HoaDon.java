package fourcore.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDon {
	private String maHoaDon;
	private NhanVien nhanVien;
	private LocalDateTime ngayThanhToan;
	private double tongtien;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public LocalDateTime getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public double getTongtien() {
		return tongtien;
	}
	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}
	public HoaDon(String maHoaDon, NhanVien nhanVien, LocalDateTime ngayThanhToan, double tongtien) {
		setMaHoaDon(maHoaDon);
		setNgayThanhToan(ngayThanhToan);
		setTongtien(tongtien);
        setNhanVien(nhanVien);
	}
	public HoaDon() {

	}

	
	
	
}
