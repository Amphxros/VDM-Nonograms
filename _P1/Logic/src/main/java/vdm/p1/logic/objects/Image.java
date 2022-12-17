package vdm.p1.logic.objects;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;

public final class Image extends GameObject {
	private IImage image;

	public Image(IImage image) {
		super();
		this.image = image;
	}

	public Image setImage(IImage image) {
		this.image = image;
		return this;
	}

	public void render(IGraphics graphics) {
		super.render(graphics);
		graphics.drawImage(image, getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
	}
}
