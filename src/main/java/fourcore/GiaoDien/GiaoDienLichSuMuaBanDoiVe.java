package fourcore.GiaoDien;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import fourcore.Entity.ChiTietHoaDon;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.LichSuTuongTacVe;
import fourcore.Entity.Tau;
import fourcore.Entity.Ve;
import fourcore.dao.ChiTietHoaDonDAO;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.LichSuTuongTacVe_Dao;
import fourcore.dao.NhanVienDAO;
import fourcore.dao.Tau_DAO;
import fourcore.dao.VeDAO;
import fourcore.util.XuatExcelThongKeLichSuTuongTacVe;
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
import javafx.scene.control.DatePicker;
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

public class GiaoDienLichSuMuaBanDoiVe extends Application {

	private VBox noiDungChinh;
	Class<?> clazz = this.getClass();
	private Label lblLichSuMuaBanDoiVe;
	private Pane pnlLichSuMuaBanDoiVe;
	private VBox pnlDataDoiVe;
	private ScrollPane scrollPane;
	private GridPane tableColLichSu;
	private HBox pnlLichSuMuaBanDoiVeButton;
	private Button btnLichSuMuaVe;
	private Button btnLichSuHoanVe;
	private Button btnLichSuDoiVe;
	private String styleLichSuMuaBanDoiVeButton;

	int cnt1 = 0;
	int cnt2 = 0;
	int cnt3 = 0;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
	private LichSuTuongTacVe_Dao dao;
	private ArrayList<LichSuTuongTacVe> list;
	private Map<LichSuTuongTacVe, Double> listXuatThongKe = new HashMap<LichSuTuongTacVe, Double>();
	private Tau_DAO tDao;
	private ChuyenTauDAO ctDAO;
	private ChuyenTau ct;
	private NhanVienDAO nvDao;
	private Tau t;
	private VeDAO veDao;
	private Button xemCTBtn;
	private VBox layoutTimKiem;
	private HBox layoutLblTimKiem;
	private Label lblTimKiem;
	private VBox layoutTxtTimKiem;
	private TextField txtTimKiem;
	private HBox comboboxPnl;
	private LocalDate ngaydi;
	private DatePicker dateTu;
	private DatePicker dateDen;
	private LocalDate ngayDen;
	private StackPane pnlXuatTKBtn;
	private Button btnXuatTK;
	private ChiTietHoaDonDAO cthdDao;
	private ChiTietHoaDon cthd;

	public VBox taoDataChoTableLichSuMuaBanDoiVe(String mave, String chuyen, String loai, String gaDiGaDen,
			String ngayKhoiHanh, String vitrighe, LocalDate ngayMua, String hoten, String doituong, String sogiayto,
			double giave, String giamdoituong, String khuyenmai, double giatrichenhlech, double thanhtien,
			String mavedoi, String nhanvien, String ghichu, String trangThai) throws SQLException {
		VBox pnlTraVe = new VBox();
		VBox.setMargin(pnlTraVe, new Insets(0, 30, 0, 45));
		// ======= DÒNG DỮ LIỆU CHÍNH =======
		GridPane data = new GridPane();
		pnlTraVe.getChildren().add(data);

		data.setHgap(10);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1330);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 15px;";
		String ghiChuValue = null;
		if (trangThai.equalsIgnoreCase("Hoạt động")) {
			ghiChuValue = "Còn hoạt động";
		} else if (trangThai.equalsIgnoreCase("Kết thúc") && !mavedoi.isEmpty() && mavedoi != null) {
			ghiChuValue = "Vé đã được đổi";
		} else if (trangThai.equalsIgnoreCase("Đã hoàn trả")) {
			ghiChuValue = "Vé đã hoàn trả";
		}
		Label[] labels = { new Label(mave), new Label(chuyen), new Label(loai), new Label(gaDiGaDen),
				new Label(ngayKhoiHanh), new Label(vitrighe), new Label(formatter.format(ngayMua)),
				new Label(ghiChuValue), new Label(nhanvien) };
		double[] widths = { 200, 200, 250, 250, 230, 210, 250, 230, 200 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);
			if (i == 7) {
				if ("Còn hoạt động".equalsIgnoreCase(lbl.getText())) {
					lbl.setStyle(
							"-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #009D75;");
				} else if ("Vé đã được đổi".equalsIgnoreCase(lbl.getText())
						|| "Vé đã hoàn trả".equalsIgnoreCase(lbl.getText())) {
					lbl.setStyle(
							"-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 15px;  -fx-text-fill: rgba(203, 0, 44, 0.83);");
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
			data.setStyle(hoverStyle);
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
			scaleUp.setToX(1.02);
			scaleUp.setToY(1.02);
			scaleUp.play();
		});

		data.setOnMouseExited(e -> {
			data.setStyle(normalStyle);
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
		VBox pnlsubCT5 = taoSubCT2("Phí hoàn đổi trả", nf.format(giatrichenhlech), lblCTStyle, lblValueCTStyle);
		if (loai.equalsIgnoreCase("Bán vé")) {
			thanhtien *= 1.08;
		} else if (loai.equalsIgnoreCase("Hoàn trả vé")) {
			thanhtien = giave - giatrichenhlech;
		}
		VBox pnlsubCT6 = taoSubCT2("Thành tiền", nf.format(thanhtien), lblCTStyle, lblValueCTStyle);

		if (loai.equalsIgnoreCase("Bán vé")) {
			VBox pnlsubCT3 = taoSubCT2("Giảm đối tượng", giamdoituong, lblCTStyle, lblValueCTStyle);
			VBox pnlsubCT4 = taoSubCT2("Khuyến mãi", khuyenmai, lblCTStyle, lblValueCTStyle);
			pnlsubCT1.setPrefWidth(400);
			for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT5, pnlsubCT6 }) {
				pnl.setPrefWidth(280);
			}
			pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT6);
			pnlThongTinChiTiet.setManaged(false);
			pnlThongTinChiTiet.setVisible(false);

			checkVisible(pnlTraVe, pnlThongTinChiTiet, null);

			pnlTraVe.getChildren().add(pnlThongTinChiTiet);
			return pnlTraVe;
		}

		if (loai.equalsIgnoreCase("đổi vé")) {
			StackPane xemCTBtnPnl = new StackPane();
			xemCTBtn = new Button("Chi tiết vé mới");
			String btnCss = """
					    -fx-background-color: rgba(0, 186, 203, 0.3);
					    -fx-background-radius: 15px;
					    -fx-border-radius: 15px;
					    -fx-border-color: #B6D0D3;
					    -fx-border-width: 1px;
					    -fx-font-family: 'Kanit';
					""";
			xemCTBtn.setStyle(btnCss);
			hieuUngHover(xemCTBtn);
			xemCTBtnPnl.getChildren().add(xemCTBtn);
			xemCTBtnPnl.setAlignment(Pos.CENTER);

			for (Pane pnl : new Pane[] { pnlsubCT1, pnlsubCT2, pnlsubCT5, xemCTBtnPnl }) {
				pnl.setPrefWidth(280);
			}

			System.out.println(mavedoi);
			GridPane ctVeMoiPnl = taoSubCTDoiVe(mavedoi, loai, ngayMua);

			ctVeMoiPnl.setVisible(false);
			ctVeMoiPnl.setManaged(false);

			xemCTBtn.setOnMouseClicked(event -> {
				boolean check = ctVeMoiPnl.isVisible();
				ctVeMoiPnl.setVisible(!check);
				ctVeMoiPnl.setManaged(!check);
			});

			pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT5, xemCTBtnPnl);
			pnlThongTinChiTiet.setManaged(false);
			pnlThongTinChiTiet.setVisible(false);
			checkVisible(pnlTraVe, pnlThongTinChiTiet, ctVeMoiPnl);
			pnlTraVe.getChildren().addAll(pnlThongTinChiTiet, ctVeMoiPnl);
			return pnlTraVe;
		}

		
		pnlsubCT1.setPrefWidth(400);
		for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT5, pnlsubCT6 }) {
			pnl.setPrefWidth(400);
		}
		pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT5, pnlsubCT6);
		pnlThongTinChiTiet.setManaged(false);
		pnlThongTinChiTiet.setVisible(false);
		checkVisible(pnlTraVe, pnlThongTinChiTiet, null);
		pnlTraVe.getChildren().add(pnlThongTinChiTiet);
		return pnlTraVe;
	}

	private void checkVisible(VBox pnl, HBox Hpnl, GridPane a) {
		pnl.setOnMouseClicked(event -> {
			boolean check = Hpnl.isVisible();
			Hpnl.setManaged(!check);
			Hpnl.setVisible(!check);
			if (a != null && a.isVisible() == true && check == true) {
				Hpnl.setManaged(!check);
				Hpnl.setVisible(!check);
				a.setManaged(false);
				a.setVisible(false);
			}
		});
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

	public GridPane taoSubCTDoiVe(String ma, String loai, LocalDate ngayMua) throws SQLException {
		veDao = new VeDAO();
		ctDAO = new ChuyenTauDAO();
		tDao = new Tau_DAO();
		cthdDao = new ChiTietHoaDonDAO();

		Ve v = veDao.getVeBangMaVe(ma);
		ct = ctDAO.getChuyenTauBangMa(v.getChuyenTau().getMaChuyenTau());
		t = tDao.getTauByMaTau(ct.getTau().getMaTau());
		cthd = cthdDao.getCthdByMaVe(ma);
		GridPane data = new GridPane();
		data.setHgap(10);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1330);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";

		Label[] labels = { new Label(v.getMaVeTau()), new Label(t.getLoaiTau().getTenLoaiTau()),
				new Label(v.getGaDi() + " - " + v.getGaDen()),
				new Label(formatter.format(v.getNgayGioDi().toLocalDate()) + " - "
						+ v.getNgayGioDi().toLocalTime().toString()),
				new Label("Toa số " + v.getSoToa() + " chỗ " + v.getSoGhe()), new Label(formatter.format(ngayMua)),
				new Label("Thành tiền: " + nf.format(cthd.getThanhTien() * 1.08)) };
		double[] widths = { 200, 200, 250, 230, 210, 230, 250 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);
			StackPane pane = new StackPane(lbl);
			pane.setPrefSize(widths[i], 70);
			pane.setAlignment(Pos.CENTER);
			data.add(pane, i, 0);
		}

		String normalStyle = """
				    -fx-background-color: rgba(0, 203, 132, 0.3);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-width: 1px;
				""";

		String hoverStyle = """
				    -fx-background-color: rgba(0, 203, 132);
				    -fx-background-radius: 15px;
				    -fx-border-radius: 15px;
				    -fx-border-width: 1px;
				    -fx-cursor: hand;
				""";

		data.setStyle(normalStyle);

		data.setOnMouseEntered(e -> {
			data.setStyle(hoverStyle);
			ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), data);
			scaleUp.setToX(1.02);
			scaleUp.setToY(1.02);
			scaleUp.play();
		});

		data.setOnMouseExited(e -> {
			data.setStyle(normalStyle);
			ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), data);
			scaleDown.setToX(1.0);
			scaleDown.setToY(1.0);
			scaleDown.play();
		});

		return data;
	}

	public void loadDuLieuChung(LichSuTuongTacVe x) throws SQLException {
		ct = ctDAO.getChuyenTauBangMa(x.getVeTau().getChuyenTau().getMaChuyenTau());
		t = tDao.getTauByMaTau(ct.getTau().getMaTau());
		nvDao = new NhanVienDAO();
		cthdDao = new ChiTietHoaDonDAO();
		ChiTietHoaDon cthd = cthdDao.getCthdByMaVe(x.getVeTau().getMaVeTau());
		String maNV = nvDao.getMaNhanVienBangMaVeVaLoaiTuongTac(x.getVeTau().getMaVeTau(),
				x.getLoaiTuongTacVe().getMaLoaiTuongTac());
		pnlDataDoiVe.getChildren()
				.add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(), t.getLoaiTau().getTenLoaiTau(),
						x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
						x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
						formatter.format(x.getVeTau().getNgayGioDi().toLocalDate()) + " - "
								+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
						"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
						x.getNgayTuongTac().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
						x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(),
						x.getVeTau().getKhachHang().getCccd(), veDao.layGiaTienGheTheoMaVe(x.getVeTau().getMaVeTau()),
						nf.format(x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()) + "%",
						nf.format(x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai()) + "%", cthd.getDongia(),
						cthd.getThanhTien(), x.getVeTau().getTrangThaiDoiVe(), maNV, x.getVeTau().getGhiChu(),
						x.getVeTau().getTrangThaiVe()));
	}

	public StackPane thongBaoKhongTimThayVe() {
		String css = " -fx-font-family: 'Inter';" + "-fx-font-weight: bold;" + "-fx-font-size: 20px;"
				+ "-fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);";
		Label tb = new Label("Không tìm thấy vé thuộc loại cần tìm!");
		tb.setStyle(css);
		StackPane tbao = new StackPane(tb);
		tbao.setAlignment(Pos.CENTER);
		return tbao;
	}

	public VBox loadDuLieuLenTable() throws SQLException {
		dao = new LichSuTuongTacVe_Dao(0);
		list = dao.getList();
		ctDAO = new ChuyenTauDAO();
		tDao = new Tau_DAO();
		veDao = new VeDAO();
		VBox box = new VBox(10);
		nvDao = new NhanVienDAO();
		cthdDao = new ChiTietHoaDonDAO();

		for (LichSuTuongTacVe x : list) {
			cthd = cthdDao.getCthdByMaVe(x.getVeTau().getMaVeTau());
			ct = ctDAO.getChuyenTauBangMa(x.getVeTau().getChuyenTau().getMaChuyenTau());
			t = tDao.getTauByMaTau(ct.getTau().getMaTau());
			String maNV = nvDao.getMaNhanVienBangMaVeVaLoaiTuongTac(x.getVeTau().getMaVeTau(),
					x.getLoaiTuongTacVe().getMaLoaiTuongTac());
			box.getChildren().add(taoDataChoTableLichSuMuaBanDoiVe(x.getVeTau().getMaVeTau(),
					t.getLoaiTau().getTenLoaiTau(), x.getLoaiTuongTacVe().getTenLoaiTuongTac(),
					x.getVeTau().getGaDi() + " - " + x.getVeTau().getGaDen(),
					formatter.format(x.getVeTau().getNgayGioDi().toLocalDate()) + " - "
							+ x.getVeTau().getNgayGioDi().toLocalTime().toString(),
					"Toa số " + x.getVeTau().getSoToa() + " chỗ " + x.getVeTau().getSoGhe(),
					x.getNgayTuongTac().toLocalDate(), x.getVeTau().getKhachHang().getHoten(),
					x.getVeTau().getDoiTuongGiamGia().getTenDoiTuongGiamGia(), x.getVeTau().getKhachHang().getCccd(),
					veDao.layGiaTienGheTheoMaVe(x.getVeTau().getMaVeTau()),
					nf.format(x.getVeTau().getDoiTuongGiamGia().getGiaTriPhanTramGiamGia()) + "%",
					nf.format(x.getVeTau().getKhuyenMai().getGiaTriPhanTramKhuyenMai()) + "%", x.getGiaTriChenhLech(),
					cthd.getThanhTien(), x.getVeTau().getTrangThaiDoiVe(), maNV, x.getVeTau().getGhiChu(),
					x.getVeTau().getTrangThaiVe()));
		}
		return box;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			// Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1300);
			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));

			pnlLichSuMuaBanDoiVe = new Pane();
			lblLichSuMuaBanDoiVe = new Label("Lịch sử mua bán đổi vé");
			pnlLichSuMuaBanDoiVe.getChildren().add(lblLichSuMuaBanDoiVe);
			lblLichSuMuaBanDoiVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			VBox.setMargin(pnlLichSuMuaBanDoiVe, new Insets(20, 0, 0, 50));
			noiDungChinh.getChildren().add(pnlLichSuMuaBanDoiVe);

			layoutTimKiem = new VBox();

			layoutLblTimKiem = new HBox();
			layoutLblTimKiem.setPrefSize(1200, 40);
			lblTimKiem = new Label("Nhập mã vé");
			lblTimKiem.setTranslateX(10);
			lblTimKiem.setTranslateY(0);
			lblTimKiem.setStyle(
					"-fx-font-family: 'Inter';-fx-font-weight:bold;-fx-font-size:18px;-fx-text-fill : #00BACB;");

			ImageView imgTimKiem = new ImageView(getClass().getResource("/images/copy/lookup.png").toExternalForm());
			imgTimKiem.setTranslateX(1050);
			imgTimKiem.setFitHeight(25);
			imgTimKiem.setFitWidth(25);

			layoutLblTimKiem.getChildren().addAll(lblTimKiem, imgTimKiem);
			layoutLblTimKiem.setTranslateY(48);
			layoutTimKiem.getChildren().add(layoutLblTimKiem);

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

			VBox.setMargin(layoutTimKiem, new Insets(0, 0, 20, 0));
			noiDungChinh.getChildren().add(layoutTimKiem);

			pnlLichSuMuaBanDoiVeButton = new HBox(200);
			pnlLichSuMuaBanDoiVeButton.setAlignment(Pos.CENTER);

			btnLichSuMuaVe = new Button("Lịch sử bán vé");
			btnLichSuDoiVe = new Button("Lịch sử đổi vé");
			btnLichSuHoanVe = new Button("Lịch sử hoàn vé");

			styleLichSuMuaBanDoiVeButton = """
					    -fx-font-family: 'Inter';
					    -fx-font-weight: bold;
					    -fx-font-size: 20px;
					    -fx-text-fill: white;
					    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
					    -fx-background-radius: 10;
						-fx-cursor: hand;
						-fx-padding: 10 20 10 20;
					""";

			btnLichSuDoiVe.setStyle(styleLichSuMuaBanDoiVeButton);
			btnLichSuMuaVe.setStyle(styleLichSuMuaBanDoiVeButton);
			btnLichSuHoanVe.setStyle(styleLichSuMuaBanDoiVeButton);

			pnlLichSuMuaBanDoiVeButton.getChildren().addAll(btnLichSuMuaVe, btnLichSuDoiVe, btnLichSuHoanVe);
			noiDungChinh.getChildren().add(pnlLichSuMuaBanDoiVeButton);

			// combobox loc theo thoi gian
			comboboxPnl = new HBox(130);
			noiDungChinh.getChildren().add(comboboxPnl);
			comboboxPnl.setAlignment(Pos.CENTER);
			VBox.setMargin(comboboxPnl, new Insets(10, 0, 10, 0));

			dateTu = new DatePicker();
			dateTu.setId("date");
			dateTu.setPromptText("Từ");
			dateTu.setOnAction(e -> {
				ngaydi = dateTu.getValue();
				dateTu.setValue(ngaydi);
			});
			InputStream is = getClass().getResourceAsStream("/fonts/Inter/static/Inter_24pt-Bold.ttf");
			Font font_combobox = Font.loadFont(is, 15);
			dateTu.getEditor().setFont(font_combobox);

			dateDen = new DatePicker();
			dateDen.setId("date");
			dateDen.setPromptText("Đến");
			dateDen.setOnAction(e -> {
				ngayDen = dateDen.getValue();
				dateDen.setValue(ngayDen);
			});
			dateDen.getEditor().setFont(font_combobox);
			comboboxPnl.getChildren().addAll(dateTu, dateDen);

			// table
			tableColLichSu = new GridPane();
			tableColLichSu.setHgap(10);
			tableColLichSu.setVgap(20);
			tableColLichSu.setAlignment(Pos.CENTER);
			tableColLichSu.setMaxWidth(1330);
			VBox.setMargin(tableColLichSu, new Insets(30, 20, 10, 35));

			String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 18px; -fx-font-weight: bold;";

			String[] headers = { "Mã vé", "Mã chuyến", "Loại tương tác", "Ga đi - Ga đến", "Ngày khởi hành",
					"Vị trí ghế", "Ngày tương tác", "Ghi chú", "Mã NV thanh toán" };

			double[] widths = { 200, 200, 250, 250, 230, 210, 230, 230, 250 };

			for (int i = 0; i < headers.length; i++) {
				Label label = new Label(headers[i]);
				label.setStyle(styleHeader);
				StackPane pane = new StackPane(label);
				pane.setPrefWidth(widths[i]);
				pane.setAlignment(Pos.CENTER);
				tableColLichSu.add(pane, i, 0);
			}

			noiDungChinh.getChildren().add(tableColLichSu);

			pnlDataDoiVe = new VBox();
			pnlDataDoiVe = loadDuLieuLenTable();
			pnlDataDoiVe.setAlignment(Pos.CENTER);

			// === TẠO SCROLLPANE ===
			scrollPane = new ScrollPane(pnlDataDoiVe);
			scrollPane.setMaxHeight(500);
			scrollPane.setFitToWidth(true);
			scrollPane.setPannable(true);
			scrollPane.setStyle("""
					    -fx-background-color: transparent;
					    -fx-border-color: transparent;
					    -fx-border-width: 0;
					""");

			// Chỉ hiện thanh cuộn dọc
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
			noiDungChinh.getChildren().add(scrollPane);

			pnlXuatTKBtn = new StackPane();
			btnXuatTK = new Button("Xuất thống kê");
			btnXuatTK.setStyle(styleLichSuMuaBanDoiVeButton);
			pnlXuatTKBtn.getChildren().add(btnXuatTK);
			pnlXuatTKBtn.setAlignment(Pos.CENTER);
			VBox.setMargin(pnlXuatTKBtn, new Insets(20, 0, 0, 0));
			noiDungChinh.getChildren().add(pnlXuatTKBtn);
			// add su kien disable btn
			txtTimKiem.setOnAction(event -> {
				String regex = "^VE\\d+$";
				String input = txtTimKiem.getText().trim();

				if (input.isEmpty() || !Pattern.matches(regex, input)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Mã vé không hợp lệ! (Định dạng hợp lệ: VE123)");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}

			});
			btnXuatTK.setOnMouseClicked(event -> {
				if (!pnlDataDoiVe.getChildren().isEmpty() && dateTu.getValue() != null && dateTu.getValue() != null
						&& (cnt1 == 1 || cnt2 == 1 || cnt3 == 1)) {

					String loai = null;
					if (cnt1 == 1) {
						loai = "bán vé";
					} else if (cnt2 == 1) {
						loai = "đổi vé";
					} else if (cnt3 == 1) {
						loai = "hoàn trả vé";
					}

					for (Map.Entry<LichSuTuongTacVe, Double> entry : listXuatThongKe.entrySet()) {
						System.out.println(entry.getKey() + " " + entry.getValue());
					}

					XuatExcelThongKeLichSuTuongTacVe xuatExcel = new XuatExcelThongKeLichSuTuongTacVe();
					try {
						xuatExcel.xuatThongKeTuongTacVe(listXuatThongKe, loai, dateTu.getValue(), dateDen.getValue());
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi xuất thống kê");
					alert.setHeaderText(null);
					alert.setContentText(
							"Hãy chọn ngày bắt đầu và ngày kết thúc, chưa chọn loại tương tác để thống kê, dữ liệu thống kê đang rỗng!");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					alert.initOwner(stage);
					alert.initModality(Modality.WINDOW_MODAL);
					alert.showAndWait();
				}
			});
			String normalStyle = """
					    -fx-font-family: 'Inter';
					    -fx-font-weight: bold;
					    -fx-font-size: 20px;
					    -fx-text-fill: white;
					    -fx-background-color: linear-gradient(to top, #00BACB, #8EE6ED);
					    -fx-background-radius: 10;
					    -fx-cursor: hand;
					    -fx-padding: 10 20 10 20;
					""";

			String blankStyle = """
					    -fx-font-family: 'Inter';
					    -fx-font-weight: bold;
					    -fx-font-size: 20px;
					    -fx-text-fill: linear-gradient(to top, #00BACB, #8EE6ED);
					    -fx-background-color: white;
					    -fx-border-color: linear-gradient(to top, #00BACB, #8EE6ED);
					    -fx-background-radius: 10;
					    -fx-border-radius: 10;
					    -fx-cursor: hand;
					    -fx-padding: 10 20 10 20;
					""";

			btnLichSuMuaVe.setOnMouseClicked(event -> {
				String maVeGetText = txtTimKiem.getText();
				System.out.println(maVeGetText);

				if (cnt1 == 0) {
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(blankStyle);
					btnLichSuDoiVe.setStyle(blankStyle);
					cnt1 = 1;
					cnt2 = 0;
					cnt3 = 0;
					listXuatThongKe.clear();
					if (maVeGetText != null && !maVeGetText.trim().isEmpty()) {
						pnlDataDoiVe.getChildren().clear();
						try {
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT01")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}
								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT01")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									loadDuLieuChung(x);

								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}

					} else {
						pnlDataDoiVe.getChildren().clear();
						try {
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT01")) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}

								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT01")) {
									loadDuLieuChung(x);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}
					}
				} else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);

					pnlDataDoiVe.getChildren().clear();
					try {

						for (LichSuTuongTacVe x : list) {
							loadDuLieuChung(x);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			btnLichSuDoiVe.setOnMouseClicked(event -> {
				String maVeGetText = txtTimKiem.getText();
				System.out.println(maVeGetText);

				if (cnt2 == 0) {

					btnLichSuMuaVe.setStyle(blankStyle);
					btnLichSuHoanVe.setStyle(blankStyle);
					btnLichSuDoiVe.setStyle(normalStyle);
					cnt1 = 0;
					cnt2 = 1;
					cnt3 = 0;
					listXuatThongKe.clear();
					if (maVeGetText != null && !maVeGetText.trim().isEmpty()) {
						pnlDataDoiVe.getChildren().clear();
						try {
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT02")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}
								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT02")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									loadDuLieuChung(x);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}

					} else {
						pnlDataDoiVe.getChildren().clear();
						try {
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT02")) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}

								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT02")) {
									loadDuLieuChung(x);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}
					}
				} else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;
					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);

					pnlDataDoiVe.getChildren().clear();
					try {
						for (LichSuTuongTacVe x : list) {
							loadDuLieuChung(x);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			btnLichSuHoanVe.setOnMouseClicked(event -> {
				String maVeGetText = txtTimKiem.getText();
				System.out.println(maVeGetText);

				if (cnt3 == 0) {
					btnLichSuMuaVe.setStyle(blankStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(blankStyle);

					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 1;
					listXuatThongKe.clear();
					// Nếu người dùng nhập mã vé để tìm kiếm
					if (maVeGetText != null && !maVeGetText.trim().isEmpty()) {
						pnlDataDoiVe.getChildren().clear();

						try {
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT03")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}
								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT03")
										&& x.getVeTau().getMaVeTau().contains(maVeGetText)) {
									loadDuLieuChung(x);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}
					}
					// Nếu không nhập mã vé — hiển thị tất cả vé hoàn trả
					else {
						try {
							pnlDataDoiVe.getChildren().clear();
							for (LichSuTuongTacVe x : list) {
								if (dateTu.getValue() != null && dateDen.getValue() != null
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT03")) {
									if ((x.getNgayTuongTac().toLocalDate().isAfter(dateTu.getValue())
											&& x.getNgayTuongTac().toLocalDate().isBefore(dateDen.getValue()))
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateTu.getValue())
											|| x.getNgayTuongTac().toLocalDate().isEqual(dateDen.getValue())) {
										loadDuLieuChung(x);
										listXuatThongKe.put(x,
												x.tinhTongTien(x.getLoaiTuongTacVe().getMaLoaiTuongTac()));
									}
								}
								if ((dateTu.getValue() == null || dateDen.getValue() == null)
										&& x.getLoaiTuongTacVe().getMaLoaiTuongTac().equalsIgnoreCase("LTT03")) {
									loadDuLieuChung(x);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (pnlDataDoiVe.getChildren().isEmpty()) {
							pnlDataDoiVe.getChildren().add(thongBaoKhongTimThayVe());
						}
					}
				}
				// Nếu đang bật -> nhấn lại thì tắt về trạng thái mặc định
				else {
					cnt1 = 0;
					cnt2 = 0;
					cnt3 = 0;

					btnLichSuMuaVe.setStyle(normalStyle);
					btnLichSuHoanVe.setStyle(normalStyle);
					btnLichSuDoiVe.setStyle(normalStyle);
					pnlDataDoiVe.getChildren().clear();

					try {
						for (LichSuTuongTacVe x : list) {
							loadDuLieuChung(x);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			hieuUngHover(btnLichSuDoiVe);
			hieuUngHover(btnLichSuHoanVe);
			hieuUngHover(btnLichSuMuaVe);
			hieuUngHover(btnXuatTK);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
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

	public VBox getLichSuMuaVe() {
		return this.noiDungChinh;
	}

}