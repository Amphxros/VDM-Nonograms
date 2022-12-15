package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class Text extends GameObject {
	private final IFont font;
	private String text;
	private Color color = Color.BLACK;

	public Text(String text, IFont font) {
		super();
		this.font = font;

		setText(text);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;

		Dimension dimension = font.getGraphics().getTextDimensions(font, text);
		setWidth(dimension.getWidth());
		setHeight(dimension.getHeight());
	}

	@Override
	public void render(IGraphics graphics) {
		super.render(graphics);

		/**
		 * CHANGE THIS TO PALETTE TOO
		 */
		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawText(text, getPosition().getX(), getPosition().getY() + getHeight());
	}
}
