package hibernate.general;

import hibernate.dao.ContentTagLinkerDao;
import hibernate.dao.FrontPageDao;
import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.FrontPage;
import hibernate.tables.Tag;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


public class Example {
	private static HibernateFactory factory = HibernateFactory.getInstance();

	public static void main(String[] args) throws SQLException {
		ContentTagLinkerDao contentTagLinkerDao= factory.getContentTagLinkerDao();
		FrontPageDao frontPageDao = factory.getFrontPageDao();
		
		set10Links(contentTagLinkerDao);
		set10FrontPages(frontPageDao);
	}
	
	private static void set10Links(ContentTagLinkerDao ctld)
			throws SQLException {
		ContentTagLinker contentTagLinker = new ContentTagLinker();
		Content content = new Content();
		User user = new User();
		user.setFirstName("Vasya");
		user.setLastName("Pupkin");
		user.setLogin("vas");
		try {
			user.setPassword("vas");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		user.setRole(UserRole.UNCONFIRMED);
		factory.getUserDao().addUser(user);
		content.setAuthor(user);
		content.setText("some text");
		factory.getContentDao().addContent(content);

		Tag tag = new Tag();
		tag.setName("Tag");
		tag.setUrl("http://1/1/1/1.html");
		factory.getTagDao().addTag(tag);

		contentTagLinker.setContent(content);
		ctld.addLink(contentTagLinker);

	}

	private static void set10FrontPages(FrontPageDao fpd) throws SQLException {
		FrontPage frontPage = new FrontPage();
		for (int i = 1; i < 11; i++) {
			frontPage.setAuthor(factory.getUserDao().getUser(1));
			frontPage.setContentId(factory.getContentDao().getContent(1));
			frontPage.setOrder(i);
			fpd.addFrontPageContent(frontPage);
		}
	}
}
