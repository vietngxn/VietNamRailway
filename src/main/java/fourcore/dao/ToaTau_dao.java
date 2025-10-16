package fourcore.dao;

import java.util.ArrayList;

import fourcore.Entity.GheNgoi;
import fourcore.Entity.LoaiToaTau;
import fourcore.Entity.ToaTau;


public class ToaTau_dao {
	
	LoaiToaTau loaiToaTau;
	
	private ArrayList<ToaTau> toaTaudao = new ArrayList<ToaTau>();

	

//
//	
	public ArrayList<ToaTau> testListToaTau() {
		toaTaudao.clear();
		ToaTau toaTau1 = new ToaTau("GN01", "LGN01", 36, "Sẵn sàng", loaiToaTau);
		ToaTau toaTau2 = new ToaTau("GN02", "LGN01", 18, "Sẵn sàng", loaiToaTau);
		toaTaudao.add(toaTau1);
		toaTaudao.add(toaTau2);
		return this.toaTaudao;
	}
	
	public ToaTau getToaTau(String maToaTau) {
		for(ToaTau tt : toaTaudao) {
			if(tt.getMaToaTau().equalsIgnoreCase(maToaTau)) return tt;
		}
		return null;
	}
	
	
}
