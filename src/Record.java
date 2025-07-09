import java.time.LocalDate;

public class Record {
    private Book book;
    private User user;
    private LocalDate date;
    private boolean returnStatus = false;

    public Record(Book book, User user){
        this.book = book;
        this.user = user;
        this.date = LocalDate.now();
    }

    // getter and setter
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
