package by.epam.webtask.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailSender {
    static final Logger logger = LogManager.getLogger();
    private MimeMessage message;
    private Properties properties;
    private static MailSender instance;

    public MailSender() {
        try {
            properties = new Properties();
            properties.load(new FileReader("src/main/resources/config/mail.properties"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static MailSender getInstance(){
        if(instance ==null){
            instance = new MailSender();
        }
        return instance;
    }

    public void send(String sendToEmail, String mailSubject, String mailText) {
        try {
            initMessage(sendToEmail, mailSubject, mailText);
            Transport.send(message);
        } catch (AddressException e) {
            logger.log(Level.ERROR, "Invalid address: " + sendToEmail + " " + e);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error generating or sending message: " + e);
        }
    }

    private void initMessage(String sendToEmail, String mailSubject, String mailText) throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
