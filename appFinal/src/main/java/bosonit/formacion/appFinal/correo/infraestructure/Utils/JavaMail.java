package bosonit.formacion.appFinal.correo.infraestructure.Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * Java implementa la función de envío de correo
 *
 *
 * @author WQ
 *
 */
public class JavaMail {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put ("mail.smtp.host", "smtp.163.com"); // dirección del servidor smtp

        Session session = Session.getInstance(props);
        session.setDebug(true);

        Message msg = new MimeMessage(session);
        msg.setSubject ("Este es un programa de prueba ...");
        msg.setText ("¡Hola! Este es mi primer programa javamail --- WQ");
        msg.setFrom (nueva dirección de Internet ("183 **** 1265@163.com")); // Buzón del remitente (mi buzón 163)
        msg.setRecipient(Message.RecipientType.TO,
                nueva dirección de Internet ("183 **** 1265@163.com")); // Buzón del destinatario (mi buzón QQ)
        msg.saveChanges();

        Transport transport = session.getTransport();
        transport.connect ("183 **** 1265@163.com", "d **** 56"); // Buzón del remitente, código de autorización (puede obtener la información del código de autorización en la configuración del buzón)

        transport.sendMessage(msg, msg.getAllRecipients());

        System.out.println ("Correo enviado con éxito ...");
        transport.close();
    }
}
