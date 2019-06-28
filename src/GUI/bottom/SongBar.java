package GUI.bottom;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.left.Libraries;
import GUI.theme.Colors;
import com.mpatric.mp3agic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SongBar extends JPanel {
    private JPanel imagePlace = new JPanel();
    private JLabel songName = new JLabel();
    private JPanel artistSong = new JPanel();
    private JLabel artistName = new JLabel();
    private JPanel heart = new JPanel();

    /**
     * SongBar structure containing song image, song name, album name and artist name
     *
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public SongBar() throws InvalidDataException, IOException, UnsupportedTagException {
        this.setBackground(Colors.getDown());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //getting Information--------------------------------------------------
        Mp3File song = null;
        ID3v1 info1 = null;
        ID3v2 info2 = null;
        if (MainWindow.player != null) {
            song = new Mp3File(Libraries.getFilesVIAid().get(Center.getIndex()));
            if (song.hasId3v1Tag())
                info1 = song.getId3v1Tag();
            if (song.hasId3v2Tag())
                info2 = song.getId3v2Tag();
        }
        //image----------------------------------------------------------------
        imagePlace.setPreferredSize(new Dimension(80, 0));
        BufferedImage img;
        if (song != null) {
            if (!song.hasId3v2Tag() || info2.getAlbumImage() == null || info2 == null) {
                img = Colors.getSongImage();
            } else {
                byte[] imageData = info2.getAlbumImage();
                img = ImageIO.read(new ByteArrayInputStream(imageData));
            }
        } else {
            img = Colors.getSongImage();
        }
        JPanel imagePanel;
        Image image = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
            }
        };
        imagePanel.setPreferredSize(new Dimension(80, 80));
        imagePanel.setBackground(new Color(0, 0, 0, 0));
        imagePlace.add(imagePanel);
        imagePlace.setBackground(Colors.getDown());
        //artistSongPanel-----------------------------------------------------------
        artistSong.setLayout(new BoxLayout(artistSong, BoxLayout.Y_AXIS));
        artistSong.setBackground(Colors.getDown());
        //songName----------------
        if (song!=null){
            if (info1 == null) {
                songName.setText(Libraries.getInformation().get(Center.getSongName()).getName());
            } else {
                songName.setText(info1.getTitle());
            }
        }else {
            songName.setText("Nothing");
        }
        songName.setBackground(Colors.getDown());
        songName.setForeground(Colors.getText1());
        songName.setPreferredSize(new Dimension(100, 43));
        songName.setFont(new Font("B Nahar", Font.BOLD, 15));
        artistSong.add(songName);
        //emptyPanel--------------
        String albumName;
        if (MainWindow.player == null || info1 == null || info1.getAlbum() == null || info1.getAlbum() == "") {
            albumName = "Unknown";
        } else {
            albumName = info1.getAlbum();
        }
        JLabel albumNameLabel = new JLabel(albumName);
        albumNameLabel.setBackground(Colors.getDown());
        albumNameLabel.setForeground(Colors.getText1());
        albumNameLabel.setFont(new Font("B Nahar", Font.PLAIN, 13));
        albumNameLabel.setPreferredSize(new Dimension(100, 42));
        artistSong.add(albumNameLabel);
        //artistName--------------
        if (info1 == null) {
            artistName.setText("Unknown");
        } else {
            if (info1.getArtist() == "" || info1.getArtist() == null)
                artistName.setText("Unknown");
            else
                artistName.setText(info1.getAlbum());
        }
        artistName.setBackground(Colors.getDown());
        artistName.setForeground(Colors.getText1());
        artistName.setFont(new Font("B Nahar", Font.PLAIN, 13));
        artistName.setPreferredSize(new Dimension(100, 42));
        artistSong.add(artistName);
        //heart----------------------------------------------------------------
        heart.setBackground(Colors.getDown());
        heart.setPreferredSize(new Dimension(20, 0));
        JLabel heartLabel = new JLabel("\uD83D\uDC94");
        heartLabel.setBackground(Colors.getDown());
        heartLabel.setForeground(Colors.getText2());
        heartLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (heartLabel.getText().equals("\uD83D\uDC94")){
                    heartLabel.setFont(new Font("B Nahar", Font.PLAIN, 18));
                    heartLabel.setText("♥");

                }else if (heartLabel.getText().equals("♥")){
                    heartLabel.setFont(new Font("B Nahar", Font.PLAIN, 15));
                    heartLabel.setText("\uD83D\uDC94");
                }
            }

        });
        heart.add(heartLabel);
        //add------------------------------------------------------------------
        this.add(imagePlace);
        this.add(artistSong);
        this.add(heart);
    }
}
