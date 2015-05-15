package servlets;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import hibernate.tables.userInfo.UserStatus;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authorization.EmailValidate;
import authorization.PasswordHash;
import authorization.SendMessage;

@WebServlet(description = "to register a new user", urlPatterns = { "/registration" })
public class Registration extends HttpServlet {
	private final static String HOST = "10.9.2.159";
	private static final long serialVersionUID = 1L;
	HibernateFactory hibernateFactory = HibernateFactory.getInstance();
	UserDao userDao = hibernateFactory.getUserDao();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password_confirmation");
		UserRole role = UserRole.UNCONFIRMED;
		UserStatus status = UserStatus.AVAILABLE;
		System.out.println("Registration servlet working...");

		request.setAttribute("firstname", firstName);
		request.setAttribute("lastname", lastName);
		request.setAttribute("email", email);

		if (!EmailValidate.validateEmail(email)) {
			request.setAttribute("email", null);
			getServletContext().getRequestDispatcher("/registration.jsp")
					.forward(request, response);
			return;
		}

		if ((login == "") || (login == null) || (password == "")
				|| (password == null) || (firstName == "")
				|| (firstName == null) || (lastName == "")
				|| (lastName == null) || (email == null)
				|| (!password.equals(passwordConfirm))
				|| (password.length() > 100) || (password.length() < 8)
				|| (password.contains(" ")) || (password.contains("/r"))
				|| (password.contains("/n"))) {
			// request.setAttribute("passwords_not_equal", Boolean.TRUE);
			// response.sendRedirect(request.getContextPath()
			// +"/registration.jsp");
			getServletContext().getRequestDispatcher("/registration.jsp")
					.forward(request, response);
			return;
		}
		// request.setAttribute("passwords_not_equal", Boolean.FALSE);
		try {
			List<User> users = userDao.getUsers();
			for (User user : users) {
				if (user.getLogin().equals(login)) {
					// request.setAttribute("login_exists", Boolean.TRUE);
					// response.sendRedirect(request.getContextPath()
					// +"/registration.jsp");
					getServletContext().getRequestDispatcher(
							"/registration.jsp").forward(request, response);
					return;
				}
			}
			// request.setAttribute("login_exists", Boolean.FALSE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setLogin(login);
		newUser.setEmail(email);
		try {
			String hash = PasswordHash.createHash(password);
			newUser.setPassword(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			e1.printStackTrace();
			getServletContext().getRequestDispatcher("/registration.jsp")
					.forward(request, response);
			return;
		}
		newUser.setRole(role);
		newUser.setStatus(status);

		try {
			userDao.addUser(newUser);
			System.out.println("New user was saved in database");
			// getServletContext().getRequestDispatcher("/successLogin.jsp").forward(request,
			// response);
			String[] recipients = { email };
			String subject = "Подтверждение регистрации	";
			
			String info = this.getServletContext().getContextPath();
			
			String text = "Вы зарегистрированиы на сайте CMSPuzzle-1.0-SNAPSHOT <br>"
					+ "Для потверждения регистрации пройдите по ссылке "
					+"http://"+ HOST + ":" + request.getServerPort()+ info+"/successLogin.jsp?confirmationCode=3";
			try {
				new SendMessage().sendMessage(recipients, subject, text);
				System.out.println("Message was sent");
			} catch (MessagingException |SecurityException e) {
				System.out.println("Message was not sent");
				e.printStackTrace();
			} finally {
				getServletContext().getRequestDispatcher("/").forward(request,
						response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/error.jsp").forward(
					request, response);
		}
	}
}
