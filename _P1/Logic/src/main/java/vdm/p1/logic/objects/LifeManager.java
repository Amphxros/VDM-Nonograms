package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
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
	Table table;

	public LifeManager(IEngine engine, IFont font, Table table) {
		this.engine = engine;
		this.life_number = 3;
		this.table=table;

		heartFill = engine.getGraphics().newImage("image/heartfill.png");
		heartEmpty = engine.getGraphics().newImage("image/heartempty.png");

		Padding padding = new Padding(0.35, 0.01);
		GameObject text= new Text(Integer.toString(this.life_number),font)
				.setVerticalAlignment(VerticalAlignment.TOP)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE);
		for (int i = 0; i < life_number; i++) {
			GameObject g = new Image(heartFill)
					//casts the int (i+1) cause its NONE, LEFT, CENTRE, RIGHT
					.setHorizontalAlignment(HorizontalAlignment.values()[i + 1])
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			g.setWidth(2*text.getWidth());
			g.setHeight(2*text.getHeight());
			padding.addChild(g);
			}
			padding.addChild(text);
			addChild(padding);
		}

		public boolean removeHeart () {

			life_number--;
			if(life_number>=0) {
				GameObject heart = new Image(heartEmpty);

				GameObject pad = getChildren().get(0); //gets the padding
				Text text= (Text)pad.getChildren().get(life_number+1);
				text.setText(Integer.toString(life_number));

				heart.setWidth(2*text.getWidth());
				heart.setWidth(2*text.getHeight());

				pad.getChildren().remove(life_number); //removes the last filled heart
				pad.addChild(heart); //adds an empty one




			}
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
	public void update(double delta) {
		super.update(delta);

		//checks if any cell is wrong to remove a heart
		for (Cell[] cells : table.getCells()) {
			for (Cell cell : cells) {
				if (cell.isWrong()) {
					cell.setWrong(true);
					removeHeart();

				}
			}
		}
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();

		super.handleParentScreenChange();
	}
}
