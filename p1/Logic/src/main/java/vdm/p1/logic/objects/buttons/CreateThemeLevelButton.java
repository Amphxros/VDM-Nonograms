package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IScene;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.GameScene;
import vdm.p1.logic.scenes.Scene;

public final class CreateThemeLevelButton extends GoToSceneButton {
	private final GameTheme theme;
	private final String level;

	public CreateThemeLevelButton(IScene scene, GameTheme theme, String level) {
		super(scene);
		this.theme = theme;
		this.level = level;
	}

	@Override
	protected Scene createScene() {
		return new GameScene(getEngine(), theme, level);
	}
}
