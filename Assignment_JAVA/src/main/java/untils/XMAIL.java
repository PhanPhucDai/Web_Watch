package untils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class XMAIL {

    final String email = "dai582005@gmail.com";
    final String password = "lfkh qcdx ssvm dkqs";
 
    public void configMail(Properties properties) {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    
    public void sendMail(String sendEmail, String contends ) {
        Properties properties = new Properties();
        configMail(properties);   
        Session session;

        try {
            
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmail));   
            message.setSubject("Quên mật khẩu");
            message.setText("Mật khẩu mới của bạn là: "+ contends+"\n"+"Vui lòng không cho ai biết mã này");

         
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendMailShareVideo(String sendEmail, String linkVideo, String contend) {
        Properties properties = new Properties();
        configMail(properties);   
        Session session;

        try {
            
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmail));   
            message.setSubject("Chia sẻ video");
            message.setText("Link video: http://localhost:8080/Assignment_JAVA/Video/deitail/"+linkVideo+"\n"+
            					"Nội dung"+ contend);

         
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
