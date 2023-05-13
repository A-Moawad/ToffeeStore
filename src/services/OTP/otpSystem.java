package services.OTP;

import apps.App;
import utilities.Utility;

import java.util.Random;

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
    public otpSystem (String recipientEmail, String recipientUserName)
    {
        super();

        this.recipientEmail = recipientEmail;
        this.recipientUserName = recipientUserName;

        this.generatedOTP = generateOTP();
        this.matched = false;
        this.subject = "OTP Verification";
        this.body = "To authenticate, please use the following One Time Password (OTP):" + this.generatedOTP;

        // default for system
        this.emailManger = new EmailManger();
    }


    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }


    public String getRecipientUserName() {
        return recipientUserName;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public boolean verified()
    {
        return this.matched;
    }


    @Override
    public void run()
    {
        // send otp to user
        emailManger.sendEmail(getRecipientEmail(), getRecipientUserName(), getSubject(), getBody());

        // ask user to get input
        String userOTP = Utility.getValidOTPFromUser();

        while (getAppStatus())
        {
            while(!userOTP.equals(this.generatedOTP))
            {
                Utility.printFormatedMessage("!! Wrong OTP !!", false);

                userOTP =  Utility.getValidOTPFromUser();
            }

            // done
            System.out.println("!! Correct OTP !!");
            setAppStatus(false);
            break;
        }
    }

    private String generateOTP()
    {
        return generateRandomIntString(10);
    }

    public  String generateRandomIntString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


}
