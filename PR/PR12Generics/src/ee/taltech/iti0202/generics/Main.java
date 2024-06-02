package ee.taltech.iti0202.generics;

class Main {
    public static void main(String[] args) {
        // Cat
        AnimalBox<PersianCat> persianCatAnimalBox = new AnimalBox();

        PersianCat persianCat = new PersianCat("Elf");
        persianCatAnimalBox.put(persianCat);

        Optional<PersianCat> catFromBox = persianCatAnimalBox.getAnimal();
        System.out.println(catFromBox.isPresent()); // true
        System.out.println(persianCat.equals(catFromBox.get())); // true

        System.out.println(persianCatAnimalBox.sound()); // Prrr-prrr

        // Dog
        AnimalBox<DobermanDog> dogAnimalBox = new AnimalBox();

        DobermanDog dobby = new DobermanDog("Dobby");
        dogAnimalBox.put(dobby);

        System.out.println(dogAnimalBox.sound()); // Woof!

        // Food
        Food meat = new Meat("MEAT");
        System.out.println(persianCatAnimalBox.feed(meat)); // Elf was fed with MEAT
    }
}