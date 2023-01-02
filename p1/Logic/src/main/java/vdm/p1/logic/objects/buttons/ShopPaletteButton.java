package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.PaletteItem;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.Scene;

public class ShopPaletteButton extends Button {
	private static final String PALETTE_ICON_ROUTE = "image/palettes_icons/";

	private final GameManager gameManager;
	private final PaletteItem palette;
	private final IFont font;
	private final IImage coin;

	public ShopPaletteButton(IScene scene, GameManager gameManager, PaletteItem palette, IFont font, IImage coin) {
		super(scene);

		this.gameManager = gameManager;
		this.palette = palette;
		this.font = font;
		this.coin = coin;
	}

	@Override
	public void init() {
		int x = getX();
		int y = getY();

		final int textOffsetY = 22;

		IImage icon = getEngine().getGraphics().newImage(PALETTE_ICON_ROUTE + palette.getName() + ".png");

		addChild(new Image(getScene(), icon).setPosition(x, y).setSize(30, 30));
		addChild(new Text(getScene(), palette.getName(), font, HorizontalAlignment.RIGHT).setPosition(350, y + textOffsetY));

		if (!isUnlocked()) {
			addChild(new Image(getScene(), coin).setPosition(x + 40, y + 5).setSize(20, 20));
			addChild(new Text(getScene(), Integer.toString(palette.getPrice()), font, HorizontalAlignment.LEFT).setPosition(x + 70, y + textOffsetY));
		}

		super.init();
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		if (isSet()) return true;
		if (isUnlocked()) {
			gameManager.setPalette(palette.getId());
			gameManager.save(getEngine());
			return true;
		}

		if (gameManager.getMoney() >= palette.getPrice()) {
			gameManager.setMoney(gameManager.getMoney() - palette.getPrice());
			gameManager.addPalette(palette.getId());
			gameManager.save(getEngine());

			getChildren().remove(3); // Text
			getChildren().remove(2); // Image

			((Text) ((Scene) getScene()).getObjects().get(2)).setText(Integer.toString(gameManager.getMoney()));
			return true;
		}

		System.out.println("User attempted to buy Palette[" + palette.getId() + "] But does not have enough money.");
		return true;
	}

	public boolean isUnlocked() {
		return gameManager.getUnlockedPalettes().contains(palette.getId());
	}

	public boolean isSet() {
		return gameManager.getCurrentPaletteId() == palette.getId();
	}
}
