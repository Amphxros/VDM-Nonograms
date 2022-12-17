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
		font = Typeface.create(new Typeface.Builder(assetManager, route).setWeight(size).build(), isBold ? Typeface.NORMAL : Typeface.BOLD);
	}

	public Typeface getFont() {
		return font;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isBold() {
		return font.isBold();
	}

	@Override
	public IGraphics getGraphics() {
		return graphics;
	}

	public Typeface getUnderlyingFont() {
		return font;
	}
}
