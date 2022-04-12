package com.selenium.library;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Email Manager
 * 
 * @author salag
 *
 */
public class EmailManager {

	public static Logger log = LoggerFactory.getLogger(EmailManager.class);

	private String toAddress = "";
	private String ccAddress = "";
	private String bccAddress = "";
	private List<String> attachements = new ArrayList<String>();
	
	public void setAttachements(List<String> tempAttachements) {
		this.attachements = tempAttachements;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public void sendEmail(String subject, String emailBody) {
		
		//to be updated with info from company
		String host = "smtp.gmail.com";
		String port = "587";
		String userID = "";
		String userPass = "";
	
		sendEmail(host, port, userID, userPass, subject, emailBody, attachements);
	}

	private void sendEmail(String hostName, String port, final String emailUserID, final String emailUserPass,
			String emailSubject, String emailBody, List<String> attachments) {
		try {
			
			// set SMTP server properties
			Properties prop = new Properties();
			//to be updated with info from company
			prop.put("mail.smtp.host", hostName);
			prop.put("mail.smtp.port", port);
			prop.put("mail.smtp.auth", true);
			prop.put("mail.user", emailUserID);
			prop.put("mail.password", emailUserPass);
			prop.put("mail.smtp.starttls.enable", true);
			log.info("Step01> Preparing email configuration...");

			// create a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailUserID, emailUserPass);
				}
			};

			Session session = Session.getInstance(prop, auth);
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(emailUserID));
			msg.addRecipients(Message.RecipientType.TO, setMultipleEmails(toAddress));
			if (!(ccAddress.isEmpty()) && !(ccAddress.equals(null))) {
				msg.addRecipients(Message.RecipientType.CC, setMultipleEmails(ccAddress));
			}
			if (!(bccAddress.isEmpty()) && !(bccAddress.equals(null))) {
				msg.addRecipients(Message.RecipientType.BCC, setMultipleEmails(bccAddress));
			}

			msg.setSubject(emailSubject);
			msg.setSentDate(new Date());

			// create message parts
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailBody, "text/html");
			// create multi-parts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			// add attachements
			if (attachments.size() > 0) {
				for (String singleAttachement : attachments) {
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						attachPart.attachFile(singleAttachement);
					} catch (Exception e) {
						log.error("Attaching files to email failed...", e);
					}
					multipart.addBodyPart(attachPart);
				}
			}
			log.info("Step2> Attaching report files and error screenshots...");
			msg.setContent(multipart);

			log.info("Step3> Sending email in progress...");
			Transport.send(msg);

			log.info("Step4> Sending email completed.");

		} catch (Exception e) {
			log.error("Seding email failed...");
			log.error("Error", e);
			assertTrue(false);
		}
	}

	private Address[] setMultipleEmails(String emailAddresses) {
		String multipleEmails[] = emailAddresses.split(";");
		InternetAddress[] addresses = new InternetAddress[multipleEmails.length];
		try {
			for (int i = 0; i < multipleEmails.length; i++) {
				addresses[i] = new InternetAddress(multipleEmails[i].trim());
			}
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return addresses;
	}

//	public static void main(String[] args) {
//		EmailManager myEmail = new EmailManager();
//		myEmail.toAddress = "salagor.denis@gmail.com";
//		myEmail.ccAddress = "dsologor@yahoo.com";
//
//		String subject = "Congrats, email sent!";
//		String emailBody = "Congratulations! " + "<br><br>" + "You received it!" + "<br><br><br>" + "Regards!" + "<br>"
//				+ "HR Team";
//		myEmail.sendEmail(subject, emailBody);
//
//	}

}
