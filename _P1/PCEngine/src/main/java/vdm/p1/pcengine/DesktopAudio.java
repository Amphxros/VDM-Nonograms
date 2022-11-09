package vdm.p1.pcengine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import vdm.p1.engine.IAudio;
import vdm.p1.engine.ISound;

public final class DesktopAudio implements IAudio {
    @Override
    public ISound createSound(String path) {
        Clip sound;

        try {
            File audioFile = new File("assets/" + path + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            sound = AudioSystem.getClip();
            sound.open(audioStream);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return new DesktopSound(sound);
    }

    @Override
    public void playSound(ISound s) {
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.play();
    }
}
