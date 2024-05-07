package ee.taltech.iti0202.testing;

class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.sound();
    }
}

class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}