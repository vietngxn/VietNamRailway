package fourcore.Control;

import java.sql.SQLException;

import fourcore.GiaoDien.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NhanVienControl {
	QuanLiNhanVien qlyNhanVien = new QuanLiNhanVien();;
	ThemNhanVien themnv = new ThemNhanVien();
	Stage stage_themnv = new Stage();
	CapNhatNhanVien cnnv = new CapNhatNhanVien();
	Stage stage_cnvv = new Stage();
	
    public void handleMenuTrangChuSelect(BorderPane root) throws SQLException {
        Stage stage = new Stage();
        qlyNhanVien.start(stage);
        VBox quanLyNhanVienGD = qlyNhanVien.getQuanLiNhanVien();
        root.setCenter(quanLyNhanVienGD);
    }

    
    public void handleThemNhanVien(BorderPane root) throws Exception {
        
        
        themnv.start(stage_themnv);
        qlyNhanVien.getButtonthem().setOnMouseClicked(e ->{
            root.setCenter(themnv.getLayoutThemNhanVIen());
        });
    }
    
    public void handlethoat(BorderPane root) throws Exception {
    	
    	
    	themnv.start(stage_themnv);
    	
    	themnv.getButtonThoat().setOnMouseClicked(e-> {
    		try {
				root.setCenter(qlyNhanVien.getQuanLiNhanVien());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    	
    }
    
    
    public void handlecapNhatbtn(BorderPane root) throws Exception
	{
    	
    	cnnv.start(stage_cnvv);
    	
		qlyNhanVien.getButtoncapnhat().setOnMouseClicked(e-> {
			root.setCenter(cnnv.getcapNhatNhanVienLayout());
		});
	}
    
    
    public void handlethoat2(BorderPane root) throws Exception
    {
    	
    	cnnv.start(stage_cnvv);
    	
    	cnnv.getthoatButton().setOnMouseClicked(e1->{
			try {
				root.setCenter(qlyNhanVien.getQuanLiNhanVien());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}
