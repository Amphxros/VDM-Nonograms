package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.VerticalAlignment;

public final class HandleVerticalAlignment extends Component {
	private VerticalAlignment alignment;

	public HandleVerticalAlignment(VerticalAlignment alignment) {
		this.alignment = alignment;
	}

	public VerticalAlignment getAlignment() {
		return alignment;
	}

	public void setAlignment(VerticalAlignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public void handleParentScreenChange() {
		GameObject gameObject = getGameObject();
		GameObject parent = gameObject.getParent();

		switch (alignment) {
			case NONE:
				break;
			case TOP:
				gameObject.getPosition().setY(parent.getPosition().getY());
				break;
			case MIDDLE:
				gameObject.getPosition().setY(parent.getPosition().getY() + (int) ((parent.getHeight() - gameObject.getHeight()) / 2.0));
				break;
			case BOTTOM:
				gameObject.getPosition().setY(parent.getPosition().getY() + parent.getHeight() - gameObject.getHeight());
				break;
		}
	}
}
