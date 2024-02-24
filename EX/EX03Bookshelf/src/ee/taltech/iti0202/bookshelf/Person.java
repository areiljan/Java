package ee.taltech.iti0202.bookshelf;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ee.taltech.iti0202.bookshelf.Book.bookInfo;

public class Person {
    public String name;
    public int money;

    /**
     * Constructor.
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    /**
     * Add money to the person money counter.
     */
    public void setMoney(int bookPrice) {
        money += bookPrice;
    }

    public String getName() {
        return name;
    }

    /**
     * Method to just buy a book.
     *
     * @return true if the buy was successful, else false.
     */
    public boolean buyBook(Book book) {
        if (book != null && book.getOwner() == null && getMoney() >= book.getPrice()) {
            this.money -= book.getPrice();
            book.setOwner(this);
            return true;
        }
        return false;
    }

    /**
     * Special method to just sell a book.
     *
     * @return true if the sale was successful, else false.
     */
    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() != null && (book.getOwner().equals(this))) {
            this.money += book.getPrice();
            book.setOwner(null);
            return true;
        }
        return false;
    }

    /**
     * Special method to just return the books owned by the person.
     *
     * @return list of books.
     */
    public List<Book> getBooks() {
        List<Book> ownerBooks = new ArrayList<>();
        for (Map.Entry<Book, Person> entry : bookInfo.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getName().equalsIgnoreCase(this.getName())) {
                    ownerBooks.add(entry.getKey());
                }
            }
        }
        return ownerBooks;
    }
}
