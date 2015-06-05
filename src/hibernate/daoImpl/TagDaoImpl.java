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
		return (Tag) HibernateDaoBuilder.getTableValue(id, new Tag());
	}

	public List<Tag> getTags() throws SQLException {
		return (List<Tag>) HibernateDaoBuilder.getTableValues(new Tag());
	}

	public void deleteTag(Tag tag) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(tag);
	}

	/**
	 * get list of Tag by 1 property
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<Tag> getTagsByProperty(String propertyName, Object propertyValue)
			throws SQLException {
		return (List<Tag>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName, propertyValue, new Tag());
	}

	/**
	 * get list of Tag by 2 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<Tag> getTagsByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2)
			throws SQLException {
		return (List<Tag>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName1, propertyValue1, propertyName2, propertyValue2,
				new Tag());
	}

	/**
	 * get list of Tag by 3 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<Tag> getTagsByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2,
			String propertyName3, Object propertyValue3) throws SQLException {
		return (List<Tag>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName1, propertyValue1, propertyName2, propertyValue2,
				propertyName3, propertyValue3, new Tag());
	}

	public void updateTag(Tag tag) throws SQLException {
		HibernateDaoBuilder.updateTableValue(tag);
	}

}
