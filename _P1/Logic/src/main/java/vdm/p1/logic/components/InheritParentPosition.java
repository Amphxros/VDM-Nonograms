package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;

public final class InheritParentPosition extends Component {
	@Override
	public void handleParentScreenChange() {
		GameObject gameObject = getGameObject();
		gameObject.setPosition(gameObject.getParent().getPosition());
	}
}
