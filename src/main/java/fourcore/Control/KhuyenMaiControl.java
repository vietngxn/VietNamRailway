package fourcore.Control;

import fourcore.GiaoDien.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KhuyenMaiControl {
    QuanLyCTKM quanLyCTKM = new QuanLyCTKM();
    ThemChuongTrinhKhuyenMai themChuongTrinhKhuyenMai = new ThemChuongTrinhKhuyenMai();
    Stage themCTKMStage = new Stage();
    CapNhatChuongTrinhKhuyenMai capNhatChuongTrinhKhuyenMai = new CapNhatChuongTrinhKhuyenMai();
    Stage capnhatCTKMStage = new Stage();
    
    public void handleMenuTrangChuSelect(BorderPane root){

        Stage stage = new Stage();
        quanLyCTKM.start(stage);
        VBox quanLyCTKMMenu = quanLyCTKM.getQuanLiCTKM();
        root.setCenter(quanLyCTKMMenu);
        
        
    }
    
    public void handleThemCTKM(BorderPane root) throws Exception { 
    	
    	
        themChuongTrinhKhuyenMai.start(themCTKMStage);
        
        quanLyCTKM.getBtn_themCTKM().setOnMouseClicked(e ->{
            root.setCenter(themChuongTrinhKhuyenMai.getLayoutThemCTKM());
        });
    }
    
    public void handlethoatCTKM(BorderPane root) throws Exception 
    {
    	Stage themCTKMStage1 = new Stage();
    	themChuongTrinhKhuyenMai.start(themCTKMStage1);
    	
    	
        
    	themChuongTrinhKhuyenMai.getButtonThoat().setOnMouseClicked(e -> {
    		root.setCenter(quanLyCTKM.getQuanLiCTKM());
    	});
    }
    
 
    
    public void suaThongTinCTKMSelect(BorderPane root) throws Exception {
    	
    	capNhatChuongTrinhKhuyenMai.start(capnhatCTKMStage);

        quanLyCTKM.getBtn_capnhat().setOnMouseClicked(e ->{
            root.setCenter(capNhatChuongTrinhKhuyenMai.getLayout());
            
            
        });
        
    }
    
    
    public void handlethoatCTKM2(BorderPane root) throws Exception
    {
    	
    	capNhatChuongTrinhKhuyenMai.start(capnhatCTKMStage);
    	
    	capNhatChuongTrinhKhuyenMai.getButtonThoat().setOnMouseClicked(e -> {
    		root.setCenter(quanLyCTKM.getQuanLiCTKM());
    	});
    	
    }
    

}
