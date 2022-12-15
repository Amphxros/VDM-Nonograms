package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.base.Button;

public final class PurchaseAndSetPaletteButton extends Button {
	private final GameManager gameManager;
	private final PaletteItem paletteObject;
	private final int index;

	public PurchaseAndSetPaletteButton(IEngine engine, int index, PaletteItem paletteObject) {
		super();
		this.index = index;
		this.paletteObject = paletteObject;

		gameManager = ((Logic) engine.getLogic()).getGameManager();
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		if (gameManager.getMoney() >= paletteObject.getPrice()) {
			gameManager.setMoney(gameManager.getMoney() - paletteObject.getPrice());
		}

		return true;
	}
}
