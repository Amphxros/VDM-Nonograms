package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;

public final class HandleParentResize extends Component {
	private final double ratio;

	public HandleParentResize(double width, double height) {
		this(width / height);
	}

	public HandleParentResize(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public void handleParentScreenChange() {
		GameObject gameObject = getGameObject();
		GameObject parent = gameObject.getParent();

		int parentW = parent.getWidth();
		int parentH = parent.getHeight();

		double ratioP = parentW / (double) parentH;

		if (ratio == ratioP) {
			// Container and Parent have the same ratio:
			//   → Set width and height of parent
			gameObject.setWidth(parentW);
			gameObject.setHeight(parentH);
		} else if (ratio > ratioP) {
			// Container's height is smaller than Parent's:
			//   → Set width of parent, calculate height
			gameObject.setWidth(parentW);
			gameObject.setHeight((int) (parentW / ratio));
		} else {
			// Container's width is smaller than Parent's:
			//   → Set height of parent, calculate width
			gameObject.setWidth((int) (parentH * ratio));
			gameObject.setHeight(parentH);
		}
	}
}
