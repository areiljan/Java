package ee.taltech.iti0202.bookshelf;



public class Person {
    public String name;
    public int money;
    
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int bookPrice) {
        money += bookPrice;
    }

    public String getName() {
        return name;
    }

    public boolean buyBook(Book book) {
        if (book.getOwner() == null && getMoney() > book.getPrice()) {
            this.money -= book.getPrice();
            book.owner = this;
            return true;
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (book.getOwner().getName().equals(this.getName()) && book != null) {
            this.money += book.getPrice();
            book.setOwner(null);
            return true;
        }
        return false;
    }
}