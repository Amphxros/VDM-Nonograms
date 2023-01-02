package vdm.p1.logic.scenes;

import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.Logic;
import vdm.p1.logic.PaletteItem;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;
import vdm.p1.logic.objects.buttons.ShopPaletteButton;

public final class ShopScene extends Scene {
	public ShopScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		IImage coin = engine.getGraphics().newImage("image/coin.png");
		GameManager gameManager = ((Logic) engine.getLogic()).getGameManager();

		addButton(new GoToStartSceneButton(this), font, "Volver", 20, 50);

		addGameObject(new Image(this, coin).setPosition(320, 55).setSize(30, 30));
		addGameObject(new Text(this, Integer.toString(gameManager.getMoney()), font, HorizontalAlignment.RIGHT).setPosition(310, 75));

		int y = 120;
		for (PaletteItem item : gameManager.getPalettes()) {
			addGameObject(new ShopPaletteButton(this, gameManager, item, font, coin).setPosition(50, y).setSize(300, 30));
			y += 50;
		}
	}
}
