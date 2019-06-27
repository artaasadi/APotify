package GUI;

import GUI.theme.Colors;
import GUI.theme.RoundJTextField;

import java.awt.*;
import javax.swing.*;

public class Top extends JPanel {
    public Top(){
        //TopBorder
        this.setBackground(Colors.getTop());
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(0,50));
        //search------------------------------------------------------------
        JPanel left=new JPanel();
        left.setBackground(Colors.getTop());
        JTextField s=new JTextField();
        s.setPreferredSize(new Dimension(0,0));
        left.add(s);
        JTextField search=new RoundJTextField(15);
        ((RoundJTextField) search).setDefaultText("\uD83D\uDD0D search");
        left.add(search);
        this.add(left);
        //right panel-------------------------------------------------------
        JPanel panel=new JPanel();
        JLabel userName=new JLabel("arta.2000");
        userName.setBackground(Colors.getTop());
        userName.setForeground(Colors.getText2());
        userName.setFont(new Font("Agency FB",Font.PLAIN,20));
        panel.add(userName);
        panel.setBackground(Colors.getTop());
        this.add(panel);
    }
}