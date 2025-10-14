package fourcore.Entity;

public class LoaiGhe {
    private String maLoaiGhe;
    private String tenLoaiGhe;
    private String giaLoaiGhe;

    public LoaiGhe(String maLoaiGhe, String tenLoaiGhe, String giaLoaiGhe) {
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

    public String getGiaLoaiGhe() {
        return giaLoaiGhe;
    }

    public void setGiaLoaiGhe(String giaLoaiGhe) {
        this.giaLoaiGhe = giaLoaiGhe;
    }
}


