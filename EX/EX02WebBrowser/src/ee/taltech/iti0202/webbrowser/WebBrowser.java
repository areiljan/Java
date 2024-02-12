package ee.taltech.iti0202.webbrowser;

import java.util.*;

public class WebBrowser {
    private String homePage;
    private List<String> bookMarks = new ArrayList<>();
    private List<String> history = new ArrayList<>();
    private Integer positionInHistory;
    /**
     * Constructor.
     */
    public WebBrowser() {
        history.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        history.add(this.homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (positionInHistory >= 1) {
            positionInHistory = positionInHistory - 1;
            history.add(history.get(positionInHistory));
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (positionInHistory < history.size() - 1) {
            positionInHistory = positionInHistory + 1;
            history.add(history.get(positionInHistory));
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
        }
        positionInHistory = history.size() - 1;
    }

    /**
     * Add the current webpage as a bookmark.
     */
    public void addAsBookmark() {
        bookMarks.add(history.get(history.size() - 1));
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
    public List<String> getHistory() {;
        return history;
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> visits = new HashMap<>();
        String topThreeList = null;
        for (String website : history) { // Making a map of the history
            if (history.contains(website)) {
                int newCount;
                newCount = visits.get(website) + 1;
                visits.put(website, newCount);
            } else {
                visits.put(website, 1);
            }
        }
        List<Map.Entry<String, Integer>> visitsSorted = new ArrayList<>(visits.entrySet());

        // Sort the entries by value in descending order
        Collections.sort(visitsSorted, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<Map.Entry<String, Integer>> topThree = visitsSorted.subList(0, Math.min(3, visitsSorted.size()));

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : topThree) {
            result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" visits\n");
        }
        return result.toString();
    }

    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        try {
            int lastIndex = history.size() - 1;
            return history.get(lastIndex);
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        WebBrowser webBrowser = new WebBrowser();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        webBrowser.back();
        webBrowser.back();
        System.out.println(webBrowser.getHistory());  // [google.com, facebook.com, google.com]

        webBrowser = new WebBrowser();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        webBrowser.back();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.forward();
        System.out.println(webBrowser.getCurrentUrl());  // facebook.com

        System.out.println(webBrowser.getHistory()); // [google.com, facebook.com, google.com, facebook.com]
    }
}
