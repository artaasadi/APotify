package logic;
//import com.sun.java.util.jar.pack.Package;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    private ArrayList<FileInputStream> songs = new ArrayList<>();
    private JFileChooser fc;
    HashMap<String, File> information = new HashMap<>();
    ArrayList<File> files = new ArrayList<>();
    HashMap<Integer, File> fileViaID = new HashMap<>();
    File[] file;

    public ArrayList<String> getNames() {
        return names;
    }

    ArrayList<String> names = new ArrayList<>();

    public HashMap<String, File> getInformation() {
        return information;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public HashMap<Integer, File> getFileViaID() {
        return fileViaID;
    }

    public File[] getFile() {
        return file;
    }

    public FileReader() {
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new FileNameExtensionFilter(".mp3 files", "mp3"));
        JFrame f = new JFrame();
        int result = fc.showOpenDialog(f);
        file = fc.getSelectedFiles();
        for (int i = 0; i < file.length; i++) {
            files.add(file[i]);
            fileViaID.put(i, file[i]);
            information.put(file[i].getName(), file[i]);
            names.add(file[i].getName());
        }

    }
}




