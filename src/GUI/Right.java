package GUI;

import GUI.bottom.SongBar;
import GUI.centerModes.Center;
import GUI.left.Libraries;
import GUI.theme.Colors;
import GUI.theme.ManualScrollBar;
import NetWork.Client;
import NetWork.Server;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import logic.PauseablePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Right extends JPanel {
    private static JLabel userName;
    private static JPanel songBar;
    private static JList sharedList;
    private static Client client;
    private static Server server;

    public Right() throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {
        this.setBackground(Colors.getRight());
        this.setPreferredSize(new Dimension(200, 0));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //ServerButton-----------------------------------------------------
        JButton serverButton = new JButton("Server");
        serverButton.setBackground(Colors.getRight());
        serverButton.setForeground(Colors.getText1());
        serverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server = new Server(1994);
                server.start();
            }
        });
        this.add(serverButton);
        //clientButton-----------------------------------------------------
        JButton clientButton = new JButton("Client");
        clientButton.setBackground(Colors.getRight());
        clientButton.setForeground(Colors.getText1());
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainWindow.player != null) {
                    File sendingFile = Libraries.getInformation().get(Center.getIndexHashmap().get(Center.getIndex()));
                    if (MainWindow.left.gPlayLists.playLists.get(1).contain(sendingFile)) {
                        client = new Client("localhost", 1994, sendingFile.getAbsolutePath());
                    } else {
                        System.out.println("not contained in sharedPlayList");
                    }
                } else {
                    System.out.println("no music playing yet");
                }
            }
        });
        this.add(clientButton);

        //refreshButton-----------------------------------------------------
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(Colors.getRight());
        refreshButton.setForeground(Colors.getText1());
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainWindow.rightRefresh();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(refreshButton);
        //UserNameLabel----------------------------------------------------
        if (Server.getFriendName() != null) {
            userName = new JLabel(Server.getFriendName());
        } else {
            userName = new JLabel("No Connection");
        }
        userName.setBackground(Colors.getRight());
        userName.setForeground(Colors.getText2());
        this.add(userName);
        //SongBarShowingFriendsCurrentMusicPlaying-------------------------
        if (Server.getIn() != null && Server.isIsFinished()) {
            songBar = new SongBar(new File(Server.getIn()), true);
            MainWindow.player = new PauseablePlayer(new FileInputStream(new File(Server.getIn())));
        } else {
            System.out.println("ha");
            songBar = new SongBar(null, true);

        }
        songBar.setMaximumSize(new Dimension(180, 90));
        this.add(songBar);
        //sharedListShowingFriendsSharedList-------------------------------
        String s[] = {"aa", "qq", "pp", "oo", "ww", "aa", "qq", "pp", "oo", "ww", "aa", "qq", "pp", "oo", "ww", "aa", "qq", "pp", "oo", "ww", "aa", "qq", "pp", "oo", "ww", "aa", "qq", "pp", "oo", "ww"};
        sharedList = new JList(s);
        sharedList.setMaximumSize(new Dimension(180, 50));
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
