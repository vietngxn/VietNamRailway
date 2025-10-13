package fourcore.GiaoDien;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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


public class QuanLyCTKM extends Application {
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
	private Label lbl_title_maCTKM;
	private Label lbl_title_tenCTKM;
	private Node lbl_title_ngaybatdau;
	private Node lbl_title_ngayketthuc;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private ArrayList<HBox> hangchon = new ArrayList<>();
	private HBox layout_button;
	private Button btn_xoaCTKM;
	private Button btn_themCTKM;
	private Button btn_capnhat;
	private Label lbl_title_doituong;
	private Node lbl_title_trangthai;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1200,800);
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
		
		lbl_title = new Label("Quản Lí chương trình khuyến mãi"); 
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);
		
		layout_timkiem = new VBox();
		
		layout_lbl_timkiem = new HBox();
		layout_lbl_timkiem.setPrefSize(740, 40);
		lbl_timkiem = new Label("Tìm kiếm chương trình khuyến mãi");
		lbl_timkiem.setTranslateX(10);
		lbl_timkiem.setTranslateY(0);
		lbl_timkiem.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");
	
		ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
		img_timkiem.setTranslateX(410);
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
		
		lbl_title_maCTKM = new Label("Mã Chương Trình");
		
		lbl_title_maCTKM.setStyle(style);
		
		lbl_title_tenCTKM = new Label("Tên Chương Trình");
		lbl_title_tenCTKM.setStyle(style);
		
		
		lbl_title_ngaybatdau = new Label("Ngày Bắt Đầu");
		
		lbl_title_ngaybatdau.setStyle(style);
		
		lbl_title_ngayketthuc = new Label("Ngày kết thúc");
		
		lbl_title_ngayketthuc.setStyle(style);
		
		
		lbl_title_doituong = new Label("Đối Tượng áp dụng");
		
		lbl_title_doituong.setStyle(style);
		
		lbl_title_trangthai = new Label("Trạng Thái");
		lbl_title_trangthai.setTranslateX(-10);
		lbl_title_trangthai.setStyle(style);
		
		
				
		table_title.getChildren().addAll(lbl_title_maCTKM,lbl_title_tenCTKM,lbl_title_ngaybatdau,lbl_title_ngayketthuc,lbl_title_doituong,lbl_title_trangthai);
		table_layout.getChildren().add(table_title);
		
		
		table_desc = new VBox();
		table_desc.setSpacing(20);
		table_desc.setPrefSize(1200, 400);
		table_desc.setMaxSize(1200, 400);
		
		create_layout_dong("CTKM001", "Quach Ngoc Long",LocalDate.now(),LocalDate.now(),"tiendat",true);
		
		create_layout_dong("CTKM001", "Quach Ngoc Long",LocalDate.now(),LocalDate.now(),"tiendat",false);
		create_layout_dong("CTKM001", "Quach Ngoc Long",LocalDate.now(),LocalDate.now(),"tiendat",false);
		create_layout_dong("CTKM001", "Quach Ngoc Long",LocalDate.now(),LocalDate.now(),"tiendat",true);
		
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
		
		btn_xoaCTKM = new Button("Xóa Chương Trình Khuyến Mãi");
		btn_xoaCTKM.setStyle(style+ "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #CB002CD3 23%, #CB002C1F 100%);");
		btn_xoaCTKM.setPrefSize(225, 50);
		btn_xoaCTKM.setDisable(true);
		
		btn_themCTKM = new Button("Thêm Chương Trình Khuyến Mãi");
		btn_themCTKM.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_themCTKM.setPrefSize(225, 50);
		
		
		btn_capnhat = new Button("Cập nhật thông tin");
		btn_capnhat.setStyle(style+"-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_capnhat.setPrefSize(225, 50);
		
		layout_button.setSpacing(40);
		layout_button.getChildren().addAll(btn_xoaCTKM,btn_themCTKM,btn_capnhat);
		
		
		btn_xoaCTKM.setOnMouseClicked(event -> {
			if(hangchon.size() == 0)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông Báo");
				alert.setHeaderText(null);
				alert.setContentText("Vui Lòng Chọn Chương Trình Khuyến Mãi Muốn Xóa!");
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
				alert.setContentText("Xóa Chương Trình Khuyến Mãi Thành Công!");
				alert.showAndWait();
			}
		});
		
		btn_xoaCTKM.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_xoaCTKM.setStyle(btn_xoaCTKM.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_xoaCTKM.setStyle(btn_xoaCTKM.getStyle());
			}
		});
		
		btn_themCTKM.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_themCTKM.setStyle(btn_themCTKM.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_themCTKM.setStyle(btn_themCTKM.getStyle());
			}
		});
		
		btn_capnhat.hoverProperty().addListener((obs,oval,nval) -> {
			if(nval)
			{
				btn_capnhat.setStyle(btn_capnhat.getStyle() +"-fx-cursor:hand;");
			}
			else {
				btn_capnhat.setStyle(btn_capnhat.getStyle());
			}
		});
		
		noiDungChinh.getChildren().add(layout_button);
		
	}
	public void create_layout_dong(String maCT,String tenCT,LocalDate ngaybatdau,LocalDate ngayketthuc,String doituong,boolean trangthai)
	{
		HBox layout_dong = new HBox();
		layout_dong.setStyle("-fx-background-color : #00BACB4D;-fx-background-radius:15px;-fx-border-radius:15px;");
		layout_dong.setPadding(new Insets(20));
		layout_dong.setPrefSize(950, 70);
		layout_dong.setMaxSize(950, 70);
		layout_dong.setSpacing(50);
		
		String style = "-fx-font-family: 'Kanit';-fx-font-weight:bold;-fx-font-size:18px;";
		
		Label maCTKM = new Label(maCT);
	
		maCTKM.setStyle(style);
	
		
		Label tenCTKM = new Label(tenCT);
		tenCTKM.setTranslateX(25);
		tenCTKM.setStyle(style);
		tenCTKM.setWrapText(true);
		
	
		
		Label ngaybatdau1 = new Label(ngaybatdau.toString());
		ngaybatdau1.setTranslateX(15);
		ngaybatdau1.setStyle(style);
	
		
		Label ngayketthuc1 = new Label(ngayketthuc.toString());
		ngayketthuc1.setTranslateX(15);
		ngayketthuc1.setStyle(style);
		
		
		
		Label doituongapdung = new Label(doituong);
		doituongapdung.setTranslateX(60);
		doituongapdung.setStyle(style);
		
		Label s = new Label("Đã Kết Thúc");
		s.setStyle(style+"-fx-text-fill:red;");	
		s.setTranslateX(80);
		if(trangthai == true)
		{
			s = new Label("Sẵn Sàng");
			s.setStyle(style+"-fx-text-fill:green;");
			s.setTranslateX(90);
		}
		
		Label trangthai1 = s;
		
		
		
		layout_dong.getChildren().addAll(maCTKM,tenCTKM,ngaybatdau1,ngayketthuc1,doituongapdung,trangthai1);
		

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
	        btn_xoaCTKM.setDisable(hangchon.isEmpty());
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