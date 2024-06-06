package ee.taltech.iti0202.spotify;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Playlist.
 */
public class Playlist {
    private final String name;
    private final List<Song> songs;

    /**
     * Instantiates a new Playlist.
     *
     * @param name the name
     */
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    /**
     * Add song.
     *
     * @param song the song
     */
    public void addSong(Song song) {
        boolean songFound = false;
        for (Song s : songs) {
            songFound = song.title().equalsIgnoreCase(song.title()) && song.artist().equalsIgnoreCase(song.artist());
        }
        if (!songFound) {
            songs.add(song);
        }
    }

    /**
     * Remove song.
     *
     * @param song the song
     */
    public void removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
        }
    }

    /**
     * Gets songs by artist.
     *
     * @param artist the artist
     * @return the songs by artist
     */
    public List<Song> getSongsByArtist(String artist) {
        List<Song> songsByArtist = new ArrayList<>();
        for (Song s : songs) {
            if (s.artist().equalsIgnoreCase(artist)) {
                songsByArtist.add(s);
            }
        }
        return songsByArtist;
    }

    /**
     * Sort songs alphabetically list.
     *
     * @return the list
     */
    public List<Song> sortSongsAlphabetically() {
        return songs.stream()
                .sorted(Comparator.comparing(Song::title))
                .toList();
    }

    /**
     * Gets longest song.
     *
     * @return the longest song
     */
    public Song getLongestSong() {
        return songs.stream()
                .sorted(Comparator.comparingDouble(Song::duration))
                .toList().getFirst();
    }

    /**
     * Gets duration of playlist.
     *
     * @return the duration of playlist
     */
    public String getDurationOfPlaylist() {
        Integer seconds = 0;
        Integer hours = 0;
        Integer minutes = 0;
        String endString = "";
        for (Song s : songs) {
            seconds += s.duration();
        }
        if (seconds >= 3600) {
            hours = seconds / 3600;
            seconds = seconds - hours * 3600;
            endString += hours + "h ";
        }
        if (seconds >= 60) {
            minutes = seconds / 60;
            seconds = seconds - minutes * 60;
            endString += minutes + "m ";
        }
        if (seconds > 0) {
            endString += "s";
        }
        return endString;

    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
