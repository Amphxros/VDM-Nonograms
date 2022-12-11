package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;

public final class BackgroundColor extends GameObject {
	private final Color color;

	public BackgroundColor(Color color) {
		this.color = color;
		this.addComponent(new InheritParentSize());
		this.addComponent(new InheritParentPosition());
	}

	@Override
	public void render(IGraphics graphics) {
		super.render(graphics);

		graphics.setColor(color);
		graphics.fillRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
	}
}
