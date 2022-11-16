package vdm.p2.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class BackgroundColor extends GameObject {
	private final Color color;

	public BackgroundColor(Color color) {
		this.color = color;
	}

	@Override
	public void render(IGraphics graphics) {
		if (!isEnabled()) {
			return;
		}

		super.render(graphics);

		graphics.setColor(color);
		graphics.fillRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();
		super.handleParentScreenChange();
	}
}
