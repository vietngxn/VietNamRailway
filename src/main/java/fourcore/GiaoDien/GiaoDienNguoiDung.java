package fourcore.GiaoDien;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import fourcore.Entity.NhanVien;
import fourcore.dao.GaTauDao;
import fourcore.dao.TaiKhoanDAO;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class GiaoDienNguoiDung extends Application {
    private BorderPane manHinhChinh;
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
    public DatePicker date;
    Button btnDangXuat = new Button("Đăng xuất");
    private Stage window = new Stage();
    BorderPane root = new BorderPane();
    ComboBox<String> comboBox1 = new ComboBox<>();
    ComboBox<String> comboBox2 = new ComboBox<>();
    public ToggleGroup loaive = new ToggleGroup();
    public Label thongBao =  new Label();
    NhanVien nv;
    public GiaoDienNguoiDung() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("NhanVien.dat"));
            nv = (NhanVien) ois.readObject();
            window = primaryStage;

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

            taoGiaoDien();



            BorderPane.setMargin(noiDungChinh, new Insets(0, 0, 0, 50));
            root.setLeft(menuList);
            root.setCenter(noiDungChinh);
            root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            window.setFullScreen(true);
//			window.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void taoGiaoDien() throws SQLException{
        noiDungChinh = new VBox(25);
        noiDungChinh.setPadding(new Insets(30));
        noiDungChinh.setAlignment(Pos.TOP_CENTER);
        noiDungChinh.setStyle("-fx-background-color: #F5F7FA;");

        // ===== TIÊU ĐỀ =====
        Label lblTitle = new Label("Thông tin nhân viên");
        lblTitle.setFont(Font.font("Inter", FontWeight.BOLD, 26));

        // ===== CARD THÔNG TIN =====
        VBox cardInfo = new VBox(12);
        cardInfo.setPadding(new Insets(20));
        cardInfo.setPrefWidth(500);
        cardInfo.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 16;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 12, 0, 0, 4);
                """);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngaySinhFormat = nv.getNgaySinh().format(formatter);
        Label lblMaNV = taoLabelInfo("Mã nhân viên:", nv.getMaNhanVien());
        Label lblHoTen = taoLabelInfo("Họ và tên:", nv.getHoTen());
        Label lblNgaySinh = taoLabelInfo("Ngày sinh:", ngaySinhFormat);
        Label lblCCCD = taoLabelInfo("CCCD:", nv.getCccd());

        cardInfo.getChildren().addAll(lblMaNV, lblHoTen, lblNgaySinh, lblCCCD);

        // ===== ĐỔI MẬT KHẨU =====
        VBox doiMatKhauBox = new VBox(12);
        doiMatKhauBox.setPadding(new Insets(20));
        doiMatKhauBox.setPrefWidth(500);
        doiMatKhauBox.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 16;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 12, 0, 0, 4);
                """);

        Label lblDoiMK = new Label("Đổi mật khẩu");
        lblDoiMK.setFont(Font.font("Inter", FontWeight.BOLD, 18));

        PasswordField txtMatKhauCu = taoPasswordField("Mật khẩu cũ");
        PasswordField txtMatKhauMoi = taoPasswordField("Mật khẩu mới");
        PasswordField txtXacNhan = taoPasswordField("Xác nhận mật khẩu");

        Button btnDoiMatKhau = new Button("Cập nhật mật khẩu");
        btnDoiMatKhau.setOnMouseClicked(mouseEvent -> {
            TaiKhoanDAO taiKhoanDAO;
            try {
                taiKhoanDAO = new TaiKhoanDAO();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(txtMatKhauCu.getText().isEmpty() || txtMatKhauMoi.getText().isEmpty() || txtXacNhan.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không được để trống");
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                alert.initOwner(stage);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.showAndWait();
            }else {
                try {
                    if(!taiKhoanDAO.checkPass(nv.getMaNhanVien(), txtMatKhauCu.getText())){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Mật khẩu cũ không đúng");
                                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                alert.initOwner(stage);
                                alert.initModality(Modality.WINDOW_MODAL);
                                alert.showAndWait();
                    }else if(!txtMatKhauMoi.getText().equals(txtXacNhan.getText())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Mật khẩu mới và mật khẩu xác nhận không khớp");
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        alert.initOwner(stage);
                        alert.initModality(Modality.WINDOW_MODAL);
                        alert.showAndWait();
                    }else if(txtMatKhauMoi.getText().equals(txtMatKhauCu.getText())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Mật khẩu không được giống với mật khẩu cũ");
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        alert.initOwner(stage);
                        alert.initModality(Modality.WINDOW_MODAL);
                        alert.showAndWait();
                    }else {
                        taiKhoanDAO.updatePass(nv.getMaNhanVien(), txtMatKhauMoi.getText());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Đổi mật khẩu thành công");
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        alert.initOwner(stage);
                        alert.initModality(Modality.WINDOW_MODAL);
                        alert.showAndWait();

                        txtMatKhauCu.setText("");
                        txtXacNhan.setText("");
                        txtMatKhauMoi.setText("");
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        });
        btnDoiMatKhau.setPrefHeight(40);
        btnDoiMatKhau.setStyle("""
                -fx-background-color: #00BACB;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 10;
                """);

        doiMatKhauBox.getChildren().addAll(
                lblDoiMK,
                txtMatKhauCu,
                txtMatKhauMoi,
                txtXacNhan,
                btnDoiMatKhau
        );

        // ===== NÚT ĐĂNG XUẤT =====

        btnDangXuat.setPrefSize(200, 45);
        btnDangXuat.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #E53935;
                -fx-text-fill: #E53935;
                -fx-font-weight: bold;
                -fx-background-radius: 12;
                -fx-border-radius: 12;
                """);

        // ===== ADD TẤT CẢ =====
        noiDungChinh.getChildren().addAll(
                lblTitle,
                cardInfo,
                doiMatKhauBox,
                btnDangXuat
        );
    }

    private Label taoLabelInfo(String title, String value) {
        Label lbl = new Label(title + " " + value);
        lbl.setFont(Font.font("Inter", 15));
        return lbl;
    }

    private PasswordField taoPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setPrefHeight(40);
        pf.setStyle("""
                -fx-background-radius: 10;
                -fx-border-radius: 10;
                """);
        return pf;
    }
    public VBox getNoiDungChinh(){
        return this.noiDungChinh;
    }
    public Button getBtnDangXuat(){
        return this.btnDangXuat;
    }



    public static void main(String[] args) {
        launch(args);
//		Application.launch(BanVe.class, args);
    }
}