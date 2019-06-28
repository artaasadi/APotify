package GUI;

import GUI.left.Libraries;
import GUI.theme.Colors;
import GUI.theme.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Top extends JPanel {
    static JTextField search;
    private File found;

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

    public File getFound() {
        return found;
    }

    private class Handle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = search.getText();
            String tmp = null;
            for (int i = 0; i < Libraries.getNames().size(); i++) {
                tmp = Libraries.getNames().get(i);
                if (tmp.contains(name))
                    break;
            }
            if (found != null) {
                found = Libraries.getInformation().get(tmp);
                System.out.println(found.getName());
            }
        }
    }
}