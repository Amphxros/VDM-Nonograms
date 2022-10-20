package es.ucm.vdm.pcengine;

import es.ucm.vdm.engine.IAudio;
import es.ucm.vdm.engine.ISound;

public class PCAudio implements IAudio {
    @Override
    public PCSound createSound(String filename) {
        try {
            return new PCSound(filename);
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public void playSound(ISound s) {
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.stop();
    }
}
