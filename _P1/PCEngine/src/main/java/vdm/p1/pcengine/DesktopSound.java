package vdm.p1.pcengine;

import javax.sound.sampled.Clip;

import vdm.p1.engine.ISound;

public final class DesktopSound implements ISound {
    private final Clip sound;

    public DesktopSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public void play() {
        if (!sound.isRunning())
            sound.start();
    }

    @Override
    public void stop() {
        if (sound.isRunning()) {
            sound.stop();
            sound.setFramePosition(0);
        }
    }

    @Override
    public void setLoop() {
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
