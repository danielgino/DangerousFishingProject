package enemies;
import javax.swing.*;
import java.awt.*;

import static utilz.Constants.Images.PUFFER_FISH_IMAGE;
import static utilz.Constants.FishConstants.*;

public class PufferFish extends Enemy implements EnemiesMethods {
    private Image pufferFishImage;

    public PufferFish(int x, int y) {
        super(x,y);
        this.pufferFishImage = new ImageIcon(PUFFER_FISH_IMAGE).getImage();
    }

    @Override
    public void moveRight() {

    }
    @Override
    public void moveLeft() {
        setX(getX() -5); ;
    }
    public Rectangle getPufferFishBounds() {
        return new Rectangle(getX() + 10, getY() + 17, HEIGHT_DEFAULT - 5, HEIGHT_DEFAULT - 10);
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(pufferFishImage, getX(), getY(), this);
        graphics.drawRect(getX() + 10, getY() + 17, HEIGHT_DEFAULT - 5, HEIGHT_DEFAULT - 10);
    }
}
