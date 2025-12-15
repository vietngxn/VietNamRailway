package fourcore.GiaoDien;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import fourcore.Control.BanVeControl;
import fourcore.Entity.*;
import fourcore.dao.ChuongTrinhKhuyenMaiDAO;
import fourcore.dao.DoiTuongGiamGiaDAO;
import fourcore.dao.GaTauDao;
import fourcore.dao.ToaTauDAO;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
import javafx.scene.text.Text;

public class GioVe extends Application {

	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
    KhuyenMai kmnull = new KhuyenMai("KM000",0);
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	Class<?> clazz = this.getClass();
	private ScrollPane scrollPaneMenu;
	private VBox danhSachMenuItem;
	private HBox quanLiVeTauMenu;
	private Label quanLiVeTauLabel;
	private ImageView quanLiVeTauIconView;
	private ImageView showMenuPhuIconView;

	private ScrollPane scrollPane;
//	private int cnt = 1;
	private Pane pnlGioVelbl;
	private Label lblGioVe;
	private VBox pnlDataGioVe;
	private VBox pnlGioVeButton;
	private HBox pnlGioVeButtonSub1;
	private HBox pnlGioVeButtonSub2;
	private Button btnApDungChuongTrinhKhuyenMai;
	private Label lblTongCong;
	private Label lblTongCongValue;
	private HBox pnlTongCong;
	private Button btnTroLai= new Button("Trở lại");;
	Button btnTiepTuc = new Button();
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
    String gaDen = "";
	ToaTauDAO toaTauDAO = new ToaTauDAO();
	ArrayList<ToaTau> listToaTauTrenChuyen;
	public ArrayList<GheTrenChuyenTau> listGheSelected = new ArrayList<>();
	DoiTuongGiamGiaDAO dtggDAO = new DoiTuongGiamGiaDAO();
	public ArrayList<DoiTuongGiamGia> listDoiTuongGiamGia = dtggDAO.getListDoiTuongGiamGia();
	Map<GheTrenChuyenTau, Double> mapTienGhe = new LinkedHashMap<>();
	Map<GheTrenChuyenTau, KhachHang> mapChuyenTauVaUser = new LinkedHashMap<>();
    GaTauDao gaTauDao = new GaTauDao();
	ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();

	private List<TextField> listTxtHoTen = new ArrayList<>();
	private List<TextField> listTxtSoGiayTo = new ArrayList<>();
	private List<ComboBox<String>> listCmbDoiTuong = new ArrayList<>();
    ArrayList<GheTrenChuyenTau> listKhuHoiSelected= new ArrayList<GheTrenChuyenTau>();

	double phanTramGG = 0;
	double tongCongThanhTien;
	private Label lblTrangThaiApDung;
	KhuyenMai khuyenMaiSelect = null;

	private TextField txtHoTen;
	private TextField txtSoGiayTo;
	ArrayList<KhuyenMai> listCTKM;
	
	public GioVe() throws SQLException {
	}
    public VBox getGDChinh() {
        return noiDungChinh;
    }
	@Override
	public void start(Stage primaryStage) {
		try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gaDen.dat"))) {
                gaDen = ois.readObject().toString();
                System.out.println("Dữ liệu ga den đọc được: " + gaDen);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1600, 800);
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
			noiDungChinh.setPrefWidth(1200);

			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);

			pnlGioVelbl = new Pane();
			lblGioVe = new Label("Giỏ vé");
			pnlGioVelbl.getChildren().add(lblGioVe);
			lblGioVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlGioVelbl);
			VBox.setMargin(pnlGioVelbl, new Insets(20, 0, 0, 50));
			pnlDataGioVe = new VBox(10);
			pnlDataGioVe.setAlignment(Pos.CENTER);

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ds_ghe_dang_chon.dat"))) {
                listGheSelected.clear();

				listGheSelected = (ArrayList<GheTrenChuyenTau>) ois.readObject();
				System.out.println("Dữ liệu đọc được: " + listGheSelected);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ds_ghe_khuhoi_dang_chon.dat"))) {
                listKhuHoiSelected = (ArrayList<GheTrenChuyenTau>) ois.readObject();
                System.out.println("Dữ liệu đọc được: " + listKhuHoiSelected);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            pnlDataGioVe.getChildren().clear();
			for (int i = 0; i < listGheSelected.size(); i++) {
				pnlDataGioVe.getChildren().add(taoDataChoTableGioVe(listGheSelected.get(i),"di"));
			}
            if(listKhuHoiSelected.size()>0){
                for(int i = 0; i < listKhuHoiSelected.size(); i++){
                    listKhuHoiSelected.get(i).setKhuHoi(true);
                    pnlDataGioVe.getChildren().add(taoDataChoTableGioVe(listKhuHoiSelected.get(i),"ve"));

                }
            }


			scrollPane = new ScrollPane();
			scrollPane.setContent(pnlDataGioVe);
			scrollPane.setMaxHeight(650);
			VBox.setMargin(scrollPane, new Insets(100, 0, 0, 0));
			scrollPane.setFitToWidth(true);
			scrollPane.setStyle("""
					    -fx-background-color: transparent;
					    -fx-border-color: transparent;
					    -fx-border-width: 0;
					""");
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Không hiện thanh ngang
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Thanh dọc chỉ hiện khi cần
			scrollPane.setPannable(true);

			// ScrollPane sẽ tự động mở rộng nhưng không vượt quá chiều cao còn lại của cửa
			// sổ
			VBox.setVgrow(scrollPane, Priority.ALWAYS);
			noiDungChinh.getChildren().add(scrollPane);

			pnlGioVeButton = new VBox();
			pnlGioVeButtonSub1 = new HBox();
			pnlGioVeButtonSub2 = new HBox();

			String btnRedStyle = "-fx-font-family: 'Inter';" + "-fx-font-size: 20px;" + "-fx-font-weight: bold;"
					+ "-fx-text-fill:white;"
					+ "-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);"
					+ "-fx-background-radius:15px;";

			String btnBlueStyle = "-fx-font-family: 'Inter';" + "-fx-font-size: 20px;" + "-fx-font-weight: bold;"
					+ "-fx-text-fill:white;" + "-fx-background-color: linear-gradient(to top, #00BACB, #B6D0D3);"
					+ "-fx-background-radius:15px;";

			String lblStyle = "-fx-font-size: 36px;";
			btnApDungChuongTrinhKhuyenMai = new Button("Áp dụng CTKM");
			btnApDungChuongTrinhKhuyenMai.setStyle(btnBlueStyle);
			btnApDungChuongTrinhKhuyenMai.setPrefSize(280, 50);
			lblTrangThaiApDung = new Label();

			pnlTongCong = new HBox();
			lblTongCong = new Label("Tổng cộng:");
			lblTongCong.setWrapText(true);
			lblTongCong.setStyle(lblStyle);

			lblTongCongValue = new Label("" +0);
			lblTongCongValue.setWrapText(true);
			lblTongCongValue.setStyle(lblStyle + "-fx-font-weight: bold;");

			HBox.setMargin(lblTongCong, new Insets(0, 20, 0, 0));
			pnlTongCong.setAlignment(Pos.CENTER);

			HBox.setMargin(pnlTongCong, new Insets(0, 0, 0, 650));
			pnlTongCong.getChildren().addAll(lblTongCong, lblTongCongValue);
			btnApDungChuongTrinhKhuyenMai.setOnAction(event -> {
				Stage popupStage = new Stage();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                popupStage.initOwner(stage);
				popupStage.initModality(Modality.WINDOW_MODAL);
				popupStage.initStyle(StageStyle.UTILITY);
				popupStage.setAlwaysOnTop(true);
				popupStage.setTitle("Chọn chương trình khuyến mãi");

				// Nội dung popup
				VBox root1 = new VBox(20);
				root1.setPadding(new Insets(20));
				root1.setAlignment(Pos.CENTER);

				Label lbl = new Label("Chọn chương trình khuyến mãi");
				lbl.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

				ComboBox<String> cmbCTKM = new ComboBox<>();
				cmbCTKM.setPromptText("Chọn chương trình");

				// Lấy danh sách CTKM từ DB
				ChuongTrinhKhuyenMaiDAO ctkmDAO = null;
				try {
					ctkmDAO = new ChuongTrinhKhuyenMaiDAO();
                    ctkmDAO.getListKhuyenMai();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

					try {
						listCTKM = ctkmDAO.getListKhuyenMaiCoXoa();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				ArrayList<String> tenChuongTrinhKhuyenMai = new ArrayList<>();
                tenChuongTrinhKhuyenMai.clear();
				for (KhuyenMai km : listCTKM) {
					tenChuongTrinhKhuyenMai.add(km.getTenChuongTrinh());
				}
				cmbCTKM.getItems().addAll(tenChuongTrinhKhuyenMai);

				Button btnApDung = new Button("Áp dụng");
				btnApDung.setStyle("-fx-font-size: 16px; -fx-background-color: #00BACB; -fx-text-fill: white;");
				btnApDung.setPrefWidth(120);

				btnApDung.setOnAction(e -> {
					String selected = cmbCTKM.getValue();
					if (selected == null) {
						Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng chọn chương trình khuyến mãi!",
								ButtonType.OK);
						alert.initOwner(popupStage);
						alert.showAndWait();
						return;
					} else {
						for (KhuyenMai km : listCTKM) {
							if (km.getTenChuongTrinh().equals(selected)) {
								khuyenMaiSelect = km;
								break;
							}
						}
						double phanTramGG = khuyenMaiSelect.getGiaTriPhanTramKhuyenMai();
						tongCongThanhTien = 0.0;
						for (Double gia : mapTienGhe.values()) {
							tongCongThanhTien += gia;
						}
						tongCongThanhTien = tongCongThanhTien - ((tongCongThanhTien * phanTramGG) / 100);
						loadLableTongCongValue(tongCongThanhTien);

						lblTrangThaiApDung.setText("Chương trình đang áp dụng: " + khuyenMaiSelect.getTenChuongTrinh()
								+ " với ưu đãi " + khuyenMaiSelect.getGiaTriPhanTramKhuyenMai() + "%");
						popupStage.close();
					}

				});

				root1.getChildren().addAll(lbl, cmbCTKM, btnApDung);
				Scene scene1 = new Scene(root1, 400, 200);
				popupStage.setScene(scene1);
				popupStage.showAndWait(); // show modal trong fullscreen, không tạo cửa sổ thứ 2
			});
			lblTrangThaiApDung.setTranslateX(400);
			lblTrangThaiApDung.setTranslateY(-130);
			lblTrangThaiApDung.setFont(Font.font("Arial", FontWeight.BOLD, 16));
			lblTrangThaiApDung.setStyle("-fx-text-fill: #43A047");
			pnlGioVeButtonSub1.getChildren().addAll(btnApDungChuongTrinhKhuyenMai, pnlTongCong);

			btnTroLai.setStyle(btnRedStyle);
			btnTroLai.setPrefSize(270, 50);
            btnTiepTuc.setText("Tiếp tục");
			btnTiepTuc.setStyle(btnBlueStyle);
			btnTiepTuc.setPrefSize(280, 50);

			pnlGioVeButtonSub2.getChildren().addAll(btnTroLai, btnTiepTuc);
			HBox.setMargin(btnTroLai, new Insets(0, 750, 0, 0));
			pnlGioVeButton.getChildren().addAll(pnlGioVeButtonSub1, pnlGioVeButtonSub2);
			VBox.setMargin(pnlGioVeButtonSub1, new Insets(20, 0, 0, 0));
			VBox.setMargin(pnlGioVeButtonSub2, new Insets(50, 0, 0, 0));
			noiDungChinh.getChildren().addAll(pnlGioVeButton, lblTrangThaiApDung);

			VBox.setMargin(pnlGioVeButton, new Insets(0, 0, 0, 20));
			hieuUngHover(btnApDungChuongTrinhKhuyenMai);
			hieuUngHover(btnTiepTuc);
			hieuUngHover(btnTroLai);
            BanVeControl banVeControl = new BanVeControl();
			btnTiepTuc.setOnMouseClicked(event -> {
                mapChuyenTauVaUser.clear();
                ArrayList<GheTrenChuyenTau> tongGheTrenChuyenTau = listGheSelected;
                for(int i = 0; i < listKhuHoiSelected.size(); i++){
                    tongGheTrenChuyenTau.add(listKhuHoiSelected.get(i));
                }
				for (int i = 0; i < listTxtHoTen.size(); i++) {
                    if(listTxtHoTen.get(i).getText()==null || listTxtSoGiayTo.get(i).getText().trim().isEmpty() ||  listCmbDoiTuong.get(i).getValue()==null){
                        System.out.println("so giay to: " + listTxtSoGiayTo.get(i).getText());
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        alert.initOwner(stage);
                        alert.initModality(Modality.WINDOW_MODAL);
                        alert.showAndWait();
                        break;
                    }
                    System.out.println("so giay to: " + listTxtSoGiayTo.get(i).getText());

					KhachHang kh = new KhachHang(listTxtHoTen.get(i).getText(), listTxtSoGiayTo.get(i).getText(),
							listCmbDoiTuong.get(i).getValue());
					GheTrenChuyenTau ghe;
					ghe = tongGheTrenChuyenTau.get(i);
					mapChuyenTauVaUser.put(ghe, kh);
			        System.out.println("Thêm vào map: " + ghe + " -> " + kh.toString2());
				}
                if(mapChuyenTauVaUser.size()==tongGheTrenChuyenTau.size()) {
                    // Ghi danh sách ghế ra file
                    File file = new File("mapGheVaKhachHang.dat");
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                        oos.writeObject(mapChuyenTauVaUser);
                        System.out.println("✅ Đã ghi " + mapChuyenTauVaUser.size() + " ghế và khách hàng vào file: " + file.getAbsolutePath());
                    }
                    catch (IOException e) {
                    e.printStackTrace();
                }
                    if(khuyenMaiSelect != null){
                        File file2 = new File("KMSelected.dat");
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file2))) {
                            oos.writeObject(khuyenMaiSelect);
                            System.out.println("✅ Đã ghi khuyen mai: "+khuyenMaiSelect.getTenChuongTrinh()+" vào file: " + file2.getAbsolutePath());
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        File file2 = new File("KMSelected.dat");
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file2))) {
                            oos.writeObject(kmnull);
                            System.out.println("✅ Đã ghi khuyen mai null vào file: " + file2.getAbsolutePath());
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (banVeControl != null) {
                        try {
                            BorderPane root2 = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
                            banVeControl.tiepDenGDXuatHoaDon(root2);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

			});

			primaryStage.setFullScreen(true);
//			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void clearSelectedList(){
        listGheSelected.clear();
    }
    public Button getGioVeTroLaiBtn(){
        return btnTroLai;
    }
    public Button getGioVeTiepTucBtn(){
        return btnTiepTuc;
    }
    public VBox getGDGioVe(){
        return noiDungChinh;
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

	public VBox taoDataChoTableGioVe(GheTrenChuyenTau gheTrenChuyenTau, String flag) throws SQLException {
		Label lblGiamDoiTuongValue = new Label("0");
		Label lblKhuyenMaiValue = new Label("0");
        double giaVe1Value = 0;
        if (gheTrenChuyenTau.getGheNgoi().isLuuDong()){
            giaVe1Value = gheTrenChuyenTau.getChuyenTau().getGiaCuocTrenChuyenTau() * gaTauDao.getCuLiBangTenGa(gaDen) + gheTrenChuyenTau.getGiaTienGhe();
        }else {
            giaVe1Value = gheTrenChuyenTau.getGiaTienGhe();
        }
		Label lblThanhTienValue = new Label(String.valueOf(giaVe1Value));
        System.out.println("Ga den:"+ gaTauDao.getCuLiBangTenGa(gaDen)+" Gia tri thanh tien khoi tao "+ giaVe1Value +" gia ghe" + gheTrenChuyenTau.getGiaTienGhe());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
		VBox pnlReturn = new VBox();
		VBox.setMargin(pnlReturn, new Insets(0, 30, 0, 30));
		// ======= DÒNG DỮ LIỆU CHÍNH =======
		GridPane data = new GridPane();
		pnlReturn.getChildren().add(data);

		data.setHgap(10);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1330);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";

		// list toa (ToaTauDAO)
		int soToa = 0;
		listToaTauTrenChuyen = toaTauDAO.getListToaTauByMaCT(gheTrenChuyenTau.getChuyenTau().getMaChuyenTau());
		for (int i = 0; i < listToaTauTrenChuyen.size(); i++) {
			if (listToaTauTrenChuyen.get(i).getMaToaTau()
					.equals(gheTrenChuyenTau.getGheNgoi().getToaTau().getMaToaTau())) {
				soToa = i + 1;
				break;
			}
		}
        Label[] labels = null;
        if(flag.equals("di")){
            labels = new Label[]{new Label(gheTrenChuyenTau.getChuyenTau().getMaChuyenTau()), new Label("TPHCM - " + gaDen),
                    new Label(gheTrenChuyenTau.getChuyenTau().getNgayGioDi().format(formatter)),
                    new Label("Ghế số: " + gheTrenChuyenTau.getGheNgoi().getSoGhe() + " Toa số: " + soToa)};
        }else{
            labels = new Label[]{new Label(gheTrenChuyenTau.getChuyenTau().getMaChuyenTau()), new Label(gaDen + " - " + "TPHCM"),
                    new Label(gheTrenChuyenTau.getChuyenTau().getNgayGioDi().format(formatter)),
                    new Label("Ghế số: " + gheTrenChuyenTau.getGheNgoi().getSoGhe() + " Toa số: " + soToa)};
        }

		double[] widths = { 200, 200, 200, 200 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);

			StackPane pane = new StackPane(lbl);
			pane.setPrefSize(widths[i], 70);
			if (i == 3) {
				pane.setTranslateX(400);
			} else if (i == 2) {
				pane.setTranslateX(200);
			} else if (i == 1) {
				pane.setTranslateX(-140);
			} else if (i == 0) {
				pane.setTranslateX(-70);
			}
			pane.setAlignment(Pos.CENTER);
			data.add(pane, i, 0);
		}

		// === ICON “plus” ===
		ImageView img_xoa = new ImageView(getClass().getResource("/images/copy/plus.png").toExternalForm());
		img_xoa.setFitHeight(40);
		img_xoa.setFitWidth(40);
		GridPane.setMargin(img_xoa, new Insets(0, 0, 0, 400));

		data.add(img_xoa, 4, 0);

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
				      -fx-border-color: black;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";

//	-fx-padding: 8 12 8 12;
		String rightStyle = """
				    -fx-background-color: white;
				    -fx-background-radius: 0 10px 10px 0;
				    -fx-border-radius: 0 10px 10px 0;
				    -fx-border-color: black;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";
        double giaTienGhe= 0;
        if(gheTrenChuyenTau.getGheNgoi().isLuuDong()){
            giaTienGhe = gheTrenChuyenTau.getChuyenTau().getGiaCuocTrenChuyenTau() * gaTauDao.getCuLiBangTenGa(gaDen) + gheTrenChuyenTau.getGiaTienGhe();
        }else{
            giaTienGhe  =gheTrenChuyenTau.getGiaTienGhe();
        }
		pnlsubCT1.addRow(0, taoSubCT1("Họ tên", "", leftStyle, rightStyle, 1));
		pnlsubCT1.addRow(1, taoSubCT1("Đối tượng", "", leftStyle, rightStyle, 2, giaTienGhe,
				lblGiamDoiTuongValue, lblThanhTienValue, gheTrenChuyenTau));
		pnlsubCT1.addRow(2, taoSubCT1("Số giấy tờ", "", leftStyle, rightStyle, 3));

		// Các panel giá trị
		String lblCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 18px;";
		String lblValueCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 30px;";
        GaTauDao gaTauDao = new GaTauDao();

		VBox pnlsubCT2 = taoSubCT2("Giá vé", giaVe1Value , lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT3 = taoSubCT2WithLabel("Giảm đối tượng", lblGiamDoiTuongValue, lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT5 = taoSubCT2WithLabel("Thành tiền", lblThanhTienValue, lblCTStyle, lblValueCTStyle);

		pnlReturn.setUserData("");
        double giaGheHienTai = 0;
        if(gheTrenChuyenTau.getGheNgoi().isLuuDong()){
            giaGheHienTai = gheTrenChuyenTau.getChuyenTau().getGiaCuocTrenChuyenTau() * gaTauDao.getCuLiBangTenGa(gaDen) + gheTrenChuyenTau.getGiaTienGhe();
        }else {
            giaGheHienTai = gheTrenChuyenTau.getGiaTienGhe();

        }
		mapTienGhe.put(gheTrenChuyenTau, giaGheHienTai);
		tongCongThanhTien += gheTrenChuyenTau.getGiaTienGhe();

		pnlsubCT1.setPrefWidth(400);
		for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT3, pnlsubCT5 })
			pnl.setPrefWidth(320);

		pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT3, pnlsubCT5);
		pnlReturn.getChildren().add(pnlThongTinChiTiet);

		pnlThongTinChiTiet.setManaged(false);
		pnlThongTinChiTiet.setVisible(false);

		pnlReturn.setOnMouseClicked(event -> {
			boolean check = pnlThongTinChiTiet.isVisible();
			pnlThongTinChiTiet.setManaged(!check);
			pnlThongTinChiTiet.setVisible(!check);
		});

		img_xoa.setOnMouseEntered(e -> {
			img_xoa.setOpacity(0.5);
			img_xoa.setStyle("-fx-cursor: hand;");
		});

		img_xoa.setOnMouseExited(e -> img_xoa.setOpacity(1.0));

		img_xoa.setOnMouseClicked(e -> {
			listGheSelected.remove(gheTrenChuyenTau);
			System.out.println(listGheSelected.size());
			mapTienGhe.remove(gheTrenChuyenTau);
			((VBox) pnlReturn.getParent()).getChildren().remove(pnlReturn);
			tongCongThanhTien = 0.0;
			for (Double gia : mapTienGhe.values()) {
				tongCongThanhTien += gia;
			}
			loadLableTongCongValue(tongCongThanhTien);
			System.out.println("Đã xóa vé: " + gheTrenChuyenTau.getMaGheTrenChuyenTau());
		});

		return pnlReturn;
	}

	private VBox taoSubCT2WithLabel(String title, Label lblValue, String labelStyle, String valueStyle) {
		Label lblTitle = new Label(title);
		lblTitle.setStyle(labelStyle);
		lblValue.setStyle(valueStyle);
		VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
		box.setAlignment(Pos.CENTER);
		return box;
	}

	private HBox taoSubCT1(String label, String value, String leftStyle, String rightStyle, int check) {
		StackPane left = new StackPane(new Label(label));
		StackPane right = new StackPane();
		right.setPrefSize(200, 40);

		if (check == 1) {
			txtHoTen = new TextField();
			txtHoTen.setPromptText("Nhập họ tên");
			String regexHoten = "[a-zA-ZÀ-ỹ\\s]+$";
			txtHoTen.setOnAction(event -> {
				String input = txtHoTen.getText();
				System.out.println(input);
				if (!input.matches(regexHoten)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Họ tên không hợp lệ");
					// Gắn vào Stage hiện tại (cực kỳ quan trọng)
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					// Không cho resize, luôn ở giữa cửa sổ cha
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}
			});
			txtHoTen.setStyle(rightStyle);
			txtHoTen.setMaxWidth(Double.MAX_VALUE);
			txtHoTen.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(txtHoTen, Pos.CENTER);
			right.getChildren().add(txtHoTen);
			listTxtHoTen.add(txtHoTen);
		} else if (check == 3) {
			txtSoGiayTo = new TextField();
			txtSoGiayTo.setPromptText("Nhập số giấy tờ");
			String regexSoGiayTo = "^[0-9]+$";
			txtSoGiayTo.setOnAction(event -> {
				String input = txtSoGiayTo.getText();
				if (!input.matches(regexSoGiayTo)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Số giấy tờ không hợp lệ");
					// Gắn vào Stage hiện tại (cực kỳ quan trọng)
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);

					// Không cho resize, luôn ở giữa cửa sổ cha
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}
			});
			txtSoGiayTo.setStyle(rightStyle);
			txtSoGiayTo.setMaxWidth(Double.MAX_VALUE);
			txtSoGiayTo.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(txtSoGiayTo, Pos.CENTER);
			right.getChildren().add(txtSoGiayTo);
			listTxtSoGiayTo.add(txtSoGiayTo);
		}
		left.setPrefWidth(100);
		right.setPrefWidth(200);
		left.setStyle(leftStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		return new HBox(left, right);
	}

	private HBox taoSubCT1(String label, String value, String leftStyle, String rightStyle, int check,
			double giaTienGhe, Label lblGiamDoiTuongValue, Label lblThanhTienValue, GheTrenChuyenTau gtc) {
		StackPane left = new StackPane(new Label(label));
		StackPane right = new StackPane();
		right.setPrefSize(200, 40);

		if (check == 2) {
            ComboBox<String> cmbDoiTuong = new ComboBox<>();
			cmbDoiTuong.setPromptText("Chọn đối tượng");

			ArrayList<String> listTenDoiTuong = new ArrayList<>();
			for (DoiTuongGiamGia doiTuong : listDoiTuongGiamGia) {
				listTenDoiTuong.add(doiTuong.getTenDoiTuongGiamGia());
			}
			cmbDoiTuong.getItems().addAll(listTenDoiTuong);
			cmbDoiTuong.setStyle(rightStyle);
			cmbDoiTuong.setMaxWidth(Double.MAX_VALUE);
			cmbDoiTuong.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(cmbDoiTuong, Pos.CENTER);

			cmbDoiTuong.setOnAction(event -> {
				String tenDoiTuong = cmbDoiTuong.getValue();
				if (tenDoiTuong == null)
					return;
                DoiTuongGiamGia doiTuong = null;
				for(DoiTuongGiamGia dt :  listDoiTuongGiamGia) {
                    if(dt.getTenDoiTuongGiamGia().equals(tenDoiTuong)) {
                        doiTuong = dt;
                    }
                }
                    System.out.println("click combobox");
					double phanTramGiam = doiTuong.getGiaTriPhanTramGiamGia();
					double tienGiam = giaTienGhe * phanTramGiam / 100.0;
					double thanhTienMoi = giaTienGhe - tienGiam;
					mapTienGhe.put(gtc, thanhTienMoi);
					tongCongThanhTien = 0;
					for (Double gia : mapTienGhe.values()) {
						tongCongThanhTien += gia;
					}
					lblTrangThaiApDung.setText("");
					loadLableTongCongValue(tongCongThanhTien);
                    String lblgdt = tienGiam + "(" +phanTramGiam + "% )";
					lblGiamDoiTuongValue.setText(lblgdt);
					lblThanhTienValue.setText(String.format("%.0f", thanhTienMoi));


			});

			right.getChildren().add(cmbDoiTuong);
			listCmbDoiTuong.add(cmbDoiTuong);
		}

		left.setPrefWidth(100);
		right.setPrefWidth(200);
		left.setStyle(leftStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);

		return new HBox(left, right);
	}

	private VBox taoSubCT2(String title, double value, String labelStyle, String valueStyle) {
		Label lblTitle = new Label(title);
		lblTitle.setStyle(labelStyle);
		Label lblValue = new Label("" + value);
		lblValue.setStyle(valueStyle);
		VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
		box.setAlignment(Pos.CENTER);
		return box;
	}

	public void loadLableTongCongValue(double tongCongThanhTien) {
		lblTongCongValue.setText(nf.format(tongCongThanhTien));
	}

	public static void main(String[] args) {
		launch(args);
//		Application.launch(TrangChu.class, args);
	}
}