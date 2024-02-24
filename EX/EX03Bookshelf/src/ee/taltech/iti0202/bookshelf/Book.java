package ee.taltech.iti0202.bookshelf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Book {
    private final String title;
    private final String author;
    private final int yearOfPublishing;
    private final int price;
    private static int id = 0;
    private final int idToReturn;
    private boolean createdWithOF;
    public Person owner;
    static HashMap<Book, Person> bookInfo = new HashMap<>();
    private static Book lastadded = null;

    /**
     * Add one integer to the id counter every time this is called.
     */
    public static int getAndIncrementNextId() {
        id++;
        return id - 1;
    }

    /**
     * Book constructor.
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.idToReturn = getAndIncrementNextId();
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
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
    public Person getOwner() {
        return owner;
    }

    /**
     * Set the book owner.
     */
    public void setOwner(Person newOwner) {
        owner = newOwner;
        bookInfo.put(this, owner);
    }

    public int getPrice() {
        return price;
    }


    public int getId() {
        return idToReturn;
    }

    /**
     * Buy the book.
     *
     * @return true if the transaction was successful, else false.
     */
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

    /**
     * Special method to add marked books with four arguments.
     *
     * @return the created book object.
     */
    public static Book of(String title, String author,
                          int yearOfPublishing, int price) {
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            Book book = entry.getKey();
            if (book.createdWithOF) {
                if (Objects.equals(book.getAuthor(), author) && Objects.equals(book.getTitle(), title)
                        && book.getYearOfPublishing() == yearOfPublishing) {
                    return book;
                }
            }
        }
        Book book = new Book(title, author, yearOfPublishing, price);
        book.setCreatedWithOF(true);
        return book;
    }

    /**
     * Special method to add marked books with two arguments.
     *
     * @return the created book object.
     */
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

    /**
     * Special method to get the marked books by the owner.
     *
     * @return list of books that the owner has.
     */
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

    /**
     * Special method to remove marked books.
     *
     * @return true if the removal was successful, else false.
     */
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

    /**
     * Special method to get the books by the named author.
     *
     * @return a list of books that were written by the author.
     */
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
