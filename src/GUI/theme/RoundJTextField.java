package GUI.theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JTextField {
    private static JTextField empty=new JTextField();

    private Shape shape;
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
        RoundJTextField.super.setText("\uD83D\uDD0D search");
        RoundJTextField.super.setForeground(Colors.getText2());
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Colors.getCenter());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Colors.getTop());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
    public void setDefaultText(String s){
        super.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                RoundJTextField.super.setText("");
                RoundJTextField.super.setForeground(Colors.getText1());
            }
            public void focusLost(FocusEvent e) {
                RoundJTextField.super.setText(s);
                RoundJTextField.super.setForeground(Colors.getText2());
            }
        });
        empty.requestFocus();
    }
}