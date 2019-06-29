package logic;

import GUI.bottom.PlayBar;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.util.TimerTask;
import java.io.*;

public class PlayedTimeClass extends TimerTask {
    private Mp3File song;
    private PauseablePlayer player;
    private int start;

    public PlayedTimeClass(Mp3File song, PauseablePlayer player, int start) throws InvalidDataException, IOException, UnsupportedTagException {
        this.song = song;
        this.player = player;
        this.start = start;
    }


    @Override
    public void run() {
        // player.play(start, song.getFrameCount());
        String value = String.format("%02d : %02d\n", (((player.getPlayer().getAudio().getPosition() + (start * 26)) / 1000) / 60), (((player.getPlayer().getAudio().getPosition() + (start * 26)) / 1000) % 60));
        PlayBar.progressBar.setValue(((player.getPlayer().getAudio().getPosition() + (start * 26))));
        PlayBar.playedTime.setText(value);
    }
}
