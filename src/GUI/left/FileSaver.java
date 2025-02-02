package GUI.left;

import java.io.*;
import java.util.ArrayList;

public class FileSaver {
    File myfile;
    ArrayList<String> path;
    static int i = 0;

    /**
     * makse new song
     *
     * @param filename name of the file
     */
    public FileSaver(String filename) {
        myfile = new File(filename + ".bin");
        path = new ArrayList<>();

    }

    /**
     * add musics to file
     *
     * @param path path of the song
     * @throws IOException
     */
    public void addMusic(String path) throws IOException {
        this.path.add(path);
        write();

    }

    /**
     * making a file that saves file directions
     *
     * @throws IOException
     */
    public void write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(myfile));
        ObjectOutputStream o = new ObjectOutputStream(fileOutputStream);
        o.writeObject(path);

    }

    public ArrayList<String> readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(String.valueOf(myfile));
        ObjectInputStream o = new ObjectInputStream(fileInputStream);
        ArrayList<String> tmp = null;
        try {
            tmp = (ArrayList<String>) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tmp;

    }


}