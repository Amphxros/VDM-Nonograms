package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public class LifeManager extends GameObject {
	private final IEngine engine;
	private int remainingLives;

	public LifeManager(IEngine engine) {
		this.engine = engine;
		this.remainingLives = 3;

		GameObject heartLeft = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.TOP);

		GameObject heartCenter = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);

		GameObject heartRight = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.TOP);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(heartLeft)
				.addChild(heartCenter)
				.addChild(heartRight);

		addChild(padding);
	}

	public boolean removeHeart() {
		//GameObject pass
		return --remainingLives > 0;
	}
}
