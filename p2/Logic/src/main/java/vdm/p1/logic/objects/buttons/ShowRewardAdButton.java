package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IAdsManager;
import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.LifeManager;
import vdm.p1.logic.objects.base.Button;

public class ShowRewardAdButton extends Button {
	IAdsManager adsManager;
	LifeManager lifeManager;
	public ShowRewardAdButton(IScene scene, LifeManager lifeManager) {
		super(scene);
		this.lifeManager= lifeManager;
		this.adsManager= getEngine().getAdManager();
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		adsManager.showRewardAd();
		return super.onPrimaryAction(event);
	}

	@Override
	public void update(double delta) {
		if(adsManager.onAdRewardShown())
			lifeManager.addHeart();
	}
}
