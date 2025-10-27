package fourcore.Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fourcore.GiaoDien.BanVe;
import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.GiaoDienHoanTraVe;
import fourcore.GiaoDien.GiaoDienXuatHoaDonHoanTraVe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HoanTraVeControl {

	public HoanTraVeControl() {
		// TODO Auto-generated constructor stub
	}

	GiaoDienHoanTraVe giaoDien = new GiaoDienHoanTraVe();
	Map<String, Double> mapVe = new HashMap<String, Double>();
	private GiaoDienXuatHoaDonHoanTraVe gd;

	public void traVeGiaoDien(BorderPane root, Map<String, Double> list) throws IOException {
		gd = new GiaoDienXuatHoaDonHoanTraVe();
		VBox gdXuatHoaDonHoanTraVeShow = gd.getNoiDungChinhVe();
		root.setCenter(gdXuatHoaDonHoanTraVeShow);
		capNhatSoLuong(list);
		capNhatTongCong(list);
	}


	public Map<String, Double> loadDanhSachLenGiaoDienThanhToan() {
		return mapVe;
	}

	public double capNhatTongCong(Map<String, Double> listVe) {
		double tongcong = 0;
		for (Map.Entry<String, Double> entry : listVe.entrySet()) {
			String key = entry.getKey();
			Double value = entry.getValue();
			tongcong += value;
		}
		return tongcong;
	}

	public int capNhatSoLuong(Map<String, Double> listVe) {
		int tongcong = 0;
		for (Map.Entry<String, Double> entry : listVe.entrySet()) {
			tongcong++;
		}
		return tongcong;
	}

}
