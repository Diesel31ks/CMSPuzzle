package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.TagDao;
import hibernate.tables.Tag;
import hibernate.util.HibernateUtil;

public class TagDaoImpl implements TagDao {

	@Override
	public void addTag(Tag tag) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Tag getTag(int id) throws SQLException {
		Session session = null;
		Tag tag = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tag = (Tag) session.load(Tag.class, id);
			session.getTransaction().commit();
		}finally{
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tag;
	}

	@Override
	public List<Tag> getTags() throws SQLException {
		Session session = null;
		List<Tag> tags = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tags = session.createCriteria(Tag.class).list();
			session.getTransaction().commit();
		}finally{
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tags;
	}

	@Override
	public void deleteTag(Tag tag) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(tag);
			session.getTransaction().commit();
		}finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}
