package authorization;

import java.sql.SQLException;

import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import javax.mail.MessagingException;

public class TestSendMessage {
	public static void main(String[] args) throws SQLException {
		try {
			String[] recipients = {"Diesel31ks@mail.ru"};
			String subject = "������������� �����������";
			User user = HibernateFactory.getInstance().getUserDao().getUser(1);
			String confirmCode = user.getConfirmCode();
			String message = "�� ����������������� �� ����� CMSPuzzle-1_sasha."
					+ "��� ������������ ����������� �������� �� ������ "
					+"http://10.9.2.159:8090/CMSPuzzle-1_sasha/SuccessRegistration?confirmationCode="+
					confirmCode;
			new SendMessage().sendMessage(recipients, subject, message );
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
