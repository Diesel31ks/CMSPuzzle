package hibernate.dao;

import hibernate.tables.ContentTagLinker;

import java.sql.SQLException;
import java.util.List;

public interface ContentTagLinkerDao {

	void addLink(ContentTagLinker contentTagLinker) throws SQLException;

	ContentTagLinker getLink(int id) throws SQLException;

	List<ContentTagLinker> getLinks() throws SQLException;

	void deleteLink(ContentTagLinker contentTagLinker) throws SQLException;
}
