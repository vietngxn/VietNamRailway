package fourcore.Control;

import fourcore.GiaoDien.QuanLiHoaDon;
import fourcore.GiaoDien.QuanLiThongKeDoanhThuTheoNam;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThongKeChuyenTauControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        QuanLiThongKeDoanhThuTheoNam gdQuanLiHoaDon = new QuanLiThongKeDoanhThuTheoNam();
        Stage quanLiHoaDonStage = new Stage();
        gdQuanLiHoaDon.start(quanLiHoaDonStage);
        VBox gdQLHD = gdQuanLiHoaDon.getGD();
        root.setCenter(gdQLHD);
    }
}
