package GUI.theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Colors {
    private static Color text1, text2, top, down, center, left, right, scroll0, scroll1, scroll2;
    private static BufferedImage wallpaper, songImage, volumeImage;
    private static ImageIcon shuffleIcon, previousIcon, playIcon, nextIcon, repeatIcon;

    public static void setColors(String darkness, String color) throws IOException {
        //Dark or Light----------------------------------------------------
        if (darkness.equals("dark")) {
            top = new Color(40, 40, 40);
            down = new Color(40, 40, 40);
            left = new Color(30, 30, 30);
            right = new Color(30, 30, 30);
            center = new Color(110, 110, 110);
            text1 = new Color(180, 180, 180);
            wallpaper = ImageIO.read(new File("resources/dark/wallpaper.jpg"));
            shuffleIcon = new ImageIcon("resources/dark/shuffle.png");
            previousIcon = new ImageIcon("resources/dark/previous.png");
            playIcon = new ImageIcon("resources/dark/play.png");
            nextIcon = new ImageIcon("resources/dark/next.png");
            repeatIcon = new ImageIcon("resources/dark/repeat.png");
            volumeImage = ImageIO.read(new File("resources/dark/volume.png"));
            songImage = ImageIO.read(new File("resources/dark/Unknown.png"));
        } else if (darkness.equals("light")) {
            top = new Color(196, 205, 207);
            down = new Color(196, 205, 207);
            left = new Color(214, 220, 205);
            right = new Color(214, 220, 205);
            center = new Color(200, 200, 200);
            text1 = new Color(40, 40, 40);
            wallpaper = ImageIO.read(new File("resources/light/wallpaper.jpg"));
            shuffleIcon = new ImageIcon("resources/light/shuffle.png");
            previousIcon = new ImageIcon("resources/light/previous.png");
            playIcon = new ImageIcon("resources/light/play.png");
            nextIcon = new ImageIcon("resources/light/next.png");
            repeatIcon = new ImageIcon("resources/light/repeat.png");
            volumeImage = ImageIO.read(new File("resources/light/volume.png"));
            songImage = ImageIO.read(new File("resources/light/Unknown.png"));
        }
        //Color-------------------------------------------------------------
        if (color.equals("green")) {
            text2 = new Color(125, 160, 124);
            scroll2 = new Color(125, 160, 124);
            scroll1 = new Color(125, 160, 124, 150);
            scroll0 = new Color(125, 160, 124, 100);
        } else if (color.equals("blue")) {
            text2 = new Color(130, 180, 210);
            scroll2 = new Color(130, 180, 210);
            scroll1 = new Color(130, 180, 210, 200);
            scroll0 = new Color(130, 180, 210, 170);
        } else if (color.equals("gold")) {
            text2 = new Color(200, 170, 130);
            scroll2 = new Color(200, 170, 130);
            scroll1 = new Color(200, 170, 130, 200);
            scroll0 = new Color(200, 170, 130, 170);
        } else if (color.equals("rose gold")) {
            text2 = new Color(140, 80, 83);
            scroll2 = new Color(140, 80, 83);
            scroll1 = new Color(140, 80, 83, 200);
            scroll0 = new Color(140, 80, 83, 170);
        } else if (color.equals("purple")) {
            text2 = new Color(140, 100, 140);
            scroll2 = new Color(140, 100, 140);
            scroll1 = new Color(140, 100, 140, 200);
            scroll0 = new Color(140, 100, 140, 170);
        }
    }

    public static Color getCenter() {
        return center;
    }

    public static Color getDown() {
        return down;
    }

    public static Color getLeft() {
        return left;
    }

    public static Color getRight() {
        return right;
    }

    public static Color getText1() {
        return text1;
    }

    public static Color getText2() {
        return text2;
    }

    public static Color getTop() {
        return top;
    }

    public static Color getScroll0() {
        return scroll0;
    }

    public static Color getScroll1() {
        return scroll1;
    }

    public static Color getScroll2() {
        return scroll2;
    }

    public static BufferedImage getWallpaper() {
        return wallpaper;
    }

    public static BufferedImage getSongImage() {
        return songImage;
    }

    public static ImageIcon getShuffleIcon() {
        return shuffleIcon;
    }

    public static ImageIcon getPreviousIcon() {
        return previousIcon;
    }

    public static ImageIcon getPlayIcon() {
        return playIcon;
    }

    public static ImageIcon getNextIcon() {
        return nextIcon;
    }

    public static ImageIcon getRepeatIcon() {
        return repeatIcon;
    }

    public static BufferedImage getVolumeImage() {
        return volumeImage;
    }

}
