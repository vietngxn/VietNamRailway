package fourcore.Entity;

public class Tau {

//  =======================
//  ||   VARIABLE        ||
//  =======================
	private String maTau;
	private String tenTau;
	private LoaiTau loaiTau;

//  =======================
//  ||   CONSTRUCTORS    ||
//  =======================
	public Tau(String maTau, String tenTau, LoaiTau loaiTau) {
		setMaTau(maTau);
		setTenTau(tenTau);
		setLoaiTau(loaiTau);
	}

	public Tau() {
	}

//  =======================
//  || GETTER AND SETTER ||
//  =======================
	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		this.maTau = maTau;
	}

	public String getTenTau() {
		return tenTau;
	}

	public void setTenTau(String tenTau) {
		this.tenTau = tenTau;
	}

	public LoaiTau getLoaiTau() {
		return loaiTau;
	}

	public void setLoaiTau(LoaiTau loaiTau) {
		this.loaiTau = loaiTau;
	}

	@Override
	public String toString() {
		return "Tau [maTau=" + maTau + ", tenTau=" + tenTau + ", loaiTau=" + loaiTau + "]";
	}

}
