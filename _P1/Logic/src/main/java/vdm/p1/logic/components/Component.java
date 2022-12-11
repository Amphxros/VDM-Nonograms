package vdm.p1.logic.components;

import vdm.p1.logic.GameObject;

public abstract class Component {
	private GameObject gameObject = null;

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	/**
	 * An event called when the parent's size or position change. This is not called automatically,
	 * and should be called manually based on whether or not the size has changed.
	 */
	public void handleParentScreenChange() {
	}
}
