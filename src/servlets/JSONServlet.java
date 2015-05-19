package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/JSONServlet")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 2666565230603548512L;
	JSONParser parser = new JSONParser();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:14905/CMSPuzzle-1_sasha/JSONServlet?json={"k":"2"}
		// String s = "{\"k\":\"2\"}";
		String string = (String) request.getParameter("json");
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(string);
			String valueOfK = null;
			if (json.containsKey("k")) {
				valueOfK = (String) json.get("k");
				System.out.println("k=" + valueOfK);
			}

		} catch (ParseException pe) {
			System.out.println("position" + pe.getPosition());
			System.out.println(pe);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// put some value pairs into the JSON object .
		// json.put("Mobile", 999998888);
		// json.put("Name", "ManojSarnaik");

		// finally output the json string
		System.out.println(json.toJSONString());
		out.flush();
		out.print(json.toJSONString());

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
