package fourcore.Control;

import fourcore.GiaoDien.QuanLiNhanVien;
import fourcore.GiaoDien.QuanLyCTKM;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KhuyenMaiControl {
    public void handleMenuTrangChuSelect(BorderPane root){
        QuanLyCTKM quanLyCTKM = new QuanLyCTKM();
        Stage stage = new Stage();
        quanLyCTKM.start(stage);
        VBox quanLyCTKMMenu = quanLyCTKM.getQuanLiCTKM();
        root.setCenter(quanLyCTKMMenu);
    }
    void themCTKM(){

    }


}
