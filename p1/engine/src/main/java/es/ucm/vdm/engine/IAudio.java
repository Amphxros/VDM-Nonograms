package es.ucm.vdm.engine;

public interface IAudio {
    ISound createSound(String filename);
    void playSound(ISound s);
    void stopSound(ISound s);
}
