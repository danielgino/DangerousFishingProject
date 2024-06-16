package objects;
import fishes.BonusFish;
import fishes.Fish;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardListener implements KeyListener {
    private GamePanel gamePanel;
    private  FishingRod rod;
    private Fish fish;
    private ArrayList<Fish> fishes;
    private ArrayList<Fish> fishesToRemove;
    private BonusFish bonusFish;
    private ArrayList<BonusFish> bonusFishes;
    private ArrayList<BonusFish> bonusFishesToRemove;

    public KeyboardListener(GamePanel gamePanel, FishingRod rod,Fish fish,ArrayList<Fish> fishes,ArrayList<BonusFish> bonusFishes,BonusFish bonusFish){
        this.gamePanel = gamePanel;
        this.rod = rod;
        this.fish = fish;
        this.fishes = fishes;
        this.bonusFishes = bonusFishes;
        this.bonusFish = bonusFish;
        this.fishesToRemove = new ArrayList<>();
        this.bonusFishesToRemove=new ArrayList<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> this.rod.moveUp();
            case KeyEvent.VK_UP ->this.rod.moveUp();

            case KeyEvent.VK_S -> this.rod.moveDown();
            case KeyEvent.VK_DOWN -> this.rod.moveDown();

            case KeyEvent.VK_SPACE -> {
                for (int i = 0; i < fishes.size(); i++) {
                    Fish currentFish = fishes.get(i);
                    if (currentFish.isCaught() && currentFish.getY() <= 80) {
                        fishesToRemove.add(currentFish);
                       this.gamePanel.addScore(1);
                    }
                }
                for (Fish fishToRemove : this.fishesToRemove) {
                    this.gamePanel.removeFish(fishToRemove);
                }
                for (int i = 0; i < bonusFishes.size(); i++) {
                    BonusFish currentFish = bonusFishes.get(i);
                    if (currentFish.getY() <= 80) {
                        bonusFishesToRemove.add(currentFish);
                        this.gamePanel.addScore(3);
                    }
                }

                for (BonusFish bonusFishToRemove : this.bonusFishesToRemove) {
                    this.gamePanel.removeBonusFish(bonusFishToRemove);
                }

                this.bonusFishesToRemove.clear();

                for (Fish fishToRemove : this.fishesToRemove) {
                    this.gamePanel.removeFish(fishToRemove);
                }
            }
        }
        this.gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
