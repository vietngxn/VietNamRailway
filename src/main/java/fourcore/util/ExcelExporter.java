package fourcore.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fourcore.Entity.Tau;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExcelExporter {
    
    /**
     * Xuất thống kê chuyến tàu chạy nhiều nhất
     */
    public static void exportThongKeTau(Map<Tau, Integer> map, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Thống Kê Chuyến Tàu");
            
            // Tạo header style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle totalStyle = createTotalStyle(workbook);
            
            // Tạo title
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("THỐNG KÊ CHUYẾN TÀU CHẠY NHIỀU NHẤT");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 2));
            
            // Tạo timestamp
            XSSFRow timeRow = sheet.createRow(1);
            XSSFCell timeCell = timeRow.createCell(0);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            timeCell.setCellValue("Thời gian xuất: " + now.format(formatter));
            
            // Tạo header cho bảng
            int headerRowNum = 3;
            XSSFRow headerRow = sheet.createRow(headerRowNum);
            
            String[] headers = {"Mã Tàu", "Tên Tàu", "Số Chuyến Đi"};
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Điều chỉnh độ rộng cột
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            
            // Điền dữ liệu
            int rowNum = 4;
            for (Map.Entry<Tau, Integer> entry : map.entrySet()) {
                Tau tau = entry.getKey();
                Integer soLan = entry.getValue();
                
                XSSFRow row = sheet.createRow(rowNum++);
                
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(tau.getMaTau());
                cell0.setCellStyle(dataStyle);
                
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(tau.getTenTau());
                cell1.setCellStyle(dataStyle);
                
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(soLan);
                cell2.setCellStyle(dataStyle);
            }
            
            // Tạo tổng cộng
//            int totalRowNum = rowNum + 1;
//            XSSFRow totalRow = sheet.createRow(totalRowNum);
//            XSSFCell totalLabelCell = totalRow.createCell(1);
//            totalLabelCell.setCellValue("Tổng Cộng:");
//            totalLabelCell.setCellStyle(totalStyle);
//            
//            XSSFCell totalValueCell = totalRow.createCell(2);
//            totalValueCell.setCellValue(map.size());
//            totalValueCell.setCellStyle(totalStyle);
            
            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
//            showSuccessAlert("Xuất file thành công!", "File đã được lưu tại:\n" + filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Lỗi", "Không thể xuất file: " + e.getMessage());
        }
    }
    
    public static void exportThongKeKhachHang(Map<?, Integer> map, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Thống Kê Khách Hàng");
            
            // Tạo header style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle totalStyle = createTotalStyle(workbook);
            
            // Tạo title
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("THỐNG KÊ TOP 10 KHÁCH HÀNG ĐI TÀU NHIỀU NHẤT");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 2));
            
            // Tạo timestamp
            XSSFRow timeRow = sheet.createRow(1);
            XSSFCell timeCell = timeRow.createCell(0);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            timeCell.setCellValue("Thời gian xuất: " + now.format(formatter));
            
            // Tạo header cho bảng
            int headerRowNum = 3;
            XSSFRow headerRow = sheet.createRow(headerRowNum);
            
            String[] headers = {"Mã Khách Hàng", "Tên Khách Hàng", "Số Lần Đi"};
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Điều chỉnh độ rộng cột
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            
            // Điền dữ liệu
            int rowNum = 4;
            int count = 1;
            for (Map.Entry<?, Integer> entry : map.entrySet()) {
                Object khObj = entry.getKey();
                Integer soLan = entry.getValue();
                
                XSSFRow row = sheet.createRow(rowNum++);
                
                // Lấy thông tin khách hàng từ object bằng reflection
                String maKH = "N/A";
                String tenKH = "N/A";
                
                try {
                    maKH = (String) khObj.getClass().getMethod("getMaKhachHang").invoke(khObj);
                    tenKH = (String) khObj.getClass().getMethod("getHoten").invoke(khObj);
                } catch (Exception e) {
                    // Nếu không lấy được, sử dụng toString()
                    tenKH = khObj != null ? khObj.toString() : "N/A";
                }
                
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(maKH);
                cell0.setCellStyle(dataStyle);
                
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(tenKH);
                cell1.setCellStyle(dataStyle);
                
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(soLan);
                cell2.setCellStyle(dataStyle);
                
                count++;
                if (count > 10) break; // Chỉ lấy top 10
            }
            
            // Tạo tổng cộng
//            int totalRowNum = rowNum + 1;
//            XSSFRow totalRow = sheet.createRow(totalRowNum);
//            XSSFCell totalLabelCell = totalRow.createCell(1);
//            totalLabelCell.setCellValue("Tổng Khách Hàng:");
//            totalLabelCell.setCellStyle(totalStyle);
//            
//            XSSFCell totalValueCell = totalRow.createCell(2);
//            totalValueCell.setCellValue(Math.min(map.size(), 10));
//            totalValueCell.setCellStyle(totalStyle);
            
            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
//            showSuccessAlert("Xuất file thành công!", "File đã được lưu tại:\n" + filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Lỗi", "Không thể xuất file: " + e.getMessage());
        }
    }
    
    /**
     * Xuất thống kê doanh thu theo tháng trong năm
     */
    public static void exportThongKeDoanhThuTheoNam(Map<Integer, Double> map, int nam, double tongDoanhThu, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Thống Kê Doanh Thu");
            
            // Tạo các style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle totalStyle = createTotalStyle(workbook);
            
            // Tạo title
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("THỐNG KÊ DOANH THU THEO THÁNG NĂM " + nam);
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 1));
            
            // Tạo timestamp
            XSSFRow timeRow = sheet.createRow(1);
            XSSFCell timeCell = timeRow.createCell(0);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            timeCell.setCellValue("Thời gian xuất: " + now.format(formatter));
            
            // Tạo header cho bảng
            int headerRowNum = 3;
            XSSFRow headerRow = sheet.createRow(headerRowNum);
            
            String[] headers = {"Tháng", "Doanh Thu (đ)"};
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Điều chỉnh độ rộng cột
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 6000);
            
            // Điền dữ liệu
            int rowNum = 4;
            for (int thang = 1; thang <= 12; thang++) {
                Double doanhThu = map.getOrDefault(thang, 0.0);
                
                XSSFRow row = sheet.createRow(rowNum++);
                
                XSSFCell cellThang = row.createCell(0);
                cellThang.setCellValue("Tháng " + thang);
                cellThang.setCellStyle(dataStyle);
                
                XSSFCell cellDoanhThu = row.createCell(1);
                cellDoanhThu.setCellValue(doanhThu);
                cellDoanhThu.setCellStyle(dataStyle);
            }
            
            // Tạo tổng doanh thu
            int totalRowNum = rowNum + 1;
            XSSFRow totalRow = sheet.createRow(totalRowNum);
            
            XSSFCell totalLabelCell = totalRow.createCell(0);
            totalLabelCell.setCellValue("Tổng Doanh Thu:");
            totalLabelCell.setCellStyle(totalStyle);
            
            XSSFCell totalValueCell = totalRow.createCell(1);
            totalValueCell.setCellValue(tongDoanhThu);
            totalValueCell.setCellStyle(totalStyle);
            
            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
//            showSuccessAlert("Xuất file thành công!", "File đã được lưu tại:\n" + filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Lỗi", "Không thể xuất file: " + e.getMessage());
        }
    }
    
    
    public static void exporthongKeDoanhThuPieChart(Map<String,Double> map,int nam ,double tongDoanhThu,String filepath ) throws IOException
    {
    	try(XSSFWorkbook work = new XSSFWorkbook())
    	{
    		XSSFSheet sheet = work.createSheet();
    		
    		CellStyle header = createHeaderStyle(work);
    		CellStyle  title = createTitleStyle(work);
    		CellStyle  data = createDataStyle(work);
    		
    		XSSFRow titlerow = sheet.createRow(0);
    		XSSFCell titlecell = titlerow.createCell(0);
    		titlecell.setCellValue("Thống Kê doanh thu theo piechart "+ nam);
    		titlecell.setCellStyle(title);
    		
    		 XSSFRow timeRow = sheet.createRow(1);
    		 XSSFCell timeCell = timeRow.createCell(0);
    	     LocalDateTime now = LocalDateTime.now();
    	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    	     timeCell.setCellValue("Thời gian xuất: " + now.format(formatter));
    		
    	     int headerRowNum = 3;
    	        XSSFRow headerRow = sheet.createRow(headerRowNum);
    	        
    	        String[] headers = {"Loại Doanh Thu", "Giá trị (đ)", "Tỷ lệ (%)"};
    	        for (int i = 0; i < headers.length; i++) {
    	            XSSFCell cell = headerRow.createCell(i);
    	            cell.setCellValue(headers[i]);
    	            cell.setCellStyle(header);
    	        }
    	        
    	       
    	        sheet.setColumnWidth(0, 5000);
    	        sheet.setColumnWidth(1, 6000);
    	        sheet.setColumnWidth(2, 4000);
    	        
    	        int rowNum = 3;
    	        
    	        double totalValue = 0;
    	        for (Map.Entry<String, Double> entry : map.entrySet()) {
    	            totalValue += entry.getValue();
    	        }
    	        
    	        
    		
    		
    	}
    }
    
    
    public static void exportThongKeDoanhThutheoTau(Map<Tau, Double> map, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Thống Kê Chuyến Tàu");
            
            // Tạo header style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle totalStyle = createTotalStyle(workbook);
            
            // Tạo title
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("THỐNG KÊ CHUYẾN TÀU DOANH THU CAO NHẤT");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 2));
            
            // Tạo timestamp
            XSSFRow timeRow = sheet.createRow(1);
            XSSFCell timeCell = timeRow.createCell(0);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            timeCell.setCellValue("Thời gian xuất: " + now.format(formatter));
            
            // Tạo header cho bảng
            int headerRowNum = 3;
            XSSFRow headerRow = sheet.createRow(headerRowNum);
            
            double doanhthu = 0;
            String[] headers = {"Mã Tàu", "Tên Tàu", "Doanh Thu"};
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Điều chỉnh độ rộng cột
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            
            // Điền dữ liệu
            int rowNum = 4;
            for (Map.Entry<Tau, Double> entry : map.entrySet()) {
                Tau tau = entry.getKey();
                double soLan = entry.getValue();
                
                XSSFRow row = sheet.createRow(rowNum++);
                
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(tau.getMaTau());
                cell0.setCellStyle(dataStyle);
                
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(tau.getTenTau());
                cell1.setCellStyle(dataStyle);
                
                XSSFCell cell2 = row.createCell(2);
                DecimalFormat df = new DecimalFormat("#,###");
                String tf = df.format(soLan);
                cell2.setCellValue(tf+ " +");
                cell2.setCellStyle(dataStyle);
                doanhthu += entry.getValue();
            }
            
//             Tạo tổng cộng
            double totalRowNum = rowNum + 1;
            XSSFRow totalRow = sheet.createRow((int) totalRowNum);
            XSSFCell totalLabelCell = totalRow.createCell(1);
            totalLabelCell.setCellValue("Tổng Cộng:");
            totalLabelCell.setCellStyle(totalStyle);
            
            XSSFCell totalValueCell = totalRow.createCell(2);
            DecimalFormat df = new DecimalFormat("#,###");
            String tf = df.format(doanhthu);
            totalValueCell.setCellValue(tf+ " đ");
            totalValueCell.setCellStyle(totalStyle);
            
            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
//            showSuccessAlert("Xuất file thành công!", "File đã được lưu tại:\n" + filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Lỗi", "Không thể xuất file: " + e.getMessage());
        }
    }
    
    private static CellStyle createHeaderStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 12);
        
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        
        return style;
    }
    
    private static CellStyle createTitleStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        return style;
    }
    
    private static CellStyle createDataStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        
        return style;
    }
    
    private static CellStyle createTotalStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        
        return style;
    }
    
    private static void showSuccessAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private static void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}