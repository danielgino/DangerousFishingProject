package gamestates;

import main.GameWindow;
import javax.swing.*;
import java.awt.*;
import static utilz.Constants.Images.*;
import static utilz.Constants.InstructionsConstants.*;
import static utilz.Constants.Text.*;

public class Option extends BasePanel {
    private Image sharkImage;

    public Option(GameWindow window) {
        super(window);
        Font font = new Font("Ink Free", Font.BOLD, 20);

        JLabel title = new JLabel(TITLE_TEXT_OPTION);
        JLabel title2 = new JLabel(UNDER_TITLE_TEXT);

        title.setSize(TITLE_WIDTH, TITLE_HEIGHT);
        title.setBounds(400, 10, (TITLE_WIDTH * 3), TITLE_HEIGHT);
        Font fontTitle = new Font("Ink Free", Font.BOLD, 35);
        title.setFont(fontTitle);

        title2.setSize(TITLE_WIDTH / 2, TITLE_HEIGHT / 2);
        title2.setBounds(400, 50, (TITLE_WIDTH * 3), TITLE_HEIGHT);
        title2.setFont(font);

        JTextArea underTitle = new JTextArea(10, 20);
        underTitle.setBounds(165, 250, 985, 412);
        underTitle.setBackground(new Color(50, 75, 150, 190));

        JButton soundButton = new JButton("SOUND");
        soundButton.setFont(font);
        soundButton.setForeground(Color.BLACK);
        soundButton.setBackground(Color.WHITE);
        soundButton.setOpaque(true);
        soundButton.setBounds(520, 300, 250, 40);
        soundButton.addActionListener(e -> {
            GameWindow.toggleSound();
            soundButton.setText(window.isIsSoundOn() ? "SOUND OFF" : "SOUND ON");
        });

        this.add(soundButton);
        JButton highScoresButton = new JButton("HIGH-SCORES");
        highScoresButton.setFont(font);
        highScoresButton.setForeground(Color.BLACK);
        highScoresButton.setBackground(Color.WHITE);
        highScoresButton.setOpaque(true);
        highScoresButton.setBounds(520, 400, 250, 40);
        highScoresButton.addActionListener(e -> {
            window.showHighScores();
        });

        this.add(highScoresButton);

        JButton backToMainMenu = new JButton(FOR_BACK);
        backToMainMenu.setFont(font);
        backToMainMenu.setForeground(Color.BLACK);
        backToMainMenu.setBackground(Color.WHITE);
        backToMainMenu.setOpaque(true);
        backToMainMenu.setBounds(520, 500, 250, 40);
        backToMainMenu.addActionListener(e -> {
            window.showMainMenu();
        });

        this.add(backToMainMenu);

        JLabel sharkLabel = new JLabel();
        setJLabelFish(sharkLabel, 800);

        this.sharkImage = new ImageIcon(SHARK_IMAGE).getImage();

        this.add(title2);
        this.add(underTitle);
        this.add(title);
    }

    @Override
    protected void drawCustomImages(Graphics g) {
        int[] sharkPositions = {1, 2, 3, 3, 3}; // מערך שמכיל את המקדמים לכפל ה-FISH_X

        for (int i = 0; i < sharkPositions.length; i++) {
            int xOffset = (i < 3) ? -30 : 170 + (i - 3) * 200;
            int xPosition = (FISH_X * sharkPositions[i]) + xOffset;
            g.drawImage(sharkImage, xPosition, FISH_Y + 70, FISH_WIDTH + 100, FISH_HEIGHT, null);
        }
    }
}