package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.base.Button;

public class PurchaseAndSetPaletteButton extends Button {
	IEngine engine;
	private final GameManager gm;
	PaletteObject paletteObject;
	int index;
	public PurchaseAndSetPaletteButton(IEngine engine,int index,PaletteObject paletteObject){
		super();
		this.engine=engine;
		this.index=index;
		this.paletteObject=paletteObject;

		gm=((Logic) engine.getLogic()).getGameManager();

	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		if(gm.getMoney()>=paletteObject.getPrice()){
			gm.setMoney(gm.getMoney() - paletteObject.getPrice());

		}
		return true;
	}
}
