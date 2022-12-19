package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IScene;
import vdm.p1.logic.objects.base.GoToSceneButton;
import vdm.p1.logic.scenes.Scene;
import vdm.p1.logic.scenes.ShopScene;

public final class GoToShopSceneButton extends GoToSceneButton {
	public GoToShopSceneButton(IScene scene) {
		super(scene);
	}

	@Override
	protected Scene createScene() {
		return new ShopScene(getEngine());
	}
}
