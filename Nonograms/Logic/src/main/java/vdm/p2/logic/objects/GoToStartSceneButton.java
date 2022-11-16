package vdm.p2.logic.objects;

import vdm.p2.engine.IEngine;
import vdm.p2.engine.TouchEvent;
import vdm.p2.logic.Logic;
import vdm.p2.logic.objects.base.Button;
import vdm.p2.logic.scenes.StartScene;

public final class GoToStartSceneButton extends Button {
	private final IEngine engine;

	public GoToStartSceneButton(IEngine engine) {
		super();
		this.engine = engine;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		Logic logic = (Logic) engine.getLogic();
		logic.changeScene(new StartScene(engine));
		return true;
	}
}
