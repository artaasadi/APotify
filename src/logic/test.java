package logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    AdvancedPlayer player;

    public test() throws FileNotFoundException, JavaLayerException {
        player = new AdvancedPlayer(new FileInputStream(new File("Over_the_Horizon.mp3")));
    }

    public void stop() {
        player.stop();
    }

    public void play() throws JavaLayerException {
        player.play();
    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        Scanner scn = new Scanner(System.in);
        test t = new test();
        t.play();
        String str = scn.next();
        if (str.equals("stop"))
            t.stop();
    }
}