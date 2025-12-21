package fourcore.Entity;


import java.io.Serializable;
import java.util.ArrayList;

public class HanhTrinh implements Serializable {
    private String maHanhTrinh;
    private String tenHanhTrinh;
    private ArrayList<Ga> listGa;
    private boolean isRemove;

    public HanhTrinh() {
    }
    
    public HanhTrinh(String maHanhTrinh, String tenHanhTrinh, ArrayList<Ga> listGa) {
        setMaHanhTrinh(maHanhTrinh);
        setTenHanhTrinh(tenHanhTrinh);
        setListGa(listGa);
    }
    
    public HanhTrinh(String maHanhTrinh, String tenHanhTrinh, ArrayList<Ga> listGa, boolean isRemove) {
        setMaHanhTrinh(maHanhTrinh);
        setTenHanhTrinh(tenHanhTrinh);
        setListGa(listGa);
        setRemove(isRemove);
    }

    public ArrayList<Ga> getListGa() {
        return listGa;
    }

    public void setListGa(ArrayList<Ga> listGa) {
        this.listGa = listGa;
    }

    public String getMaHanhTrinh() {
        return maHanhTrinh;
    }

    public void setMaHanhTrinh(String maHanhTrinh) {
        this.maHanhTrinh = maHanhTrinh;
    }

    public String getTenHanhTrinh() {
        return tenHanhTrinh;
    }

    public void setTenHanhTrinh(String tenHanhTrinh) {
        this.tenHanhTrinh = tenHanhTrinh;
    }

	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}
    
}
