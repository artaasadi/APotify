package logic;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        ArrayList<FileInputStream> songs = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        HashMap<Integer, FileInputStream> openedSongs = new HashMap<>();
        PauseablePlayer player;
        boolean flag = true;
        int a = scn.nextInt();
        String command = scn.next();
        //start playing
        FileInputStream fileInputStream = new FileInputStream(fileReader.getFileViaID().get(a));
        Mp3File inf = new Mp3File(fileReader.getFileViaID().get(a));
        openedSongs.put(a, fileInputStream);
        player = new PauseablePlayer(fileInputStream);
        if (command.equals("play")) {
            player.play(0, inf.getFrameCount());
            System.out.println(fileReader.getFileViaID().get(a).getName());

        }
        while (flag) {
            command = scn.next();
            try {
                // pause
                if (command.equals("pause")) {
                    boolean b;
                    b = player.pause();
                }
                //resume
                else if (command.equals("resume"))
                    player.resume();
                    //end
                else if (command.equals("exit")) {
                    player.close();
                    player.getInputStream().close();
                    flag = false;
                    System.exit(1);
                } else if (command.equals("next")) {
                    if (a + 1 < fileReader.getFileViaID().size()) {
                        player.getPlayer().close();
                        player.stop();
                        FileInputStream stream = new FileInputStream(fileReader.getFileViaID().get(a + 1));
                        player = new PauseablePlayer(stream);
                        inf = new Mp3File(fileReader.getFileViaID().get(a + 1));
                        player.play(0, inf.getFrameCount());
                        a++;
                        System.out.println(fileReader.getFileViaID().get(a).getName());
                    }
                } else if (command.equals("previous")) {
                    if (a > 0) {
                        player.getPlayer().close();
                        player.stop();
                        FileInputStream stream = new FileInputStream(fileReader.getFileViaID().get(a - 1));
                        player = new PauseablePlayer(stream);
                        inf = new Mp3File(fileReader.getFileViaID().get(a - 1));
                        player.play(0, inf.getFrameCount());
                        a--;
                        System.out.println(fileReader.getFileViaID().get(a).getName());
                    }
                } else if (command.equals("repeat")) {
                    while (true) {
                        // if (player.getPlayer().isComplete()) {
                        player.getPlayer().close();
                        player.stop();
                        FileInputStream stream = new FileInputStream(fileReader.getFileViaID().get(a));
                        player = new PauseablePlayer(stream);
                        // player.play();
                        String cmnd = scn.next();
                        if (cmnd != null)
                            break;
                        //  }
                    }
                } else if (command.equals("search")) {
                    String name = scn.next();
                    int i;
                    for (i = 0; i < fileReader.getNames().size(); i++) {
                        String tmp = fileReader.getNames().get(i);
                        if (tmp.contains(name))
                            break;
                        else System.out.println("Not Found!");
                    }
                    player.getPlayer().close();
                    player.stop();
                    FileInputStream stream = new FileInputStream(fileReader.information.get(fileReader.getNames().get(i)));
                    player = new PauseablePlayer(stream);
                    // player.play();
                } else if (command.equals("info")) {
                    Mp3File song = new Mp3File(fileReader.getFileViaID().get(a));
                    if (song.hasId3v2Tag()) {
                        ID3v2 id3v2tag = song.getId3v2Tag();
                        long timee = song.getLengthInMilliseconds();
                        // String albumArtist = id3v2tag.getAlbumArtist();
                        int time = id3v2tag.getLength();
                        int sec = (int) ((timee / 1000) % 60);
                        int min = (int) ((timee / 1000) / 60);
                        System.out.println("time = " + min + ":" + sec);
                        String date = id3v2tag.getDate();
                        String lyrics = id3v2tag.getLyrics();
                        // System.out.println("Artist:" + albumArtist);
                        System.out.println("Duration:" + time);
                        System.out.println("Date:" + date);
                        System.out.println("Lyrics:" + lyrics);

                    }
                    if (song.hasId3v1Tag()) {
                        ID3v1 id3v1 = song.getId3v1Tag();
                        String title = id3v1.getTitle();
                        String album = id3v1.getAlbum();
                        String artist = id3v1.getArtist();
                        System.out.println("Artist:" + artist);
                        System.out.println("Albums:" + album);
                        System.out.println("Title:" + title);
//                        byte[] imageData = id3v2tag.getAlbumImage();
//                        //converting the bytes to an image
//                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                    }

                } else if (command.equals("shuffle")) {
                    player.getPlayer().close();
                    player.stop();
                    FileInputStream stream = new FileInputStream(fileReader.getFileViaID().get(new Random(fileReader.getFiles().size())));
                    player = new PauseablePlayer(stream);
                    //  player.play();
                } else if (command.equals("skip")) {
                    int frame = scn.nextInt();
                    player.getPlayer().close();
                    player.stop();
                    fileInputStream = new FileInputStream(fileReader.getFileViaID().get(a));
                    inf = new Mp3File(fileReader.getFileViaID().get(a));
                    player = new PauseablePlayer(fileInputStream);
                    player.play(frame, inf.getFrameCount());

                }
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}










