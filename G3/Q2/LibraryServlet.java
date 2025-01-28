import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String ISBN = request.getParameter("ISBN");
            String copiesStr = request.getParameter("copies");
            int copies = (copiesStr != null) ? Integer.parseInt(copiesStr) : 0;

            if (title != null && author != null && ISBN != null && copies > 0) {
                Book book = new Book(0,title, author, ISBN, copies);
                saveBookToDatabase(book);
//                response.sendRedirect("viewBooks.jsp");
                response.sendRedirect("LibraryServlet?action=view");

            } else {
                response.getWriter().write("Invalid input data.");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action received: " + action);

        if ("view".equals(action)) {
            List<Book> books = getAllBooksFromDatabase();
            System.out.println("Books fetched: " + (books != null ? books.size() : "null"));
            request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewBooks.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void saveBookToDatabase(Book book) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            System.out.println("Connected to the database!");
            String query = "INSERT INTO Books (title, author, ISBN, copies) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, book.getISBN());
                stmt.setInt(4, book.getCopies());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Book added successfully!");
                } else {
                    System.out.println("Failed to add book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Book> getAllBooksFromDatabase() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
            String query = "SELECT * FROM Books";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("ISBN"),
                            rs.getInt("copies")
                    );
//                    System.out.println("Fetched Book: " + book.getTitle());
                    System.out.println("Fetched Book: " + rs.getString("title"));

                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (books.isEmpty()) {
            System.out.println("No books found in the database.");
        } else {
            System.out.println("Books size: " + (books != null ? books.size() : "null"));
//            System.out.println("Books retrieved from the database: " + books.size());
        }
        return books;
    }
}
