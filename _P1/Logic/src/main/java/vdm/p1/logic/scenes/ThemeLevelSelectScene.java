package vdm.p1.logic.scenes;


import java.util.Arrays;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.CreateThemeLevelButton;
import vdm.p1.logic.objects.buttons.GoToThemeSelectSceneButton;

public final class ThemeLevelSelectScene extends Scene {
	private final GameManager gameManager;
	private final IImage locked;

	public ThemeLevelSelectScene(IEngine engine, GameTheme theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");

		gameManager = ((Logic) engine.getLogic()).getGameManager();

		// TODO: Use this?
		// IImage image = engine.getGraphics().newImage("image/" + theme + "_theme.png");

		locked = engine.getGraphics().newImage("image/lock.png");

		addButton(new GoToThemeSelectSceneButton(getEngine()), font, "Volver", 20, 50);
		addGameObject(new Text(theme.getName(), font).setPosition(200, 50));

		// Load level grid:
		if (!theme.loaded()) theme.load(engine);

		String[] levels = theme.getLevels();
		for (int i = 0, r = 0; i < levels.length; i += 3, r++) {
			String[] span = Arrays.copyOfRange(levels, i, Math.min(levels.length, i + 3));
			for (int j = 0; j < span.length; j++) {
				addLevelButton(font, glass, i + j, theme, span[j], 50 + (i * 100), 50 + (j * 100));
			}
		}
	}

	private void addLevelButton(IFont font, IImage glass, int index, GameTheme theme, String level, int x, int y) {
		final int size = 50;
		final int halfSize = 25;

		if (gameManager.getLastUnlockedTheme() > theme.getIndex() || gameManager.getLastUnlockedLevel() >= index) {
			addGameObject(new Image(glass).setPosition(x, y).setSize(size, size));
			addGameObject(new Text(Integer.toString(index), font).setPosition(x + halfSize, y + size));
			addGameObject(new CreateThemeLevelButton(getEngine(), theme, level).setPosition(x, y).setSize(size, size));
		} else {
			addGameObject(new Image(locked).setPosition(x, y).setSize(size, size));
		}
	}

	private void addButton(GameObject button, IFont font, String text, int x, int y) {
		GameObject textComponent = new Text(text, font).setPosition(x + 50, y);
		addGameObject(button.addChild(textComponent).setPosition(x, y).setSize(100, 50));
	}
}
