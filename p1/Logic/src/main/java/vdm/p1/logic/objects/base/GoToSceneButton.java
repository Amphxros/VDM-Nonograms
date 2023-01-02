package vdm.p1.logic.objects.base;

import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.scenes.Scene;

public abstract class GoToSceneButton extends Button {
	public GoToSceneButton(IScene scene) {
		super(scene);
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		getEngine().getLogic().setScene(createScene());
		return true;
	}

	protected abstract Scene createScene();
}
