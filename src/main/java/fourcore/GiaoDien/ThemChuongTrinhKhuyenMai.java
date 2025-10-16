package fourcore.GiaoDien;


import java.time.format.DateTimeFormatter;

import fourcore.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThemChuongTrinhKhuyenMai extends Application {
	public static void main(String[] args) {
		Application.launch(ThemChuongTrinhKhuyenMai.class, args);
	}
	private Stage window;
	private VBox layoutThemCTKM;
	private Scene sceneThemCTKM;
	private Label lblThemCTKM;
	private GridPane gridThemCTKM;
	private Label lblMaCT;
	private TextField txtMaCT;
	private Label lblTenCT;
	private TextField txtTenCT;
	private StackPane spMaCT;
	private StackPane spTenCT;
	private DatePicker ngayBatDau;
	private Label lblNgayBatDau;
	private TextField txtNgayBatDau;
	private Button buttonNgayBatDau;
	private StackPane spNgayBatDau;
	private VBox vboxNgayBatDau;
	private DatePicker ngayKetThuc;
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
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblAnimation = new Animation();
		
		window = primaryStage;
		
		
		layoutThemCTKM = new VBox();
		
		
		create_themchuongtrinhkm_layout();
		
		
		sceneThemCTKM = new Scene(layoutThemCTKM, 900, 700);
		sceneThemCTKM.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
		
		txtMaCT.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		    if (isNowFocused && txtMaCT.getText().trim().isEmpty()) {
		    	lblAnimation.scaleUp(lblMaCT);
		    } else {
		    	if(txtMaCT.getText().trim().isEmpty()) lblAnimation.scaleDown(lblMaCT);
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


		window.setScene(sceneThemCTKM);
		window.setFullScreen(true);
		window.show();
	}
	public void create_themchuongtrinhkm_layout() {
		
		//label đầu
		lblThemCTKM = new Label("Thêm chương trình khuyến mãi");
		lblThemCTKM.setId("lbl_TieuDe");
		
		
		//grid chứa các cái cần nhập
		gridThemCTKM = new GridPane();
		gridThemCTKM.setHgap(80);
		gridThemCTKM.setVgap(50);
		gridThemCTKM.setPadding(new Insets(70, 0, 0, 0));
		gridThemCTKM.setAlignment(Pos.CENTER);
		
		
		//hàng đầu tiên chứa mã chương trình và tên chương trình
		lblMaCT = new Label("Mã chương trình");
		lblMaCT.setId("lbl_TextField");

		
		txtMaCT = new TextField();
		txtMaCT.setPrefWidth(600);
		txtMaCT.setPrefHeight(40);
		txtMaCT.setId("txt_CapNhatChuyenTau");
		
		spMaCT = new StackPane();
		spMaCT.getChildren().addAll(lblMaCT, txtMaCT);
		spMaCT.setAlignment(lblMaCT, Pos.CENTER_LEFT);
		
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
		
		
		buttonThemCTKMBox = new HBox(10); //thay doi 10
//		buttonThemCTKMBox.setPrefWidth(1350);
		buttonThemCTKMBox.setMaxWidth(1600);
//		buttonThemCTKMBox.setStyle("-fx-background-color: black");
		
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
		
		gridThemCTKM.add(spMaCT, 0, 0);
		gridThemCTKM.add(spTenCT, 1, 0);
		gridThemCTKM.add(spNgayBatDau, 0, 1);
		gridThemCTKM.add(spNgayKetThuc, 1, 1);
		gridThemCTKM.add(spcomboDoiTuong, 0, 2);
		
		
		layoutThemCTKM.getChildren().addAll(lblThemCTKM, gridThemCTKM, buttonThemCTKMBox);
		layoutThemCTKM.setAlignment(Pos.CENTER);
		layoutThemCTKM.setStyle("-fx-background-color: #FFFFFF");
	}
}
