package music.player;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
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
    private Button btnReplayMusic;
    private Button btnRandomMusic;
    private Button btnSettings;

    private Scene scenePlayer;

    private Slider sliderVolume;
    private Slider sliderProgressTrack;

    VBox playerPanel;
        VBox musicControlPanel;
            HBox musicControlMainPanel;
            HBox musicControlAdditionalPanel;
        HBox settingsPanel;
        HBox fileNamePanel;
        HBox progressTrackPanel;
        BorderPane playlistPanel;
            HBox namePlaylistPanel;
            HBox currentPlaylistPanel;
        HBox optionalPanel;

    private static boolean isRandomMusic = false;
    private static boolean isReplayedMusic = false;

    private static int curVolumeValue;
    private static PlayerStyle curPlayerStyle ;

    private PlayerController playerController;


    public GUIMusicPlayer() {
        btnPlayPauseMusic = new Button();
        btnStopMusic = new Button();
        btnPrevMusic = new Button();
        btnNextMusic = new Button();
        btnReplayMusic = new Button("replay");
        btnRandomMusic = new Button("random");
        btnSettings = new Button("settings");

        btnPlayPauseMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnPlayPauseMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnStopMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnStopMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnPrevMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnPrevMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnNextMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnNextMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);

        curPlayerStyle = PlayerStyle.BaseStyle;

        sliderProgressTrack = new Slider();
        sliderVolume = new Slider();

        curVolumeValue = 50;
        sliderVolume.setValue(curVolumeValue);
        sliderVolume.setMax(Constants.MAX_VOLUME_VALUE);
        sliderVolume.setMin(Constants.MIN_VOLUME_VALUE);


        btnPlayPauseMusic.setOnAction(e-> ButtonClicked(e));
        btnStopMusic.setOnAction(e-> ButtonClicked(e));
        btnPrevMusic.setOnAction(e-> ButtonClicked(e));
        btnNextMusic.setOnAction(e-> ButtonClicked(e));

        // Defining controller
        playerController = new PlayerController();
        playerController.loadMusic("audios/ringtone.mp3");

        playerPanel = new VBox();
        musicControlPanel = new VBox();
        musicControlMainPanel = new HBox();
        musicControlAdditionalPanel = new HBox();
        settingsPanel = new HBox();
        fileNamePanel = new HBox();
        progressTrackPanel = new HBox();
        playlistPanel = new BorderPane();
        namePlaylistPanel = new HBox();
        currentPlaylistPanel = new HBox();
        optionalPanel = new HBox();

        settingsPanel.getChildren().addAll(btnSettings);
        musicControlPanel.getChildren().addAll(musicControlMainPanel, musicControlAdditionalPanel);
        musicControlAdditionalPanel.getChildren().addAll(btnRandomMusic, btnReplayMusic, sliderVolume);
        musicControlMainPanel.getChildren().addAll(btnPrevMusic, btnPlayPauseMusic, btnStopMusic, btnNextMusic);

        playerPanel.getChildren().addAll(settingsPanel, fileNamePanel, musicControlPanel, progressTrackPanel, playlistPanel, optionalPanel);

        scenePlayer = new Scene(playerPanel, Constants.WIDTH_WINDOW, Constants.HEIGHT_WINDOW);

        defineStyle(curPlayerStyle);
    }

    private void defineStyle(PlayerStyle playerStyle) {
        switch (playerStyle) {
            case BaseStyle: {
                scenePlayer.getStylesheets().add(GUIMusicPlayer.class.getResource("/styles/BaseStyle.css").toExternalForm());
                break;
            }
            case ProStyle: {
                scenePlayer.getStylesheets().add(GUIMusicPlayer.class.getResource("/styles/BaseStyle.css").toExternalForm());
                break;
            }
        }

        btnPlayPauseMusic.getStyleClass().add("play-pause-button");
        btnStopMusic.getStyleClass().add("stop-button");
        btnPrevMusic.getStyleClass().add("prev-button");
        btnNextMusic.getStyleClass().add("next-button");

    }

    public final Scene getScenePlayer() {
        return scenePlayer;
    }

    public void ButtonClicked(ActionEvent e) {
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
        playerController.playButtonController();
    }

    private void StopMusicAction() {
        playerController.stopButtonController();
    }

    private void PrevMusicAction() {
    }

    private void NextMusicAction() {

    }
}
