package fourcore.dao;

import fourcore.Entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenTau_Dao {
    public ArrayList<ToaTau> toaTauList = new ArrayList<ToaTau>();
    public ArrayList <GheTrenChuyenTau> listGheTrenChuyenTau = new ArrayList<>();
    public ArrayList<GheNgoi> listGheNgoi;
    public ArrayList<ChuyenTau> listChuyenTau = new ArrayList<>();
    public ChuyenTau_Dao(){

    }
    public LoaiToaTau toaGheXoay = new LoaiToaTau("GN01", "Ghế xoay lên tầng vũ trụ");
    public LoaiToaTau toaGiuongNam = new LoaiToaTau("GN02", "Giường nằm phê pha");

    public LoaiTau loaiTau1 = new LoaiTau("LT01", "TauHoaBacNam", 12000);
    public Tau tau1 = new Tau("SE6", "TauHi", loaiTau1);
    public Tau tau2 = new Tau("SE2", "TauHi", loaiTau1);
    public Tau tau3 = new Tau("SE3", "TauHi", loaiTau1);

    public ChuyenTau chuyen1 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 3, 15, 30),LocalDateTime.of(2025, 10, 14, 3, 30),tau1, "CT001");
    public ChuyenTau chuyen2 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 3, 20, 30),LocalDateTime.of(2025, 7, 14, 12, 30),tau2, "CT002");
    public ChuyenTau chuyen3 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 1, 7, 00),LocalDateTime.of(2025, 9, 14, 4, 30),tau3, "CT003");
    public ChuyenTau chuyen4 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "CT004");
    public ChuyenTau chuyen5 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "CT005");
    public ChuyenTau chuyen6 = new ChuyenTau(10000 , LocalDateTime.of(2025, 10, 2, 12, 00),LocalDateTime.of(2025, 4, 14, 5, 30),tau2, "CT006");

    public ArrayList<ChuyenTau> setListChuyenTau() {
        listChuyenTau.add(chuyen1);
        listChuyenTau.add(chuyen2);
        listChuyenTau.add(chuyen3);
        listChuyenTau.add(chuyen4);
        listChuyenTau.add(chuyen5);
        listChuyenTau.add(chuyen6);
        return listChuyenTau;
    }
    public ToaTau toaTau1 = new ToaTau("GN01", "LGN01", 36, "Sẵn sàng",toaGheXoay);
    public ToaTau toaTau2 = new ToaTau("GN02", "LGN01", 18, "Sẵn sàng",toaGiuongNam);
    public ArrayList<ToaTau> getListToaTau() {
        ArrayList<ToaTau> listToaTau = new ArrayList<>();
        listToaTau.add(toaTau1);
        listToaTau.add(toaTau2);
        return listToaTau;
    }
    public LoaiGhe loaiGhe = new LoaiGhe("LG01", "GheNgoi", 300000);
    public Tang tang = new Tang("TG01", 1, 5000);
    public KhoangTau khoangTau = new KhoangTau("KG01", 1);
    GheNgoi ghe1  = new GheNgoi("GH01", loaiGhe, tang, khoangTau, toaTau1, 1, 0, false);
    GheNgoi ghe2  = new GheNgoi("GH02", loaiGhe, tang, khoangTau, toaTau1, 2, 0, false);
    GheNgoi ghe3  = new GheNgoi("GH03", loaiGhe, tang, khoangTau, toaTau1, 3, 0, false);
    GheNgoi ghe4  = new GheNgoi("GH04", loaiGhe, tang, khoangTau, toaTau1, 4, 0, false);
    GheNgoi ghe5  = new GheNgoi("GH05", loaiGhe, tang, khoangTau, toaTau1, 5, 0, false);
    GheNgoi ghe6  = new GheNgoi("GH06", loaiGhe, tang, khoangTau, toaTau1, 6, 0, false);
    GheNgoi ghe7  = new GheNgoi("GH07", loaiGhe, tang, khoangTau, toaTau1, 7, 0, false);
    GheNgoi ghe8  = new GheNgoi("GH08", loaiGhe, tang, khoangTau, toaTau1, 8, 0, false);
    GheNgoi ghe9  = new GheNgoi("GH09", loaiGhe, tang, khoangTau, toaTau1, 9, 0, false);
    GheNgoi ghe10 = new GheNgoi("GH10", loaiGhe, tang, khoangTau, toaTau1, 10, 0, false);
    GheNgoi ghe11 = new GheNgoi("GH11", loaiGhe, tang, khoangTau, toaTau1, 11, 0, false);
    GheNgoi ghe12 = new GheNgoi("GH12", loaiGhe, tang, khoangTau, toaTau1, 12, 0, false);
    GheNgoi ghe13 = new GheNgoi("GH13", loaiGhe, tang, khoangTau, toaTau1, 13, 0, false);
    GheNgoi ghe14 = new GheNgoi("GH14", loaiGhe, tang, khoangTau, toaTau1, 14, 0, false);
    GheNgoi ghe15 = new GheNgoi("GH15", loaiGhe, tang, khoangTau, toaTau1, 15, 0, false);
    GheNgoi ghe16 = new GheNgoi("GH16", loaiGhe, tang, khoangTau, toaTau1, 16, 0, false);
    GheNgoi ghe17 = new GheNgoi("GH17", loaiGhe, tang, khoangTau, toaTau1, 17, 0, false);
    GheNgoi ghe18 = new GheNgoi("GH18", loaiGhe, tang, khoangTau, toaTau1, 18, 0, false);
    GheNgoi ghe19 = new GheNgoi("GH19", loaiGhe, tang, khoangTau, toaTau1, 19, 0, false);
    GheNgoi ghe20 = new GheNgoi("GH20", loaiGhe, tang, khoangTau, toaTau1, 20, 0, false);
    GheNgoi ghe21 = new GheNgoi("GH21", loaiGhe, tang, khoangTau, toaTau1, 21, 0, false);
    GheNgoi ghe22 = new GheNgoi("GH22", loaiGhe, tang, khoangTau, toaTau1, 22, 0, false);
    GheNgoi ghe23 = new GheNgoi("GH23", loaiGhe, tang, khoangTau, toaTau1, 23, 0, false);
    GheNgoi ghe24 = new GheNgoi("GH24", loaiGhe, tang, khoangTau, toaTau1, 24, 0, false);
    GheNgoi ghe25 = new GheNgoi("GH25", loaiGhe, tang, khoangTau, toaTau1, 25, 0, true);
    GheNgoi ghe26 = new GheNgoi("GH26", loaiGhe, tang, khoangTau, toaTau1, 26, 0, true);
    GheNgoi ghe27 = new GheNgoi("GH27", loaiGhe, tang, khoangTau, toaTau1, 27, 0, true);
    GheNgoi ghe28 = new GheNgoi("GH28", loaiGhe, tang, khoangTau, toaTau1, 28, 0, true);
    GheNgoi ghe29 = new GheNgoi("GH29", loaiGhe, tang, khoangTau, toaTau1, 29, 0, true);
    GheNgoi ghe30 = new GheNgoi("GH30", loaiGhe, tang, khoangTau, toaTau1, 30, 0, true);
    GheNgoi ghe31 = new GheNgoi("GH31", loaiGhe, tang, khoangTau, toaTau1, 31, 0, true);
    GheNgoi ghe32 = new GheNgoi("GH32", loaiGhe, tang, khoangTau, toaTau1, 32, 0, true);
    GheNgoi ghe33 = new GheNgoi("GH33", loaiGhe, tang, khoangTau, toaTau1, 33, 0, true);
    GheNgoi ghe34 = new GheNgoi("GH34", loaiGhe, tang, khoangTau, toaTau1, 34, 0, true);
    GheNgoi ghe35 = new GheNgoi("GH35", loaiGhe, tang, khoangTau, toaTau1, 35, 0, true);
    GheNgoi ghe36 = new GheNgoi("GH36", loaiGhe, tang, khoangTau, toaTau1, 36, 0, true);
    GheNgoi ghe37 = new GheNgoi("GH01", loaiGhe, tang, khoangTau, toaTau2, 1, 0, false);
    GheNgoi ghe38 = new GheNgoi("GH02", loaiGhe, tang, khoangTau, toaTau2, 2, 0, false);
    GheNgoi ghe39 = new GheNgoi("GH03", loaiGhe, tang, khoangTau, toaTau2, 3, 0, false);
    GheNgoi ghe40 = new GheNgoi("GH04", loaiGhe, tang, khoangTau, toaTau2, 4, 0, false);
    GheNgoi ghe41 = new GheNgoi("GH05", loaiGhe, tang, khoangTau, toaTau2, 5, 0, false);
    GheNgoi ghe42 = new GheNgoi("GH06", loaiGhe, tang, khoangTau, toaTau2, 6, 0, false);
    GheNgoi ghe43 = new GheNgoi("GH07", loaiGhe, tang, khoangTau, toaTau2, 7, 0, false);
    GheNgoi ghe44 = new GheNgoi("GH08", loaiGhe, tang, khoangTau, toaTau2, 8, 0, false);
    GheNgoi ghe45 = new GheNgoi("GH09", loaiGhe, tang, khoangTau, toaTau2, 9, 0, false);
    GheNgoi ghe46 = new GheNgoi("GH10", loaiGhe, tang, khoangTau, toaTau2, 10, 0, false);
    GheNgoi ghe47 = new GheNgoi("GH11", loaiGhe, tang, khoangTau, toaTau2, 11, 0, false);
    GheNgoi ghe48 = new GheNgoi("GH12", loaiGhe, tang, khoangTau, toaTau2, 12, 0, false);
    GheNgoi ghe49 = new GheNgoi("GH13", loaiGhe, tang, khoangTau, toaTau2, 13, 0, true);
    GheNgoi ghe50 = new GheNgoi("GH14", loaiGhe, tang, khoangTau, toaTau2, 14, 0, true);
    GheNgoi ghe51 = new GheNgoi("GH15", loaiGhe, tang, khoangTau, toaTau2, 15, 0, true);
    GheNgoi ghe52 = new GheNgoi("GH16", loaiGhe, tang, khoangTau, toaTau2, 16, 0, true);
    GheNgoi ghe53 = new GheNgoi("GH17", loaiGhe, tang, khoangTau, toaTau2, 17, 0, true);
    GheNgoi ghe54 = new GheNgoi("GH18", loaiGhe, tang, khoangTau, toaTau2, 18, 0, true);
    
    public ArrayList<GheNgoi> addGheVaoDSGhe() {
        listGheNgoi = new ArrayList<>();
        listGheNgoi.add(ghe1);
        listGheNgoi.add(ghe2);
        listGheNgoi.add(ghe3);
        listGheNgoi.add(ghe4);
        listGheNgoi.add(ghe5);
        listGheNgoi.add(ghe6);
        listGheNgoi.add(ghe7);
        listGheNgoi.add(ghe8);
        listGheNgoi.add(ghe9);
        listGheNgoi.add(ghe10);
        listGheNgoi.add(ghe11);
        listGheNgoi.add(ghe12);
        listGheNgoi.add(ghe13);
        listGheNgoi.add(ghe14);
        listGheNgoi.add(ghe15);
        listGheNgoi.add(ghe16);
        listGheNgoi.add(ghe17);
        listGheNgoi.add(ghe18);
        listGheNgoi.add(ghe19);
        listGheNgoi.add(ghe20);
        listGheNgoi.add(ghe21);
        listGheNgoi.add(ghe22);
        listGheNgoi.add(ghe23);
        listGheNgoi.add(ghe24);
        listGheNgoi.add(ghe25);
        listGheNgoi.add(ghe26);
        listGheNgoi.add(ghe27);
        listGheNgoi.add(ghe28);
        listGheNgoi.add(ghe29);
        listGheNgoi.add(ghe30);
        listGheNgoi.add(ghe31);
        listGheNgoi.add(ghe32);
        listGheNgoi.add(ghe33);
        listGheNgoi.add(ghe34);
        listGheNgoi.add(ghe35);
        listGheNgoi.add(ghe36);
        listGheNgoi.add(ghe37);
        listGheNgoi.add(ghe38);
        listGheNgoi.add(ghe39);
        listGheNgoi.add(ghe40);
        listGheNgoi.add(ghe41);
        listGheNgoi.add(ghe42);
        listGheNgoi.add(ghe43);
        listGheNgoi.add(ghe44);
        listGheNgoi.add(ghe45);
        listGheNgoi.add(ghe46);
        listGheNgoi.add(ghe47);
        listGheNgoi.add(ghe48);
        listGheNgoi.add(ghe49);
        listGheNgoi.add(ghe50);
        listGheNgoi.add(ghe51);
        listGheNgoi.add(ghe52);
        listGheNgoi.add(ghe53);
        listGheNgoi.add(ghe54);
        return listGheNgoi;
    }
//Search ghe theo toa tau
    public ArrayList<GheNgoi> getDanhSachGheTheoToa(ToaTau toaTau) {
        ArrayList<GheNgoi> listGheNgoiTheoToa = new ArrayList<>();
        for (GheNgoi g : listGheNgoi) {
            if (g.getToaTau() == toaTau) {
                listGheNgoiTheoToa.add(g);
            }
        }
        return listGheNgoiTheoToa;
    }

    public ArrayList<GheTrenChuyenTau> getDanhSachGheTCTheoToa(ToaTau toaTau, ChuyenTau chuyenTau) {
        ArrayList<GheTrenChuyenTau> listGheNgoiTCTheoToa = new ArrayList<>();
        for (GheTrenChuyenTau g : listGheTrenChuyenTau) {
            if (g.getGheNgoi().getToaTau() == toaTau && g.getChuyenTau() == chuyenTau) {
                listGheNgoiTCTheoToa.add(g);
            }
        }
        return listGheNgoiTCTheoToa;
    }

//Search Chuyen tau bang ma chuyen
    public ChuyenTau getChuyenTau(String maChuyen){
        for(ChuyenTau c: listChuyenTau){
            if (c.getMaChuyenTau().equals(maChuyen)) {
                return c;
            }
        }
        return null;
    }

//    public ChuyenTau

//Them ghe vao Danh sach Ghe Tren Chuyen Tau
    public ArrayList <GheTrenChuyenTau>  themGheVaoDSGheTrenChuyenTau(ToaTau toaTau, ChuyenTau chuyenTau) {
        ArrayList <GheNgoi> dsGheTheoToa = getDanhSachGheTheoToa(toaTau);
        for (GheNgoi g : dsGheTheoToa) {
            int  i=0;
            String maGheTrenChuyenTau = String.format("CGT%02d" , i+1);
            GheTrenChuyenTau gheTrenChuyenTau = new GheTrenChuyenTau(maGheTrenChuyenTau , "SS" , 2000000, chuyenTau,g);
            listGheTrenChuyenTau.add(gheTrenChuyenTau);
        }
        return listGheTrenChuyenTau;
}

//Search toa theo chuyen tau
    public ArrayList <ToaTau> getListToaTheoChuyenTau(ChuyenTau chuyenTau) {
        ArrayList <ToaTau> listToaTheoChuyenTau = new ArrayList<>();

        for (int i = 0; i< listGheTrenChuyenTau.size(); i++) {
            if(listGheTrenChuyenTau.get(i).getChuyenTau() == chuyenTau) {
                if (listGheTrenChuyenTau.get(i).getGheNgoi().getToaTau().getLoaiToaTau().getMaLoaiToaTau() == "GN01"){
                    listToaTheoChuyenTau.add(listGheTrenChuyenTau.get(i).getGheNgoi().getToaTau());
                    i+=35;
                }else{
                    listToaTheoChuyenTau.add(listGheTrenChuyenTau.get(i).getGheNgoi().getToaTau());
                    i+=17;
                }
            }
        }
        return listToaTheoChuyenTau;
    }

//    GheTrenChuyenTau(String maGheTrenChuyenTau, String trangThaiGhe, double giaTienGhe, ChuyenTau chuyenTau, GheNgoi gheNgoi)

public static void main(String[] args) {
        ChuyenTau_Dao chuyenTau_Dao = new ChuyenTau_Dao();
        chuyenTau_Dao.addGheVaoDSGhe();
        chuyenTau_Dao.themGheVaoDSGheTrenChuyenTau(chuyenTau_Dao.toaTau1, chuyenTau_Dao.chuyen1);
        chuyenTau_Dao.themGheVaoDSGheTrenChuyenTau(chuyenTau_Dao.toaTau2, chuyenTau_Dao.chuyen1);

}



}
