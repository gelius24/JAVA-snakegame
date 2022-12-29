import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    int UNITE = 20;
    int LARGEUR = 500; int HAUTEUR = 500;
    Snake snake = new Snake(LARGEUR, HAUTEUR, UNITE);
    Apple apple = new Apple(LARGEUR, HAUTEUR, UNITE);
    Timer timer;
    Ecouteur ecouteur = new Ecouteur();
    boolean playing = false;
    InfoPanel infoPanel;
    boolean gridOn = false;

    GamePanel(InfoPanel infoPanel){
        this.infoPanel = infoPanel;
        setPreferredSize(new Dimension(LARGEUR,HAUTEUR));
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(ecouteur);
    }

    public void play(){
        timer = new Timer(90, e -> {
            checkCollision();
            snake.move();
            repaint();
        });
        timer.start();
    }

    public void gameOver(){
        snake.newSnake(LARGEUR, HAUTEUR);
        apple.newApple(LARGEUR, HAUTEUR);
        infoPanel.score = 0;
        infoPanel.myLabel.setText("Score :" + infoPanel.score);
        playing = false;
        timer.stop();
    }

    void checkCollision(){
        // serpent - pomme
        if (snake.getX() == apple.getX() && snake.getY() == apple.getY()){
            snake.eat();
            apple.newApple(LARGEUR, HAUTEUR);
            infoPanel.myLabel.setText("Score :" + ++infoPanel.score);
        }
        // serpent - terrain
        if (snake.getX() < 0 || snake.getX() > LARGEUR || snake.getY() < 0 || snake.getY() > HAUTEUR)
            gameOver();
        // serpent - serpent
        if (snake.getBodySize() > 1){
            for (int i = 1; i < snake.getBodySize(); i++){
                if (snake.getX() == snake.x[i] && snake.getY() == snake.y[i])
                    gameOver();
            }
        }
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,LARGEUR,HAUTEUR);
        if (gridOn)
            drawGrid(g);
        snake.drawSnake(g);
        apple.drawApple(g);
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.WHITE);
        for (int i = 0; i < LARGEUR/UNITE; i++)
            g.drawLine(i*UNITE,0, i*UNITE, HAUTEUR);
        for (int i = 0; i < HAUTEUR/UNITE; i++)
            g.drawLine(0,i*UNITE, LARGEUR, i*UNITE);
    }

    public class Ecouteur extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (!playing) {
                playing = true;
                play();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                snake.setDirection('r');
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                snake.setDirection('l');
            if (e.getKeyCode() == KeyEvent.VK_UP)
                snake.setDirection('u');
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                snake.setDirection('d');
            if (e.getKeyCode() == KeyEvent.VK_G){
                if (!gridOn)
                    gridOn = true;
                else
                    gridOn = false;
            }

        }
    }
}