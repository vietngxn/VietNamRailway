package fourcore.Control;

import fourcore.GiaoDien.QuanLiHoaDon;
import fourcore.GiaoDien.QuanLiThongKeChuyenTau;
import fourcore.GiaoDien.QuanLiThongKeDoanhThuTheoNam;
import fourcore.GiaoDien.QuanLiThongKeKhachHang;
import fourcore.GiaoDien.QuanLyThongKeDoanhThuTheoChuyenTau;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThongKeChuyenTauControl {
    public void handleMenuTrangChuSelect(BorderPane root) {
        QuanLiThongKeChuyenTau gdQuanLiHoaDon = new QuanLiThongKeChuyenTau();
        Stage quanLiHoaDonStage = new Stage();
        gdQuanLiHoaDon.start(quanLiHoaDonStage);
        VBox gdQLHD = gdQuanLiHoaDon.getQuanLiThongKe();
        root.setCenter(gdQLHD);
    }
    public void handleShowThongKe(BorderPane root) {
        QuanLiThongKeDoanhThuTheoNam gdQuanLiThongKe = new QuanLiThongKeDoanhThuTheoNam();
        gdQuanLiThongKe.setLoaiThongKe("ThongKeKhachHang");
        Stage thongKeKhachHangStage = new Stage();
        gdQuanLiThongKe.start(thongKeKhachHangStage);
        VBox quanLiThongKeVBox = gdQuanLiThongKe.getQuanLiThongKe();
        root.setCenter(quanLiThongKeVBox);
    }
    
    public void handleshowthongke2(BorderPane root)
    {
    	QuanLyThongKeDoanhThuTheoChuyenTau qltk = new QuanLyThongKeDoanhThuTheoChuyenTau();
    	Stage Stage_thongke = new Stage();
    	qltk.start(Stage_thongke);
    	root.setCenter(qltk.getLayout());
    }
    
    
}