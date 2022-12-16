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
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");

		addButton(new GoToStartSceneButton(getEngine()), font, "Volver", 20, 50);
		addGameObject(new Text("Selecciona el tamaño del puzzle", font).setPosition(200, 50));

		addLevelButton(font, glass, 4, 4, 20, 50);
		addLevelButton(font, glass, 5, 5, 120, 50);
		addLevelButton(font, glass, 5, 10, 240, 50);

		addLevelButton(font, glass, 8, 8, 20, 200);
		addLevelButton(font, glass, 10, 10, 120, 200);
		addLevelButton(font, glass, 10, 15, 240, 200);
	}

	private void addButton(GameObject button, IFont font, String text, int x, int y) {
		GameObject textComponent = new Text(text, font).setPosition(x + 50, y);
		addGameObject(button.addChild(textComponent).setPosition(x, y).setSize(100, 50));
	}

	private void addLevelButton(IFont font, IImage glass, int rows, int columns, int x, int y) {
		GameObject image = new Image(glass).setPosition(x + 20, y + 20).setSize(60, 60);
		GameObject text = new Text(rows + "x" + columns, font).setPosition(x + 50, y + 100);
		addGameObject(new CreateLevelButton(getEngine(), rows, columns).addChild(image).addChild(text));
	}
}
