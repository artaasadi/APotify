package GUI.bottom;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.left.Libraries;
import GUI.theme.Colors;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import logic.PauseablePlayer;
import logic.PlayedTimeClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayBar extends JPanel {
    private ArrayList<FileInputStream> songs = new ArrayList<>();
    // HashMap<Integer, FileInputStream> openedSongs = new HashMap<>();
//    private PauseablePlayer player = MainWindow.getPlayer();
    private Handle handler = new Handle();
    private JButton play = new JButton();
    private JPanel playPausePanel = new JPanel();
    private CardLayout cardLayout;
    private static int index;
    Mp3File song = null;
    public static JProgressBar progressBar;
    public static JLabel playedTime;
    Timer timer;
    TimerTask task;

    public PlayBar() throws InvalidDataException, IOException, UnsupportedTagException {

        this.setBackground(Colors.getDown());
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //empty0-----------------------------------------------
        JPanel empty0 = new JPanel();
        empty0.setBackground(Colors.getDown());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        this.add(empty0, c);
        //empty1-----------------------------------------------
        JPanel empty1 = new JPanel();
        empty1.setBackground(Colors.getDown());
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 90;
        this.add(empty1, c);
        //shuffle----------------------------------------------
        JButton shuffle = new JButton("\uD83D\uDD00");
        shuffle.setFont(new Font("B Nahar", Font.PLAIN, 20));
        shuffle.setBorderPainted(false);
        shuffle.setFocusPainted(false);
        shuffle.setSize(1, 1);
        shuffle.setBackground(Colors.getDown());
        shuffle.setForeground(Colors.getText2());
        c.ipadx = 1;
        c.gridx = 2;
        this.add(shuffle, c);
        shuffle.addActionListener(handler);
        //previous---------------------------------------------
        JButton previous = new JButton("⏮️");
        previous.setFont(new Font("B Nahar", Font.BOLD, 20));
        previous.setBorderPainted(false);
        previous.setFocusPainted(false);
        previous.setSize(1, 1);
        previous.setBackground(Colors.getDown());
        previous.setForeground(Colors.getText2());
        c.gridx = 3;
        this.add(previous, c);
        previous.addActionListener(handler);
        //play and pause container-----------------------------
//        cardLayout = new CardLayout(1, 1);
//        playPausePanel.setLayout(cardLayout);
        //play-------------------------------------------------
//        play=new JButton();
        if (MainWindow.player == null || MainWindow.player.getPlayerStatus() == 2)
            play.setText("▶️");
        else if (MainWindow.player.getPlayerStatus() == 1)
            play.setText("\u23F8️");
        play.setFont(new Font("B Nahar", Font.BOLD, 20));
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        play.setContentAreaFilled(true);
        play.setSize(1, 1);
        play.setBackground(Colors.getDown());
        play.setForeground(Colors.getText2());
        play.addActionListener(handler);
        c.gridx = 4;
        this.add(play, c);
        //next-------------------------------------------------
        JButton next = new JButton("⏭️");
        next.setFont(new Font("B Nahar", Font.BOLD, 20));
        next.setBorderPainted(false);
        next.setFocusPainted(false);
        next.setSize(1, 1);
        next.setBackground(Colors.getDown());
        next.setForeground(Colors.getText2());
        c.gridx = 5;
        this.add(next, c);
        next.addActionListener(handler);

        //repeat-----------------------------------------------
        JButton repeat = new JButton("\uD83D\uDD01");
//        repeat.setIcon(Colors.getRepeatIcon());
        repeat.setFont(new Font("B Nahar", Font.PLAIN, 20));
        repeat.setBorderPainted(false);
        repeat.setFocusPainted(false);
        repeat.setSize(1, 1);
        repeat.setBackground(Colors.getDown());
        repeat.setForeground(Colors.getText2());
        c.gridx = 6;
        this.add(repeat, c);
        repeat.addActionListener(handler);

        //emptyPanel2------------------------------------------
        JPanel empty2 = new JPanel();
        empty2.setBackground(Colors.getDown());
        c.gridx = 7;
        c.gridy = 0;
        c.ipadx = 50;
        this.add(empty2, c);
        //emptyPanel3------------------------------------------
        JPanel empty3 = new JPanel();
        empty3.setBackground(Colors.getDown());
        c.gridx = 7;
        c.gridy = 0;
        c.ipadx = 50;
        this.add(empty3, c);
        //emptyPanel4------------------------------------------
        JPanel empty4 = new JPanel();
        empty4.setBackground(Colors.getDown());
        c.ipady = 10;
        c.gridy = 1;
        this.add(empty4, c);
        //progressDefinition-----------------------------------
        progressBar = new JProgressBar();
        if (Center.getSongName() != null) {
            song = new Mp3File(Libraries.getInformation().get(Center.getSongName()));
            progressBar.setMaximum(song.getFrameCount() * 26);
            timer = new Timer();
            task = new PlayedTimeClass(song, MainWindow.player, 0);
            timer.schedule(task, 0, 1000);
        }
        //playedTime-------------------------------------------
        String playedTimeValue = "" + (((progressBar.getValue() / 1000) / 60) < 10 ? "0" + ((progressBar.getValue() / 1000) / 60) : ((progressBar.getValue() / 1000) % 60)) + ":" + (((progressBar.getValue() / 1000) % 60) < 10 ? "0" + ((progressBar.getValue() / 1000) % 60) : ((progressBar.getValue() / 1000) % 60));
        playedTime = new JLabel(playedTimeValue);
        playedTime.setBackground(Colors.getDown());
        playedTime.setForeground(Colors.getText1());
        c.ipadx = 10;
        c.ipady = 2;
        c.gridy = 2;
        c.gridx = 0;
        this.add(playedTime, c);
        //progress---------------------------------------------
        progressBar.setBackground(Colors.getLeft());
        progressBar.setForeground(Colors.getScroll2());
        progressBar.setBorderPainted(false);
        progressBar.setPreferredSize(new Dimension(150, 2));
        c.ipady = 5;
        c.gridwidth = 7;
        c.gridx = 1;
        c.ipadx = 350;
        c.ipady = 5;
        progressBar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                MainWindow.player.getPlayer().close();
                MainWindow.player.stop();
                try {
                    MainWindow.player = null;
                    MainWindow.player = new PauseablePlayer(new FileInputStream(Libraries.getInformation().get(Center.getSongName())));
                    song = new Mp3File(Libraries.getInformation().get(Center.getSongName()));
                    progressBar.setMaximum(song.getFrameCount() * 26);
                } catch (JavaLayerException | UnsupportedTagException | InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                int mouseX = e.getX();
                int progressBarVal = (int) Math.round(((double) mouseX / (double) progressBar.getWidth()) * progressBar.getMaximum());
                progressBar.setValue(progressBarVal);
                String playedTimeValue = "" + (((progressBar.getValue() / 1000) / 60) < 10 ? "0" + ((progressBar.getValue() / 1000) / 60) : ((progressBar.getValue() / 1000) % 60)) + ":" + (((progressBar.getValue() / 1000) % 60) < 10 ? "0" + ((progressBar.getValue() / 1000) % 60) : ((progressBar.getValue() / 1000) % 60));
                playedTime.setText(playedTimeValue);
                MainWindow.player.play(progressBar.getValue() / 26, song.getFrameCount());
                try {
                    timer = new Timer();
                    task = new PlayedTimeClass(song, MainWindow.player, progressBar.getValue() / 26);
                    timer.schedule(task, 0, 1000);
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                System.out.println("progress : " + Center.getIndex());
            }
        });
        this.add(progressBar, c);
        //wholeTime---------------------------------------------
        String wholeTimeValue = "" + (((progressBar.getMaximum() / 1000) / 60) < 10 ? "0" + ((progressBar.getMaximum() / 1000) / 60) : ((progressBar.getMaximum() / 1000) % 60)) + ":" + (((progressBar.getMaximum() / 1000) % 60) < 10 ? "0" + ((progressBar.getMaximum() / 1000) % 60) : ((progressBar.getMaximum() / 1000) % 60));
        JLabel wholeTime = new JLabel(wholeTimeValue);
        wholeTime.setBackground(Colors.getDown());
        wholeTime.setForeground(Colors.getText1());
        c.ipadx = 0;
        c.ipady = 2;
        c.gridy = 2;
        c.gridx = 8;
        this.add(wholeTime, c);
    }

    public static ImageIcon resizeImageIcon(ImageIcon imageIcon, Integer width, Integer height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        graphics2D.dispose();

        return new ImageIcon(bufferedImage, imageIcon.getDescription());

    }


    public static int getIndex() {
        return index;
    }

    private class Handle implements ActionListener {
        ///////////////////////////////////////////////////////-+-*-+-\\
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.contains("▶️")) {
                play.setText("\u23F8️");
                if (index == Center.getIndex() && MainWindow.player != null) {
                    MainWindow.player.resume();
                } else {
                    try {
                        String name = Center.getSongName();
                        FileInputStream stream = new FileInputStream(Libraries.getInformation().get(name));
                        MainWindow.player = new PauseablePlayer(stream);
                        MainWindow.player.play(0, new Mp3File(Libraries.getInformation().get(name)).getFrameCount());
                        index = Center.getIndex();
                    } catch (FileNotFoundException | UnsupportedTagException | InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println("play : " + Center.getIndex());
            } else if (command.contains("\u23F8️")) {
                play.setText("▶️");
                boolean b = false;
                if (MainWindow.player != null)
                    b = MainWindow.player.pause();
                System.out.println(b);
                System.out.println("pause : " + Center.getIndex());
            }
            //shuffleAction----------------------------------------
            else if (command.contains("\uD83D\uDD00")) {
                //needShuffle = true;
            }
            //nextAction--------------------------------------------
            else if (command.contains("⏭️")) {
                if (index + 1 < Libraries.getFiles().size()) {
                    MainWindow.player.getPlayer().close();
                    MainWindow.player.stop();
                    Center.setIndex(Center.getIndex() + 1);
                    index++;
                    Center.setSongName(Center.getIndexHashmap().get(Center.getIndex()));
                    try {
                        String name = Center.getSongName();
                        FileInputStream stream = new FileInputStream(Libraries.getInformation().get(name));
                        MainWindow.player = new PauseablePlayer(stream);
                        MainWindow.player.play(0, new Mp3File(Libraries.getFilesVIAid().get(index)).getFrameCount());
                        if (play.getText().equals("▶️"))
                            play.setText("\u23F8️");
                    } catch (JavaLayerException | UnsupportedTagException | InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                //previousAction--------------------------------------------
            } else if (command.contains("⏮️")) {
                if (index > 0) {
                    MainWindow.player.getPlayer().close();
                    MainWindow.player.stop();
                    Center.setIndex(Center.getIndex() - 1);
                    index--;
                    Center.setSongName(Center.getIndexHashmap().get(Center.getIndex()));
                    try {
                        String name = Center.getSongName();
                        FileInputStream stream = new FileInputStream(Libraries.getInformation().get(name));
                        MainWindow.player = new PauseablePlayer(stream);
                        MainWindow.player.play(0, new Mp3File(Libraries.getFilesVIAid().get(index)).getFrameCount());
                        if (play.getText().equals("▶️"))
                            play.setText("\u23F8️");
                    } catch (JavaLayerException | UnsupportedTagException | InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            try {
                MainWindow.bottomRefresh();
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            }
//            } else if (command.contains("⏮️")) {
////                if (a > 0) {
////                    player.getPlayer().close();
////                    player.stop();
////                    FileInputStream stream = new FileInputStream(Libraries.getFiles().get(1));
////                    ;
////                    player = new PauseablePlayer(stream);
////                    player.play();
////                    a--;
////                    System.out.println(fileReader.getFileViaID().get(a).getName());
//            }
        }
    }
}


