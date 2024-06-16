package objects;
import javax.swing.*;
import java.awt.*;

import static utilz.Constants.GamePanelConstant.HEART_HEIGHT;
import static utilz.Constants.GamePanelConstant.HEART_WIDTH;
import static utilz.Constants.Images.HEART;

public class Heart {
    private int x, y;
    private Image heartImage;
    private boolean caught;

    public Heart(int x, int y) {
        this.x = x;
        this.y = y;
        this.heartImage = new ImageIcon(HEART).getImage();
        this.caught = false;
    }

    public void moveLeft() {
        this.x -= 5;
    }

    // הזזת הלב באלכסון
    public void moveDiagonally() {
        this.x -= 5;
        this.y -= 1;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getHeartBounds() {
        return new Rectangle(this.x, this.y, HEART_WIDTH, HEART_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(heartImage, x, y, null);
    }
}
