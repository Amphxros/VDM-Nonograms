package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.ISound;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.buttons.CheckSolutionButton;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class GameScene extends Scene {
	private final ISound sound;

	public GameScene(IEngine engine, int rows, int columns) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		IFont tableFont = engine.getGraphics().newFont("font/pico.ttf", 7, false);
		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		Table table = (Table) Table.fromRandom(this, tableFont, rows, columns).setPosition(50, 150).setSize(300, 300);

		addGameObject(table);
		addButton(new GoToStartSceneButton(this), engine.getGraphics().newImage("image/grey_boxCross.png"), font, "Rendirse", 20, 20);
		addButton(new CheckSolutionButton(this, table), engine.getGraphics().newImage("image/grey_boxCheckmark.png"), font, "Comprobar", 260, 20);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void dispose() {
		super.dispose();
		getEngine().getAudio().stopSound(sound);
	}
}
