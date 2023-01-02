package vdm.p1.logic;

import java.io.Serializable;

import vdm.p1.engine.Palette;

public final class PaletteItem implements Serializable {
	private final int id;
	private final Palette palette = new Palette();
	private final String name;
	private final int price;

	public PaletteItem(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
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
