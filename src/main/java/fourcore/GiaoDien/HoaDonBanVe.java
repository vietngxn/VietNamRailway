package fourcore.GiaoDien;

import com.itextpdf.kernel.colors.Lab;
import fourcore.Control.ChuyenSoThanhChu;
import fourcore.Control.HoaDonExportPDF;
import fourcore.Entity.HoaDon;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.ThongTinCtHoaDon;
import fourcore.Entity.Ve;
import fourcore.dao.HoaDonDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDonBanVe extends Application {

    private BorderPane root;
    private VBox table_layout;
    private int cnt = 1;
	private Button btn_xuatHoaDon;
	private Button btn_thoat;
	private VBox table_desc;
    HoaDon hoaDon;
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    ArrayList<ThongTinCtHoaDon> listThongTinCtHoaDon;
    public HoaDonBanVe(HoaDon hoaDon) throws SQLException {
        this.hoaDon = hoaDon;
        listThongTinCtHoaDon = hoaDonDAO.getThongTinCTHoaDon(hoaDon.getMaHoaDon());

    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        root = new BorderPane();

        create_title_layout(hoaDon);
            create_table_layout(listThongTinCtHoaDon);

        create_footer_layout(listThongTinCtHoaDon,hoaDonDAO.getKhuyenMaiByMaHoaDon(hoaDon.getMaHoaDon()));

        Scene scene = new Scene(root, 870, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hóa đơn");
//        primaryStage.show();
    }

    // --------------------- PHẦN TIÊU ĐỀ ---------------------
    private void create_title_layout(HoaDon hoaDon) throws SQLException {
        VBox title_layout = new VBox(10);
        title_layout.setPadding(new Insets(20));
        title_layout.setAlignment(Pos.CENTER_LEFT);

        Label lbl_tieuDe = new Label("Hóa đơn bán vé");
        lbl_tieuDe.setStyle("-fx-font-family : 'Inter';-fx-font-size: 30px; -fx-font-weight: bold;");
        StackPane pane_tieuDe = new StackPane(lbl_tieuDe);
        
        pane_tieuDe.setAlignment(Pos.CENTER);
        String style = "-fx-font-family:'Inter';-fx-font-size : 16px;-fx-font-weight:bold;";
        
        
        Label lbl_maHoaDon = new Label("Mã hóa đơn: " + hoaDon.getMaHoaDon());
        lbl_maHoaDon.setStyle(style);
        
        Label lbl_donViBan = new Label("Đơn vị bán: Tổng công ty đường sắt Việt Nam                     Nhân viên bán hàng: "+ hoaDon.getMaNhanVien().getHoTen());
        lbl_donViBan.setStyle(style);

        Label lbl_diaChiBan = new Label("Địa chỉ: QMJH+RF6, Nguyễn Thông, Phường 9, Quận 3, Thành phố Hồ Chí Minh");
        lbl_diaChiBan.setStyle(style);

        Label lbl_Space = new Label(" ");

        Label lbl_nguoiMua = new Label("Người mua: "+ hoaDon.getTenKhachHangThanhToan());
        lbl_nguoiMua.setStyle(style);
        
        Label lbl_diaChi = new Label("Địa chỉ khách hàng: "+ hoaDon.getDiaChiKhachHangThanhToan());
        lbl_diaChi.setStyle(style);
        
        Label lbl_sdt = new Label("Điện thoại: "+ hoaDon.getSdtKhachHangThanhToan());
        lbl_sdt.setStyle(style);

        Label lbl_cccd = new Label("CCCD: "+ hoaDon.getCccdKhachHangThanhToan());
        lbl_cccd.setStyle(style);

        VBox info_layout = new VBox(5, lbl_maHoaDon, lbl_donViBan,lbl_diaChiBan,lbl_Space, lbl_nguoiMua, lbl_diaChi, lbl_sdt,lbl_cccd);
        info_layout.setPadding(new Insets(10, 0, 0, 50));

        title_layout.getChildren().addAll(pane_tieuDe, info_layout);
        root.setTop(title_layout);
    }

    private void create_table_layout(ArrayList<ThongTinCtHoaDon> thongTinCtHoaDon) {
        table_layout = new VBox(10);
        table_layout.setPadding(new Insets(20, 50, 20, 50));

        GridPane table_header = new GridPane();
        table_header.setHgap(15);
        table_header.setVgap(10);
        table_header.setAlignment(Pos.CENTER);
        String style = "-fx-font-family:'Inter';-fx-font-size : 14px;-fx-font-weight:bold;";
        String[] headers = {"STT", "Mã vé", "Tên loại ghế", "Đối tượng", "Đơn giá", "Thành tiền"};
        for (int i = 0; i < headers.length; i++) {
            Label lbl_header = new Label(headers[i]);
            lbl_header.setStyle("-fx-font-weight: bold;");
            lbl_header.setPrefWidth(100);
            lbl_header.setAlignment(Pos.CENTER);
            lbl_header.setStyle(style);
            table_header.add(lbl_header, i, 0);
        }

        table_layout.getChildren().add(table_header);

        
        table_desc = new VBox();
        table_desc.setSpacing(10);         // <-- đây là setSpacing đúng chỗ
        table_desc.setPadding(new Insets(10, 0, 10, 0));
        cnt = 1;
        for(int i=0;i<thongTinCtHoaDon.size();i++){
            create_table_row(cnt, thongTinCtHoaDon.get(i).getMaVe(), thongTinCtHoaDon.get(i).getTenLoaiGhe(), thongTinCtHoaDon.get(i).getDoiTuong(), thongTinCtHoaDon.get(i).getDonGia()+"",  thongTinCtHoaDon.get(i).getThanhTien()+"");

        }
        ScrollPane scrPane = new ScrollPane(table_desc);
        
        scrPane.setPrefViewportHeight(400);
        scrPane.setFitToWidth(true);
        scrPane.setPannable(true);
        scrPane.setStyle("""
                    -fx-background-color: transparent;
                    -fx-border-color: transparent;
                    -fx-border-width: 0;
                """);

        scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        table_layout.getChildren().add(scrPane);
        
        root.setCenter(table_layout);
    }

    
    private void create_table_row(int stt, String maVe, String tenDichVu, String doiTuong,
                                  String donGia, String thanhTien) {
        GridPane layout_dong = new GridPane();
        layout_dong.setHgap(15);
        layout_dong.setVgap(5);
        layout_dong.setAlignment(Pos.CENTER);
        layout_dong.setPrefHeight(70);
        String style = "-fx-font-family:'Inter';-fx-font-size : 12px;-fx-font-weight:bold;";
        layout_dong.setStyle("-fx-background-color :  #00BACB;-fx-background-radius:15px;-fx-border-radius:15px;");
        
        String[] values = {String.valueOf(cnt),maVe, tenDichVu, doiTuong, donGia, thanhTien};

        for (int i = 0; i < values.length; i++) {
            Label lbl_cell = new Label(values[i]);
            lbl_cell.setPrefWidth(100);
            lbl_cell.setAlignment(Pos.CENTER);
            lbl_cell.setStyle(style);
            layout_dong.add(lbl_cell, i, 0);
            if(i == 2)
            {
            	lbl_cell.setWrapText(true);
            }
        }
        cnt++;
        table_desc.getChildren().add(layout_dong);
    }

    private void create_footer_layout(ArrayList<ThongTinCtHoaDon> thongTinCtHoaDon, KhuyenMai km) {
        VBox footer_layout = new VBox(10);
        footer_layout.setPadding(new Insets(10, 50, 20, 50));


        VBox thanhToanBox = new VBox(10);

        String style = "-fx-font-family:'Inter';-fx-font-size : 15px;-fx-font-weight:bold;";
        double tongCong  = 0;
        for(int i=0;i<thongTinCtHoaDon.size();i++){
            tongCong += thongTinCtHoaDon.get(i).getThanhTien();
        }
        HBox tongCong_layout = new HBox();
        tongCong_layout.setAlignment(Pos.CENTER_RIGHT);
        tongCong_layout.setSpacing(20);

        Label lbl_tongCong = new Label("Tổng cộng:");
        lbl_tongCong.setStyle(style);
        
        Label lbl_tien = new Label(tongCong+"");
        lbl_tien.setStyle(style);

        tongCong_layout.getChildren().addAll(lbl_tongCong, lbl_tien);
        thanhToanBox.getChildren().add(tongCong_layout);


        HBox chietKhauLayout = new HBox();
        chietKhauLayout.setAlignment(Pos.CENTER_RIGHT);
        chietKhauLayout.setSpacing(20);

        Label lbl_chietKhau = new Label("Chiết khấu ("+km.getGiaTriPhanTramKhuyenMai()+"%) :");
        lbl_chietKhau.setStyle(style);

        Label lbl_tongChietKhau = new Label("-"+tongCong*(km.getGiaTriPhanTramKhuyenMai()/100));
        lbl_tongChietKhau.setStyle(style);

        chietKhauLayout.getChildren().addAll(lbl_chietKhau, lbl_tongChietKhau);
        thanhToanBox.getChildren().add(chietKhauLayout);

        HBox VATLayout = new HBox();
        VATLayout.setAlignment(Pos.CENTER_RIGHT);
        VATLayout.setSpacing(20);

        Label lbl_vat = new Label("Tổng cộng thuế (Thuế suất 8%) :");
        lbl_vat.setStyle(style);

        Label lbl_tongVAT = new Label(((tongCong-(tongCong*(km.getGiaTriPhanTramKhuyenMai()/100)))*8/100) +"");
        lbl_tongVAT.setStyle(style);

        VATLayout.getChildren().addAll(lbl_vat, lbl_tongVAT);
        thanhToanBox.getChildren().add(VATLayout);

        HBox tongCongThanhToanLayout = new HBox();
        tongCongThanhToanLayout.setAlignment(Pos.CENTER_RIGHT);
        tongCongThanhToanLayout.setSpacing(20);

        Label lbl_tongcongthanhtoan = new Label("Tổng cộng tiền thanh toán: ");
        lbl_tongcongthanhtoan.setStyle(style);

        Label lbl_valuetongcongthanhtoan = new Label(hoaDon.getTongTien()+"");
        lbl_valuetongcongthanhtoan.setStyle(style);

        tongCongThanhToanLayout.getChildren().addAll(lbl_tongcongthanhtoan, lbl_valuetongcongthanhtoan);
        thanhToanBox.getChildren().add(tongCongThanhToanLayout);

        ChuyenSoThanhChu moneyConvert =  new ChuyenSoThanhChu();
        Label lbl_tienBangChu = new Label(moneyConvert.numberToVietnamese((long)hoaDon.getTongTien()));
        lbl_tienBangChu.setStyle(style);
        lbl_tienBangChu.setTranslateX(400);
        
        
        HBox button_layout = new HBox(20);
        button_layout.setAlignment(Pos.CENTER_RIGHT);
        btn_xuatHoaDon = new Button("Xuất hóa đơn");
        btn_xuatHoaDon.setPrefSize(150, 50);
        btn_thoat = new Button("Thoát");
        btn_thoat.setPrefSize(150, 50);
        
        btn_xuatHoaDon.setStyle("-fx-background-color : #00BACB;-fx-background-radius:15px;-fx-border-radius:15px;-fx-font-family: 'Inter';-fx-text-fill :white;-fx-font-size : 15px;-fx-font-weight:bold;");
        btn_thoat.setStyle("-fx-background-color: white; -fx-border-color: #00BACB;-fx-background-radius:15px;-fx-border-radius: 15px; -fx-font-weight: bold;-fx-font-family:'Inter';-fx-font-weight:bold;-fx-text-fill:#00BACB;");

        button_layout.getChildren().addAll(btn_xuatHoaDon, btn_thoat);
        btn_xuatHoaDon.setOnAction(e -> {
            HoaDonExportPDF hoaDonExportPDF = new HoaDonExportPDF();
            try {
                System.out.println("kich thuoc list thong tin ct hoadon: "+ listThongTinCtHoaDon.size());
                hoaDonExportPDF.exportHoaDon(hoaDon,listThongTinCtHoaDon,hoaDonDAO.getKhuyenMaiByMaHoaDon(hoaDon.getMaHoaDon()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        footer_layout.getChildren().addAll(thanhToanBox, lbl_tienBangChu, button_layout);
        root.setBottom(footer_layout);
    }
    public void showAsPopup(Stage owner) {
        Stage popupStage = new Stage();

        if (owner != null) {
            popupStage.initOwner(owner);
            popupStage.initModality(Modality.WINDOW_MODAL); // Giữ owner fullscreen
        } else {
            popupStage.initModality(Modality.APPLICATION_MODAL); // Tự modal nếu không có owner
        }

        popupStage.initStyle(StageStyle.DECORATED);
        popupStage.setTitle("Hóa đơn bán vé");

        try {
            ArrayList<ThongTinCtHoaDon> listThongTinCtHoaDon = hoaDonDAO.getThongTinCTHoaDon(hoaDon.getMaHoaDon());
            root = new BorderPane();
            create_title_layout(hoaDon);
            create_table_layout(listThongTinCtHoaDon);
            create_footer_layout(listThongTinCtHoaDon, hoaDonDAO.getKhuyenMaiByMaHoaDon(hoaDon.getMaHoaDon()));

            Scene scene = new Scene(root, 870, 1000);
            popupStage.setScene(scene);
            popupStage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        launch(args);
    }
}
