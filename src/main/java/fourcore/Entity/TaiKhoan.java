package fourcore.Entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private String maNhanVien;
	private String tenDangNhap;
	private String vaiTro;
	private String matKhau;
	private boolean isRemove;
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	public TaiKhoan() {
		
	}
	public TaiKhoan(String maTaiKhoan, String maNhanVien, String tenDangNhap, String matKhau,boolean isRemove) {
		
		this.maTaiKhoan = maTaiKhoan;
		this.maNhanVien = maNhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.isRemove = isRemove;
	}
	public boolean getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}
	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", maNhanVien=" + maNhanVien + ", tenDangNhap=" + tenDangNhap
				+ ", vaiTro=" + vaiTro + ", matKhau=" + matKhau + "]";
	}
	
	
	
}
