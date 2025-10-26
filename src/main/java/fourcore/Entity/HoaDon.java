package fourcore.Entity;

import java.time.LocalDateTime;

public class HoaDon {
	private String maHoaDon;
	private LoaiHoaDon maLoaiHoaDon;
	private NhanVien maNhanVien;
	private String tenKhachHangThanhToan;
	private String emailKhachHangThanhToan;
	private String cccdKhachHangThanhToan;
	private String sdtKhachHangThanhToan;
	private LocalDateTime ngayThanhToan;
	private double tongTien;

	// ===== Constructors =====
	public HoaDon() {
	}

	public HoaDon(String maHD, LoaiHoaDon loaihd, NhanVien maNV, String tenKHThanhToan, String emailKHThanhToan,
			String cccdKHThanhToan, String sdtKHThanhToan, LocalDateTime ngayTT, double tongTienHD) {
		maHoaDon = maHD;
		maLoaiHoaDon = loaihd;
		maNhanVien = maNV;
		tenKhachHangThanhToan = tenKHThanhToan;
		emailKhachHangThanhToan = emailKHThanhToan;
		cccdKhachHangThanhToan = cccdKHThanhToan;
		sdtKhachHangThanhToan = sdtKHThanhToan;
		ngayThanhToan = ngayTT;
		tongTien = tongTienHD;
	}

	// ===== Getters & Setters =====
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

	public String getTenKhachHangThanhToan() {
		return tenKhachHangThanhToan;
	}

	public void setTenKhachHangThanhToan(String tenKhachHangThanhToan) {
		this.tenKhachHangThanhToan = tenKhachHangThanhToan;
	}

	public String getEmailKhachHangThanhToan() {
		return emailKhachHangThanhToan;
	}

	public void setEmailKhachHangThanhToan(String emailKhachHangThanhToan) {
		this.emailKhachHangThanhToan = emailKhachHangThanhToan;
	}

	public String getCccdKhachHangThanhToan() {
		return cccdKhachHangThanhToan;
	}

	public void setCccdKhachHangThanhToan(String cccdKhachHangThanhToan) {
		this.cccdKhachHangThanhToan = cccdKhachHangThanhToan;
	}

	public String getSdtKhachHangThanhToan() {
		return sdtKhachHangThanhToan;
	}

	public void setSdtKhachHangThanhToan(String sdtKhachHangThanhToan) {
		this.sdtKhachHangThanhToan = sdtKhachHangThanhToan;
	}

	public LocalDateTime getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public LoaiHoaDon getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}

	public void setMaLoaiHoaDon(LoaiHoaDon maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}

	// ===== toString() =====
	@Override
	public String toString() {
		return "HoaDon{" + "maHoaDon='" + maHoaDon + '\'' + ", maNhanVien='" + maNhanVien + '\''
				+ ", tenKhachHangThanhToan='" + tenKhachHangThanhToan + '\'' + ", emailKhachHangThanhToan='"
				+ emailKhachHangThanhToan + '\'' + ", cccdKhachHangThanhToan='" + cccdKhachHangThanhToan + '\''
				+ ", sdtKhachHangThanhToan='" + sdtKhachHangThanhToan + '\'' + ", ngayThanhToan=" + ngayThanhToan
				+ ", tongTien=" + tongTien + '}';
	}
}
