package fourcore.Entity;

import java.time.LocalDate;

public class Ve {

	String maVeTau, gaDi, gaDen, tenTau, loaiGhe, loaiVe, maGiayTo, ghiChu, trangThaiDoiVe, trangThaiVe, maChuyenTau, maKhachHang, maNhanVien, maKhuyenMai, maDoiTuongGiamGia;
	LocalDate ngayDi, gioDi;
	double giaVe;
	int soToa, soKhoang, soTang, soGhe;
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
	public String getLoaiGhe() {
		return loaiGhe;
	}
	public void setLoaiGhe(String loaiGhe) {
		this.loaiGhe = loaiGhe;
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
	public String getMaChuyenTau() {
		return maChuyenTau;
	}
	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}
	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}
	public String getMaDoiTuongGiamGia() {
		return maDoiTuongGiamGia;
	}
	public void setMaDoiTuongGiamGia(String maDoiTuongGiamGia) {
		this.maDoiTuongGiamGia = maDoiTuongGiamGia;
	}
	public LocalDate getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(LocalDate ngayDi) {
		this.ngayDi = ngayDi;
	}
	public LocalDate getGioDi() {
		return gioDi;
	}
	public void setGioDi(LocalDate gioDi) {
		this.gioDi = gioDi;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
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
	public Ve() {}
	public Ve(String maVeTau, String gaDi, String gaDen, String tenTau, String loaiGhe, String loaiVe, String maGiayTo,
			String ghiChu, String trangThaiDoiVe, String trangThaiVe, String maChuyenTau, String maKhachHang,
			String maNhanVien, String maKhuyenMai, String maDoiTuongGiamGia, LocalDate ngayDi, LocalDate gioDi,
			double giaVe, int soToa, int soKhoang, int soTang, int soGhe) {
		super();
		setMaVeTau(maVeTau);
		setGaDi(gaDi);
		setGaDen(gaDen);
		setTenTau(tenTau);
		setLoaiGhe(loaiGhe);
		setLoaiVe(loaiVe);
		setMaGiayTo(maGiayTo);
		setGhiChu(ghiChu);
		setTrangThaiDoiVe(trangThaiDoiVe);
		setTrangThaiVe(trangThaiVe);
		setMaChuyenTau(maChuyenTau);
		setMaKhachHang(maKhachHang);
		setMaNhanVien(maNhanVien);
		setMaKhuyenMai(maKhuyenMai);
		setMaDoiTuongGiamGia(maDoiTuongGiamGia);
		setNgayDi(ngayDi);
		setGioDi(gioDi);
		setGiaVe(giaVe);
		setSoToa(soToa);
		setSoKhoang(soKhoang);
		setSoTang(soTang);
		setSoGhe(soGhe);
	}
	@Override
	public String toString() {
		return "Ve [maVeTau=" + maVeTau + ", gaDi=" + gaDi + ", gaDen=" + gaDen + ", tenTau=" + tenTau + ", loaiGhe="
				+ loaiGhe + ", loaiVe=" + loaiVe + ", maGiayTo=" + maGiayTo + ", ghiChu=" + ghiChu + ", trangThaiDoiVe="
				+ trangThaiDoiVe + ", trangThaiVe=" + trangThaiVe + ", maChuyenTau=" + maChuyenTau + ", maKhachHang="
				+ maKhachHang + ", maNhanVien=" + maNhanVien + ", maKhuyenMai=" + maKhuyenMai + ", maDoiTuongGiamGia="
				+ maDoiTuongGiamGia + ", ngayDi=" + ngayDi + ", gioDi=" + gioDi + ", giaVe=" + giaVe + ", soToa="
				+ soToa + ", soKhoang=" + soKhoang + ", soTang=" + soTang + ", soGhe=" + soGhe + "]";
	}
	
}
