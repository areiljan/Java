package ee.taltech.iti0202.webbrowser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebBrowserTest {

    @org.junit.jupiter.api.Test
    void homePage() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.setHomePage("clubpenguin.com");
        webBrowser.homePage();
        webBrowser.setHomePage("Y8.com");
        webBrowser.homePage();
        assertEquals("Y8.com", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void back() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("rahamaa.ee");
        webBrowser.goTo("clubpenguin.ee");
        webBrowser.goTo("Y8.ee");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        webBrowser.back(); // Tests whether the back() can go backwards when the history ends.
        List<String> expectedUrls;
        expectedUrls = Arrays.asList("google.com", "rahamaa.ee", "clubpenguin.ee", "Y8.ee",
                "clubpenguin.ee", "rahamaa.ee", "google.com");
        assertEquals(expectedUrls, webBrowser.getHistory()); // Correct order of history after back()
        assertEquals("google.com", webBrowser.getCurrentUrl()); // Does the currentpage also change
    }

    @org.junit.jupiter.api.Test
    void forward() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("rahamaa.ee");
        webBrowser.goTo("clubpenguin.ee");
        webBrowser.back();
        webBrowser.back();
        webBrowser.forward();
        webBrowser.forward();
        webBrowser.forward(); // Cannot go forwards more than it did go back
        List<String> expectedUrls;
        expectedUrls = Arrays.asList("google.com", "rahamaa.ee", "clubpenguin.ee", "rahamaa.ee",
                "google.com", "rahamaa.ee", "clubpenguin.ee");
        assertEquals(expectedUrls, webBrowser.getHistory());
    }

    @org.junit.jupiter.api.Test
    void goTo() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("google.com");
        webBrowser.goTo("google.com");
        webBrowser.goTo("google.com");
        List<String> expectedUrls = Arrays.asList("google.com");
        assertEquals(expectedUrls, webBrowser.getHistory());
    }

    @org.junit.jupiter.api.Test
    void addAsBookmark() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.addAsBookmark();
        webBrowser.goTo("Y8.com");
        webBrowser.addAsBookmark();
        List<String> expectedUrls = Arrays.asList("google.com", "Y8.com");
        assertEquals(expectedUrls, webBrowser.getBookmarks());
    }

    @org.junit.jupiter.api.Test
    void getTop3VisitedPages() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("rahamaa.ee");
        webBrowser.goTo("clubpenguin.ee");
        webBrowser.back();
        webBrowser.forward();
        webBrowser.forward(); // Cannot go forwards more than it did go back
        String expectedResult = "rahamaa.ee - 2 visits\nclubpenguin.ee - 2 visits\ngoogle.com - 1 visit";
        assertEquals(expectedResult, webBrowser.getTop3VisitedPages());
    }
}
