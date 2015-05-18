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

@WebServlet("/successRestore")
public class SuccessRestore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();
       
    public SuccessRestore() {
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String login = (String) request.getParameter("login");
		String newRestoreCode = (String) request.getParameter("restoreCode");
		System.out.println("SuccessRestore servlet working...");
		User user = null;
		if ((newRestoreCode != null) && (newRestoreCode != "")&&
				(login!= null) && (login != "")) {
			String savedRestoreCode = null;
			try {
				user = userDao .getUserByProperty("login", login);
				if (user != null) {
					savedRestoreCode = user.getRestoreCode();
					request.setAttribute("firstname", user.getFirstName());
					request.setAttribute("lastname", user.getLastName());
					user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
					userDao.updateUser(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
			if ((savedRestoreCode != null) && (newRestoreCode.equals(savedRestoreCode))) {
				System.out.println("OK!");
				request.getRequestDispatcher("/successLogin.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
		}else{
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
	}
    
}
