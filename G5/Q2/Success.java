import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Success extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Login Successful!</h1>");
        out.println("<p>Welcome to the University Portal.</p>");
    }
}
