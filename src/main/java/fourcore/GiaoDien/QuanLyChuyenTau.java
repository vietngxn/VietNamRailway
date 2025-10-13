package fourcore.GiaoDien;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class QuanLyChuyenTau extends Application {
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
	private Label lbl_title_maChuyenTau;
	private Label lbl_title_dauTau;
	private Node lbl_title_soluongtoa;
	private Node lbl_title_veTrong;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private ArrayList<HBox> hangchon = new ArrayList<>();
	private HBox layout_button;
	private Button btn_xoaChuyenTau;
	private Button btn_themChuyenTau;
	private Button btn_suaChuyenTau;
	private Label lbl_title_thoigiankhoihanh;
	private Node lbl_title_gadigaden;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1500, 800);

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
			
			create_title_layout();
			
			create_table_layout();
			
			create_layout_button();
			primaryStage.setFullScreen(true);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create_title_layout() {
		title_layout  = new VBox();
		title_layout.setPadding(new Insets(30));
		title_layout.setSpacing(20);
		
		lbl_title = new Label("Quản Lí Chuyến Tàu"); 
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);
		
		layout_timkiem = new VBox();
		
		layout_lbl_timkiem = new HBox();
		layout_lbl_timkiem.setPrefSize(740, 40);
		lbl_timkiem = new Label("Nhập mã Chuyến Tàu");
		lbl_timkiem.setTranslateX(10);
		lbl_timkiem.setTranslateY(0);
		lbl_timkiem.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");
	
		ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
		img_timkiem.setTranslateX(520);
		img_timkiem.setFitHeight(25);
		img_timkiem.setFitWidth(25);
		layout_lbl_timkiem.getChildren().addAll(lbl_timkiem,img_timkiem);
		layout_lbl_timkiem.setTranslateY(48);
		layout_timkiem.getChildren().add(layout_lbl_timkiem);
		
		
		layout_txt_timkiem = new VBox();
		txt_timkiem = new TextField();
		txt_timkiem.setPrefHeight(40);
		txt_timkiem.setMaxSize(750, 45);
		txt_timkiem.setPadding(new Insets(10));
		txt_timkiem.setStyle("-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
		txt_timkiem.setFocusTraversable(false);
		layout_txt_timkiem.getChildren().add(txt_timkiem);	
		layout_timkiem.getChildren().add(layout_txt_timkiem);
		
		layout_timkiem.setTranslateX(100);
		
		title_layout.getChildren().add(layout_timkiem);
		
		txt_timkiem.focusedProperty().addListener((obs,oval,nval) -> {
			TranslateTransition tt = new TranslateTransition(Duration.millis(350), lbl_timkiem);
			if(nval)
			{
				tt.setToY(-40);
			}
			else
			{
				tt.setToY(0);
			}
			tt.play();
		});
		
		noiDungChinh.getChildren().add(title_layout);
	}
	
	public void create_table_layout() {
		table_layout = new VBox();
		
		table_layout.setPadding(new Insets(10));
		
		table_layout.setPrefSize(1200, 500);
		table_layout.setTranslateX(10);
		table_layout.setMaxSize(1200, 500);
		
		table_title = new HBox();
		table_title.setPrefSize(1200, 50);
		table_title.setMaxSize(1200, 50);
		table_title.setSpacing(30);
		
		
		String style = "-fx-font-family: 'Kanit';-fx-font-weight:bold;-fx-font-size:18px;";
		
		lbl_title_maChuyenTau = new Label("Mã Chuyến");
		
		lbl_title_maChuyenTau.setStyle(style);
		
		lbl_title_dauTau = new Label("Đầu Tàu");
		lbl_title_dauTau.setStyle(style);
		
		
		lbl_title_soluongtoa = new Label("Số lượng toa");
		
		lbl_title_soluongtoa.setStyle(style);
		
		lbl_title_veTrong = new Label("Vé Trống");
		
		lbl_title_veTrong.setStyle(style);
		
		
		lbl_title_thoigiankhoihanh = new Label("Thời Gian Khởi Hành");
		lbl_title_thoigiankhoihanh.setTranslateX(20); 
		lbl_title_thoigiankhoihanh.setStyle(style);
		
		lbl_title_gadigaden = new Label("Ga đi - Ga đến");
		lbl_title_gadigaden.setTranslateX(80);
		lbl_title_gadigaden.setStyle(style);
		
		

		table_title.getChildren().addAll(lbl_title_maChuyenTau,lbl_title_dauTau,lbl_title_soluongtoa,lbl_title_veTrong,lbl_title_thoigiankhoihanh,lbl_title_gadigaden);
		table_layout.getChildren().add(table_title);
		
		
		table_desc = new VBox();
		table_desc.setSpacing(20);
		table_desc.setPrefSize(1200, 400);
		table_desc.setMaxSize(1200, 400);
		
		create_layout_dong("CT001", "SE1",10,10,LocalTime.now(),"Hà Nội","Thành Phố Hồ Chí Minh");
		
		create_layout_dong("CT001", "SE1",10,10,LocalTime.now(),"Hà Nội","Đà Nẵng");

		
		scrollPane = new ScrollPane();
		scrollPane.setContent(table_desc);
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Không hiện thanh ngang
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Thanh dọc chỉ hiện khi cần
		scrollPane.setStyle("-fx-background-color: transparent");
		scrollPane.setPannable(true);

		// ScrollPane sẽ tự động mở rộng nhưng không vượt quá chiều cao còn lại của cửa sổ
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		scrollPane.setMaxHeight(400);
		
		table_layout.getChildren().add(scrollPane);
		
		noiDungChinh.getChildren().add(table_layout);
	}
	public void create_layout_button()
	{
		layout_button = new HBox();
		
		layout_button.setPrefSize(950, 50);
		layout_button.setAlignment(Pos.CENTER_RIGHT);
		layout_button.setTranslateX(-20);	
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size:13.5px;-fx-text-fill:white;-fx-background-radius: 20px;";
		
		btn_xoaChuyenTau = new Button("Xóa Chuyến Tàu");
		btn_xoaChuyenTau.setStyle(style+ "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #CB002CD3 23%, #CB002C1F 100%);");
		btn_xoaChuyenTau.setPrefSize(225, 50);
		btn_xoaChuyenTau.setDisable(true);
		
		btn_themChuyenTau = new Button("Thêm Chuyến Tàu");
		btn_themChuyenTau.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_themChuyenTau.setPrefSize(225, 50);
		
		
		btn_suaChuyenTau = new Button("Cập nhật thông tin");
		btn_suaChuyenTau.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_suaChuyenTau.setPrefSize(225, 50);
		
		layout_button.setSpacing(40);
		layout_button.getChildren().addAll(btn_xoaChuyenTau,btn_themChuyenTau,btn_suaChuyenTau);
		
		
		btn_xoaChuyenTau.setOnMouseClicked(event -> {
			if(hangchon.size() == 0)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông Báo");
				alert.setHeaderText(null);
				alert.setContentText("Vui Lòng Chọn Chuyến Tàu Khuyến Mãi Muốn Xóa!");
				alert.showAndWait();
			}
			else {
				ArrayList<HBox> dsxoa = new ArrayList<HBox>(hangchon);
				for(HBox x : dsxoa)
				{					
					FadeTransition ft = new FadeTransition(Duration.millis(300),x);
					ft.setFromValue(1.0);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished(E ->{						
						table_desc.getChildren().remove(x);
					});
				}
				hangchon.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Xóa Chuyến Tàu Thành Công!");
				alert.showAndWait();
			}
		});
		
		btn_xoaChuyenTau.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_xoaChuyenTau.setStyle(btn_xoaChuyenTau.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_xoaChuyenTau.setStyle(btn_xoaChuyenTau.getStyle());
			}
		});
		
		btn_themChuyenTau.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_themChuyenTau.setStyle(btn_themChuyenTau.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_themChuyenTau.setStyle(btn_themChuyenTau.getStyle());
			}
		});
		
		btn_suaChuyenTau.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_suaChuyenTau.setStyle(btn_suaChuyenTau.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_suaChuyenTau.setStyle(btn_suaChuyenTau.getStyle());
			}
		});
		
		noiDungChinh.getChildren().add(layout_button);
		
	}
								   
	public void create_layout_dong(String maChuyenTau,String dauTau,int soluong,int veTrong,LocalTime thoigiankhoihanh,String gadi,String gaden)
	{
		HBox layout_dong = new HBox();
		layout_dong.setStyle("-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;");
		layout_dong.setPadding(new Insets(20));
		layout_dong.setPrefSize(950, 100);
		layout_dong.setMaxSize(950, 100);
		layout_dong.setSpacing(50);
		
		String style = "-fx-font-family: 'Kanit';-fx-font-weight:bold;-fx-font-size:18px;";
		
		Label maChuyenTau1 = new Label(maChuyenTau);
	
		maChuyenTau1.setStyle(style);
	
		
		Label dauTau1 = new Label(dauTau);
		dauTau1.setTranslateX(25);
		dauTau1.setStyle(style);
		dauTau1.setWrapText(true);
		
	
		
		Label soLuongToa = new Label(""+soluong);
		soLuongToa.setTranslateX(60);
		soLuongToa.setStyle(style);
	
		
		
		Label veTrong1 = new Label(""+veTrong);
		veTrong1.setTranslateX(120);
		veTrong1.setStyle(style);
		
		
		
		Label thoiGianKhoiHanh = new Label(thoigiankhoihanh.toString());
		thoiGianKhoiHanh.setTranslateX(150);
		thoiGianKhoiHanh.setStyle(style);
		
		
		Label gadigaden = new Label(""+gadi+" - "+gaden);
		

		
		
		gadigaden.setPrefSize(200, 100);
		gadigaden.setTranslateX(170);
		gadigaden.setStyle(style);
		gadigaden.setTranslateY(-15);
		
		Platform.runLater(() -> {
		    Text text = new Text(gadigaden.getText());
		    text.setFont(gadigaden.getFont());
		    double textWidth = text.getLayoutBounds().getWidth();

		    if (textWidth > gadigaden.getWidth()) {
		        gadigaden.setWrapText(true);
		        gadigaden.setTranslateY(-3);
		    }
		});
		
		layout_dong.getChildren().addAll(maChuyenTau1,dauTau1,soLuongToa,veTrong1,thoiGianKhoiHanh,gadigaden);
		

		final HBox dongHienTai = layout_dong;
	    
		
		String style_macdinh = "-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;";
		String style_thaydoi = "-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;-fx-border-color:#00BACB;-fx-border-width:2px;";
		dongHienTai.setOnMouseClicked(e -> {
	        
	        if (hangchon.contains(dongHienTai)) {
	            dongHienTai.setStyle(style_macdinh);
	            hangchon.remove(dongHienTai);
	        } 
	        
	        else {
	            dongHienTai.setStyle(style_thaydoi);
	            hangchon.add(dongHienTai);
	        }
	        btn_xoaChuyenTau.setDisable(hangchon.isEmpty());
	    });
	    
		dongHienTai.setOnMouseEntered(e -> {
			dongHienTai.setStyle(dongHienTai.getStyle()+"-fx-cursor:hand;");
		});
	    
	    
		table_desc.getChildren().add(layout_dong);
	}	 
	
	
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}