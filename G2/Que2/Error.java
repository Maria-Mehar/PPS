import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Error")
public class Error extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String msg = (String) request.getAttribute("msg");
        String loginID = (String) request.getAttribute("loginID");

        out.println("<h2>Login Failed</h2>");
        out.println("<p>Message: " + msg + "</p>");
        out.println("<p>Login ID: " + loginID + "</p>");
        out.println("<a href='login.html'>Go back to Login Page</a>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
