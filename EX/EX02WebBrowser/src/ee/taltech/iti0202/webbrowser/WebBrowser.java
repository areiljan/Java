package ee.taltech.iti0202.webbrowser;

import java.util.*;

public class WebBrowser {
    private String homePage;
    private List<String> bookMarks = new ArrayList<>();
    private List<String> history = new ArrayList<>();
    private List<String> backAndForwardHistory = new ArrayList<>();
    private Integer positionInHistory = 0;
    /**
     * Constructor.
     */
    public WebBrowser() {
        history.add("google.com");
        backAndForwardHistory.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        backAndForwardHistory.add(this.homePage);
        history.add(this.homePage);
        positionInHistory = backAndForwardHistory.size() - 1;
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (positionInHistory >= 1) {
            positionInHistory--;
            history.add(backAndForwardHistory.get(positionInHistory));
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (positionInHistory < backAndForwardHistory.size() - 1) {
            positionInHistory++;
            history.add(backAndForwardHistory.get(positionInHistory));
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url where to go
     */
    public void goTo(String url) {
        if (!url.equals(history.get(history.size() - 1))) {
            history.add(url);
            backAndForwardHistory.add(url);
        }
        positionInHistory = backAndForwardHistory.size() - 1;
    }

    /**
     * Add the current webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookMarks.contains(backAndForwardHistory.get(positionInHistory))) {
            bookMarks.add(backAndForwardHistory.get(positionInHistory));
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        if (bookMarks.contains(bookmark)) {
            bookMarks.remove(bookmark);
        }
    }

    public List<String> getBookmarks() {
        return bookMarks;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> visits = new HashMap<>();
        for (String website : history) { // Making a map of the history
            int newCount = visits.getOrDefault(website, 0) + 1;
            visits.put(website, newCount);
        }

        List<Map.Entry<String, Integer>> visitsSorted = new ArrayList<>(visits.entrySet());

        // Sort the entries by value in descending order
        Collections.sort(visitsSorted, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        // Make a list of maps.
        List<Map.Entry<String, Integer>> topThree = visitsSorted.subList(0, Math.min(3, visitsSorted.size()));
        Collections.sort(topThree, (entry1, entry2) -> {
            int result = entry2.getValue().compareTo(entry1.getValue()); // Sort by value first in descending order
            if (result != 0) {
                return result; // If values are different, return the comparison result
            } else {
                // If values are the same, find the first occurrence in the history
                int firstIndex1 = history.indexOf(entry1.getKey());
                int firstIndex2 = history.indexOf(entry2.getKey());
                return Integer.compare(firstIndex1, firstIndex2); // Compare first indices
            }
        });
        StringBuilder result = new StringBuilder();
        boolean isFirstEntry = true;
        for (Map.Entry<String, Integer> entry : topThree) {
            if (!isFirstEntry) {
                result.append("\n"); // Add a newline before each entry except the first one
            }
            isFirstEntry = false;

            if (entry.getValue() == 1) {
                result.append(entry.getKey()).append(" - ").append(1).append(" visit");
            } else {
                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" visits");
            }
        }
        return result.toString();
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return backAndForwardHistory.get(positionInHistory);
    }

    public static void main(String[] args) {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("1");
        webBrowser.goTo("2");
        webBrowser.goTo("3");
        webBrowser.goTo("4");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        webBrowser.goTo("6");
        webBrowser.goTo("7");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        System.out.println(webBrowser.getHistory());
        System.out.println(webBrowser.getCurrentUrl());
    }
}
