package services.OTP;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailManger
{

    private  String username;
    private  String password;

    private String recipientEmail;
    private String recipientUserName;
    private String subject;
    private String body;

    private Properties props;

    private  Session session;

    private boolean isSent;


    public EmailManger()
    {
        // init sender and password
        this.username = "ttoffestore@gmail.com";
        this.password = "yeubtcjyymleyjgk";

        // set props
        setProps();

        // set session
        setSession();
    }

    public EmailManger(String senderEmail, String senderPassword)
    {

        // init sender and password
        this.username = senderEmail;
        this.password = senderPassword;

        // set props
        setProps();

        // set session
        setSession();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }


    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isSent()
    {
        return this.isSent;
    }

    public void sendEmail(String recipientEmail, String recipientUserName, String subject, String body)
    {
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText("Dear " + recipientUserName + ",\n\n" + body);
            Transport.send(message);

            this.isSent = true;

        }
        catch (Exception e)
        {
            this.isSent = false;
            System.out.println(e);
        }
    }

    private void setProps()
    {
        // init props
        this.props = new Properties();

        // set props
        this.props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.debug.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
    }

    private void setSession()
    {
        // init session
        // set session
        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}


