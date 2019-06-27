package GUI;

import GUI.bottom.Bottom;
import GUI.centerModes.Center;
import GUI.left.GPlayLists;
import GUI.left.Left;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import logic.APlayLists;
import logic.PauseablePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow {

    private static JFrame mainFrame = new JFrame();
    private static BorderLayout layout = new BorderLayout();
    private static Center center;
    private static Bottom bottom;
    public static Left left;
    private static Right right;
    public static PauseablePlayer player;
    private static Top topPanel;
    private static JFrame playListAddFrame;

    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        //theme------------------------------------------------------------
        Colors.setColors("dark", "rose gold");
        //mainFrame--------------------------------------------------------
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screensize.width, screensize.height);
        mainFrame.setTitle("APotify");
        ImageIcon imageIcon = new ImageIcon("resources/icon1.png");
        mainFrame.setIconImage(imageIcon.getImage());
        mainFrame.setLayout(layout);
        //TopBorder---------------------------------------------------------
        topPanel = new Top();
        mainFrame.add(topPanel, BorderLayout.NORTH);
        //Bottom------------------------------------------------------------
        bottom = new Bottom();
        mainFrame.add(bottom, BorderLayout.SOUTH);
        //Left--------------------------------------------------------------
        left = new Left();
        mainFrame.add(left, BorderLayout.WEST);
        //Right-------------------------------------------------------------
        right = new Right();
        mainFrame.add(right, BorderLayout.EAST);
        //Center------------------------------------------------------------
        if (center == null)
            center = new Center();
        mainFrame.add(center, BorderLayout.CENTER);
        //visibility---------------------------------------------------------
        mainFrame.setVisible(true);
    }

    public static PauseablePlayer getPlayer() {
        return player;
    }

    /**
     * changing the form of center
     *
     * @param c gets the Center
     */
    public static void changeCenter(Center c) {
        mainFrame.remove(center);
        center = c;
        System.out.println("BULIBALA");
        mainFrame.add(center, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    /**
     * refreshing bottom panel
     *
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public static void bottomRefresh() throws InvalidDataException, IOException, UnsupportedTagException {
        mainFrame.remove(bottom);
        bottom = new Bottom();
        mainFrame.add(bottom, BorderLayout.SOUTH);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    /**
     * refreshing left panel
     *
     * @param gPlayLists
     */
    public static void leftRefresh(GPlayLists gPlayLists) {
        left.changePlaylists(gPlayLists);
        left.revalidate();
        left.repaint();
        mainFrame.add(left, BorderLayout.WEST);
        mainFrame.repaint();
        mainFrame.revalidate();
        mainFrame.setVisible(true);
    }

    /**
     * adding PlayList action in PlayLists panel add button
     */
    public static class AddPlayList implements ActionListener {
        private AddAction addAction = new AddAction();
        private JTextField playListName;
        private JButton addButton;
        private JFrame addPlayListFrame;

        @Override
        public void actionPerformed(ActionEvent e) {
            //mainFrame------------------------------------------------
            addPlayListFrame = new JFrame();
            addPlayListFrame.setUndecorated(true);
            addPlayListFrame.setSize(400, 100);
            addPlayListFrame.setLocation(200, 480);
            JPanel panel = new JPanel();
            panel.setBackground(Colors.getDown());
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            //textField------------------------------------------------
            playListName = new JTextField();
            playListName.setPreferredSize(new Dimension(300, 50));
            playListName.setBackground(Colors.getLeft());
            playListName.setForeground(Colors.getText1());
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 3;
            panel.add(playListName, c);
            //addButton------------------------------------------------
            addButton = new JButton("ADD");
            addButton.setBackground(Colors.getLeft());
            addButton.setForeground(Colors.getText2());
            addButton.setSize(100, 50);
            addButton.addActionListener(addAction);
            c.gridx = 2;
            c.gridy = 1;
            c.gridwidth = 1;
            panel.add(addButton, c);
            addPlayListFrame.add(panel);
            addPlayListFrame.setVisible(true);
        }

        private class AddAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                left.gPlayLists.playLists.add(new APlayLists(playListName.getText()));
                left.gPlayLists.showerList = left.gPlayLists.makeShowerList();
                mainFrame.remove(left);
                left.remove(left.gPlayLists);
                left.gPlayLists.remove(left.gPlayLists.scroll);
//                left.gPlayLists.scroll=new JScrollPane(left.gPlayLists.showerList);
                left.gPlayLists.add(left.gPlayLists.scrollPaneMaker(left.gPlayLists.showerList));
                left.gPlayLists.repaint();
                left.gPlayLists.revalidate();
                left.add(left.gPlayLists, BorderLayout.SOUTH);
                left.repaint();
                left.revalidate();
                mainFrame.add(left, BorderLayout.WEST);
                mainFrame.repaint();
                mainFrame.revalidate();
                addPlayListFrame.setVisible(false);
//                MainWindow.leftRefresh(getGPlayLists());
            }
        }

    }

    /**
     * @return new AddPlayList Object
     */
    public static AddPlayList addAction() {
        return new AddPlayList();
    }

    /**
     * adding songs to gPlayLists
     * @param file
     * @param me
     */
    public static void playListAddFrameMaker(MouseEvent me, File file) {
        //mainFrame-------------------------------------------------------------
        playListAddFrame = new JFrame();
        playListAddFrame.setUndecorated(true);
        playListAddFrame.setSize(150, 200);
        playListAddFrame.setLocation(me.getX(), me.getY());
        //JPanel----------------------------------------------------------------
        JPanel panel = new JPanel();
        panel.setBackground(Colors.getDown());
        //Label-----------------------------------------------------------------
        JLabel label = new JLabel("Choose PlayList :");
        label.setBackground(Colors.getDown());
        label.setForeground(Colors.getText1());
        panel.add(label);
        //list------------------------------------------------------------------
        JList list = new JList(MainWindow.left.gPlayLists.showerList.getModel());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    addToPlayList(o.toString(), file);
                }
                playListAddFrame.setVisible(false);
            }
        });
        //scrollPane------------------------------------------------------------
        ManualScrollBar msb = new ManualScrollBar();
        JScrollPane scroll = (JScrollPane) msb.makeUI(list);
        panel.add(scroll);
        playListAddFrame.add(panel);
        playListAddFrame.setVisible(true);
    }

    /**
     * adding a file to a playList
     * @param playlistName
     * @param file
     */
    public static void addToPlayList(String playlistName, File file) {
        for (APlayLists aPlayLists : left.gPlayLists.playLists) {
            if (aPlayLists.getName() == playlistName) {
                aPlayLists.addSong(file);
            }
        }
    }


}
