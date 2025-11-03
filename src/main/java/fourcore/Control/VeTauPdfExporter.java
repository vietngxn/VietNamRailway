package fourcore.Control;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fourcore.Entity.Ve;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VeTauPdfExporter {

    public static void main(String[] args) throws Exception {
        VeTauPdfExporter exporter = new VeTauPdfExporter();
    }

    public void exportVeTauToPdf(Ve ve) throws Exception {
        String dest = "VeTauExport/"+ve.getMaVeTau()+".pdf";
        String qrImagePath = "qr/qrcode.png"; // đường dẫn QR code
        String fontPath = "C:/Windows/Fonts/arial.ttf"; // font Unicode, thay theo hệ thống nếu cần
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Tạo font Unicode với nhúng font
        PdfFont font = PdfFontFactory.createFont(
                fontPath,
                com.itextpdf.io.font.PdfEncodings.IDENTITY_H,
                EmbeddingStrategy.PREFER_EMBEDDED
        );

        // Tiêu đề công ty
        Paragraph company = new Paragraph("CÔNG TY CỔ PHẦN VẬN TẢI\nĐƯỜNG SẮT VIỆT NAM")
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(12);
        document.add(company);

        // Tiêu đề vé
        Paragraph title = new Paragraph("THẺ LÊN TÀU HỎA\nBOARDING PASS")
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(16)
                .setMarginTop(10)
                .setMarginBottom(10);
        document.add(title);

        // Thêm ảnh QR code
        ImageData qrData = ImageDataFactory.create(qrImagePath);
        Image qrCode = new Image(qrData)
                .scaleToFit(150, 150)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(qrCode);

        document.add(new Paragraph("\n"));

        // Tạo bảng 2 cột
        float[] colWidths = {100, 150, 100, 150};
        Table table = new Table(colWidths).setWidth(UnitValue.createPercentValue(100));

        // Hàm tiện lợi thêm row
        java.util.function.BiConsumer<String, String> addRow = (label, value) -> {
            table.addCell(new Cell().add(new Paragraph(label).setFont(font).setBold()));
            table.addCell(new Cell(1,3).add(new Paragraph(value).setFont(font)));
        };

        addRow.accept("Mã vé/TicketID:", ve.getMaVeTau());
        addRow.accept("Ga đi:", ve.getGaDi());
        addRow.accept("Ga đến:", ve.getGaDen());
        addRow.accept("Tàu/Train:", ve.getTenTau());
        addRow.accept("Ngày đi/Date:", ve.getNgayGioDi().format(dateFormat));
        addRow.accept("Giờ đi/Time:", ve.getNgayGioDi().format(timeFormat));
        addRow.accept("Toa/Coach:", ""+ve.getSoToa());
        addRow.accept("Chỗ ngồi/Seat:", ve.getSoGhe()+"");
        addRow.accept("Họ tên/Name:", ve.getKhachHang().getHoten());
        addRow.accept("Giấy tờ/Passport:", ve.getKhachHang().getCccd());

        document.add(table);
        Paragraph sub = new Paragraph("Ghi chú: thẻ lên tàu hỏa không phải là hóa đơn GTGT và không có giá trị thanh toán\n" +
                "This boarding pass is not an offical invoice\n" +
                "Để đảm bảo quyền lợi, quý khách vui lòng mang theo thẻ lên tàu hỏa cùng với gấy tờ tùy thân trong suốt hành trình.\n" +
                "Ngày in/Printed date: " + LocalDate.now().format(dateFormat))
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic()
                .setFontSize(12)
                .setMarginTop(10)
                .setMarginBottom(10);
        document.add(sub);


        document.close();
        File pdfFile = new File(dest);
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile); // mở file PDF bằng ứng dụng mặc định
            } else {
                System.out.println("Desktop không được hỗ trợ. Vui lòng mở file thủ công: " + pdfFile.getAbsolutePath());
            }
        } else {
            System.out.println("File PDF không tồn tại: " + dest);
        }
        System.out.println("PDF Boarding Pass đã được tạo: " + dest);
    }
}
