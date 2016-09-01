package music.player;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by valeriy on 18.08.16.
 */
public class PlaylistController {
    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;
    private ArrayList<Playlist> playlists;
    private ArrayList<String> nameOfPlaylists;


    private int playlistNumber;

    public PlaylistController() {
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();

        setFileFilters();

        initPlaylists();
    }

    private void setFileFilters() {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("WAV", "*.wav"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3"));
    }

    public void loadDirectory(Stage stage, int playlistNumber) {
        directoryChooser.setTitle("Choose directory of files");
        File file = directoryChooser.showDialog(stage);
        if (file == null) {
            return;
        }
        System.out.println("Files will add to playlist" );
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
        loadPlaylistsFromConfig("res/config/playlists.txt");
        for (String name: nameOfPlaylists) {
            playlists.add(new Playlist());
        }
//        playlists.add(new Playlist());
    }

    public Playlist getPlaylist() {
        return playlists.get(playlistNumber);
    }

    public final int getPlaylistNumber() {
        return playlistNumber;
    }

    public void loadPlaylistsFromConfig(String filePath) {
        int numberPlaylists = 0;
        nameOfPlaylists = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("UTF-8"))))
        {
            numberPlaylists = Integer.parseInt(reader.readLine());
            if (numberPlaylists == 0) {
                return;
            }

            playlistNumber = Integer.parseInt(reader.readLine());

            int i = 0;
            while (i++ < numberPlaylists) {
                nameOfPlaylists.add(reader.readLine());
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

}
