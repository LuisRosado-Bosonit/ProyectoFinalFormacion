package bosonit.formacion.appFinal.correo.infraestructure.Utils;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;



public class enviadorCorreos {
    final String username = "ejemplo@gmail.com";
    final String password = "password";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });


    try {

        // Define message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setSubject("asunto");
        message.addRecipient(Message.RecipientType.TO,new InternetAddress("cmedina@imptec.com.pe"));
        message.setText("gracias Chuidiang");
        // Envia el mensaje
        Transport.send(message);
    } catch (Exception e) {
    }

}
