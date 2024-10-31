package utp.edu.pe.jracero.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ApachePOI {
    public void sendEmail(String userEmail, String subject, String message){
        String username = "ayapalleckhotel@gmail.com";
        String appPassword = "xvup roow nvjy bltj"; // Replace with the 16-character App Password

        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(username, appPassword));
            email.setSSLOnConnect(true);
            email.setFrom(username);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(userEmail);
            email.send();

            System.out.println("Email sent successfully!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}