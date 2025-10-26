package fourcore.Control;

import fourcore.GiaoDien.QuanLiHoaDon;
import fourcore.GiaoDien.QuanLiNhanVien;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NhanVienControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        QuanLiNhanVien qlyNhanVien = new QuanLiNhanVien();
        Stage stage = new Stage();
        qlyNhanVien.start(stage);
        VBox quanLyNhanVienGD = qlyNhanVien.getGDQlyNhanVien();
        root.setCenter(quanLyNhanVienGD);
    }
}
