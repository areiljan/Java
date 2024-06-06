package ee.taltech.iti0202.spotify;

public record Song(String title, String artist, int duration) {

    @Override
    public String toString() {
        // TODO
        return "\"" + title + "\"" + " by " + artist;
    }
}
