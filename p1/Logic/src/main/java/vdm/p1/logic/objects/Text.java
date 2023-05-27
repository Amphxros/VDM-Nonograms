package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IScene;
import vdm.p1.logic.GameObject;

public final class Text extends GameObject {
	private final IFont font;
	private String text;
	private Color color = Color.BLACK;
	private final HorizontalAlignment alignment;

	public Text(IScene scene, String text, IFont font) {
		this(scene, text, font, HorizontalAlignment.CENTRE);
	}

	public Text(IScene scene, String text, IFont font, HorizontalAlignment alignment) {
		super(scene);
		this.text = text;
		this.font = font;
		this.alignment = alignment;
	}

	public Text setColor(Color color) {
		this.color = color;
		return this;
	}

	public Text setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public void render(IGraphics graphics) {
		super.render(graphics);

		graphics.setColor(color);
		graphics.setFont(font);
		graphics.setTextAlignment(alignment);
		graphics.drawText(text, getX(), getY());
	}
}
