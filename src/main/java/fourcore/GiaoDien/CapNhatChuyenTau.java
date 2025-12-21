package fourcore.GiaoDien;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.Ga;
import fourcore.Entity.HanhTrinh;
import fourcore.Entity.HanhTrinhGa;
import fourcore.animation.Animation;
import fourcore.animation.GhiFile;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.HanhTrinhGa_dao;
import fourcore.dao.HanhTrinh_DAO;
import fourcore.dao.Tau_DAO;
import fourcore.dao.ToaTauDAO;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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
	private HBox banVeBox;
	 private HBox doiVeBox;
	 private HBox hoanVeBox;
	 private HBox capVeBox;
	 private HBox quanLiKhachHangMenu;
	 private ImageView quanLiKhachHangIconView;
	 private ImageView quanLiKhachHangView;
	 private HBox themKhachHangBox;
	 private HBox xoaKhachHangBox;
	 private HBox suaKhachHangBox;
	 private Label quanLiKhachHang;
	 private Label quanLiKhachHangLabel;
	 private HBox quanLiHoaDonMenu;
	 private ImageView quanLiHoaDonIconView;
	 private Label quanLiHoaDonLabel;
	 private HBox timHoaDonBox;
	 private HBox quanLiThongKeMenu;
	 private ImageView quanLiThongKeIconView;
	 private Label quanLiThongKeLabel;
	 private HBox quanLiNhanVienMenu;
	 private ImageView quanLiNhanVienIconView;
	 private HBox quanLiCTKMMenu;
	 private ImageView quanLiCTKMIconView;
	 private HBox quanLiChuyenTauMenu;
	 private ImageView quanLiChuyenTauIconView;
	 private ImageView userIcon;
	private Label userLabel;
	private ImageView settingIcon;
	private ImageView moTaDoanhThuIcon;
	private VBox layoutThemToaTau = new VBox(20);
	private Label lblThemToaTau;
	private Label lblThemChuyenTau;
	private Animation animation = new Animation();
	private int checkToaGheNgoi = 0;
	private int checkToaGiuongNam = 8;
	
	private ToaTauDAO toataudao = new ToaTauDAO();
	private Label lblToa;
	private ImageView imgToa;
	private Map<Integer, StackPane> mapSpToa = new HashMap<>();
	private Tau_DAO taudao = new Tau_DAO();
	private GridPane gridThemChuyenTau;
	private TextField txtNgayKhoiHanh;
	private Button buttonNgayKhoiHanh;
	private StackPane spNgayKhoiHanh;
	private DatePicker ngayKhoiHanh;
	private TextField txtGioKhoiHanh;
	private Button buttonGioKhoiHanh;
	private StackPane spGioKhoiHanh;
	private TextField txtNgayDenDuKien;
	private Button buttonNgayDenDuKien;
	private StackPane spNgayDenDuKien;
	private TextField txtGioDenDuKien;
	private Button buttonGioDenDuKien;
	private StackPane spGioDenDuKien;
	
	private TableView<HanhTrinh> table;
	private Map<String, Object> mapTmp = new HashMap<String, Object>();
	private Button buttonTiepTuc;
	
	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
	
	Class<?> clazz = this.getClass();
	private ScrollPane scrollPaneMenu;
	private VBox danhSachMenuItem;
	private HBox quanLiVeTauMenu;
	private Label quanLiVeTauLabel;

	private ImageView quanLiVeTauIconView;

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

	private VBox layoutCapNhatChuyenTau = new VBox();

	private TextField txtMaChuyenTau;
	private TextField txtThoiGianKhoiHanh;
	private HBox thoiGianKhoiHanhBox;

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
	private File fileTmp;
	private GhiFile ghiFile = new GhiFile();
	private ChuyenTauDAO chuyentaudao = null;
	private HanhTrinh_DAO hanhtrinhdao = new HanhTrinh_DAO();
	private ArrayList<HanhTrinh> listHanhTrinh = null;
	private HanhTrinhGa_dao hanhtrinhgadao = new HanhTrinhGa_dao();
	private ArrayList<HanhTrinh> hanhTrinhRemove = new ArrayList<HanhTrinh>();
	private Button buttonTroLai;
	private List<String> listMaHanhTrinhTheoNgay;
	private TextField txtChuyenTauChon;
	private StackPane spChuyenTauChon;
	private boolean checkDuLieu = true;


    public CapNhatChuyenTau() throws SQLException {
    }

    @Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1540, 800);
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
			
			creat_capnhatchuyentau_layout();
				
				noiDungChinh = new VBox();
				noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
				noiDungChinh.setPrefWidth(1200);
				
				noiDungChinh = layoutThemToaTau;
				
				BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
				root.setLeft(menuList);
				root.setCenter(noiDungChinh);
				
				primaryStage.setFullScreen(true);
				primaryStage.show();

			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
public VBox creat_capnhatchuyentau_layout() throws IOException, SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fileTmp = new File("src/main/resources/tmp_CapNhatChuyenTau.txt");
		Map<String, Object> mapTmp = new HashMap<>();
		chuyentaudao = new ChuyenTauDAO();
		
		List<String> allLine = Files.readAllLines(Paths.get("src/main/resources/tmp_CapNhatChuyenTau.txt")); 
		String line = allLine.get(0);
		JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
		String maChuyenTau = obj.get("maChuyenTau").getAsString();
		ChuyenTau chuyenTau = chuyentaudao.getChuyenTauBangMa(maChuyenTau);
		
		
		//label đầu
		HBox boxLblThemChuyenTau = new HBox();
		lblThemChuyenTau = new Label("Cập nhật hành trình");
		lblThemChuyenTau.setId("lbl_TieuDe");
		boxLblThemChuyenTau.getChildren().add(lblThemChuyenTau);
		boxLblThemChuyenTau.setAlignment(Pos.TOP_LEFT);
		boxLblThemChuyenTau.setMaxWidth(1100);
		
		gridThemChuyenTau = new GridPane();
		gridThemChuyenTau.setHgap(80);
		gridThemChuyenTau.setVgap(50);
//		gridThemChuyenTau.setPadding(new Insets(60, 0, 0, 0));
		gridThemChuyenTau.setAlignment(Pos.CENTER);
		
		ngayKhoiHanh = new DatePicker();
		ngayKhoiHanh.setPrefWidth(30);
		ngayKhoiHanh.setPrefHeight(60);
		ngayKhoiHanh.setStyle("-fx-font-size: 18px;");
		ngayKhoiHanh.setOpacity(0);
		
		txtNgayKhoiHanh = new TextField();
		txtNgayKhoiHanh.setPrefWidth(450);
		txtNgayKhoiHanh.setPrefHeight(60);
		txtNgayKhoiHanh.setId("txt_ThemChuyenTau");
		txtNgayKhoiHanh.setMouseTransparent(true);
		txtNgayKhoiHanh.setFocusTraversable(false);
		txtNgayKhoiHanh.setPromptText("Ngày khởi hành");
		
		
		ngayKhoiHanh.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal != null) {
				txtNgayKhoiHanh.setText(newVal.format(formatter));
				
			}
		});
		
		buttonNgayKhoiHanh = new Button();
		ImageView iconThoiGian = animation.taoImgGhe("/img/calendar.png");
		iconThoiGian.setFitWidth(30);
		iconThoiGian.setFitHeight(30);
		buttonNgayKhoiHanh.setGraphic(iconThoiGian);
		buttonNgayKhoiHanh.setStyle("-fx-background-color: transparent");
		
		spNgayKhoiHanh = new StackPane();
		spNgayKhoiHanh.getChildren().addAll(txtNgayKhoiHanh, buttonNgayKhoiHanh, ngayKhoiHanh);
		spNgayKhoiHanh.setAlignment(buttonNgayKhoiHanh, Pos.CENTER_RIGHT);
		spNgayKhoiHanh.setAlignment(ngayKhoiHanh, Pos.CENTER_RIGHT);
		
		txtChuyenTauChon = new TextField();
		txtChuyenTauChon.setPrefWidth(450);
		txtChuyenTauChon.setPrefHeight(60);
		txtChuyenTauChon.setId("txt_ThemChuyenTau");
		txtChuyenTauChon.setMouseTransparent(true);
		txtChuyenTauChon.setFocusTraversable(false);
		txtChuyenTauChon.setPromptText("Hành trình đang chọn: " + chuyenTau.getHanhTrinh().getMaHanhTrinh());
		
		spChuyenTauChon = new StackPane();
		spChuyenTauChon.getChildren().addAll(txtChuyenTauChon);
		
		
		gridThemChuyenTau.add(spNgayKhoiHanh, 0, 0);
		gridThemChuyenTau.add(spChuyenTauChon, 1, 0);
//		gridThemChuyenTau.add(spNgayDenDuKien, 0, 1);
//		gridThemChuyenTau.add(spGioDenDuKien, 1, 1);
		
		table = new TableView<>();
		table.setId("table_DauTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String>  maHanhTrinh = new javafx.scene.control.TableColumn<>("Mã hành trình");
		maHanhTrinh.setCellValueFactory(new PropertyValueFactory<>("maHanhTrinh"));
		maHanhTrinh.setPrefWidth(300);
		maHanhTrinh.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String> tenHanhTrinh = new javafx.scene.control.TableColumn<>("Tên hành trình");
		tenHanhTrinh.setCellValueFactory(new PropertyValueFactory<>("tenHanhTrinh"));
		tenHanhTrinh.setPrefWidth(350);
		tenHanhTrinh.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String> soTramDung = new javafx.scene.control.TableColumn<>("Số trạm dừng");
		soTramDung.setCellValueFactory(cellData -> {
			HanhTrinh hanhTrinh = cellData.getValue();
			ArrayList<Ga> listGa = hanhtrinhdao.getListGaByMaHanhTrinh(hanhTrinh.getMaHanhTrinh());
			if(hanhTrinh != null && !listGa.isEmpty()) return new SimpleStringProperty(String.valueOf(listGa.size()));
			else return new SimpleStringProperty("");
				
		});
		soTramDung.setPrefWidth(250);
		soTramDung.setId("table_ThemToaTau");
		
		javafx.scene.control.TableColumn<HanhTrinh, String> isRemove = new javafx.scene.control.TableColumn<>("Trạng thái");
		isRemove.setCellValueFactory(cellData -> {
			HanhTrinh hanhTrinh = cellData.getValue();
			if(hanhTrinh != null && hanhTrinh.isRemove()) return new SimpleStringProperty("Đã tồn tại");
			else return new SimpleStringProperty("Sẵn sàng");
		});
		isRemove.setPrefWidth(298);
		isRemove.setId("table_ThemToaTau");
		
		
		LocalDate ngayKhoiHanhDate = chuyenTau.getNgayGioDi().toLocalDate();
		ngayKhoiHanh.setValue(null);
		txtNgayKhoiHanh.setText(ngayKhoiHanhDate.format(formatter));
		
		listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(ngayKhoiHanhDate);

        listHanhTrinh = hanhtrinhdao.getList();
        
        
        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
            HanhTrinh ht = listHanhTrinh.get(i);
            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
            	ht.setRemove(true);
//                listHanhTrinh.remove(i);
            }
        }
		
		ObservableList<HanhTrinh> data = themSpacer(listHanhTrinh);
		
		
		
//		ngayKhoiHanh.valueProperty().addListener((obs, oldDate, newDate) -> {
//			if(newDate == null) return;
//		    if (newDate != null) {
//		        if(newDate.isBefore(LocalDate.now())) {
//		        	Alert alert = new Alert(Alert.AlertType.ERROR);
//					alert.setTitle("Lỗi");
//					alert.setHeaderText(null);
//					alert.setContentText("Ngày khởi hành không được trước ngày hiện tại");
//					Stage stage = (Stage) ngayKhoiHanh.getScene().getWindow();
//					alert.initOwner(stage);
//					alert.initModality(Modality.WINDOW_MODAL);
//					alert.showAndWait();
//					
//					txtNgayKhoiHanh.setText(ngayKhoiHanhDate.format(formatter));
//					ngayKhoiHanh.setValue(ngayKhoiHanhDate);
//		        } else if(newDate.equals(ngayKhoiHanhDate)) {
//		        	listMaHanhTrinhTheoNgay.clear();
//			        listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(newDate);
//			        listHanhTrinh.clear();
//			        listHanhTrinh = hanhtrinhdao.getList();
//			        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
//			            HanhTrinh ht = listHanhTrinh.get(i);
//			            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
//			            	ht.setRemove(true);
////			                listHanhTrinh.remove(i);
//			            }
//			        }
//			        ObservableList<HanhTrinh> dataNew = themSpacer(listHanhTrinh);
//			        table.setItems(dataNew);
//		        } else {
//		        	listMaHanhTrinhTheoNgay.clear();
//			        listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(newDate);
//			        listHanhTrinh.clear();
//			        listHanhTrinh = hanhtrinhdao.getList();
//			        
//			        
//			        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
//			            HanhTrinh ht = listHanhTrinh.get(i);
//			            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
//			            	ht.setRemove(true);
////			                listHanhTrinh.remove(i);
//			            }
//			            if(listMaHanhTrinhTheoNgay.contains(chuyenTau.getHanhTrinh().getMaHanhTrinh()) && !txtNgayKhoiHanh.getText().equalsIgnoreCase(ngayKhoiHanhDate.toString())) {
//			            	Alert alert = new Alert(Alert.AlertType.ERROR);
//							alert.setTitle("Lỗi");
//							alert.setHeaderText(null);
//							alert.setContentText("Ngày bạn đang chọn hiện đã có hành trình " + chuyenTau.getHanhTrinh().getMaHanhTrinh());
//							Stage stage = (Stage) ngayKhoiHanh.getScene().getWindow();
//							alert.initOwner(stage);
//							alert.initModality(Modality.WINDOW_MODAL);
//							alert.showAndWait();
//							ngayKhoiHanh.setValue(null);
//			            }
//			        }
//			        
////			        if(listHanhTrinh.size() == 0) {
////			        	Alert alert = new Alert(Alert.AlertType.ERROR);
////						alert.setTitle("Lỗi");
////						alert.setHeaderText(null);
////						alert.setContentText("Ngày đang chọn hiện không còn hành trình");
////						Stage stage = (Stage) ((Node) ((EventObject) obs).getSource()).getScene().getWindow();
////						alert.initOwner(stage);
////						alert.initModality(Modality.WINDOW_MODAL);
////						alert.showAndWait();
////			        }
//			        ObservableList<HanhTrinh> dataNew = themSpacer(listHanhTrinh);
//			        table.setItems(dataNew);
//		        }
//		        	
//		        }
//		    	
//		        
//		        	
//		  
//		        
//		    
//		});
		ngayKhoiHanh.valueProperty().addListener((obs, oldDate, newDate) -> {
		    if (newDate == null) return;
		    
		    if(newDate.isEqual(ngayKhoiHanhDate)) {
		    	System.out.println("1");
		    	checkDuLieu = true;
		    	listMaHanhTrinhTheoNgay.clear();
		        listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(newDate);
		        listHanhTrinh.clear();
		        listHanhTrinh = hanhtrinhdao.getList();
		        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
		            HanhTrinh ht = listHanhTrinh.get(i);
		            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
		                ht.setRemove(true);
		            }
		        }
		        ObservableList<HanhTrinh> dataNew = themSpacer(listHanhTrinh);
		        table.setItems(dataNew);
		        txtNgayKhoiHanh.setText(ngayKhoiHanhDate.format(formatter));
		        ngayKhoiHanh.setValue(null);
		        return;
		    }
		    
		    if (newDate.isBefore(LocalDate.now()) && newDate != null) {
		    	System.out.println(newDate + " " + ngayKhoiHanhDate);
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Lỗi");
		        alert.setHeaderText(null);
		        alert.setContentText("Ngày khởi hành không được trước ngày hiện tại");
		        Stage stage = (Stage) ngayKhoiHanh.getScene().getWindow();
		        alert.initOwner(stage);
		        alert.initModality(Modality.WINDOW_MODAL);
		        alert.showAndWait();

		        ngayKhoiHanh.setValue(ngayKhoiHanhDate);
		    }  else {
		    	checkDuLieu = false;
		        listMaHanhTrinhTheoNgay.clear();
		        listMaHanhTrinhTheoNgay = chuyentaudao.getListMaHanhTrinhTheoNgay(newDate);
		        listHanhTrinh.clear();
		        listHanhTrinh = hanhtrinhdao.getList();

		        for (int i = listHanhTrinh.size() - 1; i >= 0; i--) {
		            HanhTrinh ht = listHanhTrinh.get(i);
		            if (listMaHanhTrinhTheoNgay.contains(ht.getMaHanhTrinh())) {
		                ht.setRemove(true);
		            }
		        }

		        if (listMaHanhTrinhTheoNgay.contains(chuyenTau.getHanhTrinh().getMaHanhTrinh())
		                && !newDate.equals(ngayKhoiHanhDate)) {
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Lỗi");
		            alert.setHeaderText(null);
		            alert.setContentText("Ngày bạn đang chọn hiện đã có hành trình " + chuyenTau.getHanhTrinh().getMaHanhTrinh());
		            Stage stage = (Stage) ngayKhoiHanh.getScene().getWindow();
		            alert.initOwner(stage);
		            alert.initModality(Modality.WINDOW_MODAL);
		            alert.showAndWait();
		            ngayKhoiHanh.setValue(null);
		        }

		        ObservableList<HanhTrinh> dataNew = themSpacer(listHanhTrinh);
		        table.setItems(dataNew);
		    }
		});

		
		table.setPrefHeight(700);
		table.getColumns().addAll(maHanhTrinh, tenHanhTrinh, soTramDung, isRemove); 
		table.setMaxWidth(1200);
		
		table.setItems(data);
		table.getColumns().forEach(col -> col.setSortable(false));
		
		table.setRowFactory(tv -> {
		    TableRow<HanhTrinh> row = new TableRow<HanhTrinh>() {
		        @Override
		        public void updateItem(HanhTrinh hanhTrinh, boolean empty) {
		            super.updateItem(hanhTrinh, empty);
		            
		            getStyleClass().remove("selected-row");
		            setStyle("");
		            setMouseTransparent(true);
		            setFocusTraversable(false);
		            table.setId("");

		            if (hanhTrinh != null && hanhTrinh.getMaHanhTrinh().isEmpty()) {
		                setStyle("-fx-background-color: white; -fx-min-height: 16px; -fx-max-height: 16px;");
		                setMouseTransparent(true);    
		                setFocusTraversable(false);  
		            } else if (hanhTrinh != null && chuyenTau.getHanhTrinh().getMaHanhTrinh().equalsIgnoreCase(hanhTrinh.getMaHanhTrinh()) && checkDuLieu ){
		            	setStyle("-fx-background-color: green");
		            	setMouseTransparent(true);    
		                setFocusTraversable(false);
		            } else if (hanhTrinh != null && hanhTrinh.isRemove()) {
		            	setStyle("-fx-background-color: red");
		            	setMouseTransparent(true);    
		                setFocusTraversable(false); 
		            }  else if (hanhTrinh != null && chuyenTau.getHanhTrinh().getMaHanhTrinh().equalsIgnoreCase(hanhTrinh.getMaHanhTrinh())) {
		            	setStyle("-fx-background-color: green");
		            	setMouseTransparent(true);    
		                setFocusTraversable(false);
		            } else if (hanhTrinh != null && hanhTrinhRemove.contains(hanhTrinh)) {
		            	getStyleClass().add("selected-row");
		            }
		    }
		    };
		    row.setOnMouseClicked(event -> {
		    	if (!row.isEmpty()) {
		            HanhTrinh hanhTrinh = row.getItem();
		            if(hanhTrinhRemove.contains(hanhTrinh)) {
		            	hanhTrinhRemove.remove(hanhTrinh);
		            }
		            else {
		            	hanhTrinhRemove.clear();
		            	hanhTrinhRemove.add(hanhTrinh);
		            }
		         table.refresh();
		        }
		    });
		    return row;
		});
		
		buttonTiepTuc = new Button("Tiếp tục");
		buttonTiepTuc.setPrefWidth(200);
		buttonTiepTuc.setPrefHeight(60);
		buttonTiepTuc.setId("button_Blue");
		
		buttonTroLai = new Button("Trở lại");
		buttonTroLai.setPrefWidth(200);
		buttonTroLai.setPrefHeight(60);
		buttonTroLai.setId("button_Red");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox boxButton = new HBox(buttonTroLai, spacer, buttonTiepTuc);
		boxButton.setMaxWidth(1250);
		
		//đưa vào layout
		layoutThemToaTau.getChildren().addAll(boxLblThemChuyenTau, gridThemChuyenTau, table, boxButton);
		layoutThemToaTau.setAlignment(Pos.CENTER);
		layoutThemToaTau.setStyle("-fx-background-color: #FFFFFF");
		return layoutThemToaTau;
	}
	
	public ObservableList<HanhTrinh> themSpacer(ArrayList<HanhTrinh> listHanhTrinh) {
	    ObservableList<HanhTrinh> listHanhTrinhSpace = FXCollections.observableArrayList();
	    for (int i = 0; i < listHanhTrinh.size(); i++) {
	    	if(i == 0) listHanhTrinhSpace.add(new HanhTrinh("", "", null, true)); 
	    	listHanhTrinhSpace.add(listHanhTrinh.get(i));                   
	        if (i < listHanhTrinh.size() - 1) {                 
	        	listHanhTrinhSpace.add(new HanhTrinh("", "", null, true));
	        }
	    }
	    return listHanhTrinhSpace;
	}
	
//	public void removeGiaoDien(ArrayList<HanhTrinh> hanhTrinhRemove, HanhTrinh hanhTrinh) {
//		if(!hanhTrinhRemove.contains(hanhTrinh)) hanhTrinhRemove.add(hanhTrinh);
//		else hanhTrinhRemove.remove(hanhTrinh);
//	}
	
	public VBox getGDThemChuyenTau(){
        return  this.noiDungChinh;
    }
	public Button getButtonTiepTuc() {
		return this.buttonTiepTuc;
	}
	public Button getButtonTroLai() {
		return this.buttonTroLai;
	}
	public boolean xuLyEventCu() throws IOException, SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<String> allLine = Files.readAllLines(Paths.get("src/main/resources/tmp_CapNhatChuyenTau.txt")); 
		String line = allLine.get(0);
		JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
		String maChuyenTau = obj.get("maChuyenTau").getAsString();
		ChuyenTau chuyenTau = chuyentaudao.getChuyenTauBangMa(maChuyenTau);
		LocalDate ngayKhoiHanhDate = chuyenTau.getNgayGioDi().toLocalDate();
		
		
		
		if(ngayKhoiHanh.getValue() == null && txtNgayKhoiHanh.getText().equalsIgnoreCase(ngayKhoiHanhDate.format(formatter))) {
			
			mapTmp.put("maHanhTrinh", chuyenTau.getHanhTrinh().getMaHanhTrinh());
			mapTmp.put("ngayKhoiHanh", txtNgayKhoiHanh.getText());
			ArrayList<HanhTrinhGa> listHanhTrinhGa = hanhtrinhgadao.getListHanhTrinhGaTheoMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
	        LocalDate ngayDenDuKienLast = chuyenTau.getNgayGioDi().toLocalDate().plusDays(listHanhTrinhGa.getLast().getSoNgayDiQua());
	        mapTmp.put("ngayDenDuKien", ngayDenDuKienLast.format(formatter));
	        mapTmp.put("gioKhoiHanh", listHanhTrinhGa.get(0).getGioDiKeHoach().toString());
	        mapTmp.put("gioDenDuKien", listHanhTrinhGa.getLast().getGioDiKeHoach().toString());
			
			
			
		}
		else if(ngayKhoiHanh.getValue() == null && !txtNgayKhoiHanh.getText().equalsIgnoreCase(ngayKhoiHanhDate.format(formatter))){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText(null);
			alert.setContentText("Ngày khởi hành hiện tại chưa phù hợp");
			Stage stage = (Stage) ngayKhoiHanh.getScene().getWindow();
			alert.initOwner(stage);
			alert.initModality(Modality.WINDOW_MODAL);
			alert.showAndWait();
			return false;
		}
		 else if(ngayKhoiHanh.getValue() != null && !txtNgayKhoiHanh.getText().equalsIgnoreCase(ngayKhoiHanhDate.format(formatter))){
			mapTmp.put("maHanhTrinh", chuyenTau.getHanhTrinh().getMaHanhTrinh());
			mapTmp.put("ngayKhoiHanh", txtNgayKhoiHanh.getText());
			ArrayList<HanhTrinhGa> listHanhTrinhGa = hanhtrinhgadao.getListHanhTrinhGaTheoMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
	        LocalDate ngayDenDuKienLast = ngayKhoiHanh.getValue().plusDays(listHanhTrinhGa.getLast().getSoNgayDiQua());
	        mapTmp.put("ngayDenDuKien", ngayDenDuKienLast.format(formatter));
	        mapTmp.put("gioKhoiHanh", listHanhTrinhGa.get(0).getGioDiKeHoach().toString());
	        mapTmp.put("gioDenDuKien", listHanhTrinhGa.getLast().getGioDiKeHoach().toString());
		} else {
			mapTmp.put("maHanhTrinh", chuyenTau.getHanhTrinh().getMaHanhTrinh());
			mapTmp.put("ngayKhoiHanh", txtNgayKhoiHanh.getText());
			ArrayList<HanhTrinhGa> listHanhTrinhGa = hanhtrinhgadao.getListHanhTrinhGaTheoMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
	        LocalDate ngayDenDuKienLast = chuyenTau.getNgayGioDi().toLocalDate().plusDays(listHanhTrinhGa.getLast().getSoNgayDiQua());
	        mapTmp.put("ngayDenDuKien", ngayDenDuKienLast.format(formatter));
	        mapTmp.put("gioKhoiHanh", listHanhTrinhGa.get(0).getGioDiKeHoach().toString());
	        mapTmp.put("gioDenDuKien", listHanhTrinhGa.getLast().getGioDiKeHoach().toString());
		}
        Gson gson = new Gson();
        String json = gson.toJson(mapTmp);
        ghiFile.appendData(json, fileTmp);
        return true; 
    }
	
	public static void main(String[] args) {

		Application.launch(CapNhatChuyenTau.class, args);
	}
}