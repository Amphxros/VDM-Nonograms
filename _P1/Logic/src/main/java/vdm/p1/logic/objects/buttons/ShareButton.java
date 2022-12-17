package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.base.Button;

public class ShareButton extends Button {
	private final IEngine engine;
	private final String imagePath;

	public ShareButton(IEngine engine, String imagePath) {
		this.imagePath = imagePath;
		this.engine = engine;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		engine.getShareIntent().share(imagePath);
		return true;
	}
}