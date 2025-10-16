package fourcore.GiaoDien;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class BanVe extends Application {
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
	private Label noidungtieude;
	private VBox header_layout;
	private VBox center_layout;
	private VBox center_layout1;
	private HBox combolayout1;
	private HBox combolayout2;
	private HBox combobox1;
	private HBox combobox2;
	private VBox combolayout3;
	private VBox combolayout4;
	private HBox radio_layout;
	private VBox radio_layout1;
	private VBox radio_layout2;
	private RadioButton rb_motchieu;
	private RadioButton rb_khuhoi;
	private VBox layout_btn_timkiem;
	private Button btn_timkiem;
	private DatePicker date1;
	private Font Font2;
	private DatePicker date;
	private Stage window = new Stage(); 
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
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
			
			create_head_title();
			
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
			window.setFullScreen(true);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create_head_title()
	{
		header_layout = new VBox();
		header_layout.setPrefSize(1200, 200);
		
		
		
	     
		noidungtieude = new Label("Bán Vé");
		noidungtieude.setTranslateY(50);
		noidungtieude.setTranslateX(20);
		
		InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_28pt-Bold.ttf");
	    Font font_title = Font.loadFont(is, 50);
	    noidungtieude.setFont(font_title);
		
		header_layout.getChildren().add(noidungtieude);
		noiDungChinh.getChildren().add(header_layout);
		
		
		// chua center_layout
		center_layout = new VBox();
		center_layout.setPrefSize(1000,600);
		
		
		// chua 4 combo box
		center_layout1 = new VBox();
		center_layout1.setPrefSize(1000, 300);
		center_layout1.setTranslateX(100);
		
		//chua 2 combobox dau tien
		combobox1 = new HBox();
		combobox1.setPrefSize(1000, 80);
		
		create_combobox_layout1();
		
		combobox2 = new HBox();
		combobox2.setPrefSize(1000, 50);
		create_combobox_layout2();
		
		//radio button
		radio_layout  = new HBox();
		radio_layout.setPrefSize(400,50);
		create_radio_box();
		
		//btn TimKiem
		layout_btn_timkiem = new VBox();
		layout_btn_timkiem.setPrefSize(400, 50);
		create_timkiem_btn();
		
	}
	
	public void create_combobox_layout1() {
		
		// combo 1
		combolayout1 = new HBox(); 
		combolayout1.setPrefSize(400, 150);
		ObservableList<String> items = FXCollections.observableArrayList("Ha Noi","TPHCM","Da Nang");
		 
		ComboBox<String> comboBox1 = createSearchableComboBox(items);
	    comboBox1.setPromptText("Ga đi");
	    comboBox1.setPrefWidth(300);
	    //comboBox1.setStyle("-fx-background-color  : rgb(121,217,225)");
	        
	    comboBox1.setId("combo-box");
	    combolayout1.getChildren().add(comboBox1);
		combobox1.getChildren().add(combolayout1);
		//center_layout1.getChildren().add(combobox1);
		
		
		
		//combo 2
		combolayout2 = new HBox(); 
		combolayout2.setPrefSize(400, 150);
		ObservableList<String> item = FXCollections.observableArrayList("Ha Noi","TPHCM","Da Nang");
		
		
		ComboBox<String> combobox = createSearchableComboBox(item);
	    combobox.setPromptText("Ga đến");
	    combobox.setPrefWidth(300);
	   // combobox.setStyle("-fx-background-color  : rgb(121,217,225)");
	    combobox.setId("combo-box");
        combolayout2.getChildren().add(combobox);
		
        combobox1.getChildren().add(combolayout2);
        
		center_layout1.getChildren().add(combobox1);
		
		
		
		center_layout.getChildren().add(center_layout1);
		noiDungChinh.getChildren().add(center_layout);
	}
	private ComboBox<String> createSearchableComboBox(ObservableList<String> items) {
        ComboBox<String> combobox = new ComboBox<>(items);
        
        combobox.setPrefHeight(35);
        combobox.setPrefWidth(200);
        combobox.setEditable(true);
        combobox.setId("combo-box");
        
        InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
        Font font_combobox = Font.loadFont(is, 15);
        combobox.getEditor().setFont(font_combobox);
        
        
        ObservableList<String> listgoc = FXCollections.observableArrayList(items);
        
        combobox.getEditor().textProperty().addListener((obs,oval,nval) -> {
        if(nval == null || nval.isEmpty())
        {
        		combobox.setItems(listgoc);
        		combobox.hide();
        }
        else {
        ObservableList<String> list2 = FXCollections.observableArrayList();
        String txt = nval.toLowerCase();
        for(String item : listgoc)
        {
        	if(item.toLowerCase().contains(txt))
        	{	
        		list2.add(item);
        	}
        }
        
        combobox.setItems(list2);
        if(!list2.isEmpty())
        {
        	combobox.show();
        }
        else 
        {
        	combobox.hide();
        }
        }
        });
        
        combobox.setOnAction(e -> {
        	String txt = combobox.getSelectionModel().getSelectedItem();
        	if(txt != null)
        	{
        		combobox.getEditor().setText(txt);
        	}
        });
        
        
        combobox.setOnHidden(e -> {
        	String txt = combobox.getSelectionModel().getSelectedItem();
        	if(txt!= null && !txt.isEmpty() && listgoc.contains(txt)) {
        		combobox.setValue(txt);
        	}
        });
        combobox.skinProperty().addListener((obs, oldSkin, newSkin) -> {
		    if (newSkin != null) {
		        javafx.scene.Node arrowButton = combobox.lookup(".arrow-button");
		        if (arrowButton != null) {
		            arrowButton.setStyle("-fx-background-color: transparent;");
		        }
		    }
		});
        return combobox;
    }
	public void create_combobox_layout2()
	{
		//combo 3
		combolayout3 = new VBox();
		combolayout3.setPrefSize(400,50);
		date = new DatePicker();
		date.setPrefSize(300, 35);
		date.setId("date");
		date.setPromptText("Ngày đi");
		date.setOnAction(e -> {
			LocalDate ngaydi = date.getValue();
			date.setValue(ngaydi);
		});
		InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
	    Font font_combobox = Font.loadFont(is, 15);
	    date.getEditor().setFont(font_combobox);
		
		combolayout3.getChildren().add(date);
		combobox2.getChildren().add(combolayout3);
		
		
		
		//combo4
		combolayout4 = new VBox();
		combolayout4.setPrefSize(400, 50);
		date1 = new DatePicker();
		date1.setPrefSize(300,35);
		date1.setPromptText("Ngày về");
		date1.setOnAction(e -> {
			LocalDate ngayve = date1.getValue();
			date1.setValue(ngayve);
		});
		
		date1.getEditor().setFont(font_combobox);
		date1.setId("date");
		combolayout4.getChildren().add(date1);
		combobox2.getChildren().add(combolayout4);

		center_layout1.getChildren().add(combobox2);
	}
	public void create_radio_box()
	{
		radio_layout = new HBox();
		ToggleGroup loaive = new ToggleGroup();
		
		InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf");
		Font font_combobox = Font.loadFont(is, 15);
		
		radio_layout1 = new VBox();
		
		rb_motchieu = new RadioButton("Một Chiều");
		rb_motchieu.setPrefSize(150,30);
		rb_motchieu.setStyle("-fx-font-size:15px;-fx-font-weight:bold;");
		rb_motchieu.setToggleGroup(loaive);
		
	    rb_motchieu.setFont(font_combobox);
		radio_layout1.getChildren().add(rb_motchieu);
		
		
		radio_layout2 = new VBox();
		
		rb_khuhoi = new RadioButton("Khứ Hồi");
		rb_khuhoi.setFont(font_combobox);
		rb_khuhoi.setPrefSize(150,30);
		rb_khuhoi.setToggleGroup(loaive);
		
		radio_layout2.getChildren().add(rb_khuhoi);
		
		radio_layout.getChildren().addAll(radio_layout1,radio_layout2);
		radio_layout2.setTranslateX(50);
		radio_layout.setTranslateX(515);
		radio_layout.setTranslateY(-140);
		
		loaive.selectedToggleProperty().addListener((obs,oval,nval) -> {
			if(nval == rb_khuhoi)
			{
				combolayout4.setDisable(false);
				date1.setEditable(false);
				date1.setDisable(false);
				rb_khuhoi.setStyle("-fx-text-fill: #00ABCB;");
			}else{
				combolayout4.setDisable(true);
				date1.setEditable(true);
				date1.setDisable(true);
				rb_khuhoi.setStyle("-fx-text-fill: #AEAEAE;");
			}
		});
		center_layout.getChildren().add(radio_layout);
	}
	
	public void create_timkiem_btn()
	{
		btn_timkiem = new Button("Tìm Kiếm");
		btn_timkiem.setPrefSize(200, 50);
		InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-SemiBold.ttf");
	    Font font_timkiem = Font.loadFont(is, 30);
	    btn_timkiem.setFont(font_timkiem);
		
		btn_timkiem.setPadding(new Insets(5));
		btn_timkiem.setStyle(
			    "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #00BACB 23%, #B6D0D3 100%);" +
			    "-fx-text-fill: white;" +
			    "-fx-background-radius: 20px;" +
			    "-fx-border-radius: 20px;"+
			    "-fx-cursor : hand;"
			);
		layout_btn_timkiem.getChildren().add(btn_timkiem);
		layout_btn_timkiem.setTranslateX(350);
		layout_btn_timkiem.setTranslateY(-100);
		
		center_layout.getChildren().add(layout_btn_timkiem);
		
		btn_timkiem.hoverProperty().addListener((obs,oval,nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_timkiem);
			if(nval)
			{
				st.setToX(1.1);
				st.setToY(1.1);
			}
			else {
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});
		btn_timkiem.setOnAction(E -> {
			window.close();
			TrangChu tc = new TrangChu();
			Stage newstage = new Stage();
			tc.start(newstage);
		});
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(BanVe.class, args);
	}
}