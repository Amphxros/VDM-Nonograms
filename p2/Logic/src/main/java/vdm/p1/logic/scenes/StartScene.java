package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.objects.buttons.GoToLevelSelectSceneButton;
import vdm.p1.logic.objects.buttons.GoToShopSceneButton;
import vdm.p1.logic.objects.buttons.GoToThemeSelectSceneButton;

public final class StartScene extends Scene {
	public StartScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, false);
		IImage share = engine.getGraphics().newImage("image/cart.png");

		int maxWidth = engine.getGraphics().getWidth();
		int center = maxWidth / 2;

		int buttonW = (int) (maxWidth * 0.8);
		int buttonX = center - buttonW / 2;

		// Title
		addGameObject(new Text(this, "Nonogramas", font).setPosition(center, 50));

		// Quick Match
		addGameObject(new Text(this, "Partida Rapida", font).setPosition(center, 250));
		addGameObject(new GoToLevelSelectSceneButton(this).setPosition(buttonX, 210).setSize(buttonW, 50));

		// Story Mode
		addGameObject(new Text(this, "Modo Historia", font).setPosition(center, 350));
		addGameObject(new GoToThemeSelectSceneButton(this).setPosition(buttonX, 310).setSize(buttonW, 50));

		// Buttons
		addGameObject(createButton(new GoToShopSceneButton(this).setPosition(20, 500).setSize(50, 50), share));
	}

	private GameObject createButton(GameObject button, IImage image) {
		return button.addChild(new Image(this, image)
				.setPosition(button.getPosition())
				.setSize(button.getSize()));
	}
}
