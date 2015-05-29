package servlets;

import hibernate.dao.ContentDao;
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
		}

//		String string = (String) request.getParameter("json");
//		JSONObject json = null;
//		try {
//			json = (JSONObject) parser.parse(string);
//			String valueOfK = null;
//			if (json.containsKey("k")) {
//				valueOfK = (String) json.get("k");
//				System.out.println("k=" + valueOfK);
//			}
//
//		} catch (ParseException pe) {
//			System.out.println("position" + pe.getPosition());
//			System.out.println(pe);
//		}

		// finally output the json string
//		System.out.println(json.toJSONString());
//		out.flush();
//		out.print(json.toJSONString());

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
