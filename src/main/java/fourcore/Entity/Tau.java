package fourcore.Entity;

import java.io.Serializable;

public class Tau implements Serializable {

//  =======================
//  ||   VARIABLE        ||
//  =======================
	private String maTau;
	private String tenTau;
	private LoaiTau loaiTau;
	private boolean isRemove;

//  =======================
//  ||   CONSTRUCTORS    ||
//  =======================
	public Tau(String maTau, String tenTau, LoaiTau loaiTau) {
		setMaTau(maTau);
		setTenTau(tenTau);
		setLoaiTau(loaiTau);
	}
	
	public Tau(String maTau, String tenTau, LoaiTau loaiTau, boolean isRemove) {
		setMaTau(maTau);
		setTenTau(tenTau);
		setLoaiTau(loaiTau);
		setRemove(isRemove);
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

	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	@Override
	public String toString() {
		return "Tau [maTau=" + maTau + ", tenTau=" + tenTau + ", loaiTau=" + loaiTau + "]";
	}

}
