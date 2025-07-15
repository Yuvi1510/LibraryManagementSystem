import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.connect();

            while (true) {
                showMenu();
                System.out.println("Choose an options: ");
                int option = input.nextInt();
                input.nextLine();
                System.out.println("Welcome to the library");
                System.out.println("Enter 1: Show available books");
                System.out.println("Enter 2: Borrow books");
                System.out.println("Enter 3: Return books");
                System.out.println("Enter 4: update books");
                System.out.println("Enter 5: add books");
                System.out.println("Enter 6: delete book");
                System.out.println("Enter 7: add user");
                System.out.println("Enter 8: delete user");
                System.out.println("Enter 9: Exit");

                if (option == 1) {
                    BookMethods.showBooks(connection);
                } else if (option == 2) {
                    borrowBook(connection, input);
                } else if (option == 3) {
                    returnBook(connection, input);
                } else if (option == 4) {
                    BookMethods.addBook(connection, input);
                } else if (option == 5) {
                    BookMethods.updateBook(connection, input);
                } else if (option == 6) {
                    BookMethods.deleteBook(connection, input);
                } else if (option == 7) {
                    UserMethods.addUser(connection, input);
                } else if (option == 8) {
                    UserMethods.deleteUser(connection, input);
                } else {
                    System.out.println("Please enter a valid option");
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void showMenu() {

        System.out.println("Welcome to the library");
        System.out.println("Enter 1: Show available books");
        System.out.println("Enter 2: Borrow books");
        System.out.println("Enter 3: Return books");
        System.out.println("Enter 4: add books");
        System.out.println("Enter 5: update books");
        System.out.println("Enter 6: delete book");
        System.out.println("Enter 7: add user");
        System.out.println("Enter 8: delete user");
        System.out.println("Enter 9: Exit");
    }


    public static void returnBook(Connection connection, Scanner input) {
        System.out.println("Enter book number: ");
        int bookNumber = input.nextInt();
        input.nextLine();
        System.out.println("Enter username: ");
        String username = input.nextLine();

        try {
            // get book by book number
            Book book = BookMethods.getBookByBookNumber(connection, bookNumber);
            System.out.println("Got the book");
            // get user by their username
            User user = UserMethods.getUserByUsername(connection, username);
            System.out.println("Got the user");

            // update the return status
            RecordMethods.updateReturnStatus(connection, user.getId(), book.getId());
            // decrease the borrow count of user by 1
            user.setBorrowCount(user.getBorrowCount() - 1);
            // update the user with new borrow count
            UserMethods.updateUser(connection, user);

//            update the book quantity
            BookMethods.updateBookQuantity(connection, book.getId(), book.getId() + 1);
            System.out.println("Thank You for returnint the book!");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrowBook(Connection connection, Scanner input) {
        System.out.println("Enter book number: ");
        int bookNumber = input.nextInt();
        input.nextLine();
        System.out.println("Enter username: ");
        String username = input.nextLine();

        try {
            // get book by book number
            Book book = BookMethods.getBookByBookNumber(connection, bookNumber);
            System.out.println("Got the book");
            // get user by their username
            User user = UserMethods.getUserByUsername(connection, username);
            System.out.println("Got the user");

            // check if the book  is available
            if (book.getQuantity() > 0) {
                // check if the user has already borrowed two books
                System.out.println(user.getBorrowCount());
                if (user.getBorrowCount() < 2) {
                    // if the book is available and user has not borrowed two books the
                    // allow to borrow
                    // insert the record
                    RecordMethods.insertRecord(connection, user.getId(), book.getId());

                    // update the quantity of book
                    BookMethods.updateBookQuantity(connection, bookNumber, book.getQuantity() - 1);

                    //update the user by increasing its borrow count by 1
                    user.setBorrowCount(user.getBorrowCount() + 1);
                    if (UserMethods.updateUser(connection, user)) {
                        System.out.println("Thank You for borrowing! Please return it on time.");
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println("--------------------------------------------------------------------------");
                    } else {
                        System.out.println("Something went wrong!");
                    }
                } else {
                    System.out.println("Sorry, your borrowing limit has reached! (Borrowing limit = 2)");
                }
            } else {
                System.out.println("Book is not available right now");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}



