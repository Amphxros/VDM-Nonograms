package vdm.p1.pcengine;

import java.io.File;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import vdm.p1.engine.IAudio;
import vdm.p1.engine.ISound;

public class DesktopAudio implements IAudio {

    public Clip sound;
    private AudioInputStream audioStream;

    @Override
    public ISound createSound(String path) {

        try {
            File audioFile = new File(path);
            audioStream =   AudioSystem.getAudioInputStream(audioFile);
            this.sound = AudioSystem.getClip();
            this.sound.open(audioStream);
        } catch (Exception e) {
            System.err.println("Couldn't load audio file");
            e.printStackTrace();
        }
        return new DesktopSound(sound);
    }

    @Override
    public void playSound(ISound s) {   s.play();   }

    @Override
    public void stopSound(ISound s) {   s.play();   }

    @Override   // Must be called before playing the sound
    public void setLoop() { this.sound.loop(Clip.LOOP_CONTINUOUSLY);    }
}
