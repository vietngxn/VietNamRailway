package fourcore.Entity;

import java.util.HashMap;
import java.util.Map;


public class ThongTinCtHoaDonHoanTraVe {
	Map<Ve, Double> list = new HashMap<Ve, Double>();

	public ThongTinCtHoaDonHoanTraVe(Map<Ve, Double> listVe) {
		this.list = listVe;
	}

	public Map<Ve, Double> getList() {
		return list;
	}

}
