package GUI;

import GUI.centerModes.Center;
import GUI.left.Libraries;
import GUI.theme.Colors;
import GUI.theme.RoundJTextField;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Top extends JPanel {
    static JTextField search;
    private ArrayList<File> founds =new ArrayList<>();

    public Top() {
        //TopBorder
        this.setBackground(Colors.getTop());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(0, 50));
        //search------------------------------------------------------------
        JPanel left = new JPanel();
        left.setBackground(Colors.getTop());
        JTextField s = new JTextField();
        s.setPreferredSize(new Dimension(0, 0));
        left.add(s);
        search = new RoundJTextField(15);
        ((RoundJTextField) search).setDefaultText("\uD83D\uDD0D search");
        search.addActionListener(new Handle());
        left.add(search);
        this.add(left);
        //right panel-------------------------------------------------------
        JPanel panel = new JPanel();
        JLabel userName = new JLabel("arta.2000");
        userName.setBackground(Colors.getTop());
        userName.setForeground(Colors.getText2());
        userName.setFont(new Font("Agency FB", Font.PLAIN, 20));
        panel.add(userName);
        panel.setBackground(Colors.getTop());
        this.add(panel);
    }

    /**
     * gives founds
     * @return founds
     */
    public ArrayList<File> getFounds() {
        return founds;
    }

    /**
     * adding ActionListener for searchField
     */
    private class Handle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = search.getText();
            String tmp = null;
            for (int i = 0; i < Libraries.getNames().size(); i++) {
                tmp = Libraries.getNames().get(i);
                if (tmp.contains(name))
                    founds.add(Libraries.getInformation().get(tmp));
            }
            try {
                MainWindow.changeCenter(new Center((double)1));
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            }
        }
    }
}
