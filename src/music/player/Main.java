package music.player;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {

    private Scene scenePlayer;
    private Stage stagePlayer;
    private GUIMusicPlayer guiMusicPlayer;

    @Override
    public void start(Stage primaryStage) {
        stagePlayer = primaryStage;
        guiMusicPlayer = new GUIMusicPlayer();

        scenePlayer = guiMusicPlayer.getScenePlayer();

        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scenePlayer);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}