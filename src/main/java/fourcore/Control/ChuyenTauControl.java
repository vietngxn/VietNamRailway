package fourcore.Control;

import java.io.IOException;
import java.sql.SQLException;

import fourcore.GiaoDien.QuanLyChuyenTau;
import fourcore.GiaoDien.ThemChuyenTau;
import fourcore.GiaoDien.ThemDauTau;
import fourcore.GiaoDien.ThemToaTau;
import fourcore.GiaoDien.ThietLapGiaGhe;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChuyenTauControl {
	public QuanLyChuyenTau gdQuanLyChuyenTau = new QuanLyChuyenTau();
	public ThemChuyenTau gdThemChuyenTau = new ThemChuyenTau();
	public ThemDauTau gdThemDauTau = new ThemDauTau();
	public ThemToaTau gdThemToaTau = new ThemToaTau();
	public ThietLapGiaGhe gdThietLap = new ThietLapGiaGhe();
	
    public void handleMenuTrangChuSelect(BorderPane root) throws SQLException, IOException {
        QuanLyChuyenTau gdQuanLiChuyenTau = new QuanLyChuyenTau();
        Button buttonThemChuyen = null;
        Stage qlChuyenTauStage = new Stage();
        
        VBox gdChinhThemChuyenTau = gdThemChuyenTau.creat_themchuyentau_layout();
        buttonThemChuyen = gdThemChuyenTau.getButtonTiepTuc();
        buttonThemChuyen = gdThemChuyenTau.getButtonTiepTuc();
        
//        VBox gdChinhThemDauTau = null;
//        Button btnThemDauTau = null;
        
        
        
        
        
        gdQuanLiChuyenTau.start(qlChuyenTauStage);
        VBox gdChinhQLChuyenTau = gdQuanLiChuyenTau.getGDQuanLiChuyenTau();
        Button btnThemChuyenTau = gdQuanLiChuyenTau.getBtn_ThemChuyenTau(); 
        
      
        btnThemChuyenTau.setOnMouseClicked(event -> {
        	root.setCenter(gdChinhThemChuyenTau);
        	 
        });
        buttonThemChuyen.setOnMouseClicked(event -> {
        	if(gdThemChuyenTau.xuLyEventCu()) {
       
        		try {
        			VBox gdChinhThemDauTau = gdThemDauTau.creat_themDauTau_layout();
        			Button btnThemDauTau = gdThemDauTau.getButtonTiepTucQuaChonToa();			
					root.setCenter(gdChinhThemDauTau);
					btnThemDauTau.setOnMouseClicked(event2 -> {
			        	if(gdThemDauTau.xuLyEventCu()) {
			        		VBox gdChinhThemToaTau = null;
			        		try {
								gdChinhThemToaTau = gdThemToaTau.creat_themtoatau_layout();
								root.setCenter(gdChinhThemToaTau);
								
								Button btnToThietLapGiaGhe = gdThemToaTau.getButtonSangThietLapGiaGhe();
								btnToThietLapGiaGhe.setOnMouseClicked(event3 -> {
									if(gdThemToaTau.xuLyEventCu()) {
										VBox gdChinhThietLap = null;
										try {
											gdChinhThietLap = gdThietLap.create_themThietLap_layout();
											root.setCenter(gdChinhThietLap);
										} catch (SQLException | IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
							} catch (SQLException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			        		
			        	}
			      });
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        });
       
        
  
       
        
        
        root.setCenter(gdChinhQLChuyenTau);
    
//    public void handleThemChuyenTau(BorderPane root) {
//    	handleMenuTrangChuSelect(root);
//    	gdQuanLyChuyenTau.getBtn_ThemChuyenTau().setOnMouseClicked(event -> {
//    		ThemChuyenTau gdThemChuyenTau = new ThemChuyenTau();
//        	Stage themChuyenTauStage = new Stage();
//        	gdThemChuyenTau.start(themChuyenTauStage);
//        	VBox gdChinhThemChuyenTau = gdThemChuyenTau.getGDThemChuyenTau();
//        	root.setCenter(gdChinhThemChuyenTau);
//    	});
// 
//    }
    
    }
}
