package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.ThemeLevelSelectScene;

public final class CreateThemeButton extends GoToSceneButton {
	private final GameTheme theme;

	public CreateThemeButton(IEngine engine, GameTheme theme) {
		super(engine);
		this.theme = theme;
	}

	@Override
	protected Scene createScene() {
		return new ThemeLevelSelectScene(getEngine(), theme);
	}
}
