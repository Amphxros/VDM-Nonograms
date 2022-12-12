package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.HandleParentResize;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

/**
 * The life manager GameObject. This object will always attach children and components that are
 * necessary for it to be visualized correctly:<br><br>
 *
 * <b>Children</b>:
 * <ul>
 *     <li>{@link Padding}
 *         <ul>
 *             <li>{@link Text}</li>
 *             <li>{@link Grid}
 *                 <ul>
 *                     <li>{@link Image}</li>
 *                     <li>{@link Image}</li>
 *                     <li>{@link Image}</li>
 *                 </ul>
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <b>Components</b>:
 * <ul>
 *     <li>{@link InheritParentSize}</li>
 *     <li>{@link InheritParentPosition}</li>
 * </ul>
 */
public final class LifeManager extends GameObject {
	private final IEngine engine;
	private final IImage heartFill;
	private final IImage heartEmpty;
	private int remainingLives = 3;

	public LifeManager(IEngine engine, IFont font) {
		this.engine = engine;

		heartFill = engine.getGraphics().newImage("image/heartfill.png");
		heartEmpty = engine.getGraphics().newImage("image/heartempty.png");

		Padding padding = new Padding(0.35, 0.01);
		GameObject text = new Text(Integer.toString(this.remainingLives), font)
				.setVerticalAlignment(VerticalAlignment.TOP)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE);
		Grid grid = (Grid) new Grid(3, FlowDirection.HORIZONTAL)
				.setVerticalAlignment(VerticalAlignment.BOTTOM)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE);
		for (int i = 0; i < remainingLives; i++) {
			grid.setElement(i, createHeart(heartFill));
		}

		padding.addChild(text).addChild(grid);
		addChild(padding);

		addComponent(new InheritParentSize());
		addComponent(new InheritParentPosition());
	}

	public IEngine getEngine() {
		return engine;
	}

	/**
	 * adds a heart and increase the life number useful when we watch an ad
	 */
	public boolean addHeart() {
		if (remainingLives == 3) {
			return false;
		}

		++remainingLives;

		// Gets the padding object the components are at:
		GameObject padding = getChildren().get(0);
		Text text = (Text) padding.getChildren().get(0);
		text.setText(Integer.toString(remainingLives));

		Grid grid = (Grid) padding.getChildren().get(1);
		grid.setElement(remainingLives, createHeart(heartFill));
		return true;
	}

	public boolean removeHeart() {
		if (remainingLives == 0) {
			return false;
		}

		--remainingLives;

		// Gets the padding object the components are at:
		GameObject padding = getChildren().get(0);
		Text text = (Text) padding.getChildren().get(0);
		text.setText(Integer.toString(remainingLives));

		Grid grid = (Grid) padding.getChildren().get(1);
		grid.setElement(remainingLives, createHeart(heartEmpty));

		return true;
	}

	private GameObject createHeart(IImage image) {
		return new Image(image)
				.addComponent(new HandleParentResize(1))
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE);
	}
}
