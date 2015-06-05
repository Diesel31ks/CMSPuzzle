package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import hibernate.dao.FrontPageDao;
import hibernate.tables.FrontPage;
import hibernate.util.HibernateDaoBuilder;

public class FrontPageDaoImpl implements FrontPageDao {

	public void addFrontPageContent(FrontPage frontPage) throws SQLException {
		HibernateDaoBuilder.saveTableValue(frontPage);
	}

	public FrontPage getFrontPage(int id) throws SQLException {
		return (FrontPage) HibernateDaoBuilder.getTableValue(id,
				new FrontPage());
	}

	public List<FrontPage> getFrontPages() throws SQLException {
		return (List<FrontPage>) HibernateDaoBuilder
				.getTableValues(new FrontPage());
	}

	public void deleteFrontPage(FrontPage frontPage) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(frontPage);
	}

	/**
	 * get list of FrontPage by 1 property
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<FrontPage> getFrontPagesByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (List<FrontPage>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName, propertyValue, new FrontPage());
	}

	/**
	 * get list of FrontPage by 2 properties
	 * 
	 * @propertyName is a field in Object which is assigned with DB table.
	 * @propertyValue is a value of propertyName
	 */
	public List<FrontPage> getFrontPagesByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2)
			throws SQLException {
		return (List<FrontPage>) HibernateDaoBuilder.getTableValuesByProperty(
				propertyName1, propertyValue1, propertyName2, propertyValue2,
				new FrontPage());
	}

	public void updateFrontPage(FrontPage frontPage) throws SQLException {
		HibernateDaoBuilder.updateTableValue(frontPage);
	}

}
