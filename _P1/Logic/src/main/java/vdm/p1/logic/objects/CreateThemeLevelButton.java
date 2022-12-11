package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.GameScene;

public final class CreateThemeLevelButton extends Button {
	private final IEngine engine;
	private final String content;

	public CreateThemeLevelButton(IEngine engine, String content) {
		super();
		this.engine = engine;
		this.content = content;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		Logic logic = (Logic) engine.getLogic();
		logic.changeScene(new GameScene(engine, content));
		return true;
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();

		super.handleParentScreenChange();
	}
}
