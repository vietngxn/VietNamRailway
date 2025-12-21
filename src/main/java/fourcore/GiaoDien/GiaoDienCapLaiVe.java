package fourcore.GiaoDien;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LichSuTuongTacVe;
import fourcore.Entity.LoaiTuongTacVe;
import fourcore.Entity.Ve;
import fourcore.dao.KhachHangDAO;
import fourcore.dao.LichSuTuongTacVe_Dao;
import fourcore.dao.LoaiTuongTac_Dao;
import fourcore.dao.VeDAO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GiaoDienCapLaiVe extends Application {

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
    private Label lblCapLaiVe;
    private Pane pnlCapLaiVeve;
    private Button btnTimKiem;
    private Pane pnlTimKiem;
    private Label colMaVe;
    private Label colChuyen;
    private Label colNgayKhoiHanh;
    private Label colViTriGhe;
    private Label colTrangThai;
    private VBox pnlDataDoiVe;
    private Label colGaDiGaDen;
    private ScrollPane scrollPane;
    private GridPane tableCol;
    private StackPane paneCol1;
    private StackPane paneCol2;
    private StackPane paneCol3;
    private StackPane paneCol4;
    private StackPane paneCol5;
    private StackPane paneCol6;
    private HBox pnlCapNhatVe;
    private Button btnCapNhatTrangThaiVe;
    private HBox doiVeBox;
    private HBox banVeBox;
    private HBox hoanVeBox;
    private HBox capVeBox;
    private HBox quanLiKhachHangMenu;
    private ImageView quanLiKhachHangIconView;
    private Label quanLiKhachHangLabel;
    private HBox quanLiHoaDonMenu;
    private ImageView quanLiHoaDonIconView;
    private Label quanLiHoaDonLabel;
    private ImageView quanLiThongKeIconView;
    private HBox quanLiThongKeMenu;
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
    private VBox layout_timkiem;
    private HBox layout_lbl_timkiem;
    private Label lbl_timkiem;
    private VBox layout_txt_timkiem;
    private TextField txt_timkiem;
    private Button btnCapVe;
    private Pane pnlCapLaiVe;
    private ArrayList<Ve> list = null;
    private Label lbl_tenTau;
    private Label lbl_ngaygiodi;
    private Label lbl_gadigaden;
    private Label lbl_maVeTau;
    private Label lbl_trangthai;
    private Label lbl_vitrighe;
    private VBox selectedPanel = null;
    private String maveTauchon = null;

    private Label lbl_trangthai2;
    private Button btn_trangthai2;
    private VBox layout_trangthai;
    private VBox layout_chiTiet;
    private Ve vechon = new Ve();
    private boolean dangCapNhatTrangThai = false;
    private VeDAO vedao;
    private LoaiTuongTac_Dao lttdao;
    private LichSuTuongTacVe_Dao lsttdao;
    private ArrayList<LichSuTuongTacVe> listtt;
    private ArrayList<LoaiTuongTacVe> listloaitt;


    // private ArrayList<Ve> list = vedao.themNhieuVeTau();

    public VBox taoDataChoTableCapLaiVe(String maVeTau, String tenTau, String gaDigaDen, LocalDateTime ngayGioDi,
                                        LocalDateTime ngayGioDen, int soToa, int soTang, int soGhe, String loaiVe, String maGiayTo, double giaVe,
                                        String ghiChu, String trangThaiDoiVe, String trangThaiVe, String maChuyenTau, String maKhachHang,
                                        String maKhuyenMai, String maDoiTuongGiamGia) throws SQLException {

        NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));

        VBox pnlReturn = new VBox();
        VBox.setMargin(pnlReturn, new Insets(0, 30, 0, 30));

        GridPane data = new GridPane();
        pnlReturn.getChildren().add(data);

        data.setHgap(10);
        data.setAlignment(Pos.CENTER);
        data.setMaxWidth(1330);
        data.setPrefHeight(70);
        data.setPadding(new Insets(0, 0, 0, 10));

        String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        String trangthai3 = "";
        if(trangThaiVe.equalsIgnoreCase("Hoạt Động"))
        {
        	trangthai3 = "Hoạt Động";
        }
        else if(trangThaiVe.equalsIgnoreCase("Kết thúc")) {
        	trangthai3 = "Kết thúc";
        }
        else if(trangThaiVe.equalsIgnoreCase("đã hoàn trả")) {
        	trangthai3 = "Đã hoàn trả";
        }
        
        Label[] labels = { lbl_maVeTau = new Label(maVeTau), lbl_tenTau = new Label(tenTau),
                lbl_gadigaden = new Label(gaDigaDen), lbl_ngaygiodi = new Label(ngayGioDi.format(formatter)),
                lbl_vitrighe = new Label("Toa số " + Integer.valueOf(soToa)+ " chỗ "+Integer.valueOf(soGhe).toString()), lbl_trangthai = new Label(trangthai3) };

        lbl_maVeTau.setTranslateX(-35);
        lbl_tenTau.setTranslateX(-35);
        lbl_gadigaden.setTranslateX(-35);
        lbl_ngaygiodi.setTranslateX(-25);
        lbl_vitrighe.setTranslateX(-20);
        lbl_trangthai.setTranslateX(-15);
        double[] widths = { 200, 180, 250, 270, 220, 200 };

        for (int i = 0; i < labels.length; i++) {
            Label lbl = labels[i];
            lbl.setStyle(baseStyle);

            if (i == 5) { // trạng thái
                String text = lbl.getText().toLowerCase();
                if (text.equalsIgnoreCase("hoạt động"))
                    lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: #009D75;");
                else if (text.equalsIgnoreCase("Đã Hoàn Trả"))
                {
                    lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: black;");
                }
                else if (text.equalsIgnoreCase("Kết thúc"))
                {
                    lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: red;");
                }
            }

            StackPane pane = new StackPane(lbl);
            pane.setPrefSize(widths[i], 70);
            pane.setAlignment(Pos.CENTER);
            data.add(pane, i, 0);
        }

        String normalStyle = """
                    -fx-background-color: rgba(0, 186, 203, 0.3);
                    -fx-background-radius: 15px;
                    -fx-border-radius: 15px;
                    -fx-border-color: #B6D0D3;
                    -fx-border-width: 1px;
                """;

        String hoverStyle = """
                    -fx-background-color: rgba(0, 186, 203);
                    -fx-background-radius: 15px;
                    -fx-border-radius: 15px;
                    -fx-border-color: #00BACB;
                    -fx-border-width: 1px;
                    -fx-cursor: hand;
                """;

        data.setStyle(normalStyle);

        data.setOnMouseEntered(e -> {
//            data.setStyle(hoverStyle);
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
            scaleUp.setToX(1.02);
            scaleUp.setToY(1.02);
            scaleUp.play();
        });

        data.setOnMouseExited(e -> {
//            data.setStyle(normalStyle);
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            scaleDown.play();
        });
        
        
       
        
        

        // ======= THÔNG TIN CHI TIẾT =======
        HBox pnlThongTinChiTiet = new HBox(50);
        VBox.setMargin(pnlThongTinChiTiet, new Insets(5, 0, 0, 5));

        // Panel 1 - thông tin cá nhân
        GridPane pnlsubCT1 = new GridPane();
        pnlsubCT1.setHgap(0);
        pnlsubCT1.setVgap(5);
        pnlsubCT1.setAlignment(Pos.CENTER);

        String leftStyle = """
                    -fx-background-color: #00BACB;
                    -fx-background-radius: 10px 0 0 10px;
                    -fx-border-radius: 10px 0 0 10px;
                    -fx-border-color: #00BACB;
                    -fx-alignment: center-left;
                    -fx-font-weight: bold;
                    -fx-font-family: "Kanit";
                    -fx-padding: 8 12 8 12;
                """;

        // -fx-padding: 8 12 8 12;
        String rightStyle = """
                    -fx-background-color: #E0E0E0;
                    -fx-background-radius: 0 10px 10px 0;
                    -fx-border-radius: 0 10px 10px 0;
                    -fx-border-color: #E0E0E0;
                    -fx-alignment: center-left;
                    -fx-font-weight: bold;
                    -fx-font-family: "Kanit";
                    -fx-padding: 8 12 8 12;
                """;
        
        String selectedStyle = """
                -fx-background-color: rgba(0, 186, 203);
                -fx-background-radius: 15px;
                -fx-border-radius: 15px;
                -fx-border-color: #00BACB;
                -fx-border-width: 2px;
                -fx-cursor: hand;
            """;

        Ve ve1 = vedao.getVeBangMaVe(maVeTau);
        pnlsubCT1.addRow(0, taoSubCT1("Họ tên", ve1.getKhachHang().getHoten(), leftStyle, rightStyle));
        pnlsubCT1.addRow(1,
                taoSubCT1("Đối tượng", ve1.getDoiTuongGiamGia().getTenDoiTuongGiamGia(), leftStyle, rightStyle));

        pnlsubCT1.addRow(2, taoSubCT1("Số giấy tờ", ve1.getKhachHang().getCccd(), leftStyle, rightStyle));

        // Các panel giá trị
        String lblCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 18px;";
        String lblValueCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 30px;";


        // LichSuTuongTacVe lstt = new vedao1.get
//        VBox pnlsubCT2 = taoSubCT2("Giá vé", nf.format(ve1.getGiaVe()), lblCTStyle, lblValueCTStyle);
        VBox pnlsubCT3 = taoSubCT2("Giảm đối tượng", nf.format(ve1.getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()),
                lblCTStyle, lblValueCTStyle);
        VBox pnlsubCT4 = taoSubCT2("Khuyến mãi", nf.format(ve1.getKhuyenMai().getGiaTriPhanTramKhuyenMai()), lblCTStyle,
                lblValueCTStyle);
        double phanTramGiam = (ve1.getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()
                + ve1.getKhuyenMai().getGiaTriPhanTramKhuyenMai()) / 100;

        double giave = vedao.getGiaVe(maVeTau);
        DecimalFormat df = new DecimalFormat("#,###");
        String ft = df.format(giave);
        VBox pnlsubCT5 = taoSubCT2("Thành tiền",ft, lblCTStyle,
                lblValueCTStyle);

        pnlsubCT1.setPrefWidth(400);
        for (Pane pnl : new Pane[] { pnlsubCT3, pnlsubCT4, pnlsubCT5 })
            pnl.setPrefWidth(320);

        pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1,  pnlsubCT3, pnlsubCT4, pnlsubCT5);
        pnlReturn.getChildren().add(pnlThongTinChiTiet);

        pnlThongTinChiTiet.setManaged(false);
        pnlThongTinChiTiet.setVisible(false);

        pnlReturn.setOnMouseClicked(event -> {
        	
        	if(trangThaiVe.equalsIgnoreCase("kết thúc"))
        	{
        		
        		btnCapVe.setDisable(true);
        	}
        	else {
        		btnCapVe.setDisable(false);
        	}
        	
        	
        	if (dangCapNhatTrangThai && selectedPanel != null && selectedPanel != pnlReturn) {
    	        // Xóa toàn bộ children trừ GridPane data (index 0)
    	        while (selectedPanel.getChildren().size() > 1) {
    	            selectedPanel.getChildren().remove(1);
    	        }
    	        
    	        layout_trangthai = null;
    	        
    	        // Reset trạng thái
    	        dangCapNhatTrangThai = false;
    	        
    	        // Deselect dòng cũ
    	        GridPane oldData = (GridPane) selectedPanel.getChildren().get(0);
    	        oldData.setStyle(normalStyle);
    	        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), oldData);
    	        scaleDown.setToX(1.0);
    	        scaleDown.setToY(1.0);
    	        scaleDown.play();
    	        
    	        selectedPanel = null;
    	        vechon = null;
    	        maveTauchon = null;
    	   }
        	
        	
        	
            boolean check = pnlThongTinChiTiet.isVisible();
            pnlThongTinChiTiet.setManaged(!check);
            pnlThongTinChiTiet.setVisible(!check);
            pnlReturn.getChildren().remove(layout_trangthai);
            
            
            // LƯU THÔNG TIN PANEL ĐÃ CHỌN
            if (!check) {
            	
            	 data.setStyle(selectedStyle);
                 ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
                 scaleUp.setToX(1.02);
                 scaleUp.setToY(1.02);
                 scaleUp.play();
                 
                 if (pnlReturn.getChildren().size() > 1) {
                     while (pnlReturn.getChildren().size() > 1) {
                         pnlReturn.getChildren().remove(1);
                     }
                 }
                 pnlReturn.getChildren().add(pnlThongTinChiTiet);
                 
                 pnlThongTinChiTiet.setManaged(true);
                 pnlThongTinChiTiet.setVisible(true);
                 pnlReturn.getChildren().remove(layout_trangthai);
                 
                selectedPanel = pnlReturn;
                vechon = new Ve(maVeTau, gaDigaDen, gaDigaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soToa, soTang,
                        soGhe, loaiVe, maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, new ChuyenTau(maChuyenTau),
                        new KhachHang(maKhachHang), new KhuyenMai(maKhuyenMai), new DoiTuongGiamGia(maDoiTuongGiamGia));
                maveTauchon = maVeTau;
                btnCapNhatTrangThaiVe.setOnAction(e -> {

                    pnlThongTinChiTiet.setVisible(false);
                    Label lbl_trangthai2 = getTrangThaiLabel(selectedPanel);
                    if (lbl_trangthai2 != null) {
                        lbl_trangthai2.setAlignment(Pos.CENTER);
                    }
                    

                    dangCapNhatTrangThai = true;
                    selectedPanel = pnlReturn;

                    // lbl_trangthai2.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill:
                    // #009D75;");
                    pnlReturn.getChildren().remove(layout_trangthai);
                    layout_trangthai = taolayout_trangthai();
                    layout_trangthai.setTranslateY(-100);
                    layout_trangthai.setTranslateX(1113);

                    lbl_trangthai2.setPrefSize(170, 40);

                    btn_trangthai2.setOnAction(e1 -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Cảnh báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Xác Nhận đổi trạng thái cho vé?");
                        alert.showAndWait();

                        try {
                            String s = "Hoạt Động";
                            if (vechon.getTrangThaiVe().equalsIgnoreCase("Hoạt Động")) {
                                s = "Kết Thúc";
                            }
                            if (vedao.ThayDoiTrangThaiVe(vechon.getMaVeTau(), s)) {
                                System.out.println("Cập nhật thành công");

                                // Refresh danh sách
                                pnlDataDoiVe.getChildren().clear();
                                hienThi();

                                // Đóng layout_trangthai sau khi cập nhật
                                if (layout_trangthai != null && layout_trangthai.getParent() != null) {
                                    selectedPanel.getChildren().remove(layout_trangthai);
                                    layout_trangthai = null;
                                }
                            } else {
                                System.out.println("Cập Nhật không thành công");
                            }
                        } catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    });

                    pnlReturn.getChildren().add(layout_trangthai);
                });
            } else { // Nếu đang đóng chi tiết
            	data.setStyle(normalStyle);
                ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
                scaleDown.setToX(1.0);
                scaleDown.setToY(1.0);
                scaleDown.play();

                pnlThongTinChiTiet.setManaged(false);
                pnlThongTinChiTiet.setVisible(false);
                pnlReturn.getChildren().remove(layout_trangthai);
                if (dangCapNhatTrangThai && selectedPanel != pnlReturn) {
                    if (layout_trangthai != null && layout_trangthai.getParent() != null) {
                        selectedPanel.getChildren().remove(layout_trangthai);
                    }
                    dangCapNhatTrangThai = false;
                }

                Label lbl_trangthai2 = getTrangThaiLabel(selectedPanel);
                if (lbl_trangthai2 != null) {
                    lbl_trangthai2.setAlignment(Pos.CENTER);
                }
                pnlReturn.getChildren().remove(layout_trangthai);

                vechon = null;
                selectedPanel = null;
                maveTauchon = null;
            }
        });

        return pnlReturn;
    }

    private HBox taoSubCT1(String label, String value, String leftStyle, String rightStyle) {
        Label lblLeft = new Label(label);
        lblLeft.setWrapText(true);
        StackPane left = new StackPane(lblLeft);
        Label lblRight = new Label(value);
        lblRight.setWrapText(true);
        StackPane right = new StackPane(lblRight);

        left.setPrefWidth(100);
        right.setPrefWidth(150);
        left.setStyle(leftStyle);
        right.setStyle(rightStyle);
        left.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER);
        return new HBox(left, right);
    }

    private VBox taoSubCT2(String title, String value, String labelStyle, String valueStyle) {
        Label lblTitle = new Label(title);
        lblTitle.setStyle(labelStyle);
        Label  lblValue = new Label();
        if(title.equalsIgnoreCase("giảm đối tượng") || title.equalsIgnoreCase("khuyến mãi"))
        {
            lblValue = new Label(value+"%");
        }
        else {
            lblValue = new Label(value);
        }
        lblValue.setStyle(valueStyle);
        VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private VBox taolayout_trangthai() {

        if (vechon.getTrangThaiVe().equalsIgnoreCase("hoạt động")) {

            btn_trangthai2 = new Button("Ngưng Hoạt Động");
            btn_trangthai2.setPrefSize(170, 40);
            btn_trangthai2.setStyle(
                    "-fx-background-color : red;-fx-text-fill : white;-fx-font-size : 16.5px;-fx-font-weight:bold;-fx-font-family : 'Kanit';");

        } else {
            btn_trangthai2 = new Button("Hoạt Động");
            btn_trangthai2.setPrefSize(170, 40);
            btn_trangthai2.setStyle(
                    "-fx-background-color : green;-fx-text-fill : white;-fx-font-size : 16.5px;-fx-font-weight:bold;-fx-font-family : 'Kanit';");
        }
        layout_trangthai = new VBox();
        layout_trangthai.getChildren().add(btn_trangthai2);

        return layout_trangthai;
    }

    private Label getTrangThaiLabel(VBox panel) {
        if (panel == null || panel.getChildren().isEmpty())
            return null;
        Node first = panel.getChildren().get(0);
        if (!(first instanceof GridPane grid))
            return null;
        if (grid.getChildren().size() < 6)
            return null;
        StackPane cell = (StackPane) grid.getChildren().get(5);
        return (Label) cell.getChildren().get(0);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            vedao = new VeDAO();
            lttdao = new LoaiTuongTac_Dao();
            listloaitt = lttdao.getList();
            lsttdao = new LichSuTuongTacVe_Dao();
            listtt = lsttdao.getList();

            menuList = new VBox();
            menuList.setStyle("-fx-background-color: #F7F7F7;");
            menuList.setPrefWidth(500);

            logoImgView = new ImageView(getClass().getResource("/img/logov2.png").toExternalForm());
            logoImgView.setFitWidth(500);
            logoImgView.setFitHeight(270);
            menuList.getChildren().add(logoImgView);

            scrollPaneMenu = new ScrollPane();
            danhSachMenuItem = new VBox();
            // Font labelFont =
            // Font.loadFont(getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-Bold.ttf"),20);

            // ======================
            // || QUAN LI VE TAU ||
            // ======================
            quanLiVeTauMenu = new HBox();
            quanLiVeTauMenu.setSpacing(102);
            quanLiVeTauMenu.setPadding(new Insets(20, 95, 20, 20));
            quanLiVeTauMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiVeTauIconView = new ImageView(getClass().getResource("/img/ticket.png").toExternalForm());
            quanLiVeTauIconView.setFitWidth(30);
            quanLiVeTauIconView.setFitHeight(30);
            quanLiVeTauIconView.setTranslateX(20);

            quanLiVeTauLabel = new Label("Quản lí vé tàu");

            InputStream interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            Font labelFont;

            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiVeTauLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            String showMenuPhuIconSource = "/img/chevron-down.png";
            showMenuPhuIconView = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
            showMenuPhuIconView.setFitWidth(20);
            showMenuPhuIconView.setFitHeight(20);
            showMenuPhuIconView.setTranslateX(50);
            quanLiVeTauMenu.getChildren().addAll(quanLiVeTauIconView, quanLiVeTauLabel, showMenuPhuIconView);

            danhSachMenuItem.getChildren().add(quanLiVeTauMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);
            VBox menuPhuQuanLiVeTau = new VBox();
            menuPhuQuanLiVeTau.setSpacing(10);
            menuPhuQuanLiVeTau.setPadding(new Insets(0, 40, 0, 0));
            menuPhuQuanLiVeTau.setVisible(false);
            menuPhuQuanLiVeTau.setManaged(false);
            menuPhuQuanLiVeTau.setStyle("-fx-background-color: #D2EEF0;");
            banVeBox = new HBox();
            doiVeBox = new HBox();
            hoanVeBox = new HBox();
            capVeBox = new HBox();

            Label banVeLabel = new Label("Bán vé");
            Label doiVeLabel = new Label("Đổi vé");
            Label hoanVeLabel = new Label("Hoàn vé");
            Label capVeLabel = new Label("Cấp vé");

            banVeBox.getChildren().add(banVeLabel);
            doiVeBox.getChildren().add(doiVeLabel);
            hoanVeBox.getChildren().add(hoanVeLabel);
            capVeBox.getChildren().add(capVeLabel);

            InputStream interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
            Font labelMenuPhu = Font.loadFont(interSemiBold, 15);

            for (Label label : new Label[] { banVeLabel, doiVeLabel, hoanVeLabel, capVeLabel }) {
                label.setStyle("-fx-background-color: #D2EEF0;");
                label.setTranslateY(-10);
                label.setFont(labelMenuPhu);
                label.setPadding(new Insets(12, 320, 12, 155));
                label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
                label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

            }
            for (HBox hbox : new HBox[] { banVeBox, doiVeBox, hoanVeBox, capVeBox }) {
                hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
                hbox.setPadding(new Insets(0, 50, 0, 0));
                hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
                hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
            }

            menuPhuQuanLiVeTau.getChildren().addAll(banVeBox, doiVeBox, hoanVeBox, capVeBox);
            danhSachMenuItem.getChildren().add(menuPhuQuanLiVeTau);

            // Sự kiện onclick vào menu
            quanLiVeTauMenu.setOnMouseClicked(event -> {
                boolean isVisible = menuPhuQuanLiVeTau.isVisible();
                menuPhuQuanLiVeTau.setVisible(!isVisible);
                menuPhuQuanLiVeTau.setManaged(!isVisible);
                if (isVisible == false) {
                    quanLiVeTauMenu.setStyle(" -fx-background-color: #79D9E1;");
                } else {
                    quanLiVeTauMenu.setStyle(" -fx-background-color: #F7F7F7;");

                }
                TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiVeTau);
                if (!isVisible) {
                    menuPhuQuanLiVeTau.setVisible(true);
                    menuPhuQuanLiVeTau.setManaged(true);
                    menuPhuQuanLiVeTau.setTranslateY(-20); // bắt đầu từ trên
                    slide.setFromY(-20);
                    slide.setToY(0);
                    quanLiVeTauMenu.setStyle("-fx-background-color: #79D9E1;");
                } else {
                    slide.setFromY(0);
                    slide.setToY(-20);
                    slide.setOnFinished(e -> {
                        menuPhuQuanLiVeTau.setVisible(false);
                        menuPhuQuanLiVeTau.setManaged(false);
                    });
                    quanLiVeTauMenu.setStyle("-fx-background-color: #F7F7F7;");
                }
                slide.play();

            });

            // ======================
            // ||QUAN LI KHACH HANG||
            // ======================
            quanLiKhachHangMenu = new HBox();
            quanLiKhachHangMenu.setSpacing(102);
            quanLiKhachHangMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiKhachHangMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiKhachHangIconView = new ImageView(getClass().getResource("/img/user-group.png").toExternalForm());
            quanLiKhachHangIconView.setFitWidth(30);
            quanLiKhachHangIconView.setFitHeight(30);
            quanLiKhachHangIconView.setTranslateX(20);

            quanLiKhachHangLabel = new Label("Quản lí khách hàng");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiKhachHangLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiKhachHangMenu.getChildren().addAll(quanLiKhachHangIconView, quanLiKhachHangLabel);

            danhSachMenuItem.getChildren().add(quanLiKhachHangMenu);

            // ======================
            // ||QUAN LI HOA DON ||
            // ======================
            quanLiHoaDonMenu = new HBox();
            quanLiHoaDonMenu.setSpacing(102);
            quanLiHoaDonMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiHoaDonMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiHoaDonIconView = new ImageView(getClass().getResource("/img/receipt-tax.png").toExternalForm());
            quanLiHoaDonIconView.setFitWidth(30);
            quanLiHoaDonIconView.setFitHeight(30);
            quanLiHoaDonIconView.setTranslateX(20);

            quanLiHoaDonLabel = new Label("Quản lí hóa đơn");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiHoaDonLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            //
            quanLiHoaDonMenu.getChildren().addAll(quanLiHoaDonIconView, quanLiHoaDonLabel);

            danhSachMenuItem.getChildren().add(quanLiHoaDonMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

            // ======================
            // ||QUAN LI THONG KE ||
            // ======================
            quanLiThongKeMenu = new HBox();
            quanLiThongKeMenu.setSpacing(102);
            quanLiThongKeMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiThongKeMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiThongKeIconView = new ImageView(
                    getClass().getResource("/img/presentation-chart-bar.png").toExternalForm());
            quanLiThongKeIconView.setFitWidth(30);
            quanLiThongKeIconView.setFitHeight(30);
            quanLiThongKeIconView.setTranslateX(20);

            quanLiThongKeLabel = new Label("Quản lí thống kê");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiThongKeLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            showMenuPhuIconSource = "/img/chevron-down.png";
            showMenuPhuIconView = new ImageView(getClass().getResource(showMenuPhuIconSource).toExternalForm());
            showMenuPhuIconView.setFitWidth(20);
            showMenuPhuIconView.setFitHeight(20);
            showMenuPhuIconView.setTranslateX(25.5);
            quanLiThongKeMenu.getChildren().addAll(quanLiThongKeIconView, quanLiThongKeLabel, showMenuPhuIconView);

            danhSachMenuItem.getChildren().add(quanLiThongKeMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);
            VBox menuPhuQuanLiThongKe = new VBox();
            menuPhuQuanLiThongKe.setSpacing(10);
            menuPhuQuanLiThongKe.setPadding(new Insets(0, 40, 0, 0));
            menuPhuQuanLiThongKe.setVisible(false);
            menuPhuQuanLiThongKe.setManaged(false);
            menuPhuQuanLiThongKe.setStyle("-fx-background-color: #D2EEF0;");

            HBox thongKe1Box = new HBox();
            HBox thongKe2Box = new HBox();
            HBox thongKe3Box = new HBox();

            Label thongKe1Label = new Label("Thống kê doanh thu theo tháng");
            Label thongKe2Label = new Label("Thống kê ");
            Label thongKe3Label = new Label("Thống kê ghế bán chạy trong tháng");

            thongKe1Box.getChildren().add(thongKe1Label);
            thongKe2Box.getChildren().add(thongKe2Label);
            thongKe3Box.getChildren().add(thongKe3Label);

            interSemiBold = getClass().getResourceAsStream("/fonts/Inter/static/Inter_18pt-SemiBold.ttf");
            labelMenuPhu = Font.loadFont(interSemiBold, 15);

            for (Label label : new Label[] { thongKe1Label, thongKe2Label, thongKe3Label }) {
                label.setStyle("-fx-background-color: #D2EEF0;");
                label.setTranslateY(0);
                label.setFont(labelMenuPhu);
                label.setPadding(new Insets(12, 320, 12, 155));
                label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #79D9E1;"));
                label.setOnMouseExited(e -> label.setStyle("-fx-background-color: #D2EEF0;"));

            }
            for (HBox hbox : new HBox[] { thongKe1Box, thongKe2Box, thongKe3Box }) {
                hbox.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
                hbox.setPadding(new Insets(0, 50, 0, 0));
                hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #79D9E1;"));
                hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #D2EEF0;"));
            }

            menuPhuQuanLiThongKe.getChildren().addAll(thongKe1Box, thongKe2Box, thongKe3Box);
            danhSachMenuItem.getChildren().add(menuPhuQuanLiThongKe);

            // Sự kiện onclick vào menu
            quanLiThongKeMenu.setOnMouseClicked(event -> {
                boolean isVisible = menuPhuQuanLiThongKe.isVisible();
                menuPhuQuanLiThongKe.setVisible(!isVisible);
                menuPhuQuanLiThongKe.setManaged(!isVisible);
                if (isVisible == false) {
                    quanLiThongKeMenu.setStyle(" -fx-background-color: #79D9E1;");
                } else {
                    quanLiThongKeMenu.setStyle(" -fx-background-color: #F7F7F7;");

                }
                TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuPhuQuanLiThongKe);
                if (!isVisible) {
                    menuPhuQuanLiThongKe.setVisible(true);
                    menuPhuQuanLiThongKe.setManaged(true);
                    menuPhuQuanLiThongKe.setTranslateY(-20);
                    slide.setFromY(-20);
                    slide.setToY(0);
                    quanLiThongKeMenu.setStyle("-fx-background-color: #79D9E1;");
                } else {
                    slide.setFromY(0);
                    slide.setToY(-20);
                    slide.setOnFinished(e -> {
                        menuPhuQuanLiThongKe.setVisible(false);
                        menuPhuQuanLiThongKe.setManaged(false);
                    });
                    quanLiThongKeMenu.setStyle("-fx-background-color: #F7F7F7;");
                }
                slide.play();

            });

            // =======================
            // ||QUAN LI NHAN VIEN ||
            // =======================
            quanLiNhanVienMenu = new HBox();
            quanLiNhanVienMenu.setSpacing(102);
            quanLiNhanVienMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiNhanVienMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiNhanVienIconView = new ImageView(getClass().getResource("/img/user-circle.png").toExternalForm());
            quanLiNhanVienIconView.setFitWidth(30);
            quanLiNhanVienIconView.setFitHeight(30);
            quanLiNhanVienIconView.setTranslateX(20);

            Label quanLiNhanVienLabel = new Label("Quản lí nhân viên");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiNhanVienLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiNhanVienMenu.getChildren().addAll(quanLiNhanVienIconView, quanLiNhanVienLabel);

            danhSachMenuItem.getChildren().add(quanLiNhanVienMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

            // =======================
            // ||QUAN LI CTKM ||
            // =======================
            quanLiCTKMMenu = new HBox();
            quanLiCTKMMenu.setSpacing(102);
            quanLiCTKMMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiCTKMMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiCTKMIconView = new ImageView(getClass().getResource("/img/tag.png").toExternalForm());
            quanLiCTKMIconView.setFitWidth(30);
            quanLiCTKMIconView.setFitHeight(30);
            quanLiCTKMIconView.setTranslateX(20);

            Label quanLiCTKMLabel = new Label("Quản lí CT khuyến mãi");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiCTKMLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiCTKMMenu.getChildren().addAll(quanLiCTKMIconView, quanLiCTKMLabel);

            danhSachMenuItem.getChildren().add(quanLiCTKMMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

            // =======================
            // ||QUAN LI CHUYEN TAU ||
            // =======================
            quanLiChuyenTauMenu = new HBox();
            quanLiChuyenTauMenu.setSpacing(102);
            quanLiChuyenTauMenu.setPadding(new Insets(15, 95, 15, 20));
            quanLiChuyenTauMenu.setStyle("-fx-alignment: center-left;");

            // quanLiVeTauIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/ticket.png"));
            quanLiChuyenTauIconView = new ImageView(
                    getClass().getResource("/img/clipboard-check.png").toExternalForm());
            quanLiChuyenTauIconView.setFitWidth(30);
            quanLiChuyenTauIconView.setFitHeight(30);
            quanLiChuyenTauIconView.setTranslateX(20);

            Label quanLiChuyenTauLabel = new Label("Quản lí chuyến tàu");

            interBoldFont = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
            if (interBoldFont != null) {
                labelFont = Font.loadFont(interBoldFont, 20);
                quanLiChuyenTauLabel.setFont(labelFont);
                System.out.println("⚠️ Set font thanh cong.");
                System.out.println("Loaded font: " + labelFont.getName());
            } else {
                System.out.println("⚠️ Không tìm thấy font, dùng font mặc định.");
                labelFont = Font.font("System", FontWeight.BOLD, 20); // fallback
            }

            // showMenuPhuIcon = new
            // Image(clazz.getResourceAsStream("/resources/images/chevron-up.png"));
            quanLiChuyenTauMenu.getChildren().addAll(quanLiChuyenTauIconView, quanLiChuyenTauLabel);

            danhSachMenuItem.getChildren().add(quanLiChuyenTauMenu);
            scrollPaneMenu.setContent(danhSachMenuItem);

            scrollPaneMenu.setPrefHeight(600);
            scrollPaneMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

            scrollPaneMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            menuList.getChildren().add(scrollPaneMenu);
            // USER BOX

            HBox userBox = new HBox();
            userIcon = new ImageView(getClass().getResource("/img/user-circle.png").toExternalForm());
            userIcon.setFitWidth(50);
            userIcon.setFitHeight(50);

            userLabel = new Label("Nguyễn Tiến Đạt");
            userLabel.setFont(labelFont);
            userLabel.setTranslateX(30);
            settingIcon = new ImageView(getClass().getResource("/img/cog.png").toExternalForm());
            settingIcon.setFitWidth(30);
            settingIcon.setFitHeight(30);
            settingIcon.setTranslateX(200);
            userBox.setPadding(new Insets(10, 0, 10, 50));
            userBox.setStyle("-fx-alignment: center-left; -fx-background-color: #79D9E1");
            userBox.setTranslateY(70);
            userBox.getChildren().addAll(userIcon, userLabel, settingIcon);
            menuList.getChildren().add(userBox);

            // Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
            noiDungChinh = new VBox();
            noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
            noiDungChinh.setPrefWidth(1300);
            BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));

            pnlCapLaiVe = new Pane();
            lblCapLaiVe = new Label("Cấp lại vé");
            pnlCapLaiVe.getChildren().add(lblCapLaiVe);
            lblCapLaiVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
            VBox.setMargin(pnlCapLaiVe, new Insets(20, 0, 0, 50));
            noiDungChinh.getChildren().add(pnlCapLaiVe);

            layout_timkiem = new VBox();

            layout_lbl_timkiem = new HBox();
            layout_lbl_timkiem.setPrefSize(1200, 40);
            lbl_timkiem = new Label("Nhập cccd của khách hàng");
            lbl_timkiem.setTranslateX(10);
            lbl_timkiem.setTranslateY(0);
            lbl_timkiem.setStyle(
                    "-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");

            ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
            img_timkiem.setTranslateX(1050);
            img_timkiem.setFitHeight(25);
            img_timkiem.setFitWidth(25);

            layout_lbl_timkiem.getChildren().addAll(lbl_timkiem, img_timkiem);
            layout_lbl_timkiem.setTranslateY(48);
            layout_timkiem.getChildren().add(layout_lbl_timkiem);

            layout_txt_timkiem = new VBox();
            txt_timkiem = new TextField();
            txt_timkiem.setPrefHeight(40);
            txt_timkiem.setMaxSize(1200, 45);
            txt_timkiem.setPadding(new Insets(10));
            txt_timkiem.setStyle(
                    "-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
            txt_timkiem.setFocusTraversable(false);
            layout_txt_timkiem.getChildren().add(txt_timkiem);
            layout_timkiem.getChildren().add(layout_txt_timkiem);

            layout_timkiem.setTranslateX(100);

            txt_timkiem.focusedProperty().addListener((obs, oval, nval) -> {
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), lbl_timkiem);
                if (nval) {
                    tt.setToY(-40);
                }
                tt.play();
            });

            VBox.setMargin(layout_timkiem, new Insets(0, 0, 10, 0));
            noiDungChinh.getChildren().add(layout_timkiem);

            btnTimKiem = new Button("Tìm kiếm");
            btnTimKiem.setMaxSize(150, 300);
            String btnStyle = """
                          -fx-font-family: 'Inter';
                       -fx-font-weight: bold;
                       -fx-font-size: 20px;
                       -fx-text-fill: white;
                       -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
                       -fx-background-radius: 10;
                    -fx-cursor: hand;
                    -fx-padding: 10 20 10 20;
                    """;
            btnTimKiem.setStyle(btnStyle);
            pnlTimKiem = new StackPane();
            pnlTimKiem.setMaxSize(1200, 400);

            pnlTimKiem.getChildren().add(btnTimKiem);
            btnTimKiem.setAlignment(Pos.CENTER);

            VBox.setMargin(pnlTimKiem, new Insets(30, 0, 0, 0));
            noiDungChinh.getChildren().add(pnlTimKiem);

            // table
            tableCol = new GridPane();
            tableCol.setHgap(10);
            tableCol.setVgap(20);
            tableCol.setAlignment(Pos.CENTER);
            tableCol.setMaxWidth(1330);
            tableCol.setMaxHeight(600);

            VBox.setMargin(tableCol, new Insets(30, 10, 10, 0));

            String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 24px; -fx-font-weight: bold;";

            colMaVe = new Label("Mã vé");
            colMaVe.setStyle(styleHeader);
            colChuyen = new Label("Chuyến");
            colChuyen.setStyle(styleHeader);
            colGaDiGaDen = new Label("Ga đi - Ga đến");
            colGaDiGaDen.setStyle(styleHeader);
            colNgayKhoiHanh = new Label("Ngày khởi hành");
            colNgayKhoiHanh.setStyle(styleHeader);
            colViTriGhe = new Label("Vị trí ghế");
            colViTriGhe.setStyle(styleHeader);
            colTrangThai = new Label("Trạng thái");
            colTrangThai.setStyle(styleHeader);

            paneCol1 = new StackPane(colMaVe);
            paneCol2 = new StackPane(colChuyen);
            paneCol3 = new StackPane(colGaDiGaDen);
            paneCol4 = new StackPane(colNgayKhoiHanh);
            paneCol5 = new StackPane(colViTriGhe);
            paneCol6 = new StackPane(colTrangThai);

            paneCol1.setPrefWidth(200);
            paneCol2.setPrefWidth(180);
            paneCol3.setPrefWidth(250);
            paneCol4.setPrefWidth(270);
            paneCol5.setPrefWidth(220);
            paneCol6.setPrefWidth(200);

            paneCol1.setAlignment(Pos.CENTER);
            paneCol2.setAlignment(Pos.CENTER);
            paneCol3.setAlignment(Pos.CENTER);
            paneCol4.setAlignment(Pos.CENTER);
            paneCol5.setAlignment(Pos.CENTER);
            paneCol6.setAlignment(Pos.CENTER);

            tableCol.add(paneCol1, 0, 0);
            tableCol.add(paneCol2, 1, 0);
            tableCol.add(paneCol3, 2, 0);
            tableCol.add(paneCol4, 3, 0);
            tableCol.add(paneCol5, 4, 0);
            tableCol.add(paneCol6, 5, 0);

            noiDungChinh.getChildren().add(tableCol);

            pnlDataDoiVe = new VBox(10);
            pnlDataDoiVe.setAlignment(Pos.CENTER);

            hienThi();

            // === TẠO SCROLLPANE ===
            scrollPane = new ScrollPane(pnlDataDoiVe);
            scrollPane.setMaxHeight(600);
            scrollPane.setFitToWidth(true);
            scrollPane.setPannable(true);
            scrollPane.setStyle("""
                        -fx-background-color: transparent;
                        -fx-border-color: transparent;
                        -fx-border-width: 0;
                    """);

            // Chỉ hiện thanh cuộn dọ
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            noiDungChinh.getChildren().add(scrollPane);

            pnlCapNhatVe = new HBox(30);

            VBox.setMargin(pnlCapNhatVe, new Insets(30, 0, 0, 500));
            pnlCapNhatVe.setAlignment(Pos.CENTER);

            btnCapNhatTrangThaiVe = new Button("Cập nhật trạng thái vé");
            btnCapNhatTrangThaiVe.setPrefSize(250, 60);
            btnCapVe = new Button("Cấp vé");
            btnCapVe.setPrefSize(250, 60);

            
            
            btnCapVe.setStyle(btnStyle);
            btnCapVe.setOnAction(e -> {
                if (selectedPanel == null) {
                    // Chưa chọn panel nào
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng chọn một vé trước!");
                    alert.showAndWait();
                } else {
                    // Đã chọn panel - lấy thông tin từ labels
                    String mave = vechon.getMaVeTau();
                    
                    
                    try {


                        File pdfFile = new File("VeTauExport/"+mave+".pdf");
                        if (pdfFile.exists()) {
                            if (Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().open(pdfFile); // mở file PDF bằng ứng dụng mặc định
                            } else {
                                System.out.println("Desktop không được hỗ trợ. Vui lòng mở file thủ công: " + pdfFile.getAbsolutePath());
                            }
                        } else {
                            System.out.println("File PDF không tồn tại: " + "VeTauExport/"+mave+".pdf");
                        }
                    	
                    		
                    	
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        
                        alert.setHeaderText(null);
                        alert.setContentText("Cấp Vé Thành Công!");
                        alert.showAndWait();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            btnCapNhatTrangThaiVe.setStyle(btnStyle);

            pnlCapNhatVe.getChildren().addAll(btnCapNhatTrangThaiVe, btnCapVe);
            noiDungChinh.getChildren().add(pnlCapNhatVe);

            // add su kien disable btn
            btnTimKiem.setOnMouseClicked(event -> {
            	String regex = "\\d{12}";
                String input = txt_timkiem.getText().trim();
                String cccd = txt_timkiem.getText().trim(); // FIX: thêm trim()

                if (input.isEmpty()) {
                    btnCapVe.setDisable(true);
                    btnCapNhatTrangThaiVe.setDisable(true);
                    String eStyle = """
                        -fx-font-size: 18px;
                        -fx-background-color: rgba(203, 0, 44, 0.83);
                        -fx-text-fill: white;
                        -fx-background-radius: 10;
                        -fx-cursor: hand;
                        -fx-padding: 10 20 10 20;
                    """;
                    btnCapNhatTrangThaiVe.setStyle(eStyle);
                    btnCapVe.setStyle(eStyle);
                    
                    
                    if(!input.matches(regex))
                    {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi định dạng");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng nhập CCCD!");
                    alert.showAndWait();
                    }
                    return;
                } else {
                    btnCapVe.setDisable(false);
                    btnCapNhatTrangThaiVe.setDisable(false);
                    
                    try {
                        System.out.println("🔍 Tìm kiếm CCCD: " + cccd);
                        
                        ArrayList<Ve> listVe = vedao.getVeTheoCCCD(cccd);
                        
                        // FIX: Kiểm tra null
                        if (listVe == null) {
                            System.out.println("❌ listVe là NULL - khởi tạo ArrayList mới");
                            listVe = new ArrayList<>();
                        }
                        
                        System.out.println("📊 Số vé tìm được: " + listVe.size());
                        
                        pnlDataDoiVe.getChildren().clear();

                        if (listVe.isEmpty()) {
                            System.out.println("⚠️ Không tìm thấy vé nào");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText(null);
                            alert.setContentText("Không tìm thấy vé nào cho CCCD: " + cccd);
                            alert.showAndWait();
                            btnCapVe.setDisable(true);
                            btnCapNhatTrangThaiVe.setDisable(true);
                        } else {
                            // FIX: Thêm try-catch trong vòng for để bắt exception
                            for (Ve x : listVe) {
                                try {
                                    System.out.println("✅ Thêm vé: " + x.getMaVeTau());
                                    
                                    pnlDataDoiVe.getChildren().add(
                                        taoDataChoTableCapLaiVe(
                                            x.getMaVeTau(),
                                            x.getChuyenTau().getMaChuyenTau(),
                                            x.getGaDi() + " - " + x.getGaDen(),
                                            x.getNgayGioDi(),
                                            x.getNgayGioDen(),
                                            x.getSoToa(),
                                            x.getSoTang(),
                                            x.getSoGhe(),
                                            x.getLoaiVe(),
                                            x.getKhachHang().getCccd(),
                                            x.getGiaVe(),
                                            x.getGhiChu(),
                                            x.getTrangThaiDoiVe(),
                                            x.getTrangThaiVe(),
                                            x.getChuyenTau().getMaChuyenTau(),
                                            x.getKhachHang().getMaKhachHang(),
                                            x.getKhuyenMai().getMaKhuyenMai(),
                                            x.getDoiTuongGiamGia().getMaDoiTuongGiamGia()
                                        )
                                    );
                                } catch (Exception ex) {
                                    System.out.println("❌ Lỗi khi thêm vé: " + x.getMaVeTau());
                                    ex.printStackTrace();
                                }
                            }
                            
                            btnCapNhatTrangThaiVe.setStyle(btnStyle);
                            btnCapVe.setStyle(btnStyle);
                        }
                        
                    } catch (SQLException e1) {
                        System.out.println("❌ SQL Error: " + e1.getMessage());
                        e1.printStackTrace();
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi cơ sở dữ liệu");
                        alert.setHeaderText(null);
                        alert.setContentText("Lỗi: " + e1.getMessage());
                        alert.showAndWait();
                        
                        btnCapVe.setDisable(true);
                        btnCapNhatTrangThaiVe.setDisable(true);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public void hienThi() throws SQLException
    {
        pnlDataDoiVe.getChildren().clear();
        list = vedao.getListVe();
        for (Ve x : list) {
            pnlDataDoiVe.getChildren().add(
                    taoDataChoTableCapLaiVe(
                            x.getMaVeTau(),
                            x.getChuyenTau().getMaChuyenTau(),
                            x.getGaDi() + " - " + x.getGaDen(),
                            x.getNgayGioDi(), // ngayGioDi (LocalDateTime)
                            x.getNgayGioDen(), // ngayGioDen (LocalDateTime)
                            x.getSoToa(), // soToa
                            x.getSoTang(), // soTang
                            x.getSoGhe(), // soGhe
                            x.getLoaiVe(), // loaiVe
                            x.getKhachHang().getCccd(), // maGiayTo
                            x.getGiaVe(), // giaVe
                            x.getGhiChu(), // ghiChu
                            x.getTrangThaiDoiVe(), // trangThaiDoiVe
                            x.getTrangThaiVe(), // trangThaiVe
                            x.getChuyenTau().getMaChuyenTau(), // maChuyenTau
                            x.getKhachHang().getMaKhachHang(), // maKhachHang
                            x.getKhuyenMai().getMaKhuyenMai(), // maKhuyenMai
                            x.getDoiTuongGiamGia().getMaDoiTuongGiamGia()));
        }
    }
    public VBox getNoiDungChinhVe() {
        return this.noiDungChinh;
    }

    public static void main(String[] args) {
        Application.launch(GiaoDienCapLaiVe.class, args);
    }
}