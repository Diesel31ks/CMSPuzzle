package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import hibernate.dao.ContentDao;
import hibernate.tables.Content;
import hibernate.util.HibernateUtil;

public class ContentDaoImpl implements ContentDao {

	@Override
	public void addContent(Content content) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(content);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Content getContent(int id) throws SQLException {
		Session session = null;
		Content content = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			content = (Content) session.get(Content.class, id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return content;
	}

	@Override
	public List<Content> getContents() throws SQLException {
		Session session = null;
		List<Content> contents = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			contents = session.createCriteria(Content.class).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return contents;
	}

	@Override
	public void deleteContent(Content content) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(content);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
