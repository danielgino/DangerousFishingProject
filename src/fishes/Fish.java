package fishes;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.FishConstants.*;
import static utilz.Constants.Images.*;

public class Fish extends JPanel {
    private int x;
    private int y;
    private Image fishImage;
    private Rectangle headBox;
    private boolean isCaught;

    public Fish(int x, int y) {
        this.fishImage = new ImageIcon(FISH_IMAGE).getImage();
        this.x = x;
        this.y = y;
        this.headBox = new Rectangle(this.x, this.y, FISH_BOX_SIZE, FISH_BOX_SIZE);
        this.isCaught = false;
    }
    public void setFishImage() {
        this.fishImage = new ImageIcon(FISH_CATCH).getImage();
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Rectangle getBoxBounds() {
        return new Rectangle(x + 110, y + 70, FISH_BOX_SIZE, FISH_BOX_SIZE);
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCaught() {
        return isCaught;
    }
    public boolean setCaught(boolean isCaught) {
        return this.isCaught = isCaught;
    }
    public void moveRight() {

        this.x += 5;
    }
    public void moveLeft() {
        this.x--;
    }
    public void moveUp() {
        this.y--;
    }
    public void moveDown() {
        this.y++;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fishImage, x, y, null);
        g.drawRect(x + 115, y + 70, headBox.width, headBox.height);
        g.setColor(new Color(255, 255, 255, 0));
    }

}
