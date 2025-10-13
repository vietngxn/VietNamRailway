package fourcore.GiaoDien;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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


public class GioVe extends Application {
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
	private Label lbl_tieude;
	private VBox tieude_layout;
	private VBox tableCol;
	private Label colChuyen;
	private Label colGaDiGaDen;
	private Label colNgayKhoiHanh;
	private Node colViTriGhe;
	private Node colXoa;
	private StackPane paneCol_chuyen;
	private StackPane paneCol_gadigaden;
	private StackPane paneCol_ngayngoihanh;
	private StackPane paneCol_vitri;
	private StackPane paneCol_xoa;
	private StackPane paneCol_ngaykhoihanh;
	private VBox table_layout;
	
	private ScrollPane scrollPane;
	private int cnt = 1 ;
	private HBox layout_button;
	private Button btn_trolai;
	private Button btn_tieptuc;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1600,800);
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
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			
			tao_noidung_tieude();
			table_layout = new VBox();
//			table_layout.setTranslateY(40);
			table_layout.setPadding(new Insets(20, 0, 40, 0));
			
			
			tao_table("SE2","Sài Gòn  -  Hà Nội",LocalDate.now(),LocalTime.now(),"Toa số 3 chỗ 23");
			tao_table("SE2","Sài Gòn  -  Hà Nội",LocalDate.now(),LocalTime.now(),"Toa số 3 chỗ 24");
			tao_table("SE2","Sài Gòn  -  Hà Nội",LocalDate.now(),LocalTime.now(),"Toa số 3 chỗ 25");

			
			
		
			scrollPane = new ScrollPane();
			scrollPane.setContent(table_layout);
			scrollPane.setFitToWidth(true);
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Không hiện thanh ngang
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Thanh dọc chỉ hiện khi cần
			scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
			scrollPane.setPannable(true);

			// ScrollPane sẽ tự động mở rộng nhưng không vượt quá chiều cao còn lại của cửa sổ
			VBox.setVgrow(scrollPane, Priority.ALWAYS);
			scrollPane.setMaxHeight(600);
			noiDungChinh.getChildren().add(scrollPane);
			tao_button();
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void tao_noidung_tieude() {
		tieude_layout = new VBox();
		tieude_layout.setPrefHeight(200);
		tieude_layout.setMaxHeight(200);
		noiDungChinh.setPadding(new Insets(20));
		lbl_tieude = new Label("Giỏ vé");
		Font font_tieude = Font.loadFont(getClass().getResource("/fonts/Inter/static/Inter_28pt-Bold.ttf").toExternalForm(), 35);
		lbl_tieude.setFont(font_tieude);
		noiDungChinh.getChildren().add(lbl_tieude);
	}
	
	public void tao_table(String chuyen,String gadi,LocalDate thoigian,LocalTime giophut,String chongoi)
	{ 
		HBox layout_dong = new HBox();
		
		layout_dong.setStyle("-fx-background-color:#00BACB;-fx-background-radius :15px;");
		layout_dong.setPrefSize(1000, 50);
		String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 24px; -fx-font-weight: bold;-fx-text-fill:black;";
		Label lbl_chuyen = new Label(chuyen);
		lbl_chuyen.setStyle(styleHeader);
		lbl_chuyen.setTranslateX(10);
		lbl_chuyen.setTranslateY(15);
		
		Label lbl_gadi = new Label(gadi);
		lbl_gadi.setStyle(styleHeader);
		lbl_gadi.setTranslateX(50);
		lbl_gadi.setTranslateY(15);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
		
		String thoigiankhoihanh = thoigian.format(dtf);
		Label lbl_thoigian = new Label(thoigiankhoihanh);
		lbl_thoigian.setStyle(styleHeader);
		lbl_thoigian.setTranslateX(140);
		lbl_thoigian.setTranslateY(15);

		int hour = giophut.getHour();
		int min = giophut.getMinute();
		
		String giophut1 = giophut.format(tf);
		Label lbl_giophut = new Label(" - "+giophut1);
		lbl_giophut.setStyle(styleHeader);
		lbl_giophut.setTranslateX(140);
		lbl_giophut.setTranslateY(15);
		
		
		Label lbl_chongoi = new Label(chongoi);
		lbl_chongoi.setStyle(styleHeader);
		lbl_chongoi.setTranslateX(240);
		lbl_chongoi.setTranslateY(15);
		
		ImageView img_xoa = new ImageView(getClass().getResource("/images/copy//plus.png").toExternalForm());
		img_xoa.setFitHeight(60);
		img_xoa.setFitWidth(60);
		img_xoa.setTranslateX(250);
		img_xoa.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				img_xoa.setStyle("-fx-cursor:hand;");
			}
		});	
		
		img_xoa.setOnMousePressed(e -> {
		    FadeTransition ft = new FadeTransition(Duration.millis(500), layout_dong);
		    ft.setFromValue(1.0);
		    ft.setToValue(0.0);

		    ft.setOnFinished(ev -> {
		        table_layout.getChildren().remove(layout_dong);
		    });

		    ft.play();
		});
		
		if(cnt % 2 == 0) {
			table_layout.setSpacing(20);
		}
		else {
		cnt++;
		}
		layout_dong.getChildren().addAll(lbl_chuyen,lbl_gadi,lbl_thoigian,lbl_giophut,lbl_chongoi,img_xoa);
		table_layout.getChildren().add(layout_dong);
	}
	
	public void tao_button() {
		
		layout_button = new HBox();
		layout_button.setPrefSize(1000, 100);
		layout_button.setTranslateY(50);
		
		
		btn_trolai = new Button("Trở lại");
		btn_trolai.setPrefSize(150,50);
		
		btn_trolai.setStyle("-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);-fx-background-radius:15px;");
		btn_tieptuc = new Button("Tiếp Tục");
		btn_tieptuc.setPrefSize(150, 50);
		btn_tieptuc.setStyle("-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #00BACB, #B6D0D3);-fx-background-radius:15px;");
		btn_tieptuc.setTranslateX(600);
		
		btn_trolai.hoverProperty().addListener((obs,oval,nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200),btn_trolai);
			
			if(nval)
			{
				st.setToX(1.1);
				st.setToY(1.1);
				btn_trolai.setStyle("-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);-fx-background-radius:15px;-fx-cursor:hand;");
			}
			else {
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});
		
		btn_tieptuc.hoverProperty().addListener((obs,oval,nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200),btn_tieptuc);
			if(nval)
			{
				st.setToX(1.1);
				st.setToY(1.1);
				btn_tieptuc.setStyle("-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #00BACB, #B6D0D3);-fx-background-radius:15px;-fx-cursor:hand;");
			}
			else {
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});
		
		
		
		layout_button.getChildren().addAll(btn_trolai,btn_tieptuc);
		noiDungChinh.getChildren().add(layout_button);
	}
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(TrangChu.class, args);
	}
}