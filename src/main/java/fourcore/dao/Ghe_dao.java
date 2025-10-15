package fourcore.dao;

import java.util.ArrayList;

import fourcore.Entity.GheNgoi;
import fourcore.Entity.KhoangTau;
import fourcore.Entity.LoaiGhe;
import fourcore.Entity.Tang;
import fourcore.Entity.ToaTau;

public class Ghe_dao {
		
	private ArrayList<GheNgoi> ghedao = new ArrayList<GheNgoi>();
	public Ghe_dao () {
		ghedao = new ArrayList<GheNgoi>();
	}
	ToaTau_dao toataudao = new ToaTau_dao();
//	String hihi = toataudao.addToaTau();
	
	ArrayList<ToaTau> tauhihi = toataudao.testListToaTau();
	LoaiGhe loaiGhe = new LoaiGhe("LG01", "GheNgoi", 300000);
	Tang tang = new Tang("TG01", 1, 5000);
	KhoangTau khoangTau = new KhoangTau("KG01", 1);
	ToaTau toaTau = toataudao.getToaTau("GN01");
	
	ToaTau toaTauv2 = toataudao.getToaTau("GN02");
//	String maGheNgoi, LoaiGhe loaiGhe, Tang tan, KhoangTau khoangTau, ToaTau toaTa, int soGhe, double giaTriTangThem, boolean luuDong
	
	GheNgoi ghe1  = new GheNgoi("GH01", loaiGhe, tang, khoangTau, toaTau, 1, 0, false);
	GheNgoi ghe2  = new GheNgoi("GH02", loaiGhe, tang, khoangTau, toaTau, 2, 0, false);
	GheNgoi ghe3  = new GheNgoi("GH03", loaiGhe, tang, khoangTau, toaTau, 3, 0, false);
	GheNgoi ghe4  = new GheNgoi("GH04", loaiGhe, tang, khoangTau, toaTau, 4, 0, false);
	GheNgoi ghe5  = new GheNgoi("GH05", loaiGhe, tang, khoangTau, toaTau, 5, 0, false);
	GheNgoi ghe6  = new GheNgoi("GH06", loaiGhe, tang, khoangTau, toaTau, 6, 0, false);
	GheNgoi ghe7  = new GheNgoi("GH07", loaiGhe, tang, khoangTau, toaTau, 7, 0, false);
	GheNgoi ghe8  = new GheNgoi("GH08", loaiGhe, tang, khoangTau, toaTau, 8, 0, false);
	GheNgoi ghe9  = new GheNgoi("GH09", loaiGhe, tang, khoangTau, toaTau, 9, 0, false);
	GheNgoi ghe10 = new GheNgoi("GH10", loaiGhe, tang, khoangTau, toaTau, 10, 0, false);
	GheNgoi ghe11 = new GheNgoi("GH11", loaiGhe, tang, khoangTau, toaTau, 11, 0, false);
	GheNgoi ghe12 = new GheNgoi("GH12", loaiGhe, tang, khoangTau, toaTau, 12, 0, false);
	GheNgoi ghe13 = new GheNgoi("GH13", loaiGhe, tang, khoangTau, toaTau, 13, 0, false);
	GheNgoi ghe14 = new GheNgoi("GH14", loaiGhe, tang, khoangTau, toaTau, 14, 0, false);
	GheNgoi ghe15 = new GheNgoi("GH15", loaiGhe, tang, khoangTau, toaTau, 15, 0, false);
	GheNgoi ghe16 = new GheNgoi("GH16", loaiGhe, tang, khoangTau, toaTau, 16, 0, false);
	GheNgoi ghe17 = new GheNgoi("GH17", loaiGhe, tang, khoangTau, toaTau, 17, 0, false);
	GheNgoi ghe18 = new GheNgoi("GH18", loaiGhe, tang, khoangTau, toaTau, 18, 0, false);
	GheNgoi ghe19 = new GheNgoi("GH19", loaiGhe, tang, khoangTau, toaTau, 19, 0, false);
	GheNgoi ghe20 = new GheNgoi("GH20", loaiGhe, tang, khoangTau, toaTau, 20, 0, false);
	GheNgoi ghe21 = new GheNgoi("GH21", loaiGhe, tang, khoangTau, toaTau, 21, 0, false);
	GheNgoi ghe22 = new GheNgoi("GH22", loaiGhe, tang, khoangTau, toaTau, 22, 0, false);
	GheNgoi ghe23 = new GheNgoi("GH23", loaiGhe, tang, khoangTau, toaTau, 23, 0, false);
	GheNgoi ghe24 = new GheNgoi("GH24", loaiGhe, tang, khoangTau, toaTau, 24, 0, false);
	GheNgoi ghe25 = new GheNgoi("GH25", loaiGhe, tang, khoangTau, toaTau, 25, 0, true);
	GheNgoi ghe26 = new GheNgoi("GH26", loaiGhe, tang, khoangTau, toaTau, 26, 0, true);
	GheNgoi ghe27 = new GheNgoi("GH27", loaiGhe, tang, khoangTau, toaTau, 27, 0, true);
	GheNgoi ghe28 = new GheNgoi("GH28", loaiGhe, tang, khoangTau, toaTau, 28, 0, true);
	GheNgoi ghe29 = new GheNgoi("GH29", loaiGhe, tang, khoangTau, toaTau, 29, 0, true);
	GheNgoi ghe30 = new GheNgoi("GH30", loaiGhe, tang, khoangTau, toaTau, 30, 0, true);
	GheNgoi ghe31 = new GheNgoi("GH31", loaiGhe, tang, khoangTau, toaTau, 31, 0, true);
	GheNgoi ghe32 = new GheNgoi("GH32", loaiGhe, tang, khoangTau, toaTau, 32, 0, true);
	GheNgoi ghe33 = new GheNgoi("GH33", loaiGhe, tang, khoangTau, toaTau, 33, 0, true);
	GheNgoi ghe34 = new GheNgoi("GH34", loaiGhe, tang, khoangTau, toaTau, 34, 0, true);
	GheNgoi ghe35 = new GheNgoi("GH35", loaiGhe, tang, khoangTau, toaTau, 35, 0, true);
	GheNgoi ghe36 = new GheNgoi("GH36", loaiGhe, tang, khoangTau, toaTau, 36, 0, true);
	GheNgoi ghe37 = new GheNgoi("GH01", loaiGhe, tang, khoangTau, toaTauv2, 1, 0, false);
	GheNgoi ghe38 = new GheNgoi("GH02", loaiGhe, tang, khoangTau, toaTauv2, 2, 0, false);
	GheNgoi ghe39 = new GheNgoi("GH03", loaiGhe, tang, khoangTau, toaTauv2, 3, 0, false);
	GheNgoi ghe40 = new GheNgoi("GH04", loaiGhe, tang, khoangTau, toaTauv2, 4, 0, false);
	GheNgoi ghe41 = new GheNgoi("GH05", loaiGhe, tang, khoangTau, toaTauv2, 5, 0, false);
	GheNgoi ghe42 = new GheNgoi("GH06", loaiGhe, tang, khoangTau, toaTauv2, 6, 0, false);
	GheNgoi ghe43 = new GheNgoi("GH07", loaiGhe, tang, khoangTau, toaTauv2, 7, 0, false);
	GheNgoi ghe44 = new GheNgoi("GH08", loaiGhe, tang, khoangTau, toaTauv2, 8, 0, false);
	GheNgoi ghe45 = new GheNgoi("GH09", loaiGhe, tang, khoangTau, toaTauv2, 9, 0, false);
	GheNgoi ghe46 = new GheNgoi("GH10", loaiGhe, tang, khoangTau, toaTauv2, 10, 0, false);
	GheNgoi ghe47 = new GheNgoi("GH11", loaiGhe, tang, khoangTau, toaTauv2, 11, 0, false);
	GheNgoi ghe48 = new GheNgoi("GH12", loaiGhe, tang, khoangTau, toaTauv2, 12, 0, false);
	GheNgoi ghe49 = new GheNgoi("GH13", loaiGhe, tang, khoangTau, toaTauv2, 13, 0, true);
	GheNgoi ghe50 = new GheNgoi("GH14", loaiGhe, tang, khoangTau, toaTauv2, 14, 0, true);
	GheNgoi ghe51 = new GheNgoi("GH15", loaiGhe, tang, khoangTau, toaTauv2, 15, 0, true);
	GheNgoi ghe52 = new GheNgoi("GH16", loaiGhe, tang, khoangTau, toaTauv2, 16, 0, true);
	GheNgoi ghe53 = new GheNgoi("GH17", loaiGhe, tang, khoangTau, toaTauv2, 17, 0, true);
	GheNgoi ghe54 = new GheNgoi("GH18", loaiGhe, tang, khoangTau, toaTauv2, 18, 0, true);


	
	public ArrayList<GheNgoi> getAllGheCuaToa(String maToaTau) {
		
		ArrayList<GheNgoi> danhSachGhe = ghedao;
		ArrayList<GheNgoi> danhSachGheCuaToa = new ArrayList<GheNgoi>();
		for( GheNgoi gn : danhSachGhe) {
			if(gn.getToaTau().getMaToaTau().equalsIgnoreCase(maToaTau)) danhSachGheCuaToa.add(gn);
		}
		return danhSachGheCuaToa;
		
	}

	
	public ArrayList<GheNgoi> testList() {
		ghedao.clear();
		ghedao.add(ghe1);
		ghedao.add(ghe2);
		ghedao.add(ghe3);
		ghedao.add(ghe4);
		ghedao.add(ghe5);
		ghedao.add(ghe6);
		ghedao.add(ghe7);
		ghedao.add(ghe8);
		ghedao.add(ghe9);
		ghedao.add(ghe10);
		ghedao.add(ghe11);
		ghedao.add(ghe12);
		ghedao.add(ghe13);
		ghedao.add(ghe14);
		ghedao.add(ghe15);
		ghedao.add(ghe16);
		ghedao.add(ghe17);
		ghedao.add(ghe18);
		ghedao.add(ghe19);
		ghedao.add(ghe20);
		ghedao.add(ghe21);
		ghedao.add(ghe22);
		ghedao.add(ghe23);
		ghedao.add(ghe24);
		ghedao.add(ghe25);
		ghedao.add(ghe26);
		ghedao.add(ghe27);
		ghedao.add(ghe28);
		ghedao.add(ghe29);
		ghedao.add(ghe30);
		ghedao.add(ghe31);
		ghedao.add(ghe32);
		ghedao.add(ghe33);
		ghedao.add(ghe34);
		ghedao.add(ghe35);
		ghedao.add(ghe36);
		ghedao.add(ghe37);
		ghedao.add(ghe38);
		ghedao.add(ghe39);
		ghedao.add(ghe40);
		ghedao.add(ghe41);
		ghedao.add(ghe42);
		ghedao.add(ghe43);
		ghedao.add(ghe44);
		ghedao.add(ghe45);
		ghedao.add(ghe46);
		ghedao.add(ghe47);
		ghedao.add(ghe48);
		ghedao.add(ghe49);
		ghedao.add(ghe50);
		ghedao.add(ghe51);
		ghedao.add(ghe52);
		ghedao.add(ghe53);
		ghedao.add(ghe54);

		return ghedao;
	}
}
