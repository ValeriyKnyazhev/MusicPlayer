package music.player;

import javafx.scene.media.MediaPlayer;

/**
 * Created by akhtyamovpavel on 8/14/16.
 */
public class PlayerController {
    private Player player;
    public PlayerController() {
        player = new Player();
    }

    /**
     * Loads audio file
     * @param pathname Path to audio file
     */
    public void loadMusic(String pathname) {
        player.loadMedia(pathname, false);
    }

    /**
     * Controls play music button
     */
    public void playButtonController() {
        if (isMusicPlayed_()) {
            player.pause();
        } else {
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


}
