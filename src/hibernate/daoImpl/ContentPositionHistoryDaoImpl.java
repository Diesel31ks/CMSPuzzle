package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import hibernate.dao.ContentPositionHistoryDao;
import hibernate.tables.ContentPositionHistory;
import hibernate.util.HibernateDaoBuilder;
/**
 * @author Alexandr Khromov
 */
public class ContentPositionHistoryDaoImpl implements ContentPositionHistoryDao {

	public void addContentPositionHistory(
			ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.saveTableValue(contentPositionHistory);
	}

	public ContentPositionHistory getContentPositionHistory(int id)
			throws SQLException {
		return (ContentPositionHistory) HibernateDaoBuilder.getTableValue(id,
				new ContentPositionHistory());
	}

	public List<ContentPositionHistory> getContentPositionHistory()
			throws SQLException {
		return (List<ContentPositionHistory>) HibernateDaoBuilder
				.getTableValues(new ContentPositionHistory());
	}

	public void deleteContentPositionHistory(
			ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(contentPositionHistory);
	}

	/**
	 * get list of ContentPositionHistory of 1 property
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<ContentPositionHistory> getContentPositionHistoryByProperty(
			String propertyName, Object propertyValue) throws SQLException {
		return (List<ContentPositionHistory>) HibernateDaoBuilder
				.getTableValuesByProperty(propertyName, propertyValue,
						new ContentPositionHistory());
	}

	/**
	 * get list of ContentPositionHistory of 2 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<ContentPositionHistory> getContentPositionHistoryByProperty(
			String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2) throws SQLException {
		return (List<ContentPositionHistory>) HibernateDaoBuilder
				.getTableValuesByProperty(propertyName1, propertyValue1,
						propertyName2, propertyValue2,
						new ContentPositionHistory());
	}

	public void updateContentPositionHistory(
			ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.updateTableValue(contentPositionHistory);
	}
}
