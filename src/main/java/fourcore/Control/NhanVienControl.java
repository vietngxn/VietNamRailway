package fourcore.Control;

import fourcore.GiaoDien.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NhanVienControl {
	private QuanLiNhanVien qlyNhanVien = new QuanLiNhanVien();;
    public void handleMenuTrangChuSelect(BorderPane root) {
        Stage stage = new Stage();
        qlyNhanVien.start(stage);
        VBox quanLyNhanVienGD = qlyNhanVien.getGDQlyNhanVien();
        root.setCenter(quanLyNhanVienGD);
    }

    public void handleThemNhanVien(BorderPane root) throws Exception {
        ThemNhanVien themNhanVien = new ThemNhanVien();
        Stage themNVStage = new Stage();
        themNhanVien.start(themNVStage);
        qlyNhanVien.getBtn_themNhanVien().setOnMouseClicked(e ->{
            root.setCenter(themNhanVien.getLayoutThemNhanVIen());
        });
    }
    public void suaThongTinNhanVien(BorderPane root) throws Exception {
        Stage suaNhanVienStage = new Stage();

        qlyNhanVien.getBtn_capnhat().setOnMouseClicked(e ->{
            CapNhatNhanVien capNhatNhanVien = null;
            capNhatNhanVien = new CapNhatNhanVien();
            try {
                capNhatNhanVien.start(suaNhanVienStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            root.setCenter(capNhatNhanVien.getLayout());
        });
    }
    
    public void handlecapNhatbtn(BorderPane root) throws Exception
	{
		qlyNhanVien.getCapnhat_btn().setOnMouseClicked(e-> {
			
		CapNhatNhanVien cnnv = new CapNhatNhanVien();
		Stage stage = new Stage();
		try {
			cnnv.start(stage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		VBox capnhatGD =  cnnv.getcapNhatNhanVienLayout();
		root.setCenter(capnhatGD);
		
		
		
		cnnv.getthoatButton().setOnMouseClicked(e1->{
			handleMenuTrangChuSelect(root);
		});
		
		
		});
	}
}
