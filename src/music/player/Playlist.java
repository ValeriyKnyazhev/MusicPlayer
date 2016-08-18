package music.player;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by valeriy on 18.08.16.
 */
public class Playlist {
    private ArrayList<File> trackPaths;

    private int currentTrackNumber;

    Playlist() {
        trackPaths = new ArrayList<>();
        currentTrackNumber = -1;
    }

    public final File getTrackPath(int trackNumber) {
        return trackPaths.get(trackNumber);
    }

    public final int getSize() {
        return trackPaths.size();
    }

    public void loadFile(File track) {
        trackPaths.add(track);
        System.out.println("added " + track.toString() + "; size = " + trackPaths.size());
    }

    public void loadDirectory(File directory) {

    }

}
