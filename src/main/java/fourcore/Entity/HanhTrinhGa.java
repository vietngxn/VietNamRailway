package fourcore.Entity;

import java.time.LocalTime;

public class HanhTrinhGa {

	private String maHanhTrinhGa;
	private int thuTuDung, soNgayDiQua;
	private LocalTime gioDiKeHoach;
	private Ga ga;
	private HanhTrinh hanhTrinh;
	
	public HanhTrinhGa(String maHanhTrinhGa, int thuTuDung, LocalTime gioDiKeHoach, int soNgayDiQua) {
		setMaHanhTrinhGa(maHanhTrinhGa);
		setThuTuDung(thuTuDung);
		setGioDiKeHoach(gioDiKeHoach);
		setSoNgayDiQua(soNgayDiQua);
	}
	
	public String getMaHanhTrinhGa() {
		return maHanhTrinhGa;
	}
	public void setMaHanhTrinhGa(String maHanhTrinhGa) {
		this.maHanhTrinhGa = maHanhTrinhGa;
	}
	public int getThuTuDung() {
		return thuTuDung;
	}
	public void setThuTuDung(int thuTuDung) {
		this.thuTuDung = thuTuDung;
	}
	public int getSoNgayDiQua() {
		return soNgayDiQua;
	}
	public void setSoNgayDiQua(int soNgayDiQua) {
		this.soNgayDiQua = soNgayDiQua;
	}
	public LocalTime getGioDiKeHoach() {
		return gioDiKeHoach;
	}
	public void setGioDiKeHoach(LocalTime gioDiKeHoach) {
		this.gioDiKeHoach = gioDiKeHoach;
	}
	public Ga getGa() {
		return ga;
	}
	public void setGa(Ga ga) {
		this.ga = ga;
	}
	public HanhTrinh getHanhTrinh() {
		return hanhTrinh;
	}
	public void setHanhTrinh(HanhTrinh hanhTrinh) {
		this.hanhTrinh = hanhTrinh;
	}
	
	
}
