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
	private final IImage heartFill;
	private final IImage heartEmpty;
	private final Table table;
	private int remainingLives = 3;

	public LifeManager(IEngine engine, IFont font, Table table) {
		this.engine = engine;
		this.table = table;

		heartFill = engine.getGraphics().newImage("image/heartfill.png");
		heartEmpty = engine.getGraphics().newImage("image/heartempty.png");

		Padding padding = new Padding(0.35, 0.01);
		GameObject text = new Text(Integer.toString(this.remainingLives), font)
				.setVerticalAlignment(VerticalAlignment.TOP)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE);
		for (int i = 0; i < remainingLives; i++) {
			GameObject g = new Image(heartFill)
					// Casts the int (i + 1) because it starts with NONE
					.setHorizontalAlignment(HorizontalAlignment.values()[i + 1])
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			g.setWidth(2 * text.getWidth());
			g.setHeight(2 * text.getHeight());
			padding.addChild(g);
		}
		padding.addChild(text);
		addChild(padding);
	}

	public boolean removeHeart() {
		if (remainingLives == 0) {
			return false;
		}

		--remainingLives;
		if (remainingLives >= 0) {
			GameObject heart = new Image(heartEmpty);

			// Gets the padding object the hearts are at:
			GameObject pad = getChildren().get(0);
			Text text = (Text) pad.getChildren().get(remainingLives + 1);
			text.setText(Integer.toString(remainingLives));

			heart.setWidth(2 * text.getWidth());
			heart.setWidth(2 * text.getHeight());

			pad.getChildren().remove(remainingLives); //removes the last filled heart
			pad.addChild(heart); //adds an empty one
		}

		return remainingLives > 0;
	}

	/**
	 * adds a heart and increase the life number useful when we watch an ad
	 */
	public void addHeart() {
		// Gets the padding object the hearts are at:
		GameObject pad = getChildren().get(0);

		// Swap an empty heart with a filled one by removing the last empty heart and adding a
		// filled one:
		pad.getChildren().remove(remainingLives);
		pad.addChild(new Image(heartFill));
		++remainingLives;
	}

	@Override
	public void update(double delta) {
		super.update(delta);

		// Remove a heart for each wrong cell:
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
