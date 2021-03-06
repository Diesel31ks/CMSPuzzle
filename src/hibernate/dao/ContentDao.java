package hibernate.dao;

import hibernate.tables.Content;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Alexandr Khromov
 */
public interface ContentDao {

	void addContent(Content content) throws SQLException;

	Content getContent(int id) throws SQLException;

	List<Content> getContentsByProperty(String propertyName, Object propertyValue) throws SQLException;
	
	List<Content> getContents() throws SQLException;

	void updateContent(Content content) throws SQLException;
	
	void deleteContent(Content content) throws SQLException;
	
}
