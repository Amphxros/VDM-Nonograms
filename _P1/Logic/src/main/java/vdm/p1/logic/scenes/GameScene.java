package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.engine.ISound;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.LifeManager;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.CheckSolutionButton;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class GameScene extends Scene {
	private final ISound sound;
	private final LifeManager lifeManager;

	public GameScene(IEngine engine, GameTheme theme, String level) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		lifeManager = (LifeManager) new LifeManager(engine, font).setPosition(130, 100).setSize(140, 40);

		addGameObject(lifeManager);
		addGameObject(Table.fromFile(font, lifeManager, theme, level).setPosition(50, 150).setSize(300, 300));
	}

	public GameScene(IEngine engine, int rows, int columns) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		lifeManager = (LifeManager) new LifeManager(engine, font).setPosition(130, 100).setSize(140, 40);
		Table table = (Table) Table.fromRandom(font, lifeManager, rows, columns).setPosition(50, 150).setSize(300, 300);

		addGameObject(lifeManager);
		addGameObject(table);

		addButton(new GoToStartSceneButton(getEngine()), engine.getGraphics().newImage("image/grey_boxCross.png"), font, "Rendirse", 20, 20);
		addButton(new CheckSolutionButton(getEngine(), table), engine.getGraphics().newImage("image/grey_boxCheckmark.png"), font, "Comprobar", 260, 20);
	}

	public LifeManager getLifeManager() {
		return lifeManager;
	}

	@Override
	public void dispose() {
		super.dispose();
		getEngine().getAudio().stopSound(sound);
	}

	@Override
	public void update(double delta) {
		super.update(delta);
		/**
		 * si el nº de vidas es 0 -->popup
		 */

		/**
		 * si el tablero esta resuelto -->otro popup
		 */
	}
}
