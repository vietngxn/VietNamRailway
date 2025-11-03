package fourcore.Control;

import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import fourcore.GiaoDien.QuanLiThongKeChuyenTau;
import fourcore.GiaoDien.QuanLiThongKeKhachHang;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThongKeKhachHangControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        QuanLiThongKeKhachHang gdQuanLiThongKe = new QuanLiThongKeKhachHang();
        gdQuanLiThongKe.setLoaiThongKe("ThongKeKhachHang");
        Stage thongKeKhachHangStage = new Stage();
        gdQuanLiThongKe.start(thongKeKhachHangStage);
        VBox quanLiThongKeVBox = gdQuanLiThongKe.getQuanLiThongKe();
        root.setCenter(quanLiThongKeVBox);
    }

}
