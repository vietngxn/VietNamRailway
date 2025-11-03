package fourcore.Control;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;

import fourcore.GiaoDien.QuanLyChuyenTau;
import fourcore.GiaoDien.ThemChuyenTau;
import fourcore.GiaoDien.ThemDauTau;
import fourcore.GiaoDien.ThemToaTau;
import fourcore.GiaoDien.ThietLapGiaGhe;
import fourcore.animation.Animation;
import fourcore.animation.GhiFile;
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
	public GhiFile ghiFile = new GhiFile();
	public File fileTmp = new File("src/main/resources/tmp_ChuyenTau.txt");
	private VBox gdChinhThemChuyenTau = null;
	private VBox gdChinhThemDauTau = null;
	private VBox gdChinhThemToaTau = null;
	private VBox gdChinhThietLap = null;
	private VBox gdChinhQuanLiChuyenTau = null;
	
	
	 public ChuyenTauControl() throws SQLException {
	 }
	 
	 public void handleMenuTrangChuSelect(BorderPane root) throws SQLException, IOException {
		 	Stage qlChuyenTauStage = new Stage();
		 	gdQuanLyChuyenTau.start(qlChuyenTauStage);
		 	gdChinhQuanLiChuyenTau = gdQuanLyChuyenTau.getGDQuanLiChuyenTau();
		 	Button btnThemChuyenTau = gdQuanLyChuyenTau.getBtn_ThemChuyenTau(); 
		 	btnThemChuyenTau.setOnMouseClicked(event -> {
	        	try {
					showThemChuyenTau(root);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 
	        });
		 	root.setCenter(gdChinhQuanLiChuyenTau);
		}
	
	 
	 public void showThemChuyenTau(BorderPane root) throws SQLException, IOException {
		    if (gdChinhThemChuyenTau == null) {
		        gdChinhThemChuyenTau = gdThemChuyenTau.creat_themchuyentau_layout();

		        Button btnThemChuyenTau = gdThemChuyenTau.getButtonTiepTuc();
		        btnThemChuyenTau.setOnMouseClicked(event -> {
					try {
						if(gdThemChuyenTau.xuLyEventCu())
						showThemDauTau(root);
					} catch (java.io.IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		    }
		    root.setCenter(gdChinhThemChuyenTau);
		}
	 private void showThemDauTau(BorderPane root) throws java.io.IOException {
		    try {
		        if (gdChinhThemDauTau == null) {
		            gdChinhThemDauTau = gdThemDauTau.creat_themDauTau_layout();

		            Button btnTroLai = gdThemDauTau.getButtonTroLai();
		            btnTroLai.setOnMouseClicked(ev -> {
		                if (ghiFile.xoaTrangDong(fileTmp, 0)) {
						    root.setCenter(gdChinhThemChuyenTau);
						    gdChinhThemDauTau.getChildren().clear();
						    gdChinhThemDauTau = null;
						} else {
						    
						}
		            });

		            Button btnTiepTuc = gdThemDauTau.getButtonTiepTucQuaChonToa();
		            btnTiepTuc.setOnMouseClicked(ev -> {
						try {
							if(gdThemDauTau.xuLyEventCu())
							showThemToaTau(root);
						} catch (java.io.IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		        }
		        root.setCenter(gdChinhThemDauTau);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		private void showThemToaTau(BorderPane root) throws java.io.IOException {
		    try {
		        if (gdChinhThemToaTau == null) {
		            gdChinhThemToaTau = gdThemToaTau.creat_themtoatau_layout();

		            Button btnTroLai = gdThemToaTau.getButtonTroLai();
		            btnTroLai.setOnMouseClicked(ev -> {
		            	if(ghiFile.xoaTrangDong(fileTmp, 1))
		            	root.setCenter(gdChinhThemDauTau);
		            	gdChinhThemToaTau.getChildren().clear();
		            	gdChinhThemToaTau = null;
		            	}
		            );

		            Button btnThietLapGiaGhe = gdThemToaTau.getButtonSangThietLapGiaGhe();
		            btnThietLapGiaGhe.setOnMouseClicked(ev -> {
						try {
							if(gdThemToaTau.xuLyEventCu())
							showThietLap(root);
						} catch (java.io.IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		        }
		        root.setCenter(gdChinhThemToaTau);
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		    }
		}

		private void showThietLap(BorderPane root) throws java.io.IOException {
		    try {
		        if (gdChinhThietLap == null) {
		            gdChinhThietLap = gdThietLap.create_themThietLap_layout();

		            Button btnTroLai = gdThietLap.getButtonTroLai();
		            btnTroLai.setOnMouseClicked(ev -> {
		            	if(ghiFile.xoaTrangDong(fileTmp, 2))
		            	root.setCenter(gdChinhThemToaTau);
		            	gdChinhThietLap.getChildren().clear();
		            	gdChinhThietLap = null;
		            	}
		            );	
		            
		        }
		        root.setCenter(gdChinhThietLap);
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		    }
		}
}
