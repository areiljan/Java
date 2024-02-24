package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {


    @Test
    void buyBookNoMoney() {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", 1926, 100);
        Book meri = new Book("Silverwhite", "Meri", 1976, 200);
        Person mati = new Person("Mati", 0);
        Person kati = new Person("Kati", 300);
        mati.buyBook(tammsaare);
        assertEquals(tammsaare.getOwner(), null);
    }

    @Test
    void buyNullBuyer() {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", 1926, 100);
        Book meri = new Book("Silverwhite", "Meri", 1976, 200);
        Person mati = null;
        tammsaare.buy(mati);
        assertEquals(tammsaare.getOwner(), null);
    }


    @Test
    void buyBookWithOwner() {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", 1926, 100);
        Book meri = new Book("Silverwhite", "Meri", 1976, 200);
        Person mati = new Person("Mati", 200);
        Person kati = new Person("Kati", 300);
        tammsaare.buy(mati);
        tammsaare.buy(kati);
        assertEquals(tammsaare.getOwner(), kati);
    }

    @Test
    void getBooks() {
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        Person mati = new Person("Mati", 2000);
        harry1.buy(mati);
        harry2.buy(mati);
        List<Book> matiBooks = mati.getBooks();
        assertEquals(matiBooks.size(), 2);
    }

    @Test
    void getBooksByOwner() {
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        Person mati = new Person("Mati", 2000);
        harry1.buy(mati);
        harry2.buy(mati);
        List<Book> matiBooks = Book.getBooksByOwner(mati);
        assertEquals(matiBooks.size(), 2);
    }

    @Test
    void removeBookAndGetRicher() {
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        Person mati = new Person("Mati", 2000);
        Person kati = new Person("Kati", 3000);
        harry1.buy(mati);
        Book.removeBook(harry1);
        assertEquals(mati.money, 2000);
    }

    @Test
    void getBooksByAuthor() {
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        List<Book> rowlingBooks = Book.getBooksByAuthor("j. k. rowling");
        assertEquals(rowlingBooks.size(), 2);
    }
}
