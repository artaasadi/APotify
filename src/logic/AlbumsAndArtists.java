package logic;

import GUI.MainWindow;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AlbumsAndArtists {

    private static HashMap<String, APlayLists> albums = new HashMap<>();
    private static HashMap<String, APlayLists> artists = new HashMap<>();

    /**
     * makes the Albums playList
     *
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public static void makeAlbum() throws InvalidDataException, IOException, UnsupportedTagException {
        for (File file : MainWindow.left.libraries.getFiles()) {
            Mp3File info = new Mp3File(file);
            if (info.hasId3v1Tag()) {
                ID3v1 tag = info.getId3v1Tag();
                if (!albums.containsKey(tag.getAlbum())) {
                    APlayLists playLists = new APlayLists(tag.getAlbum());
                    for (File file1 : MainWindow.left.libraries.getFiles()) {
                        Mp3File info2 = new Mp3File(file1);
                        if (info2.hasId3v1Tag()) {
                            ID3v1 tag2 = info2.getId3v1Tag();
                            if (!playLists.contain(file1) && tag2.getAlbum().equals(tag.getAlbum())) {
                                playLists.addSong(file1);
                            }
                        }
                    }
                    albums.put(tag.getAlbum(), playLists);
                }
            }
        }
    }

    /**
     * makes the Artists playLists
     *
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public static void makeArtist() throws InvalidDataException, IOException, UnsupportedTagException {
        for (File file : MainWindow.left.libraries.getFiles()) {
            Mp3File info = new Mp3File(file);
            if (info.hasId3v1Tag()) {
                ID3v1 tag = info.getId3v1Tag();
                if (!artists.containsKey(tag.getArtist())) {
                    APlayLists playLists = new APlayLists(tag.getArtist());
                    for (File file1 : MainWindow.left.libraries.getFiles()) {
                        Mp3File info2 = new Mp3File(file1);
                        if (info2.hasId3v1Tag()) {
                            ID3v1 tag2 = info2.getId3v1Tag();
                            if (!playLists.contain(file1) && tag2.getArtist().equals(tag.getArtist())) {
                                playLists.addSong(file1);
                            }
                        }
                    }
                    artists.put(tag.getArtist(), playLists);
                }
            }
        }
    }

    public static HashMap<String, APlayLists> getAlbums() {
        return albums;
    }

    public static HashMap<String, APlayLists> getArtists() {
        return artists;
    }
}
