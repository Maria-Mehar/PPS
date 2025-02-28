import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String url = "jdbc:mysql://localhost:3306/maria";
        String user = "root";
        String password = "";


        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");

            // Insert form data into database
            String query = "INSERT INTO first_table (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully!");

            // Retrieve the inserted data
            String selectQuery = "SELECT name, email FROM first_table WHERE name = ? AND email = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setString(1, name);
            selectStmt.setString(2, email);
            ResultSet rs = selectStmt.executeQuery();

            // Display the inserted data on the screen
            response.getWriter().println("<h2>Data Inserted Successfully!</h2>");
            while (rs.next()) {
                response.getWriter().println("<p>Name: " + rs.getString("name") + "</p>");
                response.getWriter().println("<p>Email: " + rs.getString("email") + "</p>");
            }

            // Close resources
            rs.close();
            selectStmt.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
