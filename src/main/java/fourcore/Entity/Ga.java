package fourcore.Entity;

import java.time.LocalDateTime;

public class Ga {
    private String maGa;
    private String tenGa;
    private double cuLy;
    private LocalDateTime ngaySuaDoi;


    public Ga() {
    	
    }

    public Ga(String maGa, String tenGa, double cuLy, LocalDateTime ngaySuaDoi) {
        setMaGa(maGa);
        setTenGa(tenGa);
        setCuLy(cuLy);
        setNgaySuaDoi(ngaySuaDoi);
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

    public LocalDateTime getNgaySuaDoi() {
        return ngaySuaDoi;
    }

    public void setNgaySuaDoi(LocalDateTime ngaySuaDoi) {
        this.ngaySuaDoi = ngaySuaDoi;
    }
}
