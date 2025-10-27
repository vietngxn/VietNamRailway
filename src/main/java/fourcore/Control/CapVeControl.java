package fourcore.Control;

import java.sql.SQLException;

import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CapVeControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        GiaoDienCapLaiVe capVe = new GiaoDienCapLaiVe();
        Stage capVeStage = new Stage();
        capVe.start(capVeStage);
        VBox giaoDienCapVe = capVe.getNoiDungChinhVe();
        root.setCenter(giaoDienCapVe);
    }
}
