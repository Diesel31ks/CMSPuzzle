package authorization;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.Authenticator;
import javax.mail.internet.*;

public class SendMessage {
	private static String email = "team_puzzle@mail.ua";
	private static String password = "puzart6$2015";
	private static String host = "smtp.mail.ru";
	private static String port = "465";

	public void sendMessage(String[] recipients, String subject, String text)
			throws MessagingException {

		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", host);
		mailProps.put("mail.smtp.port", port);
		mailProps.put("mail.smtp.user", email);
		mailProps.put("mail.smtp.password", password);
		mailProps.put("mail.smtp.starttls.enable", "true");
		mailProps.put("mail.smtp.auth", "true");
		// mailProps.put("mail.smtp.debug", "true");
		mailProps.put("mail.smtp.socketFactory.port", port);
		mailProps.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailProps.put("mail.smtp.socketFactory.fallback", "false");
		Authenticator auth = new SMTPAuthenticator(email, password);
		Session mailSession = Session.getDefaultInstance(mailProps, auth);

		MimeMessage mimeMessage = new MimeMessage(mailSession);
		String[] emails = recipients; // адреса получателей
		InternetAddress dests[] = new InternetAddress[emails.length];
		for (int i = 0; i < emails.length; i++) {
			dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
		}
		mimeMessage.setRecipients(Message.RecipientType.TO, dests);
		mimeMessage.setSubject(subject, "KOI8-R");

		Multipart multipart = new MimeMultipart();
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(text, "KOI8-R");
		multipart.addBodyPart(mbp1);

		mimeMessage.setFrom(email);
		mimeMessage.setContent(multipart);
		mimeMessage.setSentDate(new java.util.Date());

		Transport.send(mimeMessage);

	}
	// public class SMTPAuthenticator extends Authenticator{
	// @Override
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication(email, password);
	// }
	//
	// }
}
