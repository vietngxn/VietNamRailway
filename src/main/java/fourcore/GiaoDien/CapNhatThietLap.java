package fourcore.GiaoDien;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.GheNgoi;
import fourcore.Entity.GheTrenChuyenTau;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;
import fourcore.Entity.ToaTau;
import fourcore.animation.Animation;
import fourcore.animation.GhiFile;
import fourcore.dao.*;
import fourcore.dao.GheTrenChuyenTau_dao;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class CapNhatThietLap extends Application {
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
	private VBox layoutThemCT= new VBox(20);;
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
	private GheNgoiDAO ghedao = null;
	private ToaTauDAO toataudao = new ToaTauDAO();
	private Animation animation = new Animation();
	private GridPane gridAllGhe;
	private StackPane spAllGhe;
	private int flagScroll = 0;
	private Map<String, ArrayList<GheNgoi>> mapGheTheoToa = new HashMap<>();
	ArrayList<String> listMaToa;
	private Tau_DAO taudao = new Tau_DAO();
	private LoaiTau_DAO loaitaudao = new LoaiTau_DAO();
	private ArrayList<String> listGheChon = new ArrayList<>();
	private StackPane spToaDangChon = null;
	private ChuyenTauDAO chuyentaudao = null;
	private File fileTmp;
	private GhiFile ghiFile = new GhiFile();
	private Label lblGiaGocGhe;
	private Label lblGiaVeHienTai;
	private HanhTrinh_DAO hanhtrinhdao = new HanhTrinh_DAO();
	private Map<String, Double> mapUpdateGhe = new HashMap<>();
	private GheTrenChuyenTau_dao ghetrenchuyentaudao = new GheTrenChuyenTau_dao();
	private ChuyenTau chuyenTauCuaGheTrenChuyenTau = null;
	private Button buttonTroLai;
	private GridPane gridSPAllGheGiaGoc;
	int coto = 0, dongo = 0, counto = 0;
	private GridPane gridSPAllGheGiaHienTai = new GridPane();
	private GridPane gridSPAllGheHienTai = new GridPane();
	boolean checkVar = false;
	boolean checkVarToa = false;

    public CapNhatThietLap() throws SQLException {
    }

    @Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1540, 1000);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

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

			create_themThietLap_layout();
			
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			
			noiDungChinh = layoutThemCT;
			
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);
			
			primaryStage.setFullScreen(true);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void animationUpdateToa(Parent toaCu, Parent toaUpdate) {
		spAllGhe.getChildren().add(toaUpdate);
		toaUpdate.setOpacity(0);
		
		FadeTransition animationMoOut = new FadeTransition(Duration.millis(600), toaCu);
		animationMoOut.setFromValue(1.0);
		animationMoOut.setToValue(0.0);
		
		FadeTransition animationMoIn = new FadeTransition(Duration.millis(600), toaUpdate);
		animationMoIn.setFromValue(0.0);
		animationMoIn.setToValue(1.0);
		
		
		
		animationMoOut.setOnFinished(event -> {
			animationMoIn.play();
			spAllGhe.getChildren().remove(toaCu);
		});
		
		animationMoOut.play();
	}
	
	public void animationSlideToa(Parent toaCu, Parent toaMoi, boolean scrollRight) {
	    double width = spAllGhe.getWidth();
	    spAllGhe.getChildren().add(toaMoi);

	    if(scrollRight) toaMoi.setTranslateX(-width);
	    else toaMoi.setTranslateX(width);
	    
	    // Animation cho toa cu truot khoi giao dien
	    TranslateTransition animationOut = new TranslateTransition(Duration.millis(350), toaCu);
	    if(scrollRight) animationOut.setToX(width);
	    else animationOut.setToX(-width);
	    
	    FadeTransition animationMoOut = new FadeTransition(Duration.millis(350), toaCu);
	    animationMoOut.setToValue(0.0);

	    // Animation cho toa moi truot vao giao dien
	    TranslateTransition animationIn = new TranslateTransition(Duration.millis(350), toaMoi);
	    animationIn.setToX(0);
	    
	    FadeTransition animationMoIn = new FadeTransition(Duration.millis(350), toaMoi);
	    animationMoIn.setToValue(1.0);

	    animationOut.play();
	    animationMoOut.play();
	    animationIn.play();
	    animationMoIn.play();

	    animationIn.setOnFinished(ev -> {
	    	spAllGhe.getChildren().remove(toaCu);
	    });
	}
	
	



	
	public GridPane setUpGheNgoi(String maToa, ArrayList<GheNgoi> listGheTheoToa, Tau tau, Map<String, String> mapGheTheoToaGiaGoc, Map<String, String>mapGheTheoToaGiaHienTai) throws SQLException {
		
		DecimalFormat df = new DecimalFormat("#,###");
		String duongdanghethuong = "/img/ghe.png";
		String duongdangheluudong = "/img/gheluudong.png";
		
		int count = 0, cot = 0, dong = 0, cotAll = 0, dongAll = 0;		
		GridPane gridCumGhe = new GridPane();
		
		GridPane gridAllGhe = new GridPane();
		gridAllGhe.setHgap(50);
		gridAllGhe.setVgap(50);
		
		
		Popup popup = new Popup();
		Label popupLabel = new Label();
        popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
        popup.getContent().add(popupLabel);
		
		for(GheNgoi gn : listGheTheoToa) {
			if(gn.getToaTau().getMaToaTau().equalsIgnoreCase(maToa)) {
				if(count < 24) {
					
					Label lblGhe = new Label(Integer.toString(gn.getSoGhe()));
					lblGhe.setId("lbl_Ghe");
					
					StackPane spGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblGhe);
					spGhe.setUserData(gn.getMaGheNgoi());
					
					double giaTienGhe = tau.getLoaiTau().getGiaCuoc() * 1726;
					giaTienGhe += gn.getGiaTriTangThem() + gn.getTang().getGiaTang();
					String giaTienGheString = df.format(giaTienGhe);
					
					
					spGhe.setOnMouseClicked(event -> {
						
						
						ImageView imgGhe = (ImageView) spGhe.getChildren().get(0);
						
						String duongdanghethuongchuachon = getClass().getResource("/img/ghe.png").toExternalForm();
						String duongdanghechon = getClass().getResource("/img/gheDangChon.png").toExternalForm();
						
						if(listGheChon.contains(gn.getMaGheNgoi())) {
							imgGhe.setImage(new Image(duongdanghethuongchuachon));
							listGheChon.remove(gn.getMaGheNgoi());
						} else {
							imgGhe.setImage(new Image(duongdanghechon));
							listGheChon.add(gn.getMaGheNgoi());
						}
						
//						StackPane spGiaGhe = spGhe;
						
//						String giaGoc = mapGheTheoToaGiaGoc.get(spGhe.getChildren().get(1).toString());
//						String giaHienTai = mapGheTheoToaGiaHienTai.get(spGhe.getChildren().get(1).toString());
//						
//						Label txtGiaGoc = new Label(mapGheTheoToaGiaGoc.get(spGhe.getChildren().get(1).toString()));
//						txtGiaGoc.setId("txt_Money");
//						
//						VBox boxSPGiaGheGoc = new VBox();
//						boxSPGiaGheGoc.getChildren().addAll(spGiaGhe, txtGiaGoc);
//						
//						if(counto %4 == 0 && counto != 0) {
//							dongo++;
//						}
//						
//						gridSPAllGheGiaGoc.add(boxSPGiaGheGoc, coto, dongo);
//						coto++;
//						
						coto = 0; dongo = 0;
						gridSPAllGheGiaGoc.setPrefWidth(300);
						gridSPAllGheGiaGoc.setPrefHeight(490);
						gridSPAllGheGiaGoc.getChildren().clear();
						gridSPAllGheGiaGoc.setHgap(20);
						gridSPAllGheGiaGoc.setPadding(new Insets(5));
						
						gridSPAllGheGiaHienTai.setPrefWidth(300);
						gridSPAllGheGiaHienTai.setPrefHeight(490);
						gridSPAllGheGiaHienTai.getChildren().clear();
						gridSPAllGheGiaHienTai.setHgap(20);
						gridSPAllGheGiaHienTai.setPadding(new Insets(5));
						
						for(int i = 0; i < listGheChon.size(); i++) {
							String soGhe = mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[0].trim();;
							Label lblSPAllGheGiaGoc = new Label(soGhe);
							lblSPAllGheGiaGoc.setId("lbl_Ghe");
							
							StackPane spGiaGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaGoc);
							Label txtGiaGoc = new Label(mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaGoc.setId("txt_Money");
							Label txtGiaHienTai = new Label(mapGheTheoToaGiaHienTai.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaHienTai.setId("txt_Money");
							
							VBox boxSPGiaGheGoc = new VBox();
							boxSPGiaGheGoc.getChildren().addAll(spGiaGhe, txtGiaGoc);
							boxSPGiaGheGoc.setAlignment(Pos.CENTER);
							
							Label lblSPAllGheGiaHienTai = new Label(soGhe);
							lblSPAllGheGiaHienTai.setId("lbl_Ghe");
							
							StackPane spGiaGheHienTai = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaHienTai);
							VBox boxSPGiaGheHienTai = new VBox();
							boxSPGiaGheHienTai.getChildren().addAll(spGiaGheHienTai, txtGiaHienTai);
							boxSPGiaGheHienTai.setAlignment(Pos.CENTER);
							
							if(i %4 == 0 && i != 0) {
								dongo++;
								coto = 0;
							}
							gridSPAllGheGiaGoc.add(boxSPGiaGheGoc, coto, dongo);
							gridSPAllGheGiaHienTai.add(boxSPGiaGheHienTai, coto, dongo);
							coto++;
						}
						
//						StringBuilder chuoiGiaGoc = new StringBuilder();
//						StringBuilder chuoiGiaHienTai = new StringBuilder();
//						chuoiGiaHienTai.append("Giá vé tăng thêm: ");
//						chuoiGiaGoc.append("Giá gốc: ");
//						for(String maGhe : listGheChon) {
//						    String giaGoc = mapGheTheoToaGiaGoc.get(maGhe);
//						    String giaHienTai = mapGheTheoToaGiaHienTai.get(maGhe);
//						    chuoiGiaGoc.append(giaGoc + "vnd ");
//						    chuoiGiaHienTai.append(giaHienTai + "vnd ");
//						}
//						lblGiaGocGhe.setText(chuoiGiaGoc.toString());
//						lblGiaVeHienTai.setText(chuoiGiaHienTai.toString());
					});
					
					spGhe.setOnMouseEntered(event -> {
						String content = String.format(
			                        "Loại ghế: %s\nGiá ghế: %s\nGiá trị tăng thêm %s",
			                        gn.getLoaiGhe().getTenLoaiGhe(),
			                        giaTienGheString,
			                        df.format(gn.getGiaTriTangThem())
			                );
			                popupLabel.setText(content);
			                popup.show(spGhe, event.getScreenX() + 10, event.getScreenY() + 10);
					});
					
					spGhe.setOnMouseMoved(e -> {
			            popup.setX(e.getScreenX() + 10);
			            popup.setY(e.getScreenY() + 10);
			        });


					spGhe.setOnMouseExited(e -> {
			            popup.hide();
			        });
				
					if(count %6 == 0) {
						if(count %6 == 0 && count != 0) {
							gridCumGhe.setHgap(10);
							gridCumGhe.setVgap(10);
							gridAllGhe.add(gridCumGhe, cotAll, dongAll);
							dongAll++;
							if(dongAll == 2) {
								cotAll++;
								dongAll = 0;
							}
							cot = 0;
							dong = 0;
							gridCumGhe = new GridPane();
						}
						
						gridCumGhe.add(spGhe, cot, dong);
						
						cot++;
//						if(cot == 3) {
//							dong++; 
//							cot = 0;
//						}
						count++;
					} else {
						gridCumGhe.add(spGhe, cot, dong);
						
						cot++;
						if(cot == 3) {
							dong++; 
							cot = 0;
						}
						count++;
					}
				} else {
					Label lblGhe = new Label(Integer.toString(gn.getSoGhe()));
					lblGhe.setId("lbl_Ghe");
					
					StackPane spGhe = new StackPane(animation.taoImgGhe(duongdangheluudong), lblGhe);
					spGhe.setUserData(gn.getMaGheNgoi());	
					
					spGhe.setOnMouseClicked(event -> {
						
						
						ImageView imgGhe = (ImageView) spGhe.getChildren().get(0);
						
						String duongdangheluudongchuachon = getClass().getResource("/img/gheluudong.png").toExternalForm();
						String duongdanghechon = getClass().getResource("/img/gheDangChon.png").toExternalForm();
						
						if(listGheChon.contains(gn.getMaGheNgoi())) {
							imgGhe.setImage(new Image(duongdangheluudongchuachon));
							listGheChon.remove(gn.getMaGheNgoi());
						} else {
							imgGhe.setImage(new Image(duongdanghechon));
							listGheChon.add(gn.getMaGheNgoi());
						}
						
						coto = 0; dongo = 0;
						gridSPAllGheGiaGoc.setPrefWidth(300);
						gridSPAllGheGiaGoc.setPrefHeight(490);
						gridSPAllGheGiaGoc.getChildren().clear();
						gridSPAllGheGiaGoc.setHgap(20);
						gridSPAllGheGiaGoc.setPadding(new Insets(5));
						
						gridSPAllGheGiaHienTai.setPrefWidth(300);
						gridSPAllGheGiaHienTai.setPrefHeight(490);
						gridSPAllGheGiaHienTai.getChildren().clear();
						gridSPAllGheGiaHienTai.setHgap(20);
						gridSPAllGheGiaHienTai.setPadding(new Insets(5));
						
						for(int i = 0; i < listGheChon.size(); i++) {
							String soGhe = mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[0].trim();;
							Label lblSPAllGheGiaGoc = new Label(soGhe);
							lblSPAllGheGiaGoc.setId("lbl_Ghe");
							
							StackPane spGiaGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaGoc);
							Label txtGiaGoc = new Label(mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaGoc.setId("txt_Money");
							Label txtGiaHienTai = new Label(mapGheTheoToaGiaHienTai.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaHienTai.setId("txt_Money");
							
							VBox boxSPGiaGheGoc = new VBox();
							boxSPGiaGheGoc.getChildren().addAll(spGiaGhe, txtGiaGoc);
							boxSPGiaGheGoc.setAlignment(Pos.CENTER);
							
							Label lblSPAllGheGiaHienTai = new Label(soGhe);
							lblSPAllGheGiaHienTai.setId("lbl_Ghe");
							
							StackPane spGiaGheHienTai = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaHienTai);
							VBox boxSPGiaGheHienTai = new VBox();
							boxSPGiaGheHienTai.getChildren().addAll(spGiaGheHienTai, txtGiaHienTai);
							boxSPGiaGheHienTai.setAlignment(Pos.CENTER);
							
							if(i %4 == 0 && i != 0) {
								dongo++;
								coto = 0;
							}
							gridSPAllGheGiaGoc.add(boxSPGiaGheGoc, coto, dongo);
							gridSPAllGheGiaHienTai.add(boxSPGiaGheHienTai, coto, dongo);
							coto++;
						}
						
//						StringBuilder chuoiGiaGoc = new StringBuilder();
//						StringBuilder chuoiGiaHienTai = new StringBuilder();
//						chuoiGiaHienTai.append("Giá vé tăng thêm: ");
//						chuoiGiaGoc.append("Giá gốc: ");
//						for(String maGhe : listGheChon) {
//						    String giaGoc = mapGheTheoToaGiaGoc.get(maGhe);
//						    String giaHienTai = mapGheTheoToaGiaHienTai.get(maGhe);
//						    chuoiGiaGoc.append(giaGoc + "vnd ");
//						    chuoiGiaHienTai.append(giaHienTai + "vnd ");
//						}
//						lblGiaGocGhe.setText(chuoiGiaGoc.toString());
//						lblGiaVeHienTai.setText(chuoiGiaHienTai.toString());
					});
					
					spGhe.setOnMouseEntered(event -> {
						String content = String.format(
			                        "Loại ghế: %s\nGiá cước ghế: %s\nGiá trị tăng thêm %s",
			                        gn.getLoaiGhe().getTenLoaiGhe(),
			                        df.format(tau.getLoaiTau().getGiaCuoc()),
			                        df.format(gn.getGiaTriTangThem())
			                );
			                popupLabel.setText(content);
			                popup.show(spGhe, event.getScreenX() + 10, event.getScreenY() + 10);
					});
					
					spGhe.setOnMouseMoved(e -> {
			            popup.setX(e.getScreenX() + 10);
			            popup.setY(e.getScreenY() + 10);
			        });


					spGhe.setOnMouseExited(e -> {
			            popup.hide();
			        });
					
					if(count %6 == 0) {
						if(count %6 == 0 && count != 0) {
							gridCumGhe.setHgap(10);
							gridCumGhe.setVgap(10);
							gridAllGhe.add(gridCumGhe, cotAll, dongAll);
							dongAll++;
							if(dongAll == 2) {
								cotAll++;
								dongAll = 0;
							}
							cot = 0;
							dong = 0;
							gridCumGhe = new GridPane();
						}
						
						gridCumGhe.add(spGhe, cot, dong);
						cot++;
//						if(cot == 3) {
//							dong++; 
//							cot = 0;
//						}
						count++;
					} else {
						gridCumGhe.add(spGhe, cot, dong);
						
						cot++;
						if(cot == 3) {
						dong++; 
						cot = 0;
						}
						count++;
					}
				}
			}
		}
		
		if (gridCumGhe.getChildren().size() > 0) {
			gridCumGhe.setHgap(10);
			gridCumGhe.setVgap(10);
		    gridAllGhe.add(gridCumGhe, cotAll, dongAll);
		}
		gridAllGhe.setAlignment(Pos.CENTER);
		gridAllGhe.setMaxWidth(600);
		gridAllGhe.setPrefHeight(300);
		gridAllGhe.setPadding(new Insets(20, 0, 20, 0));
//		gridAllGhe.setId("grid_AllGhe");
		return gridAllGhe;
	}
	
	public VBox setUpGheNam(String maToa, ArrayList<GheNgoi> listGhe, Tau tau, Map<String, String> mapGheTheoToaGiaGoc, Map<String, String>mapGheTheoToaGiaHienTai) throws SQLException {
		
		DecimalFormat df = new DecimalFormat("#,###");
		String duongdanghethuong = "/img/ghegiuongnam.png";
		String duongdangheluudong = "/img/ghegiuongnamluudong.png";
		
		HBox boxKhoang = new HBox(120);
		Label lblKhoang1 = new Label("Khoang 1");
		lblKhoang1.setId("txt_Money");
		Label lblKhoang2 = new Label("Khoang 2");
		lblKhoang2.setId("txt_Money");
		Label lblKhoang3 = new Label("Khoang 3");
		lblKhoang3.setId("txt_Money");
		boxKhoang.getChildren().addAll(lblKhoang1, lblKhoang2, lblKhoang3);
		
		
		int count = 0, cot = 0, dong = 0, cotAll = 0, dongAll = 0;
		GridPane gridCumGhe = new GridPane();
		
		GridPane gridAllGhe = new GridPane();
		
		Popup popup = new Popup();
		Label popupLabel = new Label();
        popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
        popup.getContent().add(popupLabel);
		
		for(GheNgoi gn : listGhe) {
			if(gn.getToaTau().getMaToaTau().equalsIgnoreCase(maToa)) {
				if(count < 12) {
					Label lblGhe = new Label(Integer.toString(gn.getSoGhe()));
					lblGhe.setId("lbl_Ghe");
					
					ImageView imgLuuDong = animation.taoImgGhe(duongdanghethuong);
					imgLuuDong.setScaleX(1.2); 
					imgLuuDong.setScaleY(1.2);
					
//					StackPane spGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblGhe);
					StackPane spGhe = new StackPane(imgLuuDong, lblGhe);
					spGhe.setUserData(gn.getMaGheNgoi());
					
					double giaTienGhe = tau.getLoaiTau().getGiaCuoc() * 1726;
					giaTienGhe += gn.getGiaTriTangThem() + gn.getTang().getGiaTang();
					String giaTienGheString = df.format(giaTienGhe);
					
					spGhe.setOnMouseClicked(event -> {
						
						
						ImageView imgGhe = (ImageView) spGhe.getChildren().get(0);
						
						String duongdanghethuongchuachon = getClass().getResource("/img/ghegiuongnam.png").toExternalForm();
						String duongdanghechon = getClass().getResource("/img/giuongDangChon.png").toExternalForm();
						
						if(listGheChon.contains(gn.getMaGheNgoi())) {
							imgGhe.setImage(new Image(duongdanghethuongchuachon));
							listGheChon.remove(gn.getMaGheNgoi());
						} else {
							imgGhe.setImage(new Image(duongdanghechon));
							listGheChon.add(gn.getMaGheNgoi());
						}
						
						coto = 0; dongo = 0;
						gridSPAllGheGiaGoc.setPrefWidth(300);
						gridSPAllGheGiaGoc.setPrefHeight(490);
						gridSPAllGheGiaGoc.getChildren().clear();
						gridSPAllGheGiaGoc.setHgap(20);
						gridSPAllGheGiaGoc.setPadding(new Insets(5));
						
						gridSPAllGheHienTai.setPrefWidth(300);
						gridSPAllGheHienTai.setPrefHeight(490);
						gridSPAllGheHienTai.getChildren().clear();
						gridSPAllGheHienTai.setHgap(20);
						gridSPAllGheHienTai.setPadding(new Insets(5));
						
						
						
						for(int i = 0; i < listGheChon.size(); i++) {
							String soGhe = mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[0].trim();;
							Label lblSPAllGheGiaGoc = new Label(soGhe);
							lblSPAllGheGiaGoc.setId("lbl_Ghe");
							
							StackPane spGiaGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaGoc);
							Label txtGiaGoc = new Label(mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaGoc.setId("txt_Money");
							Label txtGiaHienTai = new Label(mapGheTheoToaGiaHienTai.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaHienTai.setId("txt_Money");
							
							VBox boxSPGiaGheGoc = new VBox();
							boxSPGiaGheGoc.getChildren().addAll(spGiaGhe, txtGiaGoc);
							boxSPGiaGheGoc.setAlignment(Pos.CENTER);
							
							Label lblSPAllGheGiaHienTai = new Label(soGhe);
							lblSPAllGheGiaHienTai.setId("lbl_Ghe");
							
							StackPane spGiaGheHienTai = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaHienTai);
							VBox boxSPGiaGheHienTai = new VBox();
							boxSPGiaGheHienTai.getChildren().addAll(spGiaGheHienTai, txtGiaHienTai);
							boxSPGiaGheHienTai.setAlignment(Pos.CENTER);
							
							if(i %4 == 0 && i != 0) {
								dongo++;
								coto = 0;
							}
							gridSPAllGheGiaGoc.add(boxSPGiaGheGoc, coto, dongo);
							gridSPAllGheGiaHienTai.add(boxSPGiaGheHienTai, coto, dongo);
							coto++;
						}
					});
					
					spGhe.setOnMouseEntered(event -> {
						String content = String.format(
			                        "Loại ghế: %s\nGiá ghế: %s\nGiá trị tăng thêm %s",
			                        gn.getLoaiGhe().getTenLoaiGhe(),
			                        giaTienGheString,
			                        df.format(gn.getGiaTriTangThem())
			                );
			                popupLabel.setText(content);
			                popup.show(spGhe, event.getScreenX() + 10, event.getScreenY() + 10);
					});
					
					spGhe.setOnMouseMoved(e -> {
			            popup.setX(e.getScreenX() + 10);
			            popup.setY(e.getScreenY() + 10);
			        });


					spGhe.setOnMouseExited(e -> {
			            popup.hide();
			        });
					
					if(count %6 == 0) {
						if(count %6 == 0 && count != 0) {
							gridCumGhe.setHgap(10);
							gridCumGhe.setVgap(10);
							Line line = new Line(0, 0, 0, 130);
							line.setStroke(Color.BLACK);
							line.setStrokeWidth(2);
							gridAllGhe.add(gridCumGhe, cotAll, dongAll);
							gridAllGhe.add(line, cotAll + 1, dongAll);
							cotAll+=2;
							cot = 0;
							dong = 0;
							gridCumGhe = new GridPane();
						}
						
						gridCumGhe.add(spGhe, cot, dong);
						cot++;
//						if(cot == 2) {
//							dong++; 
//							cot = 0;
//						}						
						count++;
						
					} else {
						gridCumGhe.add(spGhe, cot, dong);
						
						cot++;
						if(cot == 2) {
							dong++; 
							cot = 0;
						}
						count++;
					}
				} else {
					Label lblGhe = new Label(Integer.toString(gn.getSoGhe()));
					lblGhe.setId("lbl_Ghe");
					
					ImageView imgLuuDong = animation.taoImgGhe(duongdangheluudong);
					imgLuuDong.setScaleX(1.2); 
					imgLuuDong.setScaleY(1.2);
					
//					StackPane spGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblGhe);
					StackPane spGhe = new StackPane(imgLuuDong, lblGhe);
					spGhe.setUserData(gn.getMaGheNgoi());
					
					spGhe.setOnMouseClicked(event -> {
						
						
						ImageView imgGhe = (ImageView) spGhe.getChildren().get(0);
						
						String duongdangheluudongchuachon = getClass().getResource("/img/ghegiuongnamluudong.png").toExternalForm();
						String duongdanghechon = getClass().getResource("/img/giuongDangChon.png").toExternalForm();
						
						if(listGheChon.contains(gn.getMaGheNgoi())) {
							imgGhe.setImage(new Image(duongdangheluudongchuachon));
							listGheChon.remove(gn.getMaGheNgoi());
						} else {
							imgGhe.setImage(new Image(duongdanghechon));
							listGheChon.add(gn.getMaGheNgoi());
						}
						
						coto = 0; dongo = 0;
						gridSPAllGheGiaGoc.setPrefWidth(300);
						gridSPAllGheGiaGoc.setPrefHeight(490);
						gridSPAllGheGiaGoc.getChildren().clear();
						gridSPAllGheGiaGoc.setHgap(20);
						gridSPAllGheGiaGoc.setPadding(new Insets(5));
						gridSPAllGheHienTai.setPrefWidth(300);
						gridSPAllGheHienTai.setPrefHeight(490);
						gridSPAllGheHienTai.getChildren().clear();
						gridSPAllGheHienTai.setHgap(20);
						gridSPAllGheHienTai.setPadding(new Insets(5));
						
						
						
						for(int i = 0; i < listGheChon.size(); i++) {
							String soGhe = mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[0].trim();;
							Label lblSPAllGheGiaGoc = new Label(soGhe);
							lblSPAllGheGiaGoc.setId("lbl_Ghe");
							
							StackPane spGiaGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaGoc);
							Label txtGiaGoc = new Label(mapGheTheoToaGiaGoc.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaGoc.setId("txt_Money");
							Label txtGiaHienTai = new Label(mapGheTheoToaGiaHienTai.get(listGheChon.get(i)).split(":")[1].trim());
							txtGiaHienTai.setId("txt_Money");
							
							VBox boxSPGiaGheGoc = new VBox();
							boxSPGiaGheGoc.getChildren().addAll(spGiaGhe, txtGiaGoc);
							boxSPGiaGheGoc.setAlignment(Pos.CENTER);
							
							Label lblSPAllGheGiaHienTai = new Label(soGhe);
							lblSPAllGheGiaHienTai.setId("lbl_Ghe");
							
							StackPane spGiaGheHienTai = new StackPane(animation.taoImgGhe(duongdanghethuong), lblSPAllGheGiaHienTai);
							VBox boxSPGiaGheHienTai = new VBox();
							boxSPGiaGheHienTai.getChildren().addAll(spGiaGheHienTai, txtGiaHienTai);
							boxSPGiaGheHienTai.setAlignment(Pos.CENTER);
							
							if(i %4 == 0 && i != 0) {
								dongo++;
								coto = 0;
							}
							gridSPAllGheGiaGoc.add(boxSPGiaGheGoc, coto, dongo);
							gridSPAllGheGiaHienTai.add(boxSPGiaGheHienTai, coto, dongo);
							coto++;
						}
						
					});
					
					spGhe.setOnMouseEntered(event -> {
						String content = String.format(
			                        "Loại ghế: %s\nGiá cước ghế: %s\nGiá trị tăng thêm %s",
			                        gn.getLoaiGhe().getTenLoaiGhe(),
			                        df.format(tau.getLoaiTau().getGiaCuoc()),
			                        df.format(gn.getGiaTriTangThem())
			                );
			                popupLabel.setText(content);
			                popup.show(spGhe, event.getScreenX() + 10, event.getScreenY() + 10);
					});
					
					spGhe.setOnMouseMoved(e -> {
			            popup.setX(e.getScreenX() + 10);
			            popup.setY(e.getScreenY() + 10);
			        });


					spGhe.setOnMouseExited(e -> {
			            popup.hide();
			        });
					
					if(count %6 == 0) {
						if(count %6 == 0 && count != 0) {
							gridCumGhe.setHgap(10);
							gridCumGhe.setVgap(10);
							Line line = new Line(0, 0, 0, 130);
							line.setStroke(Color.BLACK);
							line.setStrokeWidth(2);
							gridAllGhe.add(gridCumGhe, cotAll, dongAll);
							gridAllGhe.add(line, cotAll + 1, dongAll);
							cotAll+=2;
							cot = 0;
							dong = 0;
							gridCumGhe = new GridPane();
						}
						
						gridCumGhe.add(spGhe, cot, dong);
						cot++;
//						if(cot == 2) {
//							dong++; 
//							cot = 0;
//						}				
						count++;
					} else {
						gridCumGhe.add(spGhe, cot, dong);
						
						cot++;
						if(cot == 2) {
							dong++; 
							cot = 0;
						}
						count++;
					}
				}
			}
		}
		if (gridCumGhe.getChildren().size() > 0) {
			gridCumGhe.setHgap(10);
			gridCumGhe.setVgap(10);
		    gridAllGhe.add(gridCumGhe, cotAll, dongAll);
		}
		gridAllGhe.setHgap(50);
//		gridAllGhe.setVgap(50);
		gridAllGhe.setAlignment(Pos.CENTER);
		gridAllGhe.setMaxWidth(600);
		gridAllGhe.setPrefHeight(150);
//		gridAllGhe.setId("grid_AllGhe");
		
		VBox boxKhoangAndGhe = new VBox(20);
		boxKhoangAndGhe.setAlignment(Pos.CENTER);
		boxKhoangAndGhe.setMaxWidth(600);
		boxKhoangAndGhe.setPrefHeight(300);
		boxKhoang.setAlignment(Pos.CENTER);
		boxKhoangAndGhe.getChildren().addAll(boxKhoang, gridAllGhe);
		return boxKhoangAndGhe;
	}
	

	
	
	public VBox create_themThietLap_layout() throws SQLException, IOException {
		
		DecimalFormat df = new DecimalFormat("#,###");
		String duongdantoa = "/img/thantau.png";
		String duongdandautao = "/img/dautau.png";
		String duongdantoafull = "/img/toaFullCho.png";
		String duongdantoadangchon = "/img/thantauchon.png";
		int flagToaDau = 0;
		fileTmp = new File("src/main/resources/tmp_CapNhatChuyenTau.txt");
		ghedao = new GheNgoiDAO();
		chuyentaudao = new ChuyenTauDAO();
		
	
		
		spAllGhe = new StackPane();
		spAllGhe.setPrefWidth(600);
		spAllGhe.setMaxWidth(600);
		spAllGhe.setPrefHeight(300);
		spAllGhe.setId("grid_AllGhe");
		
		
		gridAllGhe = new GridPane();
		
		List<String> allLine = Files.readAllLines(Paths.get("src/main/resources/tmp_CapNhatChuyenTau.txt"));
		
		String line0 = allLine.get(0);
		JsonObject obj1 = JsonParser.parseString(line0).getAsJsonObject();
		String maChuyenTau = obj1.get("maChuyenTau").getAsString();
		ChuyenTau chuyenTau = chuyentaudao.getChuyenTauBangMa(maChuyenTau);
		
		ArrayList<ToaTau> listToaTauTheoChuyenTau = toataudao.getListToaTauByMaCT(maChuyenTau);
		
		String line1 = allLine.get(2); 
		JsonObject objMaTau = JsonParser.parseString(line1).getAsJsonObject();
		String maTau = objMaTau.get("maTau").getAsString();
		Tau tau = taudao.getTauTheoMa(maTau);
		
		String line2 = allLine.get(3);
		JsonArray arrListToa = JsonParser.parseString(line2).getAsJsonArray();
		ArrayList<String> listMaToa = new ArrayList<>();
		ArrayList<ToaTau> listToaTau = new ArrayList<>();
		
		for(JsonElement je : arrListToa) {
			JsonObject objToaTau = je.getAsJsonObject();
			String maToaTau = objToaTau.get("maToaTau").getAsString();
			listMaToa.add(maToaTau);
			listToaTau.add(toataudao.getToaTauTheoMa(maToaTau));
		}
		
		
		String stringMaToaSQL = listMaToa.stream().map(ma -> "'" + ma + "'").collect(Collectors.joining(", "));
		mapGheTheoToa.clear();
		mapGheTheoToa = ghedao.getMapGheTheoToa(stringMaToaSQL);
		ArrayList<String> listToaTauTheoChuyenTauDaBan = new ArrayList<>();
//		for(Map.Entry<String, ArrayList<GheNgoi>> map : mapGheTheoToa.entrySet()) {
//			System.out.println(map.getKey());
//			if(map.getKey().startsWith("GN")) {
//				for(int j = 0; j <= listToaTauTheoChuyenTau.size()-1; j++) {
//					if(listToaTauTheoChuyenTau.get(j).getMaToaTau().equalsIgnoreCase(map.getKey())) {
//						for(int i = 1; i <= 36; i++) {
//							GheTrenChuyenTau gtct = ghedao.getGheTrenChuyenTau(i, map.getKey(), chuyenTau.getMaChuyenTau());
//							if(gtct.getTrangThaiGhe().equalsIgnoreCase("Đã bán")) {
//								System.out.println("nnn");
//								listToaTauTheoChuyenTauDaBan.add(map.getKey());
////								for(int j = 0; j <= listToaTauTheoChuyen.size()-1; j++) {
////									if(listToaTauTheoChuyenTauDaBan.get(j).equalsIgnoreCase(map.getKey())) {
////									
////									System.out.println("----------");
////									break;
////									}
////								}
//								break;
//							}
//							break;
//					}
//				}
//				}
////				for(int i = 1; i <= 36; i++) {
////					GheTrenChuyenTau gtct = ghedao.getGheTrenChuyenTau(i, map.getKey(), chuyenTau.getMaChuyenTau());
////					if(gtct.getTrangThaiGhe().equalsIgnoreCase("Đã bán")) {
////						System.out.println("nnn");
////						listToaTauTheoChuyenTauDaBan.add(map.getKey());
//////						for(int j = 0; j <= listToaTauTheoChuyen.size()-1; j++) {
//////							if(listToaTauTheoChuyenTauDaBan.get(j).equalsIgnoreCase(map.getKey())) {
//////							
//////							System.out.println("----------");
//////							break;
//////							}
//////						}
////						break;
////					}
////				}
//			} else{
//				for(int j = 0; j <= listToaTauTheoChuyenTau.size()-1; j++) {
//					if(listToaTauTheoChuyenTau.get(j).getMaToaTau().equalsIgnoreCase(map.getKey())) {
//						for(int i = 1; i <= 18; i++) {
//							GheTrenChuyenTau gtct = ghedao.getGheTrenChuyenTau(i, map.getKey(), chuyenTau.getMaChuyenTau());
//							if(gtct.getTrangThaiGhe().equalsIgnoreCase("Đã bán")) {
//								System.out.println("nnn");
//								listToaTauTheoChuyenTauDaBan.add(map.getKey());
////								for(int j = 0; j <= listToaTauTheoChuyen.size()-1; j++) {
////									if(listToaTauTheoChuyenTauDaBan.get(j).equalsIgnoreCase(map.getKey())) {
////									
////									System.out.println("----------");
////									break;
////									}
////								}
//								break;
//							}
//						}
//						break;
//					}
//					
//				}
//				for(int i = 1; i <= 18; i++) {
//					GheTrenChuyenTau gtct = ghedao.getGheTrenChuyenTau(i, map.getKey(), chuyenTau.getMaChuyenTau());
//					if(gtct.getTrangThaiGhe().equalsIgnoreCase("Đã bán")) {
//						System.out.println("nnn");
//						listToaTauTheoChuyenTauDaBan.add(map.getKey());
////						for(int j = 0; j <= listToaTauTheoChuyen.size()-1; j++) {
////							if(listToaTauTheoChuyenTauDaBan.get(j).equalsIgnoreCase(map.getKey())) {
////							
////							System.out.println("----------");
////							break;
////							}
////						}
//						break;
//					}
//				}
//			}
//		}
		
		
		Map<String, String> mapGheTheoToaGiaGoc = new HashMap<>();
		Map<String, String> mapGheTheoToaGiaHienTai = new HashMap<>();
		for(Map.Entry<String, ArrayList<GheNgoi>> map : mapGheTheoToa.entrySet()) {
			for(GheNgoi ghe : map.getValue()) {
				mapGheTheoToaGiaGoc.put(ghe.getMaGheNgoi(), ghe.getSoGhe() + ": " + df.format(ghe.getGiaTriTangThem()));
				mapGheTheoToaGiaHienTai.put(ghe.getMaGheNgoi(), ghe.getSoGhe() + ": " + df.format(ghe.getGiaTriTangThem()));
			}
		}
		
		Label lblThemCT = new Label("Cập nhật chuyến tàu");
		lblThemCT.setId("lbl_TieuDe");
		HBox boxLblThemCT = new HBox(lblThemCT);
		boxLblThemCT.setMaxWidth(1000);
		boxLblThemCT.setAlignment(Pos.CENTER_LEFT);
		
		Label lblThietLapGiaCuoc = new Label("Thiết lập giá cước");
		lblThietLapGiaCuoc.setId("lbl_TieuDe");
		
		
		Label lblGiaCuoc = new Label("Giá cước");
		lblGiaCuoc.setPrefWidth(100);
		lblGiaCuoc.setPrefHeight(40);
		lblGiaCuoc.setId("lbl_Money");
		lblGiaCuoc.setAlignment(Pos.CENTER);
		
		TextField txtGiaCuoc = new TextField();
		txtGiaCuoc.setPrefWidth(250);
		txtGiaCuoc.setId("txt_Money");
		
		HBox boxGiaCuoc = new HBox();
		boxGiaCuoc.getChildren().addAll(lblGiaCuoc, txtGiaCuoc);
//		boxGiaCuoc.setMaxWidth();
		boxGiaCuoc.setId("box_Money");
		
		Label lblGiaGocCuoc = new Label("Giá gốc: " + df.format(tau.getLoaiTau().getGiaCuoc())+"/km");
		lblGiaGocCuoc.setId("txt_Money");
		
		GridPane gridThietLapGiaCuoc = new GridPane();
		gridThietLapGiaCuoc.add(boxGiaCuoc, 0, 0);
		gridThietLapGiaCuoc.setColumnSpan(boxGiaCuoc, 2);
		gridThietLapGiaCuoc.add(lblGiaGocCuoc, 2, 0);
		gridThietLapGiaCuoc.setHgap(20);
		gridThietLapGiaCuoc.setAlignment(Pos.CENTER);
		
		
		Label lblThietLapGiaGhe = new Label("Thiết lập giá ghế");
		lblThietLapGiaGhe.setId("lbl_TieuDe");
		
		HBox boxToaTau = new HBox(5);
		boxToaTau.setAlignment(Pos.CENTER);
		
		ImageView imgDauTao = animation.taoImgGhe(duongdandautao);
		imgDauTao.setFitWidth(50);
		
		Label lblDauTao = new Label(tau.getLoaiTau().getTenLoaiTau());
		lblDauTao.setId("lbl_Toa");
		StackPane spDauTao = new StackPane(imgDauTao, lblDauTao);
		spDauTao.setMargin(lblDauTao, new Insets(10, 0, 0, 0));
		
		Popup popup = new Popup();
		Label popupLabel = new Label();
        popupLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
        popup.getContent().add(popupLabel);
        
        spDauTao.setOnMouseEntered(event -> {
			String content = String.format(
                        "Mã tàu: %s\nLoại: %s",
                        tau.getMaTau(),
                        tau.getLoaiTau().getTenLoaiTau()
                );
                popupLabel.setText(content);
                popup.show(spDauTao, event.getScreenX() + 10, event.getScreenY() + 10);
		});
		
        spDauTao.setOnMouseMoved(e -> {
            popup.setX(e.getScreenX() + 10);
            popup.setY(e.getScreenY() + 10);
        });


        spDauTao.setOnMouseExited(e -> {
            popup.hide();
        });
		
		
		for(int i = listMaToa.size() - 1; i >= -1; i--) {
			
			if(i == -1) {
				boxToaTau.getChildren().add(spDauTao);
				break;
			}
			
			else {
				
				if(flagToaDau == 0) {
					
					for(int j = 0; j <= listToaTauTheoChuyenTau.size()-1; j++) {
						if(!listToaTauTheoChuyenTau.get(j).getMaToaTau().equalsIgnoreCase(listMaToa.get(i))) {
							if(listMaToa.get(i).startsWith("GN")) {
								gridAllGhe = setUpGheNgoi(listMaToa.get(i), mapGheTheoToa.get(listMaToa.get(i)), tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
								spAllGhe.getChildren().add(gridAllGhe);
								flagToaDau++;
								j = 100;
							} else {
								VBox boxKhoangAndGhe = setUpGheNam(listMaToa.get(i), mapGheTheoToa.get(listMaToa.get(i)), tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
								spAllGhe.getChildren().add(boxKhoangAndGhe);
								flagToaDau++;	
								j = 100;
							}	
						}
					}
					
//					if(listMaToa.get(0).startsWith("GN")) {
//						gridAllGhe = setUpGheNgoi(listMaToa.get(0), mapGheTheoToa.get(listMaToa.get(0)), tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
//						spAllGhe.getChildren().add(gridAllGhe);
//						flagToaDau++;
//					}
//					else {
//						VBox boxKhoangAndGhe = setUpGheNam(listMaToa.get(0), mapGheTheoToa.get(listMaToa.get(0)), tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
//						spAllGhe.getChildren().add(boxKhoangAndGhe);
//						flagToaDau++;	
//					}		
				}
				
				for(int o = 0; o <= listToaTauTheoChuyenTau.size()-1; o++) {
					if(listMaToa.get(i).equalsIgnoreCase(listToaTauTheoChuyenTau.get(o).getMaToaTau())) {
						ImageView imgToa = animation.taoImgGhe(duongdantoafull);
						imgToa.setFitWidth(50);
						Label lblToa = new Label(listMaToa.get(i).toString());
						lblToa.setId("lbl_Toa");
						
						Popup popupToa = new Popup();
						Label popupLabelToa = new Label();
		                popupLabelToa.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
		                popupToa.getContent().add(popupLabelToa);
		                
		                ToaTau toaTau = listToaTau.get(i);
						
						StackPane spToa = new StackPane(imgToa, lblToa);
						spToa.setUserData(listMaToa.get(i));
						spToa.setMargin(lblToa, new Insets(10, 0, 0, 0));
					
						spToa.setOnMouseEntered(event -> {
							String content = String.format(
		                                "Mã toa: %s\nLoại: %s",
		                                toaTau.getMaToaTau(),
		                                toaTau.getLoaiToaTau().getTenLoaiToaTau()
		                        );
		                        popupLabel.setText(content);
		                        popup.show(spToa, event.getScreenX() + 10, event.getScreenY() + 10);
						});
						
						spToa.setOnMouseMoved(e -> {
		                    popup.setX(e.getScreenX() + 10);
		                    popup.setY(e.getScreenY() + 10);
		                });


		                spToa.setOnMouseExited(e -> {
		                    popup.hide();
		                });
		                spToa.setMouseTransparent(true);
		                checkVarToa = true;
		                boxToaTau.getChildren().add(spToa);
					} 
				}
				if(!checkVarToa) {
					ImageView imgToa = animation.taoImgGhe(duongdantoa);
//					imgToa.setOpacity(0.1);
					imgToa.setFitWidth(50);
					
					Label lblToa = new Label(listMaToa.get(i).toString());
					lblToa.setId("lbl_Toa");
					
					Popup popupToa = new Popup();
					Label popupLabelToa = new Label();
	                popupLabelToa.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
	                popupToa.getContent().add(popupLabelToa);
	                
	                ToaTau toaTau = listToaTau.get(i);
					
					StackPane spToa = new StackPane(imgToa, lblToa);
					spToa.setUserData(listMaToa.get(i));
					spToa.setMargin(lblToa, new Insets(10, 0, 0, 0));
					
					if(!checkVar) {
						ImageView imgToaChon = (ImageView) spToa.getChildren().get(0);
						imgToaChon.setImage(new Image(getClass().getResource(duongdantoadangchon).toExternalForm()));
						spToaDangChon = spToa;
						checkVar = true;
					}
					
					
					
					spToa.setOnMouseEntered(event -> {
						String content = String.format(
	                                "Mã toa: %s\nLoại: %s",
	                                toaTau.getMaToaTau(),
	                                toaTau.getLoaiToaTau().getTenLoaiToaTau()
	                        );
	                        popupLabel.setText(content);
	                        popup.show(spToa, event.getScreenX() + 10, event.getScreenY() + 10);
					});
					
					spToa.setOnMouseMoved(e -> {
	                    popup.setX(e.getScreenX() + 10);
	                    popup.setY(e.getScreenY() + 10);
	                });


	                spToa.setOnMouseExited(e -> {
	                    popup.hide();
	                });
					
					spToa.setOnMouseClicked(event -> {
						listGheChon.clear();
						gridSPAllGheGiaGoc.getChildren().clear();
						gridSPAllGheGiaHienTai.getChildren().clear();
						
						
						for(int e = 0; e < boxToaTau.getChildren().size()-1; e++) {
							StackPane spToaKhac = (StackPane) boxToaTau.getChildren().get(e);
							ImageView imgToaThuong = (ImageView) spToaKhac.getChildren().get(0);
							imgToaThuong.setImage(new Image(getClass().getResource(duongdantoa).toExternalForm()));
						}
					
						
						spToaDangChon = spToa;
						Boolean scrollRight = null;
						String maToa = spToa.getUserData().toString();
						ImageView imgToaChon = (ImageView) spToa.getChildren().get(0);
						imgToaChon.setImage(new Image(getClass().getResource(duongdantoadangchon).toExternalForm()));
						ArrayList<GheNgoi> listGhe = mapGheTheoToa.get(maToa);
						mapGheTheoToaGiaHienTai.clear();
						for(GheNgoi gn : listGhe) {
							mapGheTheoToaGiaHienTai.put(gn.getMaGheNgoi(), gn.getSoGhe() + ": " + df.format(gn.getGiaTriTangThem()));
						}
						
						for(int  j = 0; j < listMaToa.size(); j++) {
							if(maToa.equalsIgnoreCase(listMaToa.get(j)) ) {
								if(j == flagScroll) return;
								else if(j > flagScroll) {
									scrollRight = true;
									flagScroll = j;
									break;
								} else {
									scrollRight = false;
									flagScroll = j;
									break;
								}
							}
						}
						Parent toaMoi = null;
						if(maToa.startsWith("GN")) {
							gridAllGhe = new GridPane();
							try {
								gridAllGhe = setUpGheNgoi(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							toaMoi = gridAllGhe;
						} else if(maToa.startsWith("GIN")) {
							VBox boxKhoangAndGhe = null;
							try {
								boxKhoangAndGhe = setUpGheNam(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							toaMoi = boxKhoangAndGhe;
						}
						
						Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
						
						animationSlideToa(toaCu, toaMoi, scrollRight);
					
					});
					
					boxToaTau.getChildren().add(spToa);
				}
				checkVarToa = false;
//				ImageView imgToa = animation.taoImgGhe(duongdantoa);
////				imgToa.setOpacity(0.1);
//				imgToa.setFitWidth(50);
//				
//				Label lblToa = new Label(listMaToa.get(i).toString());
//				lblToa.setId("lbl_Toa");
//				
//				Popup popupToa = new Popup();
//				Label popupLabelToa = new Label();
//                popupLabelToa.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-font-size: 13;");
//                popupToa.getContent().add(popupLabelToa);
//                
//                ToaTau toaTau = listToaTau.get(i);
//				
//				StackPane spToa = new StackPane(imgToa, lblToa);
//				spToa.setUserData(listMaToa.get(i));
//				spToa.setMargin(lblToa, new Insets(10, 0, 0, 0));
//				
//				if(i == 0) {
//					ImageView imgToaChon = (ImageView) spToa.getChildren().get(0);
//					imgToaChon.setImage(new Image(getClass().getResource(duongdantoadangchon).toExternalForm()));
//					spToaDangChon = spToa;
//				}
//				
//				
//				
//				spToa.setOnMouseEntered(event -> {
//					String content = String.format(
//                                "Mã toa: %s\nLoại: %s",
//                                toaTau.getMaToaTau(),
//                                toaTau.getLoaiToaTau().getTenLoaiToaTau()
//                        );
//                        popupLabel.setText(content);
//                        popup.show(spToa, event.getScreenX() + 10, event.getScreenY() + 10);
//				});
//				
//				spToa.setOnMouseMoved(e -> {
//                    popup.setX(e.getScreenX() + 10);
//                    popup.setY(e.getScreenY() + 10);
//                });
//
//
//                spToa.setOnMouseExited(e -> {
//                    popup.hide();
//                });
//				
//				spToa.setOnMouseClicked(event -> {
//					listGheChon.clear();
//					gridSPAllGheGiaGoc.getChildren().clear();
//					gridSPAllGheGiaHienTai.getChildren().clear();
//					
//					
//					for(int e = 0; e < boxToaTau.getChildren().size()-1; e++) {
//						StackPane spToaKhac = (StackPane) boxToaTau.getChildren().get(e);
//						ImageView imgToaThuong = (ImageView) spToaKhac.getChildren().get(0);
//						imgToaThuong.setImage(new Image(getClass().getResource(duongdantoa).toExternalForm()));
//					}
//				
//					
//					spToaDangChon = spToa;
//					Boolean scrollRight = null;
//					String maToa = spToa.getUserData().toString();
//					ImageView imgToaChon = (ImageView) spToa.getChildren().get(0);
//					imgToaChon.setImage(new Image(getClass().getResource(duongdantoadangchon).toExternalForm()));
//					ArrayList<GheNgoi> listGhe = mapGheTheoToa.get(maToa);
//					mapGheTheoToaGiaHienTai.clear();
//					for(GheNgoi gn : listGhe) {
//						mapGheTheoToaGiaHienTai.put(gn.getMaGheNgoi(), gn.getSoGhe() + ": " + df.format(gn.getGiaTriTangThem()));
//					}
//					
//					for(int  j = 0; j < listMaToa.size(); j++) {
//						if(maToa.equalsIgnoreCase(listMaToa.get(j)) ) {
//							if(j == flagScroll) return;
//							else if(j > flagScroll) {
//								scrollRight = true;
//								flagScroll = j;
//								break;
//							} else {
//								scrollRight = false;
//								flagScroll = j;
//								break;
//							}
//						}
//					}
//					Parent toaMoi = null;
//					if(maToa.startsWith("GN")) {
//						gridAllGhe = new GridPane();
//						try {
//							gridAllGhe = setUpGheNgoi(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						toaMoi = gridAllGhe;
//					} else if(maToa.startsWith("GIN")) {
//						VBox boxKhoangAndGhe = null;
//						try {
//							boxKhoangAndGhe = setUpGheNam(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//						toaMoi = boxKhoangAndGhe;
//					}
//					
//					Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
//					
//					animationSlideToa(toaCu, toaMoi, scrollRight);
//				
//				});
//				
//				boxToaTau.getChildren().add(spToa);
			}
			
		}
		
		Label lblGiaGhe = new Label("Giá ghế");
//		lblGiaGhe.setPrefWidth(70);
		lblGiaGhe.setPrefWidth(100);
		lblGiaGhe.setPrefHeight(40);
		lblGiaGhe.setId("lbl_Money");
		lblGiaGhe.setAlignment(Pos.CENTER);
		
		TextField txtGiaGhe = new TextField();
		txtGiaGhe.setPrefWidth(250);
		txtGiaGhe.setId("txt_Money");
		
		HBox boxGiaGhe = new HBox();
		boxGiaGhe.getChildren().addAll(lblGiaGhe, txtGiaGhe);
		boxGiaGhe.setId("box_Money");
		
//		lblGiaGocGhe = new Label("Giá gốc: ");
//		lblGiaGocGhe.setId("txt_Money");
		Label lblGiaCuocHienTai = new Label("Giá cước hiện tại: " + df.format(tau.getLoaiTau().getGiaCuoc())+"/km");
		lblGiaCuocHienTai.setId("txt_Money");
		
		GridPane gridGiaGhe = new GridPane();
		gridGiaGhe.add(boxGiaGhe, 0, 0);
		gridGiaGhe.setColumnSpan(boxGiaGhe, 2);
		gridGiaGhe.add(lblGiaCuocHienTai, 2, 0);
		gridGiaGhe.setHgap(20);
		gridGiaGhe.setAlignment(Pos.CENTER);
		
		
//		lblGiaVeHienTai = new Label("Giá vé tăng thêm: ");
//		lblGiaVeHienTai.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
//		HBox boxGiaHienTai = new HBox(50);
//		boxGiaHienTai.getChildren().addAll(lblGiaCuocHienTai);
//		boxGiaHienTai.setAlignment(Pos.CENTER);
		
		Button buttonCapNhat = new Button();
		buttonCapNhat.setText("Cập nhật");
		buttonCapNhat.setPrefWidth(200);
		buttonCapNhat.setPrefHeight(60);
		buttonCapNhat.setId("button_Blue");
		
		buttonCapNhat.setOnMouseClicked(event -> {

			if(txtGiaCuoc.getText().isEmpty() && txtGiaGhe.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Lỗi");
				alert.setHeaderText(null);
				alert.setContentText("Vui lòng nhập giá trị muốn thay đổi");
				 Stage stage = (Stage) txtGiaCuoc.getScene().getWindow();
				alert.initOwner(stage);
				alert.initModality(Modality.WINDOW_MODAL);
				alert.showAndWait();
				System.out.println("loi r nhap chu vao di");
			}
			else {
				if(!txtGiaCuoc.getText().isEmpty()) {					
					double giaCuocNew = Double.parseDouble(txtGiaCuoc.getText());

						LoaiTau giaCuocLoaiTauNew = new LoaiTau(tau.getLoaiTau().getMaLoaiTau(), tau.getLoaiTau().getTenLoaiTau(), giaCuocNew);
						tau.setLoaiTau(giaCuocLoaiTauNew);
						
						lblGiaCuocHienTai.setText("Giá cước hiện tại: " + df.format(giaCuocNew) + "/km");
						
						String maToa = spToaDangChon.getUserData().toString();
						
						ArrayList<GheNgoi> listGhe = mapGheTheoToa.get(maToa);
						
					
						
						if(maToa.startsWith("GN")) {
							Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
							Parent toaUpdate = null;
							try {
								toaUpdate = setUpGheNgoi(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							animationUpdateToa(toaCu, toaUpdate);
						} else {
							Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
							Parent toaUpdate = null;
							try {
								toaUpdate = setUpGheNam(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							animationUpdateToa(toaCu, toaUpdate);
						}
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Thành công");
						alert.setHeaderText(null);
						alert.setContentText("Đã thành công cập nhật giá cước");
						 Stage stage = (Stage) txtGiaCuoc.getScene().getWindow();
						alert.initOwner(stage);
						alert.initModality(Modality.WINDOW_MODAL);
						alert.showAndWait();
					}
				if(listGheChon.size() == 0 && !txtGiaGhe.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Vui lòng chọn ghế cần cập nhật giá trị tăng thêm");
					 Stage stage = (Stage) txtGiaGhe.getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					System.out.println("vui long chon ghe can cap nhat gia");
				}
				else if(listGheChon.size() != 0 && txtGiaGhe.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Vui lòng nhập giá tiền muốn cập nhật cho ghế");
					 Stage stage = (Stage) txtGiaGhe.getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					System.out.println("vui long nhap gia tien muon cap nhat cho ghe");
				}
				else if(listGheChon.size() != 0 && !txtGiaGhe.getText().isEmpty()){
					double giaTriTangThemNew = Double.parseDouble(txtGiaGhe.getText());
					
					String maToa = spToaDangChon.getUserData().toString();
					
					ArrayList<GheNgoi> listGhe = mapGheTheoToa.get(maToa);
					mapGheTheoToaGiaHienTai.clear();
					
					for(GheNgoi gn : listGhe) {
						if(listGheChon.contains(gn.getMaGheNgoi())) {
							gn.setGiaTriTangThem(giaTriTangThemNew);
							mapUpdateGhe.put(gn.getMaGheNgoi(), gn.getGiaTriTangThem());
//							ghedao.capNhatGiaTriTangThem(gn.getMaGheNgoi(), giaTriTangThemNew);
						}
						mapGheTheoToaGiaHienTai.put(gn.getMaGheNgoi(), gn.getSoGhe() + ": " + df.format(gn.getGiaTriTangThem()));
					}
					
					if(maToa.startsWith("GN")) {
						Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
						Parent toaUpdate = null;
						try {
							toaUpdate = setUpGheNgoi(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						animationUpdateToa(toaCu, toaUpdate);
					} else {
						Parent toaCu = (Parent) spAllGhe.getChildren().get(0);
						Parent toaUpdate = null;
						try {
							toaUpdate = setUpGheNam(maToa, listGhe, tau, mapGheTheoToaGiaGoc, mapGheTheoToaGiaHienTai);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						animationUpdateToa(toaCu, toaUpdate);
					}
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText(null);
					alert.setContentText("Đã cập nhật giá ghế thành công");
					 Stage stage = (Stage) txtGiaGhe.getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}
				listGheChon.clear();
				gridSPAllGheGiaGoc.getChildren().clear();
				gridSPAllGheGiaHienTai.getChildren().clear();
			}
		});
		
		buttonTroLai = new Button("Trở lại");
		buttonTroLai.setPrefWidth(200);
		buttonTroLai.setPrefHeight(60);
		buttonTroLai.setId("button_Red");
		Button buttonThemChuyenTau = new Button();
		buttonThemChuyenTau.setText("Cập nhật chuyến tàu");
		buttonThemChuyenTau.setPrefWidth(200);
		buttonThemChuyenTau.setPrefHeight(60);
		buttonThemChuyenTau.setId("button_Blue");
		
		buttonThemChuyenTau.setOnMouseClicked(event -> {
			int maGTCT = ghetrenchuyentaudao.getMaGheTrenChuyenTauCuoiCung();
			maGTCT++;
			
			
			for(String line : allLine) {
				JsonElement jsonElement = JsonParser.parseString(line);
				
				if(jsonElement.isJsonObject()) {
					JsonObject obj = jsonElement.getAsJsonObject();
					
					if(obj.has("ngayKhoiHanh")) {
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
						double giaCuocTrenChuyenTau = tau.getLoaiTau().getGiaCuoc();
						String ngayKhoiHanh = obj.get("ngayKhoiHanh").getAsString();
						String gioKhoiHanh = obj.get("gioKhoiHanh").getAsString();
						String fullTime = ngayKhoiHanh + " " + gioKhoiHanh;
						LocalDateTime dateTimeDi = LocalDateTime.parse(fullTime, formatter);
						
						String ngayDenDuKien = obj.get("ngayDenDuKien").getAsString();
						String gioDiKeHoach = obj.get("gioDenDuKien").getAsString();
						String fullTimeDen = ngayDenDuKien + " " + gioDiKeHoach;
						LocalDateTime dateTimeDen = LocalDateTime.parse(fullTimeDen, formatter);
						
						String maHanhTrinh = obj.get("maHanhTrinh").getAsString();
						HanhTrinh hanhTrinh = null;
						try {
							hanhTrinh = hanhtrinhdao.getById(maHanhTrinh);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				
						ChuyenTau chuyenTau1 = new ChuyenTau(chuyenTau.getMaChuyenTau(), tau, hanhTrinh, dateTimeDi, dateTimeDen, giaCuocTrenChuyenTau);
						chuyenTauCuaGheTrenChuyenTau = chuyenTau1;
						chuyentaudao.updateChuyenTauDB(chuyenTau1);
					}
				} 
				
			}
			
			if(ghedao.capNhatGiaTriTangThemChoMap(mapUpdateGhe)) { 
				ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau = new ArrayList<>();
				try {
					ChuyenTau chuyenTau1 = chuyentaudao.getChuyenTauBangMa(chuyenTau.getMaChuyenTau());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
//				ArrayList<GheTrenChuyenTau> listGheTau = ghetrenchuyentaudao.getListGheTrenChuyenTauByMaCT(chuyenTau.getMaChuyenTau());
				
				
				
				for(int i = 0; i <= listMaToa.size()-1; i++) {
					for(int j = 0; j <= listToaTauTheoChuyenTau.size()-1; j++) {
						if(listMaToa.get(i).equalsIgnoreCase(listToaTauTheoChuyenTau.get(j).getMaToaTau())) {
							listMaToa.remove(i);
						}
					}
				}
				
				for(String maToa : listMaToa ) {
					ArrayList<GheNgoi> listGheNgoiInsert = mapGheTheoToa.get(maToa);
					
					double giaTienGhe = tau.getLoaiTau().getGiaCuoc() * 1726;
					for(GheNgoi gn : listGheNgoiInsert) {
						if(!gn.isLuuDong()) {
							String maGTCTInsert = "GTCT" + String.format("%02d", maGTCT);
							giaTienGhe += gn.getGiaTriTangThem() + gn.getTang().getGiaTang();
							GheTrenChuyenTau gtct = new GheTrenChuyenTau(maGTCTInsert, "Còn trống", giaTienGhe, chuyenTauCuaGheTrenChuyenTau, gn);
							listGheTrenChuyenTau.add(gtct);
							maGTCT++;
						}
						else {
							String maGTCTInsert = "GTCT" + String.format("%02d", maGTCT);
							giaTienGhe = gn.getGiaTriTangThem() + gn.getTang().getGiaTang();
							GheTrenChuyenTau gtct = new GheTrenChuyenTau(maGTCTInsert, "Còn trống", giaTienGhe, chuyenTauCuaGheTrenChuyenTau, gn);
							listGheTrenChuyenTau.add(gtct);
							maGTCT++;
						}
						
					}
				}
			for(GheTrenChuyenTau gtct : listGheTrenChuyenTau) {
				System.out.println(gtct.getMaGheTrenChuyenTau());
			}
				if(ghetrenchuyentaudao.addGheTrenChuyenTauVaoDB(listGheTrenChuyenTau)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText(null);
					alert.setContentText("Đã thành công thêm chuyến tàu");
					 Stage stage = (Stage) txtGiaCuoc.getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
					System.out.println("thanh cong r do");
					
					PrintWriter writer;
					try {
						writer = new PrintWriter("src/main/resources/tmp_CapNhatChuyenTau.txt");
						writer.print("");
						writer.close();
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
					}
					

				}	
			} else {
				System.out.println("loi o giatien");
			}
			
		});
		
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		HBox boxButton = new HBox(buttonTroLai, spacer1, buttonCapNhat, spacer2, buttonThemChuyenTau);
		boxButton.setMaxWidth(1100);
		
		HBox boxAllGhe = new HBox(50);
		boxAllGhe.setMaxWidth(1200);
		
	
		StackPane spAllGheGiaGoc = new StackPane();
		spAllGheGiaGoc.setPrefWidth(300);
		spAllGheGiaGoc.setMaxWidth(300);
		spAllGheGiaGoc.setPrefHeight(500);
		spAllGheGiaGoc.setId("grid_AllGhe");
		
		VBox boxSPAllGheGiaGoc = new VBox();
		boxSPAllGheGiaGoc.setPrefWidth(300);
		boxSPAllGheGiaGoc.setPrefHeight(500);
		boxSPAllGheGiaGoc.setAlignment(Pos.CENTER);
		
		Label lblSPAllGheGiaGoc = new Label("Giá gốc - vnđ");
		lblSPAllGheGiaGoc.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
		
		gridSPAllGheGiaGoc = new GridPane();
		gridSPAllGheGiaGoc.setPrefWidth(300);
		gridSPAllGheGiaGoc.setPrefHeight(490);
		
		boxSPAllGheGiaGoc.getChildren().addAll(lblSPAllGheGiaGoc, gridSPAllGheGiaGoc);
	
		spAllGheGiaGoc.getChildren().add(boxSPAllGheGiaGoc);
		
		StackPane spAllGheGiaHienTai = new StackPane();
		spAllGheGiaHienTai.setPrefWidth(300);
		spAllGheGiaHienTai.setMaxWidth(300);
		spAllGheGiaHienTai.setPrefHeight(500);
		spAllGheGiaHienTai.setId("grid_AllGhe"); 
		
		VBox boxSPAllGheGiaHienTai = new VBox();
		boxSPAllGheGiaHienTai.setPrefWidth(300);
		boxSPAllGheGiaHienTai.setPrefHeight(500);
		boxSPAllGheGiaHienTai.setAlignment(Pos.CENTER);
		
		Label lblSPAllGheGiaHienTai = new Label("Giá hiện tại - vnđ");
		lblSPAllGheGiaHienTai.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
		gridSPAllGheGiaHienTai = new GridPane();
		gridSPAllGheGiaHienTai.setPrefWidth(300);
		gridSPAllGheGiaHienTai.setPrefHeight(490);
		
		boxSPAllGheGiaHienTai.getChildren().addAll(lblSPAllGheGiaHienTai, gridSPAllGheGiaHienTai);
		
		spAllGheGiaHienTai.getChildren().add(boxSPAllGheGiaHienTai); 
		
		
		
		boxAllGhe.getChildren().addAll(spAllGheGiaGoc,  spAllGhe,  spAllGheGiaHienTai);
		
		layoutThemCT.getChildren().addAll(boxLblThemCT, lblThietLapGiaCuoc, gridThietLapGiaCuoc, lblThietLapGiaGhe, boxToaTau, boxAllGhe, gridGiaGhe,  boxButton);;
		layoutThemCT.setAlignment(Pos.CENTER);
		layoutThemCT.setStyle("-fx-background-color: #FFFFFF");
		return layoutThemCT;
	}
	
	
	public Button getButtonTroLai() {
		return this.buttonTroLai;
	}
	
	
	public static void main(String[] args) {
//		launch(args);
		Application.launch(CapNhatThietLap.class, args);
	}
}