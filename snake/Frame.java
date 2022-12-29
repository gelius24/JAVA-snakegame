import javax.swing.*;
import java.awt.*;

/**
 * JFrame : layout par defaut --> BorderLayout
 */
public class Frame extends JFrame {
    JPanel contentPane = (JPanel) getContentPane();
    GamePanel gamePanel;
    InfoPanel infoPanel;

    Frame(){
        config();
        infoPanel = new InfoPanel();
        gamePanel = new GamePanel(infoPanel);
        contentPane.add(gamePanel);
        contentPane.add(infoPanel);
    }

    private void config(){
        setTitle("JEU DU SERPENT");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.BLACK);
    }
}