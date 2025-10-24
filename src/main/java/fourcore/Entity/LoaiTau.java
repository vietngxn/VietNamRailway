package fourcore.Entity;


import java.io.Serializable;

public class LoaiTau implements Serializable {
    private String maLoaiTau;
    private String tenLoaiTau;
    private double giaCuoc;
    public LoaiTau() {}
    public LoaiTau(String maLoaiTau, String tenLoaiTau, double giaCuoc) {
        setMaLoaiTau(maLoaiTau);
        setTenLoaiTau(tenLoaiTau);
        setGiaCuoc(giaCuoc);
    }
    public String getMaLoaiTau() {
        return maLoaiTau;
    }
    public void setMaLoaiTau(String maLoaiTau) {
        this.maLoaiTau = maLoaiTau;
    }
    public String getTenLoaiTau() {
        return tenLoaiTau;
    }
    public void setTenLoaiTau(String tenLoaiTau) {
        this.tenLoaiTau = tenLoaiTau;
    }
    public double getGiaCuoc() {
        return giaCuoc;
    }
    public void setGiaCuoc(double giaCuoc) {
        this.giaCuoc = giaCuoc;
    }

}
