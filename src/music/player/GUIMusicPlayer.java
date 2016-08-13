package music.player;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by valeriy on 13.08.16.
 */
public class GUIMusicPlayer {

    private Button btnNextMusic;
    private Button btnPrevMusic;
    private Button btnPlayPauseMusic;
    private Button btnStopMusic;
    private Scene scenePlayer;
    VBox playerPanel;
    HBox musicControlPanel;


    public GUIMusicPlayer() {
        btnPlayPauseMusic = new Button();
        btnStopMusic = new Button();
        btnPrevMusic = new Button();
        btnNextMusic = new Button();
        btnPlayPauseMusic.setOnAction(e-> ButtonClicked(e));
        btnStopMusic.setOnAction(e-> ButtonClicked(e));
        btnPrevMusic.setOnAction(e-> ButtonClicked(e));
        btnNextMusic.setOnAction(e-> ButtonClicked(e));


        playerPanel = new VBox();

        musicControlPanel = new HBox();


        musicControlPanel.getChildren().addAll(btnPrevMusic, btnPlayPauseMusic, btnStopMusic, btnNextMusic);
        playerPanel.getChildren().addAll(musicControlPanel);

        scenePlayer = new Scene(playerPanel, 300, 400);

        defineButtonStyles();


    }

    private void defineButtonStyles() {
        scenePlayer.getStylesheets().add(GUIMusicPlayer.class.getResource("/styles/Button.css").toExternalForm());

        btnPlayPauseMusic.getStyleClass().add("play-pause-button");
        btnPlayPauseMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnPlayPauseMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);

        btnStopMusic.getStyleClass().add("stop-button");
        btnStopMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnStopMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);

        btnPrevMusic.getStyleClass().add("prev-button");
        btnPrevMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnPrevMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);

        btnNextMusic.getStyleClass().add("next-button");
        btnNextMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnNextMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
    }

    public final Scene getScenePlayer() {
        return scenePlayer;
    }

    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource() == btnPlayPauseMusic) {
            PlayPauseMusicAction();
        }
        else if (e.getSource() == btnStopMusic) {
            StopMusicAction();
        }
        else if (e.getSource() == btnPrevMusic) {
            PrevMusicAction();
        }
        else if (e.getSource() == btnNextMusic) {
            NextMusicAction();
        }
    }

    private void PlayPauseMusicAction() {
        if (btnStopMusic.isVisible()) {
            btnStopMusic.setVisible(false);
        }
        else {
            btnStopMusic.setVisible(true);
        }
    }

    private void StopMusicAction() {

    }

    private void PrevMusicAction() {

    }

    private void NextMusicAction() {

    }
}
