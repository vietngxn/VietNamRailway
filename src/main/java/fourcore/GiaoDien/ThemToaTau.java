package fourcore.GiaoDien;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fourcore.Entity.*;
import fourcore.animation.Animation;
import fourcore.animation.GhiFile;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.Tau_DAO;
import fourcore.dao.ToaTauDAO;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.text.Text;


public class ThemToaTau extends Application {
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
	private Label lblCapNhatChuyenTau;
	private VBox layoutThemToaTau = new VBox(20);
	private GridPane gridCapNhatChuyenTau;
	private HBox buttonCapNhatChuyenTauBox;
	private Label lblThemToaTau;
	private Label lblThemChuyenTau;
	private Animation animation = new Animation();
	private int checkToaGheNgoi = 0;
	private int checkToaGiuongNam = 9;
	private Tau_DAO taudao = new Tau_DAO();
	private ToaTauDAO toataudao = new ToaTauDAO();
	private Label lblToa;
	private ImageView imgToa;
	private Map<Integer, StackPane> mapSpToa = new HashMap<>();
	private File fileTmp;
	private GhiFile ghiFile = new GhiFile();
	private Button buttonTiepTuc;
	private ChuyenTauDAO chuyentaudao = new ChuyenTauDAO();
	private Button buttonTroLai;

    public ThemToaTau() throws SQLException {
    }

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
            root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
			
			//Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau		
			
//			creat_capnhatchuyentau_layout();
            creat_themtoatau_layout();
			
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			
			noiDungChinh = layoutThemToaTau;
			
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			
			primaryStage.setFullScreen(true);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
public VBox creat_themtoatau_layout() throws SQLException, IOException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String duongdantoa = "/img/thantauchon.png";
		String duongdandautao = "/img/dautau.png";
//		toataudao.testListToaTau();
		ArrayList<ToaTau> listToaTauTheoChuyen = new ArrayList<ToaTau>();
		fileTmp = new File("src/main/resources/tmp_ChuyenTau.txt");
		
		List<String> allLine = Files.readAllLines(Paths.get("src/main/resources/tmp_ChuyenTau.txt"));
		String line = allLine.get(1); 
		JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
		String maTau = obj.get("maTau").getAsString();
		Tau tau = taudao.getTauTheoMa(maTau);
		
		
		//label đầu
		HBox boxLblThemChuyenTau = new HBox();
		lblThemChuyenTau = new Label("Thêm chuyến tàu");
		lblThemChuyenTau.setId("lbl_TieuDe");
		boxLblThemChuyenTau.getChildren().add(lblThemChuyenTau);
		boxLblThemChuyenTau.setAlignment(Pos.TOP_LEFT);
		boxLblThemChuyenTau.setMaxWidth(1000);
		
		lblThemToaTau = new Label("Thêm toa tàu");
		lblThemToaTau.setId("lbl_TieuDe");
		
		HBox boxToaTau = new HBox(5);
		boxToaTau.setAlignment(Pos.CENTER);
		for(int i = 1; i <= 12; i++) {
			if(i == 12) {
				Label lblDauTau = new Label(tau.getLoaiTau().getTenLoaiTau());
				lblDauTau.setId("lbl_Toa");
				ImageView imgDauTau = animation.taoImgGhe(duongdandautao);
				imgDauTau.setFitWidth(50);
				StackPane spDauTau = new StackPane();
				spDauTau.getChildren().addAll(imgDauTau, lblDauTau);
				spDauTau.setMargin(lblDauTau, new Insets(10, 0, 0, 0));
				boxToaTau.getChildren().add(spDauTau);
			} else {
				lblToa = new Label();
				lblToa.setId("lbl_Toa");
				imgToa = animation.taoImgGhe(duongdantoa);
				imgToa.setFitWidth(50);
				imgToa.setOpacity(0.5);
				StackPane spToa = new StackPane();
				spToa.getChildren().addAll(imgToa, lblToa);
				spToa.setMargin(lblToa, new Insets(10, 0, 0, 0));
				mapSpToa.put(i, spToa);
				boxToaTau.getChildren().add(spToa);	
				}
		}
		
		TableView<ToaTau> table = new TableView<>();
		
		javafx.scene.control.TableColumn<ToaTau, String>  maToaCol = new javafx.scene.control.TableColumn<>("Mã toa");
		maToaCol.setCellValueFactory(new PropertyValueFactory<>("maToaTau"));
		maToaCol.setPrefWidth(100);
		maToaCol.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<ToaTau, String> tenToaCol = new javafx.scene.control.TableColumn<>("Tên toa");
		tenToaCol.setCellValueFactory(new PropertyValueFactory<>("tenToaTau"));
		tenToaCol.setPrefWidth(465);
		tenToaCol.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<ToaTau, Integer> soGheCol = new javafx.scene.control.TableColumn<>("Số ghế");
		soGheCol.setCellValueFactory(new PropertyValueFactory<>("soGhe"));
		soGheCol.setPrefWidth(100);
		soGheCol.setId("table_ThemToaTau");

//		javafx.scene.control.TableColumn<ToaTau, String> trangThaiCol = new javafx.scene.control.TableColumn<>("Trạng thái");
//		trangThaiCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
//		trangThaiCol.setPrefWidth(240);
//		trangThaiCol.setId("table_ThemToaTau");
//		javafx.scene.control.TableColumn<ToaTau, String> trangThaiCol = new javafx.scene.control.TableColumn<>("Trạng thái");
//		trangThaiCol.setPrefWidth(138);
//		trangThaiCol.setId("table_ThemToaTau");
//		trangThaiCol.setCellFactory(column -> new javafx.scene.control.TableCell<ToaTau, String>() {
//		    @Override
//		    protected void updateItem(String value, boolean empty) {
//		        super.updateItem(value, empty);
//
//		        ToaTau toaTau = getTableRow() != null ? (ToaTau) getTableRow().getItem() : null;
//
//		        if (empty || toaTau == null) {
//		            setText(null);
//		            setStyle("");
//		        } else {
//		            setText(toaTau.getTrangThai() == null ? "" : toaTau.getTrangThai());
//		            setStyle("-fx-text-fill: #009D75; -fx-font-weight: bold;");
//		        }
//		    }
//		});


		javafx.scene.control.TableColumn<ToaTau, String> loaiToaCol = new javafx.scene.control.TableColumn<>("Loại toa");
		
		loaiToaCol.setCellValueFactory(cellData -> {
			ToaTau toaTau = cellData.getValue();
			if(toaTau != null && toaTau.getLoaiToaTau() != null) return new SimpleStringProperty(cellData.getValue().getLoaiToaTau().getTenLoaiToaTau());
			else return new SimpleStringProperty("");
				
		});
		loaiToaCol.setPrefWidth(300);
		loaiToaCol.setId("table_ThemToaTau");
		
		TableColumn<ToaTau, String> trangThai = new javafx.scene.control.TableColumn<>("Trạng thái");
		trangThai.setCellValueFactory(cellData -> {
			ToaTau toaTau = cellData.getValue();
			if(toaTau != null && toaTau.isRemove()) return new SimpleStringProperty("Đang chạy");
			else return new SimpleStringProperty("Sẵn sàng");
		});
		trangThai.setPrefWidth(235);
		trangThai.setId("table_ThemToaTau");
		
		table.setPrefHeight(700);
		table.getColumns().addAll(maToaCol, loaiToaCol, soGheCol, tenToaCol, trangThai);
		table.setMaxWidth(1200);
		
	
		String line0 = allLine.get(0);
		JsonObject objNgayKhoiHanh = JsonParser.parseString(line0).getAsJsonObject();
		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String ngayKhoiHanh = objNgayKhoiHanh.get("ngayKhoiHanh").getAsString();
		String gioKhoiHanh = objNgayKhoiHanh.get("gioKhoiHanh").getAsString();
		String fullTime = ngayKhoiHanh + " " + gioKhoiHanh;
		LocalDateTime dateTimeDi = LocalDateTime.parse(fullTime, formatterTime);
		
		ArrayList<String> listChuyenTauTheoNgay = chuyentaudao.getListMaChuyenTauTheoNgay(dateTimeDi);
		for(String a : listChuyenTauTheoNgay) System.out.println("a " + a);
		ArrayList<ToaTau> listToaTauTheoTenToaTau = toataudao.getListToaTauTheoListMaChuyenTau(listChuyenTauTheoNgay);
		for(ToaTau b : listToaTauTheoTenToaTau) System.out.println("b " + b.getMaToaTau());
		ArrayList<ToaTau> listToaTauTheoHanhTrinh = toataudao.getListToaTauTenToaTau(tau.getLoaiTau().getTenLoaiTau());
		for(ToaTau c : listToaTauTheoHanhTrinh) System.out.println("c " + c.getMaToaTau());
		
	
//		listToaTauTheoHanhTrinh.removeAll(listToaTauTheoTenToaTau);
		for(int i = 0; i < listToaTauTheoHanhTrinh.size(); i++) {
			for(int j = 0; j < listToaTauTheoTenToaTau.size(); j++) {
				if(listToaTauTheoHanhTrinh.get(i).getMaToaTau().equalsIgnoreCase(listToaTauTheoTenToaTau.get(j).getMaToaTau())) listToaTauTheoHanhTrinh.get(i).setRemove(true);
			}
		} 
		for(ToaTau d : listToaTauTheoHanhTrinh) System.out.println("d " + d.getMaToaTau());
		
		ObservableList<ToaTau> data = themSpacer(listToaTauTheoHanhTrinh);
		
//		table.setRowFactory(tv -> new TableRow<ToaTau>() {
//		    @Override
//		    public void updateItem(ToaTau toaTau, boolean empty) {
//		        super.updateItem(toaTau, empty);
//
//		        if (toaTau != null && toaTau.getMaToaTau().isEmpty()) {
//		            setStyle("-fx-background-color: white; -fx-min-height: 16px; -fx-max-height: 16px;");
//		            setMouseTransparent(true);    
//		            setFocusTraversable(false);  
//		        } else {
//		        	setStyle("");
//		            setMouseTransparent(false);   
//		            setFocusTraversable(true);   
//		        }
//		    }
//		        
//		});


		table.setRowFactory(tv -> {
		    TableRow<ToaTau> row = new TableRow<ToaTau>() {
		        @Override
		        public void updateItem(ToaTau toaTau, boolean empty) {
		            super.updateItem(toaTau, empty);

		            getStyleClass().remove("selected-row");
		            setStyle("");
		            setMouseTransparent(false);
		            setFocusTraversable(true);
		            table.setId("");

		            if (toaTau != null && toaTau.getMaToaTau().isEmpty()) {
		                setStyle("-fx-background-color: white; -fx-min-height: 16px; -fx-max-height: 16px;");
		                setMouseTransparent(true);    
		                setFocusTraversable(false);  
		            } else if (toaTau != null && listToaTauTheoChuyen.contains(toaTau)) {
		                getStyleClass().add("selected-row");
		            } else if (toaTau != null && toaTau.isRemove()) {
		            	setStyle("-fx-background-color: red");
		            	setMouseTransparent(true);    
		                setFocusTraversable(false); 
		            }
		        }
		    };

		    row.setOnMouseClicked(event -> {
		        if (!row.isEmpty()) {
		            ToaTau toaTauClick = row.getItem();
		            if (!listToaTauTheoChuyen.contains(toaTauClick)) {
		                addToaTau(toaTauClick, listToaTauTheoChuyen);
		                
		            } else {
		            	removeToaTau(toaTauClick, listToaTauTheoChuyen);
		            	}
		            table.refresh();
		        }
		    });
		    return row;
		});



//		table.setOnMouseClicked(event -> {
//			TableView<ToaTau> tv = (TableView<ToaTau>) event.getSource();
//			
//			animation.scaleTableUp(table.getRowFactory());
//		});
		table.setItems(data);
		table.getColumns().forEach(col -> col.setSortable(false));
		
		
		buttonTiepTuc = new Button("Tiếp tục");
		buttonTiepTuc.setPrefWidth(200);
		buttonTiepTuc.setPrefHeight(60);
		buttonTiepTuc.setId("button_Blue");
		
		buttonTroLai = new Button("Trở lại");
		buttonTroLai.setPrefWidth(200);
		buttonTroLai.setPrefHeight(60);
		buttonTroLai.setId("button_Red");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox boxButton = new HBox(buttonTroLai, spacer, buttonTiepTuc);
		boxButton.setMaxWidth(1250);
		
		//đưa vào layout
		layoutThemToaTau.getChildren().addAll(boxLblThemChuyenTau, lblThemToaTau, boxToaTau, table, boxButton);
		layoutThemToaTau.setAlignment(Pos.CENTER);
		layoutThemToaTau.setStyle("-fx-background-color: #FFFFFF");
		return layoutThemToaTau;
	}
	
	
	public ObservableList<ToaTau> themSpacer(ArrayList<ToaTau> listToaTau) {
	    ObservableList<ToaTau> listToaTauSpace = FXCollections.observableArrayList();
	    for (int i = 0; i < listToaTau.size(); i++) {
	    	if(i == 0) listToaTauSpace.add(new ToaTau("", "", 0, null)); 
	    	listToaTauSpace.add(listToaTau.get(i));                   
	        if (i < listToaTau.size() - 1) {                 
	        	listToaTauSpace.add(new ToaTau("", "", 0, null));
	        }
	    }
	    return listToaTauSpace;
	}
//	public void addToaTau(ToaTau toaTau, ArrayList<ToaTau> listToaTauTheoChuyen) {
//		if(toaTau.getSoGhe()  == 36 && checkToaGheNgoi == 2) System.out.println("loi r");
//		else if(toaTau.getSoGhe() == 36 && checkToaGheNgoi == 0) {
//			if(!listToaTauTheoChuyen.contains(toaTau)) {
//				StackPane spToa = mapSpToa.get(10);
//				Label lblToa = (Label) spToa.getChildren().get(1);
//				ImageView imgToa = (ImageView) spToa.getChildren().get(0);
//				lblToa.setText(toaTau.getMaToaTau());
//				imgToa.setOpacity(1.0);
//				checkToaGheNgoi++;
//				listToaTauTheoChuyen.add(toaTau);
//			}
//		} else if(toaTau.getSoGhe() == 36 && checkToaGheNgoi == 1) {
//			if(!listToaTauTheoChuyen.contains(toaTau)) {
//				StackPane spToa = mapSpToa.get(9);
//				Label lblToa = (Label) spToa.getChildren().get(1);
//				ImageView imgToa = (ImageView) spToa.getChildren().get(0);
//				lblToa.setText(toaTau.getMaToaTau());
//				imgToa.setOpacity(1.0);
//				checkToaGheNgoi++;
//				listToaTauTheoChuyen.add(toaTau);
//			}
//		} else if(toaTau.getSoGhe() == 18 && checkToaGiuongNam == 0){
//			if(!listToaTauTheoChuyen.contains(toaTau)) {
//				StackPane spToa = mapSpToa.get(checkToaGiuongNam);
//				Label lblToa = (Label) spToa.getChildren().get(1);
//				ImageView imgToa = (ImageView) spToa.getChildren().get(0);
//				lblToa.setText(toaTau.getMaToaTau());
//				imgToa.setOpacity(1.0);
//				checkToaGiuongNam--;
//				listToaTauTheoChuyen.add(toaTau);
//			}
//		} else if(toaTau.getSoGhe() == 18 && checkToaGiuongNam > 0) {
//			if(!listToaTauTheoChuyen.contains(toaTau)) {
//				StackPane spToa = mapSpToa.get(checkToaGiuongNam);
//				Label lblToa = (Label) spToa.getChildren().get(1);
//				ImageView imgToa = (ImageView) spToa.getChildren().get(0);
//				lblToa.setText(toaTau.getMaToaTau());
//				imgToa.setOpacity(1.0);
//				checkToaGiuongNam--;
//				listToaTauTheoChuyen.add(toaTau);
//			}
//		} else {
//			System.out.println("loi");
//		}
//	} 
	public boolean xuLyEventCu() {
	    List<Integer> sortedKey = new ArrayList<>(mapSpToa.keySet());
	    Collections.sort(sortedKey, Collections.reverseOrder());
	    StringBuilder sb = new StringBuilder();
	    int viTriThuc = 1;

	    for(Integer key : sortedKey) {
	        StackPane spToa = mapSpToa.get(key);

	        Label lblToa = (Label) spToa.getChildren().get(1);
	        String maToa = lblToa.getText();

	        if(maToa != null && !maToa.isEmpty()) {
	            sb.append("{\"maToaTau\":").append("\"").append(maToa).append("\"")
	              .append(",\"viTriToa\":").append("\"").append(viTriThuc).append("\"").append("},");
	            viTriThuc++;
	        }
	    }

	    if (sb.length() == 0) {
	        System.out.println("Không có toa nào hợp lệ");
	        return false;
	    } else {
	        sb.setLength(sb.length() - 1); 
	    }

	    String dataLine = "[" + sb.toString() + "]";
	    ghiFile.appendData(dataLine, fileTmp);
	    return true;
	}

	public Button getButtonSangThietLapGiaGhe() {
		return this.buttonTiepTuc;
	}
	public void addToaTau(ToaTau toaTau, ArrayList<ToaTau> listToaTauTheoChuyen) {
	    int index = -1;

	    if (toaTau.getSoGhe() == 36) {
	        if (checkToaGheNgoi == 2) {
	        	System.out.println("loi gn");
	        	return;
	        } 
	        index = (checkToaGheNgoi == 0) ? 11 : 10;
	        checkToaGheNgoi++;
	    } 
	    else if (toaTau.getSoGhe() == 18) {
	        if (checkToaGiuongNam < 0) {
	        	System.out.println("loi gin");
	        	return;
	        }
	        index = checkToaGiuongNam;
	        checkToaGiuongNam--;
	    }

	    if (index != -1 && !listToaTauTheoChuyen.contains(toaTau)) {
	        StackPane spToa = mapSpToa.get(index);
	        Label lblToa = (Label) spToa.getChildren().get(1);
	        ImageView imgToa = (ImageView) spToa.getChildren().get(0);

	        lblToa.setText(toaTau.getMaToaTau());
	        imgToa.setOpacity(1.0);
	        listToaTauTheoChuyen.add(toaTau);
	    }
	}
	public void removeToaTau(ToaTau toaTau, ArrayList<ToaTau> listToaTauTheoChuyen) {
		 int index = -1;

		    if (toaTau.getSoGhe() == 36) {
		    	index = (checkToaGheNgoi == 1) ? 11 : 10;
		        checkToaGheNgoi--;
		        listToaTauTheoChuyen.remove(toaTau);
		    } 
		    else if (toaTau.getSoGhe() == 18) {
		    	index = checkToaGiuongNam + 1;
		        checkToaGiuongNam++;
		        listToaTauTheoChuyen.remove(toaTau);
		    }

		    if (index != -1) {
		        StackPane spToa = mapSpToa.get(index);
		        Label lblToa = (Label) spToa.getChildren().get(1);
		        ImageView imgToa = (ImageView) spToa.getChildren().get(0);

		        lblToa.setText("");
		        imgToa.setOpacity(0.5);
		    }
	}
	public Button getButtonTroLai() {
		return this.buttonTroLai;
	}	  
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}