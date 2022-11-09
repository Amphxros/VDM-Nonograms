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

    /**
     * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
     *
     * @param graphics The assigned platform-specific {@link IGraphics} engine.
     */
    @Override
    public void render(IGraphics graphics) {
        for (GameObject gameObject : getGameObjects()) {
            gameObject.render(graphics);
        }
    }

    /**
     * Signal the scene's {@link GameObject}s to handle a frame update.
     *
     * @param delta The amount of time elapsed since the previous frame.
     */
    @Override
    public void update(double delta) {
        for (GameObject gameObject : getGameObjects()) {
            gameObject.update(delta);
        }
    }

    /**
     * Signal the scene's {@link GameObject}s to handle {@link Input}'s events.
     *
     * @param input The assigned platform-specific {@link Input} engine.
     */
    @Override
    public void handleInput(Input input) {
        for (TouchEvent event : input.getTouchEvents()) {
            for (GameObject gameObject : getGameObjects()) {
                gameObject.handleInput(event);
            }
        }
    }

    /**
     * Called when the scene is destroyed.
     */
    @Override
    public void dispose() {
    }
}
