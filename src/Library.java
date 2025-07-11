import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    static List<Book> books = new ArrayList<>();

    public static void main(String[] args)  {
        try {
            Connection connection = DatabaseConnection.connect();

//            Book book1 = new Book(101, "Atomic Habits", 50, "James Clear");
//            insertBooks(connection, book1);
//              Book book2 = new Book(102, "Rich Dad Poor Dad", 45, "Robert Kiyosaki");
//            insertBooks(connection, book2);

//            User user1 = new User("Yuvraj", "gmail",0);
//            insertUser(connection, user1);


            List<Book> books = selectBooks(connection);
            for(Book book: books){
                System.out.println("book number: "+book.getBookNumber());
                System.out.println("book name: "+ book.getName());
                System.out.println("book quantity: "+book.getQuantity());
                System.out.println("book author: "+book.getAuthor());
                System.out.println("_________________________________________________________");
                System.out.println("_________________________________________________________");
            }

            List<User> users = selectUsers(connection);
            for(User user: users){
                System.out.println("username: "+ user.getUsername());
                System.out.println("contact: "+ user.getContact());
                System.out.println("borrow_count: "+ user.getBorrowCount());
                System.out.println("_________________________________________________________");
                System.out.println("_________________________________________________________");
            }

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public static List<User> selectUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        String selectBook = "SELECT * FROM users";
        PreparedStatement ps = connection.prepareStatement(selectBook);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String username = rs.getString("username");
            String contact = rs.getString("contact");
            int borrowCount = rs.getInt("borrow_count");
            User user = new User(username, contact,borrowCount);
            users.add(user);
        }

        return users;
    }


    public static List<Book> selectBooks(Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        String selectBook = "SELECT * FROM books";
        PreparedStatement ps = connection.prepareStatement(selectBook);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int bookNumber = rs.getInt("book_number");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            String author = rs.getString("author");
            Book book = new Book(bookNumber, name, quantity, author);
            books.add(book);
        }

        return books;
    }

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

    public static  void insertUser(Connection connection, User user) throws SQLException {

        String insertUserQuery = "INSERT INTO users(username,contact,borrow_count) VALUES(?,?,?);";
        PreparedStatement ps2 = connection.prepareStatement(insertUserQuery);
        ps2.setString(1,user.getUsername());
        ps2.setString(2, user.getContact());
        ps2.setInt(3, user.getBorrowCount());
        if(ps2.executeUpdate() > 0 ){
            System.out.println("User inserted successfully");
        }else{
            System.out.println("Failed to insert user");
        }
    }








//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        // create some books and users
//        // Book(int bookNumber, String name, int quantity, String author)
//        Book book1 = new Book(101, "Atomic Habits", 50, "James Clear");
////        books.add(book1);
////        Book book2 = new Book(102, "Rich Dad Poor Dad", 45, "Robert Kiyosaki");
////        books.add(book2);
////        Book book3 = new Book(103, "The Alchemist", 40, "Paulo Coelho");
////        books.add(book3);
////        Book book4 = new Book(104, "Think and Grow Rich", 55, "Napoleon Hill");
////        books.add(book4);
////        Book book5 = new Book(105, "The Psychology of Money", 60, "Morgan Housel");
////        books.add(book5);
////        Book book6 = new Book(106, "The 7 Habits of Highly Effective People", 70, "Stephen Covey");
////        books.add(book6);
////        Book book7 = new Book(107, "Start With Why", 65, "Simon Sinek");
////        books.add(book7);
////        Book book8 = new Book(108, "Deep Work", 50, "Cal Newport");
////        books.add(book8);
////        Book book9 = new Book(109, "Ikigai", 35, "Francesc Miralles");
////        books.add(book9);
////        Book book10 = new Book(110, "The Subtle Art of Not Giving a F*ck", 55, "Mark Manson");
////        books.add(book10);
//
//        //create user
//        User user1 = new User("Yuvraj", "gmail");
//        // show available options - show books - borrow books - return books  - exit application
//        showMenu();
//       while (true){
//            System.out.println("Choose an options: ");
//           int option = input.nextInt();
//           input.nextLine();
//
//           if(option == 1){
//               showBooks();
//           } else if (option == 2) {
//               int bookNo = 108;
//               Book book = books.stream().filter((requiredBook)-> requiredBook.getBookNumber() == bookNo).findFirst().orElse(null);
//               user1.borrowBook(user1, book1);
//           } else if (option == 3) {
//               System.out.println("returning books");
//           } else if (option == 4) {
//               System.out.println("Exiting the application.....");
//               break;
//           }else {
//               System.out.println("Please enter a valid option");
//           }
//       }
//
//
//        // need to figure out where to keep the books
//
//    }

    public static void showMenu(){

        System.out.println("Welcome to the library");
        System.out.println("Enter 1: Show available books");
        System.out.println("Enter 2: Borrow books");
        System.out.println("Enter 3: Return books");
        System.out.println("Enter 4: Exit");
    }

    public  static  void showBooks(){
        for(Book book: books){
            System.out.println(book.getBookNumber()+" - " + book.getName() + " by "+book.getAuthor());
        }
    }
}