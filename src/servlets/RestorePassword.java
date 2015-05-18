package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authorization.EmailValidate;

@WebServlet("/RestorePassword")
public class RestorePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password_confirmation");
		request.setAttribute("email", email);

		System.out.println("RestoringPassword servlet working...");
		if (!EmailValidate.validateEmail(email)) {
			request.setAttribute("email", null);
			getServletContext().getRequestDispatcher("/restorePassword.jsp")
					.forward(request, response);
			return;
		}
		
		if ((login == "") || (login == null) || (password == "")
				|| (password == null) || (!password.equals(passwordConfirm))
				|| (password.length() > 100) || (password.length() < 8)
				|| (password.contains(" ")) || (password.contains("/r"))
				|| (password.contains("/n"))) {
			getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
			return;
		}
		checkingEmailExists(request, response, email);

		User newUser = null;
		String restoreCode = String.valueOf(ServletUtil.getRandomCode());
		try {
			newUser = userDao.getUserByProperty("login", login);
			newUser.setRestoreCode(restoreCode);
			userDao.updateUser(newUser);
		} catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
		}
		String[] recipients = { email };
		String subject = "Восстановление пароля";
		String info = this.getServletContext().getContextPath();
		String text = "Вы зарегистрированиы на сайте "+info
				+ "Для восстановления пароля пройдите по ссылке "
				+"http://"+ ServletUtil.HOST + ":" + request.getServerPort()
				+ info+"/SuccessRegistration?login=" + newUser.getLogin()
				+ "&restoreCode=" + restoreCode;
		ServletUtil.sendMessage(request, response, recipients, subject, text);
	}


	
	private void checkingEmailExists(HttpServletRequest request, HttpServletResponse response, String email)
			throws ServletException, IOException {
		try {
			List<User> users = userDao.getUsers();
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					getServletContext().getRequestDispatcher(
							"/restorePassword.jsp").forward(request, response);
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
       
}
