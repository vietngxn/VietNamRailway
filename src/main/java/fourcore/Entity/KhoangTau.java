package fourcore.Entity;

public class KhoangTau {
    private String maKhoang;
    private int soKhoang;

    public KhoangTau(String maKhoang, int soKhoang) {
        setMaKhoang(maKhoang);
        setSoKhoang(soKhoang);
    }

    public KhoangTau() {}

    public String getMaKhoang() {
        return maKhoang;
    }
    public void setMaKhoang(String maKhoang) {
        this.maKhoang = maKhoang;
    }
    public int getSoKhoang() {
        return soKhoang;
    }
    public void setSoKhoang(int soKhoang) {
        this.soKhoang = soKhoang;
    }
}
