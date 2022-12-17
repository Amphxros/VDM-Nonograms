package vdm.p1.logic.objects.base;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.scenes.Scene;

public abstract class GoToSceneButton extends Button {
	private final IEngine engine;

	public GoToSceneButton(IEngine engine) {
		super();
		this.engine = engine;
	}

	public IEngine getEngine() {
		return engine;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		engine.getLogic().setScene(createScene());
		return true;
	}

	protected abstract Scene createScene();
}
