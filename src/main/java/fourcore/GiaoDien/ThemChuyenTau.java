package fourcore.GiaoDien;

import java.io.File;

import com.google.gson.Gson;
import java.io.InputStream;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fourcore.Entity.*;
import fourcore.animation.Animation;
import fourcore.animation.GhiFile;
import fourcore.dao.*;
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


public class ThemChuyenTau extends Application {
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
	private GridPane gridThemChuyenTau;
	private TextField txtNgayKhoiHanh;
	private Button buttonNgayKhoiHanh;
	private StackPane spNgayKhoiHanh;
	private DatePicker ngayKhoiHanh;
	private TextField txtGioKhoiHanh;
	private Button buttonGioKhoiHanh;
	private StackPane spGioKhoiHanh;
	private TextField txtNgayDenDuKien;
	private Button buttonNgayDenDuKien;
	private StackPane spNgayDenDuKien;
	private TextField txtGioDenDuKien;
	private Button buttonGioDenDuKien;
	private StackPane spGioDenDuKien;
	private File fileTmp;
	private GhiFile ghiFile = new GhiFile();
	private ChuyenTauDAO chuyentaudao = null;
	private HanhTrinh_DAO hanhtrinhdao = new HanhTrinh_DAO();
	private HanhTrinhGa_dao hanhtrinhgadao = new HanhTrinhGa_dao();
	private TableView<HanhTrinh> table;
	private Map<String, Object> mapTmp;
	private Button buttonTiepTuc;

    public ThemChuyenTau() throws SQLException {
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
            logoImgView.setFitHeight(260);
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
            menuPhuQuanLiVeTau.setPadding(new Insets(0, 60, 0, 0));
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
            menuPhuQuanLiThongKe.setPadding(new Insets(0, 60, 0, 0));
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
            scrollPaneMenu.setPrefHeight(450);
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
            userBox.setTranslateY(60);
            userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
            menuList.getChildren().add(userBox);
            root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
			
			//Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau		
			
//			creat_capnhatchuyentau_layout();
            creat_themchuyentau_layout();
			
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
	
	
public VBox creat_themchuyentau_layout() throws SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		mapTmp = new HashMap<>();
		fileTmp = new File("src/main/resources/tmp_ChuyenTau.txt");
		chuyentaudao = new ChuyenTauDAO();
		
		//label đầu
		HBox boxLblThemChuyenTau = new HBox();
		lblThemChuyenTau = new Label("Thêm chuyến tàu");
		lblThemChuyenTau.setId("lbl_TieuDe");
		boxLblThemChuyenTau.getChildren().add(lblThemChuyenTau);
		boxLblThemChuyenTau.setAlignment(Pos.TOP_LEFT);
		boxLblThemChuyenTau.setMaxWidth(1100);
		
		gridThemChuyenTau = new GridPane();
		gridThemChuyenTau.setHgap(80);
		gridThemChuyenTau.setVgap(50);
//		gridThemChuyenTau.setPadding(new Insets(60, 0, 0, 0));
		gridThemChuyenTau.setAlignment(Pos.CENTER);
		
		ngayKhoiHanh = new DatePicker();
		ngayKhoiHanh.setPrefWidth(30);
		ngayKhoiHanh.setPrefHeight(60);
		ngayKhoiHanh.setStyle("-fx-font-size: 18px;");
		ngayKhoiHanh.setOpacity(0);
		
		txtNgayKhoiHanh = new TextField();
		txtNgayKhoiHanh.setPrefWidth(450);
		txtNgayKhoiHanh.setPrefHeight(60);
		txtNgayKhoiHanh.setId("txt_ThemChuyenTau");
		txtNgayKhoiHanh.setMouseTransparent(true);
		txtNgayKhoiHanh.setFocusTraversable(false);
		txtNgayKhoiHanh.setPromptText("Ngày khởi hành");
		
		
		
		ngayKhoiHanh.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				txtNgayKhoiHanh.setText(newVal.format(formatter));
				
				
			}
		});
		
		buttonNgayKhoiHanh = new Button();
		ImageView iconThoiGian = animation.taoImgGhe("/img/calendar.png");
		iconThoiGian.setFitWidth(30);
		iconThoiGian.setFitHeight(30);
		buttonNgayKhoiHanh.setGraphic(iconThoiGian);
		buttonNgayKhoiHanh.setStyle("-fx-background-color: transparent");
		
		spNgayKhoiHanh = new StackPane();
		spNgayKhoiHanh.getChildren().addAll(txtNgayKhoiHanh, buttonNgayKhoiHanh, ngayKhoiHanh);
		spNgayKhoiHanh.setAlignment(buttonNgayKhoiHanh, Pos.CENTER_RIGHT);
		spNgayKhoiHanh.setAlignment(ngayKhoiHanh, Pos.CENTER_RIGHT);
		
//		ngayKhoiHanh = new DatePicker();
//		ngayKhoiHanh.setPrefWidth(30);
//		ngayKhoiHanh.setPrefHeight(60);
//		ngayKhoiHanh.setStyle("-fx-font-size: 18px;");
//		ngayKhoiHanh.setOpacity(0);
		
//		txtGioKhoiHanh = new TextField();
//		txtGioKhoiHanh.setPrefWidth(450);
//		txtGioKhoiHanh.setPrefHeight(60);
//		txtGioKhoiHanh.setId("txt_ThemChuyenTau");
////		txtGioKhoiHanh.setMouseTransparent(true);
//		txtGioKhoiHanh.setFocusTraversable(false);
//		txtGioKhoiHanh.setPromptText("Giờ khởi hành");
//		
//		
//		buttonGioKhoiHanh= new Button();
//		ImageView iconThoiGianKhoiHanh = animation.taoImgGhe("/img/clock.png");
//		iconThoiGianKhoiHanh.setFitWidth(30);
//		iconThoiGianKhoiHanh.setFitHeight(30);
//		buttonGioKhoiHanh.setGraphic(iconThoiGianKhoiHanh);
//		buttonGioKhoiHanh.setStyle("-fx-background-color: transparent");
//		
//		spGioKhoiHanh = new StackPane();
////		spNgayKhoiHanh.getChildren().addAll(txtNgayKhoiHanh, buttonGioKhoiHanh, ngayKhoiHanh);
//		spGioKhoiHanh.getChildren().addAll(txtGioKhoiHanh, buttonGioKhoiHanh);
//		spGioKhoiHanh.setAlignment(buttonGioKhoiHanh, Pos.CENTER_RIGHT);
////		spNgayKhoiHanh.setAlignment(ngayKhoiHanh, Pos.CENTER_RIGHT);
		
//		txtNgayDenDuKien = new TextField();
//		txtNgayDenDuKien.setPrefWidth(450);
//		txtNgayDenDuKien.setPrefHeight(60);
//		txtNgayDenDuKien.setId("txt_ThemChuyenTau");
//		txtNgayDenDuKien.setMouseTransparent(true);
//		txtNgayDenDuKien.setFocusTraversable(false);
//		txtNgayDenDuKien.setPromptText("Ngày đến dự kiến");
//		
//		buttonNgayDenDuKien = new Button();
//		ImageView iconNgayDenDuKien = animation.taoImgGhe("/img/calendar.png");
//		iconNgayDenDuKien.setFitWidth(30);
//		iconNgayDenDuKien.setFitHeight(30);
//		buttonNgayDenDuKien.setGraphic(iconNgayDenDuKien);
//		buttonNgayDenDuKien.setStyle("-fx-background-color: transparent");
//		
//		spNgayDenDuKien = new StackPane();
//		spNgayDenDuKien.getChildren().addAll(txtNgayDenDuKien, buttonNgayDenDuKien);
//		spNgayDenDuKien.setAlignment(buttonNgayDenDuKien, Pos.CENTER_RIGHT);
		
//		txtGioDenDuKien = new TextField();
//		txtGioDenDuKien.setPrefWidth(450);
//		txtGioDenDuKien.setPrefHeight(60);
//		txtGioDenDuKien.setId("txt_ThemChuyenTau");
//		txtGioDenDuKien.setMouseTransparent(true);
//		txtGioDenDuKien.setFocusTraversable(false);
//		txtGioDenDuKien.setPromptText("Giờ đến dự kiến");
//		
//		buttonGioDenDuKien = new Button();
//		ImageView iconGioDenDuKien = animation.taoImgGhe("/img/clock.png");
//		iconGioDenDuKien.setFitWidth(30);
//		iconGioDenDuKien.setFitHeight(30);
//		buttonGioDenDuKien.setGraphic(iconGioDenDuKien);
//		buttonGioDenDuKien.setStyle("-fx-background-color: transparent");
//		
//		spGioDenDuKien = new StackPane();
//		spGioDenDuKien.getChildren().addAll(txtGioDenDuKien, buttonGioDenDuKien);
//		spGioDenDuKien.setAlignment(buttonGioDenDuKien, Pos.CENTER_RIGHT);
		
		gridThemChuyenTau.add(spNgayKhoiHanh, 0, 0);
//		gridThemChuyenTau.add(spGioKhoiHanh, 1, 0);
//		gridThemChuyenTau.add(spNgayDenDuKien, 0, 1);
//		gridThemChuyenTau.add(spGioDenDuKien, 1, 1);
		
		table = new TableView<>();
		table.setId("table_DauTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String>  maHanhTrinh = new javafx.scene.control.TableColumn<>("Mã hành trình");
		maHanhTrinh.setCellValueFactory(new PropertyValueFactory<>("maHanhTrinh"));
		maHanhTrinh.setPrefWidth(400);
		maHanhTrinh.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String> tenHanhTrinh = new javafx.scene.control.TableColumn<>("Tên hành trình");
		tenHanhTrinh.setCellValueFactory(new PropertyValueFactory<>("tenHanhTrinh"));
		tenHanhTrinh.setPrefWidth(400);
		tenHanhTrinh.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String> soTramDung = new javafx.scene.control.TableColumn<>("Số trạm dừng");
		
		soTramDung.setCellValueFactory(cellData -> {
			HanhTrinh hanhTrinh = cellData.getValue();
			ArrayList<Ga> listGa = hanhtrinhdao.getListGaByMaHanhTrinh(hanhTrinh.getMaHanhTrinh());
			if(hanhTrinh != null && !listGa.isEmpty()) return new SimpleStringProperty(String.valueOf(listGa.size()));
			else return new SimpleStringProperty("");
				
		});
		soTramDung.setPrefWidth(398);
		soTramDung.setId("table_ThemToaTau");
		
		ObservableList<HanhTrinh> data = themSpacer(hanhtrinhdao.getList());
		
		ngayKhoiHanh.valueProperty().addListener((obs, oldDate, newDate) -> {
		    if (newDate != null) {
		        
		        List<String> listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(newDate);

		        ArrayList<HanhTrinh> listHanhTrinh = hanhtrinhdao.getList();
		        
		        
		        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
		            HanhTrinh ht = listHanhTrinh.get(i);
		            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
		                listHanhTrinh.remove(i);
		            }
		        }

		        ObservableList<HanhTrinh> dataNew = themSpacer(listHanhTrinh);
		       
		        table.setItems(dataNew);
		    }
		});
		
		table.setPrefHeight(700);
		table.getColumns().addAll(maHanhTrinh, tenHanhTrinh, soTramDung);
		table.setMaxWidth(1200);
		
		table.setItems(data);
		table.getColumns().forEach(col -> col.setSortable(false));
		
		table.setRowFactory(tv -> {
		    TableRow<HanhTrinh> row = new TableRow<HanhTrinh>() {
		        @Override
		        public void updateItem(HanhTrinh hanhTrinh, boolean empty) {
		            super.updateItem(hanhTrinh, empty);

		            setMouseTransparent(false);
		            setFocusTraversable(true);
		            setStyle("");

		            if (hanhTrinh != null && hanhTrinh.getMaHanhTrinh().isEmpty()) {
		                setStyle("-fx-background-color: white; -fx-min-height: 16px; -fx-max-height: 16px;");
		                setMouseTransparent(true);    
		                setFocusTraversable(false);  
		            } 
		        }
		    };
		    return row;
		});
		buttonTiepTuc = new Button("Thêm chuyến");
		buttonTiepTuc.setPrefWidth(200);
		buttonTiepTuc.setPrefHeight(60);
		buttonTiepTuc.setId("button_Blue");
//		buttonTiepTuc.setOnMouseClicked(event -> {
//			if(txtNgayKhoiHanh.getText().isEmpty()) System.out.println("vui lòng chọn ngày cần thêm chuyến");
//			else {
//				HanhTrinh hanhTrinh = table.getSelectionModel().getSelectedItem();
//				if(hanhTrinh == null) System.out.println("vui lòng chọn hành trình cho chuyến tàu");
//				mapTmp.put("maHanhTrinh", hanhTrinh.getMaHanhTrinh());
//				mapTmp.put("ngayKhoiHanh", txtNgayKhoiHanh.getText());
//				
//				ArrayList<HanhTrinhGa> listHanhTrinhGa = hanhtrinhgadao.getListHanhTrinhGaTheoMaHanhTrinh(hanhTrinh.getMaHanhTrinh());
//			
//				LocalDate ngayDenDuKienLast = ngayKhoiHanh.getValue().plusDays(listHanhTrinhGa.getLast().getSoNgayDiQua());
//				
//				mapTmp.put("ngayDenDuKien", ngayDenDuKienLast.format(formatter));
//				mapTmp.put("gioKhoiHanh", listHanhTrinhGa.get(0).getGioDiKeHoach().toString());
//				mapTmp.put("gioDenDuKien", listHanhTrinhGa.getLast().getGioDiKeHoach().toString());
//				
//				String tenLoaiTau = null;
//				if(hanhTrinh.getMaHanhTrinh().equalsIgnoreCase("HT001")) tenLoaiTau = "SE1";
//				else if(hanhTrinh.getMaHanhTrinh().equalsIgnoreCase("HT009")) tenLoaiTau = "SE9";
//				
//				mapTmp.put("tenLoaiTau", tenLoaiTau);
//				
//				Gson gson = new Gson();
//				String json = gson.toJson(mapTmp);
//				ghiFile.appendData(json, fileTmp);
//			}
//		});
		
		HBox boxButton = new HBox(buttonTiepTuc);
		boxButton.setAlignment(Pos.CENTER);
		boxButton.setMaxWidth(1250);
		
		
		//đưa vào layout
		layoutThemToaTau.getChildren().addAll(boxLblThemChuyenTau, gridThemChuyenTau, table, boxButton);
		layoutThemToaTau.setAlignment(Pos.CENTER);
		layoutThemToaTau.setStyle("-fx-background-color: #FFFFFF");
		return layoutThemToaTau;
	}
	
	public ObservableList<HanhTrinh> themSpacer(ArrayList<HanhTrinh> listHanhTrinh) {
	    ObservableList<HanhTrinh> listHanhTrinhSpace = FXCollections.observableArrayList();
	    for (int i = 0; i < listHanhTrinh.size(); i++) {
	    	if(i == 0) listHanhTrinhSpace.add(new HanhTrinh("", "", null)); 
	    	listHanhTrinhSpace.add(listHanhTrinh.get(i));                   
	        if (i < listHanhTrinh.size() - 1) {                 
	        	listHanhTrinhSpace.add(new HanhTrinh("", "", null));
	        }
	    }
	    return listHanhTrinhSpace;
	}
	public VBox getGDThemChuyenTau(){
        return  this.noiDungChinh;
    }
	public Button getButtonTiepTuc() {
		return this.buttonTiepTuc;
	}
	public boolean xuLyEventCu() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(txtNgayKhoiHanh.getText().isEmpty()) {
            System.out.println("vui lòng chọn ngày cần thêm chuyến");
            return false; 
        }
        HanhTrinh hanhTrinh = table.getSelectionModel().getSelectedItem();
        if(hanhTrinh == null) {
            System.out.println("vui lòng chọn hành trình cho chuyến tàu");
            return false; 
        }
        mapTmp.put("maHanhTrinh", hanhTrinh.getMaHanhTrinh());
        mapTmp.put("ngayKhoiHanh", txtNgayKhoiHanh.getText());
        ArrayList<HanhTrinhGa> listHanhTrinhGa = hanhtrinhgadao.getListHanhTrinhGaTheoMaHanhTrinh(hanhTrinh.getMaHanhTrinh());
        LocalDate ngayDenDuKienLast = ngayKhoiHanh.getValue().plusDays(listHanhTrinhGa.getLast().getSoNgayDiQua());
        mapTmp.put("ngayDenDuKien", ngayDenDuKienLast.format(formatter));
        mapTmp.put("gioKhoiHanh", listHanhTrinhGa.get(0).getGioDiKeHoach().toString());
        mapTmp.put("gioDenDuKien", listHanhTrinhGa.getLast().getGioDiKeHoach().toString());
        String tenLoaiTau = null;
        if(hanhTrinh.getMaHanhTrinh().equalsIgnoreCase("HT001")) tenLoaiTau = "SE1";
        else if(hanhTrinh.getMaHanhTrinh().equalsIgnoreCase("HT009")) tenLoaiTau = "SE9";
        mapTmp.put("tenLoaiTau", tenLoaiTau);
        Gson gson = new Gson();
        String json = gson.toJson(mapTmp);
        ghiFile.appendData(json, fileTmp);
        return true; 
    }
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}