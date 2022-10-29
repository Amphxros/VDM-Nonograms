package es.ucm.vdm.engine;

public interface Logic {

    void setEngine(Engine eng);

    void initLogic();
    void update(double t);
    void render();
    void handleEvents();

}
