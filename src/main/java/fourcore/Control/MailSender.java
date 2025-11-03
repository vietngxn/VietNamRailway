package fourcore.Control;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    public void sendEmail(String toEmail, String filePath) throws MessagingException, IOException {

        // Thông tin tài khoản gửi
        final String fromEmail = "viet.nguyenba4605@gmail.com";
        final String password = "ybyr bdck rlgu nlmz";

        // Cấu hình SMTP (Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        // Tạo session với xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        // Phần nội dung text
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent("<p>Cảm ơn quý khách đã sử dụng dịch vụ của </p><h3>VietNamRailway</h3>", "text/html; charset=UTF-8");
        // đính kèm
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(new File(filePath));

        try {
            // Tạo nội dung mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setSubject("Vé điện tử từ VietNamRailway");
            message.setContent(multipart);

            // Gửi mail
            Transport.send(message);

            System.out.println("Gửi mail thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MessagingException, IOException {
        MailSender sender = new MailSender();
        sender.sendEmail("tiendat20051812@gmail.com","qr/qrcode.png");
    }
}
