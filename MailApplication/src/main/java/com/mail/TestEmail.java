package com.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestEmail")
public class TestEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		Properties props = new Properties();
	       
		props.put("mail.smtp.host", "smtp.example.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("dhanasureddi08@gmail.com", "btvfldeqxrfylnnw");
		    }
		});

		try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("dhanasureddi08@gmail.com"));
		    message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse("cpraveen623@gmail.com"));
		    message.setSubject("Test email");
		    message.setText("This is a test email.");

		    Transport.send(message);

		    System.out.println("Test email sent successfully.");
		} catch (MessagingException e) {
		    System.out.println("Failed to send test email: " + e.getMessage());
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
