package GUI;

import GUI.bottom.SongBar;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Right extends JPanel {
    private static JLabel userName;
    private static JPanel songBar;
    private static JList sharedList;
    public Right() throws InvalidDataException, IOException, UnsupportedTagException {
        this.setBackground(Colors.getRight());
        this.setPreferredSize(new Dimension(200, 0));
        //UserNameLabel----------------------------------------------------
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        userName=new JLabel("aava");
        userName.setBackground(Colors.getRight());
        userName.setForeground(Colors.getText2());
        this.add(userName);
        //SongBarShowingFriendsCurrentMusicPlaying-------------------------
        songBar=new SongBar(null);
        songBar.setMaximumSize(new Dimension(180,90));
        this.add(songBar);
        //sharedListShowingFriendsSharedList-------------------------------
        String s[]={"aa","qq","pp","oo","ww","aa","qq","pp","oo","ww","aa","qq","pp","oo","ww","aa","qq","pp","oo","ww","aa","qq","pp","oo","ww","aa","qq","pp","oo","ww"};
        sharedList=new JList(s);
        sharedList.setMaximumSize(new Dimension(180,50));
        sharedList.setFont(new Font("A Nahar", Font.ITALIC, 15));
        sharedList.setSelectionForeground(Colors.getText2());
        sharedList.setSelectionBackground(Colors.getLeft());
        sharedList.setBackground(Colors.getLeft());
        sharedList.setForeground(Colors.getText1());
        sharedList.setFocusable(false);
        sharedList.setBorder(BorderFactory.createEmptyBorder());
        //ScrollBar----------------------------------------
        ManualScrollBar msb = new ManualScrollBar();
        JScrollPane scroll = (JScrollPane) msb.makeUI(sharedList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setBackground(Colors.getDown());
        scroll.getVerticalScrollBar().setForeground(Colors.getRight());
        this.add(scroll);
    }
}
