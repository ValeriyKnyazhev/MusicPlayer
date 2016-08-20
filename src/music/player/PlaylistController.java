package music.player;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by valeriy on 18.08.16.
 */
public class PlaylistController {
    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;
    private ArrayList<Playlist> playlists;


    private int playlistNumber;
    // TODO: make playlist number

    public PlaylistController() {
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();

        initPlaylists();
    }

    public void loadDirectory(Stage stage, int playlistNumber) {
        directoryChooser.setTitle("Choose directory of files");
        File file = directoryChooser.showDialog(stage);
        if (file == null) {
            return;
        }
        playlists.get(playlistNumber).loadDirectory(file);
    }

    public void loadFile(Stage stage, int playlistNumber) {
        fileChooser.setTitle("Choose track");
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }
        playlists.get(playlistNumber).loadFile(file);
    }

    private void initPlaylists() {
        playlists = new ArrayList<>();

        playlists.add(new Playlist());

        playlistNumber = 0;
    }

    public Playlist getPlaylist() {
        return playlists.get(playlistNumber);
    }

}
