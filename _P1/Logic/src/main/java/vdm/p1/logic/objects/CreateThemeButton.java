package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.ThemeLevelSelectScene;

public final class CreateThemeButton extends Button {
	private final IEngine engine;
	private final GameTheme theme;

	public CreateThemeButton(IEngine engine, GameTheme theme) {
		this.engine = engine;
		this.theme = theme;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		Logic logic = (Logic) engine.getLogic();
		logic.changeScene(new ThemeLevelSelectScene(engine, theme));
		return true;
	}

	@Override
	public void render(IGraphics graphics) {
		super.render(graphics);
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();

		super.handleParentScreenChange();
	}
}
