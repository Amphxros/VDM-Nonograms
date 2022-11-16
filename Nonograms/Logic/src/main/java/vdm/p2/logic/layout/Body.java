package vdm.p2.logic.layout;

import vdm.p1.engine.IEngine;
import vdm.p1.logic.GameObject;

public final class Body extends GameObject {
	private final IEngine engine;

	public Body(IEngine engine) {
		super();
		this.engine = engine;
	}

	@Override
	public void update(double delta) {
		// Detect if the screen size has changed, if it did, send a signal to the children elements.
		if (detectSizeChanges()) this.handleParentScreenChange();

		super.update(delta);
	}

	private boolean detectSizeChanges() {
		boolean changed = false;
		if (getWidth() != engine.getWidth()) {
			setWidth(engine.getWidth());
			changed = true;
		}

		if (getHeight() != engine.getHeight()) {
			setHeight(engine.getHeight());
			changed = true;
		}

		return changed;
	}
}
