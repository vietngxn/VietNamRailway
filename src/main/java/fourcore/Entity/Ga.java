package fourcore.Entity;


public class Ga {
    private String maGa;
    private String tenGa;
    private double cuLy;
    private double thoiGian;


    public Ga() {}

    public Ga(String maGa, String tenGa, double cuLy, double thoiGian) {
        setMaGa(maGa);
        setTenGa(tenGa);
        setCuLy(cuLy);
        setThoiGian(thoiGian);
    }

    public String getMaGa() {
        return maGa;
    }

    public void setMaGa(String maGa) {
        this.maGa = maGa;
    }

    public String getTenGa() {
        return tenGa;
    }

    public void setTenGa(String tenGa) {
        this.tenGa = tenGa;
    }

    public double getCuLy() {
        return cuLy;
    }

    public void setCuLy(double cuLy) {
        this.cuLy = cuLy;
    }

    public double getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(double thoiGian) {
        this.thoiGian = thoiGian;
    }
}
