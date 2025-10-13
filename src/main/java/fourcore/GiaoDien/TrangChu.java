package fourcore.GiaoDien;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;

import fourcore.Entity.LichSuBanVe;


public class TrangChu extends Application {
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

    @Override
    public void start(Stage primaryStage) {
        try {
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
        //Label tong quan
            Label tongQuanLabel = new Label("Tổng quan");
            InputStream interFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font fontTongQuan = Font.loadFont(interFont, 40);
            tongQuanLabel.setFont(fontTongQuan);
            tongQuanLabel.setTranslateX(20);
            noiDungChinh.getChildren().add(tongQuanLabel);


            //Thong ke co ban
            HBox thongKeCoBanContainer = new HBox();
            thongKeCoBanContainer.setMinWidth(1000);

//			=======================
//			||THONG KE 01        ||
//			=======================
            VBox thongKeDoanhThu =  new VBox();
            thongKeDoanhThu.setStyle("-fx-background-color: #9EFFFF; -fx-background-radius: 12px;");
            thongKeDoanhThu.setMinWidth(250);
            thongKeDoanhThu.setMinHeight(160);
            thongKeDoanhThu.setTranslateY(40);
            thongKeDoanhThu.setTranslateX(20);
            Label thongKeDoanhThuLabel = new Label("Doanh thu trong ngày");
            thongKeDoanhThuLabel.setPadding(new Insets(10,0,0,13));
            InputStream workSansInput =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font thongKeDoanhThuLabelFont = Font.loadFont(workSansInput, 20);
            thongKeDoanhThuLabel.setFont(thongKeDoanhThuLabelFont);


            HBox tongSoTienBox = new HBox();
            tongSoTienBox.setPadding(new Insets(20,0,0,13));
            Label soTien = new Label("20.000.000");
            InputStream interFont1 = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");

            Font soTienFont = Font.loadFont(interFont1, 35);
            soTien.setFont(soTienFont);
            Label donViTien = new Label("đ");
            InputStream workSansInput1 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font donViTienFont = Font.loadFont(workSansInput1, 18);
            donViTien.setFont(donViTienFont);
            tongSoTienBox.getChildren().addAll(soTien,donViTien);

            HBox moTaDoanhThuBox = new HBox();
            moTaDoanhThuBox.setPadding(new Insets(20,0,0,5));
            moTaDoanhThuIcon = new ImageView(getClass().getResource("/img/trending-up.png").toExternalForm());
            Label tiLe = new Label("2.3%");
            InputStream workSansSmBold = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-SemiBold.ttf");
            Font tiLeFont = Font.loadFont(workSansSmBold, 20);
            tiLe.setFont(tiLeFont);
            Label tiLeDes = new Label("So với hôm qua");
            InputStream workSansRegular = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font tiLeDesFont = Font.loadFont(workSansRegular, 20);
            tiLeDes.setFont(tiLeDesFont);
            moTaDoanhThuBox.getChildren().addAll(moTaDoanhThuIcon,tiLe,tiLeDes);

            thongKeDoanhThu.getChildren().addAll(thongKeDoanhThuLabel,tongSoTienBox,moTaDoanhThuBox);
            thongKeCoBanContainer.getChildren().add(thongKeDoanhThu);

//			=======================
//			||THONG KE 02        ||
//			=======================
            VBox thongKeSoVe = new VBox();
            thongKeSoVe.setStyle("-fx-background-color: #FFDED9; -fx-background-radius: 12px;");
            thongKeSoVe.setMinWidth(250);
            thongKeSoVe.setMinHeight(160);
            thongKeSoVe.setTranslateY(40);
            thongKeSoVe.setTranslateX(70);
            Label thongKeP2Label = new Label("Tổng số vé bán trong ngày");
            thongKeP2Label.setPadding(new Insets(10,0,0,13));
            InputStream workSansInput2 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font thongKeDoanhThuLabelFont1 = Font.loadFont(workSansInput2, 20);
            thongKeP2Label.setFont(thongKeDoanhThuLabelFont1);


            HBox tongSoVeBox = new HBox();
            tongSoVeBox.setPadding(new Insets(20,0,0,90));
            Label soVe = new Label("213");
            InputStream interFont2 = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");

            Font soVeFont = Font.loadFont(interFont2, 35);
            soVe.setFont(soVeFont);
            Label donVi = new Label("vé");
            InputStream workSansInput3 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font donViFont = Font.loadFont(workSansInput3, 18);
            donVi.setFont(donViFont);
            tongSoVeBox.getChildren().addAll(soVe,donVi);

            HBox moTaLuongVeBan = new HBox();
            moTaLuongVeBan.setPadding(new Insets(20,0,0,5));
            ImageView moTaLuongVeIcon = new ImageView(getClass().getResource("/img/trending-up.png").toExternalForm());
            Label tiLeLuongVe = new Label("2.3%");
            InputStream workSansSmBold1 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-SemiBold.ttf");
            Font tiLeTangVeFont = Font.loadFont(workSansSmBold1, 20);
            tiLeLuongVe.setFont(tiLeTangVeFont);
            Label moTaTiLe = new Label("So với hôm qua");
            InputStream workSansRegular1 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font moTaTiLeFont = Font.loadFont(workSansRegular1, 20);
            moTaTiLe.setFont(moTaTiLeFont);
            moTaLuongVeBan.getChildren().addAll(moTaLuongVeIcon,tiLeLuongVe,moTaTiLe);

            thongKeSoVe.getChildren().addAll(thongKeP2Label,tongSoVeBox,moTaLuongVeBan);
            thongKeCoBanContainer.getChildren().add(thongKeSoVe);




//			=======================
//			||THONG KE 03        ||
//			=======================

            VBox thongKe3 =  new VBox();
            thongKe3.setStyle("-fx-background-color: #F1C8FF; -fx-background-radius: 12px;");
            thongKe3.setMinWidth(250);
            thongKe3.setMinHeight(160);
            thongKe3.setTranslateY(40);
            thongKe3.setTranslateX(120);
            Label thongKep3Label = new Label("Tổng số vé bán trong ngày");
            thongKep3Label.setPadding(new Insets(10,0,0,13));
            InputStream workSansInput4 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font thongKe3LabelFont = Font.loadFont(workSansInput4, 20);
            thongKep3Label.setFont(thongKe3LabelFont);


            HBox thongKeP3SoLuongBox = new HBox();
            thongKeP3SoLuongBox.setPadding(new Insets(20,0,0,90));
            Label soLuong = new Label("213");
            InputStream interFont3 = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");

            Font soLuongFont = Font.loadFont(interFont3, 35);
            soLuong.setFont(soLuongFont);
            Label donVi1 = new Label("vé");
            InputStream workSansInput5 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font donViFont1 = Font.loadFont(workSansInput5, 18);
            donVi1.setFont(donViFont1);
            thongKeP3SoLuongBox.getChildren().addAll(soLuong,donVi1);

            HBox moTa = new HBox();
            moTa.setPadding(new Insets(20,0,0,5));
            ImageView moTaIcon = new ImageView(getClass().getResource("/img/trending-up.png").toExternalForm());
            Label tiLe1 = new Label("2.3%");
            InputStream workSansSmBold2 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-SemiBold.ttf");
            Font tiLeFont1 = Font.loadFont(workSansSmBold2, 20);
            tiLe1.setFont(tiLeFont1);
            Label moTaTiLe1 = new Label("So với hôm qua");
            InputStream workSansRegular2 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font moTaTiLeFont1 = Font.loadFont(workSansRegular2, 20);
            moTaTiLe1.setFont(moTaTiLeFont1);
            moTa.getChildren().addAll(moTaIcon,tiLe1,moTaTiLe1);

            thongKe3.getChildren().addAll(thongKep3Label,thongKeP3SoLuongBox,moTa);
            thongKeCoBanContainer.getChildren().add(thongKe3);






//			=======================
//			||THONG KE 04        ||
//			=======================

            VBox thongKe4 =  new VBox();
            thongKe4.setStyle("-fx-background-color: #FFBCBC; -fx-background-radius: 12px;");
            thongKe4.setMinWidth(250);
            thongKe4.setMinHeight(160);
            thongKe4.setTranslateY(40);
            thongKe4.setTranslateX(160);
            Label thongKe4Label = new Label("Tổng số vé bán trong ngày");
            thongKe4Label.setPadding(new Insets(10,0,0,13));
            InputStream workSansInput6 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font thongKe4LabelFont = Font.loadFont(workSansInput6, 20);
            thongKe4Label.setFont(thongKe4LabelFont);


            HBox thongKe4SoLuongBox = new HBox();
            thongKe4SoLuongBox.setPadding(new Insets(20,0,0,90));
            Label soLuong4 = new Label("213");
            InputStream interFont4 = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");

            Font soLuongFont1 = Font.loadFont(interFont4, 35);
            soLuong4.setFont(soLuongFont1);
            Label donVi2 = new Label("vé");
            InputStream workSansInput7 =  getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font donViFont2 = Font.loadFont(workSansInput7, 18);
            donVi2.setFont(donViFont2);
            thongKe4SoLuongBox.getChildren().addAll(soLuong4,donVi2);

            HBox moTa4 = new HBox();
            moTa4.setPadding(new Insets(20,0,0,5));
            ImageView moTaIcon4 = new ImageView(getClass().getResource("/img/trending-up.png").toExternalForm());
            Label tiLe4 = new Label("2.3%");
            InputStream workSansSmBold4 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-SemiBold.ttf");
            Font tiLeFont4 = Font.loadFont(workSansSmBold4, 20);
            tiLe1.setFont(tiLeFont4);
            Label moTaTiLe4 = new Label("So với hôm qua");
            InputStream workSansRegular4 = getClass().getResourceAsStream("/fonts/Work_Sans/static/WorkSans-Regular.ttf");
            Font moTaTiLeFont4 = Font.loadFont(workSansRegular4, 20);
            moTaTiLe4.setFont(moTaTiLeFont4);
            moTa4.getChildren().addAll(moTaIcon4,tiLe4,moTaTiLe4);

            thongKe4.getChildren().addAll(thongKe4Label,thongKe4SoLuongBox,moTa4);
            thongKeCoBanContainer.getChildren().add(thongKe4);

//			=========================
//			||CHART + LICH SU       ||
//			=========================


            HBox chartVaLichSuBanContainer = new HBox();
            chartVaLichSuBanContainer.setTranslateY(140);
            chartVaLichSuBanContainer.setTranslateX(30);

//			=========================
//			||        CHART        ||
//			=========================
            VBox chartContainer = new VBox();
            Label thongKeLabel = new Label("Thống kê doanh thu");
            InputStream interBoldFontLoad = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font interBoldFont1 = Font.loadFont(interBoldFontLoad, 30);
            thongKeLabel.setFont(interBoldFont1);
            chartContainer.getChildren().add(thongKeLabel);



            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tháng");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Doanh thu (triệu)");


            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setMinSize(700, 500);

            XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 1", 500));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 2", 300));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 3", 700));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 4", 500));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 5", 300));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 6", 1000));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 7", 930));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 8", 200));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 9", 500));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 10", 200));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 11", 751));
            dataSeries1.getData().add(new XYChart.Data<>("Tháng 12", 770));


            barChart.getData().add(dataSeries1);
            barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: #D9FFEC;");
            barChart.setStyle("-fx-background-color: #D9FFEC;");
            for (XYChart.Series<String, Number> s : barChart.getData()) {
                for (XYChart.Data<String, Number> d : s.getData()) {
                    d.getNode().setStyle("-fx-bar-fill: #6EDBA5;");
                }
            }

            VBox chartBox = new VBox(barChart);
            chartBox.setTranslateY(50);
            chartContainer.getChildren().add(chartBox);


//			=========================
//			||        LICH SU       ||
//			=========================
            VBox lichSuCont = new VBox();
            Label lichSuLabel = new Label("Lịch sử bán vé");
            InputStream interBoldFontLoad1 = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font interBoldFont2 = Font.loadFont(interBoldFontLoad1, 30);
            lichSuLabel.setFont(interBoldFont2);
            lichSuCont.getChildren().add(lichSuLabel);
            lichSuCont.setTranslateX(50);

            TableView<LichSuBanVe> table = new TableView<>();

            // Cột 1: mã vé
            TableColumn<LichSuBanVe, String> maVeCol = new TableColumn<>("Mã Vé");
            maVeCol.setCellValueFactory(new PropertyValueFactory<>("maVe"));

            // Cột 2: tên
            TableColumn<LichSuBanVe, String> tenCol = new TableColumn<>("Tên khách hàng");
            tenCol.setCellValueFactory(new PropertyValueFactory<>("ten"));

            // Cột 3: ngày mua
            TableColumn<LichSuBanVe, String> ngayMuaCol = new TableColumn<>("Ngày mua");
            ngayMuaCol.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));

            table.getColumns().addAll(maVeCol, tenCol, ngayMuaCol);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Dữ liệu mẫu
            ObservableList<LichSuBanVe> data = FXCollections.observableArrayList(
                    new LichSuBanVe("V001", "Nguyễn Tiến Đạt", LocalDate.of(1995, 3, 15)),
                    new LichSuBanVe("V002", "Nguyễn Tiến Đạt", LocalDate.of(2006, 3, 15)),
                    new LichSuBanVe("V003", "Nguyễn Tiến Đạt", LocalDate.of(2005, 3, 15))

                    );

            table.setItems(data);
            VBox listLichSu = new VBox(table);
            listLichSu.setTranslateY(50);
            listLichSu.setMinWidth(500);
            listLichSu.setMinHeight(900);
            scene.getStylesheets().add(getClass().getResource("/table-style-trangchu.css").toExternalForm());
            lichSuCont.getChildren().add(listLichSu);





            chartVaLichSuBanContainer.getChildren().addAll(chartContainer, lichSuCont);

            noiDungChinh.getChildren().addAll(thongKeCoBanContainer,chartVaLichSuBanContainer);

            BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
            root.setLeft(menuList);
            root.setCenter(noiDungChinh);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(TrangChu.class, args);
    }
}