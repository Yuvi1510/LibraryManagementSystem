public class User {
    private String name;
    private String username;
    private String contact;
    private int borrowCount = 0;

    public User(String name, String username, String contact){
        this.name = name;
        this.username = username;
        this.contact = contact;
    }


    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    //  Methods
    public void isRegistration(){

    }

    public void borrowBook(User user, Book book){
        if(book.getQuantity() >0){
            Record recor = new Record(book, user);
            book.decreaseQuantity();
        }else{
            System.out.println("Book is not available");
        }
    }

    public void returnBook(){

    }
}
