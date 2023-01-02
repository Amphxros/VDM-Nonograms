package vdm.p1.logic.objects;

import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.engine.IScene;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Vector2D;

/**
 * The life manager GameObject. This object will always attach children and components that are
 * necessary for it to be visualized correctly.
 */
public final class LifeManager extends GameObject {
	private final IImage heartFill;
	private final IImage heartEmpty;
	private final IFont font;
	private int remainingLives = 3;

	public LifeManager(IScene scene, IFont font) {
		super(scene);
		this.font = font;

		heartFill = getEngine().getGraphics().newImage("image/heartfill.png");
		heartEmpty = getEngine().getGraphics().newImage("image/heartempty.png");
	}

	@Override
	public void init() {
		final int textOffsetY = 5;
		final int heartOffsetY = 5;

		addChild(new Text(getScene(), Integer.toString(this.remainingLives), font).setPosition(getX() + getWidth() / 2, getY() + textOffsetY));

		addChild(createHeart(heartFill).setPosition(getX(), getY() + heartOffsetY));
		addChild(createHeart(heartFill).setPosition(getX() + getWidth() / 2 - 20, getY() + heartOffsetY));
		addChild(createHeart(heartFill).setPosition(getX() + getWidth() - 40, getY() + heartOffsetY));

		super.init();
	}

	/**
	 * adds a heart and increase the life number useful when we watch an ad
	 */
	public boolean addHeart() {
		if (remainingLives == 3) {
			return false;
		}

		++remainingLives;
		((Text) getChildren().get(0)).setText(Integer.toString(remainingLives));
		((Image) getChildren().get(remainingLives)).setImage(heartFill);
		return true;
	}

	/**
	 * @return Whether or not the number of lives is greater than 0
	 */
	public boolean removeHeart() {
		if (remainingLives <= 1) {
			return false;
		}

		--remainingLives;
		((Text) getChildren().get(0)).setText(Integer.toString(remainingLives));
		((Image) getChildren().get(remainingLives + 1)).setImage(heartEmpty);
		return remainingLives > 0;
	}

	private GameObject createHeart(IImage image) {
		return new Image(getScene(), image).setSize(new Vector2D(40, 40));
	}
}
