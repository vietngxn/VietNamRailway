package fourcore.GiaoDien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.dao.ChuongTrinhKhuyenMaiDAO;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class QuanLyCTKM extends Application {
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
	private VBox layout_timkiem; 
	private VBox table_layout;
	private HBox table_title;
	private Label lbl_maKhachHang;
	private Label lbl_hoTen;
	private Label lbl_cccd;
	private Label lbl_doituong;
	private Label lbl_doiTuong;
	private HBox layout_dong;
	private Label lbl_title_maCTKM;
	private Label lbl_title_tenCTKM;
	private Node lbl_title_ngaybatdau;
	private Node lbl_title_ngayketthuc;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private GridPane hangchon = null;
	private HBox layout_button;
	private Button btn_xoaCTKM;
	private Button btn_themCTKM;
	private Button btn_capnhat;
	private Label lbl_title_dieuKienApDung;
	private Node lbl_title_trangthai;
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
    private HBox xemLichSuVeBox;
    private ArrayList<KhuyenMai> listKhuyenMai;
	private ChuongTrinhKhuyenMaiDAO ctkmDAO;
	private GridPane[] hangchonPopup = null;
	private Button btn_khoiPhuc;
	private Button btn_khoiPhuc1;
	private Stage window;
	private Button btn_timkiem;


    public Button getBtn_themCTKM(){
        return btn_themCTKM;
    }
    public Button getBtn_capnhat() {
        return btn_capnhat;
    }

    @Override
	public void start(Stage primaryStage) {
		try {
			
//			======================
//			||     GET DATA     ||
//			======================
            ctkmDAO = new ChuongTrinhKhuyenMaiDAO();
            listKhuyenMai = ctkmDAO.getListKhuyenMai();

//----------------------------------------------------------------------------------------------------------------------------------------
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
            capVeBox.setOnMouseClicked(event -> {
                GiaoDienCapLaiVe capVe = new GiaoDienCapLaiVe();
                Stage capVeStage = new Stage();
                capVe.start(capVeStage);
                VBox giaoDienCapVe =   capVe.getNoiDungChinhVe();
                root.setCenter(giaoDienCapVe);
            });
            xemLichSuVeBox.setOnMouseClicked(event -> {
                GiaoDienLichSuMuaBanDoiVe lichSuMuaBanDoiVe = new GiaoDienLichSuMuaBanDoiVe();
                Stage lichSuMuaBanDoiVeStage = new Stage();
                lichSuMuaBanDoiVe.start(lichSuMuaBanDoiVeStage);
                VBox giaoDienLichSuMuaBanDoiVe = lichSuMuaBanDoiVe.getLichSuMuaVe();
                root.setCenter(giaoDienLichSuMuaBanDoiVe);
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
//            InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//            Font labelMenuPhu = Font.loadFont(interSemiBold,15);

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
            quanLiKhachHangBox.setOnMouseClicked(event -> {
                QuanLyKhachHang gdQuanLyKhachHang = new QuanLyKhachHang();
                Stage quanLyKhachHangStage = new Stage();
                gdQuanLyKhachHang.start(quanLyKhachHangStage);
                VBox quanLyKhachHangVBox = gdQuanLyKhachHang.getQuanLiKhachHang();
               root.setCenter(quanLyKhachHangVBox);
            });

            thongKeKhachHang.setOnMouseClicked(event -> {
               QuanLiThongKeChuyenTau gdQuanLiThongKe = new QuanLiThongKeChuyenTau();
               gdQuanLiThongKe.setLoaiThongKe("ThongKeKhachHang");
               Stage thongKeKhachHangStage = new Stage();
               gdQuanLiThongKe.start(thongKeKhachHangStage);
               VBox quanLiThongKeVBox = gdQuanLiThongKe.getQuanLiThongKe();
               root.setCenter(quanLiThongKeVBox);
            });

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
//            InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//            Font labelMenuPhu = Font.loadFont(interSemiBold,15);

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
            quanLiHoaDonBox.setOnMouseClicked(event -> {
               QuanLiHoaDon gdQuanLiHoaDon = new QuanLiHoaDon();
               Stage quanLiHoaDonStage = new Stage();
               gdQuanLiHoaDon.start(quanLiHoaDonStage);
               VBox gdQLHD = gdQuanLiHoaDon.getQuanLiHoaDon();
               root.setCenter(gdQLHD);
            });

            thongKeDoanhThuBox.setOnMouseClicked(event -> {
                QuanLiThongKeChuyenTau gdQuanLiThongKe2 = new QuanLiThongKeChuyenTau();
                gdQuanLiThongKe2.setLoaiThongKe("ThongKeDoanhThu");
                Stage thongKeDoanhStage = new Stage();
                gdQuanLiThongKe2.start(thongKeDoanhStage);
                VBox gdQLTK2 = gdQuanLiThongKe2.getQuanLiThongKe();
                root.setCenter(gdQLTK2);


            });
//			======================
//			||QUAN LI THONG KE  ||
//			======================
//            quanLiThongKeMenu = new HBox();
//            quanLiThongKeMenu.setSpacing(102);
//            quanLiThongKeMenu.setPadding(new Insets(15, 95, 15, 20));
//            quanLiThongKeMenu.setStyle("-fx-alignment: center-left;");
//
//
////			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
//            quanLiThongKeIconView = new ImageView(getClass().getResource("/img/presentation-chart-bar.png").toExternalForm());
//            quanLiThongKeIconView.setFitWidth(30);
//            quanLiThongKeIconView.setFitHeight(30);
//            quanLiThongKeIconView.setTranslateX(20);
//
//            quanLiThongKeLabel = new Label("Quản lí thống kê");
//
//            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
//            if (interBoldFont != null) {
//                labelFont = Font.loadFont(interBoldFont, 20);
//                quanLiThongKeLabel.setFont(labelFont);
//                System.out.println("⚠️ Set font thanh cong.");
//                System.out.println("Loaded font: " + labelFont.getName());
//            } else {
//                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
//                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
//            }
//
////			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
//            showMenuPhuIconSource = "/img/chevron-down.png";
//            showMenuPhuIconView = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
//            showMenuPhuIconView.setFitWidth(20);
//            showMenuPhuIconView.setFitHeight(20);
//            showMenuPhuIconView.setTranslateX(25.5);
//            quanLiThongKeMenu.getChildren().addAll(quanLiThongKeIconView, quanLiThongKeLabel, showMenuPhuIconView);
//
//
//            danhSachMenuItem.getChildren().add(quanLiThongKeMenu);
//            scrollPaneMenu.setContent(danhSachMenuItem);
//            VBox menuPhuQuanLiThongKe = new VBox();
//            menuPhuQuanLiThongKe.setSpacing(10);
//            menuPhuQuanLiThongKe.setPadding(new Insets(0, 40, 0, 0));
//            menuPhuQuanLiThongKe.setVisible(false);
//            menuPhuQuanLiThongKe.setManaged(false);
//            menuPhuQuanLiThongKe.setStyle("-fx-background-color: #D2EEF0;");
//
//            HBox thongKe1Box = new HBox();
//            HBox thongKe2Box = new HBox();
//            HBox thongKe3Box = new HBox();
//
//            Label thongKe1Label = new Label("Thống kê doanh thu theo tháng");
//            Label thongKe2Label = new Label("Thống kê ");
//            Label thongKe3Label = new Label("Thống kê ghế bán chạy trong tháng");
//
//
//            thongKe1Box.getChildren().add(thongKe1Label);
//            thongKe2Box.getChildren().add(thongKe2Label);
//            thongKe3Box.getChildren().add(thongKe3Label);
//
//            interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//            labelMenuPhu = Font.loadFont(interSemiBold,15);
//
//            for (Label label : new Label[]{thongKe1Label,thongKe2Label,thongKe3Label}) {
//                label.setStyle("-fx-background-color: #D2EEF0;");
//                label.setTranslateY(0);
//                label.setFont(labelMenuPhu);
//                label.setPadding(new Insets(12, 320, 12, 155));
//                label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
//                label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));
//
//            }
//            for (HBox hbox : new HBox[]{thongKe1Box,thongKe2Box,thongKe3Box}) {
//                hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
//                hbox.setPadding(new Insets(0,50,0,0));
//                hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
//                hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
//            }
//
//            menuPhuQuanLiThongKe.getChildren().addAll(thongKe1Box,thongKe2Box,thongKe3Box);
//            danhSachMenuItem.getChildren().add(menuPhuQuanLiThongKe);
//
//            // Sự kiện onclick vào menu
//            quanLiThongKeMenu.setOnMouseClicked(event -> {
//                boolean isVisible = menuPhuQuanLiThongKe.isVisible();
//                menuPhuQuanLiThongKe.setVisible(!isVisible);
//                menuPhuQuanLiThongKe.setManaged(!isVisible);
//                if (isVisible == false) {
//                    quanLiThongKeMenu.setStyle(" -fx-background-color: #79D9E1;");
//                }
//                else {
//                    quanLiThongKeMenu.setStyle(" -fx-background-color: #F7F7F7;");
//
//
//
//                }
//                TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiThongKe);
//                if (!isVisible) {
//                    menuPhuQuanLiThongKe.setVisible(true);
//                    menuPhuQuanLiThongKe.setManaged(true);
//                    menuPhuQuanLiThongKe.setTranslateY(-20);
//                    slide.setFromY(-20);
//                    slide.setToY(0);
//                    quanLiThongKeMenu.setStyle("-fx-background-color: #79D9E1;");
//                } else {
//                    slide.setFromY(0);
//                    slide.setToY(-20);
//                    slide.setOnFinished(e -> {
//                        menuPhuQuanLiThongKe.setVisible(false);
//                        menuPhuQuanLiThongKe.setManaged(false);
//                    });
//                    quanLiThongKeMenu.setStyle("-fx-background-color: #F7F7F7;");
//                }
//                slide.play();
//
//
//
//
//            });


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

//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiNhanVienMenu.getChildren().addAll(quanLiNhanVienIconView, quanLiNhanVienLabel);


            danhSachMenuItem.getChildren().add(quanLiNhanVienMenu);
            scrollPaneMenu.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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

//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiCTKMMenu.getChildren().addAll(quanLiCTKMIconView, quanLiCTKMLabel);
            quanLiCTKMMenu.setOnMouseClicked(event -> {
               QuanLyCTKM quanLyCTKM = new QuanLyCTKM();
               Stage stage = new Stage();
               quanLyCTKM.start(stage);
               VBox quanLyCTKMMenu = quanLyCTKM.getQuanLiCTKM();
               root.setCenter(quanLyCTKMMenu);
            });
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
//		showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
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
//            InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
//            Font labelMenuPhu = Font.loadFont(interSemiBold,15);

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
            userBox.setTranslateY(140);
            userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
            menuList.getChildren().add(userBox);
			
			
			//Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			
			create_title_layout();
			
			create_table_layout();
			
			create_layout_button();
			primaryStage.setFullScreen(true);
//			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    public VBox getQuanLiCTKM() {
    	hienThi();
     return this.noiDungChinh;
    }
	
	public void create_title_layout() {
		title_layout  = new VBox();
		title_layout.setPadding(new Insets(30));
		title_layout.setSpacing(20);
		
		lbl_title = new Label("Quản Lí chương trình khuyến mãi"); 
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);
		
		layout_timkiem = new VBox();
		
		layout_lbl_timkiem = new HBox();
		layout_lbl_timkiem.setPrefSize(740, 40);
		lbl_timkiem = new Label("Nhập mã CTKM để tìm kiếm");
		lbl_timkiem.setTranslateX(15);
		lbl_timkiem.setTranslateY(0);
		lbl_timkiem.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");
	
		ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
		img_timkiem.setTranslateX(470);
		img_timkiem.setFitHeight(25);
		img_timkiem.setFitWidth(25);
		layout_lbl_timkiem.getChildren().addAll(lbl_timkiem,img_timkiem);
		layout_lbl_timkiem.setTranslateY(45);
		layout_timkiem.getChildren().add(layout_lbl_timkiem);
		
		
		layout_txt_timkiem = new VBox();
		txt_timkiem = new TextField();
		txt_timkiem.setPrefHeight(40);
		txt_timkiem.setMaxSize(750, 50);
		txt_timkiem.setPadding(new Insets(10));
		txt_timkiem.setStyle("-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
		txt_timkiem.setFocusTraversable(false);
		layout_txt_timkiem.getChildren().add(txt_timkiem);	
		layout_timkiem.getChildren().add(layout_txt_timkiem);
		
		
		
		
		layout_timkiem.setTranslateX(100);
		
		
		
		HBox layout_button_timkiem = new HBox();
		layout_button_timkiem.setAlignment(Pos.CENTER_LEFT);
		layout_button_timkiem.setSpacing(10);
		layout_button_timkiem.setTranslateX(300);
		layout_button_timkiem.setTranslateY(20);

		btn_timkiem = new Button("Tìm Kiếm");
		btn_timkiem.setPrefSize(150, 50);
		btn_timkiem.setMaxSize(150, 50);
		btn_timkiem.setStyle("-fx-background-color: #00BACB;" +
		    "-fx-text-fill: white;" +
		    "-fx-font-family: 'Inter';" +
		    "-fx-font-weight: bold;" +
		    "-fx-font-size: 14px;" +
		    "-fx-cursor: hand;" +
		    "-fx-border-radius: 30px;" +
		    "-fx-background-radius: 30px;");
		
		layout_button_timkiem.getChildren().add(btn_timkiem);
		
		btn_timkiem.setOnMouseClicked(e-> {
			String ma = txt_timkiem.getText();
			String regex = "^KM\\d+$";
			if (txt_timkiem.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setContentText("Vui lòng nhập mã CTKM để tìm kiếm");
		        alert.setHeaderText(null);
		        alert.showAndWait();
		        return;
		    }
			
			if(!ma.matches(regex))
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setContentText("Nhập mã sai định dạng!Vui lòng nhập theo định dạng KMXXXX");
		        alert.setHeaderText(null);
		        alert.showAndWait();
		        return;
			}
			
			try {
				KhuyenMai km = ctkmDAO.getKhuyenMaiBangMa(ma);
				if(km == null)
				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
			        alert.setContentText("Không tìm thấy Chương Trình Khuyến mãi");
			        alert.setHeaderText(null);
			        alert.showAndWait();
			        return;
				}
				else if(km != null && km.getRs() == 0)
				{
					table_desc.getChildren().clear();
					loadCTKMData(km.getMaKhuyenMai(),km.getTenChuongTrinh(), km.getNgayBatDau().toLocalDate(), km.getNgayKetThuc().toLocalDate(), km.getDieuKienApDung(), km.getTrangThaiKhuyenMai());
					hangchon = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		});
		
		layout_timkiem.getChildren().add(layout_button_timkiem);

		
		title_layout.getChildren().add(layout_timkiem);
		
		txt_timkiem.focusedProperty().addListener((obs,oval,nval) -> {
			TranslateTransition tt = new TranslateTransition(Duration.millis(350), lbl_timkiem);
			if(nval)
			{
				tt.setToY(-40);
			}
			else
			{
				if(txt_timkiem.getText().isEmpty())
				{
					tt.setToY(0);	
				}
				else {
					tt.setToY(-40);
				}
			}
			tt.play();
		});
		
		noiDungChinh.getChildren().add(title_layout);
	}
	
	public void create_table_layout() {
	    table_layout = new VBox();
	    table_layout.setPadding(new Insets(10));
	    
	    GridPane tableCol = new GridPane();
	    tableCol.setHgap(10);
	    tableCol.setVgap(20);
	    tableCol.setAlignment(Pos.CENTER);
	    tableCol.setMaxWidth(1330);
	    
	    table_layout.setPrefSize(1350, 730);
	    table_layout.setTranslateX(10);
	    table_layout.setMaxSize(1350, 730);
	    
	    String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 18px; -fx-font-weight: bold;";
	    
	    lbl_title_maCTKM = new Label("Mã Chương Trình");
	    lbl_title_maCTKM.setStyle(styleHeader);
	    
	    lbl_title_tenCTKM = new Label("Tên Chương Trình");
	    lbl_title_tenCTKM.setStyle(styleHeader);
	    
	    lbl_title_ngaybatdau = new Label("Ngày Bắt Đầu");
	    lbl_title_ngaybatdau.setStyle(styleHeader);
	    
	    lbl_title_ngayketthuc = new Label("Ngày Kết Thúc");
	    lbl_title_ngayketthuc.setStyle(styleHeader);
	    
	    lbl_title_dieuKienApDung = new Label("Điều Kiện Áp Dụng");
	    
	    lbl_title_dieuKienApDung.setStyle(styleHeader);
	    
	    lbl_title_trangthai = new Label("Trạng Thái");
	    lbl_title_trangthai.setStyle(styleHeader);
	    
	    
	    lbl_title_maCTKM.setTranslateX(-120);
	    lbl_title_tenCTKM.setTranslateX(-85);
	    lbl_title_ngaybatdau.setTranslateX(-40);
	    lbl_title_ngayketthuc.setTranslateX(-30);
	    lbl_title_trangthai.setTranslateX(60);
	    
	    
	    StackPane paneCol1 = new StackPane(lbl_title_maCTKM);
	    StackPane paneCol2 = new StackPane(lbl_title_tenCTKM);
	    StackPane paneCol3 = new StackPane(lbl_title_ngaybatdau);
	    StackPane paneCol4 = new StackPane(lbl_title_ngayketthuc);
	    StackPane paneCol5 = new StackPane(lbl_title_dieuKienApDung);
	    StackPane paneCol6 = new StackPane(lbl_title_trangthai);
	    
	    paneCol1.setPrefWidth(150);
	    paneCol2.setPrefWidth(180);
	    paneCol3.setPrefWidth(150);
	    paneCol4.setPrefWidth(150);
	    paneCol5.setPrefWidth(250);
	    paneCol6.setPrefWidth(120);
	    
	    paneCol1.setAlignment(Pos.CENTER);
	    paneCol2.setAlignment(Pos.CENTER);
	    paneCol3.setAlignment(Pos.CENTER);
	    paneCol4.setAlignment(Pos.CENTER);
	    paneCol5.setAlignment(Pos.CENTER);
	    paneCol6.setAlignment(Pos.CENTER);
	    
	    tableCol.add(paneCol1, 0, 0);
	    tableCol.add(paneCol2, 1, 0);
	    tableCol.add(paneCol3, 2, 0);
	    tableCol.add(paneCol4, 3, 0);
	    tableCol.add(paneCol5, 4, 0);
	    tableCol.add(paneCol6, 5, 0);
	    
	    table_layout.getChildren().add(tableCol);
	    
	    table_desc = new VBox();
	    table_desc.setSpacing(20);
	    
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//			======================
//			||     CONTROL     ||
//			======================

//  DATA INPUT PATTERN:      maCT,tenCT,ngaybatdau,ngayketthuc,trangthai
        for (KhuyenMai khuyenMai : listKhuyenMai) {
            if (khuyenMai.getRs() == 0){
            	loadCTKMData(khuyenMai.getMaKhuyenMai(), khuyenMai.getTenChuongTrinh(), khuyenMai.getNgayBatDau().toLocalDate(), khuyenMai.getNgayKetThuc().toLocalDate(), khuyenMai.getDieuKienApDung(), khuyenMai.getTrangThaiKhuyenMai());                
            }
        }


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	    
	    scrollPane = new ScrollPane();
	    scrollPane.setContent(table_desc);
	    scrollPane.setFitToWidth(true);
	    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	    scrollPane.setStyle("-fx-background-color: transparent");
	    scrollPane.setPannable(true);
	    
	    VBox.setVgrow(scrollPane, Priority.ALWAYS);
	    scrollPane.setMaxHeight(660);
	    
	    table_layout.getChildren().add(scrollPane);
	    
	    noiDungChinh.getChildren().add(table_layout);
	}
	public void create_layout_button()
	{
		layout_button = new HBox();
		
		layout_button.setPrefSize(950, 60);
		layout_button.setAlignment(Pos.CENTER_RIGHT);
		layout_button.setTranslateX(-70);	
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size:13.5px;-fx-text-fill:white;-fx-background-radius: 20px;";
		
		btn_xoaCTKM = new Button("Xóa Chương Trình Khuyến Mãi");
		btn_xoaCTKM.setStyle(style+ "-fx-background-color: linear-gradient(to bottom, #D498A5, #D32B4F);");
		btn_xoaCTKM.setPrefSize(225, 60);
		btn_xoaCTKM.setDisable(true);
		
		btn_themCTKM = new Button("Thêm Chương Trình Khuyến Mãi");
		btn_themCTKM.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_themCTKM.setPrefSize(225, 60);
		
		
		btn_capnhat = new Button("Cập nhật thông tin");
		btn_capnhat.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_capnhat.setPrefSize(225, 60);
		
		btn_khoiPhuc = new Button("Khôi Phục");
	    btn_khoiPhuc.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
	    btn_khoiPhuc.setPrefSize(225, 60);
	    
	    layout_button.setSpacing(40);
	    
	    
	    // === XỬ LÝ BUTTON KHÔI PHỤC ===
	    btn_khoiPhuc.setOnAction(e -> {
	        Stage popupStage = new Stage();
	        popupStage.initOwner(window);
	        popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
	        popupStage.initStyle(javafx.stage.StageStyle.DECORATED);
	        popupStage.setTitle("Khôi phục chương trình khuyến mãi");
	        popupStage.setWidth(1450);
	        popupStage.setHeight(950);
	        
	        hangchonPopup = new GridPane[]{null};
	        
	        VBox popupRoot = new VBox();
	        popupRoot.setStyle("-fx-background-color: #F7F7F7;");
	        popupRoot.setPrefWidth(1450);
	        popupRoot.setPrefHeight(950);
	        
	        VBox title_layout_recovery = new VBox();
	        title_layout_recovery.setPadding(new Insets(30));
	        title_layout_recovery.setSpacing(20);
	        
	        Label lbl_title_recovery = new Label("Khôi phục Chương Trình Khuyến Mãi"); 
	        lbl_title_recovery.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
	        title_layout_recovery.getChildren().add(lbl_title_recovery);
	        
	        VBox table_layout_recovery = new VBox();
	        table_layout_recovery.setPadding(new Insets(10));
	        
	        GridPane tableCol_recovery = new GridPane();
	        tableCol_recovery.setHgap(10);
	        tableCol_recovery.setVgap(20);
	        tableCol_recovery.setAlignment(Pos.CENTER);
	        tableCol_recovery.setMaxWidth(1330);
	        
	        table_layout_recovery.setPrefSize(1350, 730);
	        table_layout_recovery.setTranslateX(10);
	        table_layout_recovery.setMaxSize(1350, 730);
	        
	        String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 18px; -fx-font-weight: bold;";
	        
	        Label lbl_title_maCTKM_recovery = new Label("Mã Chương Trình");
	        lbl_title_maCTKM_recovery.setStyle(styleHeader);
	        
	        Label lbl_title_tenCTKM_recovery = new Label("Tên Chương Trình");
	        lbl_title_tenCTKM_recovery.setStyle(styleHeader);
	        
	        Label lbl_title_ngaybatdau_recovery = new Label("Ngày Bắt Đầu");
	        lbl_title_ngaybatdau_recovery.setStyle(styleHeader);
	        
	        Label lbl_title_ngayketthuc_recovery = new Label("Ngày Kết Thúc");
	        lbl_title_ngayketthuc_recovery.setStyle(styleHeader);
	        
	        Label lbl_title_dieuKienApDung_recovery = new Label("Điều Kiện Áp Dụng");
	        lbl_title_dieuKienApDung_recovery.setStyle(styleHeader);
	        
	        Label lbl_title_trangthai_recovery = new Label("Trạng Thái");
	        lbl_title_trangthai_recovery.setStyle(styleHeader);
	        
	        lbl_title_maCTKM_recovery.setTranslateX(-70);
	        lbl_title_tenCTKM_recovery.setTranslateX(-60);
	        lbl_title_ngaybatdau_recovery.setTranslateX(-30);
	        lbl_title_ngayketthuc_recovery.setTranslateX(-30);
	        lbl_title_dieuKienApDung_recovery.setTranslateX(30);
	        lbl_title_trangthai_recovery.setTranslateX(80);
	        
	        StackPane paneCol1 = new StackPane(lbl_title_maCTKM_recovery);
	        StackPane paneCol2 = new StackPane(lbl_title_tenCTKM_recovery);
	        StackPane paneCol3 = new StackPane(lbl_title_ngaybatdau_recovery);
	        StackPane paneCol4 = new StackPane(lbl_title_ngayketthuc_recovery);
	        StackPane paneCol5 = new StackPane(lbl_title_dieuKienApDung_recovery);
	        StackPane paneCol6 = new StackPane(lbl_title_trangthai_recovery);
	        
	        paneCol1.setPrefWidth(150);
	        paneCol2.setPrefWidth(180);
	        paneCol3.setPrefWidth(150);
	        paneCol4.setPrefWidth(150);
	        paneCol5.setPrefWidth(200);
	        paneCol6.setPrefWidth(130);
	        
	        paneCol1.setAlignment(Pos.CENTER);
	        paneCol2.setAlignment(Pos.CENTER);
	        paneCol3.setAlignment(Pos.CENTER);
	        paneCol4.setAlignment(Pos.CENTER);
	        paneCol5.setAlignment(Pos.CENTER);
	        paneCol6.setAlignment(Pos.CENTER);
	        
	        tableCol_recovery.add(paneCol1, 0, 0);
	        tableCol_recovery.add(paneCol2, 1, 0);
	        tableCol_recovery.add(paneCol3, 2, 0);
	        tableCol_recovery.add(paneCol4, 3, 0);
	        tableCol_recovery.add(paneCol5, 4, 0);
	        tableCol_recovery.add(paneCol6, 5, 0);
	        
	        table_layout_recovery.getChildren().add(tableCol_recovery);
	        
	        VBox table_desc_recovery = new VBox();
	        table_desc_recovery.setSpacing(20);
	        
	        try {
	        	ArrayList<KhuyenMai> listKhuyenMai1 = ctkmDAO.getListKhuyenMai();
	            for (KhuyenMai khuyenMai : listKhuyenMai1) {
	                if (khuyenMai.getRs() == 1) {
	                    loadDuLieuKhoiPhuc(table_desc_recovery, khuyenMai.getMaKhuyenMai(), 
	                        khuyenMai.getTenChuongTrinh(), khuyenMai.getNgayBatDau().toLocalDate(), 
	                        khuyenMai.getNgayKetThuc().toLocalDate(), khuyenMai.getDieuKienApDung(), khuyenMai.getTrangThaiKhuyenMai());
	                }
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        
	        
	        ScrollPane scrollPane_recovery = new ScrollPane();
	        scrollPane_recovery.setContent(table_desc_recovery);
	        scrollPane_recovery.setFitToWidth(true);
	        scrollPane_recovery.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        scrollPane_recovery.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	        scrollPane_recovery.setStyle("-fx-background-color: transparent");
	        scrollPane_recovery.setPannable(true);
	        
	        VBox.setVgrow(scrollPane_recovery, Priority.ALWAYS);
	        scrollPane_recovery.setMaxHeight(660);
	        
	        table_layout_recovery.getChildren().add(scrollPane_recovery);
	        
	        HBox layout_button_recovery = new HBox();
	        layout_button_recovery.setPrefSize(1350, 60);
	        layout_button_recovery.setAlignment(Pos.CENTER_RIGHT);
	        layout_button_recovery.setTranslateX(30);    
	        String style_recovery = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size:13.5px;-fx-text-fill:white;-fx-background-radius: 20px;";
	        
	        btn_khoiPhuc1 = new Button("Khôi Phục");
	        btn_khoiPhuc1.setStyle(style_recovery+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
	        btn_khoiPhuc1.setPrefSize(225, 60);
	        btn_khoiPhuc1.setDisable(true);
	        
	        Button btn_quayLai = new Button("Đóng");
	        btn_quayLai.setStyle(style_recovery+"-fx-background-color: linear-gradient(to bottom, #D498A5, #D32B4F);");
	        btn_quayLai.setPrefSize(225, 60);
	        
	        layout_button_recovery.setSpacing(40);
	        layout_button_recovery.setTranslateX(-100);
	        layout_button_recovery.getChildren().addAll(btn_quayLai, btn_khoiPhuc1);
	        
	        btn_khoiPhuc1.setOnAction(ev -> {
	            if(hangchonPopup[0] == null) {
	                Alert alert = new Alert(Alert.AlertType.WARNING);
	                alert.setTitle("Thông báo");
	                alert.setHeaderText(null);
	                alert.setContentText("Vui lòng chọn CTKM để khôi phục");
	                alert.showAndWait();
	                return;
	            }
	            
	            String maCTKM = ((Label)((StackPane)hangchonPopup[0].getChildren().get(0)).getChildren().get(0)).getText();
	            
	            try {
	                if(ctkmDAO.khoiPhucKhuyenMai(maCTKM)) {
	                    FadeTransition ft = new FadeTransition(Duration.millis(400), hangchonPopup[0]);
	                    ft.setFromValue(1.0);
	                    ft.setToValue(0);
	                    ft.play();
	                    ft.setOnFinished(E -> {
	                        table_desc_recovery.getChildren().remove(hangchonPopup[0]);
	                        btn_khoiPhuc1.setDisable(true);
	                        hangchonPopup[0] = null;
	                    });
	                    
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Thành công");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Khôi phục CTKM thành công!");
	                    alert.showAndWait();
	                }
	            } catch (SQLException ex) {
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("Lỗi");
	                alert.setHeaderText(null);
	                alert.setContentText("Có lỗi xảy ra: " + ex.getMessage());
	                alert.showAndWait();
	                ex.printStackTrace();
	            }
	        });
	        
	        btn_quayLai.setOnAction(ev -> {
	            popupStage.close();
	            
	            table_desc.getChildren().clear();    
	            btn_xoaCTKM.setDisable(true);
	            btn_capnhat.setDisable(true);
	            try {
	                ArrayList<KhuyenMai> listKhuyenMai1 = ctkmDAO.getListKhuyenMai();
	                for (KhuyenMai khuyenMai : listKhuyenMai1) {
	                    if (khuyenMai.getRs() == 0) {
	                        
	                        loadCTKMData(khuyenMai.getMaKhuyenMai(), khuyenMai.getTenChuongTrinh(), 
	                            khuyenMai.getNgayBatDau().toLocalDate(), khuyenMai.getNgayKetThuc().toLocalDate(), 
	                            khuyenMai.getDieuKienApDung(), khuyenMai.getTrangThaiKhuyenMai());
	                    }
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        });
	        
	        popupRoot.getChildren().addAll(title_layout_recovery, table_layout_recovery, layout_button_recovery);
	        
	        Scene popupScene = new Scene(popupRoot);
	        popupStage.setScene(popupScene);
	        popupStage.show();
	    });
		
		
		layout_button.setSpacing(40);
		layout_button.getChildren().addAll(btn_khoiPhuc, btn_themCTKM, btn_xoaCTKM, btn_capnhat);
		
		
		
		
		
		btn_xoaCTKM.setOnMouseClicked(event -> {
			if(hangchon == null)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông Báo");
				alert.setHeaderText(null);
				alert.setContentText("Chọn 1 CTKM để xóa");
				alert.showAndWait();
			}
			else {
				
			
				 	String maCTKM = ((Label) ((StackPane) hangchon.getChildren().get(0)).getChildren().get(0)).getText();
					try {
						if(ctkmDAO.xoaKhuyenMai(maCTKM))
						{
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Thông Báo");
							alert.setHeaderText(null);
							alert.setContentText("Xóa CTKM Thành Công");
							alert.showAndWait();
							
							FadeTransition ft = new FadeTransition(Duration.millis(300),hangchon);
							ft.setFromValue(1.0);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished(E ->{						
								table_desc.getChildren().remove(hangchon);
							});
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}
		});
		
		btn_xoaCTKM.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_xoaCTKM.setStyle(btn_xoaCTKM.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_xoaCTKM.setStyle(btn_xoaCTKM.getStyle());
			}
		});
		
		btn_themCTKM.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_themCTKM.setStyle(btn_themCTKM.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_themCTKM.setStyle(btn_themCTKM.getStyle());
			}
		});
		
		btn_capnhat.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_capnhat.setStyle(btn_capnhat.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_capnhat.setStyle(btn_capnhat.getStyle());
			}
		});
		
		btn_capnhat.setOnMouseClicked(e-> {
			if(hangchon == null )
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông Báo");
				alert.setHeaderText(null);
				alert.setContentText("Vui lòng chọn 1 CTKM để cập nhật");
				alert.showAndWait();
			}
			else {
				 GridPane selectedRow = hangchon;
				 
				 String maKM = ((Label)((StackPane)selectedRow.getChildren().get(0)).getChildren().get(0)).getText();
			     String tenKM = ((Label)((StackPane)selectedRow.getChildren().get(1)).getChildren().get(0)).getText();
			     String ngayBatDau = ((Label)((StackPane)selectedRow.getChildren().get(2)).getChildren().get(0)).getText();
			     String ngayKetThuc = ((Label)((StackPane)selectedRow.getChildren().get(3)).getChildren().get(0)).getText();
			     String dieuKienApDung = ((Label)((StackPane)selectedRow.getChildren().get(4)).getChildren().get(0)).getText();
			     String trangThai = ((Label)((StackPane)selectedRow.getChildren().get(5)).getChildren().get(0)).getText();
			     System.out.println(ngayBatDau);
			     
			     System.out.println(ngayKetThuc);
			     
			     LocalDate nbd = LocalDate.parse(ngayBatDau);
			     LocalDate nkt = LocalDate.parse(ngayKetThuc);
			     
			     LocalDateTime nbd1 = nbd.atStartOfDay();
			     LocalDateTime nkt1 = nkt.atStartOfDay();
			     
			     
			     try {
					double phantram = ctkmDAO.getPhanTramKhuyenMai(maKM);
					KhuyenMai KM = new KhuyenMai(maKM, tenKM, trangThai, dieuKienApDung, phantram, nbd1, nkt1);
				     
				     File file = new File("KhuyenMai.dat");
				        if (file.exists()) {
				            file.delete(); 
				        }
				     try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("KhuyenMai.dat"));
						oos.writeObject(KM);
						oos.flush();
						oos.close();
						System.out.println("Ghi Thành Công");
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			     
			     
			     
			}
		});
		
		
		noiDungChinh.getChildren().add(layout_button);
		
	}
	
	public void loadDuLieuKhoiPhuc(VBox targetPanel, String maCTKM, String tenCTKM, LocalDate ngaybatdau, LocalDate ngayketthuc, String dieuKienApDung, String trangthai) {
	    GridPane data = new GridPane();
	    
	    data.setHgap(10);
	    data.setAlignment(Pos.CENTER);
	    data.setMaxWidth(1250);
	    data.setTranslateX(20);
	    data.setPrefHeight(70);
	    data.setPadding(new Insets(0, 0, 0, 10));
	    
	    final GridPane hangchon1 = null;
	    
	    String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";
	    
	    Label lblMaCTKM = new Label(maCTKM);
	    Label lblTenCTKM = new Label(tenCTKM);
	    Label lblNgayBatDau = new Label(ngaybatdau.toString());
	    Label lblNgayKetThuc = new Label(ngayketthuc.toString());
	    Label lblDieuKienApDung = new Label(dieuKienApDung);
	    Label lblTrangThai = new Label(trangthai);
	    
	    String colorStyle = "";
	    if(trangthai.equalsIgnoreCase("kích hoạt"))
	    {	    	
	     colorStyle = "-fx-text-fill: green;";
	    }
	    else 
	    	colorStyle = "-fx-text-fill: red;";
	    lblMaCTKM.setStyle(baseStyle);
	    lblTenCTKM.setStyle(baseStyle);
	    lblNgayBatDau.setStyle(baseStyle);
	    lblNgayKetThuc.setStyle(baseStyle);
	    lblDieuKienApDung.setStyle(baseStyle);
	    lblTrangThai.setStyle(baseStyle + colorStyle);
	    
	    lblMaCTKM.setTranslateX(-70);
	    lblTenCTKM.setTranslateX(-50);
	    lblNgayBatDau.setTranslateX(-20);
	    lblNgayKetThuc.setTranslateX(-20);
	    lblDieuKienApDung.setTranslateX(50);
	    lblTrangThai.setTranslateX(100);
	    
	    lblTenCTKM.setWrapText(true);
	    lblDieuKienApDung.setWrapText(true);
	    
	    
	    StackPane paneData1 = new StackPane(lblMaCTKM);
	    StackPane paneData2 = new StackPane(lblTenCTKM);
	    StackPane paneData3 = new StackPane(lblNgayBatDau);
	    StackPane paneData4 = new StackPane(lblNgayKetThuc);
	    StackPane paneData5 = new StackPane(lblDieuKienApDung);
	    StackPane paneData6 = new StackPane(lblTrangThai);
	    
	    paneData1.setPrefWidth(150);
	    paneData2.setPrefWidth(180);
	    paneData3.setPrefWidth(150);
	    paneData4.setPrefWidth(150);
	    paneData5.setPrefWidth(200);
	    paneData6.setPrefWidth(130);
	    
	    paneData1.setAlignment(Pos.CENTER);
	    paneData2.setAlignment(Pos.CENTER);
	    paneData3.setAlignment(Pos.CENTER);
	    paneData4.setAlignment(Pos.CENTER);
	    paneData5.setAlignment(Pos.CENTER);
	    paneData6.setAlignment(Pos.CENTER);
	    
	    data.add(paneData1, 0, 0);
	    data.add(paneData2, 1, 0);
	    data.add(paneData3, 2, 0);
	    data.add(paneData4, 3, 0);
	    data.add(paneData5, 4, 0);
	    data.add(paneData6, 5, 0);
	    
	    String normalStyle = """
	        -fx-background-color: rgba(0, 186, 203, 0.3);
	        -fx-background-radius: 15px;
	        -fx-border-radius: 15px;
	        -fx-border-color: #B6D0D3;
	        -fx-border-width: 1px;
	    """;

	    String selectedStyle = """
	        -fx-background-color: rgba(0, 186, 203, 0.9);
	        -fx-background-radius: 15px;
	        -fx-border-radius: 15px;
	        -fx-border-color: #00BACB;
	        -fx-border-width: 2px;
	    """;
	    
	    data.setStyle(normalStyle);
	    
	    final GridPane dongHienTai1 = data;
	    
	    data.setOnMouseEntered(e -> {
	        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
	        scaleUp.setToX(1.02);
	        scaleUp.setToY(1.02);
	        scaleUp.play();
	    });

	    data.setOnMouseExited(e -> {
	        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
	        scaleDown.setToX(1.0);
	        scaleDown.setToY(1.0);
	        scaleDown.play();
	    });
	    
	    data.setOnMouseClicked(e -> {
	        if (hangchonPopup[0] != null && hangchonPopup[0] != dongHienTai1) {
	            hangchonPopup[0].setStyle(normalStyle);
	        }
	         
	        hangchonPopup[0] = dongHienTai1;
	        dongHienTai1.setStyle(selectedStyle);
	        btn_khoiPhuc1.setDisable(false); 
	    });
	    
	    targetPanel.getChildren().add(data);
	}
	
	public void loadCTKMData(String maCT, String tenCT, LocalDate ngaybatdau, LocalDate ngayketthuc, String doituong, String trangthai) {
	    GridPane data = new GridPane();
	    
	    data.setHgap(10);
	    data.setAlignment(Pos.CENTER);
	    data.setMaxWidth(1270);
	    data.setTranslateX(20);
	    data.setPrefHeight(70);
	    data.setPadding(new Insets(0, 0, 0, 10));
	    
	    
	    String trangthai1 = "";
	    LocalDate hientai = LocalDate.now();
	    if(!hientai.isBefore(ngaybatdau) && !hientai.isAfter(ngayketthuc))
	    {
	    	trangthai1 = "Kích Hoạt";
	    }
	    else
	    	trangthai1 = "Kết Thúc";
	    
	    
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String ngaybd = ngaybatdau.format(dtf);
	    String ngaykt = ngayketthuc.format(dtf);
	    
	    String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";
	    
	    Label lblMaCT = new Label(maCT);
	    Label lblTenCT = new Label(tenCT);
	    Label lblNgayBD = new Label(ngaybd);
	    Label lblNgayKT = new Label(ngaykt);
	    Label lbldieuKienApDung = new Label(doituong);
	    
	    Label lblTrangThai = new Label(trangthai1);
	    String colorStyle = "";
	    if(trangthai1.equalsIgnoreCase("kích hoạt"))
	    {	    	
	     colorStyle = "-fx-text-fill: green;";
	    }
	    else 
	    	colorStyle = "-fx-text-fill: red;";
	    
	    lblMaCT.setStyle(baseStyle);
	    lblTenCT.setStyle(baseStyle);
	    lblNgayBD.setStyle(baseStyle);
	    lblNgayKT.setStyle(baseStyle);
	    lbldieuKienApDung.setStyle(baseStyle);
	    lblTrangThai.setStyle(baseStyle + colorStyle);
	   
	    lblMaCT.setTranslateX(-140);
	    lblTenCT.setTranslateX(-90);
	    lblNgayBD.setTranslateX(-40);
	    lblNgayKT.setTranslateX(-40);
	    lblTrangThai.setTranslateX(60);
	    lbldieuKienApDung.setTranslateX(5);
	    lblTenCT.setWrapText(true);
	    
	    StackPane paneData1 = new StackPane(lblMaCT);
	    StackPane paneData2 = new StackPane(lblTenCT);
	    StackPane paneData3 = new StackPane(lblNgayBD);
	    StackPane paneData4 = new StackPane(lblNgayKT);
	    StackPane paneData5 = new StackPane(lbldieuKienApDung);
	    StackPane paneData6 = new StackPane(lblTrangThai);
	    
	    lbldieuKienApDung.setWrapText(true);
	    
	    paneData1.setPrefWidth(150);
	    paneData2.setPrefWidth(180);
	    paneData3.setPrefWidth(150);
	    paneData4.setPrefWidth(140);
	    paneData5.setPrefWidth(250);
	    paneData6.setPrefWidth(130);
	    
	    paneData1.setAlignment(Pos.CENTER);
	    paneData2.setAlignment(Pos.CENTER);
	    paneData3.setAlignment(Pos.CENTER);
	    paneData4.setAlignment(Pos.CENTER);
	    paneData5.setAlignment(Pos.CENTER);
	    paneData6.setAlignment(Pos.CENTER);
	    
	    data.add(paneData1, 0, 0);
	    data.add(paneData2, 1, 0);
	    data.add(paneData3, 2, 0);
	    data.add(paneData4, 3, 0);
	    data.add(paneData5, 4, 0);
	    data.add(paneData6, 5, 0);
	    
	    String normalStyle = """
	    	    -fx-background-color: rgba(0, 186, 203, 0.3);
	    	    -fx-background-radius: 15px;
	    	    -fx-border-radius: 15px;
	    	    -fx-border-color: #B6D0D3;
	    	    -fx-border-width: 1px;
	    	""";

		String hoverStyle = """
	    	    -fx-background-color: rgba(0, 186, 203, 0.6);
	    	    -fx-background-radius: 15px;
	    	    -fx-border-radius: 15px;
	    	    -fx-border-color: #00BACB;
	    	    -fx-border-width: 1px;
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
	    
	    String style_macdinh = "-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;";
	    String style_thaydoi = "-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;-fx-border-color:#00BACB;-fx-border-width:2px;";
	    
	    dongHienTai.setOnMouseClicked(e -> {
	        if (hangchon == dongHienTai) {
	            // Bỏ chọn nếu click lại hàng đang chọn
	            hangchon = null;
	            dongHienTai.setStyle(normalStyle);
	        } else {
	            // Bỏ chọn hàng cũ
	            if (hangchon != null) {
	                hangchon.setStyle(normalStyle);
	            }
	            
	            // Chọn hàng mới
	            hangchon = dongHienTai;
	            dongHienTai.setStyle(selectedStyle);
	        }
	        
	        btn_xoaCTKM.setDisable(hangchon == null);
	        btn_capnhat.setDisable(hangchon == null);
	        
	        // Ghi file
	        if (hangchon != null) {
	            String maCTKM = ((Label)((StackPane)hangchon.getChildren().get(0)).getChildren().get(0)).getText();
	            
	            try {
	                KhuyenMai km = ctkmDAO.getKhuyenMaiBangMa(maCTKM);
	                
	                File file = new File("KhuyenMai.dat");
	                if(file.exists()) {
	                    file.delete();
	                }
	                
	                ObjectOutputStream oos;
	                try {
	                    oos = new ObjectOutputStream(new FileOutputStream("KhuyenMai.dat"));
	                    oos.writeObject(km);
	                    System.out.println("Ghi Thành Cong");
	                } catch (FileNotFoundException e1) {
	                    e1.printStackTrace();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        }
	    });

	    dongHienTai.setOnMouseEntered(e -> {
	        if (hangchon != dongHienTai) {
	            dongHienTai.setStyle(hoverStyle);
	        }
	        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), dongHienTai);
	        scaleUp.setToX(1.02);
	        scaleUp.setToY(1.02);
	        scaleUp.play();
	    });

	    dongHienTai.setOnMouseExited(e -> {
	        if (hangchon != dongHienTai) {
	            dongHienTai.setStyle(normalStyle);
	        } else {
	            dongHienTai.setStyle(selectedStyle);
	        }
	        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), dongHienTai);
	        scaleDown.setToX(1.0);
	        scaleDown.setToY(1.0);
	        scaleDown.play();
	    });
	    
	    table_desc.getChildren().add(data);
	}  
	
	public void hienThi() {
	    if (table_desc != null) {
	        table_desc.getChildren().clear();
	    }
	    hangchon = null;
	    btn_xoaCTKM.setDisable(true);
	    btn_capnhat.setDisable(true);
	    
	    try {
	        listKhuyenMai = ctkmDAO.getListKhuyenMai();
	        
	        // ✅ Load dữ liệu vào table
	        for (KhuyenMai khuyenMai : listKhuyenMai) {
	            if (khuyenMai.getRs() == 0) {
	                loadCTKMData(
	                    khuyenMai.getMaKhuyenMai(),
	                    khuyenMai.getTenChuongTrinh(),
	                    khuyenMai.getNgayBatDau().toLocalDate(),
	                    khuyenMai.getNgayKetThuc().toLocalDate(),
	                    khuyenMai.getDieuKienApDung(),
	                    khuyenMai.getTrangThaiKhuyenMai()
	                );
	            }
	        }
	        
	        System.out.println("✓ Refresh table thành công!");
	        
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi refresh table: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}