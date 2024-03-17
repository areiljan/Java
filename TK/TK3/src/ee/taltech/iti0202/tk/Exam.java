package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exam {

    /**
     * Carry out battles between consecutive digits.
     *
     * When two consecutive digits battle, the larger number wins.
     * So, 5 wins against 3, 2 wins against 1.
     * The digit which loses is removed from the number.
     * When digits are equal, both are removed.
     * For example: 6672 => 7.
     * Both 6 are removed (because they are equal) and 7 wins against 2.
     * If a digit is not followed by another digit, then this digit wins.
     * For example: 345 => 45. 4 wins against 3. And 5 is alone and wins.
     *
     * The function returns an integer with only the winners.
     *
     * integerBattles(345) => 45
     * integerBattles(70) => 7
     * integerBattles(5033) => 5
     * integerBattles(123456) => 246
     */
    public static int integerBattles(int contestants) {
        String contestantsString = String.valueOf(contestants);
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

    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     *
     * Return true if the three values are evenly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     *
     * evenlySpaced(2, 4, 6) => true
     * evenlySpaced(4, 6, 2) => true
     * evenlySpaced(4, 6, 3) => false
     */
    public static boolean evenlySpaced(int a, int b, int c) {
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

    /**
     * Look for patterns like "zip" and "zap" in the string --
     * length-3, starting with 'z' and ending with 'p'.
     *
     * Return a string where for all such words, the middle letter is gone, so "zipXzap" yields "zpXzp".
     *
     * zipZap("zipXzap") => "zpXzp"
     * zipZap("zopzop") => "zpzp"
     * zipZap("zzzopzop") => "zzzpzp"
     */
    public static String zipZap(String str) {
        return null;
    }

    /**
     * Create a new map and switch keys and values in the input map.
     *
     * If the key and value of an entry are the same, then this entry is skipped.
     *
     * mapSwitchKeysAndValues({"a": "b", "c": "d"}) => {"b": "a", "d": "c"}
     * mapSwitchKeysAndValues({"a": "a", "e": "e"}) => {}
     */
    public static Map<String, String> mapSwitchKeysAndValues(Map<String, String> map) {
        return null;
    }
}
