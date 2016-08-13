package music.player;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Music Player");
        primaryStage.setWidth(200);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}