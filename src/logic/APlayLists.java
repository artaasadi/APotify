package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class APlayLists {
    private String name;
    private ArrayList<File> files = new ArrayList<>();
    private boolean isChangeable;
    private boolean isRemoveable;
    private boolean isAlbumOrArtist = false;

    public APlayLists(String name) {
        if (name.equals("Favorite") || name.equals("Shared")) {
            isChangeable = false;
            isRemoveable = false;
        } else {
            isChangeable = true;
            isRemoveable = true;
        }
        this.name = name;
    }

    public void addSong(File file) {
        files.add(file);
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public int getIndex(File file) {
        return files.indexOf(file);
    }

    public void removeSong(File file) {
        files.remove(file);
    }

    public boolean contain(File file) {
        boolean found = false;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getName().equals(file.getName())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void changeName(String newName) {
        if (isChangeable)
            name = newName;
    }

    public boolean isRemoveable() {
        return isRemoveable;
    }

    public void changeOrder(File file, int order) {
        if (order < files.size()) {
            files.remove(file);
            files.add(order, file);
        }
    }

    public File shuffle() {
        Random r = new Random();
        File shuffled = files.get(r.nextInt(files.size()));
        return shuffled;
    }

    public String getName() {
        return name;
    }

    public boolean isAlbumOrArtist() {
        return isAlbumOrArtist;
    }

    public void setAlbumOrArtist(boolean albumOrArtist) {
        isAlbumOrArtist = albumOrArtist;
    }
}
