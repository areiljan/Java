package ee.taltech.iti0202.generics.animal;

public abstract class Animal {
  private String name;
  /**
   * Animal superclass.
   * @param name - name of the animal.
   */
  public Animal(String name) {
    this.name = name;
  }

  /**
   * Name getter.
   * @return - name as string.
   */
  public String getName() {
      return name;
  }

  /**
   * Make animal make sound.
   * @return - sound as string.
   */
  public abstract String sound();
}
