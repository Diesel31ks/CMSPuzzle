package hibernate.util;

import hibernate.tables.Table;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
	
	public static void updateTableValue(Table table) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(table);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public static Table getTableValue(int id, Table table) throws SQLException {
		Session session = null;
		Table newTable = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			newTable = (Table) session.get(table.getClass(), id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newTable;
	}
	
	public static Table getTableValueByProperty(String propertyName, Object propertyValue, Table table) throws SQLException {
		Session session = null;
		Table newTable = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			newTable = (Table)session.createCriteria(table.getClass()).add(Restrictions.eq(propertyName, propertyValue)).uniqueResult();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newTable;
	}
	
	public static List<? extends Table> getTableValues(Table table) throws SQLException {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			System.out.println(table.getClass().toString());
			tableValues = session.createCriteria(table.getClass()).list();
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
