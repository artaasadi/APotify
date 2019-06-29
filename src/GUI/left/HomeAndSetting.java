package GUI.left;

import GUI.MainWindow;
import GUI.centerModes.Center;
import GUI.theme.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeAndSetting extends JPanel {
    public HomeAndSetting() {
        this.setBackground(Colors.getLeft());
        this.setPreferredSize(new Dimension(0, 120));
        //List-------------------------------------------------------------
        String s[] = {"\uD83C\uDFE0 Home", "⚙️ Settings"};
        JList list = new JList(s);
        list.setFont(new Font("A Nahar", Font.ITALIC & Font.BOLD, 20));
        list.setSelectionForeground(Colors.getText2());
        list.setFocusable(false);
        list.setSelectionBackground(Colors.getLeft());
        list.setBackground(Colors.getLeft());
        list.setForeground(Colors.getText1());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    if (o.toString() == "\uD83C\uDFE0 Home") {
                        MainWindow.changeCenter(new Center());
                        System.out.println("home");
                    }else if (o.toString()=="⚙️ Settings"){
                        MainWindow.changeCenter(new Center((float)1));
                    }
                }
            }
        });
        this.add(list);
    }
}
