package fourcore.Entity;

public class GheNgoi {
    private String maGheNgoi;
    private LoaiGhe loaiGhe;
    private Tang tang;
    private KhoangTau khoangTau;
    private ToaTau toaTau;
    private int soGhe;
    private double giaTriTangThem;
    private boolean luuDong;

    public GheNgoi(String maGheNgoi, LoaiGhe loaiGhe, Tang tang, KhoangTau khoangTau, ToaTau toaTau, int soGhe, double giaTriTangThem, boolean luuDong) {
        setMaGheNgoi(maGheNgoi);
        setLoaiGhe(loaiGhe);
        setTang(tang);
        setKhoangTau(khoangTau);
        setToaTau(toaTau);
        setSoGhe(soGhe);
        setGiaTriTangThem(giaTriTangThem);
        setLuuDong(luuDong);
    }

    public Tang getTang() {
        return tang;
    }

    public void setTang(Tang tang) {
        this.tang = tang;
    }

    public KhoangTau getKhoangTau() {
        return khoangTau;
    }

    public void setKhoangTau(KhoangTau khoangTau) {
        this.khoangTau = khoangTau;
    }

    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    public boolean isLuuDong() {
        return luuDong;
    }

    public void setLuuDong(boolean luuDong) {
        this.luuDong = luuDong;
    }

    public double getGiaTriTangThem() {
        return giaTriTangThem;
    }

    public void setGiaTriTangThem(double giaTriTangThem) {
        this.giaTriTangThem = giaTriTangThem;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public LoaiGhe getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(LoaiGhe loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public String getMaGheNgoi() {
        return maGheNgoi;
    }

    public void setMaGheNgoi(String maGheNgoi) {
        this.maGheNgoi = maGheNgoi;
    }
}

