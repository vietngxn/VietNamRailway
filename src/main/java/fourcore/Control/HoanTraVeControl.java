package fourcore.Control;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import fourcore.Entity.Ve;
import fourcore.GiaoDien.BanVe;
import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.GiaoDienHoanTraVe;
import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import fourcore.GiaoDien.GiaoDienXuatHoaDonHoanTraVe;
import fourcore.GiaoDien.HoaDonHoanTraVe;
import fourcore.GiaoDien.QuanLyKhachHang;
import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.VeDAO;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HoanTraVeControl {
	GiaoDienHoanTraVe gdHoan;
	GiaoDienXuatHoaDonHoanTraVe gdXuat;
	HoaDonHoanTraVe gdXuatHD;
	Node gdHoanBackUp;
	VeDAO dao;
	Map<Ve, Double> listVeThanhToan;
	private ChiTietHoaDonDAO cthd;

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
		
		gdHoan.traVeNutHoanVe().setOnMouseClicked(event -> {
			int kTraHopLe = 1;
			//ktra size cua map Ve thanh toan
			if (listVeThanhToan.size() == 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Lỗi");
				alert.setHeaderText(null);
				alert.setContentText("Bạn chưa chọn vé để hoàn trả!");
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				alert.initOwner(stage);
				alert.initModality(Modality.WINDOW_MODAL);
				alert.showAndWait();
				kTraHopLe = 0;
			}
			
			for (Map.Entry<Ve, Double> entry : listVeThanhToan.entrySet()) {
				String key = entry.getKey().getMaVeTau();
				try {
					dao = new VeDAO();
					cthd = new ChiTietHoaDonDAO();
					String loai = cthd.getLoaiHoaDonChoVeTau(key);
					Ve v = dao.getVeBangMaVe(key);
					System.out.println(v.toString());
					
					if (v.getTrangThaiVe().equalsIgnoreCase("đã hoàn trả")
							|| v.getTrangThaiVe().equalsIgnoreCase("kết thúc")
							|| v.getTrangThaiVe().equalsIgnoreCase("đã được đổi")) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Lỗi");
						alert.setHeaderText(null);
						alert.setContentText("Danh sách có vé sai điều kiện hoàn trả , vui lòng chọn lại!");
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						alert.initOwner(stage);
						alert.initModality(Modality.WINDOW_MODAL);
						alert.showAndWait();
						kTraHopLe = 0;
						break;
					} else if (loai.equalsIgnoreCase("Vé tập thể")
							&& v.getTrangThaiVe().equalsIgnoreCase("hoạt động")) {
						LocalDateTime ngayGioDi = v.getNgayGioDi();
						LocalDateTime hienTai = LocalDateTime.now();
						long soGioCachNhau = Duration.between(hienTai, ngayGioDi).toHours();
						System.out.println(soGioCachNhau);

						if (soGioCachNhau < 24) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Lỗi");
							alert.setHeaderText(null);
							alert.setContentText("Danh sách có vé " + v.getMaVeTau()
									+ " sai điều kiện hoàn trả , vui lòng chọn lại!");
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							alert.initOwner(stage);
							alert.initModality(Modality.WINDOW_MODAL);
							alert.showAndWait();
							kTraHopLe = 0;
							break;
						}
					} else if (loai.equalsIgnoreCase("Vé cá nhân")
							&& v.getTrangThaiVe().equalsIgnoreCase("hoạt động")) {
						LocalDateTime ngayGioDi = v.getNgayGioDi();
						LocalDateTime hienTai = LocalDateTime.now();
						long soGioCachNhau = Duration.between(hienTai, ngayGioDi).toHours();
						System.out.println(soGioCachNhau);
						if (soGioCachNhau < 4) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Lỗi");
							alert.setHeaderText(null);
							alert.setContentText("Danh sách có vé " + v.getMaVeTau()
									+ " sai điều kiện hoàn trả , vui lòng chọn lại!");
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							alert.initOwner(stage);
							alert.initModality(Modality.WINDOW_MODAL);
							alert.showAndWait();
							kTraHopLe = 0;
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (kTraHopLe == 1) {
				try {
					VBox gdMain = gdXuat.getNoiDungChinhVe();
					root.setCenter(gdMain);
					loadDuLieuThanhToan(listVeThanhToan, gdXuat.getlblTongCongValue(), gdXuat.getlblSoLuongValue());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		gdXuat.traVeNutTroVe().setOnMouseClicked(event -> {
			root.setCenter(gdHoanBackUp);
			System.out.println("Thanh toán thành công, trả về trang hoàn vé");
		});
		
		gdXuatHD.traVeBtnXuatHoaDon().setOnMouseClicked(event -> {
			System.out.println("Thanh toán thành công, trả về trang hoàn vé");
			root.setCenter(gdHoanBackUp);
		});

	}

	public void loadDuLieuThanhToan(Map<Ve, Double> listVe, Label lblTongCong, Label lblSoLuong) {
		double tongcong = 0;
		int cnt = 0;
		for (Map.Entry<Ve, Double> entry : listVe.entrySet()) {
			Double value = entry.getValue();
			tongcong += value;
			cnt++;
		}
		NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
		lblTongCong.setText(nf.format(tongcong));
		lblSoLuong.setText(String.valueOf(cnt));
	}

}