package fourcore.Control;

import fourcore.Entity.ChuyenTau;
import fourcore.Entity.Ga;
import fourcore.Entity.Ve;
import fourcore.GiaoDien.*;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.HanhTrinh_DAO;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DoiVeControl {
    ArrayList<ChuyenTau> listChuyenTau = new ArrayList<>();
    ChonVeDoi gdChonve = null;
    LocalDate ngaydi;
    Ve vechon = null;
    GiaoDienDoiVe doiVe;
    GioVeDoiVe gdGioVeDoiVe;
    GiaoDienXuatHoaDonDoiVe gdXuatHoaDonDoiVe;
    public void handleMenuTrangChuSelect(BorderPane root) {
        doiVe = new GiaoDienDoiVe();
        Stage doiVeStage = new Stage();
        doiVe.start(doiVeStage);
        VBox giaoDienDoiVe = doiVe.getNoiDungChinhVe();
        root.setCenter(giaoDienDoiVe);
    }
    public void initChonVe() throws SQLException {
        gdChonve = new ChonVeDoi("Đổi vé");

    }
    public void tiepDenGDXuatHoaDon(BorderPane root,VBox noidungchinh) throws SQLException {
        gdXuatHoaDonDoiVe = new GiaoDienXuatHoaDonDoiVe();
        Stage stage = new Stage();
        gdXuatHoaDonDoiVe.start(stage);
        root.setCenter(gdXuatHoaDonDoiVe.getNoiDungChinhVe());
        troLaiGDGioVe(root , noidungchinh);
    }
    public void troLaiGDGioVe(BorderPane root, VBox noidungchinh) throws SQLException {
        gdXuatHoaDonDoiVe.getBtnTroLai().setOnMouseClicked(e -> {
            root.setCenter(noidungchinh);
        });


    }


    public void handleSearchChuyenTau(BorderPane root) {
        ChuyenTauDAO chuyenTauDAO = null;
        try {
            chuyenTauDAO = new ChuyenTauDAO();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        listChuyenTau = chuyenTauDAO.listChuyenTau;
        doiVe.getBtnApDung().setOnMouseClicked(e->{
            if(doiVe.getDate().getValue()==null){
                doiVe.getDescription().setText("Ngày đổi không được để trống");
            }else if(doiVe.getDate().getValue().isBefore(LocalDate.now())){
                doiVe.getDescription().setText("Ngày đổi không được trước ngày hiện tại");
            }else{
                ngaydi = doiVe.getDate().getValue();
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("veDoi.dat"))) {
                    vechon = (Ve)ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ngayDoi.dat"))) {
                    oos.writeObject(doiVe.getDate().getValue());
                    System.out.println("ghi ngay doi thanh cong" + ngaydi);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gaDen.dat"))) {
                    oos.writeObject(vechon.getGaDen());
                    System.out.println("ghi ga den thanh cong: "+ vechon.getGaDen());


                    if(vechon.getGaDi().equals("Sài Gòn")){
                        timChuyenMotChieu("motchieu",root,e);

                    }else{
                        try {
                            timChuyenKhuHoi("khuhoi",root,e);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    System.out.println(vechon.getGaDi());
                    System.out.println(vechon.getGaDen());
                    if(vechon.getGaDen().equals("Sài Gòn")){
                        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("gaDen.dat"));
                        oos2.writeObject(vechon.getGaDi());
                        System.out.println("ghi ga den thanh cong: "+ vechon.getGaDi());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });


    }

    public void timChuyenMotChieu(String loaiVeString, BorderPane root, MouseEvent e){
        System.out.println("Tim chuyen doi ve");
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
        doiVe.popupStage.close();
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
        gdChonve.getTiepTucBtn().setOnMouseClicked(event -> {
            try {
                tiepDenGDGioVe(root);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gdChonve.getTrolaiBtn().setOnMouseClicked(event -> {
           root.setCenter(doiVe.getNoiDungChinhVe());
        });
    }
    public void timChuyenKhuHoi(String loaiVeString, BorderPane root, MouseEvent e) throws SQLException {
        if(!loaiVeString.equals("khuhoi")){
            return;
        }
        ArrayList<ChuyenTau> listChieuVe = new ArrayList<>();
        listChieuVe = getListKhuHoiThoaDieuKien();
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
        gdChonve.hienThiDanhSachChuyenTau(listChieuVe);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listChuyenTauFiltered.dat"))) {
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
        troLaiGDChonVe(root);
        gdChonve.getTiepTucBtn().setOnMouseClicked(event -> {
            try {
                tiepDenGDGioVe(root);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public ArrayList<ChuyenTau> getListKhuHoiThoaDieuKien() throws SQLException {
        System.out.println("Get list thoa  dieu kien");
        ArrayList<Ga> listGa;
        ArrayList<ChuyenTau> listChuyenTauFiltered = new ArrayList<>();
        HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
        System.out.println("ga den" +  vechon.getGaDen());


        //FILTER THEO GA DEN + THEO NGAY
        for(ChuyenTau chuyenTau : listChuyenTau){
            listGa = hanhTrinh_DAO.getListGaByMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());
            for(Ga g : listGa){
                if(g.getTenGa().equals(vechon.getGaDi()) && chuyenTau.getNgayGioDi().toLocalDate().equals(ngaydi) && checkHanhTrinhKhuHoi(chuyenTau)){
                    listChuyenTauFiltered.add(chuyenTau);
                    System.out.println("Them ghe....");
                }
            }
        }
        System.out.println("size: " + listChuyenTauFiltered.size());
        return listChuyenTauFiltered;
    }

    public void tiepDenGDGioVe(BorderPane root) throws SQLException {
        gdGioVeDoiVe = new GioVeDoiVe();
        Stage gdGioVeStage = new Stage();
        gdGioVeDoiVe.start(gdGioVeStage);
        root.setCenter(gdGioVeDoiVe.getGDGioVe());

        // Bind nút trở lại
        troLaiGDChonVe(root);
    }
    public void troLaiGDChonVe(BorderPane root){
        if(gdGioVeDoiVe != null && gdChonve != null){
            gdGioVeDoiVe.getGioVeTroLaiBtn().setOnMouseClicked(e -> {
                // Hiển thị lại giao diện ChonVe
                root.setCenter(gdChonve.getGdChonVe());
            });
        }
    }
    public ArrayList<ChuyenTau> getListThoaDieuKien() throws SQLException {
        System.out.println("Get list thoa  dieu kien");

        ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
        listChuyenTau = chuyenTauDAO.listChuyenTau;
        ArrayList<Ga> listGa;
        ArrayList<ChuyenTau> listChuyenTauFiltered = new ArrayList<>();
        HanhTrinh_DAO hanhTrinh_DAO = new HanhTrinh_DAO();
        System.out.println("ga den" +  vechon.getGaDen());
        //FILTER THEO GA DEN + THEO NGAY
        for(ChuyenTau chuyenTau : listChuyenTau){
            listGa = hanhTrinh_DAO.getListGaByMaHanhTrinh(chuyenTau.getHanhTrinh().getMaHanhTrinh());

            for(Ga g : listGa){
                if(g.getTenGa().equals(vechon.getGaDen()) && chuyenTau.getNgayGioDi().toLocalDate().equals(ngaydi) && checkHanhTrinhMotChieu(chuyenTau)){
                    listChuyenTauFiltered.add(chuyenTau);
                    System.out.println("Them ghe....");
                }
            }
        }
        System.out.println("size: " + listChuyenTauFiltered.size());
        return listChuyenTauFiltered;
    }
    public boolean checkHanhTrinhMotChieu(ChuyenTau chuyenTau){
        if(chuyenTau.getHanhTrinh().getTenHanhTrinh().equals("Sài Gòn ↔ Hà Nội")){
            return true;
        }
        return false;
    }
    public boolean checkHanhTrinhKhuHoi(ChuyenTau chuyenTau){
        if(chuyenTau.getHanhTrinh().getTenHanhTrinh().equals("Hà Nội ↔ Sài Gòn")){
            return true;
        }
        return false;
    }
}
