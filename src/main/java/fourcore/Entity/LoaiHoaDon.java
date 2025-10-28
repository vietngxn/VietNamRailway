package fourcore.Entity;

public class LoaiHoaDon {
	private String maLoaiHoaDon;
	private String tenLoaiHoaDon;
	private String ghiChu;

	// ===== CONSTRUCTORS =====
	public LoaiHoaDon() {
	}

	public LoaiHoaDon(String maLoaiHoaDon) {

	}

	public LoaiHoaDon(String maLoaiHoaDon, String tenLoaiHoaDon, String ghiChu) {
		setTenLoaiHoaDon(tenLoaiHoaDon);
		setMaLoaiHoaDon(maLoaiHoaDon);
		setGhiChu(ghiChu);
	}

	// ===== GETTERS & SETTERS =====
	public String getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}

	public void setMaLoaiHoaDon(String maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}

	public String getTenLoaiHoaDon() {
		return tenLoaiHoaDon;
	}

	public void setTenLoaiHoaDon(String tenLoaiHoaDon) {
		this.tenLoaiHoaDon = tenLoaiHoaDon;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	// ===== toString() =====
	@Override
	public String toString() {
		return "LoaiHoaDon{" + "maLoaiHoaDon='" + maLoaiHoaDon + '\'' + ", tenLoaiHoaDon='" + tenLoaiHoaDon + '\''
				+ ", ghiChu='" + ghiChu + '\'' + '}';
	}
}
