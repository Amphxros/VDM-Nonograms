package vdm.p1.logic;

import vdm.p1.engine.Engine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IInput;
import vdm.p1.engine.ILogic;
import vdm.p1.engine.IScene;
import vdm.p1.logic.scenes.StartScene;

public final class Logic implements ILogic {
	private IScene currentScene;

	// TODO: Do not pass Engine, pass GameManager + StartScene
	public Logic(Engine engine) {
		this.currentScene = new StartScene(engine);
	}

	@Override
	public void setScene(IScene scene) {
		if (currentScene != null) currentScene.dispose();
		currentScene = scene;
		currentScene.init();
	}

	@Override
	public void update(double t) {
		currentScene.update(t);
	}

	@Override
	public void render(IGraphics graphics) {
		currentScene.render(graphics);
	}

	@Override
	public void handleEvents(IInput input) {
		currentScene.handleInput(input);
	}
}
