package enemies;
import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends JPanel {
    private Image enemy;
    private int x;
    private int y;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Image getEnemy() {
        return enemy;
    }

    public void setEnemy(Image enemy) {
        this.enemy = enemy;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
