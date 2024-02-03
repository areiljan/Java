package ee.taltech.iti0202.idcode;
  
public class IdCode {
  
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
        this.idCodeValue = idCodeValue;
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
    public String getBirthPlace() {
        int number;
        number = Integer.parseInt(idCodeValue.substring(7,10));
        if (number >= 1 && number <= 10) {
            return "Kuressaare";
        } else if (number >= 11 && number <= 20) {
            return "Tartu";
        } else if (number >= 21 && number <= 220) {
            return "Tallinn";
        } else if (number >= 221 && number <= 270) {
            return "Kohtla-Järve";
        } else if (number >= 271 && number <= 370) {
            return "Tartu";
        } else if (number >= 371 && number <= 420) {
            return "Narva";
        } else if (number >= 421 && number <= 470) {
            return "Pärnu";
        } else if (number >= 471 && number <= 490) {
            return "Tallinn";
        } else if (number >= 491 && number <= 520) {
            return "Paide";
        } else if (number >= 521 && number <= 570) {
            return "Rakvere";
        } else if (number >= 571 && number <= 600) {
            return "Valga";
        } else if (number >= 601 && number <= 650) {
            return "Viljandi";
        } else if (number >= 651 && number <= 710) {
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
    public int getFullYear() {
        int firstNumber;
        firstNumber = Integer.parseInt(idCodeValue.substring(0, 1));
        if (firstNumber == 1 || firstNumber == 2) {
            return 1800 + Integer.parseInt(idCodeValue.substring(1,3));
        } else if (firstNumber == 3 || firstNumber == 4){
            return 1900 + Integer.parseInt(idCodeValue.substring(1,3));
        } else if (firstNumber == 5 || firstNumber == 6){
            return 2000 + Integer.parseInt(idCodeValue.substring(1,3));
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
        if (Integer.parseInt(idCodeValue.substring(0, 1)) < 7) {
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
    private boolean isYearNumberCorrect() {
        int yearNumber = getFullYear();
        boolean b = yearNumber > 1800 && yearNumber < 2025;
        return b;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        boolean b = monthNumber > 0 && monthNumber < 13;
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

    /**
       * Run tests.
       * @param args info.
       */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("37605030299");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

  }
