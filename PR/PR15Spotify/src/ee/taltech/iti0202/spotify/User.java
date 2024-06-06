package ee.taltech.iti0202.spotify;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type User.
 */
public class User {

    private final List<Playlist> playlists;

    /**
     * Instantiates a new User.
     */
    public User() {
        this.playlists = new ArrayList<>();
    }

    /**
     * Add playlist.
     *
     * @param playlist the playlist
     */
    public void addPlaylist(Playlist playlist) {
        boolean found = false;
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(playlist.getName())) {
                found = true;
            }
        }
        if (!found) {
            playlists.add(playlist);
        }
    }

    /**
     * Add song to playlist.
     *
     * @param song     the song
     * @param playlist the playlist
     */
    public void addSongToPlaylist(Song song, Playlist playlist) {
        if (playlists.contains(playlist)) {
            playlist.addSong(song);
        }
    }

    /**
     * Gets playlist.
     *
     * @param playlist the playlist
     * @return the playlist
     */
    public List<Song> getPlaylist(Playlist playlist) {
        if (playlists.contains(playlist)) {
            return playlist.getSongs();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }
}