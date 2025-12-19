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
public class DoiVeDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Map<GheTrenChuyenTau, KhachHang> mapChuyenTauVaUser;
    public   KhuyenMai ctkmSelected;
    public ArrayList<GheTrenChuyenTau> listGheTrenChuyenTau= new ArrayList<>();
    public ArrayList<KhachHang> listKhachHang =  new ArrayList<>();
    KhachHangDAO  khachHangDAO = new KhachHangDAO();
    DoiTuongGiamGiaDAO doiTuongGiamGiaDAO = new DoiTuongGiamGiaDAO();
    ArrayList<DoiTuongGiamGia> listDoiTuongGiamGia = new ArrayList<>();
    String gaDen;
    String loaiVe;
    Ve vechon;
    ToaTauDAO toaTauDAO = new ToaTauDAO();
    ArrayList<String> listMaVe = new ArrayList<>();
    private String maHoaDon;
    HoaDon hoaDon;
    private String maVeTau;
    Statement myStmt = databaseConnector.connect();
    GaTauDao gaTauDao = new GaTauDao();
    public void goiDAO(){
        System.out.println("Ban Ve dao");
    }
    public DoiVeDAO() throws SQLException {
        goiDAO();
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
            System.out.println(listGheTrenChuyenTau.get(i).isKhuHoi());
            listKhachHang.add(mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)));
            System.out.println("add list kh");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gaDen.dat"))) {
            gaDen = ois.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("veDoi.dat"))) {
            vechon = (Ve)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public double getGiaGheTrenChuyenTauNew(GheTrenChuyenTau gheTrenChuyenTau) throws SQLException {
        double giaTienGhe=0;
        String sql= "Select giaTienGhe from GheTrenChuyenTau where maGheTrenChuyenTau='"+gheTrenChuyenTau.getMaGheTrenChuyenTau()+"' and maChuyenTau='"+gheTrenChuyenTau.getChuyenTau().getMaChuyenTau()+"'";
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            giaTienGhe = rs.getDouble(1);
        }
        return giaTienGhe;
    }
    public String getMaVe() throws SQLException {
        String query = " SELECT COUNT(*) AS soLuong FROM Ve";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String maVe = String.format("VE%02d", soLuong+1);
        return maVe;
    }
    public String getMaKH() throws SQLException {
        String query = "SELECT COUNT(*) AS soLuong FROM KhachHang";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String maKH = String.format("KH%02d", soLuong+1);
        return maKH;
    }
    public String getMaHoaDon() throws SQLException {
        String query = "SELECT COUNT(*) AS soLuong FROM HoaDon";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("HD%02d", soLuong+1);
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
    public double tinhTienVe(GheTrenChuyenTau gheTrenCT) {
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
        return  tienTruocVAT;
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
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        String query = "  UPDATE GheTrenChuyenTau \n" +
                "   SET trangThaiGhe = N'Đã bán'\n" +
                "  where maGheTrenChuyenTau = ?";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,gheTrenCT.getMaGheTrenChuyenTau());
        sta.executeUpdate();
        System.out.println("Cập nhật trạng thái ghế thành công");

    }

    public HoaDon themHoaDon(HoaDon hoaDon) throws SQLException {
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        maHoaDon = getMaHoaDon();
        this.hoaDon = hoaDon;
        hoaDon.setMaHoaDon(maHoaDon);
        String query = "Insert into HoaDon " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,maHoaDon);
        sta.setString(2,hoaDon.getMaLoaiHoaDon().getMaLoaiHoaDon());
        sta.setString(3,hoaDon.getMaNhanVien().getMaNhanVien());
        sta.setString(4,hoaDon.getTenKhachHangThanhToan());
        sta.setString(5,hoaDon.getEmailKhachHangThanhToan());
        sta.setString(6,hoaDon.getCccdKhachHangThanhToan());
        sta.setString(7,hoaDon.getSdtKhachHangThanhToan());
        sta.setObject(8,LocalDateTime.now());
        sta.setDouble(9,hoaDon.getTongTien());
        sta.setString(10,hoaDon.getDiaChiKhachHangThanhToan());

        sta.executeUpdate();
        System.out.println("Thêm hóa đơn thành công");
        return hoaDon;

    }
    public String getMaCTHoaDon() throws SQLException {
        String query = "SELECT COUNT(*) AS soLuong FROM ChiTietHoaDon";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("CTHD%02d", soLuong+1);
        return ma;
    }
    //    HoaDon hoaDon, Ve ve, String moTa, double donGia, double thueVAT, double thanhTien, String LoaiHoaDonChoVe
    public ChiTietHoaDon themCTHoaDon(HoaDon hoaDon, Ve ve,GheTrenChuyenTau gheTrenChuyenTau,KhuyenMai khuyenMai) throws SQLException {
        String maCTHoaDon = getMaCTHoaDon();
        String loaiHoaDon;
        if(listGheTrenChuyenTau.size()>=10){
            loaiHoaDon = "Vé tập thể";
        }else{
            loaiHoaDon = "Vé cá nhân";
        }

        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon,ve,ve.getTenTau(),gheTrenChuyenTau.getGiaTienGhe(),8,ve.getGiaVe(),loaiHoaDon);
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        System.out.println("gia ve: "+ ve.getGiaVe());

        String query = "Insert into ChiTietHoaDon " +
                "Values (?,?,?,?,?,?,?,?)";
        PreparedStatement sta = con.prepareStatement(query);
        sta.setString(1,maCTHoaDon);
        sta.setString(2,chiTietHoaDon.getHoaDon().getMaHoaDon());
        sta.setString(3,chiTietHoaDon.getVeTau().getMaVeTau());
        sta.setString(4,chiTietHoaDon.getMoTa());
        sta.setDouble(5,gheTrenChuyenTau.getGiaTienGhe());
        sta.setDouble(6,chiTietHoaDon.getThueVAT());
        sta.setDouble(7,ve.getGiaVe());
        sta.setString(8,chiTietHoaDon.getLoaiHoaDonChoVe());
        sta.executeUpdate();
        System.out.println("Thêm ct hóa đơn thành công");
        return chiTietHoaDon;
    }
    public String getMaLichSuTuongTac() throws SQLException {
        String query = "SELECT COUNT(*) AS soLuong FROM LichSuTuongTacVe";
        ResultSet rs = myStmt.executeQuery(query);
        int soLuong = 0;
        while (rs.next()) {
            soLuong = rs.getInt(1);
        }
        rs.close();
        String ma = String.format("LSTT%02d", soLuong+1);
        return ma;
    }
    public void themLichSuTuongTacVe(Ve veTau,KhuyenMai khuyenMai, double giaTriChenhLech, String maTT) throws SQLException {
        String maLichSuTuongTac =  getMaLichSuTuongTac();
        String maLoaiTuongTac = maTT;
        String maVeTau = veTau.getMaVeTau();
        LocalDateTime ngayTuongTac =  LocalDateTime.now();
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
    public String checkCCCDKhachHang(String cccd, String doiTuong) throws SQLException {
        String query = "  select maKhachHang from KhachHang where cccd = '" +cccd+"' and doiTuong = '"+doiTuong+"'";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    public void updateGiaGheLuuDong(GheTrenChuyenTau g) throws SQLException {
        System.out.println("Update gia ghe luu dong");
        double giaTienGhe= g.getChuyenTau().getGiaCuocTrenChuyenTau() * gaTauDao.getCuLiBangTenGa(gaDen) + g.getGiaTienGhe();
        System.out.println("ga den :"+gaDen);
        System.out.println("gia tien ghe :"+giaTienGhe);
        String query = "UPDATE GheTrenChuyenTau" +
                "      SET giaTienGhe = ?" +
                "      where maGheTrenChuyenTau = ?";
        PreparedStatement sta = databaseConnector.getInstance().getConnection().prepareStatement(query);
        sta.setDouble(1, giaTienGhe);
        sta.setString(2,g.getMaGheTrenChuyenTau());
        sta.executeUpdate();
    }
    public String maGheTrenChuyenTauFromVe(String maVe) throws SQLException {
        String maGheTrenChuyenTau = "";
        String query = "select maGheTrenChuyenTau from Ve where maVeTau = '"+maVe+"'";
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()) {
            maGheTrenChuyenTau = rs.getString(1);
        }
        return maGheTrenChuyenTau;
    }
    public void themVe() throws SQLException {
        ArrayList<Ve> listVe = new ArrayList<>();
        Connection con = (Connection) databaseConnector.getInstance().getConnection();
        for(int i=0;i<listGheTrenChuyenTau.size();i++) {

            KhachHang kh = new KhachHang(getMaKH(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getHoten(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd(), mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getDoiTuong());
            String maKhachHang="";
            if(checkCCCDKhachHang(kh.getCccd(), kh.getDoiTuong()) != null){
                maKhachHang = checkCCCDKhachHang(kh.getCccd(),kh.getDoiTuong());
            }else {
                if(khachHangDAO.themKhachHang(kh)) {
                    System.out.println("Thêm Thành Công - Mã KH: " + kh.getMaKhachHang());
                }
                maKhachHang = kh.getMaKhachHang();
            }
            Ve veTau;
            maVeTau = getMaVe();
                veTau = new Ve(maVeTau,vechon.getGaDi(),vechon.getGaDen(),listGheTrenChuyenTau.get(i).getChuyenTau().getTau().getLoaiTau().getTenLoaiTau(),listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDi(),listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDen(),
                        checkThuTuToa(listGheTrenChuyenTau.get(i)),listGheTrenChuyenTau.get(i).getGheNgoi().getKhoangTau().getSoKhoang(),listGheTrenChuyenTau.get(i).getGheNgoi().getTang().getSoTang(),
                        listGheTrenChuyenTau.get(i).getGheNgoi().getSoGhe(),loaiVe,mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd(),tinhTienVe(listGheTrenChuyenTau.get(i)),
                        null,"chưa đổi","hoạt động",listGheTrenChuyenTau.get(i).getChuyenTau(),kh,ctkmSelected,getDoiTuong(listGheTrenChuyenTau.get(i)));
                listVe.add(veTau);


            String setGhiChuDaDoiVe = "Update Ve set ghiChu = N'Đã được đổi', trangThaiVe=N'Kết thúc' where maVeTau = '"+vechon.getMaVeTau()+"'";
            PreparedStatement pst = null;
            pst = con.prepareStatement(setGhiChuDaDoiVe);
            pst.executeUpdate();

            System.out.println(vechon.getGaDen());
            if(vechon.getGaDen().trim().equals("Hà Nội") || vechon.getGaDen().trim().equals("Sài Gòn")){
                String setGiaGheLuuDongVaTrangThaiGhe = "Update GheTrenChuyenTau set trangThaiGhe = N'Còn trống' where maGheTrenChuyenTau='"+maGheTrenChuyenTauFromVe(vechon.getMaVeTau())+"'";
                PreparedStatement pst1 = null;
                pst1 = con.prepareStatement(setGiaGheLuuDongVaTrangThaiGhe);
                pst1.executeUpdate();

            }else{
                String setGiaGheLuuDongVaTrangThaiGhe = "Update GheTrenChuyenTau set trangThaiGhe = N'Còn trống', giaTienGhe = 0 where maGheTrenChuyenTau='"+maGheTrenChuyenTauFromVe(vechon.getMaVeTau())+"'";
                PreparedStatement pst1 = null;
                pst1 = con.prepareStatement(setGiaGheLuuDongVaTrangThaiGhe);
                pst1.executeUpdate();
            }

            System.out.println("Đã set ghi chú ves cũ");


            String insertVe = "INSERT INTO Ve " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sta = null;
            sta = con.prepareStatement(insertVe);
            sta.setString(1,maVeTau);
            sta.setString(2,veTau.getGaDi());
            sta.setString(3,veTau.getGaDen());
            sta.setString(4, listGheTrenChuyenTau.get(i).getChuyenTau().getTau().getLoaiTau().getTenLoaiTau());
            sta.setObject(5,listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDi());
            sta.setObject(6, listGheTrenChuyenTau.get(i).getChuyenTau().getNgayGioDen());
            sta.setInt(7,checkThuTuToa(listGheTrenChuyenTau.get(i)));
            sta.setInt(8,listGheTrenChuyenTau.get(i).getGheNgoi().getKhoangTau().getSoKhoang());
            sta.setInt(9,listGheTrenChuyenTau.get(i).getGheNgoi().getTang().getSoTang());
            sta.setInt(10,listGheTrenChuyenTau.get(i).getGheNgoi().getSoGhe());
            sta.setString(11, loaiVe);
            sta.setString(12, mapChuyenTauVaUser.get(listGheTrenChuyenTau.get(i)).getCccd());
            sta.setDouble(13,listGheTrenChuyenTau.get(i).getGiaTienGhe() * ((100-getDoiTuong(listGheTrenChuyenTau.get(i)).getGiaTriPhanTramGiamGia())/100));
            sta.setString(14,null);
            sta.setString(15,vechon.getMaVeTau());
            sta.setString(16,"Hoạt động");
            sta.setString(17,listGheTrenChuyenTau.get(i).getChuyenTau().getMaChuyenTau());
            sta.setString(18,maKhachHang);
            sta.setString(19,ctkmSelected.getMaKhuyenMai());
            sta.setString(20,getDoiTuong(listGheTrenChuyenTau.get(i)).getMaDoiTuongGiamGia());
            sta.setString(21,"false");
            sta.setString(22,listGheTrenChuyenTau.get(i).getMaGheTrenChuyenTau());
            sta.executeUpdate();
            System.out.println("Thêm vé đổi thành công");

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

