package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;

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

@WebServlet(name = "successRestore", urlPatterns = { "/successRestore" })
public class SuccessRestore extends HttpServlet {
	private static final String SUCCESS_RESTORING = "/successRestoring.jsp";
	private static final String ERROR = "/error.jsp";
	private static final long serialVersionUID = -8613898861506667048L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();

	public SuccessRestore() {
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("password");
		String confirmPassword = (String) request
				.getParameter("password_confirmation");
		String login = (String) request.getParameter("login");
		System.out.println("SuccessRestore servlet working...");
		if ((password != null) && (password != "") && (confirmPassword != null)
				&& (confirmPassword != "") && (login != null) && (login != "")
				&& (!password.equals(confirmPassword))) {
			List<User> users = null;
			try {
				users = userDao.getUsersByProperty("login", login);
				if ((users != null) && (!users.isEmpty())
						&& (users.size() == 1)) {
					User user = users.get(0);
					user.setPassword(PasswordHash.createHash(password));
					user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
					userDao.updateUser(user);
					System.out.println("Password updated successful!");
					request.getRequestDispatcher(SUCCESS_RESTORING).forward(request, response);
					return;
				} else {
					request.getRequestDispatcher(ERROR).forward(request, response);
					return;
				}
			} catch (SQLException | NoSuchAlgorithmException
					| InvalidKeySpecException e) {
				e.printStackTrace();
				request.getRequestDispatcher(ERROR).forward(request,
						response);
				return;
			}
		} else {
			request.getRequestDispatcher(ERROR).forward(request,
					response);
			return;
		}
	}
}
