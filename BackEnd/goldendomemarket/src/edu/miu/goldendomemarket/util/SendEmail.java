package edu.miu.goldendomemarket.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	private String smtp_host;
	private String smtp_socketFactory_port;
	private String smtp_port;
	private String sender;
	private String password;
	
	public SendEmail(String smtp_host, String smtp_socketFactory_port, String smtp_port, String sender,
			String password) {
		super();
		this.smtp_host = smtp_host;
		this.smtp_socketFactory_port = smtp_socketFactory_port;
		this.smtp_port = smtp_port;
		this.sender = sender;
		this.password = password;
	}

	public void sendEmail(String to,String sub,String msg) {
		Properties props = new Properties();    
        props.put("mail.smtp.host", smtp_host);    
        props.put("mail.smtp.socketFactory.port", smtp_socketFactory_port);    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", smtp_port);   
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(sender,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {System.out.println("failed to send");}    
           
  }  
}
