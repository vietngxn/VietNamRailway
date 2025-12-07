package fourcore.Control;

import com.itextpdf.kernel.colors.ColorConstants;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fourcore.Entity.HoaDon;
import fourcore.Entity.KhuyenMai;
import fourcore.Entity.ThongTinCtHoaDon;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonExportPDF {

    public void exportHoaDon(HoaDon hoaDon, ArrayList<ThongTinCtHoaDon> chiTietList, KhuyenMai km) throws IOException, SQLException {
        System.out.println("size chi tiet list = " +chiTietList.size());
        String dest = "HoaDonOutput/"+hoaDon.getMaHoaDon()+".pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        String fontPath = "C:/Windows/Fonts/arial.ttf"; // font Unicode, thay theo hệ thống nếu cần

        // Font Unicode cho tiếng Việt
        PdfFont font = PdfFontFactory.createFont(
                fontPath,
                com.itextpdf.io.font.PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );        document.setFont(font);

        // Tiêu đề
        Paragraph title = new Paragraph("Hóa đơn bán vé")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Thông tin hóa đơn (theo mẫu bạn gửi)
        document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHoaDon()).setBold());
        document.add(new Paragraph("Đơn vị bán: Tổng công ty đường sắt Việt Nam                     Nhân viên bán hàng: " + hoaDon.getMaNhanVien().getHoTen()));
        document.add(new Paragraph("Địa chỉ: QMJH+RF6, Nguyễn Thông, Phường 9, Quận 3, Thành phố Hồ Chí Minh"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Người mua: " + hoaDon.getTenKhachHangThanhToan()));
        document.add(new Paragraph("Địa chỉ khách hàng: " + hoaDon.getDiaChiKhachHangThanhToan()));
        document.add(new Paragraph("Điện thoại: " + hoaDon.getSdtKhachHangThanhToan()));
        document.add(new Paragraph("CCCD: " + hoaDon.getCccdKhachHangThanhToan()));

        document.add(new Paragraph(" "));

        // Tạo bảng
        float[] columnWidths = {30f, 70f, 120f, 80f, 70f, 80f};
        Table table = new Table(UnitValue.createPointArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Header bảng
        String[] headers = {"STT", "Mã vé", "Tên loại ghế", "Đối tượng", "Đơn giá", "Thành tiền"};
        for (String h : headers) {
            table.addHeaderCell(new Cell().add(new Paragraph(h).setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        }

        // Dữ liệu từng dòng
        int stt = 1;
        for (ThongTinCtHoaDon item : chiTietList) {
            System.out.println(stt + " " + item.getMaVe() + " " + item.getTenLoaiGhe());

            table.addCell(String.valueOf(stt++));
            table.addCell(item.getMaVe());
            table.addCell(item.getTenLoaiGhe());
            table.addCell(item.getDoiTuong());
            table.addCell(String.format("%,.0f", item.getDonGia()));
            table.addCell(String.format("%,.0f", item.getThanhTien()));
        }

        document.add(table);

        document.add(new Paragraph(" "));

        // Tính tổng tiền, chiết khấu, VAT
        double tongCong = chiTietList.stream().mapToDouble(ThongTinCtHoaDon::getThanhTien).sum();
        double chietKhau = tongCong * (km.getGiaTriPhanTramKhuyenMai() / 100.0);
        double tongSauChietKhau = tongCong - chietKhau;
        double thueVAT = tongSauChietKhau * 0.08; // 8% thuế
        double tongThanhToan = tongSauChietKhau + thueVAT;

        // Các dòng tổng tiền ở cuối
        Paragraph pTongCong = new Paragraph(String.format("Tổng cộng: %, .0f đ", tongCong))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        Paragraph pChietKhau = new Paragraph(String.format("Chiết khấu (%.0f%%): -%,.0f đ", km.getGiaTriPhanTramKhuyenMai(), chietKhau))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        Paragraph pVAT = new Paragraph(String.format("Tổng cộng thuế (Thuế suất 8%%): %, .0f đ", thueVAT))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        Paragraph pThanhToan = new Paragraph(String.format("Tổng cộng tiền thanh toán: %, .0f đ", tongThanhToan))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();

        document.add(pTongCong);
        document.add(pChietKhau);
        document.add(pVAT);
        document.add(pThanhToan);

        // Chuyển số thành chữ - nếu bạn có class ChuyenSoThanhChu thì gọi ở đây
        // Ví dụ: ChuyenSoThanhChu converter = new ChuyenSoThanhChu();
        // String tienBangChu = converter.numberToVietnamese((long) tongThanhToan);
        // document.add(new Paragraph("Số tiền bằng chữ: " + tienBangChu).setTextAlignment(TextAlignment.RIGHT).setItalic());

        document.close();

        System.out.println("Xuất hóa đơn PDF thành công: " + dest);

        // Mở file PDF ngay sau khi tạo
        File pdfFile = new File(dest);
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop không được hỗ trợ trên hệ thống này.");
            }
        } else {
            System.out.println("File PDF không tồn tại: " + dest);
        }
    }
}
