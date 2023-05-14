package com.mail;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


@WebServlet("/SendMailServlet2")
public class SendMailServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String[] to = request.getParameterValues("mail");
	        String subject = request.getParameter("subject");
	        String text = request.getParameter("compose");

	        // Get the date and time to schedule the email
	        String dateStr = request.getParameter("date");
	        String timeStr = request.getParameter("time");

	        // Parse the date and time strings into a Date object
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String scheduleDateTimeStr = dateStr + " " + timeStr + ":00";
	        Date scheduleDateTime = null;
			try {
				scheduleDateTime = formatter.parse(scheduleDateTimeStr);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Get the current date and time
	        Date currentDate = new Date();

	        // Calculate the delay time for scheduling the email
	        long delay = Math.abs(scheduleDateTime.getTime() - currentDate.getTime());


	        String host = "smtp.gmail.com";
	        String username = "dhanasureddi08@gmail.com";
	        String password = "btvfldeqxrfylnnw";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        try {
	            // Create a new MimeMessage object
	            MimeMessage mimeMessage = new MimeMessage(session);

	            // Set the From address
	            mimeMessage.setFrom(new InternetAddress(username));

	            // Add the recipients
	            for (String recipient : to) {
	                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	            }

	            // Set the Subject and Text
	            mimeMessage.setSubject(subject);
	            mimeMessage.setText(text);

	            // Create a new Timer object
	            Timer timer = new Timer();

	            // Schedule the email for the specified date and time
	            timer.schedule(new TimerTask() {
	                @Override
	                public void run() {
	                    try {
	                        // Send the email
	                        Transport.send(mimeMessage);
	                        System.out.println("Email sent successfully to " + Arrays.toString(to));
	                    } catch (MessagingException mex) {
	                        mex.printStackTrace();
	                        System.err.println("Error: Unable to send message. Please try again later.");
	                    }
	                }
	            }, delay);

	            // Print a message indicating the email has been scheduled
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<h2>Email scheduled successfully for " + scheduleDateTimeStr + "</h2>");

	        } catch (ParseException pex) {
	            pex.printStackTrace();
	            System.err.println("Error: Unable to parse date/time. Please try again.");
	        } catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	}

