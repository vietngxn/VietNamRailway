package fourcore.Control;

import java.io.IOException;

import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class XemLichSuVeBoxControl {

    public void handleMenuTrangChuSelect(BorderPane root) throws IOException {
        GiaoDienLichSuMuaBanDoiVe lichSuMuaBanDoiVe = new GiaoDienLichSuMuaBanDoiVe();
        Stage lichSuMuaBanDoiVeStage = new Stage();
        lichSuMuaBanDoiVe.start(lichSuMuaBanDoiVeStage);
        VBox giaoDienLichSuMuaBanDoiVe = lichSuMuaBanDoiVe.getLichSuMuaVe();
        root.setCenter(giaoDienLichSuMuaBanDoiVe);
    }
}
