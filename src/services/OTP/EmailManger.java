package services.OTP;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * The EmailManager class is responsible for sending emails using SMTP protocol.
 */
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


    /**
     * Constructs an EmailManager object with default sender email and password.
     * The default sender email and password are set to a Gmail account.
     */
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

    /**
     * Constructs an EmailManager object with a custom sender email and password.
     *
     * @param senderEmail    The email address of the sender.
     * @param senderPassword The password of the sender's email account.
     */
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


    /**
     * Sets the email address of the sender.
     *
     * @param username The email address of the sender.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Sets the password of the sender's email account.
     *
     * @param password The password of the sender's email account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the recipient's username.
     *
     * @param recipientUserName The recipient's username.
     */
    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }

    /**
     * Sets the recipient's email address.
     *
     * @param recipientEmail The recipient's email address.
     */
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    /**
     * Sets the email body.
     *
     * @param body The email body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Sets the properties for the email.
     *
     * @param props The properties for the email.
     */
    public void setProps(Properties props) {
        this.props = props;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject The subject of the email.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Checks if the email has been successfully sent.
     *
     * @return True if the email has been sent successfully, false otherwise.
     */
    public boolean isSent()
    {
        return this.isSent;
    }

    /**
     * Sends an email to the specified recipient.
     *
     * @param recipientEmail    The recipient's email address.
     * @param recipientUserName The recipient's username.
     * @param subject           The subject of the email.
     * @param body              The body of the email.
     */
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

    /**
     * Initializes and sets the properties for the email.
     * The properties include authentication, TLS enablement, host, port, debugging, and SSL enablement.
     */
    private void setProps() {
        // Initialize properties
        this.props = new Properties();

        // Set properties
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true");
        this.props.put("mail.smtp.host", "smtp.gmail.com");
        this.props.put("mail.smtp.port", "465");
        this.props.put("mail.smtp.debug.enable", "true");
        this.props.put("mail.smtp.ssl.enable", "true");
    }

    /**
     * Initializes and sets up the email session using the properties and authentication.
     */
    private void setSession() {
        // Initialize session
        this.session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

}


