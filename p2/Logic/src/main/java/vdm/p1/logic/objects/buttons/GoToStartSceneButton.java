package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IScene;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.StartScene;

public final class GoToStartSceneButton extends GoToSceneButton {
	public GoToStartSceneButton(IScene scene) {
		super(scene);
	}

	@Override
	protected Scene createScene() {
		return new StartScene(getEngine());
	}
}
