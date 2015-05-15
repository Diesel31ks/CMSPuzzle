package hibernate.dao;

import hibernate.tables.FrontPage;

import java.sql.SQLException;
import java.util.List;

public interface FrontPageDao{
	void addFrontPageContent(FrontPage frontPage) throws SQLException;

	FrontPage getFrontPageContent(int id) throws SQLException;

	List<FrontPage> getFrontPageContents() throws SQLException;

	void deleteFrontPageContent(FrontPage frontPage) throws SQLException;
}
