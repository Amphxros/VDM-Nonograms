package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;

public final class InheritParentSize extends Component {
	@Override
	public void handleParentScreenChange() {
		GameObject gameObject = getGameObject();
		GameObject parent = gameObject.getParent();
		gameObject.setWidth(parent.getWidth());
		gameObject.setHeight(parent.getHeight());
	}
}
