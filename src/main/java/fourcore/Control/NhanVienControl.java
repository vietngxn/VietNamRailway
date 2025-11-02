package fourcore.Control;

import fourcore.GiaoDien.CapNhatNhanVien;
import fourcore.GiaoDien.QuanLiHoaDon;
import fourcore.GiaoDien.QuanLiNhanVien;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NhanVienControl {
	private QuanLiNhanVien qlyNhanVien;
    public void handleMenuTrangChuSelect(BorderPane root) {
        qlyNhanVien = new QuanLiNhanVien();
        Stage stage = new Stage();
        qlyNhanVien.start(stage);
        VBox quanLyNhanVienGD = qlyNhanVien.getGDQlyNhanVien();
        root.setCenter(quanLyNhanVienGD);
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
