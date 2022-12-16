package vdm.p1.logic.scenes;

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
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class GameScene extends Scene {
	private final ISound sound;
	private final LifeManager lifeManager;

	public GameScene(IEngine engine, GameTheme theme, String level) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		lifeManager = (LifeManager) new LifeManager(engine, font).setPosition(200, 50);

		addGameObject(lifeManager);
		addGameObject(Table.fromFile(font, lifeManager, theme, level).setPosition(20, 80).setSize(360, 360));
	}

	public GameScene(IEngine engine, int rows, int columns) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		lifeManager = (LifeManager) new LifeManager(engine, font).setPosition(200, 50);

		addGameObject(lifeManager);
		addGameObject(Table.fromRandom(font, lifeManager, rows, columns).setPosition(20, 80).setSize(360, 360));

		addButton(new GoToStartSceneButton(getEngine()), engine.getGraphics().newImage("image/grey_boxCross.png"), font, "Rendirse", 20, 20);
		addButton(new GoToStartSceneButton(getEngine()), engine.getGraphics().newImage("image/grey_boxCheckmark.png"), font, "Comprobar", 280, 20);
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

	private void addButton(GameObject button, IImage image, IFont font, String text, int x, int y) {
		GameObject imageComponent = new Image(image).setPosition(x + 5, y + 5).setSize(20, 20);
		GameObject textComponent = new Text(text, font).setPosition(x + 65, y);
		addGameObject(button.addChild(textComponent).addChild(imageComponent).setPosition(x, y).setSize(100, 50));
	}
}
