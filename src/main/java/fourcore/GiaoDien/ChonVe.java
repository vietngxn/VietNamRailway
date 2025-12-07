package fourcore.GiaoDien;

import fourcore.Control.BanVeControl;
import fourcore.Entity.*;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.GaTauDao;
import fourcore.dao.GheNgoiDAO;
import fourcore.dao.ToaTauDAO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ChonVe extends Application {
    private GaTauDao gaTauDao =  new GaTauDao();
    private  BanVe banVe = new BanVe();
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
    HBox chuyenTauMenu = new HBox();
    private HBox danhSachToaTauBox;
    private VBox danhSachGheBox;
    private StackPane ghePane= new StackPane();;
    private  GridPane danhSachGheGridPane = new GridPane();
    private HBox chuThichVaBtnBox;
    private VBox chuThichGheBox;
    public Label tenToaVaKhoang = new Label();
    Label khoangLbl = new Label();
    InputStream fontTieuDeToaTauInput = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
    Font fontTieuDeToaTau = Font.loadFont(fontTieuDeToaTauInput, 18);
    private HBox layout_button;
    public ArrayList<GheNgoi> gheDuocChon = new ArrayList<>();
    String gaDen;
    GheNgoiDAO gheNgoiDAO = new GheNgoiDAO();
    ArrayList<ToaTau> dsToaTrenChuyen = null;
    public final List<GheTrenChuyenTau> gheDangChonList = new ArrayList<>();
    Button btn_trolai = new Button("Trở lại");
    Button btn_tieptuc = new Button("Tiếp Tục");
    public ChonVe() throws SQLException {

    }
    public ChonVe(BanVe banVe) throws SQLException {
        this.banVe = banVe;
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            clearFileGheDangChon();
            System.out.println("Run Chon ve");
            System.out.println("Get list:....");

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

            //Label tong quan
            Label tongQuanLabel = new Label("Bán vé");
            InputStream interFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font fontTongQuan = Font.loadFont(interFont, 40);
            tongQuanLabel.setFont(fontTongQuan);
            tongQuanLabel.setTranslateX(20);
            noiDungChinh.getChildren().add(tongQuanLabel);

            chuyenTauMenu = new HBox();
            chuyenTauMenu.setMinWidth(1200);
//            HBox chuyenTauCont = new HBox();
//            StackPane chuyenTauBox = new StackPane();
//            ImageView chuyenTauImage = new ImageView(getClass().getResource("/img/TauHoaIcon.png").toExternalForm());
//            chuyenTauBox.getChildren().addAll(chuyenTauImage);
//            Label maChuyenTau = new Label ("SE1");
//            maChuyenTau.setFont(labelFont);
//            maChuyenTau.setTranslateX(-100);
//            maChuyenTau.setTranslateY(5);
//
//            HBox tgDi = new HBox();
//            InputStream interBold13Load = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
//            Font interBold13 = Font.loadFont(interBold13Load, 15);
//
//            InputStream interRegular13Load = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Regular.ttf");
//            Font interRegular13 = Font.loadFont(interRegular13Load, 15);
//
//            Label tgDiLabel = new Label ("TG đi");
//            tgDiLabel.setFont(interBold13);
//            tgDiLabel.setTranslateX(-10);
//            Label tgDiDuLieu = new Label("25/05 12:05");
//            tgDiDuLieu.setFont(interRegular13);
//            tgDi.setTranslateX(-174);
//            tgDi.setTranslateY(42);
//            tgDi.getChildren().addAll(tgDiLabel, tgDiDuLieu);
//
//            HBox tgDen = new HBox();
//            Label tgDenLabel = new Label ("TG đến");
//            tgDenLabel.setFont(interBold13);
//            tgDenLabel.setTranslateX(-10);
//            Label tgDenDuLieu = new Label("25/05 12:05");
//            tgDenDuLieu.setFont(interRegular13);
//            tgDen.setTranslateX(-299);
//            tgDen.setTranslateY(72);
//            tgDen.getChildren().addAll(tgDenLabel, tgDenDuLieu);
//
//            VBox slChoDatBox = new VBox();
//            Label slChoDatLabel = new Label("SL chỗ đặt");
//            slChoDatLabel.setFont(interRegular13);
//            Label soChoDat = new Label("120");
//            soChoDat.setTranslateX(20);
//            soChoDat.setFont(interBold13);
//            slChoDatBox.getChildren().addAll( slChoDatLabel, soChoDat);
//            slChoDatBox.setTranslateX(-440);
//            slChoDatBox.setTranslateY(102);
//
//            VBox slChoTrongBox = new VBox();
//            Label slChoTrongLabel = new Label("Trống");
//            slChoTrongLabel.setFont(interRegular13);
//            Label soChoTrong = new Label("8");
//            soChoTrong.setTranslateX(20);
//            soChoTrong.setFont(interBold13);
//            slChoTrongBox.getChildren().addAll( slChoTrongLabel, soChoTrong);
//            slChoTrongBox.setTranslateX(-420);
//            slChoTrongBox.setTranslateY(102);
//
//            chuyenTauCont.getChildren().add(chuyenTauBox);
//            chuyenTauCont.getChildren().add(maChuyenTau);
//            chuyenTauCont.getChildren().add(tgDi);
//            chuyenTauCont.getChildren().add(tgDen);
//            chuyenTauCont.getChildren().add(slChoDatBox);
//            chuyenTauCont.getChildren().add(slChoTrongBox);;
//            noiDungChinh.getChildren().add(chuyenTauCont);






// DATA LOAD----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            noiDungChinh.getChildren().add(chuyenTauMenu);



            danhSachToaTauBox = new HBox();
            noiDungChinh.getChildren().add(danhSachToaTauBox);
            danhSachGheBox = new VBox();


            danhSachGheBox.setMaxWidth(800);
            danhSachGheBox.setPrefHeight(350);
            danhSachGheBox.setStyle("-fx-border-color: #091723; " +
                    "-fx-border-width: 2; " +
                    "-fx-border-radius: 10; " +
                    "-fx-padding: 10;");
            danhSachGheBox.setTranslateX(200);
            danhSachGheBox.setTranslateY(100);






//-----------------------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------



            
            tenToaVaKhoang.setTranslateX(400);
            tenToaVaKhoang.setTranslateY(80);
            tenToaVaKhoang.setFont(fontTieuDeToaTau);

            noiDungChinh.getChildren().add(tenToaVaKhoang);


            ArrayList<ChuyenTau> docLai = new ArrayList<>();
            danhSachGheBox.getChildren().add(khoangLbl);
            danhSachGheBox.getChildren().add(danhSachGheGridPane);
            BanVeControl banVeControl = new BanVeControl();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listChuyenTauFiltered.dat"))) {
                docLai = (ArrayList<ChuyenTau>) ois.readObject();
                System.out.println("Dữ liệu đọc được: " + docLai);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gaDen.dat"))) {
                gaDen = ois.readObject().toString();
                System.out.println("Dữ liệu ga den đọc được: " + gaDen);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            gheDangChonList.clear();
            hienThiDanhSachChuyenTau(docLai);
            noiDungChinh.getChildren().add(danhSachGheBox);

            chuThichVaBtnBox = new HBox();
            chuThichVaBtnBox.setTranslateY(120);
            chuThichVaBtnBox.setTranslateX(40);

            VBox chuThichToaBox =  new VBox();

            HBox chuThichToaFull = new HBox();
            ImageView toaFullImg = new ImageView(getClass().getResource("/img/toaFullCho.png").toExternalForm());
            Label toaFullLabel = new Label("Toa đã đầy chỗ");
            toaFullLabel.setFont(fontTieuDeToaTau);
            toaFullLabel.setStyle("-fx-text-fill: #EC6BC5;");
            toaFullLabel.setTranslateY(5);
            toaFullLabel.setTranslateX(12);
            chuThichToaFull.getChildren().addAll(toaFullImg,toaFullLabel);

            HBox chuThichToaChon = new HBox();
            chuThichToaChon.setPadding(new Insets(30,0,0,0));
            ImageView toaChonImg = new ImageView(getClass().getResource("/img/toaDangChon.png").toExternalForm());
            Label toaChonLabel = new Label("Toa đang được chọn");
            toaChonLabel.setFont(fontTieuDeToaTau);
            toaChonLabel.setStyle("-fx-text-fill: #00EAC3;");
            toaChonLabel.setTranslateY(5);
            toaChonLabel.setTranslateX(12);
            chuThichToaChon.getChildren().addAll(toaChonImg,toaChonLabel);


            HBox chuThichToaTrong = new HBox();
            chuThichToaTrong.setPadding(new Insets(30,0,0,0));
            ImageView toaTrongImg = new ImageView(getClass().getResource("/img/thantautrong.png").toExternalForm());
            Label toaTrongLabel = new Label("Toa đang trống");
            toaTrongLabel.setFont(fontTieuDeToaTau);
            toaTrongLabel.setStyle("-fx-text-fill: #4D99E4;");
            toaTrongLabel.setTranslateY(5);
            toaTrongLabel.setTranslateX(12);
            chuThichToaTrong.getChildren().addAll(toaTrongImg,toaTrongLabel);



            chuThichToaBox.getChildren().addAll(chuThichToaFull,chuThichToaChon,chuThichToaTrong);


            chuThichGheBox = new VBox();
            chuThichGheBox.setTranslateX(100);


            HBox chuThichGheFull = new HBox();
            ImageView gheFullImg = new ImageView(getClass().getResource("/img/gheDaDat.png").toExternalForm());
            ImageView giuongFullImg = new ImageView(getClass().getResource("/img/giuongHetCho.png").toExternalForm());
            giuongFullImg.setTranslateX(20);
            Label gheFullLabel = new Label("Ghế/Giường đã được bán");
            gheFullLabel.setFont(fontTieuDeToaTau);
            gheFullLabel.setStyle("-fx-text-fill: #EC6BC5;");
            gheFullLabel.setTranslateY(8);
            gheFullLabel.setTranslateX(22);
            chuThichGheFull.getChildren().addAll(gheFullImg,giuongFullImg,gheFullLabel);

            chuThichGheBox.getChildren().addAll(chuThichGheFull);

            HBox chuThichGheChon = new HBox();
            chuThichGheChon.setTranslateY(10);

            ImageView gheChonImg = new ImageView(getClass().getResource("/img/gheDangChon.png").toExternalForm());
            ImageView giuongChonImg = new ImageView(getClass().getResource("/img/giuongDangChon.png").toExternalForm());
            giuongChonImg.setTranslateX(20);
            Label gheChonLabel = new Label("Ghế/Giường đang được chọn");
            gheChonLabel.setFont(fontTieuDeToaTau);
            gheChonLabel.setStyle("-fx-text-fill: #00EAC3;");
            gheChonLabel.setTranslateY(8);
            gheChonLabel.setTranslateX(22);
            chuThichGheChon.getChildren().addAll(gheChonImg,giuongChonImg,gheChonLabel);

            chuThichGheBox.getChildren().addAll(chuThichGheChon);


            HBox chuThichGheTrong = new HBox();
            chuThichGheTrong.setTranslateY(20);

            ImageView gheTrongImg = new ImageView(getClass().getResource("/img/gheTrong.png").toExternalForm());
            Label gheTrongLabel = new Label("Ghế đang trống");
            gheTrongLabel.setFont(fontTieuDeToaTau);
            gheTrongLabel.setStyle("-fx-text-fill: #4D99E4;");
            gheTrongLabel.setTranslateY(5);
            gheTrongLabel.setTranslateX(12);
            chuThichGheTrong.getChildren().addAll(gheTrongImg,gheTrongLabel);

            chuThichGheBox.getChildren().addAll(chuThichGheTrong);

            HBox chuThichGheLuuDong = new HBox();
            chuThichGheLuuDong.setTranslateY(30);

            ImageView gheLuuDongImg = new ImageView(getClass().getResource("/img/gheluudong.png").toExternalForm());
            gheLuuDongImg.setFitWidth(37);
            gheLuuDongImg.setFitHeight(43);
            Label gheLuuDongLabel = new Label("Ghế lưu động");
            gheLuuDongLabel.setFont(fontTieuDeToaTau);
            gheLuuDongLabel.setStyle("-fx-text-fill: #CBC400;");
            gheLuuDongLabel.setTranslateY(8);
            gheLuuDongLabel.setTranslateX(12);
            chuThichGheLuuDong.getChildren().addAll(gheLuuDongImg,gheLuuDongLabel);

            chuThichGheBox.getChildren().addAll(chuThichGheLuuDong);

            HBox chuThichGheKhongKhaDung = new HBox();
            chuThichGheKhongKhaDung.setTranslateY(40);

            ImageView gheKhongKhaDungImg = new ImageView(getClass().getResource("/img/gheDisable.png").toExternalForm());
            gheKhongKhaDungImg.setFitWidth(37);
            gheKhongKhaDungImg.setFitHeight(43);
            Label gheKhongKhaDungLabel = new Label("Ghế không khả dụng");
            gheKhongKhaDungLabel.setFont(fontTieuDeToaTau);
            gheKhongKhaDungLabel.setStyle("-fx-text-fill: #929292;");
            gheKhongKhaDungLabel.setTranslateY(8);
            gheKhongKhaDungLabel.setTranslateX(12);
            chuThichGheKhongKhaDung.getChildren().addAll(gheKhongKhaDungImg,gheKhongKhaDungLabel);

            chuThichGheBox.getChildren().addAll(chuThichGheKhongKhaDung);


            chuThichVaBtnBox.getChildren().addAll(chuThichToaBox,chuThichGheBox);

            noiDungChinh.getChildren().add(chuThichVaBtnBox);


            noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
            noiDungChinh.setPrefWidth(1200);


            tao_button(root);
            BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
            root.setLeft(menuList);
            root.setCenter(noiDungChinh);
            primaryStage.setFullScreen(true);

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public LocalDateTime tinhThoiGian(String maHanhTrinh, LocalDateTime ngayGioDi) throws SQLException {
        String gaDen = "";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gaDen.dat"))) {
            gaDen = ois.readObject().toString();
            System.out.println("Dữ liệu ga đến đọc được: " + gaDen);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String maGa = gaTauDao.getMaGaTuTenGa(gaDen);
        System.out.println(maGa);
        Time gioDen = gaTauDao.getGioDiKeHoach(maHanhTrinh, maGa);
        int soNgayDiQua = gaTauDao.getSoNgayDiQua(maHanhTrinh, maGa);
        System.out.println(soNgayDiQua);
        LocalDateTime ngayGioDen = null;
        if (gioDen != null && ngayGioDi != null) {
            LocalDateTime baseDate = ngayGioDi.plusDays(soNgayDiQua);
            LocalTime localTimeDen = gioDen.toLocalTime();
            ngayGioDen = LocalDateTime.of(baseDate.toLocalDate(), localTimeDen);
        }

        return ngayGioDen;
    }


    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> danhSachChuyenTau) throws SQLException {
//        hienThiDanhSachChuyenTau(chuyenTauMenu,danhSachToaTauBox,tenToaVaKhoang, danhSachGheGridPane,khoangLbl);


        danhSachGheGridPane.getChildren().clear();
        Image defaultImage = new Image(getClass().getResource("/img/TauHoaIcon.png").toExternalForm());
        Image selectedImage = new Image(getClass().getResource("/img/TauHoaIconChoose.png").toExternalForm());
        InputStream interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
        Font labelFont =  Font.loadFont(interBoldFont, 14);
        List<ImageView> imageViews = new ArrayList<>();

        for (ChuyenTau c : danhSachChuyenTau) {

            HBox chuyenTauCont = new HBox();
            ScrollPane chuyenTauScroll = new ScrollPane(chuyenTauCont);
            chuyenTauScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            chuyenTauScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            chuyenTauCont.setPrefWidth(200);
            StackPane chuyenTauBox = new StackPane();
            ImageView chuyenTauImage = new ImageView(defaultImage);
            chuyenTauBox.getChildren().addAll(chuyenTauImage);
            String maChuyenTau = c.getTau().getLoaiTau().getTenLoaiTau();
            Label maChuyenTaulbl = new Label (maChuyenTau);
            maChuyenTaulbl.setFont(labelFont);
//            maChuyenTaulbl.setTranslateX(-100);
            maChuyenTaulbl.setTranslateY(-80);

            HBox tgDi = new HBox();
            InputStream interBold13Load = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font interBold13 = Font.loadFont(interBold13Load, 15);

            InputStream interRegular13Load = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Regular.ttf");
            Font interRegular13 = Font.loadFont(interRegular13Load, 15);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");

            Label tgDiLabel = new Label ("TG đi");
            tgDiLabel.setFont(interBold13);
            tgDiLabel.setTranslateX(-10);
            LocalDateTime tgDiDuLieu = c.getNgayGioDi();
            String tgDiDuLieuToString = tgDiDuLieu.format(formatter);
            Label tgDiDuLieuLbl = new Label(tgDiDuLieuToString);
            tgDiDuLieuLbl.setFont(interRegular13);
            tgDi.setTranslateX(30);
            tgDi.setTranslateY(42);
            tgDi.getChildren().addAll(tgDiLabel, tgDiDuLieuLbl);

            HBox tgDen = new HBox();
            Label tgDenLabel = new Label ("TG đến");
            tgDenLabel.setFont(interBold13);
            tgDenLabel.setTranslateX(-10);
            LocalDateTime tgDenDuLieu = tinhThoiGian(c.getHanhTrinh().getMaHanhTrinh(), c.getNgayGioDi());
            String tgDenDuLieuToString = tgDenDuLieu.format(formatter);
            Label tgDenDuLieuLbl = new Label(tgDenDuLieuToString);
            tgDenDuLieuLbl.setFont(interRegular13);
            tgDen.setTranslateX(26);
            tgDen.setTranslateY(72);
            tgDen.getChildren().addAll(tgDenLabel, tgDenDuLieuLbl);

            VBox slChoDatBox = new VBox();
            Label slChoDatLabel = new Label("SL chỗ đặt");
            slChoDatLabel.setFont(interRegular13);
            gheNgoiDAO.getListTrenChuyenTau();
            int soChoDaDat = gheNgoiDAO.tongSoGheTrenChuyen(c.getMaChuyenTau())- gheNgoiDAO.soGheTrongTrenChuyen(c.getMaChuyenTau());
            Label soChoDat = new Label(""+ soChoDaDat);
            soChoDat.setTranslateX(20);
            soChoDat.setFont(interBold13);
            slChoDatBox.getChildren().addAll( slChoDatLabel, soChoDat);
            slChoDatBox.setTranslateX(10);
            slChoDatBox.setTranslateY(102);

            VBox slChoTrongBox = new VBox();
            Label slChoTrongLabel = new Label("Trống");
            slChoTrongLabel.setFont(interRegular13);
            Label soChoTrong = new Label(""+ gheNgoiDAO.soGheTrongTrenChuyen(c.getMaChuyenTau()));
            soChoTrong.setTranslateX(20);
            soChoTrong.setFont(interBold13);
            slChoTrongBox.getChildren().addAll( slChoTrongLabel, soChoTrong);
            slChoTrongBox.setTranslateX(100);
            slChoTrongBox.setTranslateY(102);


            chuyenTauBox.getChildren().add(maChuyenTaulbl);
            chuyenTauBox.getChildren().add(tgDi);
            chuyenTauBox.getChildren().add(tgDen);
            chuyenTauBox.getChildren().add(slChoDatBox);
            chuyenTauBox.getChildren().add(slChoTrongBox);

            chuyenTauCont.getChildren().add(chuyenTauBox);
            chuyenTauCont.setPadding(new Insets(0,20,0,0));

            chuyenTauMenu.getChildren().add(chuyenTauCont);
            imageViews.add(chuyenTauImage);
            chuyenTauCont.setUserData(c);
            chuyenTauCont.setOnMouseClicked(event -> {
                for (ImageView iv : imageViews) {
                    iv.setImage(defaultImage);
                }
                danhSachToaTauBox.getChildren().clear();
                chuyenTauImage.setImage(selectedImage);
                ChuyenTau selectedChuyenTau = (ChuyenTau) chuyenTauCont.getUserData();
                ChuyenTauDAO chuyenTauDAO = null;
                try {
                    chuyenTauDAO = new ChuyenTauDAO();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                ChuyenTau chuyenDuocSelect = null;
                try {
                    chuyenDuocSelect = chuyenTauDAO.getChuyenTauBangMa(selectedChuyenTau.getMaChuyenTau());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(selectedChuyenTau.getMaChuyenTau());
                ToaTauDAO toaTauDAO = null;
                try {
                    toaTauDAO = new ToaTauDAO();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    dsToaTrenChuyen = toaTauDAO.getListToaTauByMaCT(chuyenDuocSelect.getMaChuyenTau());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                System.out.println(dsToaTrenChuyen.size());
                ImageView dauTauImg = new ImageView(getClass().getResource("/img/dautau.png").toExternalForm());
                dauTauImg.setFitWidth(100);
                dauTauImg.setFitHeight(55);
                danhSachToaTauBox.setSpacing(10);
                danhSachToaTauBox.setAlignment(Pos.CENTER_RIGHT);
                danhSachToaTauBox.setPadding(new  Insets(0,200,0,0));
                danhSachToaTauBox.setTranslateY(50);
                List<ImageView> toaImageViews = new ArrayList<>();

                for (int i = dsToaTrenChuyen.size()-1; i >= 0; i--) {
                    final int toaSo = i+1;
                    // Tạo ImageView với ảnh mặc định
                    ImageView toaImg;
                    try {
                        if(checkToaTrong(dsToaTrenChuyen.get(i).getMaToaTau())==true){
                             toaImg = new ImageView(getClass().getResource("/img/thantaukin.png").toExternalForm());

                        }else {
                             toaImg = new ImageView(getClass().getResource("/img/thantautrong.png").toExternalForm());

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    toaImg.setFitWidth(90);
                    toaImg.setFitHeight(50);
                    toaImg.setUserData(dsToaTrenChuyen.get(i));
                    toaImageViews.add(toaImg);
                    ToaTau toa = dsToaTrenChuyen.get(i);
                    Popup popup = new Popup();
                    Label popupLabel = new Label();
                    popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
                    popup.getContent().add(popupLabel);

                    toaImg.setOnMouseEntered(e -> {
                        String content = String.format(
                                "Mã toa: %s\nLoại: %s",
                                toa.getMaToaTau(),
                                toa.getLoaiToaTau().getTenLoaiToaTau()
                        );
                        popupLabel.setText(content);
                        popup.show(toaImg, e.getScreenX() + 10, e.getScreenY() + 10);
                    });
                // Vi tri cua chuot
                    toaImg.setOnMouseMoved(e -> {
                        popup.setX(e.getScreenX() + 10);
                        popup.setY(e.getScreenY() + 10);
                    });

                // An khi roi khoi
                    toaImg.setOnMouseExited(e -> {
                        popup.hide();
                    });
                    // Gán sự kiện click
                    int index = i;
                    toaImg.setOnMouseClicked(toaEvent -> {
                        // Đặt lại hình cho tất cả toa theo trạng thái thực tế
                        for (int j = 0; j < toaImageViews.size(); j++) {
                            ImageView iv = toaImageViews.get(j);
                            ToaTau toaKhac = (ToaTau) iv.getUserData();

                            try {
                                if (checkToaTrong(toaKhac.getMaToaTau())) {
                                    iv.setImage(new Image(getClass().getResource("/img/thantaukin.png").toExternalForm()));
                                } else {
                                    iv.setImage(new Image(getClass().getResource("/img/thantautrong.png").toExternalForm()));
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        // Đổi hình cho toa đang chọn
                        toaImg.setImage(new Image(getClass().getResource("/img/toaDangChon.png").toExternalForm()));

                        // Lấy thông tin toa được chọn
                        ToaTau selectedToa = (ToaTau) toaImg.getUserData();
                        System.out.println("Toa được chọn: " + selectedToa.getMaToaTau());
                        tenToaVaKhoang.setText("Toa số " + toaSo + ": " + selectedToa.getLoaiToaTau().getTenLoaiToaTau());

                        // Xử lý hiển thị layout
                        danhSachGheGridPane.getChildren().clear();
                        try {
                            if (selectedToa.getLoaiToaTau().getMaLoaiToaTau().equals("LTToa01")) {
                                khoangLbl.setText("");
                                hienThiLayoutGhe(danhSachGheGridPane, selectedToa.getMaToaTau(), selectedChuyenTau.getMaChuyenTau());
                            } else if (selectedToa.getLoaiToaTau().getMaLoaiToaTau().equals("LTToa02")) {
                                hienThiLayoutGiuong(danhSachGheGridPane, selectedToa.getMaToaTau(), selectedChuyenTau.getMaChuyenTau());
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });


                    // Thêm vào danh sách hiển thị
                    danhSachToaTauBox.getChildren().add(toaImg);
                }


                danhSachToaTauBox.getChildren().add(dauTauImg);


            });

        }



    }
    public boolean checkToaTrong(String maToa) throws SQLException {
        ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau =  gheNgoiDAO.getListTrenChuyenTau();

        int cnt=0;
        for (GheTrenChuyenTau gtc : listGheTrenChuyenTau){
            if((gtc.getGheNgoi().getToaTau().getMaToaTau().equals(maToa)) && gtc.getGheNgoi().getToaTau().getLoaiToaTau().getMaLoaiToaTau().equals("LTToa01") ){
                if(gtc.getTrangThaiGhe().equals("Đã bán")){
                    cnt++;
                }
                if(cnt==36){
                    return true;
                }
            }
            if((gtc.getGheNgoi().getToaTau().getMaToaTau().equals(maToa)) && gtc.getGheNgoi().getToaTau().getLoaiToaTau().getMaLoaiToaTau().equals("LTToa02") ){
                if(gtc.getTrangThaiGhe().equals("Đã bán")){
                    cnt++;
                }
                if(cnt==18){
                    return true;
                }
            }

        }
        return false;


    }
    public Button getTrolaiBtn(){
        return btn_trolai;
    }
    public void tao_button(BorderPane root) {

        layout_button = new HBox();
        layout_button.setPrefSize(1000, 100);
        layout_button.setTranslateY(70);
        layout_button.setTranslateX(800);



        btn_trolai.setPrefSize(150, 50);
        btn_trolai.setTranslateY(-90);
        btn_trolai.setTranslateX(150);

        btn_trolai.setStyle(
                "-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(to top, #CB002C, #D498A5);-fx-background-radius:15px;");

        btn_tieptuc.setPrefSize(150, 50);
        btn_tieptuc.setStyle(
                "-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(to top, #00BACB, #B6D0D3);-fx-background-radius:15px;");
        btn_tieptuc.setTranslateX(0);

        btn_trolai.hoverProperty().addListener((obs, oval, nval) -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_trolai);

            if (nval) {
                st.setToX(1.1);
                st.setToY(1.1);
                btn_trolai.setStyle(
                        "-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(to top, #CB002C, #D498A5);-fx-background-radius:15px;-fx-cursor:hand;");
            } else {
                st.setToX(1);
                st.setToY(1);
            }
            st.play();
        });

        btn_trolai.setOnMouseClicked(e -> {
            BanVe gdBanVe = null;
            try {
                gdBanVe = new BanVe();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Stage gdBanVeStage = new Stage();
            gdBanVe.start(gdBanVeStage);
            VBox gdBanVeMain = gdBanVe.getGDBanVe();
            root.setCenter(gdBanVeMain);
            System.out.println("click click");
        });

        btn_tieptuc.hoverProperty().addListener((obs, oval, nval) -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_tieptuc);
            if (nval) {
                st.setToX(1.1);
                st.setToY(1.1);
                btn_tieptuc.setStyle(
                        "-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(to top, #00BACB, #B6D0D3);-fx-background-radius:15px;-fx-cursor:hand;");
            } else {
                st.setToX(1);
                st.setToY(1);
            }
            st.play();
        });
        btn_tieptuc.setOnAction(event -> {
            try {
                if (gheDangChonList.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng chọn ít nhất một ghế trước khi tiếp tục!");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    alert.initOwner(stage);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();

                    return;
                }

                // Ghi danh sách ghế ra file
                File file = new File("ds_ghe_dang_chon.dat");
                if(file.exists()){
                    file.delete();
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(gheDangChonList);
                }
                System.out.println("✅ Đã ghi " + gheDangChonList.size() + " ghế vào file: " + file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        layout_button.getChildren().addAll(btn_trolai, btn_tieptuc);
        noiDungChinh.getChildren().add(layout_button);
    }


    private void clearFileGheDangChon() {
        File file = new File("ds_ghe_dang_chon.dat");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            System.out.println("🧹 Đã xóa nội dung file danh sách ghế: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Button getTiepTucBtn(){
        return btn_tieptuc;
    }
    public void hienThiLayoutGhe(GridPane danhSachGheGridPane, String maToaTau, String maChuyen) throws SQLException {
        danhSachGheGridPane.getChildren().clear();
        int soGhe = 36;
        int soCot = 9;
        int soHang = 4;
        int thuTuGhe = 36;
        for(int col=0; col<soCot; col++){
            for(int row=0; row<soHang; row++){
                if(gaDen.equals("Hà Nội")){
                    System.out.println("Khong luu dong");
                    ImageView gheTrongImg =  new ImageView(getClass().getResource("/img/gheTrong.png").toExternalForm());

                    final int soGheH = thuTuGhe;
                    GheTrenChuyenTau gtc = gheNgoiDAO.getGheTrenChuyenTau(soGheH,maToaTau,maChuyen);
                    if(gtc.getTrangThaiGhe().equals("Đã bán")){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDaDat.png").toExternalForm()));
                    }
                    if(gtc.getGheNgoi().isLuuDong() == true){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDisable.png").toExternalForm()));
                        System.out.println(gtc.getTrangThaiGhe());
                    }

                    gheTrongImg.setFitWidth(60);
                    gheTrongImg.setFitHeight(60);
                    Label soThuTuGheLabel =  new Label(String.valueOf(thuTuGhe));
                    InputStream fontGheInput = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf");
                    Font fontGhe = Font.loadFont(fontGheInput, 20);
                    soThuTuGheLabel.setFont(fontGhe);
                    soThuTuGheLabel.setTranslateX(3);
                    soThuTuGheLabel.setStyle("-fx-text-fill: #ffffff;");
                    StackPane ghePane = new StackPane();
                    ghePane.getChildren().addAll(gheTrongImg,soThuTuGheLabel);
                    if(thuTuGhe==22 || thuTuGhe==10){
                        ghePane.setPadding(new Insets(30,0,0,30));

                    }else
                    if(col==3 || col==6){
                        ghePane.setPadding(new Insets(0,0,0,30));
                    }else if(row==2){
                        ghePane.setPadding(new Insets(30,0,0,0));
                    }

                    danhSachGheGridPane.add(ghePane,col,row);
                    if(gtc.getTrangThaiGhe().equals("Còn trống") && gtc.getGheNgoi().isLuuDong() != true){
                        final boolean[] isSelected = {false};
                        ghePane.setOnMouseClicked(event -> {
                            isSelected[0] = !isSelected[0]; // Toggle chọn / hủy chọn
                            System.out.println("click");
                            if (isSelected[0]) {
                                // Chọn ghế
                                gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDangChon.png").toExternalForm()));
                                gheDangChonList.add(gtc); // ➕ thêm vào danh sách chọn
                                System.out.println("Ghế số " + soGheH + " đã chọn");
                            } else {
                                // Bỏ chọn
                                gheDangChonList.removeIf(g -> g.getGheNgoi().getSoGhe() == gtc.getGheNgoi().getSoGhe());
                                if (gtc.getGheNgoi().isLuuDong()) {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/gheluudong.png").toExternalForm()));
                                } else {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/gheTrong.png").toExternalForm()));
                                }
                                System.out.println("Ghế số " + soGheH + " bỏ chọn");
                            }
                        });

                    }


                    gheTrongImg.setUserData(gtc);

                    if(!gtc.getGheNgoi().isLuuDong()){
                        Popup popup = new Popup();
                        Label popupLabel = new Label();
                        popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
                        popup.getContent().add(popupLabel);


                        ghePane.setOnMouseEntered(e -> {
                            String content = String.format(
                                    "Loại: %s\nGiá ghế: %.1f",
                                    gtc.getGheNgoi().getLoaiGhe().getTenLoaiGhe(),
                                    gtc.getGiaTienGhe()

                            );
                            popupLabel.setText(content);
                            popup.show(gheTrongImg, e.getScreenX() + 10, e.getScreenY() + 10);
                        });
                        // Vi tri cua chuot
                        ghePane.setOnMouseMoved(e -> {
                            popup.setX(e.getScreenX() + 10);
                            popup.setY(e.getScreenY() + 10);
                        });

                        // An khi roi khoi
                        ghePane.setOnMouseExited(e -> {
                            popup.hide();
                        });
                    }


                    thuTuGhe--;
                }
                else {
                    System.out.println("Luu dong");
                    ImageView gheTrongImg =  new ImageView(getClass().getResource("/img/gheluudong.png").toExternalForm());

                    final int soGheH = thuTuGhe;
                    GheTrenChuyenTau gtc = gheNgoiDAO.getGheTrenChuyenTau(soGheH,maToaTau,maChuyen);

                    if(gtc.getTrangThaiGhe().equals("Đã bán")){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDaDat.png").toExternalForm()));
                    }
                    if(!gtc.getGheNgoi().isLuuDong()){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDisable.png").toExternalForm()));
                        System.out.println(gtc.getTrangThaiGhe());
                    }
                    gheTrongImg.setFitWidth(60);
                    gheTrongImg.setFitHeight(60);
                    Label soThuTuGheLabel =  new Label(String.valueOf(thuTuGhe));
                    InputStream fontGheInput = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf");
                    Font fontGhe = Font.loadFont(fontGheInput, 20);
                    soThuTuGheLabel.setFont(fontGhe);
                    soThuTuGheLabel.setTranslateX(3);
                    soThuTuGheLabel.setStyle("-fx-text-fill: #ffffff;");
                    StackPane ghePane = new StackPane();
                    ghePane.getChildren().addAll(gheTrongImg,soThuTuGheLabel);
                    if(thuTuGhe==22 || thuTuGhe==10){
                        ghePane.setPadding(new Insets(30,0,0,30));

                    }else
                    if(col==3 || col==6){
                        ghePane.setPadding(new Insets(0,0,0,30));
                    }else if(row==2){
                        ghePane.setPadding(new Insets(30,0,0,0));
                    }

                    danhSachGheGridPane.add(ghePane,col,row);
                    if(gtc.getTrangThaiGhe().equals("Còn trống") && gtc.getGheNgoi().isLuuDong() == true){
                        final boolean[] isSelected = {false};
                        ghePane.setOnMouseClicked(event -> {
                            isSelected[0] = !isSelected[0]; // Toggle chọn / hủy chọn

                            if (isSelected[0]) {
                                // Chọn ghế
                                gheTrongImg.setImage(new Image(getClass().getResource("/img/gheDangChon.png").toExternalForm()));
                                gheDangChonList.add(gtc); // ➕ thêm vào danh sách chọn
                                System.out.println("Ghế số " + soGheH + " đã chọn");
                            } else {
                                gheDangChonList.removeIf(g -> g.getGheNgoi().getSoGhe() == gtc.getGheNgoi().getSoGhe());
                                if (gtc.getGheNgoi().isLuuDong()) {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/gheluudong.png").toExternalForm()));
                                } else {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/gheTrong.png").toExternalForm()));
                                }
                                System.out.println("Ghế số " + soGheH + " bỏ chọn");
                            }
                        });
                    }


                    gheTrongImg.setUserData(gtc);


                    if(gtc.getGheNgoi().isLuuDong()){
                        Popup popup = new Popup();
                        Label popupLabel = new Label();
                        popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
                        popup.getContent().add(popupLabel);


                        ghePane.setOnMouseEntered(e -> {
                            String content = null;
                            try {
                                content = String.format(
                                        "Loại: %s\nGiá ghế: %.1f",
                                        gtc.getGheNgoi().getLoaiGhe().getTenLoaiGhe(),
                                        gtc.getChuyenTau().getGiaCuocTrenChuyenTau() * gaTauDao.getCuLiBangTenGa(gaDen) + gtc.getGiaTienGhe()

                                );
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            popupLabel.setText(content);
                            popup.show(gheTrongImg, e.getScreenX() + 10, e.getScreenY() + 10);
                        });
                        // Vi tri cua chuot
                        ghePane.setOnMouseMoved(e -> {
                            popup.setX(e.getScreenX() + 10);
                            popup.setY(e.getScreenY() + 10);
                        });

                        // An khi roi khoi
                        ghePane.setOnMouseExited(e -> {
                            popup.hide();
                        });
                    }

                    thuTuGhe--;
                }
                }
                }

        danhSachGheGridPane.setHgap(10);
        danhSachGheGridPane.setVgap(10);
        danhSachGheGridPane.setTranslateX(40);
        danhSachGheGridPane.setTranslateY(-5);
    }

    public void hienThiLayoutGiuong(GridPane danhSachGheGridPane, String maToaTau, String maChuyen) throws SQLException {
        int soCot = 6;
        int soHang = 3;
        int thuTuGhe = 18;
        khoangLbl.setText("                      Khoang 3                                       Khoang 2                                Khoang 1");
        khoangLbl.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        danhSachGheGridPane.getChildren().clear();

        for (int col = 0; col < soCot; col++) {
            for (int row = 0; row < soHang; row++) {
                final int soGheH = thuTuGhe;
                GheTrenChuyenTau gtc = gheNgoiDAO.getGheTrenChuyenTau(soGheH, maToaTau, maChuyen);
                if (gaDen.equals("Hà Nội")) {
                    ImageView gheTrongImg = new ImageView(getClass().getResource("/img/giuongConTrong.png").toExternalForm());
                    if(gtc.getTrangThaiGhe().equals("Đã bán")){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongHetCho.png").toExternalForm()));
                    }
                    if(gtc.getGheNgoi().isLuuDong() == true){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongDisable.png").toExternalForm()));
                        System.out.println(gtc.getTrangThaiGhe());
                    }

                    gheTrongImg.setFitWidth(80);
                    gheTrongImg.setFitHeight(80);

                    Label soThuTuGheLabel = new Label(String.valueOf(soGheH));
                    InputStream fontGheInput = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf");
                    Font fontGhe = Font.loadFont(fontGheInput, 20);
                    soThuTuGheLabel.setFont(fontGhe);
                    soThuTuGheLabel.setTranslateX(0);
                    soThuTuGheLabel.setTranslateY(-7);
                    soThuTuGheLabel.setStyle("-fx-text-fill: #ffffff;");

                    StackPane ghePane = new StackPane();
                    ghePane.getChildren().addAll(gheTrongImg, soThuTuGheLabel);

                    if (col == 2 || col == 4) {
                        ghePane.setPadding(new Insets(0, 0, 0, 50));
                    } else if (row == 2) {
                        ghePane.setPadding(new Insets(0, 0, 0, 0));
                    }

                    if(gtc.getTrangThaiGhe().equals("Còn trống") && gtc.getGheNgoi().isLuuDong() != true){
                        final boolean[] isSelected = {false};
                        ghePane.setOnMouseClicked(event -> {
                            isSelected[0] = !isSelected[0]; // Toggle chọn / hủy chọn

                            if (isSelected[0]) {
                                // Đổi sang ảnh ghế đang chọn
                                gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongDangChon.png").toExternalForm()));
                                gheDangChonList.add(gtc); // ➕ thêm vào danh sách chọn

                                System.out.println("Ghế số " + soGheH + " đã chọn");
                            } else {
                                gheDangChonList.removeIf(g -> g.getGheNgoi().getSoGhe() == gtc.getGheNgoi().getSoGhe());
                                if(gtc.getGheNgoi().isLuuDong() == true){
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/ghegiuongnamluudong.png").toExternalForm()));
                                }else {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongConTrong.png").toExternalForm()));
                                    System.out.println("Ghế số " + soGheH + " bỏ chọn");
                                }

                            }
                        });
                    }
                    gheTrongImg.setUserData(gtc);


                    Popup popup = new Popup();
                    Label popupLabel = new Label();
                    popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
                    popup.getContent().add(popupLabel);

                    ghePane.setOnMouseEntered(e -> {
                        String content = String.format(
                                "Loại: %s\nGiá ghế: %.1f",
                                gtc.getGheNgoi().getLoaiGhe().getTenLoaiGhe(),
                                gtc.getGiaTienGhe()

                        );
                        popupLabel.setText(content);
                        popup.show(gheTrongImg, e.getScreenX() + 10, e.getScreenY() + 10);
                    });
                    // Vi tri cua chuot
                    ghePane.setOnMouseMoved(e -> {
                        popup.setX(e.getScreenX() + 10);
                        popup.setY(e.getScreenY() + 10);
                    });

                    // An khi roi khoi
                    ghePane.setOnMouseExited(e -> {
                        popup.hide();
                    });

                    danhSachGheGridPane.add(ghePane, col, row);

                    thuTuGhe--; // giảm số giường vì bạn đếm từ 18 về 1
                } else {
                    ImageView gheTrongImg = new ImageView(getClass().getResource("/img/ghegiuongnamluudong.png").toExternalForm());
                    if(gtc.getTrangThaiGhe().equals("Đã bán")){
                        gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongHetCho.png").toExternalForm()));
                    }
                    if(!gtc.getGheNgoi().isLuuDong()){

                        gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongDisable.png").toExternalForm()));
                        System.out.println(gtc.getTrangThaiGhe());
                    }

                    gheTrongImg.setFitWidth(80);
                    gheTrongImg.setFitHeight(80);

                    Label soThuTuGheLabel = new Label(String.valueOf(soGheH));
                    InputStream fontGheInput = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf");
                    Font fontGhe = Font.loadFont(fontGheInput, 20);
                    soThuTuGheLabel.setFont(fontGhe);
                    soThuTuGheLabel.setTranslateX(0);
                    soThuTuGheLabel.setTranslateY(-7);
                    soThuTuGheLabel.setStyle("-fx-text-fill: #ffffff;");

                    StackPane ghePane = new StackPane();
                    ghePane.getChildren().addAll(gheTrongImg, soThuTuGheLabel);

                    if (col == 2 || col == 4) {
                        ghePane.setPadding(new Insets(0, 0, 0, 50));
                    } else if (row == 2) {
                        ghePane.setPadding(new Insets(0, 0, 0, 0));
                    }

                    if(gtc.getTrangThaiGhe().equals("Còn trống") && gtc.getGheNgoi().isLuuDong() == true){
                        final boolean[] isSelected = {false};
                        ghePane.setOnMouseClicked(event -> {
                            isSelected[0] = !isSelected[0]; // Toggle chọn / hủy chọn

                            if (isSelected[0]) {
                                // Đổi sang ảnh ghế đang chọn
                                gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongDangChon.png").toExternalForm()));
                                gheDangChonList.add(gtc); // ➕ thêm vào danh sách chọn

                                System.out.println("Ghế số " + soGheH + " đã chọn");
                            } else {
                                gheDangChonList.removeIf(g -> g.getGheNgoi().getSoGhe() == gtc.getGheNgoi().getSoGhe());
                                if(gtc.getGheNgoi().isLuuDong() == true){
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/ghegiuongnamluudong.png").toExternalForm()));
                                }else {
                                    gheTrongImg.setImage(new Image(getClass().getResource("/img/giuongConTrong.png").toExternalForm()));
                                    System.out.println("Ghế số " + soGheH + " bỏ chọn");
                                }

                            }
                        });
                    }
                    gheTrongImg.setUserData(gtc);


                    Popup popup = new Popup();
                    Label popupLabel = new Label();
                    popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
                    popup.getContent().add(popupLabel);

                    ghePane.setOnMouseEntered(e -> {
                        String content = String.format(
                                "Loại: %s\nGiá ghế: %.1f",
                                gtc.getGheNgoi().getLoaiGhe().getTenLoaiGhe(),
                                gtc.getGiaTienGhe()

                        );
                        popupLabel.setText(content);
                        popup.show(gheTrongImg, e.getScreenX() + 10, e.getScreenY() + 10);
                    });
                    // Vi tri cua chuot
                    ghePane.setOnMouseMoved(e -> {
                        popup.setX(e.getScreenX() + 10);
                        popup.setY(e.getScreenY() + 10);
                    });

                    // An khi roi khoi
                    ghePane.setOnMouseExited(e -> {
                        popup.hide();
                    });

                    danhSachGheGridPane.add(ghePane, col, row);

                    thuTuGhe--; // giảm số giường vì bạn đếm từ 18 về 1
                }

            }

            danhSachGheGridPane.setHgap(10);
            danhSachGheGridPane.setVgap(10);
            danhSachGheGridPane.setTranslateX(50);
            danhSachGheGridPane.setTranslateY(10);
        }
    }

    public VBox getGdChonVe(){
        return this.noiDungChinh;
    }

    public String getTenToaTau(ToaTau toaTau) {
        return toaTau.getLoaiToaTau().getTenLoaiToaTau();
    }
    public HBox getChuyenTauMenu() {
        return this.chuyenTauMenu;
    }
    public static void main(String[] args) throws SQLException {
        Application.launch(ChonVe.class, args);

    }

}