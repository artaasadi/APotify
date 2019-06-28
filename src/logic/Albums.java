package logic;

import GUI.MainWindow;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Albums {
    //    private String name;
//    private ArrayList<File> files = new ArrayList<>();
//
//    public Albums(String name) {
//        this.name = name;
//    }
//
//    public void addFile(File file) throws InvalidDataException, IOException, UnsupportedTagException {
//        Mp3File info = new Mp3File(file);
//        if (info.getId3v1Tag().getAlbum().equals(name))
//            files.add(file);
//    }
    private static HashMap<String, APlayLists> albums = new HashMap<>();

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
                    System.out.println(tag.getAlbum()+":"+playLists.getFiles().size());
                }
            }
        }
    }

    public static HashMap<String, APlayLists> getAlbums() {
        return albums;
    }
}
