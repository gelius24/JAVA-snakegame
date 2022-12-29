import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    int score = 0;
    JLabel myLabel = new JLabel("Score :" + score);
    JLabel label1 = new JLabel("Click G to add grid");

    InfoPanel(){
        setPreferredSize(new Dimension(500, 80));
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.black);
        myLabel.setForeground(Color.white);
        label1.setForeground(Color.white);
        add(myLabel);
        add(label1);
    }
}