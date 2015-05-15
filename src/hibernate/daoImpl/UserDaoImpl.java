package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.UserDao;
import hibernate.tables.User;
import hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public User getUser(int id) throws SQLException {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			user = (User) session.get(User.class, id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		Session session = null;
		List<User> users = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			users = session.createCriteria(User.class).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return users;
	}


	@Override
	public void deleteUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}
