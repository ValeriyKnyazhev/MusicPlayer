package music.player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by valeriy on 18.08.16.
 */
public class Playlist {
    private ArrayList<File> trackPaths;

    private int currentTrackNumber;

    Playlist() {
        trackPaths = new ArrayList<>();
        currentTrackNumber = 0;
    }

    public final File getTrack(int trackNumber) {
        if (trackPaths.size() == 0) {
            return null;
        }
        return trackPaths.get(trackNumber);
    }

    public final File getNextTrack(boolean isRandom) {
        if (trackPaths.size() == 0) {
            return null;
        }
        if (isRandom) {
            currentTrackNumber = 0;
            return getTrack(0);
        } else {
            currentTrackNumber = (currentTrackNumber + 1) % trackPaths.size();
            return getTrack(currentTrackNumber);
        }
    }

    public final File getPreviousTrack(boolean isRandom) {
        if (trackPaths.size() == 0) {
            return null;
        }
        if (isRandom) {
            currentTrackNumber = 0;
            return getTrack(0);
        } else {
            currentTrackNumber = (currentTrackNumber + trackPaths.size() - 1) % trackPaths.size();
            return getTrack(currentTrackNumber);
        }
    }

    public final int getSize() {
        return trackPaths.size();
    }

    public void loadFile(File track) {
        trackPaths.add(track);
        System.out.println("added " + track.toString() + "; size = " + trackPaths.size());
    }

    public void loadDirectory(File directory) {
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(directory.listFiles()));
        trackPaths.addAll(files);
        System.out.println("size = " + trackPaths.size());
    }

}
