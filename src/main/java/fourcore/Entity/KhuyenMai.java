package fourcore.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KhuyenMai {

	private String maKhuyenMai, tenChuongTrinh, trangThaiKhuyenMai, dieuKienApDung;
	private  double giaTriPhanTramKhuyenMai;
	LocalDateTime ngayBatDau, ngayKetThuc;
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
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public KhuyenMai() {}
	public KhuyenMai(String maKhuyenMai, String tenChuongTrinh, String trangThaiKhuyenMai, String dieuKienApDung,
			double giaTriPhanTramKhuyenMai, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
		setMaKhuyenMai(maKhuyenMai);
		setTenChuongTrinh(tenChuongTrinh);
		setTrangThaiKhuyenMai(trangThaiKhuyenMai);
		setDieuKienApDung(dieuKienApDung);
		setGiaTriPhanTramKhuyenMai(giaTriPhanTramKhuyenMai);
		setNgayBatDau(ngayBatDau);
		setNgayKetThuc(ngayKetThuc);
	}
	
}
