package authorization;

import javax.mail.MessagingException;

public class TestSendMessage {
	public static void main(String[] args) {
		try {
			String[] recipients = {"Diesel31ks@mail.ru"};
			String message = "�� ����������������� �� ����� CMSPuzzle-1.0-SNAPSHOT <br>" +
							 "��� ������������ ����������� �������� �� ������ <br>" +
							 "<a href=\"/CMSPuzzle-1.0-SNAPSHOT/login.jsp\" >Log in</a>";
			new SendMessage().sendMessage(recipients, "������������� �����������", message );
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
