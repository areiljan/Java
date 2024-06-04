package ee.taltech.iti0202.exam;

public class Exam {

    /**
     * Find the longest distance between two equal symbols.
     *
     * The same symbol can contain in this distance.
     *
     * If there are no equal symbols, return -1.
     *
     * longestDistanceBetweenEqualSymbols("abcda") => 3
     * longestDistanceBetweenEqualSymbols("aaaa") => 2
     * longestDistanceBetweenEqualSymbols("aia") => 1
     * longestDistanceBetweenEqualSymbols("aiu") => -1
     * longestDistanceBetweenEqualSymbols("") => -1
     * longestDistanceBetweenEqualSymbols("aab") => 0
     * longestDistanceBetweenEqualSymbols("abcdabbg") => 4
     *
     * @param s input string
     * @return longest distance
     */
    public static int longestDistanceBetweenEqualSymbols(String s) {
        int longestDistance = -1;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            int distance = s.lastIndexOf(a);
            if (distance > longestDistance) {
                longestDistance = distance;
            }
        }
        return longestDistance;
    }

    /**
     * Write a  method that finds correct difference between two timestamps. Timestamps are given in format HH:MM where
     * HH is two-digit hour marker and MM is two-digit
     * minutes marker. Result must be also in format HH:MM. Difference means basically "time2" minus "time1".
     * timeDiff("10:00", "10:00") => "00:00"
     * timeDiff("10:00", "11:01") => "01:01"
     * timeDiff("10:00", "09:01") => "23:01"
     * timeDiff("10:00", "08:59") => "22:59"
     * timeDiff("10:01", "10:00") => "23:59"
     *
     * @param time1 time as HH:MM
     * @param time2 time as HH:MM
     * @return time difference as HH:MM
     */
    public static String timeDiff(String time1, String time2) {
        return null;
    }

}