package fourcore.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import fourcore.Entity.DoiTuongGiamGia;
import fourcore.Entity.KhachHang;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.LoaiTau;
import fourcore.Entity.Tau;

public class testDao {
//    KhachHang_DAO khDAO = new KhachHang_DAO();
//    ArrayList<KhachHang> listKH;
//
//    public static void main(String[] args) throws SQLException {
//        testDao test = new testDao();
//        test.listKH = test.khDAO.getList();
//        for (KhachHang kh : test.listKH) {
//            System.out.println(kh.toString());
//        }
//    }

//    LoaiTau_DAO ltDAO = new LoaiTau_DAO();
//    ArrayList<LoaiTau> list;
//
//    public static void main(String[] args) throws SQLException {
//        testDao test = new testDao();
//        test.list = test.ltDAO.getList();
//        for (LoaiTau kh : test.list) {
//            System.out.println(kh.toString());
//        }
//    }

//  Tau_DAO tDAO = new Tau_DAO();
//  ArrayList<Tau> list;
//
//  public static void main(String[] args) throws SQLException {
//      testDao test = new testDao();
//      test.list = test.tDAO.getList();
//      for (Tau kh : test.list) {
//          System.out.println(kh.toString());
//      }
//  }

//  DoiTuongGiamGia_DAO dtDAO = new DoiTuongGiamGia_DAO();
//  ArrayList<DoiTuongGiamGia> list;
//
//  public static void main(String[] args) throws SQLException {
//      testDao test = new testDao();
//      test.list = test.dtDAO.getList();
//      for (DoiTuongGiamGia kh : test.list) {
//          System.out.println(kh.toString());
//      }
//  }

	public static void main(String[] args) throws SQLException {
		KhuyenMai_Dao dao = new KhuyenMai_Dao();
		ArrayList<KhuyenMai> ds = dao.getList();

		for (KhuyenMai km : ds) {
			System.out.println(km.getMaKhuyenMai() + " | " + km.getTenChuongTrinh() + " | "
					+ km.getGiaTriPhanTramKhuyenMai() + "% | " + km.getNgayBatDau() + " -> " + km.getNgayKetThuc());
		}
	}

}
