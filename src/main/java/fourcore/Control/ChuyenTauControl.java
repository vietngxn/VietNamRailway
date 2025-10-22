package fourcore.Control;

import fourcore.GiaoDien.QuanLyCTKM;
import fourcore.GiaoDien.QuanLyChuyenTau;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChuyenTauControl {
    public void handleMenuTrangChuSelect(BorderPane root){
        QuanLyChuyenTau gdQuanLiChuyenTau = new QuanLyChuyenTau();
        Stage qlChuyenTauStage = new Stage();
        gdQuanLiChuyenTau.start(qlChuyenTauStage);
        VBox gdChinhQLChuyenTau = gdQuanLiChuyenTau.getGDQuanLiChuyenTau();
        root.setCenter(gdChinhQLChuyenTau);
    }
}
