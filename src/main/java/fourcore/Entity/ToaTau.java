package fourcore.Entity;

public class ToaTau {
    private String maToaTau;
    private String tenToaTau;
    private int soGhe;
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
    public int getSoGhe() {
        return soGhe;
    }
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public ToaTau(String maToaTau, String tenToaTau, int soGhe,LoaiToaTau loaiToaTau) {
        setMaToaTau(maToaTau);
        setTenToaTau(tenToaTau);
        setSoGhe(soGhe);

        setLoaiToaTau(loaiToaTau);
    }
    public ToaTau() {
    }



}