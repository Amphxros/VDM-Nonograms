package vdm.p1.logic.objects.base;

import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;

public abstract class AdRewardButton extends Button {
	public AdRewardButton(IScene scene) {
		super(scene);
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		getEngine().getAdSystem().showRewardAd();
		return true;
	}
}
