package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/successLogin")
public class SuccessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = HibernateFactory.getInstance().getUserDao();

	public SuccessLogin() {
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String newConfirmCode = (String) request.getParameter("confirmationCode");
		String login = (String) request.getParameter("login");
		User user = null;
		if ((newConfirmCode != null) && (newConfirmCode != "")&&
				(login!= null) && (login != "")) {
			String savedConfirmCode = null;
			try {
				user = userDao.getUserByProperty("login", login);
				if (user != null) {
					savedConfirmCode = user.getConfirmCode();
					request.setAttribute("firstname", user.getFirstName());
					request.setAttribute("lastname", user.getLastName());
					user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
					userDao.updateUser(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if ((savedConfirmCode!=null) && (newConfirmCode.equals(savedConfirmCode))) {
				System.out.println("OK!");
				request.getRequestDispatcher("/successLogin.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
