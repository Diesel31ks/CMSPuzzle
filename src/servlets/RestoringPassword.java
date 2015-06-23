package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Alexandr Khromov
 */
//@WebServlet("/restoringPassword")
public class RestoringPassword extends HttpServlet {
	private static final String ERROR = "/error.jsp";
	private static final long serialVersionUID = 8938715813324878733L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();
       
    public RestoringPassword() {
    }
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String login = (String) request.getParameter("login");
		String newRestoreCode = (String) request.getParameter("restoreCode");
		System.out.println("RestoringPassword servlet working...");
		User user = null;
		if ((newRestoreCode != null) && (newRestoreCode != "")&&(login!= null) && (login != "")) {
			String savedRestoreCode = null;
			try {
				if (userDao.getUsersByProperty("login", login).size()>1){
					request.getRequestDispatcher(ERROR).forward(request, response);
					return;
				}
				user = userDao.getUsersByProperty("login", login).get(0);
				if (user != null) {
					savedRestoreCode = user.getRestoreCode();
					request.setAttribute("firstname", user.getFirstName());
					request.setAttribute("lastname", user.getLastName());
					user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
					userDao.updateUser(user);
				}else{
					request.getRequestDispatcher(ERROR).forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher(ERROR).forward(request, response);
				return;
			}
			if ((savedRestoreCode != null) && (newRestoreCode.equals(savedRestoreCode))) {
				System.out.println("OK!");
				request.setAttribute("login", login);
    			request.getRequestDispatcher("/restorePasswordForm.jsp").forward(request, response);
				return;
			} else
				request.getRequestDispatcher(ERROR).forward(request, response);
				return;
		}else{
			request.getRequestDispatcher(ERROR).forward(request, response);
			return;
		}
		
	}
    
}
