package gamestates;

import main.GameWindow;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.Images.*;
import static utilz.Constants.InstructionsConstants.*;
import static utilz.Constants.Text.*;

public class Instructions extends BasePanel {
    private Image yellowFish;
    private Image bonusFish;
    private Image sharkImage;
    private Image pufferFishImage;

    public Instructions(GameWindow window) {
        super(window);

        JLabel title = new JLabel(TITLE_TEXT);
        title.setSize(TITLE_WIDTH, TITLE_HEIGHT);
        title.setBounds(400, 10, (TITLE_WIDTH * 3), TITLE_HEIGHT);
        Font fontTitle = new Font("Ink Free", Font.BOLD, 35);
        title.setFont(fontTitle);

        JTextArea instructions = new JTextArea(10, 20);
        instructions.setText(INSTRUCTIONS_TEXT);
        Font font = new Font("Ink Free", Font.BOLD, 18);
        instructions.setFont(font);
        instructions.setForeground(Color.WHITE);
        instructions.setBounds(165, 250, 985, 412);
        instructions.setBackground(new Color(50, 75, 150, 190));

        JButton backToMainMenu = new JButton();
        backToMainMenu.setText(FOR_BACK);
        backToMainMenu.setFont(font);
        backToMainMenu.setForeground(Color.WHITE);
        backToMainMenu.setBounds((int) (INSTRUCTIONS_WIDTH / POS) - 400, (int) (INSTRUCTIONS_HEIGHT / POS) + 120, 300, 50);
        backToMainMenu.setContentAreaFilled(false);
        backToMainMenu.addActionListener(e -> {
            window.showMainMenu();
        });

        this.add(backToMainMenu);

        JLabel sharkLabel = new JLabel("Shark");
        setJLabelFish(sharkLabel, 800);

        JLabel pufferFishLabel = new JLabel("Puffer Fish");
        setJLabelFish(pufferFishLabel, 1015);

        JLabel yellowFishLabel = new JLabel("Regular Fish");
        setJLabelFish(yellowFishLabel, 230);

        JLabel bonusFishLabel = new JLabel("Bonus Fish");
        setJLabelFish(bonusFishLabel, 490);

        this.yellowFish = new ImageIcon(YELLOW_FISH).getImage();
        this.bonusFish = new ImageIcon(BONUS_FISH).getImage();
        this.sharkImage = new ImageIcon(SHARK_IMAGE).getImage();
        this.pufferFishImage = new ImageIcon(PUFFER_FISH_IMAGE).getImage();

        this.add(instructions);
        this.add(title);
    }

    @Override
    protected void drawCustomImages(Graphics g) {
        g.drawImage(yellowFish, FISH_X, FISH_Y + 30, FISH_WIDTH + 20, FISH_HEIGHT + 40, null);
        g.drawImage(bonusFish, (FISH_X * 2) + 50, FISH_Y + 60, FISH_WIDTH + 20, FISH_HEIGHT - 18, null);
        g.drawImage(sharkImage, (FISH_X * 3) + 100, FISH_Y + 45, FISH_WIDTH + 100, FISH_HEIGHT, null);
        g.drawImage(pufferFishImage, (FISH_X * 5), FISH_Y + 45, FISH_WIDTH - 4, FISH_HEIGHT, null);
    }
}
