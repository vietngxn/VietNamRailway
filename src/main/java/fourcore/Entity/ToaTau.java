package fourcore.Entity;

public class ToaTau {
	private String maToaTau;
	private String tenToaTau;
	private int soToa;
    private LoaiToaTau loaiToaTau;



	public String getMaToaTau() {
		return maToaTau;
	}
	public void setMaToaTau(String maToaTau) {
		this.maToaTau = maToaTau;
	}
    public LoaiToaTau getLoaiToaTau() {
        return loaiToaTau;
    }
    public void setLoaiToaTau(LoaiToaTau loaiToaTau) {
        this.loaiToaTau = loaiToaTau;
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



    public ToaTau(String maToaTau, String tenToaTau, int soToa,  LoaiToaTau loaiToaTau) {
        setMaToaTau(maToaTau);
        setTenToaTau(tenToaTau);
        setSoToa(soToa);
        setLoaiToaTau(loaiToaTau);
    }
	public ToaTau() {
	}


	
}
