package GUI.left;

import GUI.theme.Colors;

import javax.swing.*;
import java.awt.*;

public class Left extends JPanel {
    public HomeAndSetting browseAndRadio;
    public static Libraries libraries;
    public GPlayLists gPlayLists;

    public Left() {
        this.setBackground(Colors.getLeft());
        this.setPreferredSize(new Dimension(200, 0));
        this.setLayout(new BorderLayout());
        browseAndRadio = new HomeAndSetting();
        libraries = new Libraries();
        gPlayLists = new GPlayLists();
        this.add(browseAndRadio, BorderLayout.NORTH);
        this.add(libraries, BorderLayout.CENTER);
        this.add(gPlayLists, BorderLayout.SOUTH);
    }

    public void changePlaylists(GPlayLists gPlayLists) {
        this.gPlayLists = gPlayLists;
        this.add(gPlayLists, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }

    public JPanel getgPlayLists() {
        return gPlayLists;
    }

    public JPanel getLibraries() {
        return libraries;
    }

    public JPanel getBrowseAndRadio() {
        return browseAndRadio;
    }
}

