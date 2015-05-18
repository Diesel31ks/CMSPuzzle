package servlets;

import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SuccessRegistration")
public class SuccessRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SuccessRegistration() {
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String newConfirmCode = (String) request.getParameter("confirmationCode");
		if ((newConfirmCode != null) && (newConfirmCode != "")) {
			String oldConfirmCode = null;
			try {
				User user = HibernateFactory.getInstance().getUserDao().getUser(2);
				oldConfirmCode = user.getConfirmCode();
				request.setAttribute("firstname", user.getFirstName());
				request.setAttribute("lastname", user.getLastName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (newConfirmCode.equals(oldConfirmCode)) {
				System.out.println("OK!!!!!!");
				request.getRequestDispatcher("/successLogin.jsp").forward(
						request, response);
			} else
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
		}
	}
}
