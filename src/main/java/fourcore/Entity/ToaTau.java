package fourcore.Entity;

import java.io.Serializable;

public class ToaTau implements Serializable {
    private String maToaTau;
    private String tenToaTau;
    private int soGhe;
    private LoaiToaTau loaiToaTau;
    private boolean isRemove;


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
    public int getSoGhe() {
        return soGhe;
    }
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    
    
    public boolean isRemove() {
		return isRemove;
	}
	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}
	public ToaTau(String maToaTau, String tenToaTau, int soGhe,LoaiToaTau loaiToaTau) {
        setMaToaTau(maToaTau);
        setTenToaTau(tenToaTau);
        setSoGhe(soGhe);
        setLoaiToaTau(loaiToaTau);
    }
	public ToaTau(String maToaTau, String tenToaTau, int soGhe,LoaiToaTau loaiToaTau, boolean isRemove) {
        setMaToaTau(maToaTau);
        setTenToaTau(tenToaTau);
        setSoGhe(soGhe);
        setLoaiToaTau(loaiToaTau);
        setRemove(isRemove);
    }
    public ToaTau() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToaTau)) return false;
        ToaTau other = (ToaTau) o;
        return maToaTau != null && maToaTau.equalsIgnoreCase(other.getMaToaTau());
    }

    @Override
    public int hashCode() {
        return maToaTau == null ? 0 : maToaTau.toLowerCase().hashCode();
    }


}