package fourcore.Control;

import fourcore.GiaoDien.BanVe;
import fourcore.GiaoDien.ChonVe;
import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BanVeControl {
    BanVe gdBanVe = null;
        public void handleMenuTrangChuSelect(BorderPane root){
            try {
                gdBanVe = new BanVe();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Stage thongKeKhachHangStage = new Stage();
            gdBanVe.start(thongKeKhachHangStage);
            VBox gdBanVeShow = gdBanVe.getGDBanVe();
            root.setCenter(gdBanVeShow);
            gdBanVe.getBtn_timkiem().setOnMouseClicked(e -> {
                ChonVe gdChonve = new ChonVe();
                Stage gdChonVeStage = new Stage();
                gdChonve.start(gdChonVeStage);
                VBox gdChonVeMain = gdChonve.getGdChonVe();
                root.setCenter(gdChonVeMain);

            });
        }

}
