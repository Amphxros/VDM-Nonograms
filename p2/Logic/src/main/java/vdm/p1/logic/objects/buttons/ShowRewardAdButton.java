package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IAdsManager;
import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.LifeManager;
import vdm.p1.logic.objects.base.Button;

public final class ShowRewardAdButton extends Button {
	private final IAdsManager adsManager;
	private final LifeManager lifeManager;

	public ShowRewardAdButton(IScene scene, IAdsManager adsManager, LifeManager lifeManager) {
		super(scene);
		this.lifeManager = lifeManager;
		this.adsManager = adsManager;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		adsManager.showRewardAd();
		return super.onPrimaryAction(event);
	}

	@Override
	public void update(double delta) {
		if (adsManager.onAdRewardShown())
			lifeManager.addHeart();
	}
}
