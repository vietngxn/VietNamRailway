package fourcore.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.spi.CollatorProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import fourcore.Entity.ChuyenTau;
import fourcore.Entity.LichSuTuongTacVe;
import fourcore.Entity.Tau;
import fourcore.dao.ChuyenTauDAO;
import fourcore.dao.NhanVienDAO;
import fourcore.dao.Tau_DAO;

public class XuatExcelThongKeLichSuTuongTacVe {
	private Map<LichSuTuongTacVe, Double> list = new HashMap<LichSuTuongTacVe, Double>();
	private DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter dftOutputFile = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	NumberFormat nft = NumberFormat.getInstance(new Locale("vi", "vn"));
	DateTimeFormatter dftNgayHienTaiOutputFile = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	public void xuatThongKeTuongTacVe(Map<LichSuTuongTacVe, Double> returnList, String loai, LocalDate tu,
			LocalDate den) throws IOException, SQLException {
		list = returnList;
		if (returnList.isEmpty()) {
			System.out.println("list thống kê null");
			return;
		}
		XSSFWorkbook fileExcel = new XSSFWorkbook();
		XSSFSheet sheet = fileExcel.createSheet("Thống kê tương tác vé");

		CellStyle cellstyle = fileExcel.createCellStyle();
		XSSFFont font = fileExcel.createFont();
		font.setBold(true);
		cellstyle.setFont(font);

		XSSFRow row1 = sheet.createRow(0);
		XSSFCell cell0r1 = row1.createCell(0);
		cell0r1.setCellValue("Thống kê lịch sử tương tác vé loại " + loai);
		row1.getCell(0).setCellStyle(cellstyle);

		XSSFRow row2 = sheet.createRow(1);
		XSSFCell cell0r2 = row2.createCell(0);
		cell0r2.setCellValue("Thời gian thống kê: " + dft.format(tu) + " - " + dft.format(den));

		String[] col = { "Mã vé", "Mã chuyến", "Loại tương tác", "Ga đi - ga đến", "Ngày khởi hành", "Vị trí ghế",
				"Ngày tương tác", "Mã nhân viên thanh toán", "Phí hoàn đổi trả", "Thành tiền vé" };

		XSSFRow row3 = sheet.createRow(2);
		for (int i = 0; i < col.length; i++) {
			XSSFCell cell = row3.createCell(i);
			String colValue = col[i];
			cell.setCellValue(colValue);
			cell.setCellStyle(cellstyle);
		}

		int soLuongRow = 3;
		ChuyenTauDAO ctDao = new ChuyenTauDAO();
		Tau_DAO tDao = new Tau_DAO();
		NhanVienDAO nvDao = new NhanVienDAO();
		double tongCongThanhTien = 0.0;
		double tongCongPhiHoanDoiTra = 0.0;
		for (Map.Entry<LichSuTuongTacVe, Double> entry : list.entrySet()) {
			XSSFRow row = sheet.createRow(soLuongRow);
			LichSuTuongTacVe lstt = entry.getKey();

			System.out.println(lstt.getVeTau().getMaVeTau());
			ChuyenTau ct = ctDao.getChuyenTauBangMa(lstt.getVeTau().getChuyenTau().getMaChuyenTau());
			Tau t = tDao.getTauByMaTau(ct.getTau().getMaTau());

			XSSFCell cell0 = row.createCell(0);
			String maVe = lstt.getVeTau().getMaVeTau();
			cell0.setCellValue(maVe);

			XSSFCell cell1 = row.createCell(1);
			String maChuyen = t.getLoaiTau().getTenLoaiTau();
			cell1.setCellValue(maChuyen);

			XSSFCell cell2 = row.createCell(2);
			String loaiTuongTac = lstt.getLoaiTuongTacVe().getTenLoaiTuongTac();
			cell2.setCellValue(loaiTuongTac);

			XSSFCell cell3 = row.createCell(3);
			String gaDiGaDen = lstt.getVeTau().getGaDi() + " - " + lstt.getVeTau().getGaDen();
			cell3.setCellValue(gaDiGaDen);

			XSSFCell cell4 = row.createCell(4);
			String ngayKhoiHanh = dft.format(lstt.getVeTau().getNgayGioDi().toLocalDate()) + " - "
					+ lstt.getVeTau().getNgayGioDi().toLocalTime().toString();
			cell4.setCellValue(ngayKhoiHanh);

			XSSFCell cell5 = row.createCell(5);
			String viTriGhe = "Toa số " + lstt.getVeTau().getSoToa() + " chỗ " + lstt.getVeTau().getSoGhe();
			cell5.setCellValue(viTriGhe);

			XSSFCell cell6 = row.createCell(6);
			String ngayTuongTac = dft.format(lstt.getNgayTuongTac());
			cell6.setCellValue(ngayTuongTac);

			XSSFCell cell7 = row.createCell(7);
			String maNV = nvDao.getMaNhanVienBangMaVeVaLoaiTuongTac(maVe, lstt.getLoaiTuongTacVe().getMaLoaiTuongTac());
			cell7.setCellValue(maNV);

			XSSFCell cell8 = row.createCell(8);
			String phiHoanTra = nft.format(lstt.getGiaTriChenhLech());
			cell8.setCellValue(phiHoanTra);

			XSSFCell cell9 = row.createCell(9);
			String thanhTienVe = nft.format(entry.getValue());
			cell9.setCellValue(thanhTienVe);

			soLuongRow++;
			tongCongThanhTien += entry.getValue();
			tongCongPhiHoanDoiTra += lstt.getGiaTriChenhLech();
		}
		XSSFRow row4 = sheet.createRow(soLuongRow);
		String tongcong = "Tổng cộng ";
		XSSFRow row5 = sheet.createRow(soLuongRow + 1);
		String tongCongPhiPhatSinh = "Tổng cộng phí phát sinh: ";
		String tenFile = "ThongKeLichSuTuongTacVeLoai";
		if (loai.equalsIgnoreCase("bán vé")) {
			tenFile += "BanVe";
			tongcong += "tiền bán vé thu được: " + nft.format(tongCongThanhTien);
			tongCongPhiPhatSinh += nft.format(tongCongPhiHoanDoiTra);
		} else if (loai.equalsIgnoreCase("đổi vé")) {
			tenFile += "DoiVe";
			tongcong += "tiền thu được: " + nft.format(tongCongThanhTien);
			tongCongPhiPhatSinh += nft.format(tongCongPhiHoanDoiTra);
		} else if (loai.equalsIgnoreCase("hoàn trả vé")) {
			tenFile += "HoanTraVe";
			tongcong += "tiền hoàn trả vé cho khách hàng: " + nft.format(tongCongThanhTien);
			tongCongPhiPhatSinh += nft.format(tongCongPhiHoanDoiTra);
		}
		String thoiGianHienTai = LocalDateTime.now().format(dftNgayHienTaiOutputFile);
		tenFile += dftOutputFile.format(tu) + "_" + dftOutputFile.format(den) + "_" + thoiGianHienTai + ".xlsx";
		XSSFCell cell0r4 = row4.createCell(0);
		cell0r4.setCellValue(tongcong);
		cell0r4.setCellStyle(cellstyle);

		XSSFCell cell0r5 = row5.createCell(0);
		cell0r5.setCellValue(tongCongPhiPhatSinh);
		cell0r5.setCellStyle(cellstyle);

		for (int i = 0; i <= row3.getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 3000);
		}

		try {
			File file = new File("ThongKeExport/" + tenFile);
			FileOutputStream fos = new FileOutputStream(file);
			fileExcel.write(fos);
			fos.close();
			fileExcel.close();
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Xuất file excel thành công ");
	}

}
