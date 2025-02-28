import java.sql.*;

public class MySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Replace with your database
        String user = "root"; // Replace with your MySQL username
        String password = "password"; // Replace with your MySQL password

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // SQL query to insert data
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "Maria");
            pstmt.setString(2, "maria@example.com");

            // Execute query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }

            // Closing connection
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
