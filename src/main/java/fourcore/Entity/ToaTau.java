package fourcore.Entity;

public class ToaTau {
	private String maToaTau;
	private LoaiToaTau maLoaiToaTau;
	// phần này long làm chuyến tàu xong làm khóa ngoại vô giúp đạt nha
	private String tenToaTau;
	private int soToa;
	public String getMaToaTau() {
		return maToaTau;
	}
	public void setMaToaTau(String maToaTau) {
		this.maToaTau = maToaTau;
	}
	public LoaiToaTau getMaLoaiToaTau() {
		return maLoaiToaTau;
	}
	public void setMaLoaiToaTau(LoaiToaTau maLoaiToaTau) {
		this.maLoaiToaTau = maLoaiToaTau;
	}
	public String getTenToaTau() {
		return tenToaTau;
	}
	public void setTenToaTau(String tenToaTau) {
		this.tenToaTau = tenToaTau;
	}
	public int getSoToa() {
		return soToa;
	}
	public void setSoToa(int soToa) {
		this.soToa = soToa;
	}
	public ToaTau(String maToaTau, LoaiToaTau maLoaiToaTau, String tenToaTau, int soToa) {
		super();
		setMaToaTau(maToaTau);
		setMaLoaiToaTau(maLoaiToaTau);
		setTenToaTau(tenToaTau);
		setSoToa(soToa);
	}
	public ToaTau() {
		super();
	}
	@Override
	public String toString() {
		return "ToaTau [maToaTau=" + maToaTau + ", maLoaiToaTau=" + maLoaiToaTau + ", tenToaTau=" + tenToaTau
				+ ", soToa=" + soToa + "]";
	}
	
}
