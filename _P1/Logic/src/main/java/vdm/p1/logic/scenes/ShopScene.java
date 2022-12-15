package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Logic;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public final class ShopScene extends Scene {
	private static final String palette_icon_route = "image/palettes_icons/";

	public ShopScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage coin = engine.getGraphics().newImage("image/coin.png");
		GameManager gameManager = ((Logic) engine.getLogic()).getGameManager();

		GameObject goBackButton = new GoToStartSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goBackText = new Text("Volver", font)
				.addChild(goBackButton)
				.addComponent(new InheritParentPosition());

		if (!gameManager.loadedPalettes()) gameManager.loadPalettes(engine);

		GameObject coins = createCoinPrice(font, coin, Integer.toString(gameManager.getMoney()));

		Grid grid = new Grid(8, FlowDirection.VERTICAL);
		for (int i = 0; i < 8; i++) {
			IImage preview = engine.getGraphics().newImage(palette_icon_route + gameManager.getPalette(i).getName() + ".png");
			int p = gameManager.getPalette(i).getPrice();
			grid.setElement(i, createPaletteButton(font, preview, coin, gameManager.getPalette(i).getName(), Integer.toString(p)));
		}

		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackText);

		GameObject coinHead = new Padding(0.15, 0.1, 0.8, 0.75)
				.addChild(coins);

		GameObject paddedGrid = new Padding(0.2, 0.03, 0.04, 0.03)
				.addChild(grid);
		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(coinHead)
				.addChild(paddedGrid);

		GameObject container = new Container(400, 600)
				.addChild(padding);
		getBody().addChild(container);
	}

	private GameObject createCoinPrice(IFont font, IImage coin, String numCoins) {
		GameObject image = new Image(coin)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		GameObject text = new Text(numCoins, font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		Grid grid = new Grid(2, FlowDirection.HORIZONTAL);
		grid.setElement(0, image);
		grid.setElement(1, text);

		return new Padding(0).addChild(grid);
	}

	private GameObject createPaletteButton(IFont font, IImage glass, IImage coin, String tittle, String currency) {
		GameObject image = new Image(glass)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		GameObject text = new Text(tittle, font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject coinGrid = createCoinPrice(font, coin, currency)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		Grid grid = new Grid(3, FlowDirection.HORIZONTAL);
		grid.setElement(0, image);
		grid.setElement(1, coinGrid);
		grid.setElement(2, text);

		return grid;
	}
}
