package fourcore.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChuyenTau implements Serializable {
    private String maChuyenTau;
    private Tau tau;
    private HanhTrinh hanhTrinh;
    private LocalDateTime ngayGioDi;
    private LocalDateTime ngayGioDen;
    private double giaCuocTrenChuyenTau;
    private boolean isRemove;
    public ChuyenTau(String maChuyenTau, Tau tau, HanhTrinh hanhTrinh, LocalDateTime ngayGioDi,
			LocalDateTime ngayGioDen, double giaCuocTrenChuyenTau, boolean isRemove) {
		this.maChuyenTau = maChuyenTau;
		this.tau = tau;
		this.hanhTrinh = hanhTrinh;
		this.ngayGioDi = ngayGioDi;
		this.ngayGioDen = ngayGioDen;
		this.giaCuocTrenChuyenTau = giaCuocTrenChuyenTau;
		this.isRemove = isRemove;
	}

	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public ChuyenTau() {
    }

    public ChuyenTau(double giaCuocTrenChuyenTau, LocalDateTime ngayGioDen, LocalDateTime ngayGioDi, Tau tau, String maChuyenTau) {
        this.giaCuocTrenChuyenTau = giaCuocTrenChuyenTau;
        this.ngayGioDen = ngayGioDen;
        this.ngayGioDi = ngayGioDi;
        this.tau = tau;
        this.maChuyenTau = maChuyenTau;
    }

    public ChuyenTau(String maChuyenTau) {
		super();
		this.maChuyenTau = maChuyenTau;
	}

	public ChuyenTau(String maChuyenTau, Tau tau, HanhTrinh hanhTrinh, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen, double giaCuocTrenChuyenTau ) {
        setMaChuyenTau(maChuyenTau);
        setTau(tau);
        setHanhTrinh(hanhTrinh);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setGiaCuocTrenChuyenTau(giaCuocTrenChuyenTau);
    }

    public String getMaChuyenTau() {
        return maChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.maChuyenTau = maChuyenTau;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        this.tau = tau;
    }

    public HanhTrinh getHanhTrinh() {
        return hanhTrinh;
    }

    public void setHanhTrinh(HanhTrinh hanhTrinh) {
        this.hanhTrinh = hanhTrinh;
    }

    public LocalDateTime getNgayGioDi() {
        return ngayGioDi;
    }

    public void setNgayGioDi(LocalDateTime ngayGioDi) {
        this.ngayGioDi = ngayGioDi;
    }

    public LocalDateTime getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(LocalDateTime ngayGioDen) {
        this.ngayGioDen = ngayGioDen;
    }

    public double getGiaCuocTrenChuyenTau() {
        return giaCuocTrenChuyenTau;
    }

    public void setGiaCuocTrenChuyenTau(double giaCuocTrenChuyenTau) {
        this.giaCuocTrenChuyenTau = giaCuocTrenChuyenTau;
    }
}
