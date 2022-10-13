package es.ucm.vdm.pcengine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import es.ucm.vdm.engine.ISound;

public class PCSound implements ISound {
    private final Clip clip;

    /**
     * @param path The path to the file to load.
     * @throws UnsupportedAudioFileException When the loaded {@link File} is not a supported audio file.
     * @throws IOException When the File does not exist.
     * @throws LineUnavailableException When the {@link AudioSystem} cannot create a {@link Clip}.
     */
    public PCSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(stream);
    }

    /**
     * Plays the sound.
     */
    @Override
    public void play() {
        clip.setFramePosition(0);
        if (!clip.isRunning()) {
            clip.start();
        }
    }

    /**
     * Stops playing the sound.
     */
    @Override
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }
}
