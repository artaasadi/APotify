package GUI.left;

import GUI.theme.Colors;

import javax.swing.*;
import java.awt.*;

public class HomeAndSetting extends JPanel {
    public HomeAndSetting(){
        this.setBackground(Colors.getLeft());
        this.setPreferredSize(new Dimension(0, 120));
        //List
        String s[]={"Home","Settings"};
//        String s[]={"\uD83C\uDFE0 Home","⚙️ Settings"};
        JList list=new JList(s);
        list.setFont(new Font("A Nahar", Font.ITALIC & Font.BOLD, 20));
        list.setSelectionForeground(Colors.getText2());
        list.setFocusable(false);
        list.setSelectionBackground(Colors.getLeft());
        list.setBackground(Colors.getLeft());
        list.setForeground(Colors.getText1());
        this.add(list);
    }
}
