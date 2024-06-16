package enemies;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.FishConstants.*;
import static utilz.Constants.Images.SHARK_IMAGE;

public class Shark extends Enemy implements EnemiesMethods {
    private Image sharkImage;
    private Rectangle bodyLimit;

    public Shark(int x, int y) {
        super(x,y);
        this.bodyLimit = new Rectangle(getX() + 20, getY() + 20, WIDTH_DEFAULT, HEIGHT_DEFAULT);
        this.sharkImage = new ImageIcon(SHARK_IMAGE).getImage();
        this.setPreferredSize(new Dimension(sharkImage.getWidth(this), sharkImage.getHeight(this)));
    }

    public Rectangle getSharkBounds() {
        return new Rectangle(getX()+ 20, getY() + 20, WIDTH_DEFAULT, HEIGHT_DEFAULT);
    }
    public void moveRight() {
        setX(getX() + 4);
    }
    @Override
    public void moveLeft() {

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.sharkImage, getX(), getY(), this);
        g.drawRect(getX() + 20, getY() + 20, bodyLimit.width, bodyLimit.height);
        g.setColor(new Color(255, 255, 255, 0));
    }
}


