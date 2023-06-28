package cdac.com.exam;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	@WebServlet("/ReadServlet")
	public class ReadServlet extends HttpServlet {
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "newuser", "cdac2023");
	            Statement st = conn.createStatement();
	            String sql = "SELECT * FROM custom";
	            ResultSet rs = st.executeQuery(sql);
	            
	            PrintWriter out = response.getWriter();
	            out.write("<html><body>");
	            out.write("<h1>Records</h1>");

	            out.write("<table border='1'>");
	            out.write("<tr><th>ID</th><th>Name</th><th>Email</th><th>Password</th></tr>");
	            
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String password = rs.getString("password");
	                
	                out.write("<tr><td>" + id + "</td><td>" + name + "</td><td>" + email + "</td><td>" + password + "</td></tr>");
	            }
	            
	            out.write("</table>");
	            out.write("</body></html>");
	            
	            rs.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
