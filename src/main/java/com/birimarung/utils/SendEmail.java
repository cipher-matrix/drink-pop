package com.birimarung.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
    public void sendEmail(String emailAddress,String message){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.example.com");
            email.setSmtpPort(587);
            email.setAuthentication("your_email@example.com", "your_password");
            email.setStartTLSRequired(true);
            email.setFrom("your_email@example.com");
            email.setSubject("HTML Email Test");
            email.setHtmlMsg("<h1>Hello</h1><p>This is an HTML email.</p>");
            email.addTo("recipient@example.com");
            email.send();
            System.out.println("HTML Email sent successfully!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
