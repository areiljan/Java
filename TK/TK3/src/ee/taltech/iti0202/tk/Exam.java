package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam {
    public Exam(){
        // Whatever.
    }
    /**
     * Defeat the integer that is weaker.
     * @param contestants - the Integers.
     */
    public Integer integerBattles (Integer contestants) {
        String contestantsString = contestants.toString();
        List<Integer> numberList = new ArrayList<>();

        // Iterate over each character in the string and add it to the list
        for (int i = 0; i < contestantsString.length(); i++) {

            numberList.add((int) contestantsString.charAt(i));
        }
        for (int i = 0; i < numberList.size(); i++) {
            if (i % 2 == 1) {
                Integer j = i - 1;
                if (numberList.get(i) > numberList.get(j)) {
                    numberList.remove(j);
                } else if (numberList.get(i) == numberList.get(j)) {
                    numberList.remove(i);
                    numberList.remove(j);
                } else {
                    numberList.remove(i);
                }
            }
        }
        String numbersString = "";
        for (int i = 0; i < numberList.size(); i++) {
            numbersString = numbersString + numberList.get(i);
        }
        return Integer.parseInt(numbersString);
    }

    public boolean evenlySpaced (Integer a, Integer b, Integer c) {
        int[] numbers = {a, b, c};
        Arrays.sort(numbers);
        int min = numbers[0];
        int mid = numbers[1];
        int max = numbers[2];
        if (Math.abs(min - mid) == Math.abs(max - mid)) {
            return true;
        }
        return false;
    }

}
