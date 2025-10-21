package fourcore.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.Tau;
import fourcore.Entity.Ve;

public class Ve_Dao {
	public ArrayList<Ve> listVeTau = new ArrayList<Ve>();

	public Ve_Dao() {

	}

	// ======= LOẠI TOA TÀU =======
	public static final LoaiToaTau TOA_GHE_XOAY = new LoaiToaTau("GN01", "Ghế xoay lên tầng vũ trụ", 0.0);
	public static final LoaiToaTau TOA_GIUONG_NAM = new LoaiToaTau("GN02", "Giường nằm phê pha", 0.0);

	// ======= LOẠI TÀU =======
	public static final LoaiTau LOAI_TAU_1 = new LoaiTau("LT01", "TauHoaBacNam", 12000);

	// ======= TÀU =======
	public static final Tau TAU_1 = new Tau("SE6", "TauHi", LOAI_TAU_1);
	public static final Tau TAU_2 = new Tau("SE2", "TauHi", LOAI_TAU_1);
	public static final Tau TAU_3 = new Tau("SE3", "TauHi", LOAI_TAU_1);

	// ======= CHUYẾN TÀU =======
	public static final ChuyenTau CHUYEN_1 = new ChuyenTau(10000, LocalDateTime.of(2025, 10, 3, 15, 30),
			LocalDateTime.of(2025, 10, 14, 3, 30), TAU_1, "CT001");

	public static final ChuyenTau CHUYEN_2 = new ChuyenTau(10000, LocalDateTime.of(2025, 10, 3, 20, 30),
			LocalDateTime.of(2025, 7, 14, 12, 30), TAU_2, "CT002");

	public static final ChuyenTau CHUYEN_3 = new ChuyenTau(10000, LocalDateTime.of(2025, 10, 1, 7, 0),
			LocalDateTime.of(2025, 9, 14, 4, 30), TAU_3, "CT003");

	// ======= KHÁCH HÀNG, KHUYẾN MÃI, ĐỐI TƯỢNG GIẢM GIÁ =======
	public static final KhachHang KH_1 = new KhachHang("KH001", "Nguyễn Văn An", "0123456789", null, null, null, null);
	public static final KhuyenMai KM_1 = new KhuyenMai("KM01", "Giảm 10%", null, null, 0.1, null, null);
	public static final DoiTuongGiamGia DT_1 = new DoiTuongGiamGia("SV", "Sinh viên", null, 0.15);

	// ======= VÉ =======
	public static final Ve VE_1 = new Ve("VE001", "Hà Nội", "Đà Nẵng", "SE1", LocalDateTime.of(2025, 10, 18, 7, 0),
			LocalDateTime.of(2025, 10, 18, 13, 30), 5, 2, 1, 15, "Giường nằm mềm", "012345678", 850000, "", "Chưa đổi",
			"Đã thanh toán", CHUYEN_1, KH_1, KM_1, DT_1);

	public static final Ve VE_2 = new Ve("VE002", "Hồ Chí Minh", "Huế", "SE3", LocalDateTime.of(2025, 10, 19, 9, 0),
			LocalDateTime.of(2025, 10, 19, 21, 0), 8, 3, 2, 20, "Ngồi mềm điều hòa", "0962051111111", 650000,
			"Không hoàn vé", "Chưa đổi", "Đã thanh toán", CHUYEN_1, KH_1, KM_1, DT_1);

	public static final Ve VE_3 = new Ve("VE003", "Đà Nẵng", "Hà Nội", "SE2", LocalDateTime.of(2025, 10, 20, 6, 30),
			LocalDateTime.of(2025, 10, 20, 18, 45), 3, 1, 1, 10, "Giường nằm cứng", "0372244556", 780000, "", "Đã đổi",
			"Đã hủy", CHUYEN_1, KH_1, KM_1, DT_1);

	public static final Ve VE_4 = new Ve("VE004", "Huế", "Sài Gòn", "TN1", LocalDateTime.of(2025, 10, 22, 8, 15),
			LocalDateTime.of(2025, 10, 22, 22, 0), 6, 2, 1, 8, "Ngồi cứng", "CC123456", 450000, "", "Chưa đổi",
			"Đã thanh toán", CHUYEN_1, KH_1, KM_1, DT_1);

	public static final Ve VE_5 = new Ve("VE005", "Nha Trang", "Đà Nẵng", "SE5", LocalDateTime.of(2025, 10, 25, 10, 0),
			LocalDateTime.of(2025, 10, 25, 15, 30), 4, 1, 2, 5, "Giường nằm mềm điều hòa", "0799999999", 900000,
			"Giảm giá 10%", "Chưa đổi", "Đã thanh toán", CHUYEN_1, KH_1, KM_1, DT_1);

	public ArrayList<Ve> themNhieuVeTau() {
		listVeTau.add(VE_1);
		listVeTau.add(VE_2);
		listVeTau.add(VE_3);
		listVeTau.add(VE_4);
		listVeTau.add(VE_5);
		return listVeTau;
	}

}
