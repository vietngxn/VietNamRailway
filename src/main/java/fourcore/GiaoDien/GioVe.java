package fourcore.GiaoDien;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import javafx.scene.text.Text;

public class GioVe extends Application {

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

	private ScrollPane scrollPane;
//	private int cnt = 1;
	private HBox layout_button;
	private Button btn_trolai;
	private Button btn_tieptuc;
	private Pane pnlGioVelbl;
	private Label lblGioVe;
	private VBox pnlDataGioVe;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1600, 800);
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

			quanLiVeTauMenu.getChildren().addAll(quanLiVeTauIconView, quanLiVeTauLabel, spacer, showMenuPhuIconView);

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
			for (Label label : new Label[] { banVeLabel, doiVeLabel, hoanVeLabel, capVeLabel }) {
				label.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;");
				label.setPadding(new Insets(10, 320, 10, 72));
				label.setOnMouseEntered(e -> label.setStyle("-fx-font-size: 15px;-fx-background-color: #79D9E1;"));
				label.setOnMouseExited(e -> label.setStyle("-fx-font-size: 15px;-fx-background-color: #D2EEF0;"));
			}

			menuPhuQuanLiVeTau.getChildren().addAll(banVeLabel, doiVeLabel, hoanVeLabel, capVeLabel);
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

			});

			menuList.getChildren().add(scrollPaneMenu);

			// Noi dung chinh lam phan chinh o day. T lam sidebar truoc r update sau
			noiDungChinh = new VBox();
			noiDungChinh.setStyle("-fx-background-color: #F7F7F7;");
			noiDungChinh.setPrefWidth(1200);

			BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
			root.setLeft(menuList);
			root.setCenter(noiDungChinh);

			pnlGioVelbl = new Pane();
			lblGioVe = new Label("Giỏ vé");
			pnlGioVelbl.getChildren().add(lblGioVe);
			lblGioVe.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");
			noiDungChinh.getChildren().add(pnlGioVelbl);
			VBox.setMargin(pnlGioVelbl, new Insets(20, 0, 0, 50));
			pnlDataGioVe = new VBox(10);
			pnlDataGioVe.setAlignment(Pos.CENTER);

			pnlDataGioVe.getChildren().add(taoDataChoTableGioVe("SE2", "Sài Gòn - Hà Nội", "27/09/2025  -  08:40",
					"Toa số 3 chỗ 23", "Nguyễn Tiến Đạt G", "Con cặc", "093636363636", 1400000, 0, 0, 1400000));

			pnlDataGioVe.getChildren().add(taoDataChoTableGioVe("SE2", "Sài Gòn - Hà Nội", "27/09/2025  -  08:40",
					"Toa số 3 chỗ 23", "Nguyễn Thị Kiều Trinh", "Trẻ em", "093636363636", 1400000, 0, 0, 1400000));

			pnlDataGioVe.getChildren().add(taoDataChoTableGioVe("SE2", "Sài Gòn - Hà Nội", "27/09/2025  -  08:40",
					"Toa số 3 chỗ 23", "Nguyễn Tiến Đạt G", "Con cặc", "093636363636", 1400000, 0, 0, 1400000));

			pnlDataGioVe.getChildren().add(taoDataChoTableGioVe("SE2", "Sài Gòn - Hà Nội", "27/09/2025  -  08:40",
					"Toa số 3 chỗ 23", "Nguyễn Tiến Đạt G", "Con cặc", "093636363636", 1400000, 0, 0, 1400000));

			scrollPane = new ScrollPane();
			scrollPane.setContent(pnlDataGioVe);
			scrollPane.setMaxHeight(600);
			VBox.setMargin(scrollPane, new Insets(170, 0, 0, 0));
			scrollPane.setFitToWidth(true);
			scrollPane.setStyle("""
					    -fx-background-color: transparent;
					    -fx-border-color: transparent;
					    -fx-border-width: 0;
					""");
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Không hiện thanh ngang
			scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Thanh dọc chỉ hiện khi cần
			scrollPane.setPannable(true);

			// ScrollPane sẽ tự động mở rộng nhưng không vượt quá chiều cao còn lại của cửa
			// sổ
			VBox.setVgrow(scrollPane, Priority.ALWAYS);
			noiDungChinh.getChildren().add(scrollPane);
			tao_button();
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VBox taoDataChoTableGioVe(String matau, String gaDiGaDen, String ngayKhoiHanh, String vitrighe, String hoten,
			String doituong, String sogiayto, double giave, double giamdoituong, double khuyenmai, double thanhtien) {

		NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));

		VBox pnlReturn = new VBox();
		VBox.setMargin(pnlReturn, new Insets(0, 30, 0, 30));
		// ======= DÒNG DỮ LIỆU CHÍNH =======
		GridPane data = new GridPane();
		pnlReturn.getChildren().add(data);

		data.setHgap(10);
		data.setAlignment(Pos.CENTER);
		data.setMaxWidth(1330);
		data.setPrefHeight(70);
		data.setPadding(new Insets(0, 0, 0, 10));

		String baseStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 16.5px;";

		Label[] labels = { new Label(matau), new Label(gaDiGaDen), new Label(ngayKhoiHanh), new Label(vitrighe) };
		double[] widths = { 200, 200, 200, 200 };

		for (int i = 0; i < labels.length; i++) {
			Label lbl = labels[i];
			lbl.setStyle(baseStyle);

			StackPane pane = new StackPane(lbl);
			pane.setPrefSize(widths[i], 70);
			if (i == 3) {
				pane.setTranslateX(400);
			} else if (i == 2) {
				pane.setTranslateX(200);
			} else if (i == 1) {
				pane.setTranslateX(-140);
			} else if (i == 0) {
				pane.setTranslateX(-70);
			}
			pane.setAlignment(Pos.CENTER);
			data.add(pane, i, 0);
		}

		// === ICON “plus” ===
		ImageView img_xoa = new ImageView(getClass().getResource("/images/copy/plus.png").toExternalForm());
		img_xoa.setFitHeight(40);
		img_xoa.setFitWidth(40);
		GridPane.setMargin(img_xoa, new Insets(0, 0, 0, 400));

		data.add(img_xoa, 4, 0);

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
				      -fx-border-color: black;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";

//	-fx-padding: 8 12 8 12;
		String rightStyle = """
				    -fx-background-color: white;
				    -fx-background-radius: 0 10px 10px 0;
				    -fx-border-radius: 0 10px 10px 0;
				    -fx-border-color: black;
				    -fx-alignment: center-left;
				    -fx-font-weight: bold;
				    -fx-font-family: "Kanit";
				    -fx-padding: 8 12 8 12;
				""";

		pnlsubCT1.addRow(0, createSubPane("Họ tên", hoten, leftStyle, rightStyle, 1));
		pnlsubCT1.addRow(1, createSubPane("Đối tượng", doituong, leftStyle, rightStyle, 2));
		pnlsubCT1.addRow(2, createSubPane("Số giấy tờ", sogiayto, leftStyle, rightStyle, 3));

		// Các panel giá trị
		String lblCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 18px;";
		String lblValueCTStyle = "-fx-font-family: 'Kanit'; -fx-font-weight: bold; -fx-font-size: 30px;";

		VBox pnlsubCT2 = createPriceBox("Giá vé", nf.format(giave), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT3 = createPriceBox("Giảm đối tượng", nf.format(giamdoituong), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT4 = createPriceBox("Khuyến mãi", nf.format(khuyenmai), lblCTStyle, lblValueCTStyle);
		VBox pnlsubCT5 = createPriceBox("Thành tiền", nf.format(thanhtien), lblCTStyle, lblValueCTStyle);

		pnlsubCT1.setPrefWidth(400);
		for (Pane pnl : new Pane[] { pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT5 })
			pnl.setPrefWidth(320);

		pnlThongTinChiTiet.getChildren().addAll(pnlsubCT1, pnlsubCT2, pnlsubCT3, pnlsubCT4, pnlsubCT5);
		pnlReturn.getChildren().add(pnlThongTinChiTiet);

		pnlThongTinChiTiet.setManaged(false);
		pnlThongTinChiTiet.setVisible(false);

		pnlReturn.setOnMouseClicked(event -> {
			boolean check = pnlThongTinChiTiet.isVisible();
			pnlThongTinChiTiet.setManaged(!check);
			pnlThongTinChiTiet.setVisible(!check);
		});

		img_xoa.setOnMouseEntered(e -> {
			img_xoa.setOpacity(0.5);
			img_xoa.setStyle("-fx-cursor: hand;");
		});

		img_xoa.setOnMouseExited(e -> img_xoa.setOpacity(1.0));

		img_xoa.setOnMouseClicked(e -> {
			((VBox) pnlReturn.getParent()).getChildren().remove(pnlReturn);
			System.out.println("Đã xóa vé: " + matau);
		});

		return pnlReturn;
	}

	private HBox createSubPane(String label, String value, String leftStyle, String rightStyle, int check) {
		StackPane left = new StackPane(new Label(label));
		StackPane right = new StackPane();
		right.setPrefSize(200, 40);

		if (check == 1) {
			TextField txtHoTen = new TextField();
			txtHoTen.setPromptText("Nhập họ tên");
			String regexHoten = "[a-zA-ZÀ-ỹ\\s]+$";
			txtHoTen.setOnAction(event -> {
				String input = txtHoTen.getText();
				if (!input.matches(regexHoten)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Họ tên không hợp lệ");
					alert.showAndWait();
				}
			});
			txtHoTen.setStyle(rightStyle);
			txtHoTen.setMaxWidth(Double.MAX_VALUE);
			txtHoTen.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(txtHoTen, Pos.CENTER);
			right.getChildren().add(txtHoTen);
		} else if (check == 2) {
			ComboBox<String> cmbDoiTuong = new ComboBox<>();
			cmbDoiTuong.getItems().addAll("Người lớn", "Trẻ em");
			cmbDoiTuong.setStyle(rightStyle);
			cmbDoiTuong.setMaxWidth(Double.MAX_VALUE);
			cmbDoiTuong.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(cmbDoiTuong, Pos.CENTER);
			cmbDoiTuong.setOnAction(event -> {
				String giaTriCmb = cmbDoiTuong.getValue();

				if (giaTriCmb == null || giaTriCmb.isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi");
					alert.setHeaderText(null);
					alert.setContentText("Chưa chọn đối tượng!");
					alert.showAndWait();
				} else {
					System.out.println("Đã chọn: " + giaTriCmb);
				}
			});

			right.getChildren().add(cmbDoiTuong);
		} else if (check == 3) {
			TextField txtSoGiayTo = new TextField();
			txtSoGiayTo.setPromptText("Nhập số giấy tờ");
			String regexSoGiayTo = "^[0-9]+$";
			txtSoGiayTo.setOnAction(event -> {
				String input = txtSoGiayTo.getText();
				if (!input.matches(regexSoGiayTo)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Lỗi định dạng");
					alert.setHeaderText(null);
					alert.setContentText("Số giấy tờ không hợp lệ");
					alert.showAndWait();
				}
			});
			txtSoGiayTo.setStyle(rightStyle);
			txtSoGiayTo.setMaxWidth(Double.MAX_VALUE);
			txtSoGiayTo.setMaxHeight(Double.MAX_VALUE);
			StackPane.setAlignment(txtSoGiayTo, Pos.CENTER);
			right.getChildren().add(txtSoGiayTo);
		}

		left.setPrefWidth(100);
		right.setPrefWidth(200);
		left.setStyle(leftStyle);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		return new HBox(left, right);
	}

	private VBox createPriceBox(String title, String value, String labelStyle, String valueStyle) {
		Label lblTitle = new Label(title);
		lblTitle.setStyle(labelStyle);
		Label lblValue = new Label(value);
		lblValue.setStyle(valueStyle);
		VBox box = new VBox(5, new StackPane(lblTitle), new StackPane(lblValue));
		box.setAlignment(Pos.CENTER);
		return box;
	}

//	public void tao_noidung_tieude() {
//		tieude_layout = new VBox();
//		tieude_layout.setPrefHeight(200);
//		tieude_layout.setMaxHeight(200);
//		noiDungChinh.setPadding(new Insets(20));
//		lbl_tieude = new Label("Giỏ vé");
//		Font font_tieude = Font
//				.loadFont(getClass().getResource("/fonts/Inter/static/Inter_28pt-Bold.ttf").toExternalForm(), 35);
//		lbl_tieude.setFont(font_tieude);
//		noiDungChinh.getChildren().add(lbl_tieude);
//	}

//	public void tao_table(String chuyen,String gadi,LocalDate thoigian,LocalTime giophut,String chongoi)
//	{ 
//		HBox layout_dong = new HBox();
//		
//		layout_dong.setStyle("-fx-background-color:#00BACB;-fx-background-radius :15px;");
//		layout_dong.setPrefSize(1000, 50);
//		String styleHeader = "-fx-font-family: 'Kanit'; -fx-font-size: 24px; -fx-font-weight: bold;-fx-text-fill:black;";
//		Label lbl_chuyen = new Label(chuyen);
//		lbl_chuyen.setStyle(styleHeader);
//		lbl_chuyen.setTranslateX(10);
//		lbl_chuyen.setTranslateY(15);
//		
//		Label lbl_gadi = new Label(gadi);
//		lbl_gadi.setStyle(styleHeader);
//		lbl_gadi.setTranslateX(50);
//		lbl_gadi.setTranslateY(15);
//		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
//		
//		String thoigiankhoihanh = thoigian.format(dtf);
//		Label lbl_thoigian = new Label(thoigiankhoihanh);
//		lbl_thoigian.setStyle(styleHeader);
//		lbl_thoigian.setTranslateX(140);
//		lbl_thoigian.setTranslateY(15);
//
//		int hour = giophut.getHour();
//		int min = giophut.getMinute();
//		
//		String giophut1 = giophut.format(tf);
//		Label lbl_giophut = new Label(" - "+giophut1);
//		lbl_giophut.setStyle(styleHeader);
//		lbl_giophut.setTranslateX(140);
//		lbl_giophut.setTranslateY(15);
//		
//		
//		Label lbl_chongoi = new Label(chongoi);
//		lbl_chongoi.setStyle(styleHeader);
//		lbl_chongoi.setTranslateX(240);
//		lbl_chongoi.setTranslateY(15);
//		
//		ImageView img_xoa = new ImageView(getClass().getResource("/images/copy//plus.png").toExternalForm());
//		img_xoa.setFitHeight(60);
//		img_xoa.setFitWidth(60);
//		img_xoa.setTranslateX(250);
//		img_xoa.hoverProperty().addListener((obs,oval,nval) -> {
//			if(nval)
//			{
//				img_xoa.setStyle("-fx-cursor:hand;");
//			}
//		});	
//		
//		img_xoa.setOnMousePressed(e -> {
//		    FadeTransition ft = new FadeTransition(Duration.millis(500), layout_dong);
//		    ft.setFromValue(1.0);
//		    ft.setToValue(0.0);
//
//		    ft.setOnFinished(ev -> {
//		        table_layout.getChildren().remove(layout_dong);
//		    });
//
//		    ft.play();
//		});
//		
//		if(cnt % 2 == 0) {
//			table_layout.setSpacing(20);
//		}
//		else {
//		cnt++;
//		}
//		layout_dong.getChildren().addAll(lbl_chuyen,lbl_gadi,lbl_thoigian,lbl_giophut,lbl_chongoi,img_xoa);
//		table_layout.getChildren().add(layout_dong);
//	}

	public void tao_button() {

		layout_button = new HBox();
		layout_button.setPrefSize(1000, 100);
		layout_button.setTranslateY(50);

		btn_trolai = new Button("Trở lại");
		btn_trolai.setPrefSize(150, 50);

		btn_trolai.setStyle(
				"-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);-fx-background-radius:15px;");
		btn_tieptuc = new Button("Tiếp Tục");
		btn_tieptuc.setPrefSize(150, 50);
		btn_tieptuc.setStyle(
				"-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #00BACB, #B6D0D3);-fx-background-radius:15px;");
		btn_tieptuc.setTranslateX(600);

		btn_trolai.hoverProperty().addListener((obs, oval, nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_trolai);

			if (nval) {
				st.setToX(1.1);
				st.setToY(1.1);
				btn_trolai.setStyle(
						"-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #CB002C, #D498A5);-fx-background-radius:15px;-fx-cursor:hand;");
			} else {
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});

		btn_tieptuc.hoverProperty().addListener((obs, oval, nval) -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btn_tieptuc);
			if (nval) {
				st.setToX(1.1);
				st.setToY(1.1);
				btn_tieptuc.setStyle(
						"-fx-font-family: 'Inter';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:white;-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #00BACB, #B6D0D3);-fx-background-radius:15px;-fx-cursor:hand;");
			} else {
				st.setToX(1);
				st.setToY(1);
			}
			st.play();
		});

		layout_button.getChildren().addAll(btn_trolai, btn_tieptuc);
		noiDungChinh.getChildren().add(layout_button);
	}

	public static void main(String[] args) {
		launch(args);
//		Application.launch(TrangChu.class, args);
	}
}