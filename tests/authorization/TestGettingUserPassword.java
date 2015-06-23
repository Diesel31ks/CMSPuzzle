package authorization;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import hibernate.general.HibernateFactory;
import hibernate.tables.User;
/**
 * @author Alexandr Khromov
 */
public class TestGettingUserPassword {
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User();
		user = HibernateFactory.getInstance().getUserDao().getUser(1);
		System.out.println("User validation "+PasswordHash.validatePassword("123123123", user.getPassword()));
	}

}
