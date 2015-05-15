package hibernate.util;

import hibernate.tables.Table;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

public class HibernateDaoBuilder{
	private HibernateDaoBuilder(){}

	public static void saveTableValue(Table table) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(table);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public static Table getTableValue(int id, Class<? extends Table> obj) throws SQLException {
		Session session = null;
		Table table = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			table = (Table) session.get(obj, id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return table;
	}
	
	public static List<? extends Table> getTableValues(Class<? extends Table> obj) throws SQLException {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tableValues = session.createCriteria(obj.getClass()).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tableValues;
	}
	
	public static void deleteTableValue(Table obj) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
