package utilz;
import java.util.Random;

public class Constants {

    public static class MainMenuConstants{
        public static final int BACKGROUND_WIDTH = 1280;
        public static final int BACKGROUND_HEIGHT = 720;
        public static final int BUTTON_CENTER=454;
        public static final int BUTTON_HEIGHT=110;
        public static final int BUTTON_WIDTH=410;
    }

    public static class GameWindowConstants{
        public static final int GAME_WINDOW_WIDTH = 1280;
        public static final int GAME_WINDOW_HEIGHT = 720;
    }
    public static class GamePanelConstant{
        public static final int GAME_PANEL_WIDTH = 1280;
        public static final int GAME_PANEL_HEIGHT = 720;
        public static final int HEART_WIDTH = 50;
        public static final int HEART_HEIGHT = 50;
        public static final int HEART_X = 50;
        public static final int HEART_Y = 20;
        public static final int HEART_START = 1200;
        public static final int SCORE_LABEL_WIDTH = 400;
        public static final int SCORE_LABEL_HEIGHT = 400;
        public static final int WIDTH_DEFAULT = 200;
        public static final int HEIGHT_DEFAULT = 100;
    }

    public static class InstructionsConstants{
        public static final int TITLE_WIDTH = 200;
        public static final int TITLE_HEIGHT = 200;
        public static final int INSTRUCTIONS_WIDTH = 1280;
        public static final int INSTRUCTIONS_HEIGHT = 720;
        public static final int SUB_TITLES_SIZE = 100;
        public static final int IMAGE_LABEL_HEIGHT=180;
        public static final int FISH_X = 200;
        public static final int FISH_Y = 100;
        public static final int FISH_WIDTH = 100;
        public static final int FISH_HEIGHT = 80;
        public static final float POS = 1.5f;
    }

    public static class LevelConstants{
        public static final int FISH_SPAWN_TIME = 7000;
        public static final int BONUS_FISH_SPAWN_TIME = 35000;
        public static final int SHARK_SPAWN_TIME = 25000;
        public static final int PUFFER_FISH_SPAWN_TIME = 10000;
        public static final int OCEAN_SHARK_START_HEIGHT = 233;
        public static final int OCEAN_FISHES_START_UPPER_LIMIT = 285;
        public static final int OCEAN_END_BOTTOM_LIMIT = 600;
        public static final int FISHES_WHO_START_FROM_RIGHT = 1200;
        public static final int FISHES_WHO_START_FROM_LEFT = -20;
        public static Random random = new Random();
        //Level One finals//
        public static final int START_NUM_OF_FISH = 35;
        public static final int START_NUM_OF_PUFFERS = 12;
        public static final int START_NUM_OF_SHARK = 8;
        public static final int START_NUM_OF_BONUS_FISH = 3;
    }

    public static class FishingRodConstants{
        public static final int MAX_LINE_LENGTH = 660 ; // הגובה המקסימלי  החוט
        public static final int MIN_LINE_LENGTH = 10;  // הגובה המינימלי החוט
        public static final int ROD_X = 617;
        public static final int ROD_Y = 80;
        public static final int HOOK_DIAMETER = 12;
    }

    public static class FishConstants{
        public static final int FISH_BOX_SIZE = 25;
        public static final int WIDTH_DEFAULT = 400;
        public static final int HEIGHT_DEFAULT = 100;
    }

    public static class Images{
        public static final String HOOK_PAINT = "src/images/HookPaint.png";
        public static final String BACK_GROUND = "src/images/Background.png";
        public static final String HEART = "src/images/heart.png";
        public static final String MAIN_MENU_BACK_GROUND = "src/images/MainMenuBackground.jpg";
        public static final String YELLOW_FISH = "src/images/Fish.png";
        public static final String BONUS_FISH = "src/images/bonusFish.png";
        public static final String SHARK_IMAGE = "src/images/Shark.png";
        public static final String PUFFER_FISH_IMAGE = "src/images/PufferFish.png";
        public static final String INSTRUCTIONS_BACK_GROUND = "src/images/InstructionsBackground.jpg";
        public static final String FISH_IMAGE = "src/images/fish.png";
        public static final String FISH_CATCH = "src/images/FishCatch.png";
        public static final String BONUS_IMAGE = "src/images/bonusfliped.png";
        public static final String BONUS_FISH_CATCH = "src/images/bonusFishCatch.png";
    }

    public static class Audio{
        public static final String AQUARIUM = "src/images/Aquarium.wav";
    }

    public static class Text{
        public static final String FOR_BACK = "Back to Main Menu";
        public static final String FOR_INSTRUCTION_WINDOW = "Dangerous Fishing";
        public static final String GAME_OVER_TEXT = "|GAME OVER|";
        public static final String TITLE_TEXT_OPTION = "Developed By:";
        public static final String UNDER_TITLE_TEXT = "Daniel Gino, Yakov Ben-Hemo, Shay Ozer";
        public static final String TITLE_TEXT = "Welcome to Dangerous Fishing!";
        public static final String INSTRUCTIONS_TEXT = """
                in this game currently you can catch two type of fish:  
                1.yellow fish -also called regular fish that worth 1 point every catch  
                2.gray fish - also called bonus fish that worth 3 points every catch! but It rarely spawns.
                
                But it's not that easy, the game also has Sharks and Puffer Fish that multiply faster as the score goes up 
                beyond the fact that you can't fish for a Shark, a shark can't touch the fishing line!
                for that the game starts with 3 lives, every time you touch a shark or puff fish one life goes down!
                
                ~ ! Pay attention !~
                In each level a new heart is created in the water, if you fish it you can increase the life.
                
                Game operation:
                1. Use the arrow keys to move the hook up and down, or W(for Up), S(for down).
                2. When a fish is caught on the rod, it must be brought up to the tip of the rod and only then press the 'SPACE BAR'
                As soon as you pressed the space key and the fish disappeared - a point was added to you.""";
    }

    public static class SaveAndLoadTxt{
        public static final String HIGH_SCORES_FILE = "highscores.txt";
    }

}
