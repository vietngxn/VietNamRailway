package fourcore.GiaoDien;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class GiaoDienXuatHoaDon extends Application {

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
	private HBox doiVeBox;
	private HBox banVeBox;
	private HBox hoanVeBox;
	private HBox capVeBox;
	private HBox quanLiKhachHangMenu;
	private ImageView quanLiKhachHangIconView;
	private Label quanLiKhachHangLabel;
	private HBox quanLiHoaDonMenu;
	private ImageView quanLiHoaDonIconView;
	private Label quanLiHoaDonLabel;
	private ImageView quanLiThongKeIconView;
	private HBox quanLiThongKeMenu;
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
	private Pane pnlDoiVelbl;
	private Label lblDoiVe;
	private Pane pnlThongTinlbl;
	private Label lblThongTin;
	private GridPane pnlThongTinNguoiMua;
	private Pane pnlXuatHDlbl;
	private Label lblXuatHD;
	private GridPane pnlRadXuatHoaDon;
	private RadioButton RadXuatHDCongTy;
	private RadioButton RadXuatHDCaNhan;

	@Override
	public void start(Stage primaryStage) {
		try {

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

			pnlDoiVelbl = new Pane();
			lblDoiVe = new Label("Giỏ vé");
			pnlDoiVelbl.getChildren().add(lblDoiVe);
			lblDoiVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlDoiVelbl);
			VBox.setMargin(pnlDoiVelbl, new Insets(20, 0, 0, 50));

			pnlThongTinlbl = new Pane();
			lblThongTin = new Label("Thông tin người mua vé");
			pnlThongTinlbl.getChildren().add(lblThongTin);
			lblThongTin.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlThongTinlbl);
			VBox.setMargin(pnlThongTinlbl, new Insets(20, 0, 0, 50));

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

			pnlThongTinNguoiMua.add(createSubPane("Họ tên", "Nguyễn Tiến Đạt", leftStyle, rightStyle), 0, 0);
			pnlThongTinNguoiMua.add(createSubPane("Email", "abc@gmail.com", leftStyle, rightStyle), 1, 0);
			pnlThongTinNguoiMua.add(createSubPane("Số giấy tờ", "096123123123", leftStyle, rightStyle), 0, 1);
			pnlThongTinNguoiMua.add(createSubPane("SĐT", "0933345556", leftStyle, rightStyle), 1, 1);
			noiDungChinh.getChildren().add(pnlThongTinNguoiMua);

			pnlXuatHDlbl = new Pane();
			lblXuatHD = new Label("Xuất hóa đơn");
			pnlXuatHDlbl.getChildren().add(lblXuatHD);
			lblXuatHD.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlXuatHDlbl);
			VBox.setMargin(pnlXuatHDlbl, new Insets(20, 0, 0, 50));

			pnlRadXuatHoaDon = new GridPane();
			pnlRadXuatHoaDon.setAlignment(Pos.CENTER);
			pnlRadXuatHoaDon.setHgap(400);
			pnlRadXuatHoaDon.setVgap(10);
			VBox.setMargin(pnlRadXuatHoaDon, new Insets(20, 0, 0, 0));

			RadXuatHDCongTy = new RadioButton("Xuất hóa đơn cho công ty/đơn vị");
			RadXuatHDCaNhan = new RadioButton("Xuất hóa đơn cho cá nhân");

			String radStyle = """
					    -fx-font-family: 'Inter';
					    -fx-font-weight: bold;
					    -fx-font-size: 18px;
					    -fx-text-fill: #00BACB;
					    -fx-mark-color: #00BACB;
					""";

			RadXuatHDCongTy.setStyle(radStyle);
			RadXuatHDCaNhan.setStyle(radStyle);
			ToggleGroup groupRad = new ToggleGroup();

			RadXuatHDCongTy.setToggleGroup(groupRad);
			RadXuatHDCaNhan.setToggleGroup(groupRad);

			RadXuatHDCaNhan.setSelected(true);

			pnlRadXuatHoaDon.add(RadXuatHDCongTy, 1, 0);
			pnlRadXuatHoaDon.add(RadXuatHDCaNhan, 0, 0);

			noiDungChinh.getChildren().add(pnlRadXuatHoaDon);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			BorderPane root = new BorderPane();
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);

			Scene scene = new Scene(root, 1800, 900);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hệ thống quản lý vé tàu");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HBox createSubPane(String label, String value, String leftStyle, String rightStyle) {
		Label lblLeft = new Label(label);
		lblLeft.setWrapText(true);
		StackPane left = new StackPane(lblLeft);
		left.setAlignment(Pos.CENTER);
		Label lblRight = new Label(value);
		lblRight.setWrapText(true);
		StackPane right = new StackPane(lblRight);
		right.setAlignment(Pos.CENTER);

		left.setPrefWidth(90);
		right.setPrefWidth(200);
		left.setStyle(leftStyle);
		right.setStyle(rightStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		return new HBox(left, right);
	}

	private HBox createSubXuatHoaDonPane(String label, String value, String leftStyle, String rightStyle) {
		Label lblLeft = new Label(label);
		lblLeft.setWrapText(true);
		StackPane left = new StackPane(lblLeft);
		left.setAlignment(Pos.CENTER);
		Label lblRight = new Label(value);
		lblRight.setWrapText(true);
		StackPane right = new StackPane(lblRight);
		right.setAlignment(Pos.CENTER);

		left.setPrefWidth(90);
		right.setPrefWidth(200);
		left.setStyle(leftStyle);
		right.setStyle(rightStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		return new HBox(left, right);
	}

	public VBox getNoiDungChinhVe() {
		return this.noiDungChinh;
	}

	public static void main(String[] args) {
		Application.launch(GiaoDienXuatHoaDon.class, args);
	}
}