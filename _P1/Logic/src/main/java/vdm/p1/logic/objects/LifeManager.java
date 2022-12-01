package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public class LifeManager extends GameObject {
	private final IEngine engine;
	int life_number;
	IImage heartFill;
	IImage heartEmpty;

	public LifeManager(IEngine engine) {
		this.engine = engine;
		this.life_number = 3;

		heartFill = engine.getGraphics().newImage("image/heartfill.png");
		heartEmpty = engine.getGraphics().newImage("image/heartempty.png");

		Padding padding = new Padding(0.35, 0.01);
		for (int i = 0; i < life_number; i++) {
			GameObject g = new Image(heartFill)
					//casts the int (i+1) cause its NONE, LEFT, CENTRE, RIGHT
					.setHorizontalAlignment(HorizontalAlignment.values()[i + 1])
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			padding.addChild(g);
			}
			addChild(padding);
		}

		public boolean removeHeart () {
			life_number--;
			GameObject heart = new Image(heartEmpty);

			GameObject pad = getChildren().get(0); //gets the padding
			pad.getChildren().remove(life_number); //removes the last filled heart
			pad.addChild(heart); //adds an empty one
			return life_number > 0;
		}

		/**
		 * adds a heart and increase the life number useful when we watch an ad
		 */
		public void addHeart () {
			GameObject heart = new Image(heartFill);

			GameObject pad = getChildren().get(0); //gets the padding
			pad.getChildren().remove(life_number); //removes the last filled heart
			pad.addChild(heart); //adds a filled one
			life_number++;

		}

	@Override
	public void render(IGraphics graphics) {
		graphics.setColor(Color.BLACK);
		GameObject pad = getChildren().get(0);
		graphics.drawRectangle(pad.getPosition().getX(), pad.getPosition().getY(), pad.getWidth(), pad.getHeight());
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();

		super.handleParentScreenChange();
	}
}
