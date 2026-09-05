package org.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/display")
public class display extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to user_db
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/Student",
                    "root",
                    "Munna@3307"
            );

            // Create statement
            Statement st = con.createStatement();

            // Get records from student table
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            // Tell browser we are sending HTML
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Marks</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("marks") + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}