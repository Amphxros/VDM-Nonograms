package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public class LifeManager extends GameObject {
	private final IEngine engine;
	private int remainingLives;

<<<<<<< HEAD
	IEngine engine;
	int life_number;
	IImage heartFill;
	IImage heartEmpty;

	public LifeManager(IEngine engine){
		this.engine=engine;
		this.life_number=3;

		heartFill= engine.getGraphics().newImage("image/heartfill.png");
		heartEmpty= engine.getGraphics().newImage("image/heartempty.png");

		Padding padding= new Padding(0.04, 0.01);
		for(int i=0;i<life_number;i++){
			GameObject g= new Image(heartFill)
					//casts the int (i+1) cause its NONE, LEFT, CENTRE, RIGHT
					.setHorizontalAlignment(HorizontalAlignment.values()[i+1])
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			padding.addChild(g);
		}
=======
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
>>>>>>> 69f6017086b8e72e4543c1c8c4efe28eb5d33d6e

		addChild(padding);

	}

<<<<<<< HEAD
	public boolean removeHeart(){

		life_number--;
		GameObject heart= new Image(heartEmpty);

		GameObject pad= getChildren().get(0); //gets the padding
		pad.getChildren().remove(life_number); //removes the last filled heart
		pad.addChild(heart); //adds an empty one
		return life_number > 0;
=======
	public boolean removeHeart() {
		//GameObject pass
		return --remainingLives > 0;
>>>>>>> 69f6017086b8e72e4543c1c8c4efe28eb5d33d6e
	}

	/**
	 * adds a heart and increase the life number useful when we watch an ad
	 */
	public void addHeart(){
		GameObject heart= new Image(heartFill);

		GameObject pad= getChildren().get(0); //gets the padding
		pad.getChildren().remove(life_number); //removes the last filled heart
		pad.addChild(heart); //adds a filled one
		life_number++;

	}
}
