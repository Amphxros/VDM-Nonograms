package vdm.p1.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;

public final class AndroidFont implements IFont {
	private final AndroidGraphics graphics;
	private final Typeface font;
	private final int size;

	public AndroidFont(AndroidGraphics graphics, String route, AssetManager assetManager, int size, boolean isBold) {
		this.graphics = graphics;
		this.size = size;
		font = Typeface.create(Typeface.createFromAsset(assetManager, route), size, isBold);
	}

	/**
	 *
	 * @return the typeface of the font
	 */
	public Typeface getFont() {
		return font;
	}

	/**
	 *
	 * @return the size of the font
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 *
	 * @return Whether or not the font is bold.
	 */
	@Override
	public boolean isBold() {
		return font.isBold();
	}

	/**
	 *
	 * An {@link IGraphics} instance.
	 */
	@Override
	public IGraphics getGraphics() {
		return graphics;
	}

	/**
	 *
	 * @return the typeface of the font
	 */
	public Typeface getUnderlyingFont() {
		return font;
	}
}
