package vdm.p2.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class Text extends GameObject {
	private final String text;
	private final IFont font;
	private Color color = Color.BLACK;

	public Text(String text, IFont font) {
		super();
		this.text = text;
		this.font = font;

		Dimension dimension = font.getGraphics().getTextDimensions(font, text);
		setWidth(dimension.getWidth());
		setHeight(dimension.getHeight());
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void render(IGraphics graphics) {
		if (!isEnabled()) {
			return;
		}

		super.render(graphics);

		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawText(text, getPosition().getX(), getPosition().getY() + getHeight());
	}
}
