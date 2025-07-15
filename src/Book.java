public class Book {
    private int id;
    private int bookNumber;
    private String name;
    private int quantity;
    private String author;

    public Book(int bookNumber, String name, int quantity, String author){
        this.bookNumber = bookNumber;
        this.name = name;
        this.quantity = quantity;
        this.author = author;
    }



    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // methods

    public void increaseQuantity(){

    }

    public void decreaseQuantity(){
        this.quantity -= 1;
    }
}
