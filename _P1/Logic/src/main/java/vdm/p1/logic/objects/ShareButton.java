package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.base.Button;

public class ShareButton extends Button {
	private final IEngine engine;

	public ShareButton(IEngine engine) {
		this.engine = engine;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		engine.getShareIntent().onShareIntent("compartir", "nonograms");
		return true;
	}
}
