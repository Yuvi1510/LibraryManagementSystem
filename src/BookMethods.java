import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMethods {
    // get book by book number
    public static Book getBookByBookNumber(Connection connection, int bookNumber) throws SQLException {
        String query = "SELECT * FROM books WHERE book_number=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookNumber);
        ResultSet rs = ps.executeQuery();

        Book book=null;

        while (rs.next()){
            int id = rs.getInt("book_id");
            int bookNo = rs.getInt("book_number");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            String author = rs.getString("author");
            book = new Book(bookNo,name,quantity,author);
            book.setId(id);
        }
        return book;
    }

    // method to insert book
    public static void insertBooks(Connection connection, Book book) throws SQLException {
        String query = "INSERT INTO books( book_number, name, quantity, author) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, book.getBookNumber());
        ps.setString(2, book.getName());
        ps.setInt(3, book.getQuantity());
        ps.setString(4, book.getAuthor());

        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Book inserted successfully");
        }else{
            System.out.println("Failed to insert book");
        }
    }

    // Method to select book
    public static List<Book> selectBooks(Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        String selectBook = "SELECT * FROM books";
        PreparedStatement ps = connection.prepareStatement(selectBook);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int id = rs.getInt("book_id");
            int bookNumber = rs.getInt("book_number");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            String author = rs.getString("author");
            Book book = new Book(bookNumber, name, quantity, author);
            book.setId(id);
            books.add(book);
        }

        return books;
    }

    public static void updateBookQuantity(Connection connection, int bookNumber, int quantity) throws SQLException {
        String updateQuantity = "UPDATE books SET quantity=? WHERE book_number=?;";
        PreparedStatement ps = connection.prepareStatement(updateQuantity);
        ps.setInt(1,quantity);
        ps.setInt(2, bookNumber);

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("Book quantity updated");
        }else{
            System.out.println("Failed to update book");
        }

    }


}
