package fourcore.Control;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.Ga;
import fourcore.GiaoDien.BanVe;
import fourcore.GiaoDien.ChonVe;
import fourcore.GiaoDien.GiaoDienLichSuMuaBanDoiVe;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.GheNgoiDAO;
import fourcore.dao.HanhTrinh_DAO;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BanVeControl {
    ArrayList<ChuyenTau> listChuyenTauFiltered = new ArrayList<>();
    ArrayList<ChuyenTau> listChuyenTau;
    LocalDate dateMotChieu;
    LocalDate dateKhuHoi;
    String gaDi ="";
    public String gaDen = "";
    String loaiVeString;
    ChonVe gdChonve = new ChonVe();

    BanVe gdBanVe = null;

    public BanVeControl() throws SQLException {
    }

    public void handleMenuTrangChuSelect(BorderPane root){
            try {
                gdBanVe = new BanVe();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Stage thongKeKhachHangStage = new Stage();
            gdBanVe.start(thongKeKhachHangStage);
            VBox gdBanVeShow = gdBanVe.getGDBanVe();
            root.setCenter(gdBanVeShow);

        }
    public void timKiemChuyenTauHandle(BorderPane root){
            System.out.println("Tim kiem chuyen tau handle");
            handleMenuTrangChuSelect(root);
            gdBanVe.getBtn_timkiem().setOnMouseClicked(e -> {

            try {
                gaDi = gdBanVe.getComboBox1().getSelectionModel().getSelectedItem().toString();
                gaDen = gdBanVe.getComboBox2().getSelectionModel().getSelectedItem().toString();
            }catch (Exception ex){
                gdBanVe.thongBao.setText("Ga đi - Ga đến không được để trống!");
                gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                return;
            }
            if(gaDen.equals(gaDi)){
                gdBanVe.thongBao.setText("Ga đi - Ga đến không được trùng nhau!");
                gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                return;
            }
            try{
                loaiVeString =(String) gdBanVe.loaive.getSelectedToggle().getUserData();
            }catch (Exception ex){
                gdBanVe.thongBao.setText("Chọn loại vé một chiều hoặc khứ hồi!");
                gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                return;
            }



            if(loaiVeString.equals("motchieu")){
                dateMotChieu = gdBanVe.date.getValue();
                if(dateMotChieu==null){
                    gdBanVe.thongBao.setText("Vui lòng chọn ngày đi!");
                    gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                    return;
                }
                if(dateMotChieu.isBefore(LocalDate.now())){
                    gdBanVe.thongBao.setText("Ngày đi phải sau ngày hiện tại!");
                    gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                    return;
                }
            }
            if(loaiVeString.equals("khuhoi")){
                dateMotChieu = gdBanVe.date.getValue();
                dateKhuHoi = gdBanVe.date1.getValue();
                if(dateMotChieu==null || dateKhuHoi == null){
                    gdBanVe.thongBao.setText("Vui lòng chọn đầy đủ ngày đi và ngày đến!");
                    gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                    return;
                }
                if(dateMotChieu.isBefore(LocalDate.now())|| dateKhuHoi.isBefore(LocalDate.now())){
                    gdBanVe.thongBao.setText("Ngày đi, ngày về phải sau ngày hiện tại!");
                    gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                    return;
                }
                if(dateKhuHoi.isBefore(dateMotChieu)){
                    gdBanVe.thongBao.setText("Ngày về phải sau ngày đi!");
                    gdBanVe.thongBao.setStyle("-fx-text-fill: red;");
                    return;
                }
            }

                ChuyenTauDAO chuyenTauDAO = null;
                try {
                    chuyenTauDAO = new ChuyenTauDAO();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                listChuyenTau = chuyenTauDAO.listChuyenTau;
            System.out.println(listChuyenTau.get(1).getTau().getMaTau());
            ArrayList<ChuyenTau> listInsert = new ArrayList<>();
                try {
                    listInsert = getListThoaDieuKien();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    gdChonve.hienThiDanhSachChuyenTau(listChuyenTau);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listChuyenTauFiltered.dat"))) {
                    oos.writeObject(listInsert);
                    System.out.println("Đã ghi ArrayList vào file thành công!");
                    System.out.println("size list ghi: " + listInsert.size());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Stage gdChonVeStage = new Stage();
            gdChonve.start(gdChonVeStage);
            VBox gdChonVeMain = gdChonve.getGdChonVe();
            root.setCenter(gdChonVeMain);
            troLaiGDBanVe(root);

            ;

            System.out.println(gaDi);
            System.out.println(gaDen);
            System.out.println(loaiVeString);

        });
    }
    public void troLaiGDBanVe(BorderPane root){
        gdChonve.getTrolaiBtn().setOnMouseClicked(e -> {
            root.setCenter(gdBanVe.getGDBanVe());
        });
    }


    public ArrayList<ChuyenTau> getListChuyenTau() throws SQLException {
            ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();

            listChuyenTau = chuyenTauDAO.listChuyenTau;
        return listChuyenTau;
    }
    public ArrayList<ChuyenTau> getListThoaDieuKien() throws SQLException {
        System.out.println("Get list thoa  dieu kien");

        ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
        listChuyenTau = chuyenTauDAO.listChuyenTau;
        ArrayList<Ga> listGa;
        ArrayList<ChuyenTau> listChuyenTauFiltered = new ArrayList<>();
        HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
        System.out.println("ga den" +  gaDen);
        System.out.println(dateMotChieu.toString());
            //FILTER THEO GA DEN + THEO NGAY
        for(ChuyenTau chuyenTau : listChuyenTau){
            listGa = hanhTrinh_DAO.getListGaByMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
                for(Ga g : listGa){
                    if(g.getTenGa().equals(gaDen) && chuyenTau.getNgayGioDi().toLocalDate().equals(dateMotChieu)){
                        listChuyenTauFiltered.add(chuyenTau);
                        System.out.println("Them ghe....");
                    }
                }
            }
            System.out.println("size: " + listChuyenTauFiltered.size());
            return listChuyenTauFiltered;
    }
    public ArrayList<ChuyenTau> getListChuyenTauFiltered() {
            return listChuyenTauFiltered;
    }

}
