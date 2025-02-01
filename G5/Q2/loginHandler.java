import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginHandler")
public class loginHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String dbURL = "jdbc:mysql://localhost:3306/University?useSSL=false&serverTimezone=UTC";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
                String query = "SELECT * FROM user_Info WHERE username = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            response.sendRedirect("Success");
                        } else {
                            response.sendRedirect("Error");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("Database driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while connecting to the database.");
        }
    }
}
