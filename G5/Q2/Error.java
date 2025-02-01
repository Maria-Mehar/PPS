import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Error extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Login Failed</h1>");
        out.println("<p>Invalid username or password. Please try again.</p>");
    }
}
