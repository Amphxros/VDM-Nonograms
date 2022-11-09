package vdm.p1.logic;

import vdm.p1.engine.Engine;
import vdm.p1.engine.ILogic;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.StartScene;

public final class Logic implements ILogic {
    private Engine engine;
    private IScene currentScene;

    public Logic(Engine eng) {
        this.engine = eng;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public void setEngine(Engine eng) {
        this.engine = eng;
    }

    @Override
    public void initLogic() {
        if (currentScene != null) currentScene.dispose();
        currentScene = new StartScene(getEngine());
    }

    public void changeScene(Scene scene) {
        currentScene = scene;
    }

    @Override
    public void update(double t) {
        currentScene.update(t);
    }

    @Override
    public void render() {
        currentScene.render(engine.getGraphics());
    }

    @Override
    public void handleEvents() {
        currentScene.handleInput(engine.getInput());
    }
}