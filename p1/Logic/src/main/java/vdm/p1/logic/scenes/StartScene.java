package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.GoToLevelSelectSceneButton;

public final class StartScene extends Scene {
	public StartScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, false);

		int maxWidth = engine.getGraphics().getWidth();
		int center = maxWidth / 2;

		int buttonW = (int) (maxWidth * 0.8);
		int buttonX = center - buttonW / 2;
		// Title
		addGameObject(new Text(this, "Nonogramas", font).setPosition(center, 150));

		// Quick Match
		addGameObject(new Text(this, "Partida Rapida", font).setPosition(center, 350));
		addGameObject(new GoToLevelSelectSceneButton(this).setPosition(buttonX, 310).setSize(buttonW, 50));
	}
}
