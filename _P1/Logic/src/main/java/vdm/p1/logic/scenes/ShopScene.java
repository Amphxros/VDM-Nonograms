package vdm.p1.logic.scenes;

import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Empty;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.PaletteItem;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class ShopScene extends Scene {
	private static final String PALETTE_ICON_ROUTE = "image/palettes_icons/";

	public ShopScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage coin = engine.getGraphics().newImage("image/coin.png");
		GameManager gameManager = ((Logic) engine.getLogic()).getGameManager();

		addButton(new GoToStartSceneButton(getEngine()), font, "Volver", 20, 50);

		if (!gameManager.loadedPalettes()) gameManager.loadPalettes(engine);

		addGameObject(new Image(coin).setPosition(330, 50).setSize(20, 20));
		addGameObject(new Text(Integer.toString(gameManager.getMoney()), font, HorizontalAlignment.RIGHT).setPosition(320, 50));

		int y = 100;
		for (PaletteItem item : gameManager.getPalettes()) {
			IImage preview = engine.getGraphics().newImage(PALETTE_ICON_ROUTE + item.getName() + ".png");
			String price = Integer.toString(item.getPrice());
			addPaletteButton(font, preview, coin, item.getName(), price, y);
			y += 70;
		}
	}

	private void addPaletteButton(IFont font, IImage glass, IImage coin, String title, String currency, int y) {
		// TODO: Use a proper button instead of Empty
		addGameObject(new Empty()
				.addChild(new Image(glass).setPosition(50, y).setSize(30, 30))
				.addChild(new Text(title, font).setPosition(200, y))
				.addChild(new Image(coin).setPosition(90, y).setSize(20, 20))
				.addChild(new Text(currency, font, HorizontalAlignment.RIGHT).setPosition(320, 50))
				.setPosition(50, y)
				.setSize(300, 50));
	}

	private void addButton(GameObject button, IFont font, String text, int x, int y) {
		GameObject textComponent = new Text(text, font).setPosition(x + 65, y);
		addGameObject(button.addChild(textComponent).setPosition(x, y).setSize(100, 50));
	}
}
