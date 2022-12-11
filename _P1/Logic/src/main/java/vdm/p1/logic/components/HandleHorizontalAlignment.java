package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.HorizontalAlignment;

public final class HandleHorizontalAlignment extends Component {
	private HorizontalAlignment alignment;

	public HandleHorizontalAlignment(HorizontalAlignment alignment) {
		this.alignment = alignment;
	}

	public HorizontalAlignment getAlignment() {
		return alignment;
	}

	public void setAlignment(HorizontalAlignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public void handleParentScreenChange() {
		GameObject gameObject = getGameObject();
		GameObject parent = gameObject.getParent();

		switch (alignment) {
			case NONE:
				break;
			case LEFT:
				gameObject.getPosition().setX(parent.getPosition().getX());
				break;
			case CENTRE:
				gameObject.getPosition().setX(parent.getPosition().getX() + (int) ((parent.getWidth() - gameObject.getWidth()) / 2.0));
				break;
			case RIGHT:
				gameObject.getPosition().setX(parent.getPosition().getX() + parent.getWidth() - gameObject.getWidth());
				break;
		}
	}
}
