package cdac.com.exam;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nm");
        String email = request.getParameter("em");
        String password = request.getParameter("pw");
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // loading driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "newuser", "cdac2023"); // url, username & password
            Statement st = conn.createStatement(); // Statement object used for firing SQL statements
            String sql = "UPDATE custom SET email='" + email + "', password='" + password + "' WHERE name='" + name + "'";
            System.out.println(sql);
            st.executeUpdate(sql); // DML
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        PrintWriter out = response.getWriter();
        out.write("<html><body>");
        out.write("<h1>Record updated successfully!</h1>");
        out.write("</body></html>");
    }
}

