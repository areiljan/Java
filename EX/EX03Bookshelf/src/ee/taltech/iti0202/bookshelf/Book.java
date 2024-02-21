package ee.taltech.iti0202.bookshelf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private static Book lastadded = null;
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
        this.lastadded = this;
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

    public Book of(String title, String author, int yearOfPublishing, int price) {
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
        boolean someBookWasCreated = false;
        for (Map.Entry<Person, Book> entry : bookInfo.entrySet()) {
            Book book = entry.getValue();
            if (book.createdWithOF == true) {
                if (book.getTitle() == title) {
                    return book;
                }
                someBookWasCreated = true;
            }
        }
        if (someBookWasCreated) {
            Book book = new Book(title, lastadded.getAuthor(), lastadded.getYearOfPublishing(), price);
            book.setCreatedWithOF(true);
            return book;
        }
        return null;
    }
    public static List<Book> getBooksByOwner(Person owner) {
        return new ArrayList<>();
    }
    public static boolean removeBook(Book book) {
        return true;
    }
    public static List<Book> getBooksByAuthor(String author) {
        return new ArrayList<>();
    }

}