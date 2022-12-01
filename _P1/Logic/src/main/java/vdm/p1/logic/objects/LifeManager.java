package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public class LifeManager extends GameObject {

	IEngine engine;
	int life_number;
	public LifeManager(IEngine engine){
		this.engine=engine;
		this.life_number=3;
		GameObject heart_left = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.TOP);


		GameObject heart_center = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);


		GameObject heart_right = new Image(engine.getGraphics().newImage("image/heartfill.png"))
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.TOP);


		GameObject padding= new Padding(0.04,0.1)
				.addChild(heart_left)
				.addChild(heart_center)
				.addChild(heart_right);

		addChild(padding);
	}

	public boolean removeHeart(){
		life_number--;
		GameObject pass
		return life_number > 0;
	}
}
