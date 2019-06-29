package GUI.bottom;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.left.Libraries;
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
        if (MainWindow.player != null)
            songBar = new SongBar(Libraries.getInformation().get(Center.getIndexHashmap().get(Center.getIndex())),false);
        else if (MainWindow.player == null)
            songBar = new SongBar(null,false);
        volume = new Volume();
        this.add(songBar, BorderLayout.WEST);
        this.add(playBar, BorderLayout.CENTER);
        this.add(volume, BorderLayout.EAST);
    }
}
