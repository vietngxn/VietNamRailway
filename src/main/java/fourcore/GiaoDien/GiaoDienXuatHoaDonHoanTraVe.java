package fourcore.GiaoDien;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import fourcore.Control.HoanTraVeControl;
import fourcore.Entity.ThongTinCtHoaDon;
import fourcore.Entity.Ve;
import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.GheTrenChuyenTau_dao;
import fourcore.dao.HoaDonDAO;
import fourcore.dao.LichSuTuongTacVe_Dao;
import fourcore.dao.VeDAO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GiaoDienXuatHoaDonHoanTraVe extends Application {

	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
	Class<?> clazz = this.getClass();
	private Pane pnlDoiVelbl;
	private Label lblDoiVe;
	private GridPane pnlThongTinNguoiMua;
	private Pane pnlXuatHDlbl;
	private Label lblXuatHD;
	private Pane btnRong;
	private HBox pnlTongCong;
	private Label lblTongCong;
	Label lblTongCongValue = new Label();;
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	private VBox pnlThanhToanButton;
	private HBox pnlThanhToanButtonSub1;
	private HBox pnlThanhToanButtonSub2;
	private Button btnTroLai = new Button("Trở lại");;
	private VBox pnlThongTinXuatHoaDonCaNhan;
	private Button btnThanhToan;
	NumberFormat nft = NumberFormat.getInstance(new Locale("vi", "VN"));

	String hoTen;
	String cccd;
	String email;
	String sdt;
	private TextField lblRight;
	private Label lblSoLuongVe;
	Label lblSoLuongVeValue = new Label();
	private HBox pnlSoLuongVe;

	private Scene scene;
	private BorderPane root;
	private HoanTraVeControl ctrl = new HoanTraVeControl();

	Map<Ve, Double> listVeThanhToan;
	Map<Ve, Double> listVeThanhToanTabel;

	ArrayList<TextField> txtList = new ArrayList<TextField>();
	private GiaoDienHoanTraVe gdHoan;
	private HoaDonHoanTraVe gd;

	private Runnable onThanhToanThanhCong;
	private VBox tableLayout = new VBox();
	private GridPane tableCol;
	private VBox tableDesc;
	private int cnt;

	public void setOnThanhToanThanhCong(Runnable r) {
		this.onThanhToanThanhCong = r;
	}

	public GiaoDienXuatHoaDonHoanTraVe(Map<Ve, Double> list) {
		listVeThanhToan = list;
	}

	public Label getlblTongCongValue() {
		return this.lblTongCongValue;
	}

	public Label getlblSoLuongValue() {
		return this.lblSoLuongVeValue;
	}

	public void start(Stage primaryStage, Map<Ve, Double> list) throws IOException {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hieuUngHover(Button btn) {
		btn.setOnMouseEntered(e -> {
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), btn);
			scaleUp.setToX(1.1);
			scaleUp.setToY(1.1);
			scaleUp.play();
		});

		btn.setOnMouseExited(e -> {
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), btn);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});
	}

	public VBox taoXuatHoaDonCaNhanPane(String leftstyle, String rightstyle) {
		VBox pnl = new VBox(15);
		pnl.setAlignment(Pos.CENTER);
		pnl.getChildren().add(taoSubXuatHoaDonPane("Họ tên người mua", leftstyle, rightstyle));
		pnl.getChildren().add(taoSubXuatHoaDonPane("Số điện thoại", leftstyle, rightstyle));
		pnl.getChildren().add(taoSubXuatHoaDonPane("Email", leftstyle, rightstyle));
		pnl.getChildren().add(taoSubXuatHoaDonPane("Số giấy tờ (cccd)", leftstyle, rightstyle));
		pnl.getChildren().add(taoSubXuatHoaDonPane("Địa chỉ", leftstyle, rightstyle));
		return pnl;

	}

	public HBox taoSubXuatHoaDonPane(String label, String leftStyle, String rightStyle) {
		StackPane left = new StackPane(new Label(label));
		StackPane right = new StackPane();
		right.setPrefSize(200, 40);
		TextField txtRight = new TextField();
		txtList.add(txtRight);
		txtRight.setStyle(rightStyle + " -fx-border-color: transparent;");
		txtRight.setMaxWidth(Double.MAX_VALUE);
		txtRight.setMaxHeight(Double.MAX_VALUE);
		StackPane.setAlignment(txtRight, Pos.CENTER);
		right.getChildren().add(txtRight);
		left.setPrefSize(200, 50);
		left.setAlignment(Pos.CENTER);
		right.setPrefSize(1000, 50);
		left.setStyle(leftStyle);
		right.setStyle(rightStyle);
		return new HBox(left, right);
	}
//
//	public void updateListVeThanhToan(Map<Ve, Double> newMap) {
//		if (this.listVeThanhToan == null) {
//			this.listVeThanhToan = new HashMap<>();
//		}
//
//		this.listVeThanhToan.clear();
//		this.listVeThanhToan.putAll(newMap);
//
//		refreshTable(); // ✅ OK
//	}

	public void refreshTable(Map<Ve, Double> data) {

		if (tableDesc == null)
			return;

		tableDesc.getChildren().clear();

		if (data == null || data.isEmpty())
			return;

		int cnt = 1;
		for (Map.Entry<Ve, Double> entry : data.entrySet()) {
			Ve v = entry.getKey();

			GridPane row = new GridPane();
			row.setHgap(15);
			row.setAlignment(Pos.CENTER);
			row.setPrefHeight(60);
			row.setStyle("""
					    -fx-background-color:#00BACB;
					    -fx-background-radius:15px;
					""");

			String[] values = { String.valueOf(cnt++), v.getMaVeTau(), v.getGaDi(), v.getGaDen(), v.getLoaiVe(),
					nft.format(v.getGiaVe()), "-" + nft.format(v.getGiaVe() - entry.getValue()),
					nft.format(entry.getValue()) };

			for (int i = 0; i < values.length; i++) {
				Label lbl = new Label(values[i]);
				lbl.setStyle("-fx-font-weight:bold;");
				lbl.setPrefWidth(100);
				lbl.setAlignment(Pos.CENTER);
				lbl.setWrapText(i == 2 || i == 3);
				row.add(lbl, i, 0);
			}

			tableDesc.getChildren().add(row);
		}
	}

	private void initTable() {
		tableLayout = new VBox(10);
		tableLayout.setPadding(new Insets(0, 50, 20, 0));
		tableLayout.setMaxWidth(800);
		tableLayout.setAlignment(Pos.CENTER);
		tableLayout.setTranslateX(300);
		tableLayout.setTranslateY(-10);

		tableCol = new GridPane();
		tableCol.setHgap(15);
		tableCol.setVgap(10);
		tableCol.setAlignment(Pos.CENTER);

		String style = "-fx-font-family:'Inter';-fx-font-size:14px;-fx-font-weight:bold;";
		String[] headers = { "STT", "Mã vé", "Ga đi", "Ga đến", "Loại ghế", "Giá vé", "Phí hoàn trả", "Thành tiền" };

		for (int i = 0; i < headers.length; i++) {
			Label lbl = new Label(headers[i]);
			lbl.setPrefWidth(100);
			lbl.setAlignment(Pos.CENTER);
			lbl.setStyle(style);
			tableCol.add(lbl, i, 0);
		}

		tableDesc = new VBox(10);
		tableDesc.setPadding(new Insets(6, 0, 6, 0));

		ScrollPane scrPane = new ScrollPane(tableDesc);
		scrPane.setFitToWidth(true);
		scrPane.setPannable(true);
		scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrPane.setStyle("""
				    -fx-background-color: transparent;
				    -fx-border-color: transparent;
				""");
		tableLayout.getChildren().addAll(tableCol, scrPane);
	}

	public VBox getNoiDungChinhVe() {
		if (this.noiDungChinh == null) {
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1300);

			pnlDoiVelbl = new Pane();
			lblDoiVe = new Label("Hoàn trả vé");
			pnlDoiVelbl.getChildren().add(lblDoiVe);
			lblDoiVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlDoiVelbl);
			VBox.setMargin(pnlDoiVelbl, new Insets(20, 0, 0, 50));
			pnlThongTinNguoiMua = new GridPane();
			VBox.setMargin(pnlThongTinNguoiMua, new Insets(30, 0, 0, 0));
			pnlThongTinNguoiMua.setAlignment(Pos.CENTER);
			pnlThongTinNguoiMua.setHgap(400);
			pnlThongTinNguoiMua.setVgap(10);

			String leftStyle = """
					    -fx-background-color: #00BACB;
					    -fx-background-radius: 10px 0 0 10px;
					    -fx-border-radius: 10px 0 0 10px;
					    -fx-border-color: black;
					    -fx-alignment: center-left;
					    -fx-font-weight: bold;
					    -fx-font-family: "Kanit";
					    -fx-padding: 8 12 8 12;
					""";

			String rightStyle = """
					    -fx-background-color: #E0E0E0;
					    -fx-background-radius: 0 10px 10px 0;
					    -fx-border-radius: 0 10px 10px 0;
					   -fx-border-color: black;
					    -fx-alignment: center-left;
					    -fx-font-weight: bold;
					    -fx-font-family: "Kanit";
					    -fx-padding: 8 12 8 12;
					""";

			pnlXuatHDlbl = new Pane();
			lblXuatHD = new Label("Xuất hóa đơn hoàn trả vé");
			pnlXuatHDlbl.getChildren().add(lblXuatHD);
			lblXuatHD.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlXuatHDlbl);
			VBox.setMargin(pnlXuatHDlbl, new Insets(20, 0, 0, 50));

			pnlThongTinXuatHoaDonCaNhan = taoXuatHoaDonCaNhanPane(leftStyle, rightStyle);
			noiDungChinh.getChildren().add(pnlThongTinXuatHoaDonCaNhan);
			VBox.setMargin(pnlThongTinXuatHoaDonCaNhan, new Insets(20, 0, 100, 150));

			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));

			initTable();
			noiDungChinh.getChildren().add(tableLayout);

			pnlThanhToanButton = new VBox();
			pnlThanhToanButtonSub1 = new HBox();
			pnlThanhToanButtonSub2 = new HBox();

			String btnRedStyle = "-fx-font-family: 'Inter';" + "-fx-font-size: 20px;" + "-fx-font-weight: bold;"
					+ "-fx-text-fill:white;"
					+ "-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);"
					+ "-fx-background-radius:15px;";

			String btnBlueStyle = "-fx-font-family: 'Inter';" + "-fx-font-size: 20px;" + "-fx-font-weight: bold;"
					+ "-fx-text-fill:white;" + "-fx-background-color: linear-gradient(to top, #00BACB, #B6D0D3);"
					+ "-fx-background-radius: 15px;";
			String lblStyle = "-fx-font-size: 24px;";

			btnRong = new Pane();
			pnlTongCong = new HBox();
			pnlSoLuongVe = new HBox(5);
			lblSoLuongVe = new Label("Số lượng vé hoàn trả:");

			lblSoLuongVe.setStyle(lblStyle);
			lblSoLuongVeValue.setStyle(lblStyle + "-fx-font-weight: bold;");
			pnlSoLuongVe.getChildren().addAll(lblSoLuongVe, lblSoLuongVeValue);

			lblTongCong = new Label("Tổng cộng tiền phải hoàn trả: ");
			lblTongCong.setWrapText(true);
			lblTongCong.setStyle(lblStyle);

			lblTongCongValue.setWrapText(true);
			lblTongCongValue.setStyle(lblStyle + "-fx-font-weight: bold;");

			HBox.setMargin(lblTongCong, new Insets(0, 20, 0, 0));
			pnlTongCong.setAlignment(Pos.CENTER);

			HBox.setMargin(pnlTongCong, new Insets(0, 0, 0, 150));
			pnlTongCong.getChildren().addAll(pnlSoLuongVe, lblTongCong, lblTongCongValue);
			HBox.setMargin(lblTongCong, new Insets(0, 0, 0, 100));
			HBox.setMargin(pnlSoLuongVe, new Insets(0, 300, 0, 0));
			pnlThanhToanButtonSub1.getChildren().addAll(btnRong, pnlTongCong);

			btnTroLai.setStyle(btnRedStyle);
			btnTroLai.setPrefSize(270, 50);
			btnThanhToan = new Button("Thanh toán");
			btnThanhToan.setStyle(btnBlueStyle);
			btnThanhToan.setPrefSize(280, 50);

			pnlThanhToanButtonSub2.getChildren().addAll(btnTroLai, btnThanhToan);
			HBox.setMargin(btnTroLai, new Insets(0, 750, 0, 0));
			pnlThanhToanButton.getChildren().addAll(pnlThanhToanButtonSub1, pnlThanhToanButtonSub2);
			VBox.setMargin(pnlThanhToanButtonSub1, new Insets(20, 0, 0, 0));
			VBox.setMargin(pnlThanhToanButtonSub2, new Insets(50, 0, 0, 0));
			noiDungChinh.getChildren().addAll(pnlThanhToanButton);

			VBox.setMargin(pnlThanhToanButton, new Insets(0, 0, 0, 20));
			hieuUngHover(btnThanhToan);
			hieuUngHover(btnTroLai);

			btnThanhToan.setOnMouseClicked(event -> {

				String txtHoTenValue = txtList.get(0).getText().trim();
				String txtSDTValue = txtList.get(1).getText().trim();
				String txtEmailValue = txtList.get(2).getText().trim();
				String txtSoGiayToValue = txtList.get(3).getText().trim();
				String txtDiaChiValue = txtList.get(4).getText().trim();

				if (txtHoTenValue.isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Họ tên không được bỏ trống!");
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(currentStage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					txtList.get(0).requestFocus();
					return;
				} else if (txtSDTValue.isEmpty() || !txtSDTValue.matches("^0[2389]\\d{8}$")) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Số điện thoại không được bỏ trống và phải có đúng định dạng!");
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(currentStage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					txtList.get(1).requestFocus();
					return;
				} else if (txtSoGiayToValue.isEmpty() || !txtSoGiayToValue.matches("^[0-9]{12}$")) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Số giấy tờ không được bỏ trống và phải có đúng định dạng!");
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(currentStage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					txtList.get(3).requestFocus();
					return;
				} else if (txtDiaChiValue.isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Địa chỉ không được bỏ trống!");
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(currentStage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					txtList.get(4).requestFocus();
					return;
				}

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Xác nhận");
				alert.setHeaderText("Bạn có muốn thanh toán cho hóa đơn hoàn trả vé này?");
				alert.setContentText("Hãy chọn OK để xác nhận hoặc Cancel để hủy.");
				ButtonType buttonYes = new ButtonType("Có");
				ButtonType buttonNo = new ButtonType("Không");
				alert.getButtonTypes().setAll(buttonYes, buttonNo);

				Stage stageCha = (Stage) ((Node) event.getSource()).getScene().getWindow();
				alert.initOwner(stageCha);

				// 🔹 Đảm bảo nó nằm trên cùng và chặn tương tác với stage cha
				alert.initModality(Modality.WINDOW_MODAL);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == buttonYes) {
					// Lấy stage hiện tại (đang fullscreen)
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					System.out.println(txtHoTenValue);
					System.out.println(txtSDTValue);
					// Tạo cửa sổ mới
					gd = new HoaDonHoanTraVe(txtHoTenValue, txtDiaChiValue, txtSDTValue, txtSoGiayToValue,
							txtEmailValue, listVeThanhToan);
					Stage gdStage = new Stage();
					gdStage.initOwner(currentStage);
					gdStage.initStyle(StageStyle.UTILITY);
					gdStage.setTitle("Hóa đơn hoàn trả vé");
					gdStage.setWidth(1200);
					gdStage.setHeight(1000);
					gdStage.centerOnScreen();
					gd.start(gdStage);
					gdStage.show();

					gd.setOnThanhToanThanhCong(() -> {
						onThanhToanThanhCong.run();
					});

				} else {
					System.out.println("Người dùng chọn No: hủy thanh toán");
				}

			});

			ctrl.loadDuLieuThanhToan(listVeThanhToan, lblTongCongValue, lblSoLuongVeValue);
			refreshTable(listVeThanhToan);
		}
		return noiDungChinh;
	}

	public void updateListVeThanhToan(Map<Ve, Double> newMap) {
		this.listVeThanhToan = newMap;
		refreshTable(newMap);
	}

	public HoaDonHoanTraVe traVeHoaDonHoanTraVe() {
		return this.gd;
	}

	public Button traVeNutTroVe() {
		return this.btnTroLai;
	}

	public Button traVeNutThanhToan() {
		return this.btnTroLai;
	}

	public Map<Ve, Double> traVeListVeThanhToanCuaTable() {
		return this.listVeThanhToan;
	}

	public void ganListVeThanhToanCuaTable(Map<Ve, Double> list) {
		this.listVeThanhToan = list;
	}

	public static void main(String[] args) {
		Application.launch(GiaoDienXuatHoaDonHoanTraVe.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}
}