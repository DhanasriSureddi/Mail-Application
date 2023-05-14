package com.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] to = request.getParameterValues("mail");
        String subject = request.getParameter("subject");
        String text = request.getParameter("compose");
        response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
        out.println("<h2> "+Arrays.toString(to) + " " +subject +"</h2>");

        String host = "smtp.gmail.com";
        String username = "sureddidhana21@gmail.com";
        String password = "#Dhana1234";

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
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            for (String recipient : to) {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            
            mimeMessage.setSubject(subject);
            mimeMessage.setText(text);

            Transport.send(mimeMessage);
            System.out.println("Email sent successfully to " + Arrays.toString(to));
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.err.println("Error: Unable to send message. Please try again later.");
        }
    }

}
