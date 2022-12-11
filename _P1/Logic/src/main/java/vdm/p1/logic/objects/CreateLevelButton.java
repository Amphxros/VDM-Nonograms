package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.base.Button;
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
