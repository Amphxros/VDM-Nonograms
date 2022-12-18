package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.GameScene;
import vdm.p1.logic.scenes.Scene;

public final class CreateLevelButton extends GoToSceneButton {
	private final int rows;
	private final int columns;

	public CreateLevelButton(IEngine engine, int rows, int columns) {
		super(engine);
		this.rows = rows;
		this.columns = columns;
	}

	@Override
	protected Scene createScene() {
		return new GameScene(getEngine(), rows, columns);
	}
}
