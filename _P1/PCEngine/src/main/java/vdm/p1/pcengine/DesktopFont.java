package vdm.p1.pcengine;

import java.awt.Font;

import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;

public final class DesktopFont implements IFont {
	private final DesktopGraphics graphics;
	private final Font font;

	public DesktopFont(DesktopGraphics graphics, Font font) {
		this.graphics = graphics;
		this.font = font;
	}

	/**
	 * Gets the source size of the font.
	 *
	 * @return The source size of the font.
	 */
	@Override
	public int getSize() {
		return font.getSize();
	}

	/**
	 * Gets whether or not the font is bold.
	 *
	 * @return Whether or not the font is bold.
	 */
	@Override
	public boolean isBold() {
		return font.isBold();
	}

	@Override
	public IGraphics getGraphics() {
		return graphics;
	}

	/**
	 * Gets the underlying {@link Font} this instance hosts.
	 *
	 * @return The underlying {@link Font}.
	 */
	public Font getUnderlyingFont() {
		return font;
	}
}
