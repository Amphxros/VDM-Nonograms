package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.CreateThemeButton;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class ThemeSelectScene extends Scene {
	private final GameManager gameManager;
	private final IImage locked;

	public ThemeSelectScene(IEngine engine) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		gameManager = ((Logic) engine.getLogic()).getGameManager();
		if (!gameManager.loaded()) gameManager.loadThemes(engine);

		locked = gameManager.getLastUnlockedLevel() >= 5 ? null : engine.getGraphics().newImage("image/lock.png");

		GameTheme[] themes = gameManager.getThemes();
		addLevelButton(font, themes[0], 50, 100);
		addLevelButton(font, themes[1], 150, 100);
		addLevelButton(font, themes[2], 250, 100);
		addLevelButton(font, themes[3], 50, 200);
		addLevelButton(font, themes[4], 150, 200);
		addLevelButton(font, themes[5], 250, 200);

		addGameObject(new Text("Seleccione categoria a jugar", font).setPosition(200, 50));
		addButton(new GoToStartSceneButton(getEngine()), font, "Volver", 20, 50);
	}

	private void addLevelButton(IFont font, GameTheme theme, int x, int y) {
		final int size = 50;
		final int halfSize = 25;

		String name;
		IImage icon;
		if (gameManager.getLastUnlockedTheme() < theme.getIndex()) {
			name = "BLOQUEADO";
			icon = locked;
		} else {
			name = theme.getName();
			icon = getEngine().getGraphics().newImage(theme.getImagePath());

			addGameObject(new CreateThemeButton(getEngine(), theme).setPosition(x, y).setSize(size, size + size));
		}

		addGameObject(new Text(name, font).setPosition(x + halfSize, y + size));
		addGameObject(new Image(icon).setPosition(0, 0).setSize(size, size));
	}

	private void addButton(GameObject button, IFont font, String text, int x, int y) {
		GameObject textComponent = new Text(text, font).setPosition(x + 50, y);
		addGameObject(button.addChild(textComponent).setPosition(x, y).setSize(100, 50));
	}
}
