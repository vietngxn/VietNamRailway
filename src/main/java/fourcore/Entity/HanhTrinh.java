package fourcore.Entity;


public class HanhTrinh {
    private String maHanhTrinh;
    private String tenHanhTrinh;

    public HanhTrinh() {
    }

    public HanhTrinh(String maHanhTrinh, String tenHanhTrinh) {
        setMaHanhTrinh(maHanhTrinh);
        setTenHanhTrinh(tenHanhTrinh);
    }

    public String getMaHanhTrinh() {
        return maHanhTrinh;
    }

    public void setMaHanhTrinh(String maHanhTrinh) {
        this.maHanhTrinh = maHanhTrinh;
    }

    public String getTenHanhTrinh() {
        return tenHanhTrinh;
    }

    public void setTenHanhTrinh(String tenHanhTrinh) {
        this.tenHanhTrinh = tenHanhTrinh;
    }

}
