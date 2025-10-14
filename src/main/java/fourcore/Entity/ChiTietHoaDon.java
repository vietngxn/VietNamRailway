package fourcore.Entity;

public class ChiTietHoaDon {
	private String maChiTietHoaDon;
	private HoaDon hoaDon;
    private Ve veTau;
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

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Ve getVeTau() {
        return veTau;
    }

    public void setVeTau(Ve veTau) {
        this.veTau = veTau;
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
	public ChiTietHoaDon(String maChiTietHoaDon, HoaDon hoaDon,Ve veTau, String moTa, double dongia, double thueVAT, double thanhTien) {
		setMaChiTietHoaDon(maChiTietHoaDon);
		setHoaDon(hoaDon);
        setVeTau(veTau);
		setMoTa(moTa);
		setDongia(dongia);
		setThueVAT(thueVAT);
		setThanhTien(thanhTien);
	}
	public ChiTietHoaDon() {
	}
	
}
