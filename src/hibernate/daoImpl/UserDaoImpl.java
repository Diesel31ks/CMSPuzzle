package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.UserDao;
import hibernate.tables.User;
import hibernate.util.HibernateDaoBuilder;
import hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) throws SQLException {
		HibernateDaoBuilder.saveTableValue(user);
	}

	@Override
	public User getUser(int id) throws SQLException {
		return (User)HibernateDaoBuilder.getTableValue(id, User.class);
	}

	@Override
	public List<User> getUsers() throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValues(User.class);
//		Session session = null;
//		List<User> users = null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			users = session.createCriteria(User.class).list();
//			session.getTransaction().commit();
//		} finally {
//			if ((session != null) && (session.isOpen())) {
//				session.close();
//			}
//		}
//		return users;
	}

	@Override
	public void deleteUser(User user) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(user);
	}
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			session.delete(user);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//		} finally {
//			if ((session != null) && (session.isOpen())) {
//				session.close();
//			}
//		}
//	}

}
