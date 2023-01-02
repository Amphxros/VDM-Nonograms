package vdm.p1.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import vdm.p1.engine.IFont;

public final class AndroidFont implements IFont {
	private final Typeface font;
	private final int size;

	public AndroidFont(String route, AssetManager assetManager, int size, boolean isBold) {
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

	public Typeface getUnderlyingFont() {
		return font;
	}
}
