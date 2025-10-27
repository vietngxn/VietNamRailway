package fourcore.Entity;

import java.io.Serializable;

public class GheTrenChuyenTau implements Serializable {
    private String maGheTrenChuyenTau;
    private String trangThaiGhe;
    private double giaTienGhe;
    private ChuyenTau chuyenTau;


	private GheNgoi gheNgoi;

    public GheTrenChuyenTau(String maGheTrenChuyenTau, String trangThaiGhe, double giaTienGhe, ChuyenTau chuyenTau, GheNgoi gheNgoi) {
        setMaGheTrenChuyenTau(maGheTrenChuyenTau);
        setTrangThaiGhe(trangThaiGhe);
        setGiaTienGhe(giaTienGhe);
        setChuyenTau(chuyenTau);
        setGheNgoi(gheNgoi);
    }

    public GheTrenChuyenTau() {
    }

    public String getMaGheTrenChuyenTau() {
        return maGheTrenChuyenTau;
    }

    public void setMaGheTrenChuyenTau(String maGheTrenChuyenTau) {
        this.maGheTrenChuyenTau = maGheTrenChuyenTau;
    }

    public String getTrangThaiGhe() {
        return trangThaiGhe;
    }

    public void setTrangThaiGhe(String trangThaiGhe) {
        this.trangThaiGhe = trangThaiGhe;
    }

    public double getGiaTienGhe() {
        return giaTienGhe;
    }

    public void setGiaTienGhe(double giaTienGhe) {
        this.giaTienGhe = giaTienGhe;
    }

    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;
    }

    public GheNgoi getGheNgoi() {
        return gheNgoi;
    }

    public void setGheNgoi(GheNgoi gheNgoi) {
        this.gheNgoi = gheNgoi;
    }
    

}
