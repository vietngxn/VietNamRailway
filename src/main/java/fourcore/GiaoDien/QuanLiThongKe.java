package fourcore.GiaoDien;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class QuanLiThongKe extends Application {
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
	private HBox layout_nutthongke;
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
	private VBox button_layout;
	private Button button_thongKe1;
	private Button button_thongKe2;
	private Button button_thongKe3;
	private HBox layout_combobox;
	private ComboBox<String> comboBox;
	private DatePicker date;
	private VBox btn_layout;
	private Button btn_xuatThongKe;
    String loaiThongKe;
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
			root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			create_title_layout();
		
			create_table_desc();
			
			create_btnlayout();
			primaryStage.setFullScreen(true);
//			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public VBox getQuanLiThongKe(){
        return this.noiDungChinh;
    }
    public void setLoaiThongKe(String loaiThongKe){
        this.loaiThongKe = loaiThongKe;
    }

	public void create_title_layout() {
		title_layout  = new VBox();
		title_layout.setPadding(new Insets(30));
		title_layout.setSpacing(20);
		
		lbl_title = new Label("Quản Lí Thống Kê"); 
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);
		
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size: 20px;-fx-text-fill:#00BACB;-fx-background-color:white;-fx-background-color:white;-fx-background-radius:20px;-fx-border-radius:20px;-fx-border-color:#00BACB;-fx-border-width:2px;";
		
		
		layout_nutthongke = new HBox();
		layout_nutthongke.setSpacing(50);
		
		
		Button button_thongKe1 = new Button("Thống Kê A");
		button_thongKe1.setStyle(style);
		button_thongKe1.setPrefSize(250, 50);
		
		Button button_thongKe2 = new Button("Thống Kê B");
		button_thongKe2.setStyle(style);
		button_thongKe2.setPrefSize(250, 50);
		
		
		Button button_thongKe3 = new Button("Thống Kê C");
		button_thongKe3.setStyle(style);
		button_thongKe3.setPrefSize(250, 50);
		
		layout_nutthongke.getChildren().addAll(button_thongKe1,button_thongKe2,button_thongKe3);
		
		final boolean[] check1 = {false};
		final boolean[] check2 = {false};
		final boolean[] check3 = {false};

		button_thongKe1.setOnMouseClicked(e -> {
		    if (!check1[0]) {
		        button_thongKe1.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        
		        check1[0] = true;
		        check2[0] = false;  
		        check3[0] = false;
		    } else {
		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        check1[0] = false;
		    }
		});

		button_thongKe2.setOnMouseClicked(e -> {
		    if (!check2[0]) {
		        button_thongKe2.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        
		        check2[0] = true;
		        check1[0] = false;  
		        check3[0] = false;
		    } else {
		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        check2[0] = false;
		    }
		});

		button_thongKe3.setOnMouseClicked(e -> {
		    if (!check3[0]) {
		        button_thongKe3.setStyle(style + "-fx-background-color:#00BACB;-fx-text-fill:white;");
		        button_thongKe1.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        button_thongKe2.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        
		        check3[0] = true;
		        check1[0] = false;  
		        check2[0] = false;
		    } else {
		        button_thongKe3.setStyle(style + "-fx-background-color:white;-fx-text-fill:#00BACB;");
		        check3[0] = false;
		    }
		});
		

		title_layout.getChildren().add(layout_nutthongke);
		
		layout_combobox = new HBox();
		layout_combobox.setSpacing(30);
		ObservableList<String> items = FXCollections.observableArrayList("BarChart","PieChart");
		
		String style1 = "-fx-font-family:'Inter';-fx-text-fill:#00BACB;-fx-font-size: 15px;";
		comboBox = createSearchableComboBox(items);
		comboBox.setPromptText("Thời Gian");
		comboBox.setStyle(comboBox.getStyle()+";"+style1);
		
		date = new DatePicker();
		date.setPromptText("Kiểu Thông Kê");
		date.setStyle(style1+";-fx-border-color:#00BACB;-fx-border-width:2px;");
		
		date.setPrefSize(400, 40);
		date.getStyleClass().add("date-picker-custom");
		layout_combobox.getChildren().addAll(date,comboBox);
		
		title_layout.getChildren().add(layout_combobox);
		
		
		noiDungChinh.getChildren().add(title_layout);
	}
	
	private ComboBox<String> createSearchableComboBox(ObservableList<String> items) {
        ComboBox<String> combobox = new ComboBox<>(items);
        combobox.getStyleClass().add("combo-box-custom");
        combobox.setPrefHeight(40);
        combobox.setPrefWidth(400);
        combobox.setEditable(true);
        combobox.setId("combo-box");
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
	
	public void create_table_desc()
	{
		table_desc = new VBox();
		
		if(!comboBox.getEditor().getText().equalsIgnoreCase("BarChart"))
		{
			create_barchart();
		}
		else
		{
			create_piechart();
		}
		noiDungChinh.getChildren().add(table_desc);
	}
	
	public void create_barchart() {
	    CategoryAxis xAxis = new CategoryAxis();
	    NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Tháng");
	    yAxis.setLabel("Doanh thu (triệu)");

	    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
	    barChart.setTitle("Doanh thu theo tháng");

	    XYChart.Series<String, Number> data = new XYChart.Series<>();
	    data.setName("Năm 2025");
	    data.getData().add(new XYChart.Data<>("Tháng 1", 50));
	    data.getData().add(new XYChart.Data<>("Tháng 2", 80));
	    data.getData().add(new XYChart.Data<>("Tháng 3", 40));

	    barChart.getData().add(data);
	    table_desc.getChildren().add(barChart);
	}
	
	public void create_piechart()
	{
		PieChart pieChart = new PieChart();
	    pieChart.setTitle("Tỷ lệ sản phẩm bán ra");

	    pieChart.getData().add(new PieChart.Data("Doanh Thu", 90));
	    pieChart.getData().add(new PieChart.Data("Thuế VAT", 10));
	    
	    
	    table_desc.getChildren().add(pieChart);
	}
	
	public void create_btnlayout()
	{
		btn_layout = new VBox();
		btn_layout.setTranslateX(700);
		btn_layout.setTranslateY(50);
		btn_xuatThongKe = new Button("Xuất Thống Kê");
		btn_xuatThongKe.setPrefSize(200,40);
		
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size: 20px;-fx-text-fill:#00BACB;-fx-background-color:white;-fx-background-color:white;-fx-background-radius:20px;-fx-border-radius:20px;-fx-border-color:#00BACB;-fx-border-width:2px;";
		btn_xuatThongKe.setStyle(style+"-fx-background-color:#00BACB;-fx-text-fill:white;");
		btn_layout.getChildren().add(btn_xuatThongKe);
		
		noiDungChinh.getChildren().add(btn_layout);
	}
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}