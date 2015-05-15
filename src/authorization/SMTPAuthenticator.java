package authorization;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{
	private String email;
	private String password;
	
	public SMTPAuthenticator(String email, String password ) {
		this.email = email;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, password);
	}
	
}
