<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Tasks</title>
</head>
<body>
    <h1>All Tasks</h1>

    <table border="1">
        <tr>
            <th>Task Name</th>
            <th>Task Description</th>
        </tr>

        <%
            try {
                // Ensure your MySQL Driver is correct and properly imported
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_management", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
                boolean hasData = false; // Track if we have rows
                while (rs.next()) {
                    hasData = true; // We found at least one row
        %>
                    <tr>
                        <td><%= rs.getString("name") %></td>
                        <td><%= rs.getString("description") %></td>
                    </tr>
        <%
                }

                if (!hasData) {
        %>
                    <tr>
                        <td colspan="2">No tasks found!</td>
                    </tr>
        <%
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
        %>
                <p>Error: <%= e.getMessage() %></p>
        <%
            }
        %>
    </table>

    <br>
    <button onclick="window.location.href='index.html';">Back to Home</button>

</body>
</html>
