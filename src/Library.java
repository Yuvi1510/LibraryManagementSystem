import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    static List<Book> books = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)  {
        try {
            Connection connection = DatabaseConnection.connect();

            Book book = BookMethods.getBookByBookNumber(connection,105);
            System.out.println("book id: " + book.getId());
            System.out.println("book number: "+book.getBookNumber());
            System.out.println("book name: "+ book.getName());
            System.out.println("book quantity: "+book.getQuantity());
            System.out.println("book author: "+book.getAuthor());
            System.out.println("_________________________________________________________");
            System.out.println("_________________________________________________________");

//            addUser(connection,input);
            borrowBook(connection,input);

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void borrowBook(Connection connection, Scanner input){
        System.out.println("Enter book number: ");
        int bookNumber = input.nextInt();
        input.nextLine();
        System.out.println("Enter username: ");
        String username = input.nextLine();

        try{
            // get book by book number
        Book book = BookMethods.getBookByBookNumber(connection, bookNumber);
            System.out.println("Got the book");
            // get user by their username
       User user = UserMethods.getUserByUsername(connection, username);
            System.out.println("Got the user");

            // check if the book  is available
        if(book.getQuantity() > 0){
            // check if the user has already borrowed two books
            if(user.getBorrowCount()<=2){
                // if the book is available and user has not borrowed two books the
                // allow to borrow
                // insert the record
                RecordMethods.insertRecord(connection, user.getId(), book.getId());

                // update the quantity of book
                BookMethods.updateBookQuantity(connection, bookNumber, book.getQuantity() - 1);

                //update the user by increasing its borrow count by 1
                user.setBorrowCount(user.getBorrowCount() + 1);
                if(UserMethods.updateUser(connection,user)){
                    System.out.println("Thank You for borrowing! Please return it on time.");
                }else {
                    System.out.println("Something went wrong!");
                }
            }else{
                System.out.println("Sorry, your borrowing limit has reached! (Borrowing limit = 2)");
            }
        }else {
            System.out.println("Book is not available right now");
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // add user
    public static void addUser(Connection connection, Scanner input){
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter contact: ");
        String contact = input.nextLine();

        User user = new User(username, contact);

        try{
            UserMethods.insertUser(connection, user);
        }catch (SQLException e){
            System.out.println(e.getMessage());
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
    }catch (SQLException e){
        System.out.println(e.getMessage());
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
//
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