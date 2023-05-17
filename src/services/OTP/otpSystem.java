package services.OTP;

import apps.App;
import utilities.Utility;

import java.util.Random;

/**
 * The OTPSystem class represents a system for generating and verifying OTP (One Time Password) for authentication.
 * It extends the App class.
 */
public class otpSystem extends App
{
    /* Instance attributes */

    private String recipientEmail;
    private String recipientUserName;
    private String subject;
    private String body;

    private String generatedOTP;
    private boolean matched;

    EmailManger emailManger;

    /* Constructors */

    /**
     * Constructs an OTPSystem object with the specified recipient email and recipient username.
     *
     * @param recipientEmail    The email address of the recipient.
     * @param recipientUserName The username of the recipient.
     */
    public otpSystem(String recipientEmail, String recipientUserName) {
        super();
        this.recipientEmail = recipientEmail;
        this.recipientUserName = recipientUserName;
        this.generatedOTP = generateOTP();
        this.matched = false;
        this.subject = "OTP Verification";
        this.body = "To authenticate, please use the following One Time Password (OTP): " + this.generatedOTP;
        this.emailManger = new EmailManger(); // Initialize the EmailManager instance
    }

    /* Getters and Setters */

    /**
     * Sets the recipient email.
     *
     * @param recipientEmail The email address of the recipient.
     */
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }


    /**
     * Gets the recipient email.
     *
     * @return The email address of the recipient.
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets the recipient username.
     *
     * @param recipientUserName The username of the recipient.
     */
    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }

    /**
     * Gets the recipient username.
     *
     * @return The username of the recipient.
     */
    public String getRecipientUserName() {
        return recipientUserName;
    }

    /**
     * Gets the body of the email containing the OTP.
     *
     * @return The body of the email.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the subject of the email containing the OTP.
     *
     * @return The subject of the email.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Checks if the OTP has been verified.
     *
     * @return true if the OTP has been verified, false otherwise.
     */
    public boolean verified() {
        return this.matched;
    }

    /**
     * Runs the OTP verification process.
     * Sends the OTP to the user's email, prompts the user to enter the OTP, and verifies it.
     */
    @Override
    public void run() {
        // Send OTP to user
        emailManger.sendEmail(getRecipientEmail(), getRecipientUserName(), getSubject(), getBody());

        Utility.printWelcomingMessage("!! Please check your email and confirm the OTP we've sent you !!");
        // Ask user to input OTP
        String userOTP = Utility.getValidOTPFromUser();

        while (getAppStatus()) {
            while (!userOTP.equals(this.generatedOTP)) {
                Utility.printFormatedMessage("!! Wrong OTP !!", false);

                userOTP = Utility.getValidOTPFromUser();
            }

            // Done verifying OTP
            setAppStatus(false);
            break;
        }
    }
    /**
     * Generates a random OTP of the specified length.
     *
     * @return The generated OTP as a string.
     */
    private String generateOTP() {
        return generateRandomIntString(10);
    }

    /**
     * Generates a random string of integers of the specified length.
     *
     * @param length The length of the string to generate.
     * @return The generated random string of integers.
     */
    public String generateRandomIntString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


}
