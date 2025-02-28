import java.sql.*;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String fullName = request.getParameter("fullName");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Database Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/librarydb";
            String dbUser = "root";
            String dbPassword = "";
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Check if email already exists
            String checkQuery = "SELECT email FROM students WHERE email = ?";
            pstmt = conn.prepareStatement(checkQuery);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                out.println("<h3 style='color:red;'>Registration Failed. Email already exists.</h3>");
            } else {
                // Close the first PreparedStatement and ResultSet
                rs.close();
                pstmt.close();

                // Insert User Data
                String insertQuery = "INSERT INTO students (full_name, student_id, email, password) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, fullName);
                pstmt.setString(2, studentId);
                pstmt.setString(3, email);
                pstmt.setString(4, password);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    out.println("<h3 style='color:green;'>Registration Successful!</h3>");
                } else {
                    out.println("<h3 style='color:red;'>Registration Failed. Try Again!</h3>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Database Error. Try Again!</h3>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
