package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.FrontPageDao;
import hibernate.tables.FrontPage;
import hibernate.util.HibernateUtil;

public class FrontPageDaoImpl implements FrontPageDao {

	@Override
	public void addFrontPageContent(FrontPage frontPage) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(frontPage);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public FrontPage getFrontPageContent(int id) throws SQLException {
		Session session = null;
		FrontPage frontPage = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			frontPage = (FrontPage) session.get(FrontPage.class, id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return frontPage;
	}

	@Override
	public List<FrontPage> getFrontPageContents() throws SQLException {
		Session session = null;
		List<FrontPage> contents = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			contents = session.createCriteria(FrontPage.class).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return contents;
	}

	@Override
	public void deleteFrontPageContent(FrontPage frontPage) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(frontPage);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}
