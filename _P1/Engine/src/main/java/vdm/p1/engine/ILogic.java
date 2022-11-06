package vdm.p1.engine;

public interface ILogic {
    void setEngine(Engine eng);

    void initLogic();
    void update(double t);
    void render();
    void handleEvents();
}