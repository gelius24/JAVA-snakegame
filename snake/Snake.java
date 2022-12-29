import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake extends JPanel {
    Random rand = new Random();
    int[] x, y;
    int bodySize;
    int UNITE;
    char direction;

    Snake(int largeur, int hauteur, int unite){
        UNITE = unite;
        x = new int[largeur];
        y = new int[hauteur];
        newSnake(largeur, hauteur);
    }

    void newSnake(int l, int h){
        direction = ' ';
        bodySize = 1;
        x[0] = rand.nextInt(l/UNITE) * UNITE;
        y[0] = rand.nextInt(h/UNITE) * UNITE;
    }

    void move(){
        if (bodySize > 1){
            for (int i = bodySize; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
        }
        if (direction == 'r')
            x[0] += UNITE;
        if (direction == 'l')
            x[0] -= UNITE;
        if (direction == 'u')
            y[0] -= UNITE;
        if (direction == 'd')
            y[0] += UNITE;
    }

    public void eat(){
        bodySize ++;
    }

    public void drawSnake(Graphics g){
        g.setColor(Color.green);
        for (int i = 0; i < bodySize; i++)
            g.fillRect(x[i], y[i], UNITE,UNITE);
    }

    public void setDirection(char c){ direction = c; }

    public int getX() { return x[0]; }
    public int getY() { return y[0]; }
    public int getBodySize() { return bodySize; }
}