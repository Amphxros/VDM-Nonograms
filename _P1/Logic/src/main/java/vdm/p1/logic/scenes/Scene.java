package vdm.p1.logic.scenes;

import java.util.ArrayList;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.IScene;

public abstract class Scene implements IScene {
    private final IEngine engine;
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();

    public Scene(IEngine engine) {
        this.engine = engine;
    }

    public IEngine getEngine() {
        return engine;
    }

    @Override
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject go) {
        gameObjects.add(go);
    }

    @Override
    public void render(IGraphics graphics) {
        for (GameObject go : getGameObjects()) {
            go.render(graphics);
        }
    }

    @Override
    public void update(double t) {
        for (GameObject go : getGameObjects()) {
            go.update(t);
        }
    }

    @Override
    public void handleInput(Input input) {
        for (TouchEvent t : input.getTouchEvents()) {
            for (GameObject g : getGameObjects()) {
                g.handleInput(t);
            }
        }
    }
}
