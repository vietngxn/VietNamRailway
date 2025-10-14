package fourcore.Entity;

public class LoaiGhe {
    private String maLoaiGhe;
    private String tenLoaiGhe;
    private double giaLoaiGhe;

    public LoaiGhe(String maLoaiGhe, String tenLoaiGhe, double giaLoaiGhe) {
        setMaLoaiGhe(maLoaiGhe);
        setTenLoaiGhe(tenLoaiGhe);
        setGiaLoaiGhe(giaLoaiGhe);
    }
    public LoaiGhe() {}

    public String getMaLoaiGhe() {
        return maLoaiGhe;
    }

    public void setMaLoaiGhe(String maLoaiGhe) {
        this.maLoaiGhe = maLoaiGhe;
    }

    public String getTenLoaiGhe() {
        return tenLoaiGhe;
    }

    public void setTenLoaiGhe(String tenLoaiGhe) {
        this.tenLoaiGhe = tenLoaiGhe;
    }

    public double getGiaLoaiGhe() {
        return giaLoaiGhe;
    }

    public void setGiaLoaiGhe(double giaLoaiGhe) {
        this.giaLoaiGhe = giaLoaiGhe;
    }
}


