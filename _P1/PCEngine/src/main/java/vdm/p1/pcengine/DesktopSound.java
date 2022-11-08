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
        if (!this.sound.isRunning())
            this.sound.start();
    }

    @Override
    public void stop() {
        if (this.sound.isRunning()) {
            this.sound.stop();
            this.sound.setFramePosition(0);
        }
    }
}
