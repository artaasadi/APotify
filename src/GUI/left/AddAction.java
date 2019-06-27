//package GUI.left;
//
//import GUI.theme.Colors;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.Listener;
//
//public class AddAction implements Listener  {
//    JTextField textField;
//    JFrame addFrame;
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        addFrame = new JFrame();
//        addFrame.setLayout(new FlowLayout());
//        addFrame.setUndecorated(true);
//        addFrame.setSize(300, 100);
//        addFrame.setBackground(Colors.getLeft());
//        System.out.println("aaaa");
//        addFrame.setLocation(220, 435);
//        JTextField textField = new JTextField();
//        textField.setBackground(Colors.getDown());
//        textField.setForeground(Colors.getText2());
//        textField.setPreferredSize(new Dimension(200, 30));
//        addFrame.add(textField);
//        JButton addButton=new JButton("APPLY");
//        addFrame.add(addButton);
//        addButton.addActionListener(new AddButton());
//        addFrame.setVisible(true);
//    }
//
//}
//class AddButton extends AddAction implements Listener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
////        APlayLists.addPlaylist(textField.getText());
//        addFrame.setVisible(false);
//    }
//}
