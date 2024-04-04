package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;

// Builder class for creating Person objects
    public class PersonBuilder {
        private String idCode;
        private String name;
        private Integer age;
        private Boolean isMale;

    /**
     * PersonBuilder constructor.
     * @param idCode - id code as string.
     */
    public PersonBuilder(String idCode) {
        // mandatory parameter.
            this.idCode = idCode;
        }

        // Method to set name
        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        // Method to set age
        public PersonBuilder withAge(Integer age) {
            if (age > 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            return this;
        }

        // Method to set gender
        public PersonBuilder isMale(boolean isMale) {
            this.isMale = isMale;
            return this;
        }

        // Method to build Person object
        public Person build() {
            return new Person(idCode, name, age, isMale);
        }
    }
