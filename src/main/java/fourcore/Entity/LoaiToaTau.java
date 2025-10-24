package fourcore.Entity;

public class LoaiToaTau {
	private String maLoaiToaTau;
	private String tenLoaiToaTau;
	private double giaCuoc;

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

	public double getGiaCuoc() {
		return giaCuoc;
	}

	public void setGiaCuoc(double giaCuoc) {
		this.giaCuoc = giaCuoc;
	}

	public LoaiToaTau(String maLoaiToaTau, String tenLoaiToaTau, double giaCuoc) {
		setMaLoaiToaTau(maLoaiToaTau);
		setTenLoaiToaTau(tenLoaiToaTau);
		setGiaCuoc(giaCuoc);
	}
    public LoaiToaTau(String maLoaiToaTau, String tenLoaiToaTau) {
        setMaLoaiToaTau(maLoaiToaTau);
        setTenLoaiToaTau(tenLoaiToaTau);
    }

	public LoaiToaTau() {
	}

}
