package fourcore.Control;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.Ga;
import fourcore.GiaoDien.*;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.GheNgoiDAO;
import fourcore.dao.HanhTrinh_DAO;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.SQLXML;
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
    ChonVe gdChonve = null;
    GioVe gdGiove = null;
    BanVe gdBanVe = null;
    ChonVeKhuHoi gdChonVeKhuHoi = null;
    ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();

    GiaoDienXuatHoaDon gdXuatHoaDon = new GiaoDienXuatHoaDon();
    public BanVeControl() throws SQLException {
    }

    public void handleMenuTrangChuSelect(BorderPane root) throws SQLException {
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
        public void initGioVe() throws SQLException {
        gdGiove = new GioVe();
        }
        public void initChonVe() throws SQLException {
            gdChonve = new ChonVe("Bán vé");

        }
    public void timKiemChuyenTauHandle(BorderPane root) throws SQLException {
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
                try {
                    listChuyenTau = chuyenTauDAO.getListChuyenTau();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                timChuyenMotChieu(loaiVeString,root,e);
                try {
                    timChuyenKhuHoi(loaiVeString,root,e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println(gaDi);
            System.out.println(gaDen);
            System.out.println(loaiVeString);

        });
    }
    public void timChuyenKhuHoi(String loaiVeString, BorderPane root, MouseEvent e) throws SQLException {
        if(!loaiVeString.equals("khuhoi")){
            return;
        }
        ArrayList<ChuyenTau> listChieuDi = new ArrayList<>();
        ArrayList<ChuyenTau> listChieuVe = new ArrayList<>();

        listChieuDi = getListThoaDieuKien();
        listChieuVe = getListKhuHoiThoaDieuKien();
        if(listChieuDi.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có chuyến chiều đi phù hợp!!");
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            alert.initOwner(stage);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.showAndWait();
            return;
        }
        if(listChieuVe.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có chuyến chiều về phù hợp!!");
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            alert.initOwner(stage);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.showAndWait();
            return;
        }
        try {
            gdChonve.hienThiDanhSachChuyenTau(listChuyenTau);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listChuyenTauFiltered.dat"))) {
            oos.writeObject(listChieuDi);
            System.out.println("Đã ghi ArrayList vào file thành công!");
            System.out.println("size list ghi: " + listChieuDi.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listChieuVe.dat"))) {
            oos.writeObject(listChieuVe);
            System.out.println("Đã ghi ArrayList vào file thành công!");
            System.out.println("size list ghi: " + listChieuVe.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage gdChonVeStage = new Stage();
        gdChonve.start(gdChonVeStage);
        VBox gdChonVeMain = gdChonve.getGdChonVe();
        root.setCenter(gdChonVeMain);
        troLaiGDBanVe(root);
        gdChonve.getTiepTucBtn().setOnMouseClicked(event -> {
            try {
                tiepDenGDChonVeKhuHoi(root);
            } catch (SQLException | IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void timChuyenMotChieu(String loaiVeString, BorderPane root, MouseEvent e){
        if(!loaiVeString.equals("motchieu")){
            return;
        }
        ArrayList<ChuyenTau> listInsert = new ArrayList<>();
        try {
            listInsert = getListThoaDieuKien();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if(listInsert.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có chuyến tàu phù hợp!!");
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            alert.initOwner(stage);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.showAndWait();
            return;
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
        gdChonve.getTiepTucBtn().setOnMouseClicked(event -> {
            try {
                tiepDenGDGioVe(root);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void troLaiGDBanVe(BorderPane root){
        gdChonve.getTrolaiBtn().setOnMouseClicked(e -> {
            root.setCenter(gdBanVe.getGDBanVe());

        });
    }
    public void troLaiGDGioVe(BorderPane root) throws SQLException {
        GioVe gdGioVe = new GioVe();

        Stage gdGioVeStage = new Stage();
        gdGioVe.start(gdGioVeStage);
        VBox gdChinhGioVe = gdGioVe.getGDGioVe();
            gdXuatHoaDon.getBtnTroLai().setOnMouseClicked(e -> {
                root.setCenter(gdChinhGioVe);
            });


    }

    public void troLaiGDChonVe(BorderPane root){
        if(gdGiove != null && gdChonve != null){
            gdGiove.getGioVeTroLaiBtn().setOnMouseClicked(e -> {
                // Hiển thị lại giao diện ChonVe
                root.setCenter(gdChonve.getGdChonVe());
            });
        }
    }
    public void troLaiGDChonVe2(BorderPane root){
        if(gdChonVeKhuHoi != null && gdChonve != null){
            gdChonVeKhuHoi.getTrolaiBtn().setOnMouseClicked(e -> {
                // Hiển thị lại giao diện ChonVe
                root.setCenter(gdChonve.getGdChonVe());
            });
        }
    }

    public void tiepDenGDGioVe(BorderPane root) throws SQLException {
        gdGiove = new GioVe();
        Stage gdGioVeStage = new Stage();
        gdGiove.start(gdGioVeStage);
        root.setCenter(gdGiove.getGDGioVe());

        // Bind nút trở lại
        troLaiGDChonVe(root);
    }
    public void tiepDenGDChonVeKhuHoi(BorderPane root) throws SQLException, IOException, ClassNotFoundException {
        gdChonVeKhuHoi = new ChonVeKhuHoi();
        Stage gdChonVeKhuHoiStage = new Stage();
        gdChonVeKhuHoi.start(gdChonVeKhuHoiStage);
        gdChonVeKhuHoi.getTrolaiBtn().setOnMouseClicked(e -> {
            try {
                tiepDenGDGioVe(root);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        root.setCenter(gdChonVeKhuHoi.getNoiDungChinh());
        gdChonVeKhuHoi.getTiepTucBtn().setOnMouseClicked(e -> {
            GioVe gdGioVe = null;
            try {
                gdGioVe = new GioVe();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Stage gdGioVeStage = new Stage();
            gdGioVe.start(gdGioVeStage);
            VBox gdChinhGioVe  = gdGioVe.getGDChinh();
            root.setCenter(gdChinhGioVe);

            gdGioVe.getGioVeTroLaiBtn().setOnMouseClicked(e2 ->{
                root.setCenter(gdChonVeKhuHoi.getNoiDungChinh());
            });
        });

        // Bind nút trở lại
        troLaiGDChonVe2(root);
    }
    public void tiepDenGDXuatHoaDon(BorderPane root) throws SQLException {
        gdXuatHoaDon = new GiaoDienXuatHoaDon();
        Stage stage = new Stage();
        gdXuatHoaDon.start(stage);
        root.setCenter(gdXuatHoaDon.getNoiDungChinhVe());
        troLaiGDGioVe(root);
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gaden.dat"))) {
            oos.writeObject(gaDen);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gadi.dat"))) {
            oos.writeObject(gaDi);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("loaiVe.dat"))) {
            oos.writeObject(loaiVeString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(dateMotChieu.toString());
            //FILTER THEO GA DEN + THEO NGAY
        for(ChuyenTau chuyenTau : listChuyenTau){
            listGa = hanhTrinh_DAO.getListGaByMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());

                for(Ga g : listGa){
                    if(g.getTenGa().equals(gaDen) && chuyenTau.getNgayGioDi().toLocalDate().equals(dateMotChieu) && checkHanhTrinhMotChieu(chuyenTau)){
                        listChuyenTauFiltered.add(chuyenTau);
                        System.out.println("Them ghe....");
                    }
                }
            }
            System.out.println("size: " + listChuyenTauFiltered.size());
            return listChuyenTauFiltered;
    }
    public ArrayList<ChuyenTau> getListKhuHoiThoaDieuKien() throws SQLException {
        System.out.println("Get list thoa  dieu kien");
        ArrayList<Ga> listGa;
        ArrayList<ChuyenTau> listChuyenTauFiltered = new ArrayList<>();
        HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
        System.out.println("ga den" +  gaDen);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gaden.dat"))) {
            oos.writeObject(gaDen);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gadi.dat"))) {
            oos.writeObject(gaDi);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("loaiVe.dat"))) {
            oos.writeObject(loaiVeString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(dateMotChieu.toString());
        //FILTER THEO GA DEN + THEO NGAY
        for(ChuyenTau chuyenTau : listChuyenTau){
            listGa = hanhTrinh_DAO.getListGaByMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
            for(Ga g : listGa){
                if(g.getTenGa().equals(gaDi) && chuyenTau.getNgayGioDi().toLocalDate().equals(dateKhuHoi) && checkHanhTrinhKhuHoi(chuyenTau)){
                    listChuyenTauFiltered.add(chuyenTau);
                    System.out.println("Them ghe....");
                }
            }
        }
        System.out.println("size: " + listChuyenTauFiltered.size());
        return listChuyenTauFiltered;
    }
    public boolean checkHanhTrinhKhuHoi(ChuyenTau chuyenTau){
        if(chuyenTau.getHanhTrinh().getTenHanhTrinh().equals("Hà Nội ↔ Sài Gòn")){
            return true;
        }
        return false;
    }

    public boolean checkHanhTrinhMotChieu(ChuyenTau chuyenTau){
        if(chuyenTau.getHanhTrinh().getTenHanhTrinh().equals("Sài Gòn ↔ Hà Nội")){
            return true;
        }
            return false;
    }
    public ArrayList<ChuyenTau> getListChuyenTauFiltered() {
            return listChuyenTauFiltered;
    }

}
