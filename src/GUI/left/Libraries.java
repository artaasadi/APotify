package GUI.left;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.theme.Colors;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import logic.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Libraries extends JPanel {
    private FileReader fileReader;
    private static ArrayList<File> files = new ArrayList<>();
    private static HashMap<String, File> information = new HashMap<>();
    private static HashMap<Integer, File> filesVIAid = new HashMap<>();
    private Handle handler = new Handle();
    private static HashMap<File, Integer> indexes = new HashMap<>();
    private static HashMap<PushbackReader,Mp3File> tags;

    public Libraries() {
        this.setBackground(Colors.getLeft());
        //Label-------------------------------------------------------------
        JLabel label = new JLabel("YOUR LIBRARY");
        label.setFont(new Font("A Nahar", Font.PLAIN, 20));
        label.setForeground(Colors.getText2());
        label.setBackground(Colors.getLeft());
        this.add(label);
        //Add---------------------------------------------------------------
        JButton add = new JButton("+");
        add.setBackground(Colors.getLeft());
        add.setForeground(Colors.getText1());
        add.setBorderPainted(false);
        add.setFocusPainted(false);
        add.setContentAreaFilled(false);
        add.setToolTipText("add your songs");
        this.add(add);
        add.addActionListener(handler);
        //List--------------------------------------------------------------
//        String s[] = {"Songs", "Favorite Songs", "Albums", "Artists"};
        String s[]={"\uD83C\uDFB5 Songs","❤️ Favorite Songs","\uD83D\uDCBF Albums","\uD83C\uDFA4 Artists"};
        JList list = new JList(s);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        list.setFont(new Font("A Nahar", Font.ITALIC, 17));
        list.setSelectionForeground(Colors.getText2());
        list.setSelectionBackground(Colors.getLeft());
        list.setBackground(Colors.getLeft());
        list.setForeground(Colors.getText1());
        list.setFocusable(false);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    if (o.toString()=="\uD83C\uDFB5 Songs"){
                        try {
                            MainWindow.changeCenter(new Center(0));
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        this.add(list);

    }

    public static HashMap<File, Integer> getIndexes() {
        return indexes;
    }

    public static HashMap<String, File> getInformation() {
        return information;
    }

    public static ArrayList<File> getFiles() {
        return files;
    }

    public static HashMap<Integer, File> getFilesVIAid() {
        return filesVIAid;
    }

    public static HashMap<PushbackReader, Mp3File> getTags() {
        return tags;
    }

    private class Handle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("+")) {
                fileReader = new FileReader();
                for (int i = 0; i < fileReader.getFiles().size(); i++) {
                    files.add(fileReader.getFile()[i]);
                    information.put(fileReader.getFile()[i].getName(), fileReader.getFile()[i]);
                    indexes.put(fileReader.getFile()[i], i);
                    filesVIAid.put(i, fileReader.getFile()[i]);
                }
                try {

                    MainWindow.changeCenter(new Center(0));

                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

