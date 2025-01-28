<html>
<body>
    <h1>Add New Book</h1>
    <form action="LibraryServlet" method="POST">
        <input type="hidden" name="action" value="add" />
        Title: <input type="text" name="title" required /><br>
        Author: <input type="text" name="author" required /><br>
        ISBN: <input type="text" name="ISBN" required /><br>
        Copies: <input type="number" name="copies" required /><br>
        <input type="submit" value="Add Book" />
    </form>
</body>
</html>
