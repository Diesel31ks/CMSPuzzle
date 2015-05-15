package hibernate.dao;

import hibernate.tables.Content;

import java.sql.SQLException;
import java.util.List;

public interface ContentDao{

	void addContent(Content content) throws SQLException;

	Content getContent(int id) throws SQLException;

	List<Content> getContents() throws SQLException;

	void deleteContent(Content content) throws SQLException;

}
