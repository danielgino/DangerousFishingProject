package objects;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.FishingRodConstants.*;
import static utilz.Constants.Images.HOOK_PAINT;


public class FishingRod extends JPanel {
    private int lineLength;
    private Image hookPhoto;

    public FishingRod() {
        this.hookPhoto=new ImageIcon(HOOK_PAINT).getImage();
        this.lineLength = 80;
    }

    public void moveUp() {
        if (lineLength - 18 >= MIN_LINE_LENGTH) {
            lineLength -= 18;
        }
    }

   public void moveDown() {
       if (getHookY() <= MAX_LINE_LENGTH) {
           lineLength += 18;
       }
    }

    public Rectangle getHookBounds() {
        return new Rectangle(getHookX(), getHookY(), HOOK_DIAMETER, HOOK_DIAMETER);
    }

    public Rectangle getFishingLineBounds() {
        return new Rectangle(getHookX() + 5, getHookY() - 650, 1, 650);
    }

    public int getHookY() {
        return ROD_Y - 50 + lineLength - HOOK_DIAMETER / 2;
    }

    public int getHookX() {
        return ROD_X - HOOK_DIAMETER / 2;
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(hookPhoto,ROD_X - 11 , ROD_Y - 55 + lineLength - HOOK_DIAMETER / 2, this);
        graphics.drawLine(ROD_X,ROD_Y-50,ROD_X,ROD_Y+lineLength-50);
        graphics.setColor(new Color(255, 255, 255, 0)); //המלבן של החכה שקוף
        graphics.drawRect(getHookX() + 5, getHookY() - 650, 1, 650);
    }
}
