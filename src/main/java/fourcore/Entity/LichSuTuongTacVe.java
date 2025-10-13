package fourcore.Entity;

import java.time.LocalDate;

public class LichSuTuongTacVe {

	String maTuongTac, maLoaiTuongTac, maVeTau;
	double giaTriChenhLech;
	LocalDate ngayTuongTac;
	public String getMaTuongTac() {
		return maTuongTac;
	}
	public void setMaTuongTac(String maTuongTac) {
		this.maTuongTac = maTuongTac;
	}
	public String getMaLoaiTuongTac() {
		return maLoaiTuongTac;
	}
	public void setMaLoaiTuongTac(String maLoaiTuongTac) {
		this.maLoaiTuongTac = maLoaiTuongTac;
	}
	public String getMaVeTau() {
		return maVeTau;
	}
	public void setMaVeTau(String maVeTau) {
		this.maVeTau = maVeTau;
	}
	public double getGiaTriChenhLech() {
		return giaTriChenhLech;
	}
	public void setGiaTriChenhLech(double giaTriChenhLech) {
		this.giaTriChenhLech = giaTriChenhLech;
	}
	public LocalDate getNgayTuongTac() {
		return ngayTuongTac;
	}
	public void setNgayTuongTac(LocalDate ngayTuongTac) {
		this.ngayTuongTac = ngayTuongTac;
	}
	public LichSuTuongTacVe() {}
	public LichSuTuongTacVe(String maTuongTac, String maLoaiTuongTac, String maVeTau, double giaTriChenhLech,
			LocalDate ngayTuongTac) {
		super();
		setMaTuongTac(maTuongTac);
		setMaLoaiTuongTac(maLoaiTuongTac);
		setMaVeTau(maVeTau);
		setGiaTriChenhLech(giaTriChenhLech);
		setNgayTuongTac(ngayTuongTac);
	}
	
	
}
