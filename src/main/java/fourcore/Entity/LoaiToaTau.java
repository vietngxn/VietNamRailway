package fourcore.Entity;

public class LoaiToaTau {
	private String maLoaiToaTau;
	private String tenLoaiToaTau;

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

	public LoaiToaTau(String maLoaiToaTau, String tenLoaiToaTau) {
		setMaLoaiToaTau(maLoaiToaTau);
		setTenLoaiToaTau(tenLoaiToaTau);
	}

	public LoaiToaTau() {
	}

}
