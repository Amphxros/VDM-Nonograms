package vdm.p1.engine;

public interface IEngine {
    IGraphics getGraphics();
    void setGraphics(IGraphics g);

    Input getInput();
    void setInput(Input input);

    ILogic getLogic();
    void setLogic(ILogic logic);

    IAudio getAudio();
    void setAudio(IAudio audio);

    int getWidth();
    int getHeight();
}
