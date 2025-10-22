package fourcore.Control;

import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.QuanLyKhachHang;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KhachHangControl {

    public void handleMenuTrangChuSelect(BorderPane root){
        QuanLyKhachHang gdQuanLyKhachHang = new QuanLyKhachHang();
        Stage quanLyKhachHangStage = new Stage();
        gdQuanLyKhachHang.start(quanLyKhachHangStage);
        VBox quanLyKhachHangVBox = gdQuanLyKhachHang.getQuanLiKhachHang();
        root.setCenter(quanLyKhachHangVBox);
    }
}
