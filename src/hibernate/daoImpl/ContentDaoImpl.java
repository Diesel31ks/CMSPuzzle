package hibernate.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import hibernate.dao.ContentDao;
import hibernate.tables.Content;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import hibernate.util.HibernateDaoBuilder;
/**
 * @author Alexandr Khromov
 */
public class ContentDaoImpl implements ContentDao{

	public void addContent(Content content) throws SQLException {
		HibernateDaoBuilder.saveTableValue(content);
	}

	public Content getContent(int id) throws SQLException {
		return (Content)HibernateDaoBuilder.getTableValue(id, new Content());
	}

	public List<Content> getContents() throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValues(new Content());
	}

	public void deleteContent(Content content) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(content);
	}

	/**
	 * get list of Content by 1 property
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<Content> getContentsByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName, propertyValue, new Content());
	}
	/**
//	 * get List of Content by 2 properties
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */	
	public List<Content> getContentsByProperty(String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName1, propertyValue1,propertyName2, propertyValue2, new Content());
	}
	/**
	 * get list of Content by 3 properties
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<Content> getContentsByProperty(String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2, String propertyName3, Object propertyValue3) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName1, propertyValue1, propertyName2, propertyValue2, propertyName3, propertyValue3, new Content());
	}

	public void updateContent(Content content) throws SQLException {
		HibernateDaoBuilder.updateTableValue(content);
	}
	
	public List<Content> getContentByUrl(String url) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty("url", url, new Content());
	}
	
	public List<Content> getContentByDate(Timestamp created) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty("created", created, new Content());
	}
	
	public List<Content> getContentByUser(User user) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty("author", user, new Content());
	}

	/*
	 * TODO check work or not
	 */
	public List<Content> getContentByUserRole(UserRole userRole) throws SQLException {
		User user = new User();
		user.setRole(userRole);
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty("author", user, new Content());
	}
	
	
}
