package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.StartScene;

public final class GoToStartSceneButton extends GoToSceneButton {
	public GoToStartSceneButton(IEngine engine) {
		super(engine);
	}

	@Override
	protected Scene createScene() {
		return new StartScene(getEngine());
	}
}
