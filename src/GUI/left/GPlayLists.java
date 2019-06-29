package GUI.left;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import logic.APlayLists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GPlayLists extends JPanel {

    public ArrayList<APlayLists> playLists = new ArrayList<>();
    public JList showerList;
    public JScrollPane scroll;
    private static APlayLists currentPlayList;

    public GPlayLists() {
        this.setBackground(Colors.getLeft());
        this.setPreferredSize(new Dimension(0, 350));
        //Label---------------------------------------------------------
        JLabel label = new JLabel("PLAYLISTS");
        label.setFont(new Font("A Nahar", Font.PLAIN, 20));
        label.setForeground(Colors.getText2());
        label.setBackground(Colors.getLeft());
        this.add(label);
        //add-----------------------------------------------------------
        JButton add = new JButton("+");
        add.setBackground(Colors.getLeft());
        add.setForeground(Colors.getText1());
        add.setBorderPainted(false);
        add.setFocusPainted(false);
        add.setContentAreaFilled(false);
        add.setToolTipText("add playlist");
        add.addActionListener(MainWindow.addAction());
        this.add(add);
        //showerList----------------------------------------------------------
        playLists.add(new APlayLists("❤️ Favorite"));
        playLists.add(new APlayLists("\uD83D\uDCE4 Shared"));
        ArrayList<String> playListNames = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            playListNames.add(playLists.get(i).getName());
        }
        showerList = new JList(playListNames.toArray());
        showerList.setFont(new Font("A Nahar", Font.ITALIC, 15));
        showerList.setSelectionForeground(Colors.getText2());
        showerList.setSelectionBackground(Colors.getLeft());
        showerList.setBackground(Colors.getLeft());
        showerList.setForeground(Colors.getText1());
        showerList.setFocusable(false);
        showerList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    for (APlayLists aPlayLists : playLists) {
                        if (aPlayLists.getName() == o.toString()) {
                            try {
                                MainWindow.changeCenter(new Center(aPlayLists));
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
        });
        showerList.setBorder(BorderFactory.createEmptyBorder());
        //ScrollBar-----------------------------------------------------
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(showerList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());
        this.add(scroll);
    }

//    private class AddPlayList implements ActionListener {
//        private AddAction addAction = new AddAction();
//        private JTextField playListName;
//        private JButton addButton;
//        private JFrame addPlayListFrame;
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            //mainFrame------------------------------------------------
//            addPlayListFrame = new JFrame();
//            addPlayListFrame.setUndecorated(true);
//            addPlayListFrame.setSize(400, 100);
//            addPlayListFrame.setLocation(200, 480);
//            JPanel panel=new JPanel();
//            panel.setBackground(Colors.getDown());
//            panel.setLayout(new GridBagLayout());
//            GridBagConstraints c = new GridBagConstraints();
//            //textField------------------------------------------------
//            playListName = new JTextField();
//            playListName.setPreferredSize(new Dimension(300, 50));
//            playListName.setBackground(Colors.getLeft());
//            playListName.setForeground(Colors.getText1());
//            c.gridx = 0;
//            c.gridy = 0;
//            c.gridwidth = 3;
//            panel.add(playListName, c);
//            //addButton------------------------------------------------
//            addButton = new JButton("ADD");
//            addButton.setBackground(Colors.getLeft());
//            addButton.setForeground(Colors.getText2());
//            addButton.setSize(100, 50);
//            addButton.addActionListener(addAction);
//            c.gridx = 2;
//            c.gridy = 1;
//            c.gridwidth = 1;
//            panel.add(addButton, c);
//            addPlayListFrame.add(panel);
//            addPlayListFrame.setVisible(true);
//        }
//
//        private class AddAction implements ActionListener {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gPlayLists.add(new APlayLists(playListName.getText()));
//                showerList = new JList(gPlayLists.toArray());
//                addPlayListFrame.setVisible(false);
//                GPlayLists.this.repaint();
//                GPlayLists.this.revalidate();
//                MainWindow.leftRefresh(getGPlayLists());
//            }
//        }
//    }

    private GPlayLists getGPlayLists() {
        return this;
    }

    public JList getShowerList() {
        return showerList;
    }

    public JScrollPane scrollPaneMaker(JList list) {
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(list);
        return scroll;
    }

    /**
     * makes a JList to be shown in the left panel
     *
     * @return JList
     */
    public JList makeShowerList() {
        ArrayList<String> playListNames = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            playListNames.add(playLists.get(i).getName());
        }
        showerList = new JList(playListNames.toArray());
        showerList.setFont(new Font("A Nahar", Font.ITALIC, 15));
        showerList.setSelectionForeground(Colors.getText2());
        showerList.setSelectionBackground(Colors.getLeft());
        showerList.setBackground(Colors.getLeft());
        showerList.setForeground(Colors.getText1());
        showerList.setFocusable(false);
        showerList.setBorder(BorderFactory.createEmptyBorder());
        showerList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    for (APlayLists aPlayLists : MainWindow.left.gPlayLists.playLists) {
                        if (aPlayLists.getName() == o.toString()) {
                            try {
//                                currentPlayList = aPlayLists;
                                currentPlayList=new APlayLists("currentPlayList");
                                for (File file:aPlayLists.getFiles()) {
                                    currentPlayList.addSong(file);
                                }
                                MainWindow.changeCenter(new Center(aPlayLists));
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
        });
        return showerList;
    }

    /**
     * shows the current playList
     *
     * @return currentPlayList
     */
    public APlayLists getCurrentPlayList() {
        return currentPlayList;
    }

    public static void setCurrentPlayList(APlayLists currentPlayList) {
        GPlayLists.currentPlayList = currentPlayList;
    }
}
