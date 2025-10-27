package fourcore.Entity;

import java.io.Serializable;

public class Tang implements Serializable {
    private String maTang;
    private int soTang;
    private double giaTang;

    public Tang(String maTang, int soTang, double giaTang) {
        setMaTang(maTang);
        setSoTang(soTang);
        setGiaTang(giaTang);
    }
    public Tang() {}

    public String getMaTang() {
        return maTang;
    }
    public void setMaTang(String maTang) {
        this.maTang = maTang;
    }
    public int getSoTang() {
        return soTang;
    }
    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }
    public double getGiaTang() {
        return giaTang;
    }
    public void setGiaTang(double giaTang) {
        this.giaTang = giaTang;
    }
}
