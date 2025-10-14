package fourcore.GiaoDien;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fourcore.Entity.LichSuBanVe;


public class ChonVe extends Application {
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
    HBox chuyenTauMenu;

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






// DATA TESTING----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            LoaiTau loaiTau1 = new LoaiTau("LT01", "TauHoaBacNam", 12000);
            Tau tau1 = new Tau("SE6", "TauHi", loaiTau1);
            Tau tau2 = new Tau("SE2", "TauHi", loaiTau1);
            Tau tau3 = new Tau("SE3", "TauHi", loaiTau1);
            ChuyenTau chuyen1 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 3, 15, 30),LocalDateTime.of(2025, 10, 14, 3, 30),tau1, "TauHihi");
            ChuyenTau chuyen2 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 3, 20, 30),LocalDateTime.of(2025, 7, 14, 12, 30),tau2, "TauHihi");
            ChuyenTau chuyen3 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 1, 7, 00),LocalDateTime.of(2025, 9, 14, 4, 30),tau3, "TauHihi");
            ChuyenTau chuyen4 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "TauHihi");
            ChuyenTau chuyen5 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "TauHihi");
            ChuyenTau chuyen6 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "TauHihi");

            ArrayList<ChuyenTau> chuyenTauArrayList = new ArrayList<>();
            chuyenTauArrayList.add(chuyen1);
            chuyenTauArrayList.add(chuyen2);
            chuyenTauArrayList.add(chuyen3);
            chuyenTauArrayList.add(chuyen4);
            chuyenTauArrayList.add(chuyen5);
            chuyenTauArrayList.add(chuyen6);
            ChonVe chonVeGiaoDien = new ChonVe();
            chonVeGiaoDien.hienThiDanhSachChuyenTau(chuyenTauArrayList,chuyenTauMenu);


//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            noiDungChinh.getChildren().add(chuyenTauMenu);



            HBox danhSachToaTauBox = new HBox();
            ImageView dauTauImg = new ImageView(getClass().getResource("/img/dautau.png").toExternalForm());
            ImageView thanTauTrong =  new ImageView(getClass().getResource("/img/thantautrong.png").toExternalForm());
            danhSachToaTauBox.getChildren().addAll(thanTauTrong,dauTauImg);





            noiDungChinh.getChildren().add(danhSachToaTauBox);

            noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
            noiDungChinh.setPrefWidth(1200);



            BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
            root.setLeft(menuList);
            root.setCenter(noiDungChinh);
            primaryStage.setFullScreen(true);

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> danhSachChuyenTau, HBox menuChuyenTau){
        Image defaultImage = new Image(getClass().getResource("/img/TauHoaIcon.png").toExternalForm());
        Image selectedImage = new Image(getClass().getResource("/img/TauHoaIconChoose.png").toExternalForm());
        InputStream interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
        Font labelFont =  Font.loadFont(interBoldFont, 14);
        List<ImageView> imageViews = new ArrayList<>();

        for (ChuyenTau c : danhSachChuyenTau) {
            HBox chuyenTauCont = new HBox();
//            chuyenTauCont.setPrefWidth(200);
            StackPane chuyenTauBox = new StackPane();
            ImageView chuyenTauImage = new ImageView(defaultImage);
            chuyenTauBox.getChildren().addAll(chuyenTauImage);
            String maChuyenTau = c.getTau().getMaTau();
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
            LocalDateTime tgDenDuLieu = c.getNgayGioDen();
            String tgDenDuLieuToString = tgDenDuLieu.format(formatter);
            Label tgDenDuLieuLbl = new Label(tgDenDuLieuToString);
            tgDenDuLieuLbl.setFont(interRegular13);
            tgDen.setTranslateX(26);
            tgDen.setTranslateY(72);
            tgDen.getChildren().addAll(tgDenLabel, tgDenDuLieuLbl);

            VBox slChoDatBox = new VBox();
            Label slChoDatLabel = new Label("SL chỗ đặt");
            slChoDatLabel.setFont(interRegular13);
            Label soChoDat = new Label("120");
            soChoDat.setTranslateX(20);
            soChoDat.setFont(interBold13);
            slChoDatBox.getChildren().addAll( slChoDatLabel, soChoDat);
            slChoDatBox.setTranslateX(10);
            slChoDatBox.setTranslateY(102);

            VBox slChoTrongBox = new VBox();
            Label slChoTrongLabel = new Label("Trống");
            slChoTrongLabel.setFont(interRegular13);
            Label soChoTrong = new Label("8");
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
            menuChuyenTau.getChildren().add(chuyenTauCont);
            imageViews.add(chuyenTauImage);

            chuyenTauCont.setOnMouseClicked(event -> {
                for (ImageView iv : imageViews) {
                    iv.setImage(defaultImage);
                }
                chuyenTauImage.setImage(selectedImage);
            });

        }


    }
    public HBox getChuyenTauMenu() {
        return this.chuyenTauMenu;
    }
    public static void main(String[] args) {
        Application.launch(ChonVe.class, args);

    }

}