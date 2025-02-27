import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/University";
            String dbUser  = "root";
            String dbPassword = "";
            Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            // Check if email exists
            PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM students WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                out.println("<h3 style='color:red;'>Registration failed. Email already exists!</h3>");
            } else {
                // Insert user data
                PreparedStatement insertStmt = con.prepareStatement("INSERT INTO students(fullname, email, password) VALUES (?, ?, ?)");
                insertStmt.setString(1, fullName);
                insertStmt.setString(2, email);
                insertStmt.setString(3, password);

                int result = insertStmt.executeUpdate();
                if (result > 0) {
                    out.println("<h3 style='color:green;'>Registration successful!</h3>");
                } else {
                    out.println("<h3 style='color:red;'>Registration failed. Try again!</h3>");
                }
            }
            con.close();
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(out);
        }
    }
}
