package servlets;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import authorization.SendMessage;

public class ServletUtil {
	public static final String HOST = "10.9.2.159";
	
	public static Integer getRandomCode(){
		return new Random().nextInt(1_000_000_000);
	}
	
	public static void sendMessage(String[] recipients, String subject,String text) throws IOException {
		try {
			new SendMessage().sendMessage(recipients, subject, text);
			System.out.println("Message was sent");
		} catch (MessagingException |SecurityException e) {
			System.out.println("Message was not sent");
			e.printStackTrace();
		} 
	}
}
