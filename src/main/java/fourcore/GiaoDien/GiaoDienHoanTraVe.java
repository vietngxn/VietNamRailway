package fourcore.GiaoDien;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import fourcore.Control.HoanTraVeControl;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.Tau;
import fourcore.Entity.Ve;
import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.Tau_DAO;
import fourcore.dao.VeDAO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GiaoDienHoanTraVe extends Application {

	private VBox menuList;
	private VBox noiDungChinh;
	private ImageView logoImgView;
	Class<?> clazz = this.getClass();
	private Label lblHoanVe;
	private Pane pnlHoanVe;
	private HBox pnlTimKiem;
	private VBox pnlDataHoanTraVe;
	private ScrollPane scrollPane;
	private GridPane tableCol;
	private HBox pnlCapNhatVe;
	private Button btnHoanVe;
	private Button btnTimKiemTheoMaVe;
	private Button btnTimKiemTheoNguoiMua;
	private VeDAO dao;
	private ArrayList<Ve> list;
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	int selected = 0;
	private HBox pnlTongConglbl;
	private Label lblTongCong;
	private Label lblTongCongValue = new Label();

	Map<Ve, Double> listVeThanhToan = new HashMap<>();

	private ChuyenTauDAO ctDAO;
	private Tau_DAO tDao;
	private Tau t;
	private ChuyenTau ct;

	private VBox layoutTimKiem;
	private HBox layoutLblTimkiem;
	private Label lblTimKiem;
	private VBox layoutTxtTimKiem;
	private TextField txtTimKiem;
	private StackPane pnlImgRefesh;
	private static double tongCongPhiHoanTra;

	public void loadLableTongCongValue(double tongCongThanhTien) {
		lblTongCongValue.setText(nf.format(tongCongThanhTien));
	}

	public VBox taoDataChoTableHoanVe(String mave, String chuyen, String gaDiGaDen, String trangThai, String vitrighe,
			String LoaiHoaDon, String ngayMua, String hoten, String doituong, String sogiayto, double giave,
			String giamdoituong, String khuyenmai, double phiHoanTra, double thanhtien) {

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
		Label[] labels = { new Label(mave), new Label(LoaiHoaDon), new Label(chuyen), new Label(gaDiGaDen),
				new Label(trangThai), new Label(vitrighe), new Label(ngayMua) };
		double[] widths = { 100, 270, 120, 250, 250, 220, 200 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);

			if (i == 6) { // trạng thái
				String text = lbl.getText().toLowerCase();
				if (text.equals("hoạt động"))
					lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: #009D75;");
				else if (text.equals("đã hoàn trả") || text.equals("kết thúc") || text.equals("đã được đổi"))
					lbl.setStyle(baseStyle + "-fx-font-size: 18px; -fx-text-fill: rgba(203, 0, 44, 0.83);");
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

		pnlsubCT1.addRow(0, taoSubCT1("Họ tên", hoten, leftStyle, rightStyle));
		pnlsubCT1.addRow(1, taoSubCT1("Đối tượng", doituong, leftStyle, rightStyle));
		pnlsubCT1.addRow(2, taoSubCT1("Số giấy tờ", sogiayto, leftStyle, rightStyle));

		// Các panel giá trị
		String lblCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 18px;";
		String lblValueCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 30px;";

		VBox pnlsubCT2 = taoSubCT2("Giá vé", nf.format(giave), lblCTStyle, lblValueCTStyle);
//		VBox pnlsubCT3 = taoSubCT2("Giảm đối tượng", giamdoituong, lblCTStyle, lblValueCTStyle);
//		VBox pnlsubCT4 = taoSubCT2("Khuyến mãi", khuyenmai, lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT5 = taoSubCT2("Phí hoàn trả", nf.format(phiHoanTra), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT6 = taoSubCT2("Thành tiền", nf.format(thanhtien), lblCTStyle, lblValueCTStyle);

		pnlsubCT1.setPrefWidth(400);
		for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT5, pnlsubCT6 })
			pnl.setPrefWidth(200);

		pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT5, pnlsubCT6);
		pnlReturn.getChildren().add(pnlThongTinChiTiet);

		pnlThongTinChiTiet.setManaged(false);
		pnlThongTinChiTiet.setVisible(false);

		AtomicBoolean isSelected = new AtomicBoolean(false);

		pnlReturn.setOnMouseClicked(event -> {
			boolean check = pnlThongTinChiTiet.isVisible();
			pnlThongTinChiTiet.setManaged(!check);
			pnlThongTinChiTiet.setVisible(!check);

			if (!isSelected.get()) {
				data.setStyle(hoverStyle);
				ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
				scaleUp.setToX(1.02);
				scaleUp.setToY(1.02);
				scaleUp.play();
				isSelected.set(true);
				tongCongPhiHoanTra += thanhtien;
				loadLableTongCongValue(tongCongPhiHoanTra);
				System.out.println(mave + " " + isSelected);
				Ve v;
				try {
					v = dao.getVeBangMaVe(mave);
					listVeThanhToan.put(v, thanhtien);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				data.setStyle(normalStyle);
				ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
				scaleDown.setToX(1);
				scaleDown.setToY(1);
				scaleDown.play();
				isSelected.set(false);
				tongCongPhiHoanTra -= thanhtien;
				loadLableTongCongValue(tongCongPhiHoanTra);
				System.out.println(mave + " " + isSelected);
				Ve v;
				try {
					v = dao.getVeBangMaVe(mave);
					listVeThanhToan.remove(v);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		Label lblValue = new Label(value);
		lblValue.setStyle(valueStyle);
		VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
		box.setAlignment(Pos.CENTER);
		return box;

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1300);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));

			// title
			pnlHoanVe = new Pane();
			lblHoanVe = new Label("Hoàn trả vé");
			lblHoanVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			pnlHoanVe.getChildren().add(lblHoanVe);
			VBox.setMargin(pnlHoanVe, new Insets(20, 0, 0, 50));
			noiDungChinh.getChildren().add(pnlHoanVe);

			// thanh tim kiem
			layoutTimKiem = new VBox();
			layoutLblTimkiem = new HBox();
			layoutLblTimkiem.setPrefSize(1200, 40);
			lblTimKiem = new Label("Nhập mã vé hoặc số giấy tờ người mua trên hóa đơn");
			lblTimKiem.setTranslateX(10);
			lblTimKiem.setTranslateY(0);
			lblTimKiem.setStyle(
					"-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");
			ImageView imgTimKiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
			imgTimKiem.setTranslateX(1050);
			imgTimKiem.setFitHeight(25);
			imgTimKiem.setFitWidth(25);

			layoutLblTimkiem.getChildren().addAll(lblTimKiem, imgTimKiem);
			layoutLblTimkiem.setTranslateY(48);
			layoutTimKiem.getChildren().add(layoutLblTimkiem);

			layoutTxtTimKiem = new VBox();
			txtTimKiem = new TextField();
			txtTimKiem.setPrefHeight(40);
			txtTimKiem.setMaxSize(1200, 45);
			txtTimKiem.setPadding(new Insets(10));
			txtTimKiem.setStyle(
					"-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
			txtTimKiem.setFocusTraversable(false);
			layoutTxtTimKiem.getChildren().add(txtTimKiem);
			layoutTimKiem.getChildren().add(layoutTxtTimKiem);

			layoutTimKiem.setTranslateX(100);

			txtTimKiem.focusedProperty().addListener((obs, oval, nval) -> {
				TranslateTransition tt = new TranslateTransition(Duration.millis(350), lblTimKiem);
				if (nval) {
					tt.setToY(-40);
				}
				tt.play();
			});

			VBox.setMargin(layoutTimKiem, new Insets(0, 0, 10, 0));
			noiDungChinh.getChildren().add(layoutTimKiem);

			// button tim kiem theo ma ve va so giay to nguoi mua
			String btnStyle = """
					      -fx-font-family: 'Inter';
					   -fx-font-weight: bold;
					   -fx-font-size: 22px;
					   -fx-text-fill: white;
					   -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
					   -fx-background-radius: 10;
					-fx-cursor: hand;
					-fx-padding: 10 20 10 20;
					""";
			btnTimKiemTheoMaVe = new Button("Tìm kiếm theo mã vé");
			btnTimKiemTheoMaVe.setMaxSize(350, 320);
			btnTimKiemTheoMaVe.setStyle(btnStyle);

			btnTimKiemTheoNguoiMua = new Button("Tìm kiếm theo người mua");
			btnTimKiemTheoNguoiMua.setMaxSize(350, 320);
			btnTimKiemTheoNguoiMua.setStyle(btnStyle);

			pnlTimKiem = new HBox(150);
			pnlTimKiem.setMaxSize(1200, 500);

			// Button refesh list
			pnlImgRefesh = new StackPane();
			ImageView imgRefesh = new ImageView(getClass().getResource("/images/refesh_icon.png").toExternalForm());
			imgRefesh.setFitWidth(50);
			imgRefesh.setFitHeight(50);
			pnlImgRefesh.getChildren().add(imgRefesh);
			pnlImgRefesh.setPrefSize(50, 50);
			pnlTimKiem.getChildren().addAll(btnTimKiemTheoMaVe, btnTimKiemTheoNguoiMua, pnlImgRefesh);
			pnlTimKiem.setAlignment(Pos.CENTER);

			btnTimKiemTheoMaVe.setAlignment(Pos.CENTER);
			btnTimKiemTheoNguoiMua.setAlignment(Pos.CENTER);

			VBox.setMargin(pnlTimKiem, new Insets(30, 0, 0, 0));
			noiDungChinh.getChildren().add(pnlTimKiem);

			// table col
			tableCol = new GridPane();
			tableCol.setHgap(10);
			tableCol.setVgap(20);
			tableCol.setAlignment(Pos.CENTER);
			tableCol.setMaxWidth(1330);
			VBox.setMargin(tableCol, new Insets(30, 10, 10, 0));

			String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 22px; -fx-font-weight: bold;";
			String[] tenCot = { "Mã vé", "Loại vé", "Chuyến", "Ga đi - Ga đến", "Ngày khởi hành", "Vị trí ghế",
					"Trạng thái" };
			double[] sizeCot = { 200, 200, 180, 250, 270, 220, 220 };

			for (int i = 0; i < tenCot.length; i++) {
				Label lbl = new Label(tenCot[i]);
				lbl.setStyle(styleHeader);
				StackPane pnl = new StackPane(lbl);
				pnl.setPrefWidth(sizeCot[i]);
				pnl.setAlignment(Pos.CENTER);
				tableCol.add(pnl, i, 0);
				System.out.println(i);
			}

			noiDungChinh.getChildren().add(tableCol);

			// table row
			pnlDataHoanTraVe = new VBox(10);
			pnlDataHoanTraVe.setAlignment(Pos.CENTER);
			pnlDataHoanTraVe.getChildren().clear();
			pnlDataHoanTraVe = loadDuLieuLenTable();

			// === TẠO SCROLLPANE ===
			scrollPane = new ScrollPane(pnlDataHoanTraVe);
			scrollPane.setMaxHeight(500);
			scrollPane.setFitToWidth(true);
			scrollPane.setPannable(true);
			scrollPane.setStyle("""
					    -fx-background-color: transparent;
					    -fx-border-color: transparent;
					    -fx-border-width: 0;
					""");

			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
			noiDungChinh.getChildren().add(scrollPane);

			// label tong cong
			pnlTongConglbl = new HBox();
			String lblStyle = "-fx-font-size: 36px;" + "-fx-font-weight: bold;";
			lblTongCong = new Label("Tổng cộng tiền phải hoàn trả: ");
			lblTongCongValue = new Label();
			lblTongCong.setStyle(lblStyle);
			lblTongCongValue.setStyle(lblStyle);

			pnlTongConglbl.getChildren().addAll(lblTongCong, lblTongCongValue);
			HBox.setMargin(lblTongCong, new Insets(0, 0, 0, 600));
			noiDungChinh.getChildren().add(pnlTongConglbl);

			pnlCapNhatVe = new HBox(30);
			VBox.setMargin(pnlCapNhatVe, new Insets(30, 0, 0, 500));
			pnlCapNhatVe.setAlignment(Pos.CENTER);

			// button hoanve
			btnHoanVe = new Button("Hoàn vé");
			btnHoanVe.setPrefSize(350, 60);
			btnHoanVe.setStyle(btnStyle);

			pnlCapNhatVe.getChildren().add(btnHoanVe);
			noiDungChinh.getChildren().add(pnlCapNhatVe);

			// su kien tim kiem theo ma ve
			btnTimKiemTheoMaVe.setOnMouseClicked(event -> {
				String regex = "VE\\d+";
				String input = txtTimKiem.getText().trim();
				System.out.println(input);

				if (input.isEmpty() || !Pattern.matches(regex, input)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Mã vé không hợp lệ! (Định dạng hợp lệ: VE123)");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();

				} else {
					try {
						pnlDataHoanTraVe.getChildren().clear();
						Ve x = new Ve();
						x = dao.getHoanVeBangMaVe(input);
						if (x == null) {
							Label thongBaoKhongTimThay = new Label("Không tìm thấy vé!");
							thongBaoKhongTimThay.setStyle(lblStyle);
							String css = " -fx-font-family: 'Inter';" + "-fx-font-weight: bold;"
									+ "-fx-font-size: 20px;"
									+ "-fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);";

							thongBaoKhongTimThay.setStyle(css);
							StackPane tbao = new StackPane(thongBaoKhongTimThay);
							tbao.setAlignment(Pos.CENTER);
							pnlDataHoanTraVe.getChildren().add(tbao);
						}
						ChiTietHoaDonDAO ctDao = new ChiTietHoaDonDAO();
						String tenloai = ctDao.getLoaiHoaDonChoVeTau(x.getMaVeTau());
						ct = ctDAO.getChuyenTauBangMa(x.getChuyenTau().getMaChuyenTau());
						t = tDao.getTauByMaTau(ct.getTau().getMaTau());
						setDuLieu(pnlDataHoanTraVe, x, tenloai);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
			// su kien tim kiem theo so giay to nguoi mua
			btnTimKiemTheoNguoiMua.setOnMouseClicked(event -> {
				String regex = "^\\d+$";
				String input = txtTimKiem.getText().trim();

				if (input.isEmpty() || !Pattern.matches(regex, input)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Số giấy tờ của người mua không hợp lệ");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				} else {
					try {
						pnlDataHoanTraVe.getChildren().clear();
						list.removeAll(list);
						list = dao.getListHoanVeTheoCCCDKhachHang(input);
						if (list == null || list.isEmpty()) {
							Label thongBaoKhongTimThay = new Label("Không tìm thấy vé!");
							thongBaoKhongTimThay.setStyle(lblStyle);
							String css = " -fx-font-family: 'Inter';" + "-fx-font-weight: bold;"
									+ "-fx-font-size: 20px;"
									+ "-fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);";

							thongBaoKhongTimThay.setStyle(css);
							StackPane tbao = new StackPane(thongBaoKhongTimThay);
							tbao.setAlignment(Pos.CENTER);
							pnlDataHoanTraVe.getChildren().add(tbao);
						}
						ctDAO = new ChuyenTauDAO();
						tDao = new Tau_DAO();
						for (Ve x : list) {
							ChiTietHoaDonDAO ctDao = new ChiTietHoaDonDAO();
							String tenloai = ctDao.getLoaiHoaDonChoVeTau(x.getMaVeTau());
							ct = ctDAO.getChuyenTauBangMa(x.getChuyenTau().getMaChuyenTau());
							t = tDao.getTauByMaTau(ct.getTau().getMaTau());
							setDuLieu(pnlDataHoanTraVe, x, tenloai);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});

			// su kien load lai danh sach
			pnlImgRefesh.setOnMouseClicked(event -> {
				try {
					dao = new VeDAO(0);
					int soLuongVeCapNhat = dao.CapNhatTrangThaiVe(list);
					System.out.println(soLuongVeCapNhat);
					if (soLuongVeCapNhat > 0) {
						pnlDataHoanTraVe.getChildren().clear();
						list.removeAll(list);
						listVeThanhToan.clear();
						lblTongCongValue.setText("0");
						tongCongPhiHoanTra = 0;
						pnlDataHoanTraVe.getChildren().add(loadDuLieuLenTable());
					} else {
						pnlDataHoanTraVe.getChildren().clear();
						list.removeAll(list);
						listVeThanhToan.clear();
						lblTongCongValue.setText("0");
						tongCongPhiHoanTra = 0;
						pnlDataHoanTraVe.getChildren().add(loadDuLieuLenTable());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		// cac hieu ung hover cho cac button
		hieuUngHover(btnHoanVe);
		hieuUngHover(btnTimKiemTheoMaVe);
		hieuUngHover(btnTimKiemTheoNguoiMua);
		hieuUngHoverPnl(pnlImgRefesh);

	}

	public Button traVeNutHoanVe() {
		return this.btnHoanVe;
	}

	public Map<Ve, Double> traVeListVeThanhToan() {
		return this.listVeThanhToan;
	}

	public void hieuUngHover(Button btn) {
		btn.setOnMouseEntered(e -> {
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), btn);
			scaleUp.setToX(1.1);
			scaleUp.setToY(1.1);
			scaleUp.play();
		});

		btn.setOnMouseExited(e -> {
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), btn);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});
	}

	public void hieuUngHoverPnl(StackPane pnl) {
		pnl.setOnMouseEntered(e -> {
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), pnl);
			scaleUp.setToX(1.1);
			scaleUp.setToY(1.1);
			scaleUp.play();
		});

		pnl.setOnMouseExited(e -> {
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), pnl);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});
	}

	public void setDuLieu(VBox pnlDataHoanTraVe, Ve x, String tenloai) {
		pnlDataHoanTraVe.getChildren().add(taoDataChoTableHoanVe(x.getMaVeTau(), t.getLoaiTau().getTenLoaiTau(),
				x.getGaDi() + " - " + x.getGaDen(),
				format.format(x.getNgayGioDi().toLocalDate()) + " - " + x.getNgayGioDi().toLocalTime(),
				"Toa số " + x.getSoToa() + " chỗ " + x.getSoGhe(), tenloai, x.getTrangThaiVe(),
				x.getKhachHang().getHoten(), x.getDoiTuongGiamGia().getTenDoiTuongGiamGia(), x.getKhachHang().getCccd(),
				x.getGiaVe(), nf.format(x.getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()) + "%",
				nf.format(x.getKhuyenMai().getGiaTriPhanTramKhuyenMai()) + "%",
				x.tinhPhiHoanTra(x.getNgayGioDi(), x.getGiaVe(), tenloai),
				x.tinhThanhTienThanhToanHoanTra(x.tinhPhiHoanTra(x.getNgayGioDi(), x.getGiaVe(), tenloai))));
	}

	public VBox loadDuLieuLenTable() throws SQLException {
		lblTongCongValue.setText("0");
		dao = new VeDAO(0);
		list = dao.getListVe();
		VBox box = new VBox(10);
		ChiTietHoaDonDAO ctDao = new ChiTietHoaDonDAO();
		ctDAO = new ChuyenTauDAO();
		tDao = new Tau_DAO();
		for (Ve x : list) {
			String tenloai = ctDao.getLoaiHoaDonChoVeTau(x.getMaVeTau());
			System.out.println(tenloai);
			ct = ctDAO.getChuyenTauBangMa(x.getChuyenTau().getMaChuyenTau());
			t = tDao.getTauByMaTau(ct.getTau().getMaTau());
			box.getChildren().add(taoDataChoTableHoanVe(x.getMaVeTau(), t.getLoaiTau().getTenLoaiTau(),
					x.getGaDi() + " - " + x.getGaDen(),
					format.format(x.getNgayGioDi().toLocalDate()) + " - " + x.getNgayGioDi().toLocalTime(),
					"Toa số " + x.getSoToa() + " chỗ " + x.getSoGhe(), tenloai, x.getTrangThaiVe(),
					x.getKhachHang().getHoten(), x.getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
					x.getKhachHang().getCccd(), x.getGiaVe(),
					nf.format(x.getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()) + "%",
					nf.format(x.getKhuyenMai().getGiaTriPhanTramKhuyenMai()) + "%",
					x.tinhPhiHoanTra(x.getNgayGioDi(), x.getGiaVe(), tenloai),
					x.tinhThanhTienThanhToanHoanTra(x.tinhPhiHoanTra(x.getNgayGioDi(), x.getGiaVe(), tenloai))));
		}
		return box;
	}

	public VBox getNoiDungChinhVe() {
		return this.noiDungChinh;
	}

	public static void main(String[] args) {
		Application.launch(GiaoDienHoanTraVe.class, args);
	}
}