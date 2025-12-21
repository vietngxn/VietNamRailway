package fourcore.GiaoDien;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

import fourcore.Entity.ChiTietHoaDon;
import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.HoaDon;
import fourcore.Entity.KhachHang;
import fourcore.Entity.NhanVien;
import fourcore.Entity.Ve;
import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.DoiTuongGiamGiaDAO;
import fourcore.dao.DoiTuongGiamGia_DAO;
import fourcore.dao.HoaDonDAO;
import fourcore.dao.NhanVienDAO;
import fourcore.dao.VeDAO;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class QuanLiHoaDon extends Application {
	private VBox noiDungChinh;
	Class<?> clazz = this.getClass();
	private VBox title_layout;
	private Label lbl_title;
	private Label lbl_timkiem;
	private TextField txt_timkiem;
	private HBox layout_lbl_timkiem;
	private VBox layout_txt_timkiem;
	private VBox layout_timkiem;
	private VBox table_layout;
	private Label lbl_title_maHoaDon;
	private Label lbl_title_nguoiMua;
	private Node lbl_title_ngayLap;
	private Node lbl_title_loaiHoaDon;
	private VBox table_desc;
	private ScrollPane scrollPane;
	private GridPane hangchon = null;
	private HBox layout_button;
	private Button btn_xoaChuyenTau;
//	private Button btn_xuatHoaDon;
	private Button btn_xemChiTiet;
	private Node lbl_title_tongTien;
	private HoaDonDAO hddao;
	private VeDAO vedao;
	private ArrayList<HoaDon> listhd;
	private Node lbl_title_soDienThoai;
	private Button btn_timkiem;
	private EventObject event;
	private Label lblTitle;

	DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private GridPane gridCol;
	private StackPane pnlTitle;
	private VBox tableDesc;
	private NumberFormat nft = NumberFormat.getInstance(new Locale("vi", "VN"));
	private Label lblTongCong;
	private Button btnInHoaDon;

	@Override
	public void start(Stage primaryStage) {
		try {
			hddao = new HoaDonDAO();
			listhd = hddao.getListHoaDon();
			vedao = new VeDAO();
			// Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			create_title_layout();

			create_table_layout();

			create_layout_button();
			primaryStage.setFullScreen(true);
//			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create_title_layout() {
		title_layout = new VBox();
		title_layout.setPadding(new Insets(30));
		title_layout.setSpacing(20);

		lbl_title = new Label("Quản Lí Hóa Đơn");
		lbl_title.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:30px;");
		title_layout.getChildren().add(lbl_title);

		layout_timkiem = new VBox();

		layout_lbl_timkiem = new HBox();
		layout_lbl_timkiem.setPrefSize(740, 40);
		lbl_timkiem = new Label("Nhập mã hóa đơn");
		lbl_timkiem.setTranslateX(10);
		lbl_timkiem.setTranslateY(0);
		lbl_timkiem
				.setStyle("-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");

		ImageView img_timkiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
		img_timkiem.setTranslateX(550);
		img_timkiem.setFitHeight(25);
		img_timkiem.setFitWidth(25);
		layout_lbl_timkiem.getChildren().addAll(lbl_timkiem, img_timkiem);
		layout_lbl_timkiem.setTranslateY(48);
		layout_timkiem.getChildren().add(layout_lbl_timkiem);

		layout_txt_timkiem = new VBox();
		txt_timkiem = new TextField();
		txt_timkiem.setPrefHeight(40);
		txt_timkiem.setMaxSize(750, 45);
		txt_timkiem.setPadding(new Insets(10));
		txt_timkiem.setStyle(
				"-fx-background-color: transparent;-fx-border-color: #00BACB;-fx-border-width: 0.5;-fx-border-radius: 15px;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-text-fill : #00BACB;-fx-font-size:15px;");
		txt_timkiem.setFocusTraversable(false);
		layout_txt_timkiem.getChildren().add(txt_timkiem);
		layout_timkiem.getChildren().add(layout_txt_timkiem);

		layout_timkiem.setTranslateX(100);

		HBox layout_btn_timkiem = new HBox();
		layout_btn_timkiem.setSpacing(10);
		btn_timkiem = new Button("Tìm Kiếm");
		btn_timkiem.setPrefSize(150, 50);
		btn_timkiem.setMaxSize(150, 50);
		btn_timkiem.setTranslateX(250);
		btn_timkiem.setTranslateY(20);
		btn_timkiem.setStyle(
				"-fx-background-color: #00BACB;-fx-text-fill: white;-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:14px;-fx-cursor: hand;-fx-border-radius: 30px;-fx-background-radius:30px;");

		btn_timkiem.setOnAction(e -> {
			String regex = "^HD\\d+$";
			String mahd = txt_timkiem.getText();

			if (mahd.equalsIgnoreCase("")) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Vui Lòng nhập mã hóa đơn");
				alert.setHeaderText(null);
				alert.showAndWait();
			} else if (!mahd.matches(regex)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Mã hóa đơn sai định dạng!Vui lòng nhập theo định dạng: HDXX");
				alert.setHeaderText(null);
				alert.showAndWait();
			} else {
				try {
					HoaDon hd = hddao.getHoaDonTheoMa(mahd);
					if (hd != null) {
						table_desc.getChildren().clear();
						create_layout_dong(hd.getMaHoaDon(), hd.getTenKhachHangThanhToan(),
								hd.getMaLoaiHoaDon().getTenLoaiHoaDon(), hd.getNgayThanhToan(), hd.getTongTien());
					} else {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Không tìm thấy Hóa Đơn phù hợp!");
						alert.setHeaderText(null);
						alert.showAndWait();
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		layout_btn_timkiem.getChildren().add(btn_timkiem);
		layout_timkiem.getChildren().add(layout_btn_timkiem);

		title_layout.getChildren().add(layout_timkiem);

		txt_timkiem.focusedProperty().addListener((obs, oval, nval) -> {
			TranslateTransition tt = new TranslateTransition(Duration.millis(350), lbl_timkiem);
			if (nval) {
				tt.setToY(-40);
			} else {
				if (txt_timkiem.getText().isEmpty()) {
					tt.setToY(0);
				} else {
					tt.setToY(-40);
				}
			}
			tt.play();
		});

		noiDungChinh.getChildren().add(title_layout);
	}

	public void create_table_layout() throws SQLException {
		table_layout = new VBox();
		table_layout.setPadding(new Insets(10));

		GridPane tableCol = new GridPane();
		tableCol.setHgap(10);
		tableCol.setVgap(20);
		tableCol.setAlignment(Pos.CENTER);
		tableCol.setMaxWidth(1330);

		table_layout.setPrefSize(1350, 730);
		table_layout.setTranslateX(10);
		table_layout.setMaxSize(1350, 730);

		String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 18px; -fx-font-weight: bold;";

		lbl_title_maHoaDon = new Label("Mã Hóa Đơn");
		lbl_title_maHoaDon.setStyle(styleHeader);

		lbl_title_nguoiMua = new Label("Người Mua");
		lbl_title_nguoiMua.setStyle(styleHeader);

		lbl_title_soDienThoai = new Label("Số Điện Thoại");
		lbl_title_soDienThoai.setStyle(styleHeader);

		lbl_title_ngayLap = new Label("Ngày Lập");
		lbl_title_ngayLap.setStyle(styleHeader);

		lbl_title_tongTien = new Label("Tổng Tiền");
		lbl_title_tongTien.setStyle(styleHeader);

		StackPane paneCol1 = new StackPane(lbl_title_maHoaDon);
		StackPane paneCol2 = new StackPane(lbl_title_nguoiMua);
		StackPane paneCol3 = new StackPane(lbl_title_soDienThoai);
		StackPane paneCol4 = new StackPane(lbl_title_ngayLap);
		StackPane paneCol5 = new StackPane(lbl_title_tongTien);

		lbl_title_maHoaDon.setTranslateX(-200);
		lbl_title_nguoiMua.setTranslateX(-200);
//	    lbl_title_ngayLap.setTranslateX(-150);
		lbl_title_soDienThoai.setTranslateX(-100);
		lbl_title_tongTien.setTranslateX(150);

		paneCol1.setPrefWidth(180);
		paneCol2.setPrefWidth(180);
		paneCol3.setPrefWidth(180);
		paneCol4.setPrefWidth(180);
		paneCol5.setPrefWidth(180);

		paneCol1.setAlignment(Pos.CENTER);
		paneCol2.setAlignment(Pos.CENTER);
		paneCol3.setAlignment(Pos.CENTER);
		paneCol4.setAlignment(Pos.CENTER);
		paneCol5.setAlignment(Pos.CENTER);

		tableCol.add(paneCol1, 0, 0);
		tableCol.add(paneCol2, 1, 0);
		tableCol.add(paneCol3, 2, 0);
		tableCol.add(paneCol4, 3, 0);
		tableCol.add(paneCol5, 4, 0);

		table_layout.getChildren().add(tableCol);

		table_desc = new VBox();
		table_desc.setSpacing(20);

		for (HoaDon hd : listhd) {
			create_layout_dong(hd.getMaHoaDon(), hd.getTenKhachHangThanhToan(), hd.getSdtKhachHangThanhToan(),
					hd.getNgayThanhToan(), hd.getTongTien());

		}

		scrollPane = new ScrollPane();
		scrollPane.setContent(table_desc);
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setStyle("-fx-background-color: transparent");
		scrollPane.setPannable(true);

		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		scrollPane.setMaxHeight(660);

		table_layout.getChildren().add(scrollPane);

		noiDungChinh.getChildren().add(table_layout);
	}

	public void create_layout_button() {
		layout_button = new HBox();

		layout_button.setPrefSize(950, 60);
		layout_button.setAlignment(Pos.CENTER_RIGHT);
		layout_button.setTranslateX(-70);
		String style = "-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size:13.5px;-fx-text-fill:white;-fx-background-radius: 20px;";

		btn_xemChiTiet = new Button("Xem Chi Tiết");
		btn_xemChiTiet.setStyle(style + "-fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		btn_xemChiTiet.setPrefSize(225, 60);

		layout_button.setSpacing(40);
		layout_button.getChildren().addAll(btn_xemChiTiet);

		btn_xemChiTiet.hoverProperty().addListener((obs, oval, nval) -> {
			if (nval) {
				btn_xemChiTiet.setStyle(btn_xemChiTiet.getStyle() + "-fx-cursor:hand;");
			} else {
				btn_xemChiTiet.setStyle(btn_xemChiTiet.getStyle());
			}
		});

		btn_xemChiTiet.setOnMouseClicked(event -> {
			if (hangchon.getChildren().isEmpty()) {
				System.out.println("chua chon hoa don!!!!!!!!!!!!!!!!!!!!!!!");
			} else {
				for (Node node : hangchon.getChildren()) {

					if (node instanceof StackPane) {
						StackPane pane = (StackPane) node;

						for (Node child : pane.getChildren()) {
							if (child instanceof Label && "maHoaDon".equals(child.getId())) {

								Label lbl = (Label) child;
								System.out.println(lbl.getText());
								String maHoaDon = lbl.getText();
								try {
									moPopupChiTietHoaDon(maHoaDon, btn_xemChiTiet);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								return;

							}
						}
					}
				}
			}
		});

		noiDungChinh.getChildren().add(layout_button);

	}

	private HBox taoRowThongTin(String title, String value) {
		Label lblTitle = new Label(title + ": ");
		lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

		Label lblValue = new Label(value != null ? value : "");
		lblValue.setStyle("-fx-font-weight: normal; -fx-font-size: 20px;");

		HBox row = new HBox(5);
		row.setAlignment(Pos.CENTER_LEFT);
		row.getChildren().addAll(lblTitle, lblValue);

		return row;
	}

	private void moPopupChiTietHoaDon(String maHoaDon, Node sourceNode) throws SQLException {

		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(30));

		HoaDonDAO hdDao = new HoaDonDAO();
		HoaDon hd = hdDao.getHoaDonByMaHoaDon(maHoaDon);
		ArrayList<ChiTietHoaDon> ctList = new ArrayList<ChiTietHoaDon>();
		ChiTietHoaDonDAO cthdDao = new ChiTietHoaDonDAO();
		ctList = cthdDao.getHD(maHoaDon);
		NhanVienDAO nvDAo = new NhanVienDAO();
		NhanVien nv = nvDAo.getNhanVienByMa(hd.getMaNhanVien().getMaNhanVien());

		lblTitle = new Label();
		lblTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
		pnlTitle = new StackPane();
		pnlTitle.getChildren().add(lblTitle);
		StackPane.setAlignment(lblTitle, Pos.CENTER);
		root.getChildren().add(pnlTitle);

		String col[] = null;
		double[] colWidth = null;
		if ("LHD01".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {
			lblTitle.setText("Hóa đơn bán vé");
			col = new String[] { "STT", "Mã vé", "Tên loại ghế", "Đối tượng", "Đơn giá", "Thành tiền" };
			colWidth = new double[] { 80, 150, 220, 160, 160, 200 };
		} else if ("LHD02".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {
			lblTitle.setText("Hóa đơn hoàn trả vé");
			col = new String[] { "STT", "Mã vé", "Tên loại ghế", "Đơn giá", "Phí hoàn trả", "Thành tiền" };
			colWidth = new double[] { 80, 150, 220, 160, 180, 200 };
		} else if ("LHD03".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {
			lblTitle.setText("Hóa đơn đổi vé");
			col = new String[] { "STT", "Mã vé", "Tên loại ghế", "Đơn giá", "Phí đổi trả", "Thành tiền" };
			colWidth = new double[] { 80, 150, 220, 160, 180, 200 };
		}
		HBox rowMaHD = taoRowThongTin("Mã hóa đơn", maHoaDon);
		HBox rowDonViBan = taoRowThongTin("Đơn vị bán", "Tổng công ty đường sắt Việt Nam");

		HBox rowDiaChiNhaGa = taoRowThongTin("Địa chỉ",
				"QMJH+RF6, Nguyễn Thông, Phường 9, Quận 3, Thành phố Hồ Chí Minh");

		HBox rowNgayLapHoaDon = taoRowThongTin("Ngày thanh toán", dft.format(hd.getNgayThanhToan()));

		HBox rowNguoiMua = taoRowThongTin("Tên người mua", hd.getTenKhachHangThanhToan());

		HBox rowDienThoai = taoRowThongTin("Số điện thoại người mua", hd.getSdtKhachHangThanhToan());

		HBox rowCccd = taoRowThongTin("Số giấy tờ người mua", hd.getCccdKhachHangThanhToan());

		HBox rowDiaChiNguoiMua = taoRowThongTin("Địa chỉ người mua", hd.getDiaChiKhachHangThanhToan());

		HBox rowTenNhanVien = taoRowThongTin("Tên nhân viên", nv.getHoTen());

		HBox rowMaNhanVien = taoRowThongTin("Mã nhân viên thanh toán", nv.getMaNhanVien());

		VBox pnlThongTinHoaDon = new VBox(8);
		pnlThongTinHoaDon.setPadding(new Insets(10, 20, 10, 20));
		pnlThongTinHoaDon.setAlignment(Pos.CENTER_LEFT);

		pnlThongTinHoaDon.getChildren().addAll(rowMaHD, rowDonViBan, rowDiaChiNhaGa, rowNgayLapHoaDon, rowNguoiMua);

		HBox pnlThongTinKhachHang = new HBox(70);
		pnlThongTinKhachHang.getChildren().addAll(rowDienThoai, rowCccd);
		pnlThongTinHoaDon.getChildren().add(pnlThongTinKhachHang);

		pnlThongTinHoaDon.getChildren().add(rowDiaChiNguoiMua);

		HBox pnlThongTinNhanVien = new HBox(70);
		pnlThongTinNhanVien.getChildren().addAll(rowTenNhanVien, rowMaNhanVien);
		pnlThongTinHoaDon.getChildren().add(pnlThongTinNhanVien);

		root.getChildren().add(pnlThongTinHoaDon);
		gridCol = new GridPane();
		for (int i = 0; i < col.length; i++) {
			Label lbl = new Label(col[i]);
			lbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
			lbl.setPrefWidth(colWidth[i]);
			lbl.setAlignment(Pos.CENTER);

			StackPane pane = new StackPane(lbl);
			pane.setPrefWidth(colWidth[i] + 100);
			pane.setPrefHeight(60);

			gridCol.add(pane, i, 0);
		}

		root.getChildren().add(gridCol);

		tableDesc = new VBox(10);
		tableDesc.setPadding(new Insets(6, 0, 6, 0));

		ScrollPane scrPane = new ScrollPane(tableDesc);

		scrPane.setPrefHeight(400);
		scrPane.setMaxHeight(400);

		scrPane.setFitToWidth(true);
		scrPane.setPannable(true);

		scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		VBox.setVgrow(scrPane, Priority.NEVER);
		root.getChildren().add(scrPane);

		int cnt = 1;
		VeDAO veDao = new VeDAO();
		DoiTuongGiamGia_DAO dtDao = new DoiTuongGiamGia_DAO();
		double tongCong = 0;
		for (ChiTietHoaDon ct : ctList) {
			GridPane row = new GridPane();
			row.setHgap(100);
			row.setAlignment(Pos.CENTER);
			row.setPrefHeight(60);
			row.setStyle("""
					    -fx-background-color:#00BACB;
					    -fx-background-radius:15px;
					""");
			Ve v = veDao.getVeBangMaVe(ct.getVeTau().getMaVeTau());
			DoiTuongGiamGia dt = dtDao.getDoiTuongGiamGiaBangMaDT(v.getDoiTuongGiamGia().getMaDoiTuongGiamGia());

			String[] values = null;

			if ("LHD01".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {
				values = new String[] { String.valueOf(cnt++), ct.getVeTau().getMaVeTau(), v.getLoaiVe(),
						dt.getTenDoiTuongGiamGia(), nft.format(v.getGiaVe()), nft.format(v.getGiaVe()) };
				tongCong += v.getGiaVe();
			} else if ("LHD02".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {
				String loaiHd = cthdDao.getLoaiHoaDonChoVeTau(v.getMaVeTau());
				double phiHoan = v.tinhPhiHoanTra(v.getNgayGioDi(), v.getGiaVe(), loaiHd);
				values = new String[] { String.valueOf(cnt++), ct.getVeTau().getMaVeTau(), v.getLoaiVe(),
						nft.format(v.getGiaVe()), "-" + nft.format(phiHoan), nft.format(v.getGiaVe() - phiHoan) };

				tongCong += (v.getGiaVe() - phiHoan) * -1;

			} else if ("LHD03".equalsIgnoreCase(hd.getMaLoaiHoaDon().getMaLoaiHoaDon())) {

				double phiHoan = v.tinhPhiHoanTra(hd.getNgayThanhToan(), v.getGiaVe(), ct.getLoaiHoaDonChoVe());
				values = new String[] { String.valueOf(cnt++), ct.getVeTau().getMaVeTau(), v.getLoaiVe(),
						nft.format(v.getGiaVe()), nft.format(phiHoan), nft.format(v.getGiaVe() - phiHoan) };

				tongCong += v.getGiaVe();
			}

			for (int i = 0; i < values.length; i++) {
				Label lbl = new Label(values[i]);
				lbl.setStyle("-fx-font-weight:bold;");
				lbl.setPrefWidth(100);
				lbl.setAlignment(Pos.CENTER);
				lbl.setWrapText(i == 2 || i == 3);

				StackPane pane = new StackPane(lbl);
				pane.setPrefWidth(colWidth[i] - 50);

				row.add(pane, i, 0);
			}
			tableDesc.getChildren().add(row);
		}

		lblTongCong = new Label("Tổng cộng: " + nft.format(tongCong));
		lblTongCong.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		root.getChildren().add(lblTongCong);
		VBox.setMargin(lblTongCong, new Insets(20, 0, 0, 1000));
		lblTongCong.setAlignment(Pos.CENTER);

		btnInHoaDon = new Button("In hóa đơn");
		btnInHoaDon.setStyle(
				"-fx-font-family: 'Inter';-fx-font-weight: bold;-fx-font-size:13.5px;-fx-text-fill:white;-fx-background-radius: 20px; -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);");
		root.getChildren().add(btnInHoaDon);
		btnInHoaDon.setPrefSize(200, 200);
		VBox.setMargin(btnInHoaDon, new Insets(20, 0, 0, 1000));
		Scene scene = new Scene(root);
		Stage popupStage = new Stage();

		popupStage.setTitle("Chi tiết hóa đơn");
		popupStage.setScene(scene);
		Stage parentStage = (Stage) sourceNode.getScene().getWindow();
		popupStage.initOwner(parentStage);
		popupStage.initModality(Modality.WINDOW_MODAL);
		popupStage.setWidth(1400);
		popupStage.setHeight(700);
		popupStage.showAndWait();

	}

	public void create_layout_dong(String mahoadon, String nguoimua, String loaiHD, LocalDateTime ngaylap,
			double tongtien) {
		GridPane data = new GridPane();

		data.setHgap(10);
		data.setTranslateX(20);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1250);
		data.setTranslateX(20);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";

		Label lblMaHoaDon = new Label(mahoadon);
		lblMaHoaDon.setId("maHoaDon");
		Label lblNguoiMua = new Label(nguoimua);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String ngaylap1 = ngaylap.format(dtf);
		Label lblNgayLap = new Label(ngaylap1);

		Label lblsoDienThoai = new Label(loaiHD);

		DecimalFormat df = new DecimalFormat("#,###");
		String tf = df.format(tongtien);
		Label lblTongTien = new Label(tf + " đ");

		lblMaHoaDon.setStyle(baseStyle);
		lblNguoiMua.setStyle(baseStyle);
		lblNgayLap.setStyle(baseStyle);
		lblsoDienThoai.setStyle(baseStyle);
		lblTongTien.setStyle(baseStyle);

		lblMaHoaDon.setTranslateX(-190);
		lblNguoiMua.setTranslateX(-190);
		lblNgayLap.setTranslateX(15);
		lblsoDienThoai.setTranslateX(-90);
		lblTongTien.setTranslateX(165);

		lblNguoiMua.setWrapText(true);

		StackPane paneData1 = new StackPane(lblMaHoaDon);
		StackPane paneData2 = new StackPane(lblNguoiMua);
		StackPane paneData3 = new StackPane(lblsoDienThoai);
		StackPane paneData4 = new StackPane(lblNgayLap);
		StackPane paneData5 = new StackPane(lblTongTien);

		paneData1.setPrefWidth(180);
		paneData2.setPrefWidth(180);
		paneData3.setPrefWidth(180);
		paneData4.setPrefWidth(180);
		paneData5.setPrefWidth(180);

		paneData1.setAlignment(Pos.CENTER);
		paneData2.setAlignment(Pos.CENTER);
		paneData3.setAlignment(Pos.CENTER);
		paneData4.setAlignment(Pos.CENTER);
		paneData5.setAlignment(Pos.CENTER);

		data.add(paneData1, 0, 0);
		data.add(paneData2, 1, 0);
		data.add(paneData3, 2, 0);
		data.add(paneData4, 3, 0);
		data.add(paneData5, 4, 0);

		String normalStyle = """
				    -fx-background-color: rgba(0, 186, 203, 0.3);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-color: #B6D0D3;
				    -fx-border-width: 1px;
				""";

		String hoverStyle = """
				    -fx-background-color: rgba(0, 186, 203, 0.6);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-color: #00BACB;
				    -fx-border-width: 1px;
				""";

		String selectedStyle = """
				    -fx-background-color: rgba(0, 186, 203, 0.9);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-color: #00BACB;
				    -fx-border-width: 2px;
				""";

		data.setStyle(normalStyle);

		final GridPane dongHienTai = data;

		data.setOnMouseClicked(e -> {
			// Bỏ chọn dòng cũ nếu có
			if (hangchon != null && hangchon != data) {
				hangchon.setStyle(normalStyle);
			}
			// Chọn dòng hiện tại
			hangchon = data;
			data.setStyle(selectedStyle);
		});

		// Hover effect
		data.setOnMouseEntered(e -> {
			// Chỉ thay đổi style nếu dòng này không phải dòng được chọn
			if (hangchon != data) {
				data.setStyle(hoverStyle);
			}
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
			scaleUp.setToX(1.02);
			scaleUp.setToY(1.02);
			scaleUp.play();
		});

		// Rời chuột
		data.setOnMouseExited(e -> {
			// Nếu đây không phải dòng được chọn, trả về style bình thường
			if (hangchon != data) {
				data.setStyle(normalStyle);
			}
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});
		table_desc.getChildren().add(data);
	}

	public VBox getQuanLiHoaDon() {
		return this.noiDungChinh;
	}

	public static void main(String[] args) {
		launch(args);
//		Application.launch(QuanLyKhachHang.class, args);
	}
}