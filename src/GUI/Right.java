package GUI;

import GUI.bottom.SongBar;
import GUI.theme.Colors;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Right extends JPanel {
    private static JLabel userName;
    private static JPanel songBar;
    public Right() throws InvalidDataException, IOException, UnsupportedTagException {
        this.setBackground(Colors.getRight());
        this.setPreferredSize(new Dimension(200, 0));
        //Label----------------------------------------------------
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        userName=new JLabel("aava");
        songBar=new SongBar(null);

    }
}
