package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.CreateLevelButton;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class LevelSelectScene extends Scene {
	public LevelSelectScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");

		addButton(new GoToStartSceneButton(this), font, "Volver", 20, 20);
		addGameObject(new Text(this, "Selecciona el tamaño del puzzle", font).setPosition(200, 150));

		final int x0 = 50;
		final int x1 = 170;
		final int x2 = 290;
		final int y0 = 200;
		final int y1 = 320;

		addLevelButton(font, glass, 4, 4, x0, y0);
		addLevelButton(font, glass, 5, 5, x1, y0);
		addLevelButton(font, glass, 5, 10, x2, y0);

		addLevelButton(font, glass, 8, 8, x0, y1);
		addLevelButton(font, glass, 10, 10, x1, y1);
		addLevelButton(font, glass, 10, 15, x2, y1);
	}

	private void addLevelButton(IFont font, IImage glass, int rows, int columns, int x, int y) {
		final int size = 60;
		final int textOffsetX = 30;
		final int textOffsetY = 35;

		GameObject image = new Image(this, glass).setPosition(x, y).setSize(size, size);
		GameObject text = new Text(this, rows + "x" + columns, font).setPosition(x + textOffsetX, y + textOffsetY);
		addGameObject(new CreateLevelButton(this, rows, columns).addChild(image).addChild(text).setPosition(x, y).setSize(size, size));
	}
}
