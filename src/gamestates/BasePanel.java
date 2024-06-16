package gamestates;

import main.GameWindow;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.Images.*;
import static utilz.Constants.InstructionsConstants.*;

public abstract class BasePanel extends JPanel {
    protected Image instructionsBackGround;
    protected GameWindow window;

    public BasePanel(GameWindow window) {
        this.window = window;
        this.instructionsBackGround = new ImageIcon(INSTRUCTIONS_BACK_GROUND).getImage();
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setJLabelFish(JLabel type, int x){
        type.setSize(SUB_TITLES_SIZE, SUB_TITLES_SIZE);
        type.setBounds(x, IMAGE_LABEL_HEIGHT, SUB_TITLES_SIZE, SUB_TITLES_SIZE);
        this.add(type);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(instructionsBackGround, 0, 0, INSTRUCTIONS_WIDTH, INSTRUCTIONS_HEIGHT, null);
        drawCustomImages(g);
    }

    protected abstract void drawCustomImages(Graphics g);
}
