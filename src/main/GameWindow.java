package main;
import audio.Audio;
import gamestates.Instructions;
import gamestates.Option;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import static utilz.Constants.GameWindowConstants.*;
import static utilz.Constants.SaveAndLoadTxt.HIGH_SCORES_FILE;
import static utilz.Constants.Text.*;

public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    private Instructions instructions;
    private MainMenu menu;
    private Option option;
    private static Audio audioPlayer;
    private static boolean isSoundOn = true;
    private TreeMap<Integer, String> highscores;

    public GameWindow(){
       this.setTitle(FOR_INSTRUCTION_WINDOW);
       this.setLayout(new BorderLayout());

       menu = new MainMenu(this);
       add(menu,BorderLayout.CENTER);

       this.setSize(GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);

       playAudio();

       this.setLocationRelativeTo(null);
       this.setVisible(true);

        this.highscores = new TreeMap<>((a, b) -> b - a);
        loadHighScores();
    }

    public void showGamePanel() {
        getContentPane().remove(menu);
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        update();
    }

    public void showInstructions(){
        getContentPane().remove(menu);
        instructions = new Instructions(this);
        add(instructions,BorderLayout.CENTER);
        update();
    }

    public void showMainMenu(){
        getContentPane().removeAll();
        menu = new MainMenu(this);
        add(menu,BorderLayout.CENTER);
        update();
    }

    public void showOption(){
        getContentPane().removeAll();
        option = new Option(this);
        loadHighScores();
        add(option,BorderLayout.CENTER);
       update();
}

    public void playAudio(){
        try {
            audioPlayer = new Audio();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {}
    }

    public static void toggleSound() {
        if (audioPlayer != null) {
            if (isSoundOn) {
                audioPlayer.stopAudio();
                isSoundOn = false;
            } else {
                audioPlayer.startAudio();
                isSoundOn = true;
            }
        }
    }
    public boolean isIsSoundOn(){
        return isSoundOn;
    }

    public void update(){
        revalidate();
        repaint();
    }


    public void loadHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    highscores.put(Integer.parseInt(parts[0]), parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading highscores: " + e.getMessage());
        }
    }

    public void showHighScores() {
        StringBuilder sb = new StringBuilder("High-Scores:\n");
        for (var entry : highscores.entrySet()) {
            sb.append(entry.getValue()).append(": ").append(entry.getKey()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "High-Scores:", JOptionPane.INFORMATION_MESSAGE);
    }
}



