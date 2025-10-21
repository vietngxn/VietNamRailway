package fourcore.GiaoDien;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import fourcore.Entity.LichSuTuongTacVe;
import fourcore.dao.LichSuTuongTacVe_Dao;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GiaoDienLichSuMuaBanDoiVe extends Application {

	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
	Class<?> clazz = this.getClass();
	private ScrollPane scrollPaneMenu;
	private VBox danhSachMenuItem;
	private HBox quanLiVeTauMenu;
	private Label quanLiVeTauLabel;
	private ImageView quanLiVeTauIconView;
	private ImageView showMenuPhuIconView;
	private Label lblLichSuMuaBanDoiVe;
	private Pane pnlLichSuMuaBanDoiVe;
	private VBox pnlDataDoiVe;
	private ScrollPane scrollPane;
	private GridPane tableColLichSu;
	private HBox pnlLichSuMuaBanDoiVeButton;
	private Button btnLichSuMuaVe;
	private Button btnLichSuHoanVe;
	private Button btnLichSuDoiVe;
	private String styleLichSuMuaBanDoiVeButton;
	private HBox banVeBox;
	private HBox doiVeBox;
	private HBox hoanVeBox;
	private HBox capVeBox;
	private HBox quanLiKhachHangMenu;
	private ImageView quanLiKhachHangIconView;
	private Label quanLiKhachHangLabel;
	private HBox quanLiHoaDonMenu;
	private ImageView quanLiHoaDonIconView;
	private Label quanLiHoaDonLabel;
	private HBox quanLiThongKeMenu;
	private ImageView quanLiThongKeIconView;
	private Label quanLiThongKeLabel;
	private HBox quanLiNhanVienMenu;
	private ImageView quanLiNhanVienIconView;
	private HBox quanLiCTKMMenu;
	private ImageView quanLiCTKMIconView;
	private HBox quanLiChuyenTauMenu;
	private ImageView quanLiChuyenTauIconView;
	private ImageView userIcon;
	private Label userLabel;
	private ImageView settingIcon;
	private VBox layout_timkiem;
	private HBox layout_lbl_timkiem;
	private Label lbl_timkiem;
	private VBox layout_txt_timkiem;
	private TextField txt_timkiem;
	int cnt1 = 0;
	int cnt2 = 0;
	int cnt3 = 0;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	private LichSuTuongTacVe_Dao dao;
	private ArrayList<LichSuTuongTacVe> list;

	public VBox taoDataChoTableLichSuMuaBanDoiVe(String mave, String chuyen, String loai, String gaDiGaDen,
			String ngayKhoiHanh, String vitrighe, LocalDate ngayMua, String hoten, String doituong, String sogiayto,
			double giave, double giamdoituong, double khuyenmai, double giatrichenhlech, double thanhtien) {

		VBox pnlReturn = new VBox();
		VBox.setMargin(pnlReturn, new Insets(0, 30, 0, 45));
		// ======= DÒNG DỮ LIỆU CHÍNH =======
		GridPane data = new GridPane();
		pnlReturn.getChildren().add(data);

		data.setHgap(10);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1330);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";
		Label[] labels = { new Label(mave), new Label(chuyen), new Label(loai), new Label(gaDiGaDen),
				new Label(ngayKhoiHanh.toString()), new Label(vitrighe), new Label(formatter.format(ngayMua)) };
		double[] widths = { 200, 180, 200, 250, 270, 220, 200 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);

			if (i == 5) { // trạng thái
				String text = lbl.getText().toLowerCase();
				if (text.equals("sẵn sàng"))
					lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: #009D75;");
				else if (text.equals("đã khởi hành"))
					lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: rgba(203, 0, 44, 0.83);");
			}

			StackPane pane = new StackPane(lbl);
			pane.setPrefSize(widths[i], 70);
			pane.setAlignment(Pos.CENTER);
			data.add(pane, i, 0);
		}

		String normalStyle = """
				    -fx-background-color: rgba(0, 186, 203, 0.3);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-color: #B6D0D3;
				    -fx-border-width: 1px;
				""";

		String hoverStyle = """
				    -fx-background-color: rgba(0, 186, 203);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-color: #00BACB;
				    -fx-border-width: 1px;
				    -fx-cursor: hand;
				""";

		data.setStyle(normalStyle);

		data.setOnMouseEntered(e -> {
			data.setStyle(hoverStyle);
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
			scaleUp.setToX(1.02);
			scaleUp.setToY(1.02);
			scaleUp.play();
		});

		data.setOnMouseExited(e -> {
			data.setStyle(normalStyle);
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});

		// ======= THÔNG TIN CHI TIẾT =======
		HBox pnlThongTinChiTiet = new HBox(50);
		VBox.setMargin(pnlThongTinChiTiet, new Insets(5, 0, 0, 5));

		// Panel 1 - thông tin cá nhân
		GridPane pnlsubCT1 = new GridPane();
		pnlsubCT1.setHgap(0);
		pnlsubCT1.setVgap(5);
		pnlsubCT1.setAlignment(Pos.CENTER);

		String leftStyle = """
				    -fx-background-color: #00BACB;
				    -fx-background-radius: 10px 0 0 10px;
				    -fx-border-radius: 10px 0 0 10px;
				    -fx-border-color: #00BACB;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";

//	-fx-padding: 8 12 8 12;
		String rightStyle = """
				    -fx-background-color: #E0E0E0;
				    -fx-background-radius: 0 10px 10px 0;
				    -fx-border-radius: 0 10px 10px 0;
				    -fx-border-color: #E0E0E0;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";

		pnlsubCT1.addRow(0, taoSubCT1("Họ tên", hoten, leftStyle, rightStyle));
		pnlsubCT1.addRow(1, taoSubCT1("Đối tượng", doituong, leftStyle, rightStyle));
		pnlsubCT1.addRow(2, taoSubCT1("Số giấy tờ", sogiayto, leftStyle, rightStyle));

		// Các panel giá trị
		String lblCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 18px;";
		String lblValueCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 30px;";

		VBox pnlsubCT2 = taoSubCT2("Giá vé", nf.format(giave), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT3 = taoSubCT2("Giảm đối tượng", nf.format(giamdoituong), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT4 = taoSubCT2("Khuyến mãi", nf.format(khuyenmai), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT5 = taoSubCT2("Giá trị chênh lệch", nf.format(giatrichenhlech), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT6 = taoSubCT2("Thành tiền", nf.format(thanhtien), lblCTStyle, lblValueCTStyle);

		pnlsubCT1.setPrefWidth(400);
		for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT5, pnlsubCT6 })
			pnl.setPrefWidth(320);

		pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT5, pnlsubCT6);
		pnlReturn.getChildren().add(pnlThongTinChiTiet);

		pnlThongTinChiTiet.setManaged(false);
		pnlThongTinChiTiet.setVisible(false);

		pnlReturn.setOnMouseClicked(event -> {
			boolean check = pnlThongTinChiTiet.isVisible();
			pnlThongTinChiTiet.setManaged(!check);
			pnlThongTinChiTiet.setVisible(!check);
		});

		return pnlReturn;
	}

	private HBox taoSubCT1(String label, String value, String leftStyle, String rightStyle) {
		Label lblLeft = new Label(label);
		lblLeft.setWrapText(true);
		StackPane left = new StackPane(lblLeft);
		Label lblRight = new Label(value);
		lblRight.setWrapText(true);
		StackPane right = new StackPane(lblRight);

		left.setPrefWidth(100);
		right.setPrefWidth(150);
		left.setStyle(leftStyle);
		right.setStyle(rightStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		return new HBox(left, right);
	}

	private VBox taoSubCT2(String title, String value, String labelStyle, String valueStyle) {
		Label lblTitle = new Label(title);
		lblTitle.setStyle(labelStyle);
		Label lblValue = new Label(value);
		lblValue.setStyle(valueStyle);
		VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
		box.setAlignment(Pos.CENTER);
		return box;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1600, 900);
			primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			menuList = new VBox();
			menuList.setStyle("-fx-background-color: #F7F7F7;");
			menuList.setPrefWidth(500);

			logoImgView = new ImageView(getClass().getResource("/img/logov2.png").toExternalForm());
			logoImgView.setFitWidth(500);
			logoImgView.setFitHeight(270);
			menuList.getChildren().add(logoImgView);

			scrollPaneMenu = new ScrollPane();
			danhSachMenuItem = new VBox();
//	            Font labelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf"),20);

//				======================
//				||	QUAN LI VE TAU	||
//				======================
			quanLiVeTauMenu = new HBox();
			quanLiVeTauMenu.setSpacing(102);
			quanLiVeTauMenu.setPadding(new Insets(20, 95, 20, 20));
			quanLiVeTauMenu.setStyle("-fx-alignment: center-left;");

//				quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiVeTauIconView = new ImageView(getClass().getResource("/img/ticket.png").toExternalForm());
			quanLiVeTauIconView.setFitWidth(30);
			quanLiVeTauIconView.setFitHeight(30);
			quanLiVeTauIconView.setTranslateX(20);

			quanLiVeTauLabel = new Label("Quản lí vé tàu");

			InputStream interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			Font labelFont;

			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiVeTauLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//				showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			String showMenuPhuIconSource = "/img/chevron-down.png";
			showMenuPhuIconView = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
			showMenuPhuIconView.setFitWidth(20);
			showMenuPhuIconView.setFitHeight(20);
			showMenuPhuIconView.setTranslateX(50);
			quanLiVeTauMenu.getChildren().addAll(quanLiVeTauIconView, quanLiVeTauLabel, showMenuPhuIconView);

			danhSachMenuItem.getChildren().add(quanLiVeTauMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);
			VBox menuPhuQuanLiVeTau = new VBox();
			menuPhuQuanLiVeTau.setSpacing(10);
			menuPhuQuanLiVeTau.setPadding(new Insets(0, 40, 0, 0));
			menuPhuQuanLiVeTau.setVisible(false);
			menuPhuQuanLiVeTau.setManaged(false);
			menuPhuQuanLiVeTau.setStyle("-fx-background-color: #D2EEF0;");
			banVeBox = new HBox();
			doiVeBox = new HBox();
			hoanVeBox = new HBox();
			capVeBox = new HBox();

			Label banVeLabel = new Label("Bán vé");
			Label doiVeLabel = new Label("Đổi vé");
			Label hoanVeLabel = new Label("Hoàn vé");
			Label capVeLabel = new Label("Cấp vé");

			banVeBox.getChildren().add(banVeLabel);
			doiVeBox.getChildren().add(doiVeLabel);
			hoanVeBox.getChildren().add(hoanVeLabel);
			capVeBox.getChildren().add(capVeLabel);

			InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
			Font labelMenuPhu = Font.loadFont(interSemiBold, 15);

			for (Label label : new Label[] { banVeLabel, doiVeLabel, hoanVeLabel, capVeLabel }) {
				label.setStyle("-fx-background-color: #D2EEF0;");
				label.setTranslateY(-10);
				label.setFont(labelMenuPhu);
				label.setPadding(new Insets(12, 320, 12, 155));
				label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
				label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

			}
			for (HBox hbox : new HBox[] { banVeBox, doiVeBox, hoanVeBox, capVeBox }) {
				hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
				hbox.setPadding(new Insets(0, 50, 0, 0));
				hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
				hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
			}

			menuPhuQuanLiVeTau.getChildren().addAll(banVeBox, doiVeBox, hoanVeBox, capVeBox);
			danhSachMenuItem.getChildren().add(menuPhuQuanLiVeTau);

			// Sự kiện onclick vào menu
			quanLiVeTauMenu.setOnMouseClicked(event -> {
				boolean isVisible = menuPhuQuanLiVeTau.isVisible();
				menuPhuQuanLiVeTau.setVisible(!isVisible);
				menuPhuQuanLiVeTau.setManaged(!isVisible);
				if (isVisible == false) {
					quanLiVeTauMenu.setStyle(" -fx-background-color: #79D9E1;");
				} else {
					quanLiVeTauMenu.setStyle(" -fx-background-color: #F7F7F7;");

				}
				TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiVeTau);
				if (!isVisible) {
					menuPhuQuanLiVeTau.setVisible(true);
					menuPhuQuanLiVeTau.setManaged(true);
					menuPhuQuanLiVeTau.setTranslateY(-20); // bắt đầu từ trên
					slide.setFromY(-20);
					slide.setToY(0);
					quanLiVeTauMenu.setStyle("-fx-background-color: #79D9E1;");
				} else {
					slide.setFromY(0);
					slide.setToY(-20);
					slide.setOnFinished(e -> {
						menuPhuQuanLiVeTau.setVisible(false);
						menuPhuQuanLiVeTau.setManaged(false);
					});
					quanLiVeTauMenu.setStyle("-fx-background-color: #F7F7F7;");
				}
				slide.play();

			});

//					======================
//					||QUAN LI KHACH HANG||
//					======================
			quanLiKhachHangMenu = new HBox();
			quanLiKhachHangMenu.setSpacing(102);
			quanLiKhachHangMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiKhachHangMenu.setStyle("-fx-alignment: center-left;");

//				quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiKhachHangIconView = new ImageView(getClass().getResource("/img/user-group.png").toExternalForm());
			quanLiKhachHangIconView.setFitWidth(30);
			quanLiKhachHangIconView.setFitHeight(30);
			quanLiKhachHangIconView.setTranslateX(20);

			quanLiKhachHangLabel = new Label("Quản lí khách hàng");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiKhachHangLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//				showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			quanLiKhachHangMenu.getChildren().addAll(quanLiKhachHangIconView, quanLiKhachHangLabel);

			danhSachMenuItem.getChildren().add(quanLiKhachHangMenu);

//				======================
//				||QUAN LI HOA DON   ||
//				======================
			quanLiHoaDonMenu = new HBox();
			quanLiHoaDonMenu.setSpacing(102);
			quanLiHoaDonMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiHoaDonMenu.setStyle("-fx-alignment: center-left;");

//				quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiHoaDonIconView = new ImageView(getClass().getResource("/img/receipt-tax.png").toExternalForm());
			quanLiHoaDonIconView.setFitWidth(30);
			quanLiHoaDonIconView.setFitHeight(30);
			quanLiHoaDonIconView.setTranslateX(20);

			quanLiHoaDonLabel = new Label("Quản lí hóa đơn");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiHoaDonLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

			//
			quanLiHoaDonMenu.getChildren().addAll(quanLiHoaDonIconView, quanLiHoaDonLabel);

			danhSachMenuItem.getChildren().add(quanLiHoaDonMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);

//				======================
//				||QUAN LI THONG KE  ||
//				======================
			quanLiThongKeMenu = new HBox();
			quanLiThongKeMenu.setSpacing(102);
			quanLiThongKeMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiThongKeMenu.setStyle("-fx-alignment: center-left;");

//				quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiThongKeIconView = new ImageView(
					getClass().getResource("/img/presentation-chart-bar.png").toExternalForm());
			quanLiThongKeIconView.setFitWidth(30);
			quanLiThongKeIconView.setFitHeight(30);
			quanLiThongKeIconView.setTranslateX(20);

			quanLiThongKeLabel = new Label("Quản lí thống kê");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiThongKeLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//				showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			showMenuPhuIconSource = "/img/chevron-down.png";
			showMenuPhuIconView = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
			showMenuPhuIconView.setFitWidth(20);
			showMenuPhuIconView.setFitHeight(20);
			showMenuPhuIconView.setTranslateX(25.5);
			quanLiThongKeMenu.getChildren().addAll(quanLiThongKeIconView, quanLiThongKeLabel, showMenuPhuIconView);

			danhSachMenuItem.getChildren().add(quanLiThongKeMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);
			VBox menuPhuQuanLiThongKe = new VBox();
			menuPhuQuanLiThongKe.setSpacing(10);
			menuPhuQuanLiThongKe.setPadding(new Insets(0, 40, 0, 0));
			menuPhuQuanLiThongKe.setVisible(false);
			menuPhuQuanLiThongKe.setManaged(false);
			menuPhuQuanLiThongKe.setStyle("-fx-background-color: #D2EEF0;");

			HBox thongKe1Box = new HBox();
			HBox thongKe2Box = new HBox();
			HBox thongKe3Box = new HBox();

			Label thongKe1Label = new Label("Thống kê doanh thu theo tháng");
			Label thongKe2Label = new Label("Thống kê ");
			Label thongKe3Label = new Label("Thống kê ghế bán chạy trong tháng");

			thongKe1Box.getChildren().add(thongKe1Label);
			thongKe2Box.getChildren().add(thongKe2Label);
			thongKe3Box.getChildren().add(thongKe3Label);

			interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
			labelMenuPhu = Font.loadFont(interSemiBold, 15);

			for (Label label : new Label[] { thongKe1Label, thongKe2Label, thongKe3Label }) {
				label.setStyle("-fx-background-color: #D2EEF0;");
				label.setTranslateY(0);
				label.setFont(labelMenuPhu);
				label.setPadding(new Insets(12, 320, 12, 155));
				label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
				label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

			}
			for (HBox hbox : new HBox[] { thongKe1Box, thongKe2Box, thongKe3Box }) {
				hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
				hbox.setPadding(new Insets(0, 50, 0, 0));
				hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
				hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
			}

			menuPhuQuanLiThongKe.getChildren().addAll(thongKe1Box, thongKe2Box, thongKe3Box);
			danhSachMenuItem.getChildren().add(menuPhuQuanLiThongKe);

			// Sự kiện onclick vào menu
			quanLiThongKeMenu.setOnMouseClicked(event -> {
				boolean isVisible = menuPhuQuanLiThongKe.isVisible();
				menuPhuQuanLiThongKe.setVisible(!isVisible);
				menuPhuQuanLiThongKe.setManaged(!isVisible);
				if (isVisible == false) {
					quanLiThongKeMenu.setStyle(" -fx-background-color: #79D9E1;");
				} else {
					quanLiThongKeMenu.setStyle(" -fx-background-color: #F7F7F7;");

				}
				TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiThongKe);
				if (!isVisible) {
					menuPhuQuanLiThongKe.setVisible(true);
					menuPhuQuanLiThongKe.setManaged(true);
					menuPhuQuanLiThongKe.setTranslateY(-20);
					slide.setFromY(-20);
					slide.setToY(0);
					quanLiThongKeMenu.setStyle("-fx-background-color: #79D9E1;");
				} else {
					slide.setFromY(0);
					slide.setToY(-20);
					slide.setOnFinished(e -> {
						menuPhuQuanLiThongKe.setVisible(false);
						menuPhuQuanLiThongKe.setManaged(false);
					});
					quanLiThongKeMenu.setStyle("-fx-background-color: #F7F7F7;");
				}
				slide.play();

			});

//				=======================
//				||QUAN LI NHAN VIEN  ||
//				=======================
			quanLiNhanVienMenu = new HBox();
			quanLiNhanVienMenu.setSpacing(102);
			quanLiNhanVienMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiNhanVienMenu.setStyle("-fx-alignment: center-left;");

//				quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiNhanVienIconView = new ImageView(getClass().getResource("/img/user-circle.png").toExternalForm());
			quanLiNhanVienIconView.setFitWidth(30);
			quanLiNhanVienIconView.setFitHeight(30);
			quanLiNhanVienIconView.setTranslateX(20);

			Label quanLiNhanVienLabel = new Label("Quản lí nhân viên");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiNhanVienLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//				showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			quanLiNhanVienMenu.getChildren().addAll(quanLiNhanVienIconView, quanLiNhanVienLabel);

			danhSachMenuItem.getChildren().add(quanLiNhanVienMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);

//				=======================
//				||QUAN LI CTKM        ||
//				=======================
			quanLiCTKMMenu = new HBox();
			quanLiCTKMMenu.setSpacing(102);
			quanLiCTKMMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiCTKMMenu.setStyle("-fx-alignment: center-left;");

//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiCTKMIconView = new ImageView(getClass().getResource("/img/tag.png").toExternalForm());
			quanLiCTKMIconView.setFitWidth(30);
			quanLiCTKMIconView.setFitHeight(30);
			quanLiCTKMIconView.setTranslateX(20);

			Label quanLiCTKMLabel = new Label("Quản lí CT khuyến mãi");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiCTKMLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			quanLiCTKMMenu.getChildren().addAll(quanLiCTKMIconView, quanLiCTKMLabel);

			danhSachMenuItem.getChildren().add(quanLiCTKMMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);

//				=======================
//				||QUAN LI CHUYEN TAU ||
//				=======================
			quanLiChuyenTauMenu = new HBox();
			quanLiChuyenTauMenu.setSpacing(102);
			quanLiChuyenTauMenu.setPadding(new Insets(15, 95, 15, 20));
			quanLiChuyenTauMenu.setStyle("-fx-alignment: center-left;");

//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiChuyenTauIconView = new ImageView(
					getClass().getResource("/img/clipboard-check.png").toExternalForm());
			quanLiChuyenTauIconView.setFitWidth(30);
			quanLiChuyenTauIconView.setFitHeight(30);
			quanLiChuyenTauIconView.setTranslateX(20);

			Label quanLiChuyenTauLabel = new Label("Quản lí chuyến tàu");

			interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			if (interBoldFont != null) {
				labelFont = Font.loadFont(interBoldFont, 20);
				quanLiChuyenTauLabel.setFont(labelFont);
				System.out.println("⚠️ Set font thanh cong.");
				System.out.println("Loaded font: " + labelFont.getName());
			} else {
				System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
				labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
			}

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			quanLiChuyenTauMenu.getChildren().addAll(quanLiChuyenTauIconView, quanLiChuyenTauLabel);

			danhSachMenuItem.getChildren().add(quanLiChuyenTauMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);

			scrollPaneMenu.setPrefHeight(600);
			scrollPaneMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

			scrollPaneMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

			menuList.getChildren().add(scrollPaneMenu);
//	          USER BOX

			HBox userBox = new HBox();
			userIcon = new ImageView(getClass().getResource("/img/user-circle.png").toExternalForm());
			userIcon.setFitWidth(50);
			userIcon.setFitHeight(50);

			userLabel = new Label("Nguyễn Tiến Đạt");
			userLabel.setFont(labelFont);
			userLabel.setTranslateX(30);
			settingIcon = new ImageView(getClass().getResource("/img/cog.png").toExternalForm());
			settingIcon.setFitWidth(30);
			settingIcon.setFitHeight(30);
			settingIcon.setTranslateX(200);
			userBox.setPadding(new Insets(10, 0, 10, 50));
			userBox.setStyle("-fx-alignment: center-left; -fx-background-color: #79D9E1");
			userBox.setTranslateY(70);
			userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
			menuList.getChildren().add(userBox);

			// Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1300);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);

			pnlLichSuMuaBanDoiVe = new Pane();
			lblLichSuMuaBanDoiVe = new Label("Lịch sử mua bán đổi vé");
			pnlLichSuMuaBanDoiVe.getChildren().add(lblLichSuMuaBanDoiVe);
			lblLichSuMuaBanDoiVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			VBox.setMargin(pnlLichSuMuaBanDoiVe, new Insets(20, 0, 0, 50));
			noiDungChinh.getChildren().add(pnlLichSuMuaBanDoiVe);

			layout_timkiem = new VBox();

			layout_lbl_timkiem = new HBox();
			layout_lbl_timkiem.setPrefSize(1200, 40);
			lbl_timkiem = new Label("Nhập mã vé");
			lbl_timkiem.setTranslateX(10);
			lbl_timkiem.setTranslateY(0);
			lbl_timkiem.setStyle(
					"-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");

			ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
			img_timkiem.setTranslateX(1050);
			img_timkiem.setFitHeight(25);
			img_timkiem.setFitWidth(25);

			layout_lbl_timkiem.getChildren().addAll(lbl_timkiem, img_timkiem);
			layout_lbl_timkiem.setTranslateY(48);
			layout_timkiem.getChildren().add(layout_lbl_timkiem);

			layout_txt_timkiem = new VBox();
			txt_timkiem = new TextField();
			txt_timkiem.setPrefHeight(40);
			txt_timkiem.setMaxSize(1200, 45);
			txt_timkiem.setPadding(new Insets(10));
			txt_timkiem.setStyle(
					"-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
			txt_timkiem.setFocusTraversable(false);
			layout_txt_timkiem.getChildren().add(txt_timkiem);
			layout_timkiem.getChildren().add(layout_txt_timkiem);

			layout_timkiem.setTranslateX(100);

			txt_timkiem.focusedProperty().addListener((obs, oval, nval) -> {
				TranslateTransition tt = new TranslateTransition(Duration.millis(350), lbl_timkiem);
				if (nval) {
					tt.setToY(-40);
				}
				tt.play();
			});

			VBox.setMargin(layout_timkiem, new Insets(0, 0, 20, 0));
			noiDungChinh.getChildren().add(layout_timkiem);

			pnlLichSuMuaBanDoiVeButton = new HBox(200);
			pnlLichSuMuaBanDoiVeButton.setAlignment(Pos.CENTER);

			btnLichSuMuaVe = new Button("Lịch sử mua vé");
			btnLichSuDoiVe = new Button("Lịch sử đổi vé");
			btnLichSuHoanVe = new Button("Lịch sử hoàn vé");

			styleLichSuMuaBanDoiVeButton = """
					    -fx-font-family: 'Inter';
					    -fx-font-weight: bold;
					    -fx-font-size: 20px;
					    -fx-text-fill: white;
					    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
					    -fx-background-radius: 10;
						-fx-cursor: hand;
						-fx-padding: 10 20 10 20;
					""";

			btnLichSuDoiVe.setStyle(styleLichSuMuaBanDoiVeButton);
			btnLichSuMuaVe.setStyle(styleLichSuMuaBanDoiVeButton);
			btnLichSuHoanVe.setStyle(styleLichSuMuaBanDoiVeButton);

			pnlLichSuMuaBanDoiVeButton.getChildren().addAll(btnLichSuMuaVe, btnLichSuDoiVe, btnLichSuHoanVe);

			noiDungChinh.getChildren().add(pnlLichSuMuaBanDoiVeButton);
			// table
			tableColLichSu = new GridPane();
			tableColLichSu.setHgap(10);
			tableColLichSu.setVgap(20);
			tableColLichSu.setAlignment(Pos.CENTER);
			tableColLichSu.setMaxWidth(1330);
			VBox.setMargin(tableColLichSu, new Insets(30, 20, 10, 35));

			String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 24px; -fx-font-weight: bold;";

			String[] headers = { "Mã vé", "Mã chuyến", "Loại tương tác", "Ga đi - Ga đến", "Ngày khởi hành",
					"Vị trí ghế", "Ngày mua" };

			double[] widths = { 200, 200, 250, 250, 270, 220, 200 };

			for (int i = 0; i < headers.length; i++) {
				Label label = new Label(headers[i]);
				label.setStyle(styleHeader);

				StackPane pane = new StackPane(label);
				pane.setPrefWidth(widths[i]);
				pane.setAlignment(Pos.CENTER);

				tableColLichSu.add(pane, i, 0);
			}

			noiDungChinh.getChildren().add(tableColLichSu);

			pnlDataDoiVe = loadDuLieuLenTable();
			pnlDataDoiVe.setAlignment(Pos.CENTER);

			// === TẠO SCROLLPANE ===
			scrollPane = new ScrollPane(pnlDataDoiVe);
			scrollPane.setMaxHeight(500);
			scrollPane.setFitToWidth(true);
			scrollPane.setPannable(true);
			scrollPane.setStyle("""
					    -fx-background-color: transparent;
					    -fx-border-color: transparent;
					    -fx-border-width: 0;
					""");

			// Chỉ hiện thanh cuộn dọc
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
			noiDungChinh.getChildren().add(scrollPane);

			// add su kien disable btn
			txt_timkiem.setOnAction(event -> {
				String regex = "^VE\\d{3}$";
				String input = txt_timkiem.getText().trim();

				if (input.isEmpty() || !Pattern.matches(regex, input)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Mã vé không hợp lệ! (Định dạng hợp lệ: VE123)");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}

			});

			btnLichSuMuaVe.setOnMouseClicked(event -> {
				String maVeGetText = txt_timkiem.getText();
				System.out.println(maVeGetText);
				String normalStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: white;
						    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				String blankStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-color: white;
						    -fx-border-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-border-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				if (cnt1 == 0) {
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(blankStyle);
					btnLichSuDoiVe.setStyle(blankStyle);
					cnt1 = 1;
					cnt2 = 0;
					cnt3 = 0;

					if (maVeGetText != null) {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("bán")
									&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
								pnlDataDoiVe.getChildren().clear();
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}

					} else {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("bán")) {
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
					}
				} else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);

					pnlDataDoiVe.getChildren().clear();
					for (LichSuTuongTacVe x : list) {
						pnlDataDoiVe.getChildren().add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
								x.getVeTau().getChuyenTau().getMaChuyenTau(),
								x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
								x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
								x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
										+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
								"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
								x.getVeTau().getNgayGioDi().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
								x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
								x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
								x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
								x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(), x.getGiaTriChenhLech(), 0.0));
					}
				}

			});

			btnLichSuDoiVe.setOnMouseClicked(event -> {
				String maVeGetText = txt_timkiem.getText();
				System.out.println(maVeGetText);
				String normalStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: white;
						    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				String blankStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-color: white;
						    -fx-border-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-border-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				if (cnt2 == 0) {

					btnLichSuMuaVe.setStyle(blankStyle);
					btnLichSuHoanVe.setStyle(blankStyle);
					btnLichSuDoiVe.setStyle(normalStyle);
					cnt1 = 0;
					cnt2 = 1;
					cnt3 = 0;

					if (maVeGetText != null && !maVeGetText.trim().isEmpty()) {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("đổi")
									&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}

					} else {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("đổi")) {
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
					}
				} else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);

					pnlDataDoiVe.getChildren().clear();
					for (LichSuTuongTacVe x : list) {
						pnlDataDoiVe.getChildren().add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
								x.getVeTau().getChuyenTau().getMaChuyenTau(),
								x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
								x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
								x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
										+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
								"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
								x.getVeTau().getNgayGioDi().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
								x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
								x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
								x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
								x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(), x.getGiaTriChenhLech(), 0.0));
					}
				}
			});

			btnLichSuHoanVe.setOnMouseClicked(event -> {
				String maVeGetText = txt_timkiem.getText();
				System.out.println(maVeGetText);
				String normalStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: white;
						    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				String blankStyle = """
						    -fx-font-family: 'Inter';
						    -fx-font-weight: bold;
						    -fx-font-size: 20px;
						    -fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-color: white;
						    -fx-border-color: linear-gradient(to top, #00BACB, #8EE6ED);
						    -fx-background-radius: 10;
						    -fx-border-radius: 10;
						    -fx-cursor: hand;
						    -fx-padding: 10 20 10 20;
						""";

				if (cnt3 == 0) {

					btnLichSuMuaVe.setStyle(blankStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(blankStyle);
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 1;

					if (maVeGetText != null && !maVeGetText.trim().isEmpty()) {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("hoàn trả")
									&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}

					} else {
						pnlDataDoiVe.getChildren().clear();
						for (LichSuTuongTacVe x : list) {
							if (x.getLoaiTuongTacVe().getTenLoaiTuongTac().equalsIgnoreCase("hoàn trả")) {
								pnlDataDoiVe.getChildren()
										.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
												x.getVeTau().getChuyenTau().getMaChuyenTau(),
												x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
												x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
												x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
														+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
												"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
												x.getVeTau().getNgayGioDi().toLocalDate(),
												x.getVeTau().getKhachHang().getHoten(),
												x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
												x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
												x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
												x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(),
												x.getGiaTriChenhLech(), 0.0));
							}
						}
					}
				} else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);

					pnlDataDoiVe.getChildren().clear();
					for (LichSuTuongTacVe x : list) {
						pnlDataDoiVe.getChildren().add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
								x.getVeTau().getChuyenTau().getMaChuyenTau(),
								x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
								x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
								x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
										+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
								"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
								x.getVeTau().getNgayGioDi().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
								x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
								x.getVeTau().getKhachHang().getSdt(), x.getVeTau().getGiaVe(),
								x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
								x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(), x.getGiaTriChenhLech(), 0.0));
					}
				}

			});

			hieuUngHover(btnLichSuDoiVe);
			hieuUngHover(btnLichSuHoanVe);
			hieuUngHover(btnLichSuMuaVe);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public StackPane thongBaoKhongTimThayVe() {
		String css = " -fx-font-family: 'Inter';" + "-fx-font-weight: bold;" + "-fx-font-size: 20px;"
				+ "-fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);";
		Label tb = new Label("Không tìm thấy vé thuộc loại cần tìm!");
		tb.setStyle(css);
		StackPane tbao = new StackPane(tb);
		tbao.setAlignment(Pos.CENTER);
		return tbao;
	}

	public VBox loadDuLieuLenTable() throws SQLException {
		dao = new LichSuTuongTacVe_Dao();
		list = dao.getList();
		VBox box = new VBox(10);
		for (LichSuTuongTacVe x : list) {
			box.getChildren().add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
					x.getVeTau().getChuyenTau().getMaChuyenTau(), x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
					x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
					x.getVeTau().getNgayGioDi().toLocalDate().toString() + " - "
							+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
					"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
					x.getVeTau().getNgayGioDi().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
					x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(), x.getVeTau().getKhachHang().getSdt(),
					x.getVeTau().getGiaVe(), x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia(),
					x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai(), x.getGiaTriChenhLech(), 0.0));
		}
		return box;
	}

	public void xoaDataTrenTable(VBox pnlData) {
		pnlData.getChildren().clear();
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

	public VBox getLichSuMuaVe() {
		return this.noiDungChinh;
	}

//	public public static void main(String[] args) {
//		launch(args);
//	}
}