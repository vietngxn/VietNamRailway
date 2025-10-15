package fourcore.GiaoDien;

import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fourcore.Entity.GheNgoi;
import fourcore.Entity.ToaTau;
import fourcore.animation.Animation;
import fourcore.dao.Ghe_dao;
import fourcore.dao.ToaTau_dao;
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


public class ThietLapGiaGhe extends Application {
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
	private VBox layoutThemCT;
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
	private Ghe_dao ghedao = new Ghe_dao();
	private ToaTau_dao toataudao = new ToaTau_dao(); 
	private Animation animation = new Animation();
	private GridPane gridAllGhe;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1540, 1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
			layoutThemCT = new VBox(20);
			
			create_themct_layout();
			
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
	
	
	public GridPane setUpGheNgoi(String maToa) {
		
		String duongdanghethuong = "/img/ghe.png";
		String duongdangheluudong = "/img/gheluudong.png";
		
		ArrayList<GheNgoi> listGhe = ghedao.testList(); 
		
		int count = 0, cum = 1, cot = 0, dong = 0, flag = 36, cotAll = 0, dongAll = 0;
		GridPane gridCumGhe = new GridPane();
		
		GridPane gridAllGhe = new GridPane();
		gridAllGhe.setHgap(50);
		gridAllGhe.setVgap(50);
		
		for(GheNgoi gn : listGhe) {
			if(gn.getToaTau().getMaToaTau().equalsIgnoreCase(maToa)) {
				if(count < 24) {
					Label lblGhe = new Label(Integer.toString(gn.getSoGhe()));
					lblGhe.setId("lbl_Ghe");
					
					StackPane spGhe = new StackPane(animation.taoImgGhe(duongdanghethuong), lblGhe);
					spGhe.setUserData(gn.getMaGheNgoi());
				
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
		gridAllGhe.setId("grid_AllGhe");
		return gridAllGhe;
	}
	
	public GridPane setUpGheNam(String maToa) {
		String duongdanghethuong = "/img/ghegiuongnam.png";
		String duongdangheluudong = "/img/ghegiuongnamluudong.png";
		
		Label lblKhoang1 = new Label("  Khoang 1");
		lblKhoang1.setId("txt_Money");
		Label lblKhoang2 = new Label("  Khoang 2");
		lblKhoang2.setId("txt_Money");
		Label lblKhoang3 = new Label("  Khoang 3");
		lblKhoang3.setId("txt_Money");
		
		ArrayList<GheNgoi> listGhe = ghedao.testList(); 
		
		int count = 0, cot = 0, dong = 0, cotAll = 0, dongAll = 1;
		GridPane gridCumGhe = new GridPane();
		
		GridPane gridAllGhe = new GridPane();
		gridAllGhe.add(lblKhoang1, 0, 0);
		gridAllGhe.add(lblKhoang2, 2, 0);
		gridAllGhe.add(lblKhoang3, 4, 0);
		
		
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
		gridAllGhe.setVgap(50);
		gridAllGhe.setAlignment(Pos.CENTER);
		gridAllGhe.setMaxWidth(600);
		gridAllGhe.setPrefHeight(300);
		gridAllGhe.setId("grid_AllGhe");
		return gridAllGhe;
	} 
	
	
	public void create_themct_layout() {
		
		
		String duongdantoa = "/img/thantau.png";
		String duongdandautao = "/img/dautau.png";
		int flagToaDau = 0;
		
		gridAllGhe = new GridPane();
		
		ArrayList<ToaTau> listToa = toataudao.testListToaTau();
		
		Label lblThemCT = new Label("Thêm chuyến tàu");
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
		
		Label lblGiaGocCuoc = new Label("Giá gốc: 25000");
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
		
		Label lblDauTao = new Label("SE1");
		
		StackPane spDauTao = new StackPane(imgDauTao, lblDauTao);
		spDauTao.setMargin(lblDauTao, new Insets(10, 0, 0, 0));
		
		for(int i = toataudao.testListToaTau().size() - 1; i >= -1; i--) {
			
			if(i == -1) {
				boxToaTau.getChildren().add(spDauTao);
				break;
			}
			
			else {
				if(flagToaDau == 0) {
					gridAllGhe = setUpGheNgoi("GN01");
					flagToaDau++;
				}
				
				ImageView imgToa = animation.taoImgGhe(duongdantoa);
				
				Label lblToa = new Label(toataudao.testListToaTau().get(i).getMaToaTau().toString());
				
				StackPane spToa = new StackPane(imgToa, lblToa);
				spToa.setUserData(toataudao.testListToaTau().get(i).getMaToaTau());
				spToa.setMargin(lblToa, new Insets(10, 0, 0, 0));
				spToa.setOnMouseClicked(event -> {
					int viTriLayout = layoutThemCT.getChildren().indexOf(gridAllGhe);
//					boolean ngoi;
//					if(viTriLayout == 5) ngoi = true;
//					else ngoi = false;
					
					if(viTriLayout != -1) {
						layoutThemCT.getChildren().remove(gridAllGhe);
						
						if(viTriLayout - 1 >= 0) {
							Node tmpXoa = layoutThemCT.getChildren().get(viTriLayout - 1);
							if (tmpXoa.getId() != null) layoutThemCT.getChildren().remove(tmpXoa);
						}
//						if(soPhanTuLayout == 7) {
//							layoutThemCT.getChildren().remove(viTriLayout - 1);
//						}
					}
					
					if(toataudao.getToaTau(spToa.getUserData().toString()).getMaToaTau().equalsIgnoreCase("GN03") || toataudao.getToaTau(spToa.getUserData().toString()).getMaToaTau().equalsIgnoreCase("GN01")) {
						gridAllGhe = new GridPane();
						gridAllGhe = setUpGheNgoi(spToa.getUserData().toString());
//						if(ngoi) layoutThemCT.getChildren().add(viTriLayout, gridAllGhe);
//						else layoutThemCT.getChildren().add(viTriLayout - 1, gridAllGhe);
						layoutThemCT.getChildren().add(viTriLayout, gridAllGhe);
					} else {
						gridAllGhe = new GridPane();
						gridAllGhe = setUpGheNam(spToa.getUserData().toString());
						
//						Label lblKhoang1 = new Label("Khoang 1");
//						lblKhoang1.setId("txt_Money");
//						Label lblKhoang2 = new Label("Khoang 2");
//						lblKhoang2.setId("txt_Money");
//						Label lblKhoang3 = new Label("Khoang 3");
//						lblKhoang3.setId("txt_Money");
//						
//						HBox boxKhoang = new HBox(128);
//						boxKhoang.setAlignment(Pos.CENTER);
//						boxKhoang.setId("boxKhoang");
//						boxKhoang.getChildren().addAll(lblKhoang1, lblKhoang2, lblKhoang3);
						
//						if(ngoi) {
////							layoutThemCT.getChildren().add(viTriLayout, boxKhoang);
//							layoutThemCT.getChildren().add(viTriLayout + 1, gridAllGhe);	
//						} else {
////							layoutThemCT.getChildren().add(viTriLayout - 1, boxKhoang);
//							layoutThemCT.getChildren().add(viTriLayout, gridAllGhe);
//						}
						layoutThemCT.getChildren().add(viTriLayout, gridAllGhe);
					}
				});
				
				boxToaTau.getChildren().add(spToa);
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
		
		Label lblGiaGocGhe = new Label("Giá gốc: 25000");
		lblGiaGocGhe.setId("txt_Money");
		
		GridPane gridGiaGhe = new GridPane();
		gridGiaGhe.add(boxGiaGhe, 0, 0);
		gridGiaGhe.setColumnSpan(boxGiaGhe, 2);
		gridGiaGhe.add(lblGiaGocGhe, 2, 0);
		gridGiaGhe.setHgap(20);
		gridGiaGhe.setAlignment(Pos.CENTER);
		
		Label lblGiaCuocHienTai = new Label("Giá cước hiện tại: 25000");
		lblGiaCuocHienTai.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		Label lblGiaVeHienTai = new Label("Giá vé hiện tại: 25000");
		lblGiaVeHienTai.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
		HBox boxGiaHienTai = new HBox(50);
		boxGiaHienTai.getChildren().addAll(lblGiaCuocHienTai, lblGiaVeHienTai);
		boxGiaHienTai.setAlignment(Pos.CENTER);
		
		Button buttonCapNhat = new Button();
		buttonCapNhat.setText("Cập nhật");
		buttonCapNhat.setPrefWidth(150);
		buttonCapNhat.setPrefHeight(50);
		buttonCapNhat.setId("button_Blue");
		Button buttonTroLai = new Button();
		buttonTroLai.setText("Trở lại");
		buttonTroLai.setPrefWidth(150);
		buttonTroLai.setPrefHeight(50);
		buttonTroLai.setId("button_Red");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox boxButton = new HBox(buttonTroLai, spacer, buttonCapNhat);
		boxButton.setMaxWidth(1000);
		
		layoutThemCT.getChildren().addAll(boxLblThemCT, lblThietLapGiaCuoc, gridThietLapGiaCuoc, lblThietLapGiaGhe, boxToaTau, gridAllGhe, gridGiaGhe, boxGiaHienTai, boxButton);;
		layoutThemCT.setAlignment(Pos.CENTER);
		layoutThemCT.setStyle("-fx-background-color: #FFFFFF");
	}
	
	
	
								   
	
	
	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}