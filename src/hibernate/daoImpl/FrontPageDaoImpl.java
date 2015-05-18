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
		return (FrontPage)HibernateDaoBuilder.getTableValue(id, new FrontPage());
	}

	public List<FrontPage> getFrontPages() throws SQLException {
		return (List<FrontPage>)HibernateDaoBuilder.getTableValues(new FrontPage());
	}

	public void deleteFrontPage(FrontPage frontPage) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(frontPage);
	}

	public FrontPage getFrontPageByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (FrontPage)HibernateDaoBuilder.getTableValueByProperty(propertyName, propertyValue, new FrontPage());
	}

	public void updateFrontPage(FrontPage frontPage) throws SQLException {
		HibernateDaoBuilder.updateTableValue(frontPage);
	}

}
