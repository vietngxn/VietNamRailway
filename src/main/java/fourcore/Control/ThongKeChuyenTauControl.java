package fourcore.Control;

import fourcore.GiaoDien.QuanLiHoaDon;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThongKeChuyenTauControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        QuanLiHoaDon gdQuanLiHoaDon = new QuanLiHoaDon();
        Stage quanLiHoaDonStage = new Stage();
        gdQuanLiHoaDon.start(quanLiHoaDonStage);
        VBox gdQLHD = gdQuanLiHoaDon.getQuanLiHoaDon();
        root.setCenter(gdQLHD);
    }
}
