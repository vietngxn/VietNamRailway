package fourcore.Entity;

import java.time.LocalDate;

public class DoiTuongGiamGia {
	String maDoiTuongGiamGia, tenDoiTuongGiamGia, trangThaiGiamGia;
	double giaTriPhanTramGiamGia;
	public String getMaDoiTuongGiamGia() {
		return maDoiTuongGiamGia;
	}
	public void setMaDoiTuongGiamGia(String maDoiTuongGiamGia) {
		this.maDoiTuongGiamGia = maDoiTuongGiamGia;
	}
	public String getTenDoiTuongGiamGia() {
		return tenDoiTuongGiamGia;
	}
	public void setTenDoiTuongGiamGia(String tenDoiTuongGiamGia) {
		this.tenDoiTuongGiamGia = tenDoiTuongGiamGia;
	}
	public String getTrangThaiGiamGia() {
		return trangThaiGiamGia;
	}
	public void setTrangThaiGiamGia(String trangThaiGiamGia) {
		this.trangThaiGiamGia = trangThaiGiamGia;
	}
	public double getGiaTriPhanTramGiamGia() {
		return giaTriPhanTramGiamGia;
	}
	public void setGiaTriPhanTramGiamGia(double giaTriPhanTramGiamGia) {
		this.giaTriPhanTramGiamGia = giaTriPhanTramGiamGia;
	}

	public DoiTuongGiamGia() {}
	public DoiTuongGiamGia(String maDoiTuongGiamGia, String tenDoiTuongGiamGia, String trangThaiGiamGia,
			double giaTriPhanTramGiamGia) {
		setMaDoiTuongGiamGia(maDoiTuongGiamGia);
		setTenDoiTuongGiamGia(tenDoiTuongGiamGia);
		setTrangThaiGiamGia(trangThaiGiamGia);
		setGiaTriPhanTramGiamGia(giaTriPhanTramGiamGia);
	}

	
	
}
