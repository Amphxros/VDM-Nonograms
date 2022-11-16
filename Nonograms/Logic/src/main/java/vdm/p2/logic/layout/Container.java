package vdm.p2.logic.layout;

import vdm.p2.logic.GameObject;

public final class Container extends GameObject {
	private final VerticalAlignment alignment;
	private final int innerW;
	private final int innerH;

	public Container(int innerW, int innerH) {
		this(innerW, innerH, VerticalAlignment.MIDDLE);
	}

	public Container(int innerW, int innerH, VerticalAlignment alignment) {
		super();
		this.innerW = innerW;
		this.innerH = innerH;
		this.alignment = alignment;
	}

	@Override
	public void handleParentScreenChange() {
		updateSize();
		updateHorizontalPosition();
		updateVerticalPosition();

		super.handleParentScreenChange();
	}

	private void updateSize() {
		GameObject parent = getParent();
		int parentW = parent.getWidth();
		int parentH = parent.getHeight();

		double ratioI = innerW / (double) innerH;
		double ratioP = parentW / (double) parentH;

		if (ratioI == ratioP) {
			// Container and Parent have the same ratio:
			//   → Set width and height of parent
			setWidth(parentW);
			setHeight(parentH);
		} else if (ratioI > ratioP) {
			// Container's height is smaller than Parent's:
			//   → Set width of parent, calculate height
			setWidth(parentW);
			setHeight((int) (parentW / ratioI));
		} else {
			// Container's width is smaller than Parent's:
			//   → Set height of parent, calculate width
			setWidth((int) (parentH * ratioI));
			setHeight(parentH);
		}
	}

	/**
	 * This must be called strictly after {@link Container#updateSize} for better results.
	 */
	private void updateHorizontalPosition() {
		int leftX = getParent().getPosition().getX();
		double midPW = getParent().getWidth() / 2.0;
		double midW = getWidth() / 2.0;

		getPosition().setX((int) (leftX + midPW - midW));
	}

	/**
	 * This must be called strictly after {@link Container#updateSize} for better results.
	 */
	private void updateVerticalPosition() {
		switch (alignment) {
			case TOP:
				updateVerticalTopPosition();
				break;
			case MIDDLE:
				updateVerticalMiddlePosition();
				break;
			case BOTTOM:
				updateVerticalBottomPosition();
				break;
		}
	}

	private void updateVerticalTopPosition() {
		getPosition().setY(getParent().getPosition().getY());
	}

	private void updateVerticalMiddlePosition() {
		int topY = getParent().getPosition().getY();
		double midPH = getParent().getHeight() / 2.0;
		double midH = getHeight() / 2.0;

		getPosition().setY((int) (topY + midPH - midH));
	}

	private void updateVerticalBottomPosition() {
		getPosition().setY(getParent().getPosition().getY() + getParent().getHeight() - getHeight());
	}
}
