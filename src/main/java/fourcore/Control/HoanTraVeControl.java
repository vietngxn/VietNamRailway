package fourcore.Control;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import fourcore.GiaoDien.BanVe;
import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.GiaoDienHoanTraVe;
import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import fourcore.GiaoDien.GiaoDienXuatHoaDonHoanTraVe;
import fourcore.GiaoDien.QuanLyKhachHang;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HoanTraVeControl {
	GiaoDienHoanTraVe gdHoan;
	GiaoDienXuatHoaDonHoanTraVe gdXuat;
	Node gdHoanBackUp;
	Map<String, Double> listVeThanhToan;

	public HoanTraVeControl() {
		// TODO Auto-generated constructor stub
	}

	public void handleMenuTrangChuSelect(BorderPane root) {
		gdHoan = new GiaoDienHoanTraVe();
		Stage gdStage = new Stage();
		gdHoan.start(gdStage);
		VBox noiDungChinhVBox = gdHoan.getNoiDungChinhVe();
		gdHoanBackUp = noiDungChinhVBox;
		root.setCenter(noiDungChinhVBox);
	}

	public void traVeGiaoDienXuatHoaDon(BorderPane root) throws Exception {
		handleMenuTrangChuSelect(root);
		listVeThanhToan = gdHoan.traVeListVeThanhToan();
		gdXuat = new GiaoDienXuatHoaDonHoanTraVe(listVeThanhToan);
		gdHoan.traVeNutHoanVe().setOnMouseClicked(event_ -> {
			try {
				Stage gdStage = new Stage();
				gdXuat.start(gdStage);
				VBox gdMain = gdXuat.getNoiDungChinhVe();
				root.setCenter(gdMain);
				loadDuLieuThanhToan(listVeThanhToan, gdXuat.getlblTongCongValue(), gdXuat.getlblSoLuongValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		gdXuat.traVeNutTroVe().setOnMouseClicked(event -> {
			root.setCenter(gdHoanBackUp);
		});
	}

	public void loadDuLieuThanhToan(Map<String, Double> listVe, Label lblTongCong, Label lblSoLuong) {
		double tongcong = 0;
		int cnt = 0;
		for (Map.Entry<String, Double> entry : listVe.entrySet()) {
			String key = entry.getKey();
			Double value = entry.getValue();
			tongcong += value;
			cnt++;
		}
		NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
		lblTongCong.setText(nf.format(tongcong));
		lblSoLuong.setText(String.valueOf(cnt));
	}

}
