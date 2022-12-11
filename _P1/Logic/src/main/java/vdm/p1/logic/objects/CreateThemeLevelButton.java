package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.GameScene;
import vdm.p1.logic.scenes.Scene;

public final class CreateThemeLevelButton extends GoToSceneButton {
	private final String content;

	public CreateThemeLevelButton(IEngine engine, String content) {
		super(engine);
		this.content = content;
	}

	@Override
	protected Scene createScene() {
		return new GameScene(getEngine(), content);
	}
}
