package fourcore.Entity;

public class ChiTietHoaDon {
	private String maChiTietHoaDon;
	private HoaDon hoaDon;
	private Ve veTau;
	private String moTa;
	private double donGia;
	private double thueVAT;
	private double thanhTien;
	private String LoaiHoaDonChoVe;

    public ChiTietHoaDon(HoaDon hoaDon, Ve ve, String moTa, double donGia, double thueVAT, double thanhTien, String LoaiHoaDonChoVe) {
        setHoaDon(hoaDon);
        setVeTau(ve);
        setMoTa(moTa);
        setDonGia(donGia);
        setThueVAT(thueVAT);
        setThanhTien(thanhTien);
        setLoaiHoaDonChoVe(LoaiHoaDonChoVe);
    }

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
		return donGia;
	}

	public void setDongia(double dongia) {
		this.donGia = dongia;
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

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getLoaiHoaDonChoVe() {
		return LoaiHoaDonChoVe;
	}

	public void setLoaiHoaDonChoVe(String loaiHoaDonChoVe) {
		LoaiHoaDonChoVe = loaiHoaDonChoVe;
	}

	public ChiTietHoaDon(String maChiTietHoaDon, HoaDon hoaDon, Ve veTau, String moTa, double dongia, double thueVAT,
			double thanhTien,String loaiHoaDon) {
		setMaChiTietHoaDon(maChiTietHoaDon);
		setHoaDon(hoaDon);
		setVeTau(veTau);
		setMoTa(moTa);
		setDongia(dongia);
		setThueVAT(thueVAT);
		setThanhTien(thanhTien);
		setLoaiHoaDonChoVe(loaiHoaDon);
	}


	public ChiTietHoaDon(String maChiTietHoaDon, HoaDon hoaDon, Ve veTau, String moTa, double dongia, double thueVAT,
			double thanhTien) {
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
