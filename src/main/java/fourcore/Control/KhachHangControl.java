package fourcore.Control;

import fourcore.GiaoDien.CapNhatThongTinKhachHang;
import fourcore.GiaoDien.GiaoDienCapLaiVe;
import fourcore.GiaoDien.QuanLyKhachHang;
import fourcore.GiaoDien.ThemKhachHang;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class KhachHangControl {
    QuanLyKhachHang gdQuanLyKhachHang = new QuanLyKhachHang();

    public KhachHangControl() throws SQLException {
    }

    public void handleMenuTrangChuSelect(BorderPane root) {
        Stage quanLyKhachHangStage = new Stage();
        gdQuanLyKhachHang.start(quanLyKhachHangStage);
        VBox quanLyKhachHangVBox = gdQuanLyKhachHang.getQuanLiKhachHang();
        root.setCenter(quanLyKhachHangVBox);
    }

    public void handleThemKhachHangSelect(BorderPane root) throws Exception {
        Stage themKhachHangStage = new Stage();
        ThemKhachHang themKhachHang = new ThemKhachHang();
        themKhachHang.start(themKhachHangStage);
        gdQuanLyKhachHang.getThemKH_Button().setOnMouseClicked(e ->{

            root.setCenter(themKhachHang.getThemKHLayout());
        });
    }
    public void suaThongTinKhachHangSelect(BorderPane root) throws Exception {
        Stage suaKhachHangStage = new Stage();

        gdQuanLyKhachHang.getBtn_capnhat().setOnMouseClicked(e ->{
            CapNhatThongTinKhachHang capNhatThongTinKhachHang = null;
            capNhatThongTinKhachHang = new CapNhatThongTinKhachHang();
            try {
                capNhatThongTinKhachHang.start(suaKhachHangStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            root.setCenter(capNhatThongTinKhachHang.getLayout());
        });
    }
}
