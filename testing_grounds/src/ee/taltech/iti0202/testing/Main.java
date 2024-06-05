package ee.taltech.iti0202.testing;

import javax.xml.transform.Result;

class Main {
    Result result = new Result();

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