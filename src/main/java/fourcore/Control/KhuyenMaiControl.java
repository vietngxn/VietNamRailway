package fourcore.Control;

import fourcore.GiaoDien.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KhuyenMaiControl {
    QuanLyCTKM quanLyCTKM = new QuanLyCTKM();

    public void handleMenuTrangChuSelect(BorderPane root){

        Stage stage = new Stage();
        quanLyCTKM.start(stage);
        VBox quanLyCTKMMenu = quanLyCTKM.getQuanLiCTKM();
        root.setCenter(quanLyCTKMMenu);
    }
    public void handleThemCTKM(BorderPane root) throws Exception {
        ThemChuongTrinhKhuyenMai themChuongTrinhKhuyenMai = new ThemChuongTrinhKhuyenMai();
        Stage themCTKMStage = new Stage();
        themChuongTrinhKhuyenMai.start(themCTKMStage);
        quanLyCTKM.getBtn_themCTKM().setOnMouseClicked(e ->{
            root.setCenter(themChuongTrinhKhuyenMai.getLayoutThemCTKM());
        });
    }
    public void suaThongTinKhachHangSelect(BorderPane root) throws Exception {
        Stage suaKhachHangStage = new Stage();

        quanLyCTKM.getBtn_capnhat().setOnMouseClicked(e ->{
            CapNhatChuongTrinhKhuyenMai capNhatChuongTrinhKhuyenMai = null;
            capNhatChuongTrinhKhuyenMai = new CapNhatChuongTrinhKhuyenMai();
            try {
                capNhatChuongTrinhKhuyenMai.start(suaKhachHangStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            root.setCenter(capNhatChuongTrinhKhuyenMai.getLayout());
        });
    }


}
