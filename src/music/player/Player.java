package music.player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URI;

/**
 * Created by akhtyamovpavel on 8/14/16.
 */
public class Player {
    private MediaPlayer mediaPlayer;
    private Media media;
    private Playlist playlist;

    private double currentVolume;

    public Player() {
        mediaPlayer = null;
        media = null;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * Loads media from filesystem or resource folder
     * @param pathname Path to media file
     * @param fromSystem Is media file situated in file system or not
     */
    public void loadMedia(String pathname, boolean fromSystem) {
        String fullPathname = null;
        if (fromSystem) {
            fullPathname = new File(pathname).toURI().toString();
        } else {
            fullPathname = getClass().getResource("/" + pathname).toString();
        }
        media = new Media(fullPathname);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(currentVolume);
        mediaPlayer.setOnEndOfMedia(() -> {
            loadNextTrack();
//            mediaPlayer.stop();
        });
    }

    public void loadNextTrack() {
        mediaPlayer.stop();
        if (playlist == null) {
            return;
        }
        File file = playlist.getNextTrack(false);
        if (file == null) {
            return;
        }
        loadMedia(file.toString(), true);
        play();
    }



    /**
     * Plays loaded track
     */
    public void play() {
        if (isReadyToPlay_()) {
            mediaPlayer.play();
        }
    }

    /**
     * Stops loaded track
     */
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.getStatus() != MediaPlayer.Status.STOPPED) {
            mediaPlayer.stop();
        }
    }

    /**
     * Pauses loaded track
     */
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    public MediaPlayer.Status getStatus() {
        if (mediaPlayer == null) {
            return MediaPlayer.Status.HALTED;
        }
        return mediaPlayer.getStatus();
    }

    private boolean isReadyToPlay_() {
        MediaPlayer.Status status = mediaPlayer.getStatus();
        return status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.UNKNOWN;
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
        currentVolume = volume;
    }
}
