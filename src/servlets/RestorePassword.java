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

@WebServlet("/restorePassword")
public class RestorePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		request.setAttribute("email", email);

		System.out.println("RestoringPassword servlet working...");
		if (!EmailValidate.validateEmail(email)) {
			request.setAttribute("email", null);
			getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
			return;
		}
		if ((login == "") || (login == null)){
			getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
			return;
		}
		User newUser = null;
		String restoreCode = String.valueOf(ServletUtil.getRandomCode());
		try {
			newUser = userDao.getUserByProperty("login", login);
			if (newUser.getEmail().equals(email)){
				newUser.setRestoreCode(restoreCode);
				userDao.updateUser(newUser);	
			}else{
				getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/restorePassword.jsp").forward(request, response);
			return;
		}
		String[] recipients = { email };
		String subject = "Восстановление пароля";
		String info = this.getServletContext().getContextPath();
		String text = "Вы зарегистрированиы на сайте "+info
				+ "Для восстановления пароля пройдите по ссылке "
				+"http://"+ ServletUtil.HOST + ":" + request.getServerPort()
				+ info+"/successRestore?login=" + newUser.getLogin()
				+ "&restoreCode=" + restoreCode;
		ServletUtil.sendMessage(recipients, subject, text);
		/*
		 * TODO  java.lang.IllegalStateException: Cannot forward after response has been committed
		 */
		request.getServletContext().getRequestDispatcher("/").forward(request, response);
	}
      
}
