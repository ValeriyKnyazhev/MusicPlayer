package music.player;

import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by akhtyamovpavel on 8/14/16.
 */
public class PlayerController {
    private Player player;
    private PlaylistController playlistController;

    public PlayerController() {
        player = new Player();
    }

    /**
     * Loads audio file
     * @param pathname Path to audio file
     */
    public void loadMusic(String pathname) {
        player.loadMedia(pathname, true);
    }

    /**
     * Set current playlist to player
     * @param playlist Playlist object
     */
    public void setPlaylist(Playlist playlist) {
        player.setPlaylist(playlist);
        // load first track
        File file = playlist.getTrack(0);
        if (file == null) {
            return;
        } else {
            player.loadMedia(file.toString(), true);
        }
    }

    public void setPlaylistController(PlaylistController playlistController) {
        this.playlistController = playlistController;
        setPlaylist(playlistController.getPlaylist());
    }

    /**
     * Controls play music button
     */
    public void playButtonController() {
        if (isMusicPlayed_()) {
            player.pause();
        } else {
            if (player.getStatus() == MediaPlayer.Status.HALTED) {
                File file = playlistController.getPlaylist().getTrack(0);
                if (file == null) {
                    System.out.println("Track hasn't loaded");
                    return;
                }
                player.loadMedia(file.toString(), true);
            }
            player.play();
        }
    }

    /**
     * Controls stop music button
     */
    public void stopButtonController() {
        player.stop();
    }

    /**
     * Checks that music is played from player
     * @return Is music played at this moment
     */
    private boolean isMusicPlayed_() {
        MediaPlayer.Status status = player.getStatus();
        return status == MediaPlayer.Status.PLAYING;
    }

    public void setVolume(int volume) {
        player.setVolume(volume / 100.0);
    }

    public void nextButtonController() {
        player.loadNextTrack();
    }
}
