package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import hibernate.dao.UserDao;
import hibernate.tables.User;
import hibernate.util.HibernateDaoBuilder;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) throws SQLException {
		HibernateDaoBuilder.saveTableValue(user);
	}

	public User getUser(int id) throws SQLException {
		return (User) HibernateDaoBuilder.getTableValue(id, new User());
	}

	public List<User> getUsers() throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValues(new User());
	}

	public void deleteUser(User user) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(user);
	}

	/**
	 * get list of User by 1 property
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<User> getUsersByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName, propertyValue, new User());
	}

	/**
	 * get list of User by 2 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<User> getUsersByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2)
			throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName1, propertyValue1, propertyName2, propertyValue2,
				new User());
	}

	/**
	 * get list of User by 3 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<User> getUsersByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2,
			String propertyName3, Object propertyValue3) throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName1, propertyValue1, propertyName2, propertyValue2,
				propertyName3, propertyValue3, new User());
	}

	public void updateUser(User user) throws SQLException {
		HibernateDaoBuilder.updateTableValue(user);
	}
}
