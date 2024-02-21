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
        if (getOwner() != null) {
            this.getOwner().sellBook(this);
        }
        buyer.buyBook(this);
        if (buyer == null) {
            buyer.money += getPrice();
        }
        return false;
    }

    public void setCreatedWithOF(boolean createdWithOf) {
        this.createdWithOF = createdWithOf;
    }

    public boolean getCreatedWithOF() {
        return createdWithOF;
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
        List<Book> ownerBooks = new ArrayList<>();
        for (Map.Entry<Person, Book> entry : bookInfo.entrySet()) {
            if (entry.getKey().equals(owner)) {
                ownerBooks.add(entry.getValue());
            }
        }
        return ownerBooks;
    }
    public static boolean removeBook(Book book) {
        if (book == null || !bookInfo.containsValue(book)) {
            return false; // Return false if the book is null or not found in bookInfo
        }

        // Iterate through the bookInfo hashmap to find and remove the book
        for (Map.Entry<Person, Book> entry : bookInfo.entrySet()) {
            if (entry.getValue().equals(book) && entry.getValue().getCreatedWithOF()) {
                // Remove the book if it was created using the 'of' method
                bookInfo.remove(entry.getKey());
                // If the book is owned by someone, increase their money by the book's price
                if (book.getOwner() != null) {
                    book.getOwner().setMoney(book.getPrice());
                }
                return true; // Return true after successfully removing the book
            }
        }
        return false; // Return false if the book was not removed
    }
    public static List<Book> getBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : bookInfo.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

}