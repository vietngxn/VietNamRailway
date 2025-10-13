package fourcore.Entity;

import java.time.LocalDate;

public class LoaiToaTau {
	private String maLoaiToaTau;
	private String tenLoaiToaTau;
	private LocalDate ngaySuaDoi;
	public String getMaLoaiToaTau() {
		return maLoaiToaTau;
	}
	public void setMaLoaiToaTau(String maLoaiToaTau) {
		this.maLoaiToaTau = maLoaiToaTau;
	}
	public String getTenLoaiToaTau() {
		return tenLoaiToaTau;
	}
	public void setTenLoaiToaTau(String tenLoaiToaTau) {
		this.tenLoaiToaTau = tenLoaiToaTau;
	}
	public LocalDate getNgaySuaDoi() {
		return ngaySuaDoi;
	}
	public void setNgaySuaDoi(LocalDate ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
	public LoaiToaTau(String maLoaiToaTau, String tenLoaiToaTau, LocalDate ngaySuaDoi) {
		super();
		setMaLoaiToaTau(maLoaiToaTau);
		setTenLoaiToaTau(tenLoaiToaTau);
		setNgaySuaDoi(ngaySuaDoi);
	}
	public LoaiToaTau() {
		super();
	}
	@Override
	public String toString() {
		return "LoaiToaTau [maLoaiToaTau=" + maLoaiToaTau + ", tenLoaiToaTau=" + tenLoaiToaTau + ", ngaySuaDoi="
				+ ngaySuaDoi + "]";
	}
	
	
	
}
