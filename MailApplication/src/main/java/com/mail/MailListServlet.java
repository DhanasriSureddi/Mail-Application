package com.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MailListServlet")
public class MailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Dhana",
					"postgres", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select mail from Maildata");

			ArrayList<String> items = new ArrayList<String>();
			while (rs.next()) {
				String code = rs.getString("mail");
				// String name = rs.getString("stn_name");

				items.add(code);

			}
			JSONArray jsonArray = new JSONArray();
			for (String item : items) {
				jsonArray.add(item);
			}
			String json = jsonArray.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out  = response.getWriter();
			out.print(json);

			cn.close();

		} catch (ClassNotFoundException |

				SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
