package fourcore.GiaoDien;


import java.io.InputStream;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import fourcore.Entity.KhachHang;
import fourcore.animation.Animation;
import fourcore.dao.KhachHangDAO;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class ThemKhachHang extends Application {
	public static void main(String[] args) {
		launch(args);
//		Application.launch(ThemChuongTrinhKhuyenMai.class, args);
	}
	private Stage window;
	private VBox layoutThemCTKM;
	private Scene sceneThemCTKM;
	private Label lblThemKH;
	private GridPane gridThemKH;
	private Label lblMaCT;
	private TextField txtMaCT;
	private Label lblTenCT;
	private TextField txtTenKH;
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
	private Button buttonThoat;
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
	private KhachHangDAO khdao = new KhachHangDAO();
	private TextField txtCCCD;
	private TextField txtPassport;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		KhachHangDAO khdao = new KhachHangDAO();
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
		
		
		
		
		
		
        layoutThemCTKM = new VBox();
		create_themchuongtrinhkm_layout();
		
		root.setLeft(menuList);
		root.setCenter(layoutThemCTKM);
		root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        
		
		sceneThemCTKM = new Scene(root, 900, 700);
		sceneThemCTKM.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
		
		buttonThem.setOnAction(e-> {
		    String ten = txtTenKH.getText();
		    String sdt = txtSoDienThoai.getText();
		    String email = txtemail.getText();
		    String cccd = txtCCCD.getText();	
		    String passport = txtPassport.getText();
		    String doiTuong = txtcomboDoiTuong.getText();
		    
		    try {
		        // Lấy số lượng khách hàng hiện tại từ database
		        int sl = khdao.getListKhachHang().size();
		        sl += 1; // Tăng lên 1 cho khách hàng mới
		        
		        String makh = "";
		        if(sl >= 1 && sl <= 9) {
		            makh = "KH00" + sl;
		        }
		        else if(sl >= 10 && sl <= 99) {
		            makh = "KH0" + sl;
		        }
		        else {
		            makh = "KH" + sl;
		        }
		        
		        KhachHang kh = new KhachHang(makh, ten, sdt, email, cccd, passport, doiTuong);
		        
		        if(khdao.themKhachHang(kh)) {
		            System.out.println("Thêm Thành Công - Mã KH: " + makh);
		            // Clear form sau khi thêm thành công
		            txtTenKH.clear();
		            txtSoDienThoai.clear();
		            txtemail.clear();
		            txtCCCD.clear();
		            txtPassport.clear();
		            txtcomboDoiTuong.clear();
		            comboDoiTuong.setValue(null);
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
        return layoutThemCTKM;
    }
	public void create_themchuongtrinhkm_layout() {
		
		//label đầu
		lblThemKH = new Label("Thêm Khách Hàng");
		lblThemKH.setId("lbl_TieuDe");
		
		//grid chứa các cái cần nhập
		gridThemKH = new GridPane();
		gridThemKH.setHgap(80);
		gridThemKH.setVgap(50);
		gridThemKH.setPadding(new Insets(70, 0, 0, 0));
		gridThemKH.setAlignment(Pos.CENTER);
		
		// Họ Tên
		lblTenCT = new Label("Họ Tên");
		lblTenCT.setId("lbl_TextField");
		
		txtTenKH = new TextField();
		txtTenKH.setPrefWidth(600);
		txtTenKH.setPrefHeight(40);
		txtTenKH.setId("txt_CapNhatChuyenTau");
		
		spTenCT = new StackPane();
		spTenCT.getChildren().addAll(lblTenCT, txtTenKH);
		spTenCT.setAlignment(lblTenCT, Pos.CENTER_LEFT);
		
		// Số Điện Thoại
		lblNgayBatDau = new Label("Số Điện Thoại");
		lblNgayBatDau.setId("lbl_TextField");
		
		txtSoDienThoai = new TextField();
		txtSoDienThoai.setPrefWidth(600);
		txtSoDienThoai.setPrefHeight(40);
		txtSoDienThoai.setId("txt_CapNhatChuyenTau");
		
		spNgayBatDau = new StackPane();
		spNgayBatDau.getChildren().addAll(lblNgayBatDau, txtSoDienThoai);
		spNgayBatDau.setAlignment(lblNgayBatDau, Pos.CENTER_LEFT);
		
		// Email
		lblNgayKetThuc = new Label("Email");
		lblNgayKetThuc.setId("lbl_TextField");
		
		txtemail = new TextField();
		txtemail.setPrefWidth(600);
		txtemail.setPrefHeight(40);
		txtemail.setId("txt_CapNhatChuyenTau");
		
		spNgayKetThuc = new StackPane();
		spNgayKetThuc.getChildren().addAll(lblNgayKetThuc, txtemail);
		spNgayKetThuc.setAlignment(lblNgayKetThuc, Pos.CENTER_LEFT);
		
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
		
		// Passport
		Label lblPassport = new Label("Passport");
		lblPassport.setId("lbl_TextField");
		
		txtPassport = new TextField();
		txtPassport.setPrefWidth(600);
		txtPassport.setPrefHeight(40);
		txtPassport.setId("txt_CapNhatChuyenTau");
		
		StackPane spPassport = new StackPane();
		spPassport.getChildren().addAll(lblPassport, txtPassport);
		spPassport.setAlignment(lblPassport, Pos.CENTER_LEFT);
		
		// Đối Tượng
		lblcomboDoiTuong = new Label("Đối Tượng");
		lblcomboDoiTuong.setId("lbl_TextField");
		
		comboDoiTuong = new ComboBox<>();
		comboDoiTuong.getItems().addAll("Người lớn", "Trẻ em", "Khách Quốc Tế");
		comboDoiTuong.setPrefWidth(600);
		comboDoiTuong.setPrefHeight(40);
		comboDoiTuong.setStyle("-fx-font-size: 18px");
		comboDoiTuong.setOpacity(0);
		
		buttoncomboDoiTuong = new Button();
		ImageView iconDropCombo = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
		iconDropCombo.setFitWidth(30);
		iconDropCombo.setFitHeight(30);
		buttoncomboDoiTuong.setGraphic(iconDropCombo);
		buttoncomboDoiTuong.setStyle("-fx-background-color: transparent");
		
		txtcomboDoiTuong = new TextField();
		txtcomboDoiTuong.setPrefWidth(600);
		txtcomboDoiTuong.setPrefHeight(40);
		txtcomboDoiTuong.setId("txt_CapNhatChuyenTau");
		txtcomboDoiTuong.setMouseTransparent(true);
		txtcomboDoiTuong.setFocusTraversable(false);
		
		spcomboDoiTuong = new StackPane();
		spcomboDoiTuong.getChildren().addAll(lblcomboDoiTuong, txtcomboDoiTuong, buttoncomboDoiTuong, comboDoiTuong);
		spcomboDoiTuong.setAlignment(buttoncomboDoiTuong, Pos.CENTER_RIGHT);
		spcomboDoiTuong.setAlignment(lblcomboDoiTuong, Pos.CENTER_LEFT);
		
		// Buttons
		buttonThemCTKMBox = new HBox(10);
		buttonThemCTKMBox.setMaxWidth(1600);
		
		buttonThem = new Button();
		buttonThem.setText("Thêm");
		buttonThem.setPrefWidth(150);
		buttonThem.setPrefHeight(50);
		buttonThem.setId("button_Blue");
		
		
		
		buttonThoat = new Button();
		buttonThoat.setText("Thoát");
		buttonThoat.setPrefWidth(150);
		buttonThoat.setPrefHeight(50);
		buttonThoat.setId("button_White");
		
		buttonThemCTKMBox.getChildren().addAll(buttonThem, buttonThoat);
		buttonThemCTKMBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonThemCTKMBox.setPadding(new Insets(200, 40, 20, 0));
		
		// Add to grid
		gridThemKH.add(spTenCT, 0, 0);
		gridThemKH.add(spNgayBatDau, 1, 0);
		gridThemKH.add(spNgayKetThuc, 0, 1);
		gridThemKH.add(spCCCD, 1, 1);
		gridThemKH.add(spPassport, 0, 2);
		gridThemKH.add(spcomboDoiTuong, 1, 2);
		
		layoutThemCTKM.getChildren().addAll(lblThemKH, gridThemKH, buttonThemCTKMBox);
		layoutThemCTKM.setAlignment(Pos.CENTER);
		layoutThemCTKM.setStyle("-fx-background-color: #FFFFFF");
		
		txtTenKH.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtTenKH.getText().trim().isEmpty()) {
		        lblAnimation.scaleUp(lblTenCT);
		    } else {
		        if (txtTenKH.getText().trim().isEmpty()) lblAnimation.scaleDown(lblTenCT);
		    }
		});

		txtSoDienThoai.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtSoDienThoai.getText().trim().isEmpty()) {
		        lblAnimation.scaleUp(lblNgayBatDau);
		    } else {
		        if (txtSoDienThoai.getText().trim().isEmpty()) lblAnimation.scaleDown(lblNgayBatDau);
		    }
		});

		txtemail.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtemail.getText().trim().isEmpty()) {
		        lblAnimation.scaleUp(lblNgayKetThuc);
		    } else {
		        if (txtemail.getText().trim().isEmpty()) lblAnimation.scaleDown(lblNgayKetThuc);
		    }
		});

		txtCCCD.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtCCCD.getText().trim().isEmpty()) {
		        lblAnimation.scaleUp(lblCCCD);
		    } else {
		        if (txtCCCD.getText().trim().isEmpty()) lblAnimation.scaleDown(lblCCCD);
		    }
		});

		txtPassport.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtPassport.getText().trim().isEmpty()) {
		        lblAnimation.scaleUp(lblPassport);
		    } else {
		        if (txtPassport.getText().trim().isEmpty()) lblAnimation.scaleDown(lblPassport);
		    }
		});

		comboDoiTuong.valueProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        if (txtcomboDoiTuong.getText().trim().isEmpty()) lblAnimation.scaleUp(lblcomboDoiTuong);
		        txtcomboDoiTuong.setText(newVal.toString());
		    } else {
		        txtcomboDoiTuong.clear();
		        lblAnimation.scaleDown(lblcomboDoiTuong);
		    }
		});
	}

}
