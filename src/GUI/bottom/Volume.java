package GUI.bottom;

import GUI.theme.Colors;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Volume extends JPanel {
    public Volume(){
        this.setBackground(Colors.getDown());
        this.setPreferredSize(new Dimension(250,0));
        this.setLayout(new BorderLayout());
        //Slider-----------------------------------------------------------------------
        JSlider volumeSlider=new JSlider(JSlider.HORIZONTAL,0,100,30);
        volumeSlider.setBackground(Colors.getDown());
        volumeSlider.setForeground(Colors.getScroll1());
        volumeSlider.setValue((int) (Audio.getMasterOutputVolume()*1.00));
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Audio.setMasterOutputVolume((float)volumeSlider.getValue()/100);
            }
        });
        this.add(volumeSlider,BorderLayout.CENTER);
        //volume image-----------------------------------------------------------------
        JLabel volumeLabel=new JLabel("\uD83D\uDD0A");
        volumeLabel.setFont(new Font("B Nahar",Font.PLAIN,30));
        volumeLabel.setForeground(Colors.getText2());
        volumeLabel.setBackground(Colors.getDown());
        this.add(volumeLabel,BorderLayout.WEST);
    }
}
