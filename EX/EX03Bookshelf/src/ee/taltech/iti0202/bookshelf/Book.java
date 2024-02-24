package ee.taltech.iti0202.bookshelf;

import java.security.Key;
import java.util.*;


public class Book {
    private final String title;
    private final String author;
    private final int yearOfPublishing;
    private int price;
    private static int id;
    private final int ID;
    private boolean createdWithOF;
    public Person owner;
    static HashMap<Book, Person> bookInfo = new HashMap<>();
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
        this.ID = getAndIncrementNextId();
        this.createdWithOF = false;
        this.owner = null;
        bookInfo.put(this, owner);
        Book.lastadded = this;
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

    public void setOwner(Person newOwner) {
        owner = newOwner;
        bookInfo.put(this, owner);
    }

    public int getPrice() {
        return price;
    }


    public int getId() {
        return ID;
    }

    public boolean buy(Person buyer) {
        int money = 0;
        if (buyer != null) {
            money = buyer.getMoney();
        }

        if (money >= this.getPrice()) {
            if (owner != null && buyer != this.getOwner()) {
                this.getOwner().sellBook(this);
            }
            return buyer.buyBook(this);
        }
        if (buyer == null && owner != null && buyer != this.getOwner()) {
            this.getOwner().sellBook(this);
        }
        return false;
    }

    public void setCreatedWithOF(boolean createdWithOf) {
        this.createdWithOF = createdWithOf;
    }

    public boolean getCreatedWithOF() {
        return createdWithOF;
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            Book book = entry.getKey();
            if (book.createdWithOF) {
                if (Objects.equals(book.getAuthor(), author) && Objects.equals(book.getTitle(), title) && book.getYearOfPublishing() == yearOfPublishing) {
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
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            Book book = entry.getKey();
            if (book.createdWithOF) {
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
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getName().equalsIgnoreCase(owner.getName())) {
                    ownerBooks.add(entry.getKey());
                }
            }
        }
        return ownerBooks;
    }
    public static boolean removeBook(Book book) {
        if (book == null) {
            return false; // Return false if the book is null or not found in bookInfo
        }

        // Iterate through the bookInfo hashmap to find and remove the book
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            if (entry.getKey().equals(book) && entry.getKey().getCreatedWithOF()) {
                // Remove the book if it was created using the 'of' method
                bookInfo.remove(book);
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
        for (Book book : bookInfo.keySet()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

}