package fourcore.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LichSuTuongTacVe {

	String maTuongTac;
	private Ve veTau;
	LoaiTuongTacVe loaiTuongTacVe;
	double giaTriChenhLech;
	LocalDateTime ngayTuongTac;

	public LoaiTuongTacVe getLoaiTuongTacVe() {
		return loaiTuongTacVe;
	}

	public void setLoaiTuongTacVe(LoaiTuongTacVe loaiTuongTacVe) {
		this.loaiTuongTacVe = loaiTuongTacVe;
	}

	public Ve getVeTau() {
		return veTau;
	}

	public void setVeTau(Ve veTau) {
		this.veTau = veTau;
	}

	public String getMaTuongTac() {
		return maTuongTac;
	}

	public void setMaTuongTac(String maTuongTac) {
		this.maTuongTac = maTuongTac;
	}

	public double getGiaTriChenhLech() {
		return giaTriChenhLech;
	}

	public void setGiaTriChenhLech(double giaTriChenhLech) {
		this.giaTriChenhLech = giaTriChenhLech;
	}

	public LocalDateTime getNgayTuongTac() {
		return ngayTuongTac;
	}

	public void setNgayTuongTac(LocalDateTime ngayTuongTac) {
		this.ngayTuongTac = ngayTuongTac;
	}

	public LichSuTuongTacVe() {
	}

	public LichSuTuongTacVe(String maTuongTac, LoaiTuongTacVe loaiTuongTacVe, Ve veTau, double giaTriChenhLech,
			LocalDateTime ngayTuongTac) {
		setMaTuongTac(maTuongTac);
		setLoaiTuongTacVe(loaiTuongTacVe);
		setVeTau(veTau);
		setGiaTriChenhLech(giaTriChenhLech);
		setNgayTuongTac(ngayTuongTac);
	}
	
	public LichSuTuongTacVe(LoaiTuongTacVe loaiTuongTacVe, Ve veTau, double giaTriChenhLech,
			LocalDateTime ngayTuongTac) {
		setLoaiTuongTacVe(loaiTuongTacVe);
		setVeTau(veTau);
		setGiaTriChenhLech(giaTriChenhLech);
		setNgayTuongTac(ngayTuongTac);
	}

	public double tinhTongTien(String loai) {
		if (loai.equalsIgnoreCase("LTT01")) {
			return this.veTau.getGiaVe();
		} else if (loai.equalsIgnoreCase("LTT03")) {
			return this.veTau.getGiaVe() - this.getGiaTriChenhLech();
		} else if (loai.equalsIgnoreCase("LTT02")) {
			return this.veTau.getGiaVe() + this.getGiaTriChenhLech();
		}
		return 0.0;

	}

}
