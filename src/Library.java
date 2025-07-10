import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    static List<Book> books = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // create some books and users
        // Book(int bookNumber, String name, int quantity, String author)
        Book book1 = new Book(101, "Atomic Habits", 50, "James Clear");
        books.add(book1);
        Book book2 = new Book(102, "Rich Dad Poor Dad", 45, "Robert Kiyosaki");
        books.add(book2);
        Book book3 = new Book(103, "The Alchemist", 40, "Paulo Coelho");
        books.add(book3);
        Book book4 = new Book(104, "Think and Grow Rich", 55, "Napoleon Hill");
        books.add(book4);
        Book book5 = new Book(105, "The Psychology of Money", 60, "Morgan Housel");
        books.add(book5);
        Book book6 = new Book(106, "The 7 Habits of Highly Effective People", 70, "Stephen Covey");
        books.add(book6);
        Book book7 = new Book(107, "Start With Why", 65, "Simon Sinek");
        books.add(book7);
        Book book8 = new Book(108, "Deep Work", 50, "Cal Newport");
        books.add(book8);
        Book book9 = new Book(109, "Ikigai", 35, "Francesc Miralles");
        books.add(book9);
        Book book10 = new Book(110, "The Subtle Art of Not Giving a F*ck", 55, "Mark Manson");
        books.add(book10);

        //create user
        User user1 = new User("Yuvraj", "gmail");
        // show available options - show books - borrow books - return books  - exit application
        showMenu();
       while (true){
            System.out.println("Choose an options: ");
           int option = input.nextInt();
           input.nextLine();

           if(option == 1){
               showBooks();
           } else if (option == 2) {
               int bookNo = 108;
               Book book = books.stream().filter((requiredBook)-> requiredBook.getBookNumber() == bookNo).findFirst().orElse(null);
               user1.borrowBook(user1, book1);
           } else if (option == 3) {
               System.out.println("returning books");
           } else if (option == 4) {
               System.out.println("Exiting the application.....");
               break;
           }else {
               System.out.println("Please enter a valid option");
           }
       }


        // need to figure out where to keep the books

    }

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