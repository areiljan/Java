package ee.taltech.iti0202.introduction;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Introduction {


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        } else if (valueOne == (valueTwo * 2) || valueTwo == (valueOne * 2)) {
            return "good";
        } else {
            return "ok";
        }
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++)
            if (numbers.get(i) % 2 == 0) {
                integerList.add(numbers.get(i));
            }
        return integerList;
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     *
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        int[] lst1;
        int evenCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                evenCount++;
            }
        }
        int[] evenNumbers = new int[evenCount];
        int index = 0;
        for (int i = 0; numbers.length > i; i++) {
            if (numbers[i] % 2 == 0) {
                evenNumbers[index++] = numbers[i];
            }
        }
        return evenNumbers;
    }

    /**
     * Method gets two Strings as parameters.
     * If two words have the same length, just put them together. If the length is
     * different, remove so many letters from the beginning of the longer word
     * that the two words are the same length, and then put them together.
     * If the first word was longer, return the answer in lower case. If the second word was longer,
     * return the answer in capital letters.
     * If both words are empty or with spaces, return FALSE.
     *
     * @param first String
     * @param second String
     * @return String based on the values of first and second
     */
    public String findTheString(String first, String second) {
        if (first.isEmpty() || first.contains(" ") && second.isEmpty() || second.contains(" ")) {
            return "FALSE"; }
        else if (first.length() == second.length()) {
            return first + second;
        }else {
            int difference = 0;
            if (first.length() >= second.length()) {
                difference = first.length() - second.length();
                if (difference < first.length()) {
                    return (first.substring(difference) + second).toLowerCase();
                } else {
                    return "";
                }
            } else {
                difference = second.length() - first.length();
                if (difference <= second.length()) {
                    return (first + second.substring(difference)).toUpperCase();
                } else {
                    return "";
                }
            }
        }
    }

    /**
     * Method gets one String as a parameter.
     * In a given string, count the number of characters that appear exactly three times in a row.
     *
     * @param word String
     * @return The number of triples
     */
    public static int countTripleChars(String word) {
        int count = 0;
        word = word + " ";
        for (int i = 0; i < word.length() - 2; i++) {
            char currentChar = Character.toLowerCase(word.charAt(i));
            if (i + 3 < word.length() && currentChar == word.charAt(i + 1) && currentChar == word.charAt(i + 2)) {
                if (currentChar == word.charAt(i + 3)){
                    i = i + 2;
                } else {
                    count++;
                    i = i + 2;
                }
            }
        }
        return count;
    }


    /**
     * Run tests.
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        Introduction introduction = new Introduction();
        System.out.println(introduction.howIsOutcome(3, 6)); // "bad"

        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 7, 5, 2, 1, 2, -2, 0));
        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]

        int[] array = {9, 0, 24, -6, 3};
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]

        String result = introduction.findTheString("Good", "afternoon");
        System.out.println(result);  // GOODNOON
        result = introduction.findTheString("Hello", "lo");
        System.out.println(result);  // lolo
        System.out.println(introduction.findTheString("", ""));  // FALSE
        System.out.println(introduction.findTheString("", "   "));  // FALSE
        System.out.println(introduction.findTheString("  ", "a"));  //  a  (with space in front)

        System.out.println(introduction.countTripleChars("aaabbbabbb"));  // 3
        System.out.println(introduction.countTripleChars("aaa"));  // 1
        System.out.println(introduction.countTripleChars("aaaa"));  // 0
        System.out.println(introduction.countTripleChars("aaaabbbabbbcCc"));  // 2
    }
}
