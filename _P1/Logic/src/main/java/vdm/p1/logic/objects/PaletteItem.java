package vdm.p1.logic.objects;

import vdm.p1.engine.Palette;

public final class PaletteItem {
	private final Palette palette;
	private final String name;
	private final int price;

	public PaletteItem(String name, int price) {
		palette = new Palette();
		this.name = name;
		this.price = price;
	}

	public Palette getPalette() {
		return palette;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
