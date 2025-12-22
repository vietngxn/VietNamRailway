package fourcore.GiaoDien;


import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import fourcore.Entity.ChucVu;
import fourcore.Entity.KhachHang;
import fourcore.Entity.NhanVien;
import fourcore.Entity.TaiKhoan;
import fourcore.animation.Animation;
import fourcore.dao.KhachHangDAO;
import fourcore.dao.NhanVienDAO;
import fourcore.dao.TaiKhoanDAO;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ThemNhanVien extends Application {
	public static void main(String[] args) {
		launch(args);
//		Application.launch(ThemChuongTrinhKhuyenMai.class, args);
	}
	private Stage window;
	private VBox layoutThemNhanVIen;
	private Scene sceneThemCTKM;
	private Label lblThemKH;
	private GridPane gridThemKH;
	private Label lblMaCT;
	private TextField txtMaCT;
	private Label lblTenCT;
	private TextField txtTenNhanVien;
	private StackPane spMaCT;
	private StackPane spTenCT;
	private TextField ngayBatDau;
	private Label lblNgayBatDau;
	private TextField txtSoDienThoai;
	private Button buttonNgayBatDau;
	private StackPane spNgayBatDau;
	private VBox vboxNgayBatDau;
	private TextField txtemail;
	private Label lblNgayKetThuc;
	private TextField txtNgayKetThuc;
	private Button buttonNgayKetThuc;
	private StackPane spNgayKetThuc;
	private VBox vboxNgayKetThuc;
	private ComboBox comboDoiTuong;
	private Button buttoncomboDoiTuong;
	private TextField txtcomboDoiTuong;
	private StackPane spcomboDoiTuong;
	private Label lblcomboDoiTuong;
	private HBox buttonThemCTKMBox;
	private Button buttonThem;
	
	private Animation lblAnimation;
	private VBox menuList;
	private ImageView logoImgView;
	private Node noiDungWrapper;
	private ScrollPane scrollPaneMenu;
	private VBox danhSachMenuItem;
	private HBox quanLiVeTauMenu;
	private ImageView quanLiVeTauIconView;
	private Label quanLiVeTauLabel;
	private ImageView showMenuPhuIconView;
	private HBox doiVeBox;
	private HBox banVeBox;
		private HBox hoanVeBox;
		private HBox capVeBox;
	private HBox xemLichSuVeBox;
	private HBox quanLiKhachHangMenu;
	private ImageView quanLiKhachHangIconView;
	private Label quanLiKhachHangLabel;
	private HBox quanLiHoaDonMenu;
	private ImageView quanLiHoaDonIconView;
	private Label quanLiHoaDonLabel;
	private HBox quanLiNhanVienMenu;
	private ImageView quanLiNhanVienIconView;
	private HBox quanLiCTKMMenu;
	private ImageView quanLiCTKMIconView;
	private HBox quanLiChuyenTauMenu;
	private ImageView quanLiChuyenTauIconView;
	private ImageView userIcon;
	private Labeled userLabel;
	private ImageView settingIcon;
	private KhachHangDAO khdao;
	
	private TextField txtPassport;
	
	private VBox layoutCapNhatCTKM;
	
	private Label lblCapNhatCTKM;
	private GridPane gridCapNhatNhanVien;

	private Label lblTenNhanVien;
	
	private StackPane spTenNhanVien;

	private Label lblSoDienThoai;
	
	private StackPane spSoDienThoai;

	private Label lblEmail;
	private TextField txtEmail;
	private StackPane spEmail;

	private Label lblGioiTinh;
	private TextField txtGioiTinh;
	private ComboBox<String> comboGioiTinh;
	private StackPane spGioiTinh;

	private Label lblCCCD;
	private TextField txtCCCD;
	private StackPane spCCCD;

	private Label lblTinhTrangLamViec;
	private TextField txtTinhTrangLamViec;
	private ComboBox<String> comboTinhTrangLamViec;
	private StackPane spTinhTrangLamViec;

	private HBox buttonCapNhatCTKMBox;
	private Button buttoncapNhat;
	private Button buttonThoat;
	private TextField txtChucVu;
	private TextField txtDiaChi;
	private DatePicker ngayVaoLam;
	private DatePicker ngaySinh;
	private NhanVienDAO nvdao;
	private ArrayList<NhanVien> listNhanVien;
	private ArrayList<String> listChucVu;
	private TaiKhoanDAO tkdao;
	private int soluongnv;


    
	@Override
	public void start(Stage primaryStage) throws Exception {
		khdao = new KhachHangDAO();
		nvdao = new NhanVienDAO();
		tkdao = new TaiKhoanDAO();
		soluongnv = tkdao.getSoLuongNhanVien();
		listNhanVien = nvdao.getListNhanVien();
		listChucVu = nvdao.getListChucVu();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblAnimation = new Animation();
		
		window = primaryStage;
		
		
		
        BorderPane root = new BorderPane();
        
        
        menuList = new VBox();
        menuList.setStyle("-fx-background-color: #F7F7F7;");
        menuList.setPrefWidth(500);

        logoImgView = new ImageView(getClass().getResource("/img/logov2.png").toExternalForm());
        logoImgView.setFitWidth(500);
        logoImgView.setFitHeight(270);
        menuList.getChildren().add(logoImgView);

        logoImgView.setOnMouseClicked(event -> {
            root.setCenter(noiDungWrapper);
        });

        scrollPaneMenu = new ScrollPane();
        danhSachMenuItem = new VBox();
//        Font labelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf"),20);

//		======================
//		||	QUAN LI VE TAU	||
//		======================
        quanLiVeTauMenu = new HBox();
        quanLiVeTauMenu.setSpacing(102);
        quanLiVeTauMenu.setPadding(new Insets(20, 95, 20, 20));
        quanLiVeTauMenu.setStyle("-fx-alignment: center-left;");


//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiVeTauIconView = new ImageView(getClass().getResource("/img/ticket.png").toExternalForm());
        quanLiVeTauIconView.setFitWidth(30);
        quanLiVeTauIconView.setFitHeight(30);
        quanLiVeTauIconView.setTranslateX(20);

        quanLiVeTauLabel = new Label("Vé tàu                     ");

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

//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
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
        xemLichSuVeBox = new HBox();

        Label banVeLabel = new Label("Bán vé");
        Label doiVeLabel = new Label("Đổi vé");
        Label hoanVeLabel = new Label("Hoàn vé");
        Label capVeLabel = new Label("Cấp vé");
        Label xemLichSuVeLabel = new Label("Lịch sử mua bán vé");

        banVeBox.getChildren().add(banVeLabel);
        doiVeBox.getChildren().add(doiVeLabel);
        hoanVeBox.getChildren().add(hoanVeLabel);
        capVeBox.getChildren().add(capVeLabel);
        xemLichSuVeBox.getChildren().add(xemLichSuVeLabel);


        InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
        Font labelMenuPhu = Font.loadFont(interSemiBold,15);

        for (Label label : new Label[]{banVeLabel, doiVeLabel, hoanVeLabel,capVeLabel,xemLichSuVeLabel}) {
            label.setStyle("-fx-background-color: #D2EEF0;");
            label.setTranslateY(-10);
            label.setFont(labelMenuPhu);
            label.setPadding(new Insets(12, 320, 12, 155));
            label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
            label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));
            
        }
        for (HBox hbox : new HBox[]{banVeBox, doiVeBox, hoanVeBox,capVeBox,xemLichSuVeBox}) {
            hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
            hbox.setPadding(new Insets(0,50,0,0));
            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
        }

        menuPhuQuanLiVeTau.getChildren().addAll(banVeBox, doiVeBox, hoanVeBox,capVeBox, xemLichSuVeBox);
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


//			======================
//			||QUAN LI KHACH HANG||
//			======================
        quanLiKhachHangMenu = new HBox();
        quanLiKhachHangMenu.setSpacing(102);
        quanLiKhachHangMenu.setPadding(new Insets(15, 95, 15, 20));
        quanLiKhachHangMenu.setStyle("-fx-alignment: center-left;");


//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiKhachHangIconView = new ImageView(getClass().getResource("/img/user-group.png").toExternalForm());
        quanLiKhachHangIconView.setFitWidth(30);
        quanLiKhachHangIconView.setFitHeight(30);
        quanLiKhachHangIconView.setTranslateX(20);

        quanLiKhachHangLabel = new Label("Khách hàng         ");

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

		ImageView showMenuPhuIcon = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
        showMenuPhuIcon.setFitWidth(20);
        showMenuPhuIcon.setFitHeight(20);
        showMenuPhuIcon.setTranslateX(50);
        quanLiKhachHangMenu.getChildren().addAll(quanLiKhachHangIconView, quanLiKhachHangLabel, showMenuPhuIcon);

        danhSachMenuItem.getChildren().add(quanLiKhachHangMenu);
        VBox menuPhuQuanLiKhachHang = new VBox();
        menuPhuQuanLiKhachHang.setSpacing(10);
        menuPhuQuanLiKhachHang.setPadding(new Insets(0, 40, 0, 0));
        menuPhuQuanLiKhachHang.setVisible(false);
        menuPhuQuanLiKhachHang.setManaged(false);
        menuPhuQuanLiKhachHang.setStyle("-fx-background-color: #D2EEF0;");
        HBox quanLiKhachHangBox = new HBox();
        HBox thongKeKhachHang = new HBox();


        Label quanLiKhachHangLabel = new Label("Quản lí khách hàng");
        Label thongKeKhachHangLabel = new Label("Thống kê top khách hàng");


        quanLiKhachHangBox.getChildren().add(quanLiKhachHangLabel);
        thongKeKhachHang.getChildren().add(thongKeKhachHangLabel);


//
//        InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//        Font labelMenuPhu = Font.loadFont(interSemiBold,15);

        for (Label label : new Label[]{quanLiKhachHangLabel, thongKeKhachHangLabel}) {
            label.setStyle("-fx-background-color: #D2EEF0;");
            label.setTranslateY(-10);
            label.setFont(labelMenuPhu);
            label.setPadding(new Insets(12, 320, 12, 155));
            label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
            label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

        }
        for (HBox hbox : new HBox[]{quanLiKhachHangBox, thongKeKhachHang}) {
            hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
            hbox.setPadding(new Insets(0,50,0,0));
            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
        }

        menuPhuQuanLiKhachHang.getChildren().addAll(quanLiKhachHangBox, thongKeKhachHang);
        danhSachMenuItem.getChildren().add(menuPhuQuanLiKhachHang);

        // Sự kiện onclick vào menu
        quanLiKhachHangMenu.setOnMouseClicked(event -> {
            boolean isVisible = menuPhuQuanLiKhachHang.isVisible();
            menuPhuQuanLiKhachHang.setVisible(!isVisible);
            menuPhuQuanLiKhachHang.setManaged(!isVisible);
            if (isVisible == false) {
                quanLiKhachHangMenu.setStyle(" -fx-background-color: #79D9E1;");
            }
            else {
                quanLiKhachHangMenu.setStyle(" -fx-background-color: #F7F7F7;");



            }
            TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiKhachHang);
            if (!isVisible) {
                menuPhuQuanLiKhachHang.setVisible(true);
                menuPhuQuanLiKhachHang.setManaged(true);
                menuPhuQuanLiKhachHang.setTranslateY(-20); // bắt đầu từ trên
                slide.setFromY(-20);
                slide.setToY(0);
                quanLiKhachHangMenu.setStyle("-fx-background-color: #79D9E1;");
            } else {
                slide.setFromY(0);
                slide.setToY(-20);
                slide.setOnFinished(e -> {
                    menuPhuQuanLiKhachHang.setVisible(false);
                    menuPhuQuanLiKhachHang.setManaged(false);
                });
                quanLiKhachHangMenu.setStyle("-fx-background-color: #F7F7F7;");
            }
            slide.play();




        });


//		======================
//		||QUAN LI HOA DON   ||
//		======================
        quanLiHoaDonMenu = new HBox();
        quanLiHoaDonMenu.setSpacing(102);
        quanLiHoaDonMenu.setPadding(new Insets(15, 95, 15, 20));
        quanLiHoaDonMenu.setStyle("-fx-alignment: center-left;");


//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiHoaDonIconView = new ImageView(getClass().getResource("/img/receipt-tax.png").toExternalForm());
        quanLiHoaDonIconView.setFitWidth(30);
        quanLiHoaDonIconView.setFitHeight(30);
        quanLiHoaDonIconView.setTranslateX(20);

        quanLiHoaDonLabel = new Label("Hóa đơn                 ");

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

        ImageView showMenuPhuIcon2 = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
        showMenuPhuIcon2.setFitWidth(20);
        showMenuPhuIcon2.setFitHeight(20);
        showMenuPhuIcon2.setTranslateX(50);
        quanLiHoaDonMenu.getChildren().addAll(quanLiHoaDonIconView, quanLiHoaDonLabel, showMenuPhuIcon2);


        danhSachMenuItem.getChildren().add(quanLiHoaDonMenu);

        VBox menuPhuQuanLiHoaDon = new VBox();
        menuPhuQuanLiHoaDon.setSpacing(10);
        menuPhuQuanLiHoaDon.setPadding(new Insets(0, 40, 0, 0));
        menuPhuQuanLiHoaDon.setVisible(false);
        menuPhuQuanLiHoaDon.setManaged(false);
        menuPhuQuanLiHoaDon.setStyle("-fx-background-color: #D2EEF0;");
        HBox quanLiHoaDonBox = new HBox();
        HBox thongKeDoanhThuBox = new HBox();


        Label quanLiHoaDonLabel = new Label("Quản lí hóa đơn");
        Label thongKeDoanhThuTheoNamLabel = new Label("Thống kê doanh thu theo năm");


        quanLiHoaDonBox.getChildren().add(quanLiHoaDonLabel);
        thongKeDoanhThuBox.getChildren().add(thongKeDoanhThuTheoNamLabel);


//
//        InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//        Font labelMenuPhu = Font.loadFont(interSemiBold,15);

        for (Label label : new Label[]{quanLiHoaDonLabel, thongKeDoanhThuTheoNamLabel}) {
            label.setStyle("-fx-background-color: #D2EEF0;");
            label.setTranslateY(-10);
            label.setFont(labelMenuPhu);
            label.setPadding(new Insets(12, 320, 12, 155));
            label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
            label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

        }
        for (HBox hbox : new HBox[]{quanLiHoaDonBox, thongKeDoanhThuBox}) {
            hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
            hbox.setPadding(new Insets(0,50,0,0));
            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
        }

        menuPhuQuanLiHoaDon.getChildren().addAll(quanLiHoaDonBox, thongKeDoanhThuBox);
        danhSachMenuItem.getChildren().add(menuPhuQuanLiHoaDon);


        // Sự kiện onclick vào menu
        quanLiHoaDonMenu.setOnMouseClicked(event -> {
            boolean isVisible = menuPhuQuanLiHoaDon.isVisible();
            menuPhuQuanLiHoaDon.setVisible(!isVisible);
            menuPhuQuanLiHoaDon.setManaged(!isVisible);
            if (isVisible == false) {
                menuPhuQuanLiHoaDon.setStyle(" -fx-background-color: #79D9E1;");
            }
            else {
                menuPhuQuanLiHoaDon.setStyle(" -fx-background-color: #F7F7F7;");



            }
            TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiHoaDon);
            if (!isVisible) {
                menuPhuQuanLiHoaDon.setVisible(true);
                menuPhuQuanLiHoaDon.setManaged(true);
                menuPhuQuanLiHoaDon.setTranslateY(-20); // bắt đầu từ trên
                slide.setFromY(-20);
                slide.setToY(0);
                quanLiHoaDonMenu.setStyle("-fx-background-color: #79D9E1;");
            } else {
                slide.setFromY(0);
                slide.setToY(-20);
                slide.setOnFinished(e -> {
                    menuPhuQuanLiHoaDon.setVisible(false);
                    menuPhuQuanLiHoaDon.setManaged(false);
                });
                quanLiHoaDonMenu.setStyle("-fx-background-color: #F7F7F7;");
            }
            slide.play();




        });

//		=======================
//		||QUAN LI NHAN VIEN  ||
//		=======================
        quanLiNhanVienMenu = new HBox();
        quanLiNhanVienMenu.setSpacing(102);
        quanLiNhanVienMenu.setPadding(new Insets(15, 95, 15, 20));
        quanLiNhanVienMenu.setStyle("-fx-alignment: center-left;");


//		quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiNhanVienIconView = new ImageView(getClass().getResource("/img/user-circle.png").toExternalForm());
        quanLiNhanVienIconView.setFitWidth(30);
        quanLiNhanVienIconView.setFitHeight(30);
        quanLiNhanVienIconView.setTranslateX(20);

        Label quanLiNhanVienLabel = new Label("Nhân viên");

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

//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
        quanLiNhanVienMenu.getChildren().addAll(quanLiNhanVienIconView, quanLiNhanVienLabel);


        danhSachMenuItem.getChildren().add(quanLiNhanVienMenu);
        scrollPaneMenu.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneMenu.setContent(danhSachMenuItem);



//		=======================
//		||QUAN LI CTKM        ||
//		=======================
        quanLiCTKMMenu = new HBox();
        quanLiCTKMMenu.setSpacing(102);
        quanLiCTKMMenu.setPadding(new Insets(15, 95, 15, 20));
        quanLiCTKMMenu.setStyle("-fx-alignment: center-left;");

//	quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiCTKMIconView = new ImageView(getClass().getResource("/img/tag.png").toExternalForm());
        quanLiCTKMIconView.setFitWidth(30);
        quanLiCTKMIconView.setFitHeight(30);
        quanLiCTKMIconView.setTranslateX(20);

        Label quanLiCTKMLabel = new Label("Khuyến mãi");

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

//	showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
        quanLiCTKMMenu.getChildren().addAll(quanLiCTKMIconView, quanLiCTKMLabel);

        danhSachMenuItem.getChildren().add(quanLiCTKMMenu);
        scrollPaneMenu.setContent(danhSachMenuItem);


//		=======================
//		||QUAN LI CHUYEN TAU ||
//		=======================
        quanLiChuyenTauMenu = new HBox();
        quanLiChuyenTauMenu.setSpacing(102);
        quanLiChuyenTauMenu.setPadding(new Insets(15, 95, 15, 20));
        quanLiChuyenTauMenu.setStyle("-fx-alignment: center-left;");

//	quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
        quanLiChuyenTauIconView = new ImageView(getClass().getResource("/img/clipboard-check.png").toExternalForm());
        quanLiChuyenTauIconView.setFitWidth(30);
        quanLiChuyenTauIconView.setFitHeight(30);
        quanLiChuyenTauIconView.setTranslateX(20);

        Label quanLiChuyenTauLabel = new Label("Chuyến tàu         ");

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
        ImageView showMenuPhuIcon1 = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
        showMenuPhuIcon1.setFitWidth(20);
        showMenuPhuIcon1.setFitHeight(20);
        showMenuPhuIcon1.setTranslateX(50);
//	showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
        quanLiChuyenTauMenu.getChildren().addAll(quanLiChuyenTauIconView, quanLiChuyenTauLabel, showMenuPhuIcon1);



        danhSachMenuItem.getChildren().add(quanLiChuyenTauMenu);
        VBox menuPhuQuanLiChuyenTau = new VBox();
        menuPhuQuanLiChuyenTau.setSpacing(10);
        menuPhuQuanLiChuyenTau.setPadding(new Insets(0, 40, 0, 0));
        menuPhuQuanLiChuyenTau.setVisible(false);
        menuPhuQuanLiChuyenTau.setManaged(false);
        menuPhuQuanLiChuyenTau.setStyle("-fx-background-color: #D2EEF0;");
        HBox quanLiChuyenTauBox = new HBox();
        HBox thongKeLoaiGhe = new HBox();


        Label quanLiChuyenTauuLabel = new Label("Quản lí chuyến tàu");
        Label thongKeLoaiTauBest = new Label("Thống kê loại tàu được sử dụng nhiều nhất");


        quanLiChuyenTauBox.getChildren().add(quanLiChuyenTauuLabel);
        thongKeLoaiGhe.getChildren().add(thongKeLoaiTauBest);


//
//        InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//        Font labelMenuPhu = Font.loadFont(interSemiBold,15);

        for (Label label : new Label[]{quanLiChuyenTauuLabel, thongKeLoaiTauBest}) {
            label.setStyle("-fx-background-color: #D2EEF0;");
            label.setTranslateY(-10);
            label.setFont(labelMenuPhu);
            label.setPadding(new Insets(12, 320, 12, 155));
            label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
            label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

        }
        for (HBox hbox : new HBox[]{quanLiChuyenTauBox, thongKeLoaiGhe}) {
            hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
            hbox.setPadding(new Insets(0,50,0,0));
            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
        }

        menuPhuQuanLiChuyenTau.getChildren().addAll(quanLiChuyenTauBox, thongKeLoaiGhe);
        danhSachMenuItem.getChildren().add(menuPhuQuanLiChuyenTau);

        // Sự kiện onclick vào menu
        quanLiChuyenTauMenu.setOnMouseClicked(event -> {
                    boolean isVisible = menuPhuQuanLiChuyenTau.isVisible();
                    menuPhuQuanLiChuyenTau.setVisible(!isVisible);
                    menuPhuQuanLiChuyenTau.setManaged(!isVisible);
                    if (isVisible == false) {
                        quanLiChuyenTauMenu.setStyle(" -fx-background-color: #79D9E1;");
                    } else {
                        quanLiChuyenTauMenu.setStyle(" -fx-background-color: #F7F7F7;");

                    }
                    TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiChuyenTau);
                    if (!isVisible) {
                        menuPhuQuanLiChuyenTau.setVisible(true);
                        menuPhuQuanLiChuyenTau.setManaged(true);
                        menuPhuQuanLiChuyenTau.setTranslateY(-20); // bắt đầu từ trên
                        slide.setFromY(-20);
                        slide.setToY(0);
                        quanLiChuyenTauMenu.setStyle("-fx-background-color: #79D9E1;");
                    } else {
                        slide.setFromY(0);
                        slide.setToY(-20);
                        slide.setOnFinished(e -> {
                            menuPhuQuanLiChuyenTau.setVisible(false);
                            menuPhuQuanLiChuyenTau.setManaged(false);
                        });
                        quanLiChuyenTauMenu.setStyle("-fx-background-color: #F7F7F7;");
                    }
                    slide.play();
                });


        scrollPaneMenu.setContent(danhSachMenuItem);

//----------------------------------------------------------------------------------------------------------------------
        scrollPaneMenu.setPrefHeight(600);
        scrollPaneMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scrollPaneMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        menuList.getChildren().add(scrollPaneMenu);
//      USER BOX


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
        userBox.setTranslateY(140);
        userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
        menuList.getChildren().add(userBox);
		
		
		
		
		
		
        layoutThemNhanVIen = new VBox();
		create_themchuongtrinhkm_layout();
		
		root.setLeft(menuList);
		root.setCenter(layoutThemNhanVIen);
		root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        
		
		sceneThemCTKM = new Scene(root, 900, 700);
		sceneThemCTKM.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
		
		buttonThem.setOnAction(e-> {
		    String ten = txtTenNhanVien.getText();
		    String chucvu = txtChucVu.getText();
		    LocalDate ngaysinh  = ngaySinh.getValue();
		    String diaChi = txtDiaChi.getText();
		    String email = txtemail.getText();
		    String cccd = txtCCCD.getText();	
		    String sdt = txtSoDienThoai.getText();
		    LocalDate ngayvaolam = ngayVaoLam.getValue();
//		    String passport = txtPassport.getText();
//		    String doiTuong = txtcomboDoiTuong.getText();
		    String gioiTinh = txtGioiTinh.getText();
		    
		    String regexten = "^[\\p{L} ]+$";
		    String regexsdt = "^0\\d{9}";
	        String regexcccd = "^0\\d{11}";
	        String regexemail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	        
		    
		    if(ten.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Tên không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(chucvu.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Chức vụ không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(ngaysinh == null)
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Vui lòng chọn ngày sinh");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(diaChi.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Địa chỉ không được để trống!");
		    	alert.setHeaderText(null); 
		    	alert.showAndWait();
		    	return;
		    }
		    else if(email.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Email không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(sdt.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Số điện thoại không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(ngayvaolam == null)
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Ngày vào làm Không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(gioiTinh.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Giới tính không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    else if(cccd.isEmpty())
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("CCCD không được để trống!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    
		    int year = LocalDate.now().getYear() - ngaysinh.getYear();
		    if(year < 18)
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setContentText("Nhân viên phải trên 18 tuổi!");
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
		    	return;
		    }
		    
		    
		    if(ngayvaolam.isBefore(LocalDate.now())) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setContentText("Ngày vào làm phải sau hoặc bằng ngày hiện tại!");
		        alert.setHeaderText(null);
		        alert.showAndWait();
		        return;
		    }
		    
		    if(!ten.matches(regexten))
		    {
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
	        	alert.setHeaderText(null);
	        	alert.setContentText("Tên sai định dạng!");
	        	alert.showAndWait();
	        	return;
		    }
		    else if(!sdt.matches(regexsdt)) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	        	alert.setHeaderText(null);
	        	alert.setContentText("Số điện thoại không đúng định dạng.SDT phải bao gồm 10 chữ số, bắt đầu bằng số 0 ");
	        	alert.showAndWait();
	        	return;
	        }
	        else if(!email.matches(regexemail))
	        {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	        	alert.setHeaderText(null);
	        	alert.setContentText("Email phải đúng định dạng : abc@gmail.com");
	        	alert.showAndWait();
	        	return;
	        }
	        else if(!cccd.matches(regexcccd))
	        {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	        	alert.setHeaderText(null);
	        	alert.setContentText("CCCD phải đúng định dạng : 0XXXXXXXXXXX");
	        	alert.showAndWait();
	        	return;
	        }
		    
		    
		    
		    if(chucvu.equalsIgnoreCase("nhân viên bán vé"))
		    {
		    	chucvu = "CV01";
		    }
		    else
		    {
		    	chucvu = "CV02";
		    }
		    
		    try {
		        // Lấy số lượng khách hàng hiện tại từ database
		        int sl = listNhanVien.size();
		        sl += 1; // Tăng lên 1 cho khách hàng mới
		        
		        String manv = "";
		        if(sl >= 1 && sl <= 9) {
		            manv = "NV0" + sl;
		        }
		        else {
		            manv = "NV" + sl;
		        }
		        
		        
		        
		        if(nvdao.checksdt(sdt))
		        {
		        	Alert alert = new Alert(Alert.AlertType.ERROR);
		        	alert.setContentText("SDT đã tồn tại");
		        	alert.setHeaderText(null);
		        	alert.showAndWait();
		        	return;
		        }
		        else if(nvdao.checkCCCD(cccd))
		        {
		        	Alert alert = new Alert(Alert.AlertType.ERROR);
		        	alert.setContentText("CCCD đã tồn tại");
		        	alert.setHeaderText(null);
		        	alert.showAndWait();
		        	return;
		        }
		        
		        
		        
		        NhanVien nv = new NhanVien(manv, ten, new ChucVu(chucvu), ngaysinh, diaChi, email, sdt, ngayvaolam, "còn làm", gioiTinh, cccd,true);
		        
		        if(nvdao.themNhanVien(nv)) {
		        	
		        	soluongnv +=1;
		        	String matk = "";
		        	if(soluongnv <= 9)
		        	{
		        		matk = "TK0"+soluongnv;
		        	}
		        	else
		        		matk = "TK"+soluongnv;
		        	
		        	TaiKhoan tk = new TaiKhoan(matk, manv, sdt, "123456",false);
		        	tkdao.themTaiKhoan(tk);
		            System.out.println("Thêm Thành Công\nTên đăng nhập của nhân viên là :"+sdt+"\nMật Khẩu : 123456");
		            
		            Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    	alert.setContentText("Thêm nhân viên "+ten+"\n"+"Tên đăng nhập của nhân viên là :"+sdt+"\nMật Khẩu : 123456");
			    	alert.setHeaderText(null);
			    	alert.showAndWait();
			    	
		            // Clear form sau khi thêm thành công
		            txtTenNhanVien.clear();
		            txtSoDienThoai.clear();
		            txtSoDienThoai.clear();
		            
		            txtemail.clear();
		            txtCCCD.clear();
//		            txtcomboDoiTuong.clear();
//		            comboDoiTuong.setValue(null);
		        }
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		});

		
		

		
		root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		
		window.setScene(sceneThemCTKM);
		window.setFullScreen(true);
//		window.show();
	}
    public VBox getThemKHLayout(){
        return layoutThemNhanVIen;
    }
    public void create_themchuongtrinhkm_layout() {
    	
    	//label đầu
    	lblThemKH = new Label("Thêm Nhân Viên Mới");
    	lblThemKH.setId("lbl_TieuDe");
    	
    	
    	//grid chứa các cái cần nhập
    	gridThemKH = new GridPane();
    	gridThemKH.setHgap(80);
    	gridThemKH.setVgap(50);
    	gridThemKH.setPadding(new Insets(70, 0, 0, 0));
    	gridThemKH.setAlignment(Pos.CENTER);
    	
    	
    	// Tên Nhân Viên
    	Label lblTenNhanVien = new Label("Tên Nhân Viên");
    	lblTenNhanVien.setId("lbl_TextField");
    	
    	txtTenNhanVien = new TextField();
    	txtTenNhanVien.setPrefWidth(600);
    	txtTenNhanVien.setPrefHeight(40);
    	txtTenNhanVien.setId("txt_CapNhatChuyenTau");
    	
    	StackPane spTenNhanVien = new StackPane();
    	spTenNhanVien.getChildren().addAll(lblTenNhanVien, txtTenNhanVien);
    	spTenNhanVien.setAlignment(lblTenNhanVien, Pos.CENTER_LEFT);
    	
    	txtTenNhanVien.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
    	    if (isNowFocused && txtTenNhanVien.getText().trim().isEmpty()) {
    	    	lblAnimation.scaleUp(lblTenNhanVien);
    	    } else {
    	    	if(txtTenNhanVien.getText().trim().isEmpty()) lblAnimation.scaleDown(lblTenNhanVien);
    	    }
    	});
    	
    	
    	// Chức Vụ
    	Label lblChucVu = new Label("Chức Vụ");
    	lblChucVu.setId("lbl_TextField");
    	
    	ComboBox<String> comboChucVu = new ComboBox<>();
    	for(String s : listChucVu)
    	{
    		comboChucVu.getItems().add(s);
    	}
    	comboChucVu.setPrefWidth(600);
    	comboChucVu.setPrefHeight(40);
    	comboChucVu.setStyle("-fx-font-size: 18px");
    	comboChucVu.setOpacity(0);
    	
    	txtChucVu = new TextField();
    	txtChucVu.setPrefWidth(600);
    	txtChucVu.setPrefHeight(40);
    	txtChucVu.setId("txt_CapNhatChuyenTau");
    	txtChucVu.setMouseTransparent(true);
    	txtChucVu.setFocusTraversable(false);
    	
    	Button buttonChucVu = new Button();
    	ImageView iconChucVu = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
    	iconChucVu.setFitWidth(30);
    	iconChucVu.setFitHeight(30);
    	buttonChucVu.setGraphic(iconChucVu);
    	buttonChucVu.setStyle("-fx-background-color: transparent");
    	
    	StackPane spChucVu = new StackPane();
    	spChucVu.getChildren().addAll(lblChucVu, txtChucVu, buttonChucVu, comboChucVu);
    	spChucVu.setAlignment(buttonChucVu, Pos.CENTER_RIGHT);
    	spChucVu.setAlignment(lblChucVu, Pos.CENTER_LEFT);
    	
    	comboChucVu.valueProperty().addListener((obs, oldVal, newVal) -> {
    		if(newVal != null) {
    			if(txtChucVu.getText().trim().isEmpty()) lblAnimation.scaleUp(lblChucVu);
    			txtChucVu.setText(newVal.toString());
    		}
    	});
    	
    	
    	// Ngày Sinh
    	ngaySinh = new DatePicker();
    	ngaySinh.setPrefWidth(30);
    	ngaySinh.setPrefHeight(40);
    	ngaySinh.setStyle("-fx-font-size: 18px");
    	ngaySinh.setOpacity(0);
    	
    	Label lblNgaySinh = new Label("Ngày Sinh");
    	lblNgaySinh.setId("lbl_TextField");
    	
    	ngayBatDau = new TextField();
    	ngayBatDau.setPrefWidth(600);
    	ngayBatDau.setPrefHeight(40);
    	ngayBatDau.setId("txt_CapNhatChuyenTau");
    	ngayBatDau.setMouseTransparent(true);
    	ngayBatDau.setFocusTraversable(false);
    	
    	Button buttonNgaySinh = new Button();
    	ImageView iconNgaySinh = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
    	iconNgaySinh.setFitWidth(30);
    	iconNgaySinh.setFitHeight(30);
    	buttonNgaySinh.setGraphic(iconNgaySinh);
    	buttonNgaySinh.setStyle("-fx-background-color: transparent");
    	buttonNgaySinh.setOnAction(e -> ngaySinh.show());
    	
    	StackPane spNgaySinh = new StackPane();
    	spNgaySinh.getChildren().addAll(lblNgaySinh, ngayBatDau, buttonNgaySinh, ngaySinh);
    	spNgaySinh.setAlignment(buttonNgaySinh, Pos.CENTER_RIGHT);
    	spNgaySinh.setAlignment(lblNgaySinh, Pos.CENTER_LEFT);
    	
    	ngaySinh.valueProperty().addListener((obs, oldVal, newVal) -> {
    		if(newVal != null) {
    			if(ngayBatDau.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgaySinh);
    			ngayBatDau.setText(newVal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    		}
    	});
    	
    	
    	// Địa Chỉ
    	Label lblDiaChi = new Label("Địa Chỉ");
    	lblDiaChi.setId("lbl_TextField");
    	
    	txtDiaChi = new TextField();
    	txtDiaChi.setPrefWidth(600);
    	txtDiaChi.setPrefHeight(40);
    	txtDiaChi.setId("txt_CapNhatChuyenTau");
    	
    	StackPane spDiaChi = new StackPane();
    	spDiaChi.getChildren().addAll(lblDiaChi, txtDiaChi);
    	spDiaChi.setAlignment(lblDiaChi, Pos.CENTER_LEFT);
    	
    	txtDiaChi.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
    	    if (isNowFocused && txtDiaChi.getText().trim().isEmpty()) {
    	    	lblAnimation.scaleUp(lblDiaChi);
    	    } else {
    	    	if(txtDiaChi.getText().trim().isEmpty()) lblAnimation.scaleDown(lblDiaChi);
    	    }
    	});
    	
    	
    	// Email
    	Label lblEmail = new Label("Email");
    	lblEmail.setId("lbl_TextField");
    	
    	txtemail = new TextField();
    	txtemail.setPrefWidth(600);
    	txtemail.setPrefHeight(40);
    	txtemail.setId("txt_CapNhatChuyenTau");
    	
    	StackPane spEmail = new StackPane();
    	spEmail.getChildren().addAll(lblEmail, txtemail);
    	spEmail.setAlignment(lblEmail, Pos.CENTER_LEFT);
    	
    	txtemail.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
    	    if (isNowFocused && txtemail.getText().trim().isEmpty()) {
    	    	lblAnimation.scaleUp(lblEmail);
    	    } else {
    	    	if(txtemail.getText().trim().isEmpty()) lblAnimation.scaleDown(lblEmail);
    	    }
    	});
    	
    	
    	// Số Điện Thoại
    	Label lblSoDienThoai = new Label("Số Điện Thoại");
    	lblSoDienThoai.setId("lbl_TextField");
    	
    	txtSoDienThoai = new TextField();
    	txtSoDienThoai.setPrefWidth(600);
    	txtSoDienThoai.setPrefHeight(40);
    	txtSoDienThoai.setId("txt_CapNhatChuyenTau");
    	
    	StackPane spSoDienThoai = new StackPane();
    	spSoDienThoai.getChildren().addAll(lblSoDienThoai, txtSoDienThoai);
    	spSoDienThoai.setAlignment(lblSoDienThoai, Pos.CENTER_LEFT);
    	
    	txtSoDienThoai.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
    	    if (isNowFocused && txtSoDienThoai.getText().trim().isEmpty()) {
    	    	lblAnimation.scaleUp(lblSoDienThoai);
    	    } else {
    	    	if(txtSoDienThoai.getText().trim().isEmpty()) lblAnimation.scaleDown(lblSoDienThoai);
    	    }
    	});
    	
    	
    	// Ngày Vào Làm
    	ngayVaoLam = new DatePicker();
    	ngayVaoLam.setPrefWidth(30);
    	ngayVaoLam.setPrefHeight(40);
    	ngayVaoLam.setStyle("-fx-font-size: 18px");
    	ngayVaoLam.setOpacity(0);
    	
    	Label lblNgayVaoLam = new Label("Ngày Vào Làm");
    	lblNgayVaoLam.setId("lbl_TextField");
    	
    	TextField txtNgayVaoLam = new TextField();
    	txtNgayVaoLam.setPrefWidth(600);
    	txtNgayVaoLam.setPrefHeight(40);
    	txtNgayVaoLam.setId("txt_CapNhatChuyenTau");
    	txtNgayVaoLam.setMouseTransparent(true);
    	txtNgayVaoLam.setFocusTraversable(false);
    	
    	Button buttonNgayVaoLam = new Button();
    	ImageView iconNgayVaoLam = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
    	iconNgayVaoLam.setFitWidth(30);
    	iconNgayVaoLam.setFitHeight(30);
    	buttonNgayVaoLam.setGraphic(iconNgayVaoLam);
    	buttonNgayVaoLam.setStyle("-fx-background-color: transparent");
    	buttonNgayVaoLam.setOnAction(e-> ngayVaoLam.show());
    	
    	StackPane spNgayVaoLam = new StackPane();
    	spNgayVaoLam.getChildren().addAll(lblNgayVaoLam, txtNgayVaoLam, buttonNgayVaoLam, ngayVaoLam);
    	spNgayVaoLam.setAlignment(buttonNgayVaoLam, Pos.CENTER_RIGHT);
    	spNgayVaoLam.setAlignment(lblNgayVaoLam, Pos.CENTER_LEFT);
    	
    	ngayVaoLam.valueProperty().addListener((obs, oldVal, newVal) -> {
    		if(newVal != null) {
    			if(txtNgayVaoLam.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgayVaoLam);
    			txtNgayVaoLam.setText(newVal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    		}
    	});
    	
    	
    	// Tình Trạng Làm Việc
    	Label lblTinhTrangLamViec = new Label("Tình Trạng Làm Việc");
    	lblTinhTrangLamViec.setId("lbl_TextField");
    	
    	ComboBox<String> comboTinhTrangLamViec = new ComboBox<>();
    	comboTinhTrangLamViec.getItems().addAll("Đang làm");
    	comboTinhTrangLamViec.setPrefWidth(600);
    	comboTinhTrangLamViec.setPrefHeight(40);
    	comboTinhTrangLamViec.setStyle("-fx-font-size: 18px");
    	comboTinhTrangLamViec.setOpacity(0);
    	
    	txtTinhTrangLamViec = new TextField();
    	
    	txtTinhTrangLamViec.setPrefWidth(600);
    	txtTinhTrangLamViec.setPrefHeight(40);
    	txtTinhTrangLamViec.setId("txt_CapNhatChuyenTau");
    	txtTinhTrangLamViec.setMouseTransparent(true);
    	txtTinhTrangLamViec.setFocusTraversable(false);
    	
    	Button buttonTinhTrangLamViec = new Button();
    	ImageView iconTinhTrangLamViec = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
    	iconTinhTrangLamViec.setFitWidth(30);
    	iconTinhTrangLamViec.setFitHeight(30);
    	buttonTinhTrangLamViec.setGraphic(iconTinhTrangLamViec);
    	buttonTinhTrangLamViec.setStyle("-fx-background-color: transparent");
    	
    	StackPane spTinhTrangLamViec = new StackPane();
    	spTinhTrangLamViec.getChildren().addAll(lblTinhTrangLamViec, txtTinhTrangLamViec, buttonTinhTrangLamViec, comboTinhTrangLamViec);
    	spTinhTrangLamViec.setAlignment(buttonTinhTrangLamViec, Pos.CENTER_RIGHT);
    	spTinhTrangLamViec.setAlignment(lblTinhTrangLamViec, Pos.CENTER_LEFT);
    	
    	comboTinhTrangLamViec.valueProperty().addListener((obs, oldVal, newVal) -> {
    		if(newVal != null) {
    			if(txtTinhTrangLamViec.getText().trim().isEmpty()) lblAnimation.scaleUp(lblTinhTrangLamViec);
    			txtTinhTrangLamViec.setText(newVal.toString());
    		}
    	});
    	
    	
    	// Giới Tính
    	Label lblGioiTinh = new Label("Giới Tính");
    	lblGioiTinh.setId("lbl_TextField");
    	
    	ComboBox<String> comboGioiTinh = new ComboBox<>();
    	comboGioiTinh.getItems().addAll("Nam", "Nữ");
    	comboGioiTinh.setPrefWidth(600);
    	comboGioiTinh.setPrefHeight(40);
    	comboGioiTinh.setStyle("-fx-font-size: 18px");
    	comboGioiTinh.setOpacity(0);
    	
    	txtGioiTinh = new TextField();
    	txtGioiTinh.setPrefWidth(600);
    	txtGioiTinh.setPrefHeight(40);
    	txtGioiTinh.setId("txt_CapNhatChuyenTau");
    	txtGioiTinh.setMouseTransparent(true);
    	txtGioiTinh.setFocusTraversable(false);
    	
    	Button buttonGioiTinh = new Button();
    	ImageView iconGioiTinh = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
    	iconGioiTinh.setFitWidth(30);
    	iconGioiTinh.setFitHeight(30);
    	buttonGioiTinh.setGraphic(iconGioiTinh);
    	buttonGioiTinh.setStyle("-fx-background-color: transparent");
    	
    	StackPane spGioiTinh = new StackPane();
    	spGioiTinh.getChildren().addAll(lblGioiTinh, txtGioiTinh, buttonGioiTinh, comboGioiTinh);
    	spGioiTinh.setAlignment(buttonGioiTinh, Pos.CENTER_RIGHT);
    	spGioiTinh.setAlignment(lblGioiTinh, Pos.CENTER_LEFT);
    	
    	comboGioiTinh.valueProperty().addListener((obs, oldVal, newVal) -> {
    		if(newVal != null) {
    			if(txtGioiTinh.getText().trim().isEmpty()) lblAnimation.scaleUp(lblGioiTinh);
    			txtGioiTinh.setText(newVal.toString());
    		}
    	});
    	
    	
    	// CCCD
    	Label lblCCCD = new Label("CCCD");
    	lblCCCD.setId("lbl_TextField");
    	
    	txtCCCD = new TextField();
    	txtCCCD.setPrefWidth(600);
    	txtCCCD.setPrefHeight(40);
    	txtCCCD.setId("txt_CapNhatChuyenTau");
    	
    	StackPane spCCCD = new StackPane();
    	spCCCD.getChildren().addAll(lblCCCD, txtCCCD);
    	spCCCD.setAlignment(lblCCCD, Pos.CENTER_LEFT);
    	
    	txtCCCD.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
    	    if (isNowFocused && txtCCCD.getText().trim().isEmpty()) {
    	    	lblAnimation.scaleUp(lblCCCD);
    	    } else {
    	    	if(txtCCCD.getText().trim().isEmpty()) lblAnimation.scaleDown(lblCCCD);
    	    }
    	});
    	
    	
    	// Nút hành động
    	buttonThemCTKMBox = new HBox(10);
    	buttonThemCTKMBox.setMaxWidth(1600);
    	
    	buttonThem = new Button();
    	buttonThem.setText("Thêm Nhân Viên");
    	buttonThem.setPrefWidth(300);
    	buttonThem.setPrefHeight(50);
    	buttonThem.setId("button_Blue");
    	
    	buttonThoat = new Button();
    	buttonThoat.setText("Thoát");
    	buttonThoat.setPrefWidth(150);
    	buttonThoat.setPrefHeight(50);
    	buttonThoat.setId("button_White");
    	
    	buttonThoat.setOnAction(e -> window.close());
    	
    	buttonThemCTKMBox.getChildren().addAll(buttonThem, buttonThoat);
    	buttonThemCTKMBox.setAlignment(Pos.BOTTOM_RIGHT);
    	buttonThemCTKMBox.setPadding(new Insets(200, 40, 20, 0));
    	
    	
    	// Thêm vào grid (5 hàng, 2 cột)
    	gridThemKH.add(spTenNhanVien, 0, 0);
    	gridThemKH.add(spChucVu, 1, 0);
    	gridThemKH.add(spNgaySinh, 0, 1);
    	gridThemKH.add(spDiaChi, 1, 1);
    	gridThemKH.add(spEmail, 0, 2);
    	gridThemKH.add(spSoDienThoai, 1, 2);
    	gridThemKH.add(spNgayVaoLam, 0, 3);
//    	gridThemKH.add(spTinhTrangLamViec, 1, 3);
    	gridThemKH.add(spGioiTinh, 1, 3);
    	gridThemKH.add(spCCCD, 0, 4);
    	
    	
    	layoutThemNhanVIen.getChildren().addAll(lblThemKH, gridThemKH, buttonThemCTKMBox);
    	layoutThemNhanVIen.setAlignment(Pos.CENTER);
    	layoutThemNhanVIen.setStyle("-fx-background-color: #FFFFFF");
    }
    
    public VBox getLayoutThemNhanVIen(){
        return layoutThemNhanVIen;
    }
    
    public Button getButtonThoat()
    {
    	return buttonThoat;
    }
}
