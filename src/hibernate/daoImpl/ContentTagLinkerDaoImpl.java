package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import hibernate.dao.ContentTagLinkerDao;
import hibernate.tables.ContentTagLinker;
import hibernate.util.HibernateDaoBuilder;

public class ContentTagLinkerDaoImpl implements ContentTagLinkerDao {

	public void addLink(ContentTagLinker contentTagLinker) throws SQLException {
		HibernateDaoBuilder.saveTableValue(contentTagLinker);
	}

	public ContentTagLinker getLink(int id) throws SQLException {
		return (ContentTagLinker) HibernateDaoBuilder.getTableValue(id,
				new ContentTagLinker());
	}

	public List<ContentTagLinker> getLinks() throws SQLException {
		return (List<ContentTagLinker>) HibernateDaoBuilder
				.getTableValues(new ContentTagLinker());
	}

	public void deleteLink(ContentTagLinker contentTagLinker)
			throws SQLException {
		HibernateDaoBuilder.deleteTableValue(contentTagLinker);
	}

	/**
	 * get list of ContentTagLinker by 1 property
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<ContentTagLinker> getContentTagLinkersByProperty(
			String propertyName, Object propertyValue) throws SQLException {
		return (List<ContentTagLinker>) HibernateDaoBuilder
				.getTableValuesByProperty(propertyName, propertyValue,
						new ContentTagLinker());
	}

	/**
	 * get list of ContentTagLinker by 2 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<ContentTagLinker> getContentTagLinkersByProperty(
			String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2) throws SQLException {
		return (List<ContentTagLinker>) HibernateDaoBuilder
				.getTableValuesByProperty(propertyName1, propertyValue1,
						propertyName2, propertyValue2, new ContentTagLinker());
	}

	public void updateLink(ContentTagLinker contentTagLinker)
			throws SQLException {
		HibernateDaoBuilder.updateTableValue(contentTagLinker);
	}
}
