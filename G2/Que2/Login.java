import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginID = request.getParameter("loginID");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/University";
            String dbUser  = "root";
            String dbPassword = "";
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM login_Info WHERE loginID = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginID);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("msg", "Login successful!");
                request.setAttribute("loginID", loginID);
                request.setAttribute("password", password);
                request.getRequestDispatcher("/Success").forward(request, response);
            } else {
                request.setAttribute("msg", "Invalid login credentials!");
                request.setAttribute("loginID", loginID);
                request.getRequestDispatcher("/Error").forward(request, response);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "An error occurred.");
            request.getRequestDispatcher("/Error").forward(request, response);
        }
    }
}
