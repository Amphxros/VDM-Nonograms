package vdm.p1.logic.scenes;

import java.util.Arrays;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
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
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");

		gameManager = ((Logic) engine.getLogic()).getGameManager();

		// TODO: Use this?
		// IImage image = engine.getGraphics().newImage("image/" + theme + "_theme.png");

		locked = engine.getGraphics().newImage("image/lock.png");

		addButton(new GoToThemeSelectSceneButton(getEngine()), font, "Volver", 20, 20);

		// Load level grid:
		if (!theme.loaded()) theme.load(engine);

		final int distance = 120;

		String[] levels = theme.getLevels();

		for (int r = 0, i = 0; r < levels.length; r += 3, i++) {
			String[] span = Arrays.copyOfRange(levels, r, Math.min(levels.length, r + 3));
			for (int j = 0; j < span.length; j++) {
				addLevelButton(font, glass, r + j, theme, span[j], 50 + (j * distance), 150 + (i * distance));
			}
		}
	}

	private void addLevelButton(IFont font, IImage glass, int index, GameTheme theme, String level, int x, int y) {
		final int size = 50;
		final int textOffsetX = 25;
		final int textOffsetY = 32;

		if (gameManager.getLastUnlockedTheme() > theme.getIndex() || gameManager.getLastUnlockedLevel() >= index) {
			addGameObject(new Image(glass).setPosition(x, y).setSize(size, size));
			addGameObject(new Text(Integer.toString(index), font).setPosition(x + textOffsetX, y + textOffsetY));
			addGameObject(new CreateThemeLevelButton(getEngine(), theme, level).setPosition(x, y).setSize(size, size));
		} else {
			addGameObject(new Image(locked).setPosition(x, y).setSize(size, size));
		}
	}
}
