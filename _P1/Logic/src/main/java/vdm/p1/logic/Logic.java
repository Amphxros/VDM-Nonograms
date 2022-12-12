package vdm.p1.logic;

import vdm.p1.engine.Engine;
import vdm.p1.engine.ILogic;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.StartScene;

public final class Logic implements ILogic {
	private final Engine engine;
	private final GameManager gameManager;
	private IScene currentScene;

	public Logic(Engine engine) {
		this.engine = engine;
		this.gameManager = GameManager.load(engine);
	}

	public Engine getEngine() {
		return engine;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	@Override
	public void initLogic() {
		if (currentScene != null) currentScene.dispose();
		currentScene = new StartScene(getEngine());
	}

	public void changeScene(Scene scene) {
		if (currentScene != null) currentScene.dispose();
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

	@Override
	public void handleOpeningNotifications() {
		currentScene.handleOpeningNotifications();
	}
}
