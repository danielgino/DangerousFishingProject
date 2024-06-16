package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.Audio.AQUARIUM;

public class Audio {
    private Clip clip;

    public Audio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File(AQUARIUM);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void startAudio() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
}