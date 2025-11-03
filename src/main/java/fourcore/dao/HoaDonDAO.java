package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.*;

public class HoaDonDAO {
	DatabaseConnector database = new DatabaseConnector();
	ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
	Statement st = database.connect();
    LoaiHoaDonDAO loaiHoaDonDAO = new LoaiHoaDonDAO();
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    ChuongTrinhKhuyenMaiDAO chuongTrinhKhuyenMaiDAO = new ChuongTrinhKhuyenMaiDAO();
	public HoaDonDAO() throws SQLException {
	}

	public ArrayList<HoaDon> getListHoaDon() throws SQLException {
		String q = "SELECT hd.maHoaDon, kh.hoTen , kh.sdt , hd.ngayThanhToan , hd.tongTien\r\n"
				+ "				FROM [dbo].[HoaDon] hd, [dbo].[ChiTietHoaDon] chd , [dbo].[Ve] ve , [dbo].[KhachHang] kh\r\n"
				+ "				WHERE hd.MaHoaDon = chd.MaHoaDon \r\n" + "				and\r\n"
				+ "				chd.maVeTau = ve.maVeTau\r\n" + "				and\r\n"
				+ "				ve.maKhachHang = kh.maKhachHang\r\n"
				+ "				Group by hd.maHoaDon, kh.hoTen , kh.sdt , hd.ngayThanhToan , hd.tongTien";
		ResultSet rs = st.executeQuery(q);
		while (rs.next()) {
			String mahd = rs.getString(1);
			LocalDateTime ngaythanhtoan = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double TongTien = rs.getDouble(5);
			HoaDon hd = new HoaDon(mahd, null, null, mahd, mahd, mahd, mahd, ngaythanhtoan, TongTien);
			listHoaDon.add(hd);
		}

		return listHoaDon;
	}
    public HoaDon getHoaDonByMaHoaDon(String maHoaDon) throws SQLException {
            String query = "  select * from HoaDon \n" +
                    "  where maHoaDon = '"+maHoaDon+"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String mahd = rs.getString(1);
                LoaiHoaDon loaiHoaDon = loaiHoaDonDAO.getLoaiHoaDonTheoMa(rs.getString(2));
                NhanVien nhanVien = nhanVienDAO.getNhanVienByMa(rs.getString(3));
                String tenKhachHang = rs.getString(4);
                String emailKhachHang = rs.getString(5);
                String cccd =  rs.getString(6);
                String sdtKhachHang = rs.getString(7);
                LocalDateTime ngayThanhToan =  LocalDateTime.parse(rs.getString(8));
                double TongTien = rs.getDouble(9);
                String diaChi = rs.getString(10);

                HoaDon hoaDon = new HoaDon(mahd,loaiHoaDon,nhanVien,tenKhachHang,emailKhachHang,cccd,sdtKhachHang,ngayThanhToan,TongTien,diaChi);
                return  hoaDon;
            }
            return null;
    }
    public ArrayList<ThongTinCtHoaDon> getThongTinCTHoaDon(String maHoaDon) throws SQLException {
        ArrayList<ThongTinCtHoaDon> listThongTinCtHoaDon = new ArrayList<>();
        String query = "SELECT \n" +
                "    v.maVeTau AS MaVe,\n" +
                "    lg.tenLoaiGhe AS TenLoaiGhe,\n" +
                "    dt.tenDoiTuongGiamGia AS DoiTuong,\n" +
                "    gtc.giaTienGhe AS DonGia,\n" +
                "    v.giaVe AS ThanhTien\n" +
                "FROM ChiTietHoaDon cthd\n" +
                "JOIN Ve v ON cthd.maVeTau = v.maVeTau\n" +
                "JOIN GheTrenChuyenTau gtc ON v.maGheTrenChuyenTau = gtc.maGheTrenChuyenTau\n" +
                "JOIN GheNgoi g ON gtc.maGheNgoi = g.maGheNgoi\n" +
                "JOIN LoaiGhe lg ON g.maLoaiGhe = lg.maLoaiGhe\n" +
                "JOIN DoiTuongGiamGia dt ON v.maDoiTuongGiamGia = dt.maDoiTuongGiamGia\n" +
                "WHERE cthd.maHoaDon = '"+maHoaDon+"';";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String maVeTau = rs.getString(1);
            String tenLoaiGhe = rs.getString(2);
            String doiTuong = rs.getString(3);
            double donGia = rs.getDouble(4);
            double thanhTien = rs.getDouble(5);

            ThongTinCtHoaDon thongTinCtHoaDon= new ThongTinCtHoaDon(maVeTau,tenLoaiGhe,doiTuong,donGia,thanhTien);
            listThongTinCtHoaDon.add(thongTinCtHoaDon);
        }
        return listThongTinCtHoaDon;
    }

    public KhuyenMai getKhuyenMaiByMaHoaDon(String maHoaDon) throws SQLException {

        String query = "select v.giaVe, v.maKhuyenMai from ve v, ChiTietHoaDon c\n" +
                "where c.maHoaDon = '"+maHoaDon+"' and v.maVeTau = c.maVeTau\n";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String  maKhuyenMai = rs.getString(2);
            KhuyenMai ctkm = chuongTrinhKhuyenMaiDAO.getKhuyenMaiBangMa(maKhuyenMai);

            return ctkm;
        }
        return null;
    }

	public HoaDon getHoaDonBangMaVe(String maVe) throws SQLException {
		String q = "select hd.*\r\n" + "from HoaDon as hd\r\n"
				+ "join ChiTietHoaDon as ct on hd.maHoaDon = ct.maHoaDon\r\n"
				+ "join Ve as v on ct.maVeTau = v.maVeTau\r\n" + "where v.maVeTau = " + "'" + maVe + "'";
		ResultSet rs = st.executeQuery(q);
		HoaDon hd = null;
		while (rs.next()) {
			String mahd = rs.getString("maHoaDon");
			String maloaihd = rs.getString("maLoaiHoaDon");
			String manv = rs.getString("maNhanVien");
			String tenkh = rs.getString("tenKhachHangThanhToan");
			String email = rs.getString("emailKhachHangThanhToan");
			String cccd = rs.getString("cccdKhachHangThanhToan");
			String sdt = rs.getString("sdtKhachHangThanhToan");
			LocalDateTime ngaytt = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double tongtien = rs.getDouble("tongTien");
			NhanVienDAO nvDao = new NhanVienDAO();
			NhanVien nv = nvDao.getNhanVienByMa(manv);
			LoaiHoaDonDAO loaihdDao = new LoaiHoaDonDAO();
			LoaiHoaDon loaiHD = loaihdDao.getLoaiHoaDonTheoMa(maloaihd);
			hd = new HoaDon(mahd, loaiHD, nv, tenkh, email, cccd, sdt, ngaytt, tongtien);
			System.out.println("lấy thông tin hóa đơn " + mahd);
		}
		return hd;
	}

	public void themHoaDonHoanTraVe(HoaDon hd) throws SQLException {

		String countQuery = "SELECT COUNT(*) AS soLuong FROM HoaDon";
		int soLuong = 0;
		ResultSet rs = st.executeQuery(countQuery);
		if (rs.next()) {
			soLuong = rs.getInt("soLuong");
		}
		rs.close();

		String newMa = null;
		if (soLuong < 10) {
			soLuong += 1;
			newMa = "HD0" + soLuong;
		} else {
			soLuong += 1;
			newMa = "HD" + soLuong;
		}

		String insertQuery = "INSERT INTO HoaDon (" + "maHoaDon, maLoaiHoaDon, maNhanVien, tenKhachHangThanhToan, "
				+ "emailKhachHangThanhToan, cccdKhachHangThanhToan, "
				+ "sdtKhachHangThanhToan, diaChiKhachHangThanhToan, tongTien) VALUES (" + "'" + newMa + "', " + "'"
				+ "LHD02" + "', " + "'" + hd.getMaNhanVien().getMaNhanVien() + "', " + "N'"
				+ hd.getTenKhachHangThanhToan() + "', " + "'" + hd.getEmailKhachHangThanhToan() + "', " + "'"
				+ hd.getCccdKhachHangThanhToan() + "', " + "'" + hd.getSdtKhachHangThanhToan() + "', " + "N'"
				+ hd.getDiaChiKhachHangThanhToan() + "', " + hd.getTongTien() + ")";
		hd.setMaHoaDon(newMa);
		Statement insertST = database.connect();
		int rows = insertST.executeUpdate(insertQuery);
		insertST.close();

		System.out.println("Số hàng được thêm thành công: " + rows);
	}

	public KhachHang getKH(String mahd) throws SQLException {

		ChiTietHoaDonDAO cthddao = new ChiTietHoaDonDAO();
		ArrayList<ChiTietHoaDon> ct2 = cthddao.getHD(mahd);
		String s = ct2.get(0).getVeTau().getMaVeTau().toString();
		KhachHang kh1 = cthddao.getKhachHang(s);
		return kh1;
	}

	
	public HoaDon getHoaDonTheoMa(String maHD) throws SQLException {
	    
	    String q = "SELECT * FROM HoaDon WHERE maHoaDon = '" + maHD + "'";
	    ResultSet rs = st.executeQuery(q);

	    if (rs.next()) {
	    	HoaDon hd = new HoaDon();
	    	String mahd = rs.getString("maHoaDon");
			String maloaihd = rs.getString("maLoaiHoaDon");
			String manv = rs.getString("maNhanVien");
			String tenkh = rs.getString("tenKhachHangThanhToan");
			String email = rs.getString("emailKhachHangThanhToan");
			String cccd = rs.getString("cccdKhachHangThanhToan");
			String sdt = rs.getString("sdtKhachHangThanhToan");
			LocalDateTime ngaytt = rs.getTimestamp("ngayThanhToan").toLocalDateTime();
			double tongtien = rs.getDouble("tongTien");
			NhanVienDAO nvDao = new NhanVienDAO();
			NhanVien nv = nvDao.getNhanVienByMa(manv);
			LoaiHoaDonDAO loaihdDao = new LoaiHoaDonDAO();
			LoaiHoaDon loaiHD = loaihdDao.getLoaiHoaDonTheoMa(maloaihd);
			hd = new HoaDon(mahd, loaiHD, nv, tenkh, email, cccd, sdt, ngaytt, tongtien);
			System.out.println("lấy thông tin hóa đơn " + mahd);
	        return hd;
	    }
	    return null;
	}

	
}
