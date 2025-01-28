public class Book {
    private int id;  // Change from "ID" to "id"
    private String title;
    private String author;
    private String ISBN;
    private int copies;

    public Book(int id, String title, String author, String ISBN, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.copies = copies;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }
}
