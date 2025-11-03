package fourcore.Entity;

import java.security.KeyStore.Entry;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.GheTrenChuyenTau_dao;
import fourcore.dao.HoaDonDAO;
import fourcore.dao.LichSuTuongTacVe_Dao;
import fourcore.dao.VeDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class XuatHoaDonHoanTraVe extends Application {

	private BorderPane root;
	private VBox table_layout;
	private int cnt = 1;
	private Button btn_xuatHoaDon;
	private Button btn_thoat;
	private VBox table_desc;

	Map<Ve, Double> listVe = new HashMap();
	String hoTen;
	String sdt;
	String cccd;
	String email;
	String diaChi;
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	Double tongCongThanhTien = 0.0;

	private VeDAO vedao;
	private LichSuTuongTacVe_Dao lsttDao;
	private HoaDonDAO hddao;
	private ChiTietHoaDonDAO cthdDao;
	private GheTrenChuyenTau_dao gtctDao;
	private String newMa;

	@Override
	public void start(Stage primaryStage) {
		root = new BorderPane();

		create_title_layout();
		create_table_layout();
		create_footer_layout();

		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hóa đơn");
		primaryStage.show();
	}

	public XuatHoaDonHoanTraVe(String hoten, String diaChi, String sdt, String cccd, String email,
			Map<Ve, Double> listVe) {
		this.hoTen = hoten;
		this.sdt = sdt;
		this.email = email;
		this.cccd = cccd;
		this.diaChi = diaChi;
		this.listVe = listVe;
	}

	// --------------------- PHẦN TIÊU ĐỀ ---------------------
	private void create_title_layout() {
		VBox title_layout = new VBox(10);
		title_layout.setPadding(new Insets(20));
		title_layout.setAlignment(Pos.CENTER_LEFT);

		Label lbl_tieuDe = new Label("Hóa đơn");
		lbl_tieuDe.setStyle("-fx-font-family : 'Inter';-fx-font-size: 30px; -fx-font-weight: bold;");
		StackPane pane_tieuDe = new StackPane(lbl_tieuDe);

		pane_tieuDe.setAlignment(Pos.CENTER);
		String style = "-fx-font-family:'Inter';-fx-font-size : 20px;-fx-font-weight:bold;";

		Label lbl_maHoaDon = new Label("Mã hóa đơn:");
		lbl_maHoaDon.setStyle(style);

		Label lbl_donViBan = new Label("Đơn vị bán: Tổng công ty đường sắt Việt Nam");
		lbl_donViBan.setStyle(style);

		Label lbl_nguoiMua = new Label("Người mua: " + hoTen);
		lbl_nguoiMua.setStyle(style);

		Label lbl_diaChi = new Label("Địa chỉ: " + diaChi);
		lbl_diaChi.setStyle(style);

		Label lbl_sdt = new Label("Điện thoại: " + sdt);
		lbl_sdt.setStyle(style);

		VBox info_layout = new VBox(5, lbl_maHoaDon, lbl_donViBan, lbl_nguoiMua, lbl_diaChi, lbl_sdt);
		info_layout.setPadding(new Insets(10, 0, 0, 50));

		title_layout.getChildren().addAll(pane_tieuDe, info_layout);
		root.setTop(title_layout);
	}

	private void create_table_layout() {
		table_layout = new VBox(10);
		table_layout.setPadding(new Insets(20, 50, 20, 50));

		GridPane table_header = new GridPane();
		table_header.setHgap(15);
		table_header.setVgap(10);
		table_header.setAlignment(Pos.CENTER);
		String style = "-fx-font-family:'Inter';-fx-font-size : 14px;-fx-font-weight:bold;";
		String[] headers = { "STT", "Mã vé", "Tên dịch vụ", "Đơn giá", "VAT", "Khuyến mãi", "Phí hoàn trả",
				"Thành tiền" };
		for (int i = 0; i < headers.length; i++) {
			Label lbl_header = new Label(headers[i]);
			lbl_header.setStyle("-fx-font-weight: bold;");
			lbl_header.setPrefWidth(100);
			lbl_header.setAlignment(Pos.CENTER);
			lbl_header.setStyle(style);
			table_header.add(lbl_header, i, 0);
		}

		table_layout.getChildren().add(table_header);

		table_desc = new VBox();
		table_desc.setSpacing(10); // <-- đây là setSpacing đúng chỗ
		table_desc.setPadding(new Insets(10, 0, 10, 0));

		for (Map.Entry<Ve, Double> entry : listVe.entrySet()) {
			Ve v = entry.getKey();
			double thanhtien = entry.getValue();
			create_table_row(cnt, v.getMaVeTau(), "Hoàn trả vé", v.getGiaVe(), 0.0, 0.0, v.getGiaVe() - thanhtien,
					thanhtien);
			tongCongThanhTien += thanhtien;
		}

		ScrollPane scrPane = new ScrollPane(table_desc);

		scrPane.setPrefViewportHeight(400);
		scrPane.setFitToWidth(true);
		scrPane.setPannable(true);
		scrPane.setStyle("""
				    -fx-background-color: transparent;
				    -fx-border-color: transparent;
				    -fx-border-width: 0;
				""");

		scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		table_layout.getChildren().add(scrPane);

		root.setCenter(table_layout);
	}

	private void create_table_row(int stt, String maVe, String tenDichVu, double donGia, double vat, double khuyenMai,
			double phihoan, double thanhTien) {
		GridPane layout_dong = new GridPane();
		layout_dong.setHgap(15);
		layout_dong.setVgap(5);
		layout_dong.setAlignment(Pos.CENTER);
		layout_dong.setPrefHeight(70);
		String style = "-fx-font-family:'Inter';-fx-font-size : 12px;-fx-font-weight:bold;";
		layout_dong.setStyle("-fx-background-color :  #00BACB;-fx-background-radius:15px;-fx-border-radius:15px;");

		String[] values = { String.valueOf(cnt), maVe, tenDichVu, nf.format(donGia), nf.format(vat),
				nf.format(khuyenMai), nf.format(phihoan), nf.format(thanhTien) };

		for (int i = 0; i < values.length; i++) {
			Label lbl_cell = new Label(values[i]);
			lbl_cell.setPrefWidth(100);
			lbl_cell.setAlignment(Pos.CENTER);
			lbl_cell.setStyle(style);
			layout_dong.add(lbl_cell, i, 0);
			if (i == 2) {
				lbl_cell.setWrapText(true);
			}
		}
		cnt++;
		table_desc.getChildren().add(layout_dong);
	}

	private void create_footer_layout() {
		VBox footer_layout = new VBox(10);
		footer_layout.setPadding(new Insets(10, 50, 20, 50));

		HBox tongCong_layout = new HBox();
		tongCong_layout.setAlignment(Pos.CENTER_RIGHT);
		tongCong_layout.setSpacing(20);
		String style = "-fx-font-family:'Inter';-fx-font-size : 20px;-fx-font-weight:bold;";
		Label lbl_tongCong = new Label("Tổng cộng:");
		lbl_tongCong.setStyle(style);

		Label lbl_tien = new Label(nf.format(tongCongThanhTien) + "Đ");
		lbl_tien.setStyle(style);

		tongCong_layout.getChildren().addAll(lbl_tongCong, lbl_tien);

//		Label lbl_tienBangChu = new Label("Số tiền viết bằng chữ: Một triệu hai trăm nghìn đồng");
//		lbl_tienBangChu.setStyle(style);
//		lbl_tienBangChu.setTranslateX(400);

		HBox button_layout = new HBox(20);
		button_layout.setAlignment(Pos.CENTER_RIGHT);
		btn_xuatHoaDon = new Button("Xuất hóa đơn");
		btn_xuatHoaDon.setPrefSize(150, 50);
		btn_thoat = new Button("Thoát");
		btn_thoat.setPrefSize(150, 50);

		btn_xuatHoaDon.setStyle(
				"-fx-background-color : #00BACB;-fx-background-radius:15px;-fx-border-radius:15px;-fx-font-family: 'Inter';-fx-text-fill :white;-fx-font-size : 15px;-fx-font-weight:bold;");
		btn_thoat.setStyle(
				"-fx-background-color: white; -fx-border-color: #00BACB;-fx-background-radius:15px;-fx-border-radius: 15px; -fx-font-weight: bold;-fx-font-family:'Inter';-fx-font-weight:bold;-fx-text-fill:#00BACB;");

		btn_xuatHoaDon.setOnMouseClicked(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Xác nhận");
			alert.setHeaderText("Bạn có muốn in hóa đơn hoàn trả vé này?");
			alert.setContentText("Hãy chọn OK để xác nhận hoặc Cancel để hủy.");

			ButtonType buttonYes = new ButtonType("Có");
			ButtonType buttonNo = new ButtonType("Không");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);
			// 🔹 Quan trọng: đặt stage cha cho alert
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			alert.initOwner(currentStage);

			// 🔹 Đảm bảo nó nằm trên cùng và chặn tương tác với stage cha
			alert.initModality(Modality.WINDOW_MODAL);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == buttonYes) {
				HoaDon hd = new HoaDon("NV01", hoTen, email, cccd, sdt, diaChi, tongCongThanhTien);
				System.out.println("Số lượng vé trong listVeThanhToan: " + listVe.size());
				try {
					this.vedao = new VeDAO(0);
					this.lsttDao = new LichSuTuongTacVe_Dao();
					this.hddao = new HoaDonDAO();
					this.cthdDao = new ChiTietHoaDonDAO();
					this.gtctDao = new GheTrenChuyenTau_dao();
					for (Map.Entry<Ve, Double> entry : listVe.entrySet()) {
						Ve key = entry.getKey();
						System.out.println(key);
						double value = entry.getValue();
						System.out.print(value);
						try {
							System.out.println(vedao.ThayDoiTrangThaiVe(key.getMaVeTau(), "đã hoàn trả"));
							int soLuongLichSu = lsttDao.getSoLuongLichSuTuongTacVe() + 1;
							System.out.println(soLuongLichSu);
							if (soLuongLichSu < 10) {
								newMa = "LSTT0" + soLuongLichSu;
							} else {
								newMa = "LSTT" + soLuongLichSu;
							}
							lsttDao.themLichSuTuongTacVe(new LichSuTuongTacVe(newMa, new LoaiTuongTacVe("LTT03"), key,
									key.getGiaVe() - value, LocalDateTime.now()));
							gtctDao.thayDoiTrangThaiGheConTrong(key.getMaVeTau(), key.getSoGhe());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					hddao.themHoaDonHoanTraVe(hd);
					for (Map.Entry<Ve, Double> entry : listVe.entrySet()) {
						Ve key = entry.getKey();
						double value = entry.getValue();
						cthdDao = new ChiTietHoaDonDAO();
						String loai = cthdDao.getLoaiHoaDonChoVeTau(key.getMaVeTau());
						ChiTietHoaDon cthd = new ChiTietHoaDon(hd, key, "Chi Tiết hóa đơn hoàn trả vé",
								key.getGiaVe() - value, 0, value, loai);
						cthdDao.themChiTietHoaDon(cthd);
					}
					showConfirm(currentStage, "Thông báo", "Bạn đã thanh toán hóa đơn thành công");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			currentStage.close();
		});
		button_layout.getChildren().addAll(btn_xuatHoaDon, btn_thoat);

		footer_layout.getChildren().addAll(tongCong_layout, button_layout);
		root.setBottom(footer_layout);

	}

	public static boolean showConfirm(Stage ownerStage, String title, String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		ButtonType buttonYes = new ButtonType("Có");
		ButtonType buttonNo = new ButtonType("Không");
		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		if (ownerStage != null) {
			alert.initOwner(ownerStage);
		}
		alert.initModality(Modality.WINDOW_MODAL);

		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == buttonYes;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
