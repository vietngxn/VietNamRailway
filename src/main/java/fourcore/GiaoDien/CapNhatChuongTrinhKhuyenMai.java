package fourcore.GiaoDien;

import java.time.format.DateTimeFormatter;

import fourcore.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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


public class CapNhatChuongTrinhKhuyenMai extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	private Animation lblAnimation;
	private Stage window;
	private VBox layoutCapNhatCTKM;
	private Scene sceneCapNhatCTKM;
	private Label lblCapNhatCTKM;
	private GridPane gridCapNhatCTKM;
	private Label lblMaCT;
	private TextField txtMaCT;
	private StackPane spMaCT;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblAnimation = new Animation();
		
		window = primaryStage;
		
		
		layoutCapNhatCTKM = new VBox();
		
		
		create_themchuongtrinhkm_layout();
		
		
		sceneCapNhatCTKM = new Scene(layoutCapNhatCTKM, 900, 700);
		sceneCapNhatCTKM.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		
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


		window.setScene(sceneCapNhatCTKM);
		window.setFullScreen(true);
		window.setOnShown(event -> {
			txtMaCT.setText("CTKM01");
			lblAnimation.scaleUp(lblMaCT);
			txtTenCT.setText("GiamGiaTH");
			lblAnimation.scaleUp(lblTenCT);
			txtNgayBatDau.setText("03/06/2036");
			lblAnimation.scaleUp(lblNgayBatDau);
			txtNgayKetThuc.setText("06/03/2063");
			lblAnimation.scaleUp(lblNgayKetThuc);
			txtcomboDoiTuong.setText("Trẻ em");
			lblAnimation.scaleUp(lblcomboDoiTuong);
			txtcomboTrangThai.setText("Hoạt động");
			lblAnimation.scaleUp(lblcomboTrangThai);
		});
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
		lblMaCT = new Label("Mã chương trình");
		lblMaCT.setId("lbl_TextField");

		
		txtMaCT = new TextField();
		txtMaCT.setPrefWidth(600);
		txtMaCT.setPrefHeight(40);
		txtMaCT.setId("txt_CapNhatChuyenTau");
		txtMaCT.setMouseTransparent(true);
		txtMaCT.setFocusTraversable(false);
		
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
		
		comboTrangThai = new ComboBox<>();
		comboTrangThai.getItems().addAll("Hoạt động", "Ngừng hoạt động");
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
		
		gridCapNhatCTKM.add(spMaCT, 0, 0);
		gridCapNhatCTKM.add(spTenCT, 1, 0);
		gridCapNhatCTKM.add(spNgayBatDau, 0, 1);
		gridCapNhatCTKM.add(spNgayKetThuc, 1, 1);
		gridCapNhatCTKM.add(spcomboDoiTuong, 0, 2);
		gridCapNhatCTKM.add(spcomboTrangThai, 1, 2);
		
		
		layoutCapNhatCTKM.getChildren().addAll(lblCapNhatCTKM, gridCapNhatCTKM, buttonCapNhatCTKMBox);
		layoutCapNhatCTKM.setAlignment(Pos.CENTER);
		layoutCapNhatCTKM.setStyle("-fx-background-color: #FFFFFF");
	}
}
