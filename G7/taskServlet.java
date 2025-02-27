import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/@WebServlet")
public class taskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskName = request.getParameter("taskName");
        String taskDescription = request.getParameter("taskDescription");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/task_management";
            String dbUser = "root";
            String dbPassword = "";
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String query = "INSERT INTO tasks (name, description) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, taskName);
            stmt.setString(2, taskDescription);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            response.sendRedirect("viewTasks.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
