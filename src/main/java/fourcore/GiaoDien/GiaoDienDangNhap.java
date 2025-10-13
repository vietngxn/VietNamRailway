package fourcore.GiaoDien;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GiaoDienDangNhap extends Application {
		
	private Stage stage = new Stage();
	StackPane root_pane;
	private Scene Scene;
	private VBox leftPane;
	private Label lbl_leftpane;
	private VBox rightPane;
	private Label lbl_DangNhap;
	private VBox leftContent;
	private VBox rightContent;
	private VBox box_username;
	private Label lbl_manhanvien;
	private TextField txt_manhanvien;
	private HBox box_lblmanhanvien;
	private VBox box_password;
	private HBox box_lblpassword;
	private Label lbl_password;
	private PasswordField txt_password;
	private VBox box_button;
	private Button btn_dangnhap;
	private Label lbl_title;
	private VBox vbox1; 
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage = stage;
		
		root_pane = new StackPane();
		root_pane.setAlignment(Pos.CENTER);
		
		Scene = new Scene(root_pane, 900, 500);
		
		create_right_layout();
		create_left_layout();
		Scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		primaryStage.setScene(Scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void create_left_layout()
	{
		leftPane = new VBox();
		leftPane.setStyle("-fx-background-color: rgb(9,23,35);");
		leftPane.setPrefSize(400, 500);
		leftPane.setMaxSize(500, 500);
		leftContent = new VBox();
		leftContent.setAlignment(Pos.CENTER);
		leftContent.setPrefSize(400, 500);
		ImageView img1 = new ImageView(getClass().getResource("/img/img_taulua.png").toExternalForm());
		img1.setFitWidth(200);
		img1.setFitWidth(200);
		img1.setPreserveRatio(true);
		img1.setTranslateY(-30);
		leftContent.setTranslateX(-30);
		leftContent.getChildren().add(img1);
		
		Font font1 = Font.loadFont(getClass().getResourceAsStream("/fonts/PaytoneOne-Regular.ttf"), 30);
		lbl_title = new Label("Đường Sắt Việt Nam");
		lbl_title.setFont(font1);
		lbl_title.setStyle("-fx-text-fill: white;");
		
		leftContent.getChildren().add(lbl_title);
		
		vbox1 = new VBox();
		vbox1.setPrefSize(300, 2);
		vbox1.setMaxSize(300, 2);
		vbox1.setStyle("-fx-background-color: white;");
		leftContent.getChildren().addAll(vbox1);
		
		leftPane.getChildren().add(leftContent);
		Polygon clip = new Polygon(
				0,0,	//tren trai
		        500, 0, //tren phai
		        400, 500, // duoi phai
		        0, 500);	// duoi trai
		leftPane.setClip(clip);
		
		leftPane.setTranslateX(-200);
		leftPane.setId("leftPane");
		root_pane.getChildren().add(leftPane);
	}
	
	public void create_right_layout()
	{
		rightPane = new VBox();
		rightPane.setPrefSize(500, 500);
		rightPane.setMaxSize(500, 500);
		rightPane.setStyle("-fx-background-color : linear-gradient(to right, #00BACB 1%, #091723 99%);");
		
		
		rightContent = new VBox();
		Font font1 = Font.loadFont(getClass().getResourceAsStream("/fonts/PaytoneOne-Regular.ttf"),32);
		
		rightContent.setTranslateX(150);
		lbl_DangNhap = new Label("Đăng Nhập");
		lbl_DangNhap.setTranslateY(20);
		lbl_DangNhap.setTranslateX(50);
		lbl_DangNhap.setFont(font1);
		lbl_DangNhap.setStyle("-fx-text-fill: white;");
		rightContent.getChildren().add(lbl_DangNhap);
		
		Font font3 = Font.loadFont(getClass().getResourceAsStream("/fonts/SawarabiGothic-Regular.ttf"), 15);
		
		box_username = new VBox();
		
		box_lblmanhanvien = new HBox();
		lbl_manhanvien = new Label("Mã Nhân Viên");
		lbl_manhanvien.setId("lbl_manhanvien");
		ImageView img_manhanvien = new ImageView(getClass().getResource("/img/user.png").toExternalForm());
		img_manhanvien.setFitHeight(20);
		img_manhanvien.setFitWidth(20);
		
		
		txt_manhanvien = new TextField();
		txt_manhanvien.setId("txt_manhanvien");
		txt_manhanvien.setPrefSize(250, 20);
		txt_manhanvien.setMaxSize(250, 20);
		box_lblmanhanvien.getChildren().addAll(lbl_manhanvien,img_manhanvien);
		box_username.getChildren().addAll(box_lblmanhanvien,txt_manhanvien);
		
		rightContent.getChildren().add(box_username);
		box_username.setTranslateX(20);
		
		box_password = new VBox();
		
		box_lblpassword = new HBox();
		lbl_password = new Label("Mật khẩu");
		lbl_password.setId("lbl_password");
		ImageView image_password = new ImageView(getClass().getResource("/img/key.png").toExternalForm());
		image_password.setFitHeight(20);
		image_password.setFitWidth(20);
		
		box_lblpassword.getChildren().addAll(lbl_password,image_password);
		
		txt_password = new PasswordField();
		txt_password.setId("txt_password");
		txt_password.setPrefSize(250, 20);
		txt_password.setMaxSize(250, 20);
		box_password.getChildren().addAll(box_lblpassword,txt_password);
		
		rightContent.getChildren().add(box_password);
	
		box_password.setTranslateX(20);
	
		lbl_manhanvien.setFont(font3);
		txt_manhanvien.setFont(font3);
		lbl_password.setFont(font3);
//		txt_password.setFont(font3);
		
		Font font2 = Font.loadFont(getClass().getResourceAsStream("/fonts/PaytoneOne-Regular.ttf"), 20);
		box_button = new VBox();
		btn_dangnhap = new Button("Đăng Nhập");
		btn_dangnhap.setFont(font2);
		btn_dangnhap.setId("btn_dangnhap");
		btn_dangnhap.setPrefSize(210, 30);
		
		box_button.setTranslateX(20);
		
		box_button.getChildren().add(btn_dangnhap);
		rightContent.getChildren().add(box_button);
		
		box_username.setPadding(new Insets(80, 0, 20, 0));


		box_password.setPadding(new Insets(20, 0, 20, 0));

		
		box_button.setPadding(new Insets(30, 0, 0, 20));
		
		
		
		box_lblmanhanvien.setTranslateY(25);
		box_lblmanhanvien.setTranslateX(6);
		img_manhanvien.setTranslateX(130);
		txt_manhanvien.setPadding(new Insets(5));
		
		box_lblpassword.setTranslateX(6);
		box_lblpassword.setTranslateY(25);
		image_password.setTranslateX(165);
		txt_password.setPadding(new Insets(5));
		
		rightPane.getChildren().add(rightContent);
		rightPane.setTranslateX(200);
		root_pane.getChildren().add(rightPane);
		
		
		txt_manhanvien.focusedProperty().addListener((obs,oval,nval) ->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), lbl_manhanvien);
			String s = txt_manhanvien.getText();
			int i = tt.getCycleCount();
			if(s.equalsIgnoreCase("") && i == 1 )
			{
				if(nval)
				{
					tt.setToY(-25);
					
				}
				else
				{
					tt.setToY(0);
				}
				tt.play();
			}
			else if(i!= 1 ) {
				tt.setToY(-25);
			}
		});
		
		txt_password.focusedProperty().addListener((obs,oval,nval) ->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), lbl_password);
			String s = txt_password.getText();
			int i = tt.getCycleCount();
			if(s.equalsIgnoreCase("") && i== 1 )
			{
				if(nval)
				{
					tt.setToY(-25);
				}
				else
				{
					tt.setToY(0);
				}
				tt.play();
			}
			else if(i != 1)
			{
				tt.setToY(-25);
			}
			
		});
		btn_dangnhap.hoverProperty().addListener((obs,oval,nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_dangnhap);
			
			if(nval)
			{
				st.setToX(1.1);
				st.setToY(1.1);
				btn_dangnhap.setStyle("-fx-text-fill: rgb(121,217,225);-fx-background-color: white;");
			}
			else {
				st.setToX(1);
				st.setToY(1);
				btn_dangnhap.setStyle("-fx-background-color: rgb(121,217,225);-fx-text-fill: white;");
			}
			st.play();
		});
		btn_dangnhap.setOnMouseClicked(event -> {
		    // Đóng cửa sổ đăng nhập hiện tại
		    stage.close();
		    
		    // Mở cửa sổ trang chủ mới
		    try {
		        TrangChu trangchu = new TrangChu();
		        
		        Stage newStage = new Stage();
		        
		        TrangChu tc = new TrangChu();
				tc.start(newStage);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		});
	}
	public static void main(String[] args) {
		launch(args);
	}

}
