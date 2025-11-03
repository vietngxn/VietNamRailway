package fourcore.GiaoDien;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import fourcore.Entity.KhachHang;
import fourcore.dao.ThongKeDAO;
import fourcore.util.ExcelExporter;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class QuanLiThongKeKhachHang extends Application {
	private BorderPane manHinhChinh;
	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
	private Image logoImage;
	Class<?> clazz = this.getClass();
	private ScrollPane scrollPaneMenu;
	private VBox danhSachMenuItem;
	private HBox quanLiVeTauMenu;
	private Label quanLiVeTauLabel;
	private Image quanLiVeTauIcon;
	private ImageView quanLiVeTauIconView;
	private Image showMenuPhuIcon;
	private ImageView showMenuPhuIconView;
	private VBox title_layout;
	private Label lbl_title;
	private Label lbl_timkiem;
	private TextField txt_timkiem;
	private HBox layout_lbl_timkiem;
	private VBox layout_txt_timkiem;
	private HBox layout_nutthongke;
	private VBox table_layout;
	private HBox table_title;
	private Label lbl_maKhachHang;
	private Label lbl_hoTen;
	private Label lbl_cccd;
	private Label lbl_doituong;
	private Label lbl_doiTuong;
	private HBox layout_dong;
	private Label lbl_title_maHoaDon;
	private Label lbl_title_nguoiMua;
	private Node lbl_title_ngayLap;
	private Node lbl_title_loaiHoaDon;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private GridPane hangchon = new GridPane();
	private HBox layout_button;
	private Button btn_xoaChuyenTau;
	private Button btn_xuatHoaDon;
	private Button btn_xemChiTiet;
	private Label lbl_title_thoigiankhoihanh;
	private Node lbl_title_tongTien;
	private VBox button_layout;
	private Button button_thongKe1;
	private Button button_thongKe2 = new Button();
	private Button button_thongKe3;
	private HBox layout_combobox;
	private ComboBox<String> comboBox = new ComboBox<String>();
	private DatePicker date;
	private VBox btn_layout;
	private Button btn_xuatThongKe;
	private GridPane tableCol;
	private Label colmaKhachHang;
	private Label coltenKhachHang;
	private Label colsoLanSuDung;
	private StackPane paneCol1;
	private StackPane paneCol2;
	private StackPane paneCol3;
	private VBox table_desc_layout;
	private Label lbl_maGhe;
	private Label lbl_soLanSuDung;
	private Label lbl_tenGhe;
	    private HBox banVeBox;
	    private HBox doiVeBox;
	    private HBox hoanVeBox;
	    private HBox capVeBox;
	    private HBox quanLiKhachHangMenu;
	    private ImageView quanLiKhachHangIconView;
	    private ImageView quanLiKhachHangView;
	    private HBox themKhachHangBox;
	    private HBox xoaKhachHangBox;
	    private HBox suaKhachHangBox;
	    private Label quanLiKhachHang;
	    private Label quanLiKhachHangLabel;
	    private HBox quanLiHoaDonMenu;
	    private ImageView quanLiHoaDonIconView;
	    private Label quanLiHoaDonLabel;
	    private HBox timHoaDonBox;
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
	    private ImageView moTaDoanhThuIcon;
		private VBox lineChart_layout;
		private VBox layout_total;
		private Label lbl_doanhThu;
	    private List<Boolean> checks = new ArrayList<>(Arrays.asList(false, false, false));
		private Label lbl_tongTien;
		private Label lbl_desc;
		private HBox layout_desc;
		private String loaiThongKe;
		private ThongKeDAO thongkeDAO;
		private Map<KhachHang, Integer> map;
	@Override
	public void start(Stage primaryStage) {
try {
			thongkeDAO = new ThongKeDAO();
			map = thongkeDAO.getKhachHangvaSoLanDi();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,1920,1000);
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
//            Font labelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf"),20);

//			======================
//			||	QUAN LI VE TAU	||
//			======================
            quanLiVeTauMenu = new HBox();
            quanLiVeTauMenu.setSpacing(102);
            quanLiVeTauMenu.setPadding(new Insets(20, 95, 20, 20));
            quanLiVeTauMenu.setStyle("-fx-alignment: center-left;");


//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
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

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
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
            Font labelMenuPhu = Font.loadFont(interSemiBold,15);

            for (Label label : new Label[]{banVeLabel, doiVeLabel, hoanVeLabel,capVeLabel}) {
                label.setStyle("-fx-background-color: #D2EEF0;");
                label.setTranslateY(-10);
                label.setFont(labelMenuPhu);
                label.setPadding(new Insets(12, 320, 12, 155));
                label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
                label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

            }
            for (HBox hbox : new HBox[]{banVeBox, doiVeBox, hoanVeBox,capVeBox}) {
                hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
                hbox.setPadding(new Insets(0,50,0,0));
                hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
                hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
            }

            menuPhuQuanLiVeTau.getChildren().addAll(banVeBox, doiVeBox, hoanVeBox,capVeBox);
            danhSachMenuItem.getChildren().add(menuPhuQuanLiVeTau);

            // Sự kiện onclick vào menu
            quanLiVeTauMenu.setOnMouseClicked(event -> {
                boolean isVisible = menuPhuQuanLiVeTau.isVisible();
                menuPhuQuanLiVeTau.setVisible(!isVisible);
                menuPhuQuanLiVeTau.setManaged(!isVisible);
                if (isVisible == false) {
                    quanLiVeTauMenu.setStyle(" -fx-background-color: #79D9E1;");
                }
                else {
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


//				======================
//				||QUAN LI KHACH HANG||
//				======================
            quanLiKhachHangMenu = new HBox();
            quanLiKhachHangMenu.setSpacing(102);
            quanLiKhachHangMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiKhachHangMenu.setStyle("-fx-alignment: center-left;");


//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
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

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiKhachHangMenu.getChildren().addAll(quanLiKhachHangIconView, quanLiKhachHangLabel);

            danhSachMenuItem.getChildren().add(quanLiKhachHangMenu);


//			======================
//			||QUAN LI HOA DON   ||
//			======================
            quanLiHoaDonMenu = new HBox();
            quanLiHoaDonMenu.setSpacing(102);
            quanLiHoaDonMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiHoaDonMenu.setStyle("-fx-alignment: center-left;");


//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
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

//			======================
//			||QUAN LI THONG KE  ||
//			======================
            quanLiThongKeMenu = new HBox();
            quanLiThongKeMenu.setSpacing(102);
            quanLiThongKeMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiThongKeMenu.setStyle("-fx-alignment: center-left;");


//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiThongKeIconView = new ImageView(getClass().getResource("/img/presentation-chart-bar.png").toExternalForm());
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

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
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
            labelMenuPhu = Font.loadFont(interSemiBold,15);

            for (Label label : new Label[]{thongKe1Label,thongKe2Label,thongKe3Label}) {
                label.setStyle("-fx-background-color: #D2EEF0;");
                label.setTranslateY(0);
                label.setFont(labelMenuPhu);
                label.setPadding(new Insets(12, 320, 12, 155));
                label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
                label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

            }
            for (HBox hbox : new HBox[]{thongKe1Box,thongKe2Box,thongKe3Box}) {
                hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
                hbox.setPadding(new Insets(0,50,0,0));
                hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
                hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
            }

            menuPhuQuanLiThongKe.getChildren().addAll(thongKe1Box,thongKe2Box,thongKe3Box);
            danhSachMenuItem.getChildren().add(menuPhuQuanLiThongKe);

            // Sự kiện onclick vào menu
            quanLiThongKeMenu.setOnMouseClicked(event -> {
                boolean isVisible = menuPhuQuanLiThongKe.isVisible();
                menuPhuQuanLiThongKe.setVisible(!isVisible);
                menuPhuQuanLiThongKe.setManaged(!isVisible);
                if (isVisible == false) {
                    quanLiThongKeMenu.setStyle(" -fx-background-color: #79D9E1;");
                }
                else {
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


//			=======================
//			||QUAN LI NHAN VIEN  ||
//			=======================
            quanLiNhanVienMenu = new HBox();
            quanLiNhanVienMenu.setSpacing(102);
            quanLiNhanVienMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiNhanVienMenu.setStyle("-fx-alignment: center-left;");


//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
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

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiNhanVienMenu.getChildren().addAll(quanLiNhanVienIconView, quanLiNhanVienLabel);


            danhSachMenuItem.getChildren().add(quanLiNhanVienMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

//			=======================
//			||QUAN LI CTKM        ||
//			=======================
            quanLiCTKMMenu = new HBox();
            quanLiCTKMMenu.setSpacing(102);
            quanLiCTKMMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiCTKMMenu.setStyle("-fx-alignment: center-left;");

//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
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

//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiCTKMMenu.getChildren().addAll(quanLiCTKMIconView, quanLiCTKMLabel);

            danhSachMenuItem.getChildren().add(quanLiCTKMMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);


//			=======================
//			||QUAN LI CHUYEN TAU ||
//			=======================
            quanLiChuyenTauMenu = new HBox();
            quanLiChuyenTauMenu.setSpacing(102);
            quanLiChuyenTauMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiChuyenTauMenu.setStyle("-fx-alignment: center-left;");

//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiChuyenTauIconView = new ImageView(getClass().getResource("/img/clipboard-check.png").toExternalForm());
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

//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiChuyenTauMenu.getChildren().addAll(quanLiChuyenTauIconView, quanLiChuyenTauLabel);

            danhSachMenuItem.getChildren().add(quanLiChuyenTauMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

//----------------------------------------------------------------------------------------------------------------------
            scrollPaneMenu.setPrefHeight(600);
            scrollPaneMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

            scrollPaneMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            menuList.getChildren().add(scrollPaneMenu);
//          USER BOX


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
            userBox.setPadding(new Insets(10,0,10,50));
            userBox.setStyle("-fx-alignment: center-left; -fx-background-color: #79D9E1");
            userBox.setTranslateY(70);
            userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
            menuList.getChildren().add(userBox);
			
			//Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			create_title_layout();
		
			create_table_desc();
			
			create_btnlayout();
			primaryStage.setFullScreen(true);
//			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create_title_layout() {
		title_layout  = new VBox();
		title_layout.setPadding(new Insets(30));
		title_layout.setSpacing(20);
		
		lbl_title = new Label("Thống kê top 10 khách hàng đi tàu nhiều nhất"); 
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);
		
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size: 14px;-fx-text-fill:#00BACB;-fx-background-color:white;-fx-background-color:white;-fx-background-radius:20px;-fx-border-radius:20px;-fx-border-color:#00BACB;-fx-border-width:2px;";
		
		
		layout_nutthongke = new HBox();
		
		layout_nutthongke.setSpacing(50);
		
		
		Button button_thongKe1 = new Button("Thống Kê Ghế bán chạy nhất tháng");
		button_thongKe1.setWrapText(true);
		button_thongKe1.setStyle(style);
		button_thongKe1.setPrefSize(275, 50);
		
		Button button_thongKe2 = new Button("Thống Kê theo Tháng");
		button_thongKe2.setStyle(style);
		button_thongKe2.setPrefSize(275, 50);
		
		
		Button button_thongKe3 = new Button("Thống Kê Năm");
		button_thongKe3.setStyle(style);
		button_thongKe3.setPrefSize(275, 50);
		
//		layout_nutthongke.getChildren().addAll(button_thongKe1,button_thongKe2,button_thongKe3);
		
		checks = new ArrayList<>(Arrays.asList(false, false, false));

		button_thongKe1.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
		
		button_thongKe1.setOnMouseClicked(e -> {
		    if (!checks.get(0)) {
		    	table_desc.getChildren().clear();
		        button_thongKe1.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        checks.set(0, true);
		        checks.set(1, false);
		        checks.set(2, false);
		        noiDungChinh.getChildren().remove(layout_total);
		        
//		        ObservableList<String> items = FXCollections.observableArrayList("BarChart","Table");
//		        comboBox.setItems(items);
		        if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
		        	table_desc.getChildren().clear();
		        	create_barchart_khachHang();
		        	noiDungChinh.getChildren().remove(layout_total);
		        }
		        else {
		        	table_desc.getChildren().clear();
		        	create_table();
		        }
		        comboBox.getSelectionModel().selectFirst();
//		        comboBox.setOnAction(null);
		        comboBox.setOnAction(event  -> { 
		        	if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
		        		table_desc.getChildren().clear();
		        		create_barchart_khachHang();
		        		noiDungChinh.getChildren().remove(layout_total);
		        	}
		        	else {
		        		table_desc.getChildren().clear();
		        		create_table();
		        	}
		        });
		    } else {
		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        checks.set(0, false);
		    }
		});

//		button_thongKe2.setOnMouseClicked(e -> {
//		    if (!checks.get(1)) {
//		        button_thongKe2.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
//		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        
//		        ObservableList<String> itemsThang = FXCollections.observableArrayList("BarChart", "PieChart");
//		        comboBox.setItems(itemsThang);
//		        comboBox.getSelectionModel().selectFirst();
//		        
//		        if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
//	            	table_desc.getChildren().clear();
//	                create_barchart_thang();
//	                noiDungChinh.getChildren().remove(layout_total);
//	                create_layout_total();
//	            } else {
//	            	table_desc.getChildren().clear();
//	                create_piechart_thang();
//	                noiDungChinh.getChildren().remove(layout_total);
//	                create_layout_total();
//	            }
//		        checks.set(0, false);
//		        checks.set(1, true);
//		        checks.set(2, false);
//		        comboBox.setOnAction(null);
//		        comboBox.setOnAction(event -> {
//		            if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
//		            	table_desc.getChildren().clear();
//		                create_barchart_thang();
//		                noiDungChinh.getChildren().remove(layout_total);
//		                create_layout_total();
//		            } else {
//		            	table_desc.getChildren().clear();
//		                create_piechart_thang();
//		                noiDungChinh.getChildren().remove(layout_total);
//		                create_layout_total();
//		            }
//		        });
//		    } else {
//		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        checks.set(1, false);
//		    }
//		});
//
//		button_thongKe3.setOnMouseClicked(e -> {
//			
//		    if (!checks.get(2)) {
//		        button_thongKe3.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
//		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        checks.set(0, false);
//		        checks.set(1, false);
//		        checks.set(2, true);
//		        ObservableList<String> itemsThang = FXCollections.observableArrayList("BarChart", "PieChart");
//		        comboBox.setItems(itemsThang);
//		        comboBox.getSelectionModel().selectFirst();
//		        
//		        if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
//	            	table_desc.getChildren().clear();
//	                create_barchart_nam();
//	                noiDungChinh.getChildren().remove(layout_total);
//	                create_layout_total();
//	            } else {
//	            	table_desc.getChildren().clear();
//	                create_piechart_nam();
//	                noiDungChinh.getChildren().remove(layout_total);
//	                create_layout_total();
//	            }
//		        comboBox.setOnAction(null);
//		        comboBox.setOnAction(event -> {
//		            if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
//		            	table_desc.getChildren().clear();
//		                create_barchart_nam();
//		                noiDungChinh.getChildren().remove(layout_total);
//		                create_layout_total();
//		            } else {
//		            	table_desc.getChildren().clear();
//		                create_piechart_nam();
//		                noiDungChinh.getChildren().remove(layout_total);
//		                create_layout_total();
//		            }
//		        });
//		    } else {
//		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
//		        checks.set(2, false);
//		    }
//		});
//		check(checks);
		button_thongKe1.hoverProperty().addListener((obs,oval,nval)->{
			ScaleTransition st = new ScaleTransition(Duration.millis(200), button_thongKe1);
			if(nval)
			{
				button_thongKe1.setStyle(button_thongKe1.getStyle()+"-fx-cursor:hand;");
				st.setToX(1.1);
				st.setToY(1.1);
			}
			else {
			
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});
		
//		button_thongKe2.hoverProperty().addListener((obs,oval,nval)->{
//			ScaleTransition st = new ScaleTransition(Duration.millis(200), button_thongKe2);
//			if(nval)
//			{
//				button_thongKe2.setStyle(button_thongKe2.getStyle()+"-fx-cursor:hand;");	
//				st.setToX(1.1);
//				st.setToY(1.1);
//			}
//			else {
//			
//				st.setToX(1);
//				st.setToY(1);
//			}
//			st.play();
//		});
//		
//		button_thongKe3.hoverProperty().addListener((obs,oval,nval)->{
//			ScaleTransition st = new ScaleTransition(Duration.millis(200), button_thongKe3);
//			if(nval)
//			{
//				button_thongKe3.setStyle(button_thongKe3.getStyle()+"-fx-cursor:hand;");
//				st.setToX(1.1);
//				st.setToY(1.1);
//			}
//			else {
//			
//				st.setToX(1);
//				st.setToY(1);
//			}
//			st.play();	
//		});
		
		title_layout.getChildren().add(layout_nutthongke);
		
		layout_combobox = new HBox();
		layout_combobox.setTranslateY(20);
		layout_combobox.setSpacing(30);
		ObservableList<String> items = FXCollections.observableArrayList("BarChart","Table");
		
		String style1 = "-fx-font-family:'Inter';-fx-text-fill:#00BACB;-fx-font-size: 15px;";
		comboBox = createComboBox(items);
		comboBox.setPromptText("Kiểu Thống Kê");
		comboBox.setStyle(comboBox.getStyle()+";"+style1);
		comboBox.getSelectionModel().selectFirst();
		
		comboBox.setOnAction(event  -> { 
        	if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
        		table_desc.getChildren().clear();
        		create_barchart_khachHang();
        		noiDungChinh.getChildren().remove(layout_total);
        	}
        	else {
        		table_desc.getChildren().clear();
        		create_table();
        	}
        });
		
		date = new DatePicker();
		date.setPromptText("Thời Gian");
		date.setStyle(style1+";-fx-border-color:#00BACB;-fx-border-width:2px;");
		
		date.setPrefSize(400, 40);
		date.getStyleClass().add("date-picker-custom");
		layout_combobox.getChildren().addAll(comboBox);
		
		title_layout.getChildren().add(layout_combobox);
		
		
		
		noiDungChinh.getChildren().add(title_layout);
	}
	
	
	
	private ComboBox<String> createComboBox(ObservableList<String> items) {
        ComboBox<String> combobox = new ComboBox<>(items);
        combobox.getStyleClass().add("combo-box-custom");
        combobox.setPrefHeight(40);
        combobox.setPrefWidth(400);
        combobox.setEditable(true);
        combobox.setId("combo-box");
        
        return combobox;
    }
	
	public void create_table_desc()
	{
		table_desc = new VBox();
		table_desc.setPrefHeight(400);
		
		comboBox.getSelectionModel().clearSelection();
		comboBox.setOnAction(event  -> { 
        	if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("barchart")) {
        		table_desc.getChildren().clear();
        		create_barchart_khachHang();
        		btn_xuatThongKe.setDisable(false);
        		noiDungChinh.getChildren().remove(layout_total);
        	}
        	else if(comboBox.getValue() != null && comboBox.getValue().equalsIgnoreCase("table")) {
        		table_desc.getChildren().clear();
        		btn_xuatThongKe.setDisable(false);
        		create_table();
        	}
        });
		
		
		noiDungChinh.getChildren().add(table_desc);
	}
	
	public void create_barchart_thang() {
	    // Trục tọa độ cho BarChart
	    CategoryAxis xAxis = new CategoryAxis();
	    NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Tháng");
	    yAxis.setLabel("Doanh thu (triệu)");

	    // Biểu đồ cột
	    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
	    barChart.setTitle("Doanh thu theo tháng");
	    barChart.setLegendVisible(false);
	    barChart.setAnimated(false);
	    
	    
	    
		// Dữ liệu cho BarChart
	    XYChart.Series<String, Number> barData = new XYChart.Series<>();
	    barData.getData().add(new XYChart.Data<>("Tháng 1", 500));
	    barData.getData().add(new XYChart.Data<>("Tháng 2", 300));
	    barData.getData().add(new XYChart.Data<>("Tháng 3", 700));
	    barData.getData().add(new XYChart.Data<>("Tháng 4", 500));
	    barData.getData().add(new XYChart.Data<>("Tháng 5", 300));
	    barData.getData().add(new XYChart.Data<>("Tháng 6", 1000));
	    barData.getData().add(new XYChart.Data<>("Tháng 7", 930));
	    barData.getData().add(new XYChart.Data<>("Tháng 8", 200));
	    barData.getData().add(new XYChart.Data<>("Tháng 9", 500));
	    barData.getData().add(new XYChart.Data<>("Tháng 10", 200));
	    barData.getData().add(new XYChart.Data<>("Tháng 11", 751));
	    barData.getData().add(new XYChart.Data<>("Tháng 12", 770));
	    barChart.getData().add(barData);
	    
	    
	    table_desc.getChildren().clear();
	    table_desc.getChildren().add(barChart);
	}

	
	public void create_piechart_thang()
	{
		PieChart pieChart = new PieChart();
	    pieChart.setTitle("Tỷ lệ sản phẩm bán ra");

	    pieChart.getData().add(new PieChart.Data("Doanh Thu", 90));
	    pieChart.getData().add(new PieChart.Data("Thuế VAT", 10));
	    
	    
	    table_desc.getChildren().add(pieChart);
	}
	
	
	public void create_barchart_nam()
	{
		CategoryAxis ca = new CategoryAxis();
		ca.setLabel("Năm");
		NumberAxis na = new NumberAxis();
		na.setLabel("Doanh thu(triệu)");
		
		BarChart<String, Number> barchart = new BarChart<>(ca, na);
		barchart.setTitle("Thống Kê Doanh Thu Theo Năm");
		barchart.setLegendVisible(false);
		barchart.setAnimated(false);
		
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		data.getData().add(new XYChart.Data<String, Number>("2017", 500));
		data.getData().add(new XYChart.Data<String, Number>("2018", 600));
		data.getData().add(new XYChart.Data<String, Number>("2019", 400));
		data.getData().add(new XYChart.Data<String, Number>("2020", 300));
		data.getData().add(new XYChart.Data<String, Number>("2021", 250));
		data.getData().add(new XYChart.Data<String, Number>("2022", 450));
		data.getData().add(new XYChart.Data<String, Number>("2023", 500));
		data.getData().add(new XYChart.Data<String, Number>("2024", 600));
		
		barchart.getData().add(data);
		
		table_desc.getChildren().clear();
		table_desc.getChildren().add(barchart);
	}
	
	public void create_piechart_nam()
	{
		PieChart pieChart = new PieChart();
	    pieChart.setTitle("Tỷ lệ doanh thu bán ra");

	    pieChart.getData().add(new PieChart.Data("Doanh Thu", 90));
	    pieChart.getData().add(new PieChart.Data("Thuế VAT", 10));
	    
	    
	    table_desc.getChildren().add(pieChart);
	}
	
	public void create_barchart_khachHang()
	{
			CategoryAxis ca = new CategoryAxis();
			ca.setLabel("Khách Hàng");
			NumberAxis na = new NumberAxis();
			na.setLabel("Số lần đi");
			
			BarChart<String, Number> barchart = new BarChart<String, Number>(ca, na);
			barchart.setTitle("Thống kê top 10 khách hàng đi  nhiều nhất tháng");
			barchart.setLegendVisible(false);
			barchart.setAnimated(false);
			
			ArrayList<XYChart.Data<String, Number>> listdata = new ArrayList<>();
			
			for(Map.Entry<KhachHang, Integer> x : map.entrySet())
			{
				KhachHang kh = x.getKey();
				int soLan = x.getValue();
				listdata.add(new XYChart.Data<>(kh.getHoten(),soLan));
			}
			
			for(int i = 0; i < listdata.size() - 1;i++)
			{
				for(int j = i+1 ; j < listdata.size();j++)
				{
					if(listdata.get(i).getYValue().doubleValue() < listdata.get(j).getYValue().doubleValue())
					{
						XYChart.Data<String, Number> temp = listdata.get(i);
						listdata.set(i, listdata.get(j));
						listdata.set(j, temp);
					}
				}
			}
			double  max = listdata.get(0).getYValue().doubleValue();
			na.setAutoRanging(false);
			na.setLowerBound(0);
			na.setUpperBound(max+1);
			na.setTickUnit(1);

			na.setMinorTickVisible(false);
			
			XYChart.Series<String, Number> list = new XYChart.Series<String, Number>();
			
			for(XYChart.Data<String, Number> s : listdata)
			{
				list.getData().add(s);
			}
			barchart.getData().add(list);
			
			table_desc.getChildren().clear();
			
			table_desc.getChildren().add(barchart);
	}
	
	public void create_table()
	{
		table_layout = new VBox(); 
		tableCol = new GridPane();
		tableCol.setHgap(10);
		tableCol.setVgap(20);
		tableCol.setAlignment(Pos.CENTER);
		tableCol.setMaxWidth(1330);
		VBox.setMargin(tableCol, new Insets(30, 10, 10, 0));

		String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 24px; -fx-font-weight: bold;";

		colmaKhachHang = new Label("Mã Khách Hàng");
		colmaKhachHang.setTranslateX(-250);
		colmaKhachHang.setStyle(styleHeader);
		
		coltenKhachHang = new Label("Tên Khách Hàng");
		coltenKhachHang.setTranslateX(-80);
		coltenKhachHang.setStyle(styleHeader);
		
		
		
		colsoLanSuDung = new Label("Số lần đi");
		colsoLanSuDung.setTranslateX(250);
		colsoLanSuDung.setStyle(styleHeader);
		
		
		paneCol1 = new StackPane(colmaKhachHang);
		paneCol2 = new StackPane(coltenKhachHang);
		paneCol3 = new StackPane(colsoLanSuDung);
		
		paneCol1.setPrefWidth(200);
		paneCol2.setPrefWidth(200);
		paneCol3.setPrefWidth(400);
		
		
		paneCol1.setAlignment(Pos.CENTER);
		paneCol2.setAlignment(Pos.CENTER);
		paneCol3.setAlignment(Pos.CENTER);


		tableCol.add(paneCol1, 0, 0);
		tableCol.add(paneCol2, 1, 0);
		tableCol.add(paneCol3, 2, 0);
		
		table_layout.getChildren().add(tableCol);
		
		table_desc_layout = new VBox();
		table_desc_layout.setSpacing(20);
		
		for(Map.Entry<KhachHang, Integer> x : map.entrySet())
		{
			KhachHang kh = x.getKey();
			int solan = x.getValue();
			create_layout_dong(kh.getMaKhachHang(),kh.getHoten(), solan);
		}
		
		
		table_layout.getChildren().add(table_desc_layout);
		table_desc.getChildren().add(table_layout);
	}
	
	public void create_layout_dong(String maKH,String tenKH,int soLanSuuDung)
	{
		
		GridPane data = new GridPane();
	    data.setHgap(10);
	    data.setAlignment(Pos.CENTER);
	    data.setMaxWidth(1330);
	    data.setPrefHeight(70);
	    data.setPadding(new Insets(0, 20, 0, 20));

	    // Thiết lập 3 cột có độ rộng tương đối
	    ColumnConstraints col1 = new ColumnConstraints();
	    col1.setPercentWidth(30); // Mã KH
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPercentWidth(40); // Tên KH
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPercentWidth(30); // Số lần sử dụng

	    data.getColumnConstraints().addAll(col1, col2, col3);
		
	    
		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";
		
		
		lbl_maGhe = new Label(maKH);
	    lbl_tenGhe = new Label(tenKH);
	    lbl_soLanSuDung = new Label(Integer.toString(soLanSuuDung));

	    lbl_maGhe.setStyle(baseStyle);
	    lbl_tenGhe.setStyle(baseStyle);
	    lbl_soLanSuDung.setStyle(baseStyle);
	    lbl_maGhe.setTranslateX(50);
	    lbl_soLanSuDung.setAlignment(Pos.CENTER_RIGHT);
	    GridPane.setHalignment(lbl_soLanSuDung, HPos.CENTER);

	    // Thêm vào grid đúng cột
	    data.add(lbl_maGhe, 0, 0);
	    data.add(lbl_tenGhe, 1, 0);
	    data.add(lbl_soLanSuDung, 2, 0);
		
		lbl_maGhe.setStyle(baseStyle);
		lbl_tenGhe.setStyle(baseStyle);
		lbl_soLanSuDung.setStyle(baseStyle);
		
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
		
		String selectedStyle = """
	    	    -fx-background-color: rgba(0, 186, 203, 0.9);
	    	    -fx-background-radius: 15px;
	    	    -fx-border-radius: 15px;
	    	    -fx-border-color: #00BACB;
	    	    -fx-border-width: 2px;
	    	""";
		
		data.setStyle(normalStyle);
		
		final GridPane dongHienTai = data;
		
		data.setOnMouseClicked(e -> {
	        // Bỏ chọn dòng cũ nếu có
	        if (hangchon != null && hangchon != data) {
	        	hangchon.setStyle(normalStyle);
	        }
	        // Chọn dòng hiện tại
	        hangchon = data;
	        data.setStyle(selectedStyle);
	    });

	    // Hover effect
	    data.setOnMouseEntered(e -> {
	        // Chỉ thay đổi style nếu dòng này không phải dòng được chọn
	        if (hangchon != data) {
	            data.setStyle(hoverStyle);
	        }
	        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
	        scaleUp.setToX(1.02);
	        scaleUp.setToY(1.02);
	        scaleUp.play();
	    });

	    // Rời chuột
	    data.setOnMouseExited(e -> {
	        // Nếu đây không phải dòng được chọn, trả về style bình thường
	        if (hangchon != data) {
	            data.setStyle(normalStyle);
	        }
	        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
	        scaleDown.setToX(1.0);
	        scaleDown.setToY(1.0);
	        scaleDown.play();
	    });
	    
		
		table_desc_layout.getChildren().add(data);
	}
	public void create_layout_total()
	{
		layout_total = new VBox();
		layout_total.setTranslateX(1100);
		layout_total.setTranslateY(50);
		
		layout_total.setPrefSize(200, 100);
		layout_total.setMaxSize(200, 100);
		layout_total.setAlignment(Pos.CENTER_LEFT);
		layout_total.setPadding(new Insets(10));
		String style = "-fx-text-fill:black;";
		layout_total.setStyle("-fx-background-color: #9EFFFF;-fx-background-radius:15px;-fx-border-radius:15px;");
		
		lbl_doanhThu = new Label("Tổng Doanh Thu: ");
		lbl_doanhThu.setTranslateY(-10);
		lbl_doanhThu.setStyle(style+"-fx-font-family: 'Work Sans';-fx-font-size : 20px;");
		
		lbl_tongTien = new Label("15.000.000đ");
		lbl_tongTien.setTranslateX(15);
		lbl_tongTien.setTranslateY(-5);
		
		
		
		lbl_tongTien.setStyle(style + "-fx-font-family : 'Inter';-fx-font-weight: bold;-fx-font-size : 25px;");
		
		
		
		layout_total.getChildren().addAll(lbl_doanhThu,lbl_tongTien);
		noiDungChinh.getChildren().add(layout_total);
	}
	
	public void create_btnlayout()
	{
		btn_layout = new VBox();
		btn_layout.setTranslateX(1100);
		btn_layout.setTranslateY(250);
		btn_xuatThongKe = new Button("Xuất Thống Kê");
		btn_xuatThongKe.setPrefSize(200,40);
		btn_xuatThongKe.setDisable(true);
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size: 20px;-fx-text-fill:#00BACB;-fx-background-color:white;-fx-background-color:white;-fx-background-radius:20px;-fx-border-radius:20px;-fx-border-color:#00BACB;-fx-border-width:2px;";
		btn_xuatThongKe.setStyle(style+"-fx-background-color:#00BACB;-fx-text-fill:white;");
		btn_layout.getChildren().add(btn_xuatThongKe);
		btn_xuatThongKe.hoverProperty().addListener((obs,oval,nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_xuatThongKe);
			if(nval)
			{
				btn_xuatThongKe.setStyle(btn_xuatThongKe.getStyle()+"-fx-cursor : hand;");
				st.setToX(1.1);
				st.setToY(1.1);
			}
			else
			{
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});
		
		btn_xuatThongKe.setOnMouseClicked(e -> {
		    try {
		        // Tạo folder nếu chưa có
		        String folderPath = "ThongKeExport";
		        File folder = new File(folderPath);
		        if (!folder.exists()) {
		            folder.mkdirs();
		        }
		        
		        // Tạo tên file với thời gian
		        LocalDateTime now = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
		        String fileName = "ThongKeChuyenTau_" + now.format(formatter) + ".xlsx";
		        String filePath = folderPath + "/" + fileName;
		        
		        // Export trực tiếp
		        ExcelExporter.exportThongKeKhachHang(map, filePath);
		        

                File pdfFile = new File(filePath);
                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile); // mở file PDF bằng ứng dụng mặc định
                    } else {
                        System.out.println("Desktop không được hỗ trợ. Vui lòng mở file thủ công: " + pdfFile.getAbsolutePath());
                    }
                } else {
                    System.out.println("File PDF không tồn tại: " + filePath);
                }
		        
		        // Thông báo thành công
		        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Thành công");
		        alert.setHeaderText(null);
		        alert.setContentText("Xuất file thành công!\n" + filePath);
		        alert.showAndWait();
		        
		    } catch (Exception ex) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Lỗi");
		        alert.setHeaderText(null);
		        alert.setContentText("Lỗi khi xuất file: " + ex.getMessage());
		        alert.showAndWait();
		    }
		});
		
		
		noiDungChinh.getChildren().add(btn_layout);
	}
	
	

	public VBox getQuanLiThongKe(){
        return this.noiDungChinh;
    }
    public void setLoaiThongKe(String loaiThongKe){
        this.loaiThongKe = loaiThongKe;
    }
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}