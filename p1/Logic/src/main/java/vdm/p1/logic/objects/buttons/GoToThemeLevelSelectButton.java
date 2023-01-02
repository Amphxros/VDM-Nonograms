package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IScene;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.ThemeLevelSelectScene;

public final class GoToThemeLevelSelectButton extends GoToSceneButton {
	private final GameTheme theme;

	public GoToThemeLevelSelectButton(IScene scene, GameTheme theme) {
		super(scene);
		this.theme = theme;
	}

	@Override
	protected Scene createScene() {
		return new ThemeLevelSelectScene(getEngine(), theme);
	}
}
