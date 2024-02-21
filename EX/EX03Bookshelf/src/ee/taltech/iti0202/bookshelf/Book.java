package ee.taltech.iti0202.bookshelf;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.jfr.internal.EventWriterKey.getKey;

public class Book {
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private static int id;
    private int ID;
    private boolean createdWithOF;
    public Person owner;
    static HashMap<Person, Book> bookInfo = new HashMap<>();
    public static int getAndIncrementNextId() {
        id++;
        return id - 1;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.owner = null;
        this.ID = getAndIncrementNextId();
        this.createdWithOF = false;
        bookInfo.put(owner, this);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {return yearOfPublishing;}

    public Person getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return ID;
    }

    public boolean buy(Person buyer) {
        this.getOwner().sellBook(this);
        buyer.buyBook(this);
        if (buyer == null) {
            buyer.money += getPrice();
        }
        return false;
    }

    public void setCreatedWithOF(boolean createdWithOf) {
        this.createdWithOF = createdWithOf;
    }

    public Book of(String title, String author, int yearOfPublishing, int price, ) {
        for (Map.Entry<Person, Book> entry : bookInfo.entrySet()) {
            Book book = entry.getValue();
            if (book.createdWithOF == true) {
                if (book.getAuthor() == author && book.getTitle() == title && book.getYearOfPublishing() == yearOfPublishing) {
                    return book;
                }
            }
        }
        Book book = new Book(title, author, yearOfPublishing, price);
        book.setCreatedWithOF(true);
        return book;
    }

    public static Book of(String title, int price) {
        for (Map.Entry<Person, Book> entry : bookInfo.entrySet()) {
            Book book = entry.getValue();
            if (book.createdWithOF == true) {
                if (book.getAuthor() == title && book.getTitle() == author && book.getYearOfPublishing() == yearOfPublishing) {
                    return book;
                }
            }
        }
        Book book = new Book(title, author, yearOfPublishing, price);
        book.setCreatedWithOF(true);
        return book;
    }
    public static List<Book> getBooksByOwner(Person owner) {

    }
    public static boolean removeBook(Book book) {

    }
    public static List<Book> getBooksByAuthor(String author) {

    }

}