package fourcore.Entity;

import java.time.LocalDateTime;

public class LoaiTuongTacVe {
	private String maLoaiTuongTac;
	private String tenLoaiTuongTac;

	// Constructor mặc định
	public LoaiTuongTacVe() {
	}

	// Constructor có tham số (không dùng this)
	public LoaiTuongTacVe(String maLoaiTuongTac, String tenLoaiTuongTac) {
		setMaLoaiTuongTac(maLoaiTuongTac);
		setTenLoaiTuongTac(tenLoaiTuongTac);
	}

	// Getter và Setter
	public String getMaLoaiTuongTac() {
		return maLoaiTuongTac;
	}

	public void setMaLoaiTuongTac(String maLoaiTuongTac) {
		this.maLoaiTuongTac = maLoaiTuongTac;
	}

	public String getTenLoaiTuongTac() {
		return tenLoaiTuongTac;
	}

	public void setTenLoaiTuongTac(String tenLoaiTuongTac) {
		this.tenLoaiTuongTac = tenLoaiTuongTac;
	}

}
