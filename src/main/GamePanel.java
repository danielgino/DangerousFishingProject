package main;
import enemies.PufferFish;
import enemies.Shark;
import fishes.BonusFish;
import fishes.Fish;
import level.Levels;
import objects.FishingRod;
import objects.Heart;
import objects.KeyboardListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.TreeMap;

import static utilz.Constants.GamePanelConstant.*;
import static utilz.Constants.Images.*;
import static utilz.Constants.LevelConstants.random;
import static utilz.Constants.SaveAndLoadTxt.HIGH_SCORES_FILE;
import static utilz.Constants.Text.GAME_OVER_TEXT;

public class GamePanel extends JPanel {
    private Image backgroundImage;
    private FishingRod rod;
    private ArrayList<Shark> sharks, toRemoveSharks;
    private ArrayList<Fish> fishes, flippedFishes;
    private ArrayList<PufferFish> pufferFishes, toRemovePufferFish;
    private ArrayList<BonusFish> bonusFish;
    private ArrayList<Heart> hearts, toRemoveHearts;
    private JLabel scoreLabel, leftHeart, middleHeart, rightHeart;
    private int score, currentLevel, lives;
    private boolean getInBag, gameRunning;
    private Levels levels;
    private TreeMap<Integer, String> highScores;


    public GamePanel() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.backgroundImage = new ImageIcon(BACK_GROUND).getImage();
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        scoreLabel();
        life();

        this.rod = new FishingRod();
        this.add(this.rod);

        this.sharks = new ArrayList<>();
        this.toRemoveSharks = new ArrayList<>();
        this.pufferFishes = new ArrayList<>();
        this.toRemovePufferFish = new ArrayList<>();
        this.fishes = new ArrayList<>();
        this.flippedFishes = new ArrayList<>();
        this.bonusFish = new ArrayList<>();
        this.hearts = new ArrayList<>();
        this.toRemoveHearts = new ArrayList<>();

        this.currentLevel = 1;
        this.lives = 3;
        this.getInBag = false;
        this.gameRunning = true;

        this.levels = new Levels(fishes, bonusFish, sharks, pufferFishes);
        levels.levelHardness(1, 1);

        this.addKeyListener(new KeyboardListener(this, this.rod, null, this.fishes, this.bonusFish, null));
        this.setFocusable(true);

        this.highScores = new TreeMap<>((a, b) -> b - a);
        loadHighScores();

        new Thread(() -> {
            while (gameRunning) {
                addHeartForLevel(currentLevel);sleep(40000);}}).start();

        this.mainGameLoop();
        this.setVisible(true);

    }

    private void addHeartForLevel(int level) {
        if (hearts.size() < 2) {
            int startY = random.nextInt(400, 550);
            Heart newHeart = new Heart(HEART_START + 200, startY);
            hearts.add(newHeart);
        }
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (gameRunning) {
                moveAndCheckFishes();
                moveAndCheckBonusFishes();
                moveAndCheckPufferFishes();
                moveAndCheckSharks();
                moveAndCheckHearts();

                if (this.lives == 0) {
                    gameRunning = false;
                    showGameOver();
                    break;
                }
                sleep(50);
                repaint();
            }
        }).start();
    }

    private void moveAndCheckFishes() {
        for (Fish currentFish : fishes) {
            currentFish.moveRight();
            if (checkCollision(currentFish, rod) && !this.getInBag)
                currentFish.setCaught(true);
            if (currentFish.isCaught() && !this.getInBag)
                currentFish.setPosition(rod.getHookX() - currentFish.getX() / 7, rod.getHookY() - currentFish.getY() / 7);
        }
    }

    private void moveAndCheckBonusFishes() {
        for (BonusFish currentBonusFish : this.bonusFish) {
            if (!currentBonusFish.isCaught())
                currentBonusFish.moveLeft();
            if (checkCollisionWithBonus(currentBonusFish, rod) && !this.getInBag)
                currentBonusFish.setCaught(true);
            if (currentBonusFish.isCaught() && !this.getInBag)
                currentBonusFish.setPosition(rod.getHookX() - currentBonusFish.getX() / 20, rod.getHookY() - currentBonusFish.getY() / 25);
        }
    }

    private void moveAndCheckPufferFishes() {
        for (PufferFish currentPufferFish : pufferFishes) {
            currentPufferFish.moveLeft();
            if (collisionWithPufferFish(currentPufferFish, rod)) {
                checkLives(lives);
                toRemovePufferFish.add(currentPufferFish);
            }
        }
        removePufferFish();
    }

    private void moveAndCheckSharks() {
        for (Shark currentShark : sharks) {
            currentShark.moveRight();
            if (collisionWithShark(currentShark, rod)) {
                checkLives(lives);
                toRemoveSharks.add(currentShark);
            }
        }
        removeSharksFromList();
    }

    private void moveAndCheckHearts() {
        for (Heart currentHeart : hearts) {
            currentHeart.moveLeft();
            if (collisionWithHeart(currentHeart, rod) && !currentHeart.isCaught()) {
                currentHeart.setCaught(true);
                addLife();
                toRemoveHearts.add(currentHeart);
            }
        }
        removeHeartsFromList();
    }

    public boolean collisionWithHeart(Heart heart, FishingRod rod) {
        return rod.getHookBounds().intersects(heart.getHeartBounds());
    }

    public void addLife() {
        if (lives < 3) { // הגבלת מספר החיים ל-3
            lives++;
            updateLivesDisplay();
        }
    }

    public void removeHeartsFromList() {
        hearts.removeAll(toRemoveHearts);
        toRemoveHearts.clear();
    }

    public void updateLivesDisplay() {
        switch (lives) {
            case 1 -> {
                middleHeart.setVisible(false);
                rightHeart.setVisible(false);
                leftHeart.setVisible(true);
            }
            case 2 -> {
                rightHeart.setVisible(false);
                middleHeart.setVisible(true);
                leftHeart.setVisible(true);
            }
            case 3 -> {
                rightHeart.setVisible(true);
                middleHeart.setVisible(true);
                leftHeart.setVisible(true);
            }
        }
    }

    public boolean checkCollision(Fish fish, FishingRod rod) {
        if (rod.getHookBounds().intersects(fish.getBoxBounds())) {
            fish.setFishImage();
            fish.setCaught(true);
            return true;
        }
        return false;
    }

    public boolean collisionWithShark(Shark shark, FishingRod rod) {
        if (rod.getHookBounds().intersects(shark.getSharkBounds()) || rod.getFishingLineBounds().intersects(shark.getSharkBounds())) {
            lives--;
            return true;
        }
        return false;
    }

    public boolean collisionWithPufferFish(PufferFish pufferFish, FishingRod rod) {
        if (rod.getHookBounds().intersects(pufferFish.getPufferFishBounds()) || rod.getFishingLineBounds().intersects(pufferFish.getPufferFishBounds())) {
            lives--;
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithBonus(BonusFish bonus, FishingRod rod) {
        if (rod.getHookBounds().intersects(bonus.getBonusBounds())) {
            bonus.setFishImage();
            bonus.setCaught(true);
            return true;
        }
        return false;
    }

    public void removeSharksFromList() {
        sharks.removeAll(toRemoveSharks);
        toRemoveSharks.clear();
    }

    public void removePufferFish() {
        pufferFishes.removeAll(toRemovePufferFish);
        toRemovePufferFish.clear();
    }

    public void checkLives(int lives) {
        switch (lives) {
            case 1 -> middleHeart.setVisible(false);
            case 2 -> rightHeart.setVisible(false);
            case 3 -> leftHeart.setVisible(false);
        }
    }

    public void life() {
        leftHeart = new JLabel(new ImageIcon(HEART));
        leftHeart.setBounds(HEART_X, HEART_Y, HEART_WIDTH, HEART_HEIGHT);
        this.add(leftHeart);

        middleHeart = new JLabel(new ImageIcon(HEART));
        middleHeart.setBounds((HEART_X * 2) + 5, HEART_Y, HEART_WIDTH, HEART_HEIGHT);
        this.add(middleHeart);

        rightHeart = new JLabel(new ImageIcon(HEART));
        rightHeart.setBounds((HEART_X * 3) + 10, HEART_Y, HEART_WIDTH, HEART_HEIGHT);
        this.add(rightHeart);
    }

    public void checkDifficult(int score) {
        int newLevel = currentLevel;
        int toDived = 1;
        if (score == 0) {
            newLevel = 1;
        } else if (score >= 8 && score <= 15) {
            newLevel = 2;
        } else if (score >= 16 && score <= 24) {
            newLevel = 3;
            toDived = 2;
        } else if (score >= 25 && score <= 35) {
            newLevel = 4;
            toDived = 3;
        }

        if (newLevel != currentLevel) {
            currentLevel = newLevel;
            levels.levelHardness(newLevel, toDived);
            System.out.println("Level " + newLevel + " Begins!");
            displayLevelLabel(newLevel);

        }
    }
    public void displayLevelLabel(int level) {
        JLabel levelLabel = new JLabel("Level " + level+ " Begins!");
        levelLabel.setFont(new Font("Ink Free", Font.BOLD, 48));
        levelLabel.setForeground(Color.RED);
        levelLabel.setBounds(GAME_PANEL_WIDTH /2-170, GAME_PANEL_HEIGHT / 2 - 50, 400, 100);
        this.add(levelLabel);
        this.repaint();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.remove(levelLabel);
            this.repaint();
        }).start();
}
    public void scoreLabel() {
        this.scoreLabel = new JLabel("" + this.score);
        this.scoreLabel.setSize(SCORE_LABEL_WIDTH, SCORE_LABEL_HEIGHT);
        this.scoreLabel.setBounds(245, -30, SCORE_LABEL_WIDTH, SCORE_LABEL_HEIGHT);
        Font labelFont = new Font("Arial", Font.BOLD, 42);
        this.scoreLabel.setFont(labelFont);
        this.add(scoreLabel);
    }

    public void addScore(int num) {
        score += num;
        this.scoreLabel.setText("" + this.score);
        checkDifficult(score);
    }

    public void removeFish(Fish fish) {
        this.fishes.remove(fish);
        this.getInBag = false;
    }

    public void removeBonusFish(BonusFish fish) {
        this.bonusFish.remove(fish);
        this.getInBag = false;
    }

    public void paintComponent(Graphics graphics) throws ConcurrentModificationException {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        this.rod.paint(graphics);
        try {
            for (Shark sharks : this.sharks) {
                sharks.paintComponent(graphics);
            }
            for (PufferFish currentPufferFish : this.pufferFishes) {
                currentPufferFish.paintComponent(graphics);
            }
            for (Fish fishes : this.fishes) {
                fishes.paintComponent(graphics);
            }
            for (BonusFish currentBonus : this.bonusFish) {
                currentBonus.paintComponent(graphics);
            }
            for (Fish fishes : this.flippedFishes) {
                fishes.paintComponent(graphics);
            }
            for (Heart currentHeart : this.hearts) {
                currentHeart.paintComponent(graphics);
            }
        } catch (Exception e) {}
    }

    private void showGameOver() {
        if (!gameRunning) {
            saveHighScore();
            JLabel gameOverLabel = new JLabel(GAME_OVER_TEXT);
            JOptionPane.showMessageDialog(null, GAME_OVER_TEXT);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 72));
            gameOverLabel.setForeground(Color.RED);
            gameOverLabel.setBounds(GAME_PANEL_WIDTH / 3 - 30, GAME_PANEL_HEIGHT / 2, GAME_PANEL_WIDTH - 380, HEIGHT_DEFAULT);
            this.add(gameOverLabel);
            ((GameWindow) SwingUtilities.getWindowAncestor(this)).showMainMenu();
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    highScores.put(Integer.parseInt(parts[0]), parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading highscores: " + e.getMessage());
        }
    }

    private void saveHighScore() {
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        highScores.put(score, playerName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE))) {
            for (var entry : highScores.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving highscores: " + e.getMessage());
        }
    }

}
