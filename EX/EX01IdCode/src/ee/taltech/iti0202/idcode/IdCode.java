package ee.taltech.iti0202.idcode;
  
public class IdCode {
    public static final int MAX_FIRST_NUMBER_SIZE = 7;
    private final String idCodeValue;

    enum Gender {
          MALE, FEMALE
    }
    
    /**
     * * Method returns the id code.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }
    public IdCode(String idCodeValue) {
        if (isCorrect()) {
            this.idCodeValue = idCodeValue;
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        if (isGenderNumberCorrect() && isYearNumberCorrect() && isMonthNumberCorrect() && isDayNumberCorrect() && isControlNumberCorrect()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        return String.format("This is a %s born on %s.%s.%d in %s", getGender(), idCodeValue.substring(5, 7), idCodeValue.substring(3, 5), getFullYear(), getBirthPlace());
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        if (Character.getNumericValue(idCodeValue.charAt(0)) % 2 == 1) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    /**
       * Get person's birth location.
       * 
       * @return String with the person's birth place.
       */
    public static final int MIN_KURESSAARE_SIZE = 1;
    public static final int MAX_KURESSAARE_SIZE = 10;
    public static final int MIN_TARTU_SIZE = 11;
    public static final int MAX_TARTU_SIZE = 20;
    public static final int MIN_TALLINN_SIZE = 21;
    public static final int MAX_TALLINN_SIZE = 220;
    public static final int MIN_KOHTLAJARVE_SIZE = 221;
    public static final int MAX_KOHTLAJARVE_SIZE = 270;
    public static final int MIN_TARTU_SIZE_SECOND = 271;
    public static final int MAX_TARTU_SIZE_SECOND = 370;
    public static final int MIN_NARVA_SIZE = 371;
    public static final int MAX_NARVA_SIZE = 420;
    public static final int MIN_PARNU_SIZE = 421;
    public static final int MAX_PARNU_SIZE = 470;
    public static final int MIN_TALLINN_SIZE_SECOND = 471;
    public static final int MAX_TALLINN_SIZE_SECOND = 490;
    public static final int MIN_PAIDE_SIZE = 491;
    public static final int MAX_PAIDE_SIZE = 520;
    public static final int MIN_RAKVERE_SIZE = 521;
    public static final int MAX_RAKVERE_SIZE = 570;
    public static final int MIN_VALGA_SIZE = 571;
    public static final int MAX_VALGA_SIZE = 600;
    public static final int MIN_VILJANDI_SIZE = 601;
    public static final int MAX_VILJANDI_SIZE = 650;
    public static final int MIN_VORU_SIZE = 651;
    public static final int MAX_VORU_SIZE = 710;

    public String getBirthPlace() {
        int number;
        number = Integer.parseInt(idCodeValue.substring(7,10));
        if (number >= MIN_KURESSAARE_SIZE && number <= MAX_KURESSAARE_SIZE) {
            return "Kuressaare";
        } else if (number >= MIN_TARTU_SIZE && number <= MAX_TARTU_SIZE) {
            return "Tartu";
        } else if (number >= MIN_TALLINN_SIZE && number <= MAX_TALLINN_SIZE) {
            return "Tallinn";
        } else if (number >= MIN_KOHTLAJARVE_SIZE && number <= MAX_KOHTLAJARVE_SIZE) {
            return "Kohtla-Järve";
        } else if (number >= MIN_TARTU_SIZE_SECOND && number <= MAX_TARTU_SIZE_SECOND) {
            return "Tartu";
        } else if (number >= MIN_NARVA_SIZE && number <= MAX_NARVA_SIZE) {
            return "Narva";
        } else if (number >= MIN_PARNU_SIZE && number <= MAX_PARNU_SIZE) {
            return "Pärnu";
        } else if (number >= MIN_TALLINN_SIZE_SECOND && number <= MAX_TALLINN_SIZE_SECOND) {
            return "Tallinn";
        } else if (number >= MIN_PAIDE_SIZE && number <= MAX_PAIDE_SIZE) {
            return "Paide";
        } else if (number >= MIN_RAKVERE_SIZE && number <= MAX_RAKVERE_SIZE) {
            return "Rakvere";
        } else if (number >= MIN_VALGA_SIZE && number <= MAX_VALGA_SIZE) {
            return "Valga";
        } else if (number >= MIN_VILJANDI_SIZE && number <= MAX_VILJANDI_SIZE) {
            return "Viljandi";
        } else if (number >= MIN_VORU_SIZE && number <= MAX_VORU_SIZE) {
            return "Võru";
        } else {
            return "unknown";
        }
      }
    /**
       * Get the year that the person was born in.
       * 
       * @return int with person's birth year.
       */
    public static final int NINETEENTH_CENTURY = 1800;
    public static final int TWENTIETH_CENTURY = 1900;
    public static final int TWENTY_FIRST_CENTURY = 2000;
    public int getFullYear() {
        int firstNumber;
        firstNumber = Integer.parseInt(idCodeValue.substring(0, 1));
        if (firstNumber == 1 || firstNumber == 2) {
            return NINETEENTH_CENTURY + Integer.parseInt(idCodeValue.substring(1,3));
        } else if (firstNumber == 3 || firstNumber == 4){
            return TWENTIETH_CENTURY + Integer.parseInt(idCodeValue.substring(1,3));
        } else if (firstNumber == 5 || firstNumber == 6){
            return TWENTY_FIRST_CENTURY + Integer.parseInt(idCodeValue.substring(1,3));
        } else {
            return 0;
        }
    }

    /**
       * Check if gender number is correct.
       * 
       * @return boolean describing whether the gender number is correct.
       */
    private boolean isGenderNumberCorrect() {
        if (Integer.parseInt(idCodeValue.substring(0, 1)) < MAX_FIRST_NUMBER_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     **/
    public static final int NEXT_YEAR = 2025;
    private boolean isYearNumberCorrect() {
        int yearNumber = getFullYear();
        boolean b = yearNumber > TWENTIETH_CENTURY && yearNumber < NEXT_YEAR;
        return b;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    public static final int MONTHS_IN_YEAR = 12;
    private boolean isMonthNumberCorrect() {
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        boolean b = monthNumber >= 0 && monthNumber <= MONTHS_IN_YEAR;
        return b;
    }

    /**
       * Check if the day number is correct.
       * 
       * @return boolean describing whether the day number is correct.
       */
    private boolean isDayNumberCorrect() {
        int dayNumber = Integer.parseInt(idCodeValue.substring(5, 7));
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        boolean leapYear = isLeapYear(getFullYear());
        if (monthNumber < 1 || monthNumber > 12 || dayNumber < 1) {
            // Invalid input values
            return false;
        }
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            // Adjusting for leap years
        if (leapYear && monthNumber == 2){
            daysInMonth[2] = 29;
        }
        return dayNumber <= daysInMonth[monthNumber];
    }

    /**
       * Check if the control number is correct.
       * 
       * @return boolean describing whether the control number is correct.
       */
    private boolean isControlNumberCorrect() {
        int[] firstWeights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        int[] secondWeights = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
        int sum = 0;
        int controlNumber = 0;
        for (int i = 0; i <= 9; i++) {
            sum = sum + (Integer.parseInt(idCodeValue.substring(i, i + 1)) * firstWeights[i]);
        }
        if ((sum % 11) < 10) {
            controlNumber = sum % 11;
        } else if ((sum % 11) == 10) {
            for (int i = 0; i <= 9; i++) {
                sum = sum + (Integer.parseInt(idCodeValue.substring(i, i + 1)) * secondWeights[i]);
            }
            if ((sum % 11) < 10) {
                controlNumber = sum % 11;
            } else if ((sum % 11) == 10) {
                controlNumber = 0;
            }
        } else {
            return false;
        }
        return controlNumber == Integer.parseInt(idCodeValue.substring(10));
    }

    /**
       * Check if the given year is a leap year.
       * 
       * @param fullYear
       * @return boolean describing whether the given year is a leap year.
       */
    private boolean isLeapYear(int fullYear) {
        boolean b = fullYear % 4 == 0;
        return b;
    }
  }
