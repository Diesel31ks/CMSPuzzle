package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.ContentTagLinkerDao;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.User;
import hibernate.util.HibernateUtil;

public class ContentTagLinkerDaoImpl implements ContentTagLinkerDao {

	@Override
	public void addLink(ContentTagLinker contentTagLinker) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(contentTagLinker);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}

	}

	@Override
	public ContentTagLinker getLink(int id) throws SQLException {
		Session session = null;
		ContentTagLinker contentTagLinker = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			contentTagLinker = (ContentTagLinker) session.get(ContentTagLinker.class, id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return contentTagLinker;
	}

	@Override
	public List<ContentTagLinker> getLinks() throws SQLException {
		Session session = null;
		List<ContentTagLinker> contentTagLinkers= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			contentTagLinkers = session.createCriteria(ContentTagLinker.class).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return contentTagLinkers;
	}

	@Override
	public void deleteLink(ContentTagLinker contentTagLinker) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(contentTagLinker);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
