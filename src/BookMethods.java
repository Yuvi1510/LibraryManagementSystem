import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void updateBookQuantity(Connection connection, int bookId, int quantity) throws SQLException {
        String updateQuantity = "UPDATE books SET quantity=? WHERE book_id=?;";
        PreparedStatement ps = connection.prepareStatement(updateQuantity);
        ps.setInt(1,quantity);
        ps.setInt(2, bookId);

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("Book quantity updated");
        }else{
            System.out.println("Failed to update book");
        }

    }

    // update book
    // update user
    public static boolean updateBookDetails(Connection connection, Book book) throws SQLException {
        String query = "UPDATE books SET book_number=?, name=?, quantity=?, author=? WHERE book_id=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, book.getBookNumber());
        ps.setString(2,book.getName());
        ps.setInt(3, book.getQuantity());
        ps.setString(4,book.getAuthor());
        ps.setInt(5, book.getId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected > 0;
    }


    // update book
    public static void updateBook(Connection connection, Scanner input){
        System.out.println("Enter book number of book you want to update");
        int bookNum = input.nextInt();
        input.nextLine();

        try{
            Book book = BookMethods.getBookByBookNumber(connection, bookNum);

            // ask user if they want to update this attribute
            System.out.println("Update book number?(y/n)");
            if(input.nextLine().equalsIgnoreCase("y")){
                System.out.println("Enter new book number: ");
                int newBookNumber = input.nextInt();
                input.nextLine();
                book.setBookNumber(newBookNumber);
            }

            System.out.println("Update book name?(y/n)");
            if(input.nextLine().equalsIgnoreCase("y")){
                System.out.println("Enter new name: ");
                String newName = input.nextLine();
                book.setName(newName);
            }

            System.out.println("Update book quantity?(y/n)");
            if(input.nextLine().equalsIgnoreCase("y")){
                System.out.println("Enter new quantity: ");
                int newQuantity = input.nextInt();
                input.nextLine();
                book.setQuantity(newQuantity);
            }

            System.out.println("Update book author?(y/n)");
            if(input.nextLine().equalsIgnoreCase("y")){
                System.out.println("Enter new author name: ");
                String newAuthorName = input.nextLine();
                book.setAuthor(newAuthorName);
            }

            // pass the book object with updated attributes to the update method
            if(BookMethods.updateBookDetails(connection, book)){
                System.out.println("Book updated successfully!");
            }else{
                System.out.println("Failed to update book!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    add book
    public static void addBook(Connection connection, Scanner input){
        // Book book10 = new Book(110, "The Subtle Art of Not Giving a F*ck", 55, "Mark Manson");
        System.out.println("Enter book number: ");
        int bookNumber = input.nextInt();
        input.nextLine();
        System.out.println("Enter book name: ");
        String name = input.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = input.nextInt();
        input.nextLine();
        System.out.println("Enter author name: ");
        String author = input.nextLine();

        Book book = new Book(bookNumber, name, quantity,author);
        try{
            BookMethods.insertBooks(connection,book);
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }




    public  static  void showBooks(Connection connection) throws SQLException {
        List<Book> books = BookMethods.selectBooks(connection);
        for(Book book: books){
            System.out.println(book.getBookNumber()+" - " + book.getName() + " by "+book.getAuthor());
        }
    }

    // delete book
    public static void deleteBook(Connection connection, Scanner input) throws SQLException {
        System.out.println("Enter book number: ");
        int bookNumber = input.nextInt();
        input.nextLine();
        String query = "DELETE FROM books WHERE book_number=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookNumber);

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("Book deleted successfully");
        }else {
            System.out.println("Failed to delete book!");
        }
    }

}
