package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.LevelSelectScene;
import vdm.p1.logic.scenes.Scene;

public final class GoToLevelSelectSceneButton extends GoToSceneButton {
	public GoToLevelSelectSceneButton(IEngine engine) {
		super(engine);
	}

	@Override
	protected Scene createScene() {
		return new LevelSelectScene(getEngine());
	}
}
