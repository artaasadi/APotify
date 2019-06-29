package GUI.centerModes;

import GUI.MainWindow;
import GUI.left.Libraries;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import com.mpatric.mp3agic.*;
import logic.APlayLists;
import logic.AlbumsAndArtists;
import logic.PauseablePlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static BufferedImage wallpaper;
    private static HashMap<Integer, String> indexHashmap = new HashMap<>();
    private static HashMap<Integer, String> songsIndextHashmap = new HashMap<>();
    private static HashMap<Integer, String> playListIndexHashmap = new HashMap<>();
    private static HashMap<Integer, String> searchIndexHashmap = new HashMap<>();
    private int i;
    private JScrollPane scroll;
    private String colorSet, themeSet;

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
        mainP.setBackground(new Color(0,0,0,0));
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
                        indexHashmap = new HashMap<>(songsIndextHashmap);
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
     *
     * @param aPlayLists
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public Center(APlayLists aPlayLists) throws InvalidDataException, IOException, UnsupportedTagException {
        wallpaper = Colors.getWallpaper();
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0,0,0,0));
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
                        indexHashmap = new HashMap<>(playListIndexHashmap);
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
     * structure for home
     */
    public Center() {
        System.out.println("run");
        wallpaper = Colors.getWallpaper();
        setWallpaper(wallpaper);
        Label libraryLabel = new Label("__________________LIBRARY__________________");
        libraryLabel.setBackground(Colors.getLeft());
        libraryLabel.setForeground(Colors.getText2());
        JPanel mainP = new JPanel();
        this.setLayout(new BorderLayout());
        mainP.setBackground(new Color(20, 20, 20,100));
        mainP.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 2;
//        c.weightx=0.5;
//        c.weighty=0.5;
        mainP.add(libraryLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("AlbumsAndArtists", "\uD83D\uDCBF", Colors.getText2()), c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("Songs", "\uD83C\uDFB5", Colors.getText2()), c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("Favorite", "❤", Colors.getText2()), c);
        Label playlistLabel = new Label("__________________THEME__________________");
        playlistLabel.setForeground(Colors.getText2());
        playlistLabel.setBackground(Colors.getLeft());
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        mainP.add(playlistLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("arta1", "\uD83D\uDC64", Colors.getText1()), c);
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("arta2", "\uD83D\uDC64", Colors.getText1()), c);
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("arta3", "\uD83D\uDC64", Colors.getText1()), c);
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(mainP);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());
//        this.add(mainP);
        this.add(scroll);
    }

    /**
     * setting panel
     *
     * @param f to understand its setting
     */
    public Center(float f) {
        wallpaper = Colors.getWallpaper();
        Label libraryLabel = new Label("__________________Colors__________________");
        libraryLabel.setBackground(Colors.getLeft());
        libraryLabel.setForeground(Colors.getText2());
        JPanel mainP = new JPanel();
        this.setLayout(new BorderLayout());
        mainP.setBackground(new Color(0,0,0,0));
        mainP.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 2;
        mainP.add(libraryLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        JPanel roseGoldPanel = makePanel2("rose gold", "\uD83D\uDD36", new Color(140, 80, 83));
        mainP.add(roseGoldPanel, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        JPanel goldPanel = makePanel2("gold", "\uD83D\uDD36", new Color(200, 170, 130));
        mainP.add(goldPanel, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        JPanel green = makePanel2("green", "\uD83D\uDD36", new Color(125, 160, 124));
        mainP.add(green, c);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        JPanel purple = makePanel2("purple", "\uD83D\uDD36", new Color(140, 100, 140));
        mainP.add(purple, c);
        c.gridy=3;
        c.gridx=0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        JPanel blue = makePanel2("blue", "\uD83D\uDD36", new Color(130, 180, 210));
        mainP.add(blue, c);
        Label playlistLabel = new Label("__________________THEME__________________");
        playlistLabel.setForeground(Colors.getText2());
        playlistLabel.setBackground(Colors.getLeft());
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        mainP.add(playlistLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("dark", "\uD83D\uDC64", new Color(40, 40, 40)), c);
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainP.add(makePanel2("light", "\uD83D\uDC64", new Color(196, 205, 207)), c);
        Button setButton = new Button("                  SET                  ");
        setButton.setForeground(Colors.getText2());
        setButton.setBackground(Colors.getLeft());
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainWindow.refreshColors(themeSet, colorSet);
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
            }
        });
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        mainP.add(setButton, c);
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(mainP);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());
//        this.add(mainP);
        this.add(scroll);
    }

    /**
     * search panel
     *
     * @param d
     */
    public Center(double d) throws InvalidDataException, IOException, UnsupportedTagException {
        wallpaper = Colors.getWallpaper();
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0, 0, 0, 0));
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        for (i = 0; i < MainWindow.topPanel.getFounds().size(); i++) {
            File file = MainWindow.topPanel.getFounds().get(i);
            String name = file.getName();
            JPanel song = makePanel(name);
            searchIndexHashmap.put(i, name);
            song.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        indexHashmap = searchIndexHashmap;
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
     * Albums and Artists Part
     *
     * @param s shows that it is Albums or Artists
     */
    public Center(String s) {
        //making panel---------------------------------------------------------
        JPanel mainP = new JPanel();
        mainP.setBackground(new Color(0, 0, 0, 0));
        mainP.setLayout(new GridLayout(5, 2));
//        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        //Album mode-----------------------------------------------------------
        if (s.equals("Albums")) {
            for (APlayLists album : AlbumsAndArtists.getAlbums().values()) {
                JPanel albumPanel = makePanel2(album.getName(), "\uD83D\uDCBF", Colors.getText1());
                albumPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            MainWindow.changeCenter(new Center(album));
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                mainP.add(albumPanel);
            }
        }
        //Artist mode----------------------------------------------------------
        else if (s.equals("Artists")) {
            for (APlayLists artist : AlbumsAndArtists.getArtists().values()) {
                JPanel artistPanel = makePanel2(artist.getName(), "\uD83C\uDFA4", Colors.getText1());
                artistPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            MainWindow.changeCenter(new Center(artist));
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                mainP.add(artistPanel);
            }
        }
        //scrollPane-----------------------------------------------------------
        ManualScrollBar msb = new ManualScrollBar();
        scroll = (JScrollPane) msb.makeUI(mainP);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getLeft());
//            this.add(mainP);
        this.add(scroll);
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
     * making panels mode 2
     *
     * @param s
     * @return panel
     */
    private JPanel makePanel2(String s, String e, Color color) {
        //panel----------------------------------------------------------
        JPanel panel = new JPanel();
        panel.setBackground(new Color(225, 225, 225, 20));
        Border border = BorderFactory.createLineBorder(Colors.getLeft());
        panel.setBorder(border);
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setMaximumSize(new Dimension(300,200));
        panel.setLayout(new GridLayout(2, 1));
        //name-----------------------------------------------------------
        JLabel name = new JLabel(s);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setForeground(Colors.getText1());
        panel.add(name);
        //emoji----------------------------------------------------------
        JLabel emoji = new JLabel(e);
        emoji.setHorizontalAlignment(SwingConstants.CENTER);
        emoji.setFont(new Font("B Nahar", Font.PLAIN, 95));
        emoji.setForeground(color);
        emoji.setPreferredSize(new Dimension(150, 150));
        panel.add(emoji);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s.equals("gold") || s.equals("rose gold") || s.equals("green") || s.equals("blue") || s.equals("purple")) {
                    colorSet = s;
                } else {
                    themeSet = s;
                }
            }
        });
        return panel;
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
        return indexHashmap.get(index);
    }

    /**
     * reSetting wallpaper
     *
     * @param image
     */
    public void setWallpaper(BufferedImage image) {
        wallpaper = image;
    }

    public String getNameByIndex(int index) {
        return indexHashmap.get(index);
    }
}