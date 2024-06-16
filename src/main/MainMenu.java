package main;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static utilz.Constants.Images.MAIN_MENU_BACK_GROUND;
import static utilz.Constants.MainMenuConstants.*;

public class MainMenu extends JPanel {
    private Image backgroundImage;
    private GameWindow window;

    public MainMenu(GameWindow window){

        this.window = window;
        this.backgroundImage = new ImageIcon(MAIN_MENU_BACK_GROUND).getImage();

        this.setSize(WIDTH,HEIGHT);
        this.setLayout(null);
        this.setVisible(true);

        JButton start = new JButton("");
        start.setContentAreaFilled(false);
        start.addActionListener(e->  {
            window.showGamePanel();
        });

        JButton instructions = new JButton("");
        instructions.setContentAreaFilled(false);

        instructions.addActionListener(e ->{
            window.showInstructions();
        });

        JButton options = new JButton("");
        options.setContentAreaFilled(false);

        options.addActionListener(e ->{
            window.showOption();
        });

        JButton exit = new JButton("");
        exit.addActionListener(e->  {
            System.exit(0);
        });

        exit.setContentAreaFilled(false);

        start.setBounds(BUTTON_CENTER, 187, BUTTON_WIDTH, BUTTON_HEIGHT);
        instructions.setBounds(BUTTON_CENTER, 312, BUTTON_WIDTH, BUTTON_HEIGHT);
        options.setBounds(BUTTON_CENTER, 435, BUTTON_WIDTH, BUTTON_HEIGHT);
        exit.setBounds(BUTTON_CENTER, 562, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(start);
        this.add(instructions);
        this.add(options);
        this.add(exit);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }
}
