package fourcore.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ve {
	private String maVeTau;
	private String gaDi;
	private String gaDen;
	private String tenTau;
	private LocalDateTime ngayGioDi;
	private LocalDateTime ngayGioDen;
	private int soToa;
	private int soKhoang;
	private int soTang;
	private int soGhe;
	private String loaiVe;
	private String maGiayTo;
	private double giaVe;
	private String ghiChu;
	private String trangThaiDoiVe;
	private String trangThaiVe;
	private ChuyenTau chuyenTau;
	private KhachHang khachHang;
	private KhuyenMai khuyenMai;
	private DoiTuongGiamGia doiTuongGiamGia;

	public Ve() {

	}

	public Ve(String maVeTau) {

		this.maVeTau = maVeTau;
	}

	public Ve(String maVeTau, String gaDi, String gaDen, String tenTau, LocalDateTime ngayGioDi,
			LocalDateTime ngayGioDen, int soToa, int soKhoang, int soTang, int soGhe, String loaiVe, String maGiayTo,
			double giaVe, String ghiChu, String trangThaiDoiVe, String trangThaiVe, ChuyenTau chuyenTau,
			KhachHang khachHang, KhuyenMai khuyenMai, DoiTuongGiamGia doiTuongGiamGia) {
		setMaVeTau(maVeTau);
		setGaDi(gaDi);
		setGaDen(gaDen);
		setTenTau(tenTau);
		setNgayGioDi(ngayGioDi);
		setNgayGioDen(ngayGioDen);
		setSoToa(soToa);
		setSoKhoang(soKhoang);
		setSoTang(soTang);
		setSoGhe(soGhe);
		setLoaiVe(loaiVe);
		setMaGiayTo(maGiayTo);
		setGiaVe(giaVe);
		setGhiChu(ghiChu);
		setTrangThaiDoiVe(trangThaiDoiVe);
		setTrangThaiVe(trangThaiVe);
		setChuyenTau(chuyenTau);
		setKhachHang(khachHang);
		setKhuyenMai(khuyenMai);
		setDoiTuongGiamGia(doiTuongGiamGia);
	}

	public String getMaVeTau() {
		return maVeTau;
	}

	public void setMaVeTau(String maVeTau) {
		this.maVeTau = maVeTau;
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

	public String getTenTau() {
		return tenTau;
	}

	public void setTenTau(String tenTau) {
		this.tenTau = tenTau;
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

	public int getSoToa() {
		return soToa;
	}

	public void setSoToa(int soToa) {
		this.soToa = soToa;
	}

	public int getSoKhoang() {
		return soKhoang;
	}

	public void setSoKhoang(int soKhoang) {
		this.soKhoang = soKhoang;
	}

	public int getSoTang() {
		return soTang;
	}

	public void setSoTang(int soTang) {
		this.soTang = soTang;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public String getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(String loaiVe) {
		this.loaiVe = loaiVe;
	}

	public String getMaGiayTo() {
		return maGiayTo;
	}

	public void setMaGiayTo(String maGiayTo) {
		this.maGiayTo = maGiayTo;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getTrangThaiDoiVe() {
		return trangThaiDoiVe;
	}

	public void setTrangThaiDoiVe(String trangThaiDoiVe) {
		this.trangThaiDoiVe = trangThaiDoiVe;
	}

	public String getTrangThaiVe() {
		return trangThaiVe;
	}

	public void setTrangThaiVe(String trangThaiVe) {
		this.trangThaiVe = trangThaiVe;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public DoiTuongGiamGia getDoiTuongGiamGia() {
		return doiTuongGiamGia;
	}

	public void setDoiTuongGiamGia(DoiTuongGiamGia doiTuongGiamGia) {
		this.doiTuongGiamGia = doiTuongGiamGia;
	}
}
