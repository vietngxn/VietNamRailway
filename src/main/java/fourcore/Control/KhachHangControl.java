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
    public ThemKhachHang themKhachHang = new ThemKhachHang();
    Stage stage_themkh = new Stage();
    CapNhatThongTinKhachHang capnhatKH = new CapNhatThongTinKhachHang();	
    Stage stage_capnhat = new Stage();
    


    public void handleMenuTrangChuSelect(BorderPane root) {
        Stage quanLyKhachHangStage = new Stage();
        gdQuanLyKhachHang.start(quanLyKhachHangStage);
        VBox quanLyKhachHangVBox = gdQuanLyKhachHang.getQuanLiKhachHang();
        root.setCenter(quanLyKhachHangVBox);
    }

    public void handleThemKhachHangSelect(BorderPane root) throws Exception {
        
        
        themKhachHang.start(stage_themkh);
        
        gdQuanLyKhachHang.getThemKH_Button().setOnMouseClicked(e ->{
            root.setCenter(themKhachHang.getThemKHLayout());
        });
    }
    
    public void handleThoat(BorderPane root) throws Exception {
    	themKhachHang.start(stage_capnhat);
    	
    	themKhachHang.getButton().setOnMouseClicked(e1-> {
    		root.setCenter(gdQuanLyKhachHang.getQuanLiKhachHang());
    	});
    	
    }
    
    public void suaThongTinKhachHangSelect(BorderPane root) throws Exception {
        
    	capnhatKH.start(stage_capnhat);
    	
        gdQuanLyKhachHang.getBtn_capnhat().setOnMouseClicked(e ->{
            root.setCenter(capnhatKH.getLayout());
        });
    }
    
    public void handleThoat2(BorderPane root) throws Exception {
    	
    	capnhatKH.start(stage_capnhat);
    	
    	capnhatKH.getButtonThoat().setOnMouseClicked(e->{
    		root.setCenter(gdQuanLyKhachHang.getQuanLiKhachHang());
    	});
    }
}
