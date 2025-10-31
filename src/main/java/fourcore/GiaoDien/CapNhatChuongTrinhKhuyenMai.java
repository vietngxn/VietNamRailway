package fourcore.GiaoDien;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.animation.Animation;
import fourcore.dao.ChuongTrinhKhuyenMaiDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class CapNhatChuongTrinhKhuyenMai extends Application {
    public CapNhatChuongTrinhKhuyenMai() throws SQLException {
    }

    public static void main(String[] args) {
		launch(args);
//		Application.launch(ThemChuongTrinhKhuyenMai.class, args);
	}
	private Stage window;
	private VBox layoutCapNhatCTKM;
	private Scene sceneThemCTKM;
	private Label lblKH;
	private GridPane gridCapNhatKhachHang;
	
	private Label lblTenKH;
	private TextField txtTenKH;
	
	private StackPane spTenKH;
	
	private Label lblsoDienThoai;
	private TextField txtSoDienThoai;
	
	private StackPane spsoDienThoai;
	private VBox vboxNgayBatDau;
	private TextField txtemail;
	private Label lblemail;

	private StackPane spemail;
	private VBox vboxNgayKetThuc;

	private HBox buttonThemCTKMBox;
	private Button buttoncapNhat;

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
	
	
	private Scene sceneCapNhatCTKM;
	private Label lblCapNhatCTKM;
	private GridPane gridCapNhatCTKM;
	private Label lblphanTram;
	
	private StackPane spPhanTram;
	private Label lblTenCT;
	private TextField txtTenCT;
	private StackPane spTenCT;
	private DatePicker ngayBatDau;
	private Label lblNgayBatDau;
	private TextField txtNgayBatDau;
	private Button buttonNgayBatDau;
	private StackPane spNgayBatDau;
	private DatePicker ngayKetThuc;
	private Label lblNgayKetThuc;
	private TextField txtNgayKetThuc;
	private Button buttonNgayKetThuc;
	private StackPane spNgayKetThuc;
	private ComboBox comboDoiTuong;
	private Label lblcomboDoiTuong;
	private Button buttoncomboDoiTuong;
	private TextField txtcomboDoiTuong;
	private StackPane spcomboDoiTuong;
	private HBox buttonCapNhatCTKMBox;
	private Button buttonCapNhat;
	private Button buttonThoat;
	private ComboBox comboTrangThai;
	private Label lblcomboTrangThai;
	private Button buttoncomboTrangThai;
	private TextField txtcomboTrangThai;
	private StackPane spcomboTrangThai;
	private String makm;
	private ChuongTrinhKhuyenMaiDAO ctkmDAO;
	private TextField txtphanTram;
	private Label lblGiaTriKhuyenMai;
	private TextField txtTrangThai;
	private Label lblTrangThai;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ctkmDAO= new ChuongTrinhKhuyenMaiDAO();
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
		
		
		
        
		
		
        layoutCapNhatCTKM = new VBox();
		create_themchuongtrinhkm_layout();
		
		root.setLeft(menuList);
		root.setCenter(layoutCapNhatCTKM);
		root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        
		
		sceneThemCTKM = new Scene(root, 900, 700);
		sceneThemCTKM.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
		
		
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("KhuyenMai.dat"))) {
			KhuyenMai km = (KhuyenMai) ois.readObject();
		    ois.close();
		    System.out.println(km.toString());
		   
		   makm = km.getMaKhuyenMai();
		   double phanTram = ctkmDAO.getPhanTramKhuyenMai(makm);
		   
		   txtTenCT.setText(km.getTenChuongTrinh());
           if (!txtTenCT.getText().trim().isEmpty()) lblAnimation.scaleUp(lblTenCT);

           // Giá trị KM
           txtphanTram.setText(String.valueOf(phanTram));
           if (!txtphanTram.getText().trim().isEmpty()) lblAnimation.scaleUp(lblphanTram);

           // Ngày bắt đầu
           LocalDate nbd = km.getNgayBatDau().toLocalDate();
           ngayBatDau.setValue(nbd);
           txtNgayBatDau.setText(nbd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
           if (!txtNgayBatDau.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgayBatDau);

           // Ngày kết thúc
           LocalDate nkt = km.getNgayKetThuc().toLocalDate();
           ngayKetThuc.setValue(nkt);
           txtNgayKetThuc.setText(nkt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
           if (!txtNgayKetThuc.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgayKetThuc);

           // Trạng thái
           String trangThai = km.getTrangThaiKhuyenMai();
           comboTrangThai.setValue(trangThai);
           txtcomboTrangThai.setText(trangThai);
           if (!txtcomboTrangThai.getText().trim().isEmpty()) lblAnimation.scaleUp(lblcomboTrangThai);

           // Đối tượng
           String doiTuong = km.getDieuKienApDung();
           comboDoiTuong.setValue(doiTuong);
           txtcomboDoiTuong.setText(comboDoiTuong.getValue().toString());
           if (!txtcomboDoiTuong.getText().trim().isEmpty()) lblAnimation.scaleUp(lblcomboDoiTuong);
           
		    
		     buttonCapNhat.setOnAction(e-> {
		    	 try {
		    		 String ten = txtTenCT.getText();
		    		 String trangthai = txtcomboTrangThai.getText();
		    		 String dieuKien = txtcomboDoiTuong.getText();
		    		 double giaTri =   Double.valueOf(txtphanTram.getText());
		    		 
		    		 LocalDate ngaybatdau1 = ngayBatDau.getValue();
		    		 LocalDate ngayketthuc1 = ngayKetThuc.getValue();
		    		 
		    		 
		    		 LocalDateTime nbd1 = ngaybatdau1.atStartOfDay();
		    		 LocalDateTime nbkt1 = ngayketthuc1.atStartOfDay();
		    		 
		    		 
		    		 KhuyenMai km1 = new KhuyenMai(makm, ten, trangthai, dieuKien, giaTri, nbd1, nbkt1);
					if(ctkmDAO.capNhatKhuyenMai(km1)) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thông Báo");
						alert.setHeaderText(null);
						alert.setContentText("Cập Nhật CTKM Thành Công");
						alert.showAndWait();
					 }
				 } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }
		     });
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		

		txtphanTram.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtphanTram.getText().trim().isEmpty()) {
		    	lblAnimation.scaleUp(lblphanTram);
		    } else {
		    	if(txtphanTram.getText().trim().isEmpty()) lblAnimation.scaleDown(lblphanTram);
		    }
		});
		txtTenCT.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtTenCT.getText().trim().isEmpty()) {
		    	lblAnimation.scaleUp(lblTenCT);
		    } else {
		    	if(txtTenCT.getText().trim().isEmpty()) lblAnimation.scaleDown(lblTenCT);
		    }
		});
		
		comboDoiTuong.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				if(txtcomboDoiTuong.getText().trim().isEmpty()) lblAnimation.scaleUp(lblcomboDoiTuong);
				txtcomboDoiTuong.setText(newVal.toString());
			}
		});
		
		comboTrangThai.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				if(txtcomboTrangThai.getText().trim().isEmpty()) lblAnimation.scaleUp(lblcomboTrangThai);
				txtcomboTrangThai.setText(newVal.toString());
			}
		});
		
		ngayBatDau.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				if(txtNgayBatDau.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgayBatDau);
				txtNgayBatDau.setText(newVal.format(formatter));
			}
		});
		
		ngayKetThuc.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				if(txtNgayKetThuc.getText().trim().isEmpty()) lblAnimation.scaleUp(lblNgayKetThuc);
				txtNgayKetThuc.setText(newVal.format(formatter));
			}
		});
		
		
		
		root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		
		window.setScene(sceneThemCTKM);
		window.setFullScreen(true);
		window.show();
	}
		public void create_themchuongtrinhkm_layout() {
		
		//label đầu
		lblCapNhatCTKM = new Label("Cập nhật thông tin");
		lblCapNhatCTKM.setId("lbl_TieuDe");
		
		
		//grid chứa các cái cần nhập
		gridCapNhatCTKM = new GridPane();
		gridCapNhatCTKM.setHgap(80);
		gridCapNhatCTKM.setVgap(50);
		gridCapNhatCTKM.setPadding(new Insets(70, 0, 0, 0));
		gridCapNhatCTKM.setAlignment(Pos.CENTER);
		
		
		//hàng đầu tiên chứa mã chương trình và tên chương trình
		lblphanTram = new Label("% Giảm Giá");
		lblphanTram.setId("lbl_TextField");

		
		txtphanTram = new TextField();
		txtphanTram.setPrefWidth(600);
		txtphanTram.setPrefHeight(40);
		txtphanTram.setId("txt_CapNhatChuyenTau");
		
		
		spPhanTram = new StackPane();
		spPhanTram.getChildren().addAll(lblphanTram, txtphanTram);
		spPhanTram.setAlignment(lblphanTram, Pos.CENTER_LEFT);
		
		lblTenCT = new Label("Tên chương trình");
		lblTenCT.setId("lbl_TextField");
		
		txtTenCT = new TextField();
		txtTenCT.setPrefWidth(600);
		txtTenCT.setPrefHeight(40);
		txtTenCT.setId("txt_CapNhatChuyenTau");
		
		spTenCT = new StackPane();
		spTenCT.getChildren().addAll(lblTenCT, txtTenCT);
		spTenCT.setAlignment(lblTenCT, Pos.CENTER_LEFT);
		
	
		ngayBatDau = new DatePicker();
		ngayBatDau.setPrefWidth(30);
		ngayBatDau.setPrefHeight(40);
		ngayBatDau.setStyle("-fx-font-size: 18px;");
		ngayBatDau.setOpacity(0);
		
		lblNgayBatDau = new Label("Ngày Bắt Đầu");
		lblNgayBatDau.setId("lbl_TextField");
		
		txtNgayBatDau = new TextField();
		txtNgayBatDau.setPrefWidth(600);
		txtNgayBatDau.setPrefHeight(40);
		txtNgayBatDau.setId("txt_CapNhatChuyenTau");
		txtNgayBatDau.setMouseTransparent(true);
		txtNgayBatDau.setFocusTraversable(false);
		
		buttonNgayBatDau = new Button();
		ImageView iconThoiGian = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
		iconThoiGian.setFitWidth(30);
		iconThoiGian.setFitHeight(30);
		buttonNgayBatDau.setGraphic(iconThoiGian);
		buttonNgayBatDau.setStyle("-fx-background-color: transparent");
		
		spNgayBatDau = new StackPane();
		spNgayBatDau.getChildren().addAll(lblNgayBatDau, txtNgayBatDau, buttonNgayBatDau, ngayBatDau);
		spNgayBatDau.setAlignment(buttonNgayBatDau, Pos.CENTER_RIGHT);
		spNgayBatDau.setAlignment(lblNgayBatDau, Pos.CENTER_LEFT);
		spNgayBatDau.setAlignment(ngayBatDau, Pos.CENTER_RIGHT);
		
//		vboxNgayBatDau = new VBox();
//		vboxNgayBatDau.getChildren().addAll(lblNgayBatDau, spNgayBatDau);
		
		
		ngayKetThuc = new DatePicker();
		ngayKetThuc.setPrefWidth(30);
		ngayKetThuc.setPrefHeight(40);
		ngayKetThuc.setStyle("-fx-font-size: 18px");
		ngayKetThuc.setOpacity(0);
		
		lblNgayKetThuc = new Label("Ngày kết thúc");
		lblNgayKetThuc.setId("lbl_TextField");
		
		txtNgayKetThuc = new TextField();
		txtNgayKetThuc.setPrefWidth(600);
		txtNgayKetThuc.setPrefHeight(40);
		txtNgayKetThuc.setId("txt_CapNhatChuyenTau");
		txtNgayKetThuc.setMouseTransparent(true);
		txtNgayKetThuc.setFocusTraversable(false);
		
		buttonNgayKetThuc = new Button();
		ImageView iconNgayKetThuc = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
		iconNgayKetThuc.setFitWidth(30);
		iconNgayKetThuc.setFitHeight(30);
		buttonNgayKetThuc.setGraphic(iconNgayKetThuc);
		buttonNgayKetThuc.setStyle("-fx-background-color: transparent");
		
		spNgayKetThuc = new StackPane();
		spNgayKetThuc.getChildren().addAll(lblNgayKetThuc, txtNgayKetThuc, buttonNgayKetThuc, ngayKetThuc);
		spNgayKetThuc.setAlignment(buttonNgayKetThuc, Pos.CENTER_RIGHT);
		spNgayKetThuc.setAlignment(lblNgayKetThuc, Pos.CENTER_LEFT);
		spNgayKetThuc.setAlignment(ngayKetThuc, Pos.CENTER_RIGHT);
		
//		vboxNgayKetThuc = new VBox();
//		vboxNgayKetThuc.getChildren().addAll(lblNgayKetThuc, spNgayKetThuc);
		
		comboDoiTuong = new ComboBox<>();
		comboDoiTuong.getItems().addAll("Người lớn", "Trẻ em", "Anh hùng liệt sĩ", "Tung tung tung sahur");
		comboDoiTuong.setPrefWidth(600);
		comboDoiTuong.setPrefHeight(40);
		comboDoiTuong.setStyle("-fx-font-size: 18px");
		comboDoiTuong.setOpacity(0);
		
		lblcomboDoiTuong = new Label("Đối tượng áp dụng");
		lblcomboDoiTuong.setId("lbl_TextField");
		
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
		
		comboTrangThai = new ComboBox<>();
		comboTrangThai.getItems().addAll("kích hoạt", "kết thúc");
		comboTrangThai.setPrefWidth(600);
		comboTrangThai.setPrefHeight(40);
		comboTrangThai.setStyle("-fx-font-size: 18px");
		comboTrangThai.setOpacity(0);
		
		lblcomboTrangThai = new Label("Trạng thái");
		lblcomboTrangThai.setId("lbl_TextField");
		
		buttoncomboTrangThai = new Button();
		ImageView iconDropComboTrangThai = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
		iconDropComboTrangThai.setFitWidth(30);
		iconDropComboTrangThai.setFitHeight(30);
		buttoncomboTrangThai.setGraphic(iconDropComboTrangThai);
		buttoncomboTrangThai.setStyle("-fx-background-color: transparent");
		
		txtcomboTrangThai = new TextField();
		txtcomboTrangThai.setPrefWidth(600);
		txtcomboTrangThai.setPrefHeight(40);
		txtcomboTrangThai.setId("txt_CapNhatChuyenTau");
		txtcomboTrangThai.setMouseTransparent(true);
		txtcomboTrangThai.setFocusTraversable(false);
		
		spcomboTrangThai = new StackPane();
		spcomboTrangThai.getChildren().addAll(lblcomboTrangThai, txtcomboTrangThai, buttoncomboTrangThai, comboTrangThai);
		spcomboTrangThai.setAlignment(buttoncomboTrangThai, Pos.CENTER_RIGHT);
		spcomboTrangThai.setAlignment(lblcomboTrangThai, Pos.CENTER_LEFT);
		
		
		buttonCapNhatCTKMBox = new HBox(10); //thay doi 10
		buttonCapNhatCTKMBox.setMaxWidth(1600);
		
		buttonCapNhat = new Button();
		buttonCapNhat.setText("Cập nhật");
		buttonCapNhat.setPrefWidth(150);
		buttonCapNhat.setPrefHeight(50);
		buttonCapNhat.setId("button_Blue");
		
		buttonThoat = new Button();
		buttonThoat.setText("Thoát");
		buttonThoat.setPrefWidth(150);
		buttonThoat.setPrefHeight(50);
		buttonThoat.setId("button_White");
		
		buttonCapNhatCTKMBox.getChildren().addAll(buttonCapNhat, buttonThoat);
		buttonCapNhatCTKMBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonCapNhatCTKMBox.setPadding(new Insets(200, 40, 20, 0));
		
		gridCapNhatCTKM.add(spTenCT, 0, 0);
		gridCapNhatCTKM.add(spPhanTram, 1, 0);
		gridCapNhatCTKM.add(spNgayBatDau, 0, 1);
		gridCapNhatCTKM.add(spNgayKetThuc, 1, 1);
		gridCapNhatCTKM.add(spcomboDoiTuong, 0, 2);
		gridCapNhatCTKM.add(spcomboTrangThai, 1, 2);
		
		
		layoutCapNhatCTKM.getChildren().addAll(lblCapNhatCTKM, gridCapNhatCTKM, buttonCapNhatCTKMBox);
		layoutCapNhatCTKM.setAlignment(Pos.CENTER);
		layoutCapNhatCTKM.setStyle("-fx-background-color: #FFFFFF");
	}

}
