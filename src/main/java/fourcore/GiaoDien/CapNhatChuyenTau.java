package fourcore.GiaoDien;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.text.Text;


public class CapNhatChuyenTau extends Application {
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
	private Label lbl_title_maHoaDon;
	private Label lbl_title_nguoiMua;
	private Node lbl_title_ngayLap;
	private Node lbl_title_loaiHoaDon;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private ArrayList<HBox> hangchon = new ArrayList<>();
	private HBox layout_button;
	private Button btn_xoaChuyenTau;
	private Button btn_xuatHoaDon;
	private Button btn_xemChiTiet;
	private Label lbl_title_thoigiankhoihanh;
	private Node lbl_title_tongTien;
	private Label lblCapNhatChuyenTau;
	private VBox layoutCapNhatChuyenTau = new VBox();
	private GridPane gridCapNhatChuyenTau;
	private TextField txtMaChuyenTau;
	private TextField txtThoiGianKhoiHanh;
	private HBox thoiGianKhoiHanhBox;
	private HBox buttonCapNhatChuyenTauBox;
	private Button buttonTiepTheo;
	private Button buttonThoat;
	private BorderPane layout;
	private Label lblMaChuyenTau;
	private StackPane spMaChuyenTau;
	private StackPane spThoiGianKhoiHanh;
	private VBox vboxMaChuyenTau;
	private VBox vboxComboDauTau;
	private Label lblDauTau;
	private Label lblThoiGianKhoiHanh;
	private VBox vboxThoiGianKhoiHanh;
	private Button buttonThoiGianKhoiHanh;
	private Label lblThoiGianDuKien;
	private TextField txtThoiGianDuKien;
	private Button buttonThoiGianDuKien;
	private StackPane spThoiGianDuKien;
	private VBox vboxThoiGianDuKien;
	private Button buttonComboDauTau;
	private TextField txtComboDauTau;
	private StackPane spComboDauTau;
	private ComboBox<String> comboDauTau;
	private DatePicker thoiGianKhoiHanh;
	private DatePicker thoiGianDuKien;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1540, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
			
			quanLiVeTauMenu = new HBox();
			quanLiVeTauMenu.setSpacing(102);
			quanLiVeTauMenu.setPadding(new Insets(10, 5, 10, 20));
			quanLiVeTauMenu.setStyle("-fx-alignment: center-left;");
		

//			quanLiVeTauIcon = new Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
			quanLiVeTauIconView = new ImageView(getClass().getResource("/img/ticket.png").toExternalForm());
			quanLiVeTauIconView.setFitWidth(30);
			quanLiVeTauIconView.setFitHeight(30);

			quanLiVeTauLabel = new Label("Quản lí vé tàu");
			quanLiVeTauLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

			Region spacer = new Region();
			HBox.setHgrow(spacer, Priority.ALWAYS);
			
//			showMenuPhuIcon = new Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
			showMenuPhuIconView = new ImageView(getClass().getResource("/img/chevron-up.png").toExternalForm());
			showMenuPhuIconView.setFitWidth(20);
			showMenuPhuIconView.setFitHeight(20);

			quanLiVeTauMenu.getChildren().addAll(quanLiVeTauIconView, quanLiVeTauLabel,spacer, showMenuPhuIconView);

			
			danhSachMenuItem.getChildren().add(quanLiVeTauMenu);
			scrollPaneMenu.setContent(danhSachMenuItem);
			VBox menuPhuQuanLiVeTau = new VBox();
			menuPhuQuanLiVeTau.setSpacing(10);
			menuPhuQuanLiVeTau.setPadding(new Insets(0, 50, 0, 0)); 
			menuPhuQuanLiVeTau.setVisible(false);
			menuPhuQuanLiVeTau.setManaged(false); 
			menuPhuQuanLiVeTau.setStyle("-fx-background-color: #D2EEF0;");

			Label banVeLabel = new Label("Bán vé");
			Label doiVeLabel = new Label("Đổi vé");
			Label hoanVeLabel = new Label("Hoàn vé");
			Label capVeLabel = new Label("Cấp vé");
			for (Label label : new Label[]{banVeLabel, doiVeLabel, hoanVeLabel,capVeLabel}) {
			    label.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
			    label.setPadding(new Insets(10, 320, 10, 72));
			    label.setOnMouseEntered(e -> label.setStyle("-fx-font-size: 15px;-fx-background-color: #79D9E1;"));
			    label.setOnMouseExited(e -> label.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;"));
			}

			menuPhuQuanLiVeTau.getChildren().addAll(banVeLabel, doiVeLabel, hoanVeLabel,capVeLabel);
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

			});

			menuList.getChildren().add(scrollPaneMenu);
			
			
			//Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau		
			
			creat_capnhatchuyentau_layout();
			
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			
			noiDungChinh = layoutCapNhatChuyenTau;
			
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			
			primaryStage.setFullScreen(true);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
public void creat_capnhatchuyentau_layout() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//label đầu
		lblCapNhatChuyenTau = new Label("Cập nhật thông tin chuyến tàu");
		lblCapNhatChuyenTau.setId("lbl_TieuDe");
		
		
		//grid để chứa các cái cần nhập
		gridCapNhatChuyenTau = new GridPane();
		gridCapNhatChuyenTau.setHgap(80);
		gridCapNhatChuyenTau.setVgap(50);
		gridCapNhatChuyenTau.setPadding(new Insets(70, 0, 0, 0));
		gridCapNhatChuyenTau.setAlignment(Pos.CENTER);
		
		
		//hàng đầu tiên gồm mã chuyến tàu và combobox đầu tàu
		//lblMaChuyenTau
		lblMaChuyenTau = new Label("Mã chuyến tàu");
		lblMaChuyenTau.setId("lbl_TextField_Convert");
		
		//txtMaChuyenTau
		txtMaChuyenTau = new TextField();
		txtMaChuyenTau.setPrefWidth(600);
		txtMaChuyenTau.setPrefHeight(40);
		txtMaChuyenTau.setId("txt_CapNhatChuyenTau");
		txtMaChuyenTau.setMouseTransparent(true);
		txtMaChuyenTau.setFocusTraversable(false);
		
		//comBoDauTau
		comboDauTau = new ComboBox<>();
		comboDauTau.getItems().addAll("SE1", "SE2", "SE3", "SE4");
		comboDauTau.setPrefWidth(600);
		comboDauTau.setPrefHeight(40);
		comboDauTau.setStyle("-fx-font-size: 18px");
		comboDauTau.setOpacity(0);
		
		buttonComboDauTau = new Button();
		ImageView iconDropCombo = new ImageView(new Image(getClass().getResourceAsStream("/img/chevron-down.png")));
		iconDropCombo.setFitWidth(30);
		iconDropCombo.setFitHeight(30);
		buttonComboDauTau.setGraphic(iconDropCombo);
		buttonComboDauTau.setStyle("-fx-background-color: transparent");
		
		txtComboDauTau = new TextField();
		txtComboDauTau.setPrefWidth(600);
		txtComboDauTau.setPrefHeight(40);
		txtComboDauTau.setId("txt_CapNhatChuyenTau");
		txtComboDauTau.setMouseTransparent(true);
		txtComboDauTau.setFocusTraversable(false);
		
		lblDauTau = new Label("Đầu tàu");
		lblDauTau.setId("lbl_TextField_Convert");
		
		spComboDauTau = new StackPane();
		spComboDauTau.getChildren().addAll(txtComboDauTau, buttonComboDauTau, comboDauTau);
		spComboDauTau.setAlignment(buttonComboDauTau, Pos.CENTER_RIGHT);
		
		vboxComboDauTau = new VBox();
		vboxComboDauTau.getChildren().addAll(lblDauTau, spComboDauTau);
		
		vboxMaChuyenTau = new VBox();
		vboxMaChuyenTau.getChildren().addAll(lblMaChuyenTau, txtMaChuyenTau);
		vboxMaChuyenTau.setPrefWidth(600);
		vboxMaChuyenTau.setPrefHeight(40);
		
		
		
		
			
		//hàng thứ hai gồm Date
		thoiGianKhoiHanh = new DatePicker();
		thoiGianKhoiHanh.setPrefWidth(30);
		thoiGianKhoiHanh.setPrefHeight(40);
		thoiGianKhoiHanh.setStyle("-fx-font-size: 18px;");
		thoiGianKhoiHanh.setOpacity(0);
		
		lblThoiGianKhoiHanh = new Label("Thời gian khởi hành");
		lblThoiGianKhoiHanh.setId("lbl_TextField_Convert");
		
		txtThoiGianKhoiHanh = new TextField();
		txtThoiGianKhoiHanh.setMaxWidth(800);
		txtThoiGianKhoiHanh.setPrefWidth(800);
		txtThoiGianKhoiHanh.setPrefHeight(40);
		txtThoiGianKhoiHanh.setId("txt_CapNhatChuyenTau");
		txtThoiGianKhoiHanh.setMouseTransparent(true);
		txtThoiGianKhoiHanh.setFocusTraversable(false);
		
		buttonThoiGianKhoiHanh = new Button();
		ImageView iconThoiGian = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
		iconThoiGian.setFitWidth(30);
		iconThoiGian.setFitHeight(30);
		buttonThoiGianKhoiHanh.setGraphic(iconThoiGian);
		buttonThoiGianKhoiHanh.setStyle("-fx-background-color: transparent");
		
		spThoiGianKhoiHanh = new StackPane();
		spThoiGianKhoiHanh.getChildren().addAll(txtThoiGianKhoiHanh, buttonThoiGianKhoiHanh, thoiGianKhoiHanh);
		spThoiGianKhoiHanh.setAlignment(buttonThoiGianKhoiHanh, Pos.CENTER_RIGHT);
		spThoiGianKhoiHanh.setAlignment(thoiGianKhoiHanh, Pos.CENTER_RIGHT);
		
		vboxThoiGianKhoiHanh = new VBox();
		vboxThoiGianKhoiHanh.getChildren().addAll(lblThoiGianKhoiHanh, spThoiGianKhoiHanh);
		
		
		//hàng thứ ba gồm Date
		thoiGianDuKien = new DatePicker();
		thoiGianDuKien.setPromptText("Thời gian dự kiến");
		thoiGianDuKien.setPrefWidth(30);
		thoiGianDuKien.setPrefHeight(40);
		thoiGianDuKien.setStyle("-fx-font-size: 18px");
		thoiGianDuKien.setOpacity(0);
		
		lblThoiGianDuKien = new Label("Thời gian dự kiến");
		lblThoiGianDuKien.setId("lbl_TextField_Convert");
		
		txtThoiGianDuKien = new TextField();
		txtThoiGianDuKien.setPrefWidth(800);
		txtThoiGianDuKien.setPrefHeight(40);
		txtThoiGianDuKien.setId("txt_CapNhatChuyenTau");
		txtThoiGianDuKien.setMouseTransparent(true);
		txtThoiGianDuKien.setFocusTraversable(false);
		
		buttonThoiGianDuKien = new Button();
		ImageView iconThoiGianDuKien = new ImageView(new Image(getClass().getResourceAsStream("/img/calendar.png")));
		iconThoiGianDuKien.setFitWidth(30);
		iconThoiGianDuKien.setFitHeight(30);
		buttonThoiGianDuKien.setGraphic(iconThoiGianDuKien);
		buttonThoiGianDuKien.setStyle("-fx-background-color: transparent");
		
		spThoiGianDuKien = new StackPane();
		spThoiGianDuKien.getChildren().addAll(txtThoiGianDuKien, buttonThoiGianDuKien, thoiGianDuKien);
		spThoiGianDuKien.setMaxWidth(800);
		spThoiGianDuKien.setAlignment(buttonThoiGianDuKien, Pos.CENTER_RIGHT);
		spThoiGianDuKien.setAlignment(thoiGianDuKien, Pos.CENTER_RIGHT);
		
		vboxThoiGianDuKien = new VBox();
		vboxThoiGianDuKien.getChildren().addAll(lblThoiGianDuKien, spThoiGianDuKien);

		
		//hai cái button
		buttonCapNhatChuyenTauBox = new HBox(10);
		buttonCapNhatChuyenTauBox.setMaxWidth(1100);
		
		buttonTiepTheo = new Button();
		buttonTiepTheo.setText("Tiếp theo");
		buttonTiepTheo.setPrefWidth(150);
		buttonTiepTheo.setPrefHeight(50);
		buttonTiepTheo.setId("button_Blue");
		
		buttonThoat = new Button();
		buttonThoat.setText("Thoát");
		buttonThoat.setPrefWidth(150);
		buttonThoat.setPrefHeight(50);
		buttonThoat.setId("button_White");
		
		buttonCapNhatChuyenTauBox.getChildren().addAll(buttonTiepTheo, buttonThoat);
		buttonCapNhatChuyenTauBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonCapNhatChuyenTauBox.setPadding(new Insets(100, 40, 20, 0));
		
		
		//add các node
		gridCapNhatChuyenTau.add(vboxMaChuyenTau, 0, 0);
		gridCapNhatChuyenTau.add(vboxComboDauTau, 1, 0);
		gridCapNhatChuyenTau.add(vboxThoiGianKhoiHanh, 0, 1, 2, 1);
		gridCapNhatChuyenTau.add(vboxThoiGianDuKien, 0, 2, 2, 1);
		gridCapNhatChuyenTau.setMaxWidth(800);
		
		
		//đưa vào layout
		layoutCapNhatChuyenTau.getChildren().addAll(lblCapNhatChuyenTau, gridCapNhatChuyenTau, buttonCapNhatChuyenTauBox);
		layoutCapNhatChuyenTau.setAlignment(Pos.CENTER);
		layoutCapNhatChuyenTau.setStyle("-fx-background-color: #FFFFFF");
		
		comboDauTau.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) txtComboDauTau.setText(newVal.toString());
		});
		
		thoiGianKhoiHanh.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) txtThoiGianKhoiHanh.setText(newVal.format(formatter));
		});
		
		thoiGianDuKien.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) txtThoiGianDuKien.setText(newVal.format(formatter));
		});
	}
	
	
	
								   
	
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}