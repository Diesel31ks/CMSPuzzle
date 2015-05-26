package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserStatus;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authorization.PasswordHash;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final String SUCCESS_LOGIN = "/successLogin.jsp";
	private static final String RELOGIN = "/relogin.jsp";
	private static final String ERROR = "/error.jsp";
	private static final String LOGIN = "/login.jsp";
	private static final long serialVersionUID = -4050086711880871778L;
	UserDao userDaoImpl = HibernateFactory.getInstance().getUserDao();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		request.setAttribute("login", login);
		request.setAttribute("password", password);
		System.out.println("Login servlet working...");
		try {
			if ((login == "") || (login == null) || (password == "")
					|| (password == null) || (password.length() > 100)
					|| (password.length() < 8) || (password.contains(" "))
					|| (password.contains("/r")) || (password.contains("/n"))) {
				// request.setAttribute("access_wrong", true);
				getServletContext().getRequestDispatcher(LOGIN).forward(
						request, response);
				return;
			}
			List<User> users = null;
			User user = null;
			try {
				users = userDaoImpl.getUsersByProperty("login", login);
				if (users.size()==1)
					user = users.get(0);
				if ((user != null)) {	
					if (validatePasswords(password, user)) {
						request.setAttribute("firstname", user.getFirstName());
						request.setAttribute("lastname", user.getLastName());
						request.setAttribute("access_wrong", false);
						user.setStatus(UserStatus.AVAILABLE);
						userDaoImpl.updateUser(user);
						getServletContext().getRequestDispatcher(SUCCESS_LOGIN).forward(request, response);
						return;
					} else {
						request.setAttribute("access_wrong", true);
						getServletContext().getRequestDispatcher(RELOGIN).forward(request, response);
						return;
					}
				}else{
					getServletContext().getRequestDispatcher(RELOGIN).forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			getServletContext().getRequestDispatcher(ERROR).forward(request,response);
			return;
		}
	}

	private boolean validatePasswords(String password, User user)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		return PasswordHash.validatePassword(password, user.getPassword());
	}
}
