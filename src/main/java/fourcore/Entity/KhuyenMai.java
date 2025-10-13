package fourcore.Entity;

import java.time.LocalDate;

public class KhuyenMai {

	String maKhuyenMai, tenChuongTrinh, trangThaiKhuyenMai, dieuKienApDung;
	double giaTriPhanTramKhuyenMai;
	LocalDate ngayBatDau, ngayKetThuc, ngaySuaDoi;
	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}
	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}
	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}
	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}
	public String getTrangThaiKhuyenMai() {
		return trangThaiKhuyenMai;
	}
	public void setTrangThaiKhuyenMai(String trangThaiKhuyenMai) {
		this.trangThaiKhuyenMai = trangThaiKhuyenMai;
	}
	public String getDieuKienApDung() {
		return dieuKienApDung;
	}
	public void setDieuKienApDung(String dieuKienApDung) {
		this.dieuKienApDung = dieuKienApDung;
	}
	public double getGiaTriPhanTramKhuyenMai() {
		return giaTriPhanTramKhuyenMai;
	}
	public void setGiaTriPhanTramKhuyenMai(double giaTriPhanTramKhuyenMai) {
		this.giaTriPhanTramKhuyenMai = giaTriPhanTramKhuyenMai;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public LocalDate getNgaySuaDoi() {
		return ngaySuaDoi;
	}
	public void setNgaySuaDoi(LocalDate ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
	public KhuyenMai() {}
	public KhuyenMai(String maKhuyenMai, String tenChuongTrinh, String trangThaiKhuyenMai, String dieuKienApDung,
			double giaTriPhanTramKhuyenMai, LocalDate ngayBatDau, LocalDate ngayKetThuc, LocalDate ngaySuaDoi) {
		super();
		setMaKhuyenMai(maKhuyenMai);
		setTenChuongTrinh(tenChuongTrinh);
		setTrangThaiKhuyenMai(trangThaiKhuyenMai);
		setDieuKienApDung(dieuKienApDung);
		setGiaTriPhanTramKhuyenMai(giaTriPhanTramKhuyenMai);
		setNgayBatDau(ngayBatDau);
		setNgayKetThuc(ngayKetThuc);
		setNgaySuaDoi(ngaySuaDoi);
	}
	
}
