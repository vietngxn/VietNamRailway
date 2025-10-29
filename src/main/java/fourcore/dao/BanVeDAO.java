package fourcore.dao;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.*;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

//Tao Khach Hang
//Tao Ve
//Tao HoaDon
//Tao CT Hoa Don
public class BanVeDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Map<GheTrenChuyenTau, KhachHang> mapChuyenTauVaUser;
    public   KhuyenMai ctkmSelected;
    public ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau= new ArrayList<>();
    public ArrayList<KhachHang> listKhachHang =  new ArrayList<>();
    KhachHangDAO  khachHangDAO = new KhachHangDAO();
    DoiTuongGiamGiaDAO doiTuongGiamGiaDAO = new DoiTuongGiamGiaDAO();
    ArrayList<DoiTuongGiamGia> listDoiTuongGiamGia = new ArrayList<>();
    String gaDi;
    String gaDen;
    String loaiVe;
    ToaTauDAO toaTauDAO = new ToaTauDAO();
    ArrayList<String> listMaVe = new ArrayList<>();
    private String maHoaDon;
    HoaDon hoaDon;
    private String maVeTau;

    public BanVeDAO() throws SQLException {
        listDoiTuongGiamGia = doiTuongGiamGiaDAO.getListDoiTuongGiamGia();
        loadDataFromFile();
    }
    public void loadDataFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mapGheVaKhachHang.dat"))) {
            mapChuyenTauVaUser = (Map<GheTrenChuyenTau, KhachHang>) ois.readObject();
            System.out.println("map đã đọc: " + mapChuyenTauVaUser);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("KMSelected.dat"))) {
            ctkmSelected = (KhuyenMai) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        listGheTrenChuyenTau= new ArrayList<>(mapChuyenTauVaUser.keySet());
        for(int i=0;i<listGheTrenChuyenTau.size();i++){
            listKhachHang.add(mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)));
            System.out.println("add list kh");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gaDen.dat"))) {
            gaDen = ois.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gadi.dat"))) {
            gaDi = ois.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("loaiVe.dat"))) {
            loaiVe = ois.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void themKhachHang() throws SQLException {

        for (KhachHang khachHang : listKhachHang) {
            try {
                // Lấy số lượng khách hàng hiện tại từ database
                int sl = khachHangDAO.getListKhachHang().size();
                sl += 1; // Tăng lên 1 cho khách hàng mới

                String makh = "";
                if(sl >= 1 && sl <= 9) {
                    makh = "KH00" + sl;
                }
                else if(sl >= 10 && sl <= 99) {
                    makh = "KH0" + sl;
                }
                else {
                    makh = "KH" + sl;
                }


            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    public String getMaVe() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = " SELECT COUNT(*) AS soLuong FROM Ve";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String maVe = String.format("V%05d", soLuong+1);
        return maVe;
    }
    public String getMaKH() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "SELECT COUNT(*) AS soLuong FROM KhachHang";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String maKH = String.format("KH%04d", soLuong+1);
        return maKH;
    }
    public String getMaHoaDon() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "SELECT COUNT(*) AS soLuong FROM HoaDon";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("HD%04d", soLuong+1);
        return ma;
    }

    public int checkThuTuToa(GheTrenChuyenTau gheTrenChuyenTau) throws SQLException {

        int soToa = 0;
        ArrayList<ToaTau> listToaTauTrenChuyen = toaTauDAO.getListToaTauByMaCT(gheTrenChuyenTau.getChuyenTau().getMaChuyenTau());
        for (int i = 0; i < listToaTauTrenChuyen.size(); i++) {
            if (listToaTauTrenChuyen.get(i).getMaToaTau()
                    .equals(gheTrenChuyenTau.getGheNgoi().getToaTau().getMaToaTau())) {
                soToa = i + 1;
                break;
            }
        }


        return soToa;
    }
    public double tinhTienVe(KhuyenMai khuyenMai, GheTrenChuyenTau gheTrenCT) throws SQLException {
        double tienVe= 0;
        double tienVKm = 0;
        double tienTruocVAT=0;
        double giaGhe = gheTrenCT.getGiaTienGhe();
        String tenDoiTuong = mapChuyenTauVaUser.get(gheTrenCT).getDoiTuong();
        double phanTramKhuyenMai = 0;
        double phanTramGiamDoiTuong = 0;
        for(int x=0; x<listDoiTuongGiamGia.size(); x++){
            if(listDoiTuongGiamGia.get(x).getTenDoiTuongGiamGia().equals(tenDoiTuong)){
                phanTramGiamDoiTuong = listDoiTuongGiamGia.get(x).getGiaTriPhanTramGiamGia();
                break;
            }
        }
        if(ctkmSelected!=null){
            phanTramKhuyenMai = ctkmSelected.getGiaTriPhanTramKhuyenMai();
        }
        double giaTong  = giaGhe*((100-phanTramKhuyenMai)/100);
        giaTong = giaTong*((100-phanTramGiamDoiTuong)/100);
        tienTruocVAT += giaTong;
        return  tienTruocVAT *1.08;
    }
    public DoiTuongGiamGia getDoiTuong(GheTrenChuyenTau gheTrenCT) throws SQLException {
        DoiTuongGiamGiaDAO doiTuongGiamGiaDAO = new DoiTuongGiamGiaDAO();
        String doiTuong = mapChuyenTauVaUser.get(gheTrenCT).getDoiTuong();
        ArrayList<DoiTuongGiamGia> dtggList = doiTuongGiamGiaDAO.getListDoiTuongGiamGia();
        for (DoiTuongGiamGia dtgg : dtggList) {
            if(dtgg.getTenDoiTuongGiamGia().equals(doiTuong)){
                return dtgg;
            }
        }
        return null;
    }
    public void updateTrangThaiGheTrenChuyenTau(GheTrenChuyenTau gheTrenCT) throws SQLException {
        Statement myStmt = databaseConnector.connect();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        String query = "  UPDATE GheTrenChuyenTau \n" +
                "   SET trangThaiGhe = N'đã bán'\n" +
                "  where maGheTrenChuyenTau = ?";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,gheTrenCT.getMaGheTrenChuyenTau());
        sta.executeUpdate();
        System.out.println("Cập nhật trạng thái ghế thành công");

    }

    public HoaDon themHoaDon(HoaDon hoaDon) throws SQLException {
        Statement myStmt = databaseConnector.connect();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        maHoaDon = getMaHoaDon();
        this.hoaDon = hoaDon;
        hoaDon.setMaHoaDon(maHoaDon);
        String query = "Insert into HoaDon " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,maHoaDon);
        sta.setString(2,hoaDon.getMaNhanVien().getMaNhanVien());
        sta.setObject(3,LocalDateTime.now());
        sta.setDouble(4,hoaDon.getTongTien());
        sta.setString(5,hoaDon.getMaLoaiHoaDon().getMaLoaiHoaDon());
        sta.setString(6,hoaDon.getTenKhachHangThanhToan());
        sta.setString(7,hoaDon.getEmailKhachHangThanhToan());
        sta.setString(8,hoaDon.getCccdKhachHangThanhToan());
        sta.setString(9,hoaDon.getSdtKhachHangThanhToan());
        sta.executeUpdate();
        System.out.println("Thêm hóa đơn thành công");
return hoaDon;

    }
    public String getMaCTHoaDon() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "SELECT COUNT(*) AS soLuong FROM ChiTietHoaDon";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("CTHD%04d", soLuong+1);
        return ma;
    }
//    HoaDon hoaDon, Ve ve, String moTa, double donGia, double thueVAT, double thanhTien, String LoaiHoaDonChoVe
    public void themCTHoaDon(HoaDon hoaDon, Ve ve,GheTrenChuyenTau gheTrenChuyenTau,KhuyenMai khuyenMai) throws SQLException {
        String maCTHoaDon = getMaCTHoaDon();
        String loaiHoaDon;
        if(listGheTrenChuyenTau.size()>=10){
            loaiHoaDon = "Vé tập thể";
        }else{
            loaiHoaDon = "Vé cá nhân";
        }
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon,ve,ve.getTenTau(),tinhTienVe(khuyenMai,gheTrenChuyenTau),8,tinhTienVe(khuyenMai,gheTrenChuyenTau)*1.08,loaiHoaDon);
        Statement myStmt = databaseConnector.connect();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        String query = "Insert into ChiTietHoaDon " +
                "Values (?,?,?,?,?,?,?,?)";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,maCTHoaDon);
        sta.setString(2,chiTietHoaDon.getHoaDon().getMaHoaDon());
        sta.setString(3,chiTietHoaDon.getVeTau().getMaVeTau());
        sta.setString(4,chiTietHoaDon.getMoTa());
        sta.setDouble(5,chiTietHoaDon.getDonGia());
        sta.setDouble(6,chiTietHoaDon.getThueVAT());
        sta.setDouble(7,chiTietHoaDon.getThanhTien());
        sta.setString(8,chiTietHoaDon.getLoaiHoaDonChoVe());
        sta.executeUpdate();
        System.out.println("Thêm ct hóa đơn thành công");
    }
    public String getMaLichSuTuongTac() throws SQLException {
        Statement myStmt = databaseConnector.connect();
        String query = "SELECT COUNT(*) AS soLuong FROM LichSuTuongTacVe";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("TT%03d", soLuong+1);
        return ma;
    }
    public void themLichSuTuongTacVe(Ve veTau,KhuyenMai khuyenMai, GheTrenChuyenTau gheTrenChuyenTau) throws SQLException {
        String maLichSuTuongTac =  getMaLichSuTuongTac();
        String maLoaiTuongTac = "LT01";
        String maVeTau = veTau.getMaVeTau();
        double giaTriChenhLech = tinhTienVe(khuyenMai,gheTrenChuyenTau);
        LocalDateTime ngayTuongTac =  LocalDateTime.now();
        Statement myStmt = databaseConnector.connect();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        String query = "Insert into LichSuTuongTacVe " +
                "Values (?,?,?,?,?)";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,maLichSuTuongTac);
        sta.setString(2,maLoaiTuongTac);
        sta.setString(3,maVeTau);
        sta.setDouble(4,giaTriChenhLech);
        sta.setObject(5,ngayTuongTac);

        sta.executeUpdate();
        System.out.println("Thêm lịch sử tương tác thành công");

    }

    public void themVe() throws SQLException {
        ArrayList<Ve> listVe = new ArrayList<>();
        Statement myStmt = databaseConnector.connect();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        for(int i=0;i<listGheTrenChuyenTau.size();i++) {
            KhachHang kh = new KhachHang(getMaKH(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getHoten(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getDoiTuong());

            if(khachHangDAO.themKhachHang(kh)) {
                System.out.println("Thêm Thành Công - Mã KH: " + kh.getMaKhachHang());
            }
            maVeTau = getMaVe();
            Ve veTau = new Ve(maVeTau,gaDi,gaDen,listGheTrenChuyenTau.get(i).getChuyenTau().getTau().getTenTau(),listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDi(),listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDen(),
                    checkThuTuToa(listGheTrenChuyenTau.get(i)),listGheTrenChuyenTau.get(i).getGheNgoi().getKhoangTau().getSoKhoang(),listGheTrenChuyenTau.get(i).getGheNgoi().getTang().getSoTang(),
                    listGheTrenChuyenTau.get(i).getGheNgoi().getSoGhe(),loaiVe,mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd(),tinhTienVe(ctkmSelected,listGheTrenChuyenTau.get(i)),
                    null,"chưa đổi","hoạt động",listGheTrenChuyenTau.get(i).getChuyenTau(),kh,ctkmSelected,getDoiTuong(listGheTrenChuyenTau.get(i)));
            listVe.add(veTau);



            String insertVe = "INSERT INTO Ve " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sta = null;
            sta = con.prepareStatement(insertVe);
            sta.setString(1,maVeTau);
            sta.setString(2,gaDi);
            sta.setString(3,gaDen);
            sta.setString(4, listGheTrenChuyenTau.get(i).getChuyenTau().getTau().getTenTau());
            sta.setObject(5,listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDi());
            sta.setObject(6, listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDen());
            sta.setInt(7,checkThuTuToa(listGheTrenChuyenTau.get(i)));
            sta.setInt(8,listGheTrenChuyenTau.get(i).getGheNgoi().getKhoangTau().getSoKhoang());
            sta.setInt(9,listGheTrenChuyenTau.get(i).getGheNgoi().getTang().getSoTang());
            sta.setInt(10,listGheTrenChuyenTau.get(i).getGheNgoi().getSoGhe());
            sta.setString(11, loaiVe);
            sta.setString(12, mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd());
            sta.setDouble(13,tinhTienVe(ctkmSelected,listGheTrenChuyenTau.get(i)));
            sta.setString(14,null);
            sta.setString(15,"chưa đổi");
            sta.setString(16,"hoạt động");
            sta.setString(17,listGheTrenChuyenTau.get(i).getChuyenTau().getMaChuyenTau());
            sta.setString(18,kh.getMaKhachHang());
            sta.setString(19,ctkmSelected.getMaKhuyenMai());
            sta.setString(20,getDoiTuong(listGheTrenChuyenTau.get(i)).getMaDoiTuongGiamGia());
            sta.executeUpdate();
            System.out.println("Thêm vé thành công");

            updateTrangThaiGheTrenChuyenTau(listGheTrenChuyenTau.get(i));
            File file = new File("veTau.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(listVe);
                System.out.println("✅ Đã ghi " + listVe + " vào file: " + file.getAbsolutePath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

    }
public static void main(String args[]) throws SQLException {
	BanVeDAO bvdao = new BanVeDAO();
    System.out.println(bvdao.listGheTrenChuyenTau.get(0).getGheNgoi().getKhoangTau().getMaKhoang());
}
}

