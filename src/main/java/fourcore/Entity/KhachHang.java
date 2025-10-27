package fourcore.Entity;

import java.io.Serializable;

public class KhachHang implements Serializable {
	private String maKhachHang;
	private String hoTen;
	private String sdt;
	private String email;
	private String cccd;
	private String passport;
	private String doiTuong;

	public KhachHang(String hoTen, String maGiayTo, String doiTuong) {
		setHoten(hoTen);
		setCccd(maGiayTo);
		setDoiTuong(doiTuong);
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoten() {
		return hoTen;
	}

	public void setHoten(String hoten) {
		this.hoTen = hoten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(String doiTuong) {
		this.doiTuong = doiTuong;
	}

	public KhachHang() {
	}

	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public KhachHang(String maKhachHang, String hoten, String sdt, String email, String cccd, String passport,
			String doiTuong) {

		setMaKhachHang(maKhachHang);
		setHoten(hoten);
		setSdt(sdt);
		setEmail(email);
		setCccd(cccd);
		setPassport(passport);
		setDoiTuong(doiTuong);
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoten=" + hoTen + ", sdt=" + sdt + ", email=" + email
				+ ", cccd=" + cccd + ", passport=" + passport + ", DoiTuong=" + doiTuong + "]";
	}

	public String toString2() {
		return hoTen + " " + cccd + " " + doiTuong;
	}

}