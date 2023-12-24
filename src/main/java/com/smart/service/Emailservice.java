package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Emailservice {

	public boolean sendEmail(String subject, String message, String to) {
		boolean f = false;
		//String from = "guptagovind0030@gmail.com";
String from="shaktig101101@gmail.com";
		// gmail host
		String host = "smtp.gmail.com";
		//DDTSettings settings = DDTSettings.Settings();
//	      final String sender = settings.emailSender();
//	      final String password = settings.emailPassword();
//		 boolean emailAuthenticationRequired = settings.emailAuthenticationRequired();
//		String trueOrFalse = emailAuthenticationRequired ? "true" : "false";
		// get the System Properties
		Properties properties = System.getProperties();
		System.out.println("properties >>>" + properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		//properties.put("mail.smtp.ssl.enable", "false");
		properties.put("mail.smtp.auth", "true");
		//properties.put("mail.debug", "false");
		//properties.put("java.net.preferIPv4Stack","true");
	    properties.put("mail.smtp.isSSL.enable", "false");
	    properties.put("mail.smtp.starttls.enable", "true");
	   // properties.put("mail.smtp.socketFactory.fallback", "true");
	    properties.put("mail.smtp.user", "shaktig101101@gmail.com");
	    properties.put("mail.smtp.password", "yrpglbfqodzpxfnz");

		// step 1:to get the session object..
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("shaktig101101@gmail.com", "yrpglbfqodzpxfnz");
			}

		});
		session.setDebug(true);
		try {
			// Step 2 : compose the message [text ,multi medie]
//MimeMessage m = MimeMessage(session);
			// Create a default MimeMessage object.
            MimeMessage m = new MimeMessage(session);
			// from mail
			m.setFrom(from);
			// adding recipient to maesage
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			//m.setText(message);
            m.setContent(message, "text/html");
			
			// Step 3: send the message using Transport class
			Transport.send(m);

			System.out.println("sending massage success ................");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	private MimeMessage MimeMessage(Session session) {
		// TODO Auto-generated method stub
		return null;
	}
}
