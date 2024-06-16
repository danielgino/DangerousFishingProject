package fishes;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.Images.*;

public class BonusFish extends Fish {
    private int x;
    private int y;
    private Image bonusImage;

    public BonusFish(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.bonusImage = new ImageIcon(BONUS_IMAGE).getImage();
    }
    @Override
    public int getX() {
        return this.x;
    }
    @Override
    public int getY() {
        return this.y;
    }
    public void setFishImage() {
        this.bonusImage = new ImageIcon(BONUS_FISH_CATCH).getImage();
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;


    }
    @Override
    public boolean setCaught(boolean isCaught) {
        return super.setCaught(isCaught);
    }
    public Rectangle getBonusBounds() {
        return new Rectangle(x, y + 35, 25, 30);

    }
    public void moveRight() {
        this.x += 5;
    }
    public void moveLeft() {
        this.x -= 10;
    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.bonusImage, x, y, this);
        g.drawRect(x, y + 35, 25, 30);
    }
}
