package GUI.centerModes;

import GUI.MainWindow;
import GUI.left.Libraries;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import com.mpatric.mp3agic.*;
import logic.APlayLists;
import logic.PauseablePlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Center extends JPanel {
    private static PauseablePlayer player;
    private static int index = -1;
    private FileInputStream fileInputStream;
    private static String songName;
    private BufferedImage wallpaper;
    private static HashMap<Integer, String> indexHashmap = new HashMap<>();
    private static HashMap<Integer, String> songsIndextHashmap = new HashMap<>();
    private static HashMap<Integer, String> playListIndexHashmap = new HashMap<>();
    private int i;
    private JScrollPane scroll;

    /**
     * structure for Center in default mode
     *
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public Center() {
        this.setBackground(Colors.getCenter());
        wallpaper = Colors.getWallpaper();
    }

    /**
     * structure for Center in Songs mode
     *
     * @param p just to define which structure it is
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public Center(int p) throws InvalidDataException, IOException, UnsupportedTagException {
        wallpaper = Colors.getWallpaper();
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0, 0, 0, 0));
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        for (i = 0; i < Libraries.getFiles().size(); i++) {
            File file = Libraries.getFiles().get(i);
            String name = file.getName();
            JPanel song = makePanel(name);
            songsIndextHashmap.put(i, name);
            song.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        indexHashmap = songsIndextHashmap;
                        songName = name;
                        index = Libraries.getIndexes().get(Libraries.getInformation().get(name));
                    } else if (me.getButton() == MouseEvent.BUTTON3) {
                        MainWindow.playListAddFrameMaker(me, file);
                    }
                }
            });
            mainP.add(song);
        }
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(mainP);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());

        this.add(scroll);
    }

    /**
     * structure for showing playLists
     * @param aPlayLists
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public Center(APlayLists aPlayLists) throws InvalidDataException, IOException, UnsupportedTagException {
        System.out.println("bilibili");
        wallpaper = Colors.getWallpaper();
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0, 0, 0, 0));
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        for (i = 0; i < aPlayLists.getFiles().size(); i++) {
            File file = aPlayLists.getFiles().get(i);
            String name = file.getName();
            JPanel song = makePanel(name);
            playListIndexHashmap.put(i, name);
            song.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        indexHashmap = songsIndextHashmap;
                        songName = name;
                        index = aPlayLists.getIndex(file);
                    } else if (me.getButton() == MouseEvent.BUTTON3) {
                        aPlayLists.removeSong(file);
                        try {
                            MainWindow.changeCenter(new Center(aPlayLists));
                        } catch (InvalidDataException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnsupportedTagException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            mainP.add(song);
        }
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(mainP);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());

        this.add(scroll);

    }

    /**
     * structure for setting
     * @param f
     */
    public Center(float f){
        wallpaper = Colors.getWallpaper();
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0, 0, 0, 0));

        this.setLayout(new BorderLayout());
        this.add(mainP);
    }

    public static PauseablePlayer getPlayer() {
        return player;
    }

    /**
     * a private method to make JPanel for every song
     *
     * @param s song name
     * @return JPanel
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    private JPanel makePanel(String s) throws InvalidDataException, IOException, UnsupportedTagException {
        ID3v1 info = null;
        ID3v2 info2 = null;
        JPanel songInfo = new JPanel();
//        songInfo.setPreferredSize(new Dimension(0, 20));
        songInfo.setMaximumSize(new Dimension(1100, 120));
        songInfo.setLayout(new GridLayout(1, 2));
        Mp3File song = new Mp3File(Libraries.getInformation().get(s));
        if (song.hasId3v1Tag())
            info = song.getId3v1Tag();
        if (song.hasId3v2Tag())
            info2 = song.getId3v2Tag();
        //image----------------------------------------------
        BufferedImage img;
        if (!song.hasId3v2Tag() || info2.getAlbumImage() == null || info2 == null) {
            img = Colors.getSongImage();
        } else {
            byte[] imageData = info2.getAlbumImage();
            img = ImageIO.read(new ByteArrayInputStream(imageData));
        }
        JPanel songImagePanel;
        Image image = img.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
        songImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };
        songImagePanel.setSize(new Dimension(110, 110));
        songImagePanel.setBackground(new Color(0, 0, 0, 0));
        songInfo.add(songImagePanel);
        //song,album,artist name panel----------------------
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        //songName-------------------------
        String songName;
        if (info == null) {
            songName = Libraries.getInformation().get(s).getName();
        } else {
            songName = info.getTitle();
        }
        //albumName------------------------
        String albumName;
        if (info == null) {
            albumName = "Unknown";
        } else {
            if (info.getAlbum() == "" || info.getAlbum() == null)
                albumName = "Unknown";
            else
                albumName = info.getAlbum();
        }
        //addingToLabel-----------------------------
        JLabel songNameAlbumName = new JLabel(songName + " || " + albumName);
        songNameAlbumName.setFont(new Font("B Nahar", Font.PLAIN, 20));
        songNameAlbumName.setBackground(new Color(0, 0, 0, 0));
        songNameAlbumName.setForeground(Colors.getText1());
        panel.add(songNameAlbumName);
        //artistName--------------------------------
        JLabel artistName = new JLabel();
        if (info == null) {
            artistName.setText("Unknown");
        } else {
            if (info.getArtist() == "" || info.getArtist() == null)
                artistName.setText("Unknown");
            else
                artistName.setText(info.getAlbum());
        }
        artistName.setBackground(new Color(0, 0, 0, 0));
        artistName.setForeground(Colors.getText1());
        panel.add(artistName);
        panel.setBackground(new Color(0, 0, 0, 0));
        songInfo.add(panel);
        artistName.setFont(new Font("B Nahar", Font.PLAIN, 16));
        //someSetting---------------------------------------
        songInfo.setBackground(new Color(225, 225, 225, 20));
        Border border = BorderFactory.createLineBorder(Colors.getLeft());
        songInfo.setBorder(border);
        songInfo.setPreferredSize(new Dimension(0, 100));
        return songInfo;
    }

    /**
     * Overriden method to make wallpaper
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wallpaper, 0, 0, this);
    }

    public static HashMap<Integer, String> getIndexHashmap() {
        return indexHashmap;
    }

    public static int getIndex() {
        return index;
    }

    public static void setSongName(String s) {
        songName = s;
    }

    public static void setIndex(int i) {
        index = i;
    }

    public static String getSongName() {
        return songName;
    }

    /**
     * reSetting wallpaper
     *
     * @param image
     */
    public void setWallpaper(BufferedImage image) {
        wallpaper = image;
    }
}