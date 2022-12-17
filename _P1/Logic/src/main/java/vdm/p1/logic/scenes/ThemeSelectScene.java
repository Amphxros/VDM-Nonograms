package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.GoToThemeLevelSelectButton;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class ThemeSelectScene extends Scene {
	private final GameManager gameManager;
	private final IImage locked;

	public ThemeSelectScene(IEngine engine) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		gameManager = ((Logic) engine.getLogic()).getGameManager();
		if (!gameManager.loaded()) gameManager.loadThemes(engine);

		locked = engine.getGraphics().newImage("image/lock.png");

		addButton(new GoToStartSceneButton(getEngine()), font, "Volver", 20, 20);
		addGameObject(new Text("Seleccione categoria a jugar", font).setPosition(200, 150));

		final int x0 = 50;
		final int x1 = 170;
		final int x2 = 290;
		final int y0 = 200;
		final int y1 = 320;

		GameTheme[] themes = gameManager.getThemes();
		addLevelButton(font, themes[0], x0, y0);
		addLevelButton(font, themes[1], x1, y0);
		addLevelButton(font, themes[2], x2, y0);
		addLevelButton(font, themes[3], x0, y1);
		addLevelButton(font, themes[4], x1, y1);
		addLevelButton(font, themes[5], x2, y1);
	}

	private void addLevelButton(IFont font, GameTheme theme, int x, int y) {
		final int size = 50;
		final int textOffsetX = 25;
		final int textOffsetY = 65;

		String name;
		IImage icon;
		if (gameManager.getLastUnlockedTheme() < theme.getIndex()) {
			name = "Bloqueado";
			icon = locked;
		} else {
			name = theme.getName();
			icon = getEngine().getGraphics().newImage(theme.getImagePath());

			addGameObject(new GoToThemeLevelSelectButton(getEngine(), theme).setPosition(x, y).setSize(size, size + size));
		}

		addGameObject(new Text(name, font).setPosition(x + textOffsetX, y + textOffsetY));
		addGameObject(new Image(icon).setPosition(x, y).setSize(size, size));
	}
}
