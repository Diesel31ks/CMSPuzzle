package authorization;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import hibernate.general.HibernateFactory;
import hibernate.tables.User;


public class TestGettingUserPasswod {
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User();
		user = HibernateFactory.getInstance().getUserDao().getUser(1);
		System.out.println("User validation "+PasswordHash.validatePassword("12345678", user.getPassword()));
	}

}
