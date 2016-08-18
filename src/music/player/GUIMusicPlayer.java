package music.player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by valeriy on 13.08.16.
 */
public class GUIMusicPlayer {

    private Button btnNextMusic;
    private Button btnPrevMusic;
    private Button btnPlayPauseMusic;
    private Button btnStopMusic;

    //to replay surrent music
    private Button btnReplayMusic;
    //to play track in random order
    private Button btnRandomMusic;
    //open the window with settings of the player
    private Button btnSettings;

    //main scene of player
    private Scene scenePlayer;

    private Slider sliderVolume;
    private Slider sliderProgressTrack;

    //main panel of the player
    //includes musicControlPanel, settingsPanel, fileNamePanel, progressTrackPanel, optionalPanel
    private VBox playerPanel;

    //panel for controlling tracks,
    //includes musicControlMainPanel, musicControlAdditionalPanel
    private VBox musicControlPanel;

    //panel with btnNextMusic, btnPrevMusic, btnPlayPauseMusic, btnStopMusic
    private HBox musicControlMainPanel;

    //panel with btnReplayMusic, btnRandomMusic, sliderVolume
    private HBox musicControlAdditionalPanel;

    //panel with btnSettings
    private HBox settingsPanel;

    private HBox fileNamePanel;                                                                                         //

    //panel with sliderProgressTrack
    private HBox progressTrackPanel;

    //panel for work with playlists
    //includes namePlaylistPanel at Top, currentPlaylistPanel at Center
    BorderPane playlistPanel;

    //panel with names of the playlists
    private HBox namePlaylistPanel;

    //panel with scroll pane and list view                                                                              //
    private HBox currentPlaylistPanel;

    private HBox optionalPanel;

    private static boolean isRandomMusic = false;
    private static boolean isReplayedMusic = false;

    private Stage stagePlayer;

    private static int currentVolume;
    private static PlayerStyle currentPlayerStyle ;

    private PlayerController playerController;

    private PlaylistController playlistController;


    public GUIMusicPlayer(Stage stagePlayer) {
        btnPlayPauseMusic = new Button();
        btnStopMusic = new Button();
        btnPrevMusic = new Button();
        btnNextMusic = new Button();
        btnReplayMusic = new Button("replay");
        btnRandomMusic = new Button("random");
        btnSettings = new Button("settings");

        sliderProgressTrack = new Slider();
        sliderVolume = new Slider();

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

        this.stagePlayer = stagePlayer;

        playlistController = new PlaylistController();

        // Defining controller
        playerController = new PlayerController();
        playerController.setPlaylistController(playlistController);

        this.stagePlayer = stagePlayer;

        setControlSizes();

        currentPlayerStyle = checkStyle();

        currentVolume = 50;
        setVolume(currentVolume);

        setControlValues();

        defineActions();

        settingsPanel.getChildren().addAll(btnSettings);
        musicControlPanel.getChildren().addAll(musicControlMainPanel, musicControlAdditionalPanel);
        musicControlAdditionalPanel.getChildren().addAll(btnRandomMusic, btnReplayMusic, sliderVolume);
        musicControlMainPanel.getChildren().addAll(btnPrevMusic, btnPlayPauseMusic, btnStopMusic, btnNextMusic);

        playerPanel.getChildren().addAll(settingsPanel, fileNamePanel, musicControlPanel, progressTrackPanel, playlistPanel, optionalPanel);

        scenePlayer = new Scene(playerPanel, Constants.WIDTH_WINDOW, Constants.HEIGHT_WINDOW);

        defineStyle(currentPlayerStyle);

    }

    private PlayerStyle checkStyle() {
        return PlayerStyle.BaseStyle;
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

    private void setControlSizes() {
        btnPlayPauseMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnPlayPauseMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnStopMusic.setMinSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnStopMusic.setMaxSize(Constants.WIDTH_BUTTON1, Constants.HEIGHT_BUTTON);
        btnPrevMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnPrevMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnNextMusic.setMinSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
        btnNextMusic.setMaxSize(Constants.WIDTH_BUTTON2, Constants.HEIGHT_BUTTON);
    }

    private void setControlValues() {
        sliderVolume.setValue(currentVolume);
        sliderVolume.setMax(Constants.MAX_VOLUME);
        sliderVolume.setMin(Constants.MIN_VOLUME);
    }

    private void defineActions() {
        btnPlayPauseMusic.setOnAction(e-> ButtonClicked(e));
        btnStopMusic.setOnAction(e-> ButtonClicked(e));
        btnPrevMusic.setOnAction(e-> ButtonClicked(e));
        btnNextMusic.setOnAction(e-> ButtonClicked(e));
        btnSettings.setOnAction(e-> ButtonClicked(e));

        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                setVolume(new_val.intValue());
            }
        });
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
        else if (e.getSource() == btnSettings) {
            SettingsAction();
        }
    }

    private void PlayPauseMusicAction() {
        playerController.playButtonController();
    }

    private void StopMusicAction() {
        playerController.stopButtonController();
    }

    private void PrevMusicAction() {
        playerController.prevButtonController();
    }

    private void NextMusicAction() {
        playerController.nextButtonController();
    }

    private void SettingsAction() {
        playlistController.loadFile(stagePlayer, 0);
    }

    private void setVolume(int volume) {
        playerController.setVolume(volume);
    }

}
