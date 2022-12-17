package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IShareIntent;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.base.Button;

public class ShareButton extends Button {
	private final IShareIntent intent;
	private final String imagePath;

	public ShareButton(IShareIntent intent, String imagePath) {
		this.imagePath = imagePath;
		this.intent = intent;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		intent.share(imagePath);
		return true;
	}
}
