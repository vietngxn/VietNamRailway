package fourcore.Entity;

import java.time.LocalDateTime;

public class Ga {
	private String maGa;
	private String tenGa;
	private double cuLy;
	private LocalDateTime thoiGian;

	public Ga() {
	}
    public  Ga(String maGa, String tenGa, double cuLy) {
        setMaGa(maGa);
        setTenGa(tenGa);
        setCuLy(cuLy);
    }
    public  Ga(String maGa, String tenGa) {
        setMaGa(maGa);
        setTenGa(tenGa);
    }
	public Ga(String maGa, String tenGa, double cuLy, LocalDateTime thoiGian) {
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

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}
}
