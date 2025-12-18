package fourcore.Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class NhanVien implements Serializable  {
	private String maNhanVien;
	private String hoTen;
	private ChucVu chucVu;
	private LocalDate ngaySinh;
	private String diaChi;
	private String email;
	private String sdt;
	private LocalDate ngayVaoLam;
	private String tinhTrangLamViec;
	private String gioiTinh;
	private String cccd;
	private boolean isRemove;
	public NhanVien(String maNhanVien, String hoTen, ChucVu chucVu, LocalDate ngaySinh, String diaChi, String email,
			String sdt, LocalDate ngayVaoLam, String tinhTrangLamViec, String gioiTinh, String cccd, boolean isRemove) {
		
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.chucVu = chucVu;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.sdt = sdt;
		this.ngayVaoLam = ngayVaoLam;
		this.tinhTrangLamViec = tinhTrangLamViec;
		this.gioiTinh = gioiTinh;
		this.cccd = cccd;
		this.isRemove = isRemove;
	}
	public boolean getisRemove() {
		return isRemove;
	}
	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public String getTinhTrangLamViec() {
		return tinhTrangLamViec;
	}
	public void setTinhTrangLamViec(String tinhTrangLamViec) {
		this.tinhTrangLamViec = tinhTrangLamViec;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	
	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public NhanVien(String maNhanVien, String hoTen, String sdt,String gioiTinh, String email,String cccd,String tinhTrangLamViec) {
		
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.email = email;
		this.sdt = sdt;
		this.tinhTrangLamViec = tinhTrangLamViec;
		this.gioiTinh = gioiTinh;
		this.cccd = cccd;
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", chucVu=" + chucVu + ", ngaySinh="
				+ ngaySinh + ", diaChi=" + diaChi + ", email=" + email + ", sdt=" + sdt + ", ngayVaoLam=" + ngayVaoLam
				+ ", tinhTrangLamViec=" + tinhTrangLamViec + ", gioiTinh=" + gioiTinh + ", cccd=" + cccd + "]";
	}
	public NhanVien(String maNhanVien, String hoTen, ChucVu chucVu, LocalDate ngaySinh, String diaChi, String email,
			String sdt, LocalDate ngayVaoLam, String tinhTrangLamViec, String gioiTinh, String cccd) {
		setMaNhanVien(maNhanVien);
		setHoTen(hoTen);
		setChucVu(chucVu);
		setNgaySinh(ngaySinh);
		setDiaChi(diaChi);
		setEmail(email);
		setSdt(sdt);
		setNgayVaoLam(ngayVaoLam);
		setTinhTrangLamViec(tinhTrangLamViec);
		setGioiTinh(gioiTinh);
		setCccd(cccd);
	}
	
	public NhanVien() {
	}
	
}
