package servlets;

import hibernate.dao.ContentDao;
import hibernate.general.Example;
import hibernate.general.HibernateFactory;
import hibernate.tables.Content;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/api/posts")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 2666565230603548512L;
	ContentDao contentDao = HibernateFactory.getInstance().getContentDao();
	private JSONParser parser = new JSONParser();

	/**
	 * http://localhost:14905/CMSPuzzle-1_sasha/JSONServlet?json={"k":"2"}
	 * String s = "{\"k\":\"2\"}";
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		Map<String, String[]> params = request.getParameterMap();
		// url /posts without parameters
		if (params.isEmpty() || params == null) {
			JSONObject json = new JSONObject();
			List<Content> contents = null;
			Map<Integer, Content> map = new LinkedHashMap<>();
			String [] args=null;
			try {
				Example.main(args);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				contents = contentDao.getContents();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if ((contents != null) && (!contents.isEmpty())) {
				for (Content content : contents) {
					content.setText(null);
					map.put(content.getId(), content);
				}
				json.putAll(map);
				json.toJSONString();
				System.out.println(json);
			}
			out.println("json = "+json);
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
