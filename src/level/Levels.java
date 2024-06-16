package level;
import enemies.PufferFish;
import enemies.Shark;
import fishes.BonusFish;
import fishes.Fish;
import java.util.ArrayList;

import static utilz.Constants.LevelConstants.*;

public class Levels {
    private ArrayList<Fish> fishes;
    private ArrayList<PufferFish> pufferFishes;
    private ArrayList<Shark> sharks;
    private ArrayList<BonusFish> bonusFish;

    public Levels(ArrayList<Fish> fishes, ArrayList<BonusFish> bonusFish, ArrayList<Shark> sharks, ArrayList<PufferFish> pufferFishes) {
        this.fishes = fishes;
        this.bonusFish = bonusFish;
        this.sharks = sharks;
        this.pufferFishes = pufferFishes;
    }

    public void levelHardness(int multiply, int divide) {
        spawnEntities(FishType.FISH, FISH_SPAWN_TIME / divide, START_NUM_OF_FISH * multiply);
        spawnEntities(FishType.PUFFER_FISH, PUFFER_FISH_SPAWN_TIME / divide, START_NUM_OF_PUFFERS * multiply);
        spawnEntities(FishType.BONUS_FISH, BONUS_FISH_SPAWN_TIME / divide, START_NUM_OF_BONUS_FISH * multiply);
        spawnEntities(FishType.SHARK, SHARK_SPAWN_TIME / divide, START_NUM_OF_SHARK * multiply);
    }

    private void spawnEntities(FishType type, long time, int numberToSpawn) {
        new Thread(() -> {
            int min = 0;
            while (min < numberToSpawn) {
                switch (type) {
                    case FISH:
                        synchronized (fishes) {
                            fishes.add(new Fish(random.nextInt(FISHES_WHO_START_FROM_LEFT, 0),
                                    random.nextInt(OCEAN_FISHES_START_UPPER_LIMIT, OCEAN_END_BOTTOM_LIMIT)));
                        }
                        break;
                    case PUFFER_FISH:
                        synchronized (pufferFishes) {
                            pufferFishes.add(new PufferFish(random.nextInt(FISHES_WHO_START_FROM_RIGHT, FISHES_WHO_START_FROM_RIGHT + 50),
                                    random.nextInt(OCEAN_FISHES_START_UPPER_LIMIT, OCEAN_END_BOTTOM_LIMIT)));
                        }
                        break;
                    case BONUS_FISH:
                        synchronized (bonusFish) {
                            bonusFish.add(new BonusFish(random.nextInt(FISHES_WHO_START_FROM_RIGHT, FISHES_WHO_START_FROM_RIGHT + 50),
                                    random.nextInt(OCEAN_FISHES_START_UPPER_LIMIT, OCEAN_END_BOTTOM_LIMIT)));
                        }
                        break;
                    case SHARK:
                        synchronized (sharks) {
                            sharks.add(new Shark(random.nextInt(FISHES_WHO_START_FROM_LEFT, 0),
                                    random.nextInt(OCEAN_SHARK_START_HEIGHT, OCEAN_END_BOTTOM_LIMIT)));
                        }
                        break;
                }
                min++;
                sleep(time);
            }
        }).start();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private enum FishType {
        FISH,
        PUFFER_FISH,
        BONUS_FISH,
        SHARK
    }
}
