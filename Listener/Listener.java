package Listener;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton l =(JButton) e.getSource();
        l.setBackground(Color.RED);
    }
}
