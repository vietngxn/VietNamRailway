package fourcore.Entity;


import java.util.ArrayList;

public class HanhTrinh {
    private String maHanhTrinh;
    private String tenHanhTrinh;
    private ArrayList<Ga> listGa;

    public HanhTrinh() {
    }

    public HanhTrinh(String maHanhTrinh, String tenHanhTrinh, ArrayList<Ga> listGa) {
        setMaHanhTrinh(maHanhTrinh);
        setTenHanhTrinh(tenHanhTrinh);
        setListGa(listGa);
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

}
