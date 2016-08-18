package music.player;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by valeriy on 18.08.16.
 */
public class PlaylistController {
    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;
    private ArrayList<Playlist> playlists;

    public PlaylistController() {
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();

        initPlaylists();
    }

    public void loadDirectory(Stage stage, int playlistNumber) {

    }

    public void loadFile(Stage stage, int playlistNumber) {
        fileChooser.setTitle("Choose track");

        playlists.get(playlistNumber).loadFile(fileChooser.showOpenDialog(stage));
    }

    private void initPlaylists() {
        playlists = new ArrayList<>();

        playlists.add(new Playlist());
    }

}
