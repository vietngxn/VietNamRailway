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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
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


public class ThemDauTau extends Application {
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
	private int checkToaGiuongNam = 8;
	
	private ToaTauDAO toataudao = new ToaTauDAO();
	private Label lblToa;
	private ImageView imgToa;
	private Map<Integer, StackPane> mapSpToa = new HashMap<>();
	private Tau_DAO taudao = new Tau_DAO();
	private File fileTmp;
	private GhiFile ghiFile = new GhiFile();
	private TableView<Tau> table;
	private Map<String, Object> mapTmp;
	private Button buttonTiepTuc;
	private ChuyenTauDAO chuyentaudao = new ChuyenTauDAO();
	private Button buttonTroLai;
	

    public ThemDauTau() throws SQLException {
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
            creat_themDauTau_layout();
			
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
	
	
public VBox creat_themDauTau_layout() throws SQLException, IOException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		mapTmp = new HashMap<>();
		fileTmp = new File("src/main/resources/tmp_ChuyenTau.txt");
		
		//label đầu
		HBox boxLblThemChuyenTau = new HBox();
		lblThemChuyenTau = new Label("Thêm chuyến tàu");
		lblThemChuyenTau.setId("lbl_TieuDe");
		boxLblThemChuyenTau.getChildren().add(lblThemChuyenTau);
		boxLblThemChuyenTau.setAlignment(Pos.TOP_LEFT);
		boxLblThemChuyenTau.setMaxWidth(1000);
		
		lblThemToaTau = new Label("Chọn đầu tàu");
		lblThemToaTau.setId("lbl_TieuDe");
		
		table = new TableView<>();
		table.setId("table_DauTau");
		
		javafx.scene.control.TableColumn<Tau, String>  maTauCol = new javafx.scene.control.TableColumn<>("Mã đầu tàu");
		maTauCol.setCellValueFactory(new PropertyValueFactory<>("maTau"));
		maTauCol.setPrefWidth(260);
		maTauCol.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<Tau, String> tenTauCol = new javafx.scene.control.TableColumn<>("Tên đầu tàu");
		tenTauCol.setCellValueFactory(new PropertyValueFactory<>("tenTau"));
		tenTauCol.setPrefWidth(260);
		tenTauCol.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<Tau, String> loaiTauCol = new javafx.scene.control.TableColumn<>("Loại đầu tàu");
		loaiTauCol.setCellValueFactory(cellData -> {
			Tau tau = cellData.getValue();
			if(tau != null && tau.getLoaiTau() != null) return new SimpleStringProperty(cellData.getValue().getLoaiTau().getTenLoaiTau());
			else return new SimpleStringProperty("");
		});
		loaiTauCol.setPrefWidth(440);
		loaiTauCol.setId("table_ThemToaTau");
		
		TableColumn<Tau, Double> giaCuocCol = new javafx.scene.control.TableColumn<>("Giá cước");
		giaCuocCol.setCellValueFactory(cellData -> {
			Tau tau = cellData.getValue();
			if(tau != null && tau.getLoaiTau() != null) return new SimpleDoubleProperty(cellData.getValue().getLoaiTau().getGiaCuoc()).asObject();
			else return new SimpleDoubleProperty(0.0).asObject();
		});
		giaCuocCol.setPrefWidth(240);
		giaCuocCol.setId("table_ThemToaTau");
		
//		javafx.scene.control.TableColumn<Tau, String> trangThaiCol = new javafx.scene.control.TableColumn<>("Trạng thái");
//		trangThaiCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
//		trangThaiCol.setPrefWidth(238);
//		trangThaiCol.setId("table_ThemToaTau");
//		trangThaiCol.setCellFactory(column -> new javafx.scene.control.TableCell<Tau, String>() {
//		    @Override
//		    protected void updateItem(String value, boolean empty) {
//		        super.updateItem(value, empty);
//		        
//		        Tau tau = getTableRow() != null ? getTableRow().getItem() : null;
//		 
//		        if (empty || tau == null) {
//		            setText(null);
//		            setStyle("");
//		        } else {
//		            setText(tau.getTrangThai() == null ? "" : tau.getTrangThai());
//		            setStyle("-fx-text-fill: #009D75; -fx-font-weight: bold;");
//		        }
//		    }
//		});

		
		table.setPrefHeight(700);
		table.getColumns().addAll(maTauCol, tenTauCol, loaiTauCol, giaCuocCol);
		table.setMaxWidth(1200);
		
		List<String> allLine = Files.readAllLines(Paths.get("src/main/resources/tmp_ChuyenTau.txt")); 
		String line = allLine.get(0); 
		JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
		String maHanhTrinh = obj.get("maHanhTrinh").getAsString();
		String ngayKhoiHanh = obj.get("ngayKhoiHanh").getAsString();
		String gioKhoiHanh = obj.get("gioKhoiHanh").getAsString();
		String fullTime = ngayKhoiHanh + " " + gioKhoiHanh;
		LocalDateTime dateTimeDi = LocalDateTime.parse(fullTime, formatterTime);
		
		
		ArrayList<ChuyenTau> listChuyenTau = chuyentaudao.getListChuyenTau();
		ArrayList<Tau> listTauTheoHanhTrinh = taudao.getListTauTheoHanhTrinh(maHanhTrinh);
		ArrayList<Tau> listTauRemove = new ArrayList<>();
		
		for(ChuyenTau ct : listChuyenTau) {
			for(Tau t : listTauTheoHanhTrinh) {
				if(ct.getTau().getMaTau().equalsIgnoreCase(t.getMaTau())) {
					if(animation.checkNgay(dateTimeDi, ct.getNgayGioDi(), ct.getNgayGioDen())) listTauRemove.add(t);
				}
			}
		}
		listTauTheoHanhTrinh.removeAll(listTauRemove);
//		ObservableList<Tau> data = themSpacer(taudao.getListTauTheoHanhTrinh(maHanhTrinh));
		ObservableList<Tau> data = themSpacer(listTauTheoHanhTrinh);

		table.setRowFactory(tv -> {
		    TableRow<Tau> row = new TableRow<Tau>() {
		        @Override
		        public void updateItem(Tau tau, boolean empty) {
		            super.updateItem(tau, empty);

		            setMouseTransparent(false);
		            setFocusTraversable(true);
//		            setStyle("");

		            if (tau != null && tau.getMaTau().isEmpty()) {
		                setStyle("-fx-background-color: white; -fx-min-height: 16px; -fx-max-height: 16px;");
		                setMouseTransparent(true);    
		                setFocusTraversable(false);  
		            } 
		        }
		    };

//		    row.setOnMouseClicked(event -> {
//		    	 if (!row.isEmpty()) {
//		             int index = row.getIndex();
//
//		             if (table.getSelectionModel().isSelected(index)) {
//		                 table.getSelectionModel().clearSelection();
//		             } else {
//		                 table.getSelectionModel().select(index);
//		             }
//		         }
//		    });
		    return row;
		});


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
		layoutThemToaTau.getChildren().addAll(boxLblThemChuyenTau, lblThemToaTau, table, boxButton);
		layoutThemToaTau.setAlignment(Pos.CENTER);
		layoutThemToaTau.setStyle("-fx-background-color: #FFFFFF");
		return layoutThemToaTau;
	}
	
	
	public ObservableList<Tau> themSpacer(ArrayList<Tau> listTau) {
	    ObservableList<Tau> listTauSpace = FXCollections.observableArrayList();
	    for (int i = 0; i < listTau.size(); i++) {
	    	if(i == 0) listTauSpace.add(new Tau("", "", null)); 
	    	listTauSpace.add(listTau.get(i));                   
	        if (i < listTau.size() - 1) {                 
	        	listTauSpace.add(new Tau("", "", null));
	        }
	    }
	    return listTauSpace;
	}
	public boolean xuLyEventCu() {
	    Tau tau = table.getSelectionModel().getSelectedItem();
	    if (tau == null) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText(null);
			alert.setContentText("Vui lòng chọn đầu tàu");
			 Stage stage = (Stage) table.getScene().getWindow();
			alert.initOwner(stage);
			alert.initModality(Modality.WINDOW_MODAL);
			alert.showAndWait();
	        System.out.println("vui long chon dau tau");
	        return false;
	    } else {
	        mapTmp.put("maTau", tau.getMaTau());
	        Gson gson = new Gson();
	        String json = gson.toJson(mapTmp);
	        ghiFile.appendData(json, fileTmp);
	        return true;
	    }
	}
	public Button getButtonTiepTucQuaChonToa() {
		return this.buttonTiepTuc;
	}
	public Button getButtonTroLai() {
		return this.buttonTroLai;
	}
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}