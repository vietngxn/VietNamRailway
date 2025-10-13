package fourcore.Entity;

import java.time.LocalDate;

public class HoaDon {
	private String maHoaDon;
	private NhanVien maNhanVien;
	private LocalDate ngayThanhToan;
	private double tongtien;
	
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public LocalDate getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(LocalDate ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public double getTongtien() {
		return tongtien;
	}
	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}
	public HoaDon(String maHoaDon, NhanVien maNhanVien, LocalDate ngayThanhToan, double tongtien) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.ngayThanhToan = ngayThanhToan;
		this.tongtien = tongtien;
	}
	public HoaDon() {
		super();
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", ngayThanhToan=" + ngayThanhToan
				+ ", tongtien=" + tongtien + "]";
	}
	
	
	
}
