package es.ucm.vdm.engine;

public interface Graphics {

    boolean isRunning();
    void drawRectangle(int x, int y, int w, int h);
    void pause();
    void resume();

}