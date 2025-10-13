package fourcore.Entity;

import java.time.LocalDateTime;

public class Tau {
	private String maTau;
	private String tenTau;
	private String maLoaiTau;
	private LocalDateTime ngaySuaDoi;

	public Tau() {
	}

	public Tau(String maTau, String tenTau, String maLoaiTau, LocalDateTime ngaySuaDoi) {
		setMaTau(maTau);
		setTenTau(tenTau);
		setMaLoaiTau(maLoaiTau);
		setNgaySuaDoi(ngaySuaDoi);
	}

	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		this.maTau = maTau;
	}

	public String getTenTau() {
		return tenTau;
	}

	public void setTenTau(String tenTau) {
		this.tenTau = tenTau;
	}

	public String getMaLoaiTau() {
		return maLoaiTau;
	}

	public void setMaLoaiTau(String maLoaiTau) {
		this.maLoaiTau = maLoaiTau;
	}

	public LocalDateTime getNgaySuaDoi() {
		return ngaySuaDoi;
	}

	public void setNgaySuaDoi(LocalDateTime ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
}
