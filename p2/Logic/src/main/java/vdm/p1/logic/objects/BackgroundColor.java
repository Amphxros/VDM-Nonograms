package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IScene;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Vector2D;

public final class BackgroundColor extends GameObject {
	private final Color color;

	public BackgroundColor(IScene scene, Color color) {
		super(scene);
		this.color = color;
	}

	@Override
	public void render(IGraphics graphics) {
		super.render(graphics);

		graphics.setColor(color);
		graphics.fillRectangle(getX(), getY(), getWidth(), getHeight());
	}
}
