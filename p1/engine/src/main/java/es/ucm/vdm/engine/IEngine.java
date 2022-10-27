package es.ucm.vdm.engine;

public interface IEngine {
    IGraphics getGraphics();
    IAudio getAudio();
    IInput getInput();


    void run();
    void resume();
    void pause();
    void update();
    void render();

    boolean openGame();
    boolean closeGame();
    double getDeltaTime();

    int getWidth();
    int getHeight();
}
