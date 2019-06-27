package GUI.bottom;

import GUI.theme.Colors;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Bottom extends JPanel {
    private static JPanel playBar;
    private static JPanel songBar;
    private static JPanel volume;
    public Bottom() throws InvalidDataException, IOException, UnsupportedTagException {
        this.setBackground(Colors.getDown());
        this.setPreferredSize(new Dimension(0, 90));
        this.setLayout(new BorderLayout());
        playBar = new PlayBar();
        songBar = new SongBar();
        volume = new Volume();
        this.add(songBar,BorderLayout.WEST);
        this.add(playBar,BorderLayout.CENTER);
        this.add(volume,BorderLayout.EAST);
    }
}
