package fourcore.Entity;

public class ChiTietHoaDon {
	private String maChiTietHoaDon;
	private HoaDon maHoaDon;
	// Phần này có khóa ngoại là mã vé tàu có gì tiến làm xong tiến ghép khóa ngoại giúp đạt
	private String moTa;
	private double dongia;
	private double thueVAT;
	private double thanhTien;
	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(String maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public double getDongia() {
		return dongia;
	}
	public void setDongia(double dongia) {
		this.dongia = dongia;
	}
	public double getThueVAT() {
		return thueVAT;
	}
	public void setThueVAT(double thueVAT) {
		this.thueVAT = thueVAT;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon(String maChiTietHoaDon, HoaDon maHoaDon, String moTa, double dongia, double thueVAT,
			double thanhTien) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.maHoaDon = maHoaDon;
		this.moTa = moTa;
		this.dongia = dongia;
		this.thueVAT = thueVAT;
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon() {
		super();
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maChiTietHoaDon=" + maChiTietHoaDon + ", maHoaDon=" + maHoaDon + ", moTa=" + moTa
				+ ", dongia=" + dongia + ", thueVAT=" + thueVAT + ", thanhTien=" + thanhTien + "]";
	}
	
}
