package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import hibernate.dao.TagDao;
import hibernate.tables.Tag;
import hibernate.util.HibernateDaoBuilder;

public class TagDaoImpl implements TagDao {

	public void addTag(Tag tag) throws SQLException {
		HibernateDaoBuilder.saveTableValue(tag);
	}

	public Tag getTag(int id) throws SQLException {
		return (Tag)HibernateDaoBuilder.getTableValue(id, new Tag());	
	}

	public List<Tag> getTags() throws SQLException {
		return (List<Tag>)HibernateDaoBuilder.getTableValues(new Tag());
	}

	public void deleteTag(Tag tag) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(tag);
	}

	public Tag getTagByProperty(String propertyName, Object propertyValue)
			throws SQLException {
		return (Tag)HibernateDaoBuilder.getTableValueByProperty(propertyName, propertyValue, new Tag());
	}

	public void updateTag(Tag tag) throws SQLException {
		HibernateDaoBuilder.updateTableValue(tag);
	}

}
