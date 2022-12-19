package vdm.p1.engine;

import java.util.ArrayList;

public final class Palette {
	public static final int BACKGROUND = 0;
	public static final int SELECT = 1;
	public static final int MARKED = 2;
	public static final int WRONG = 3;
	public static final int FONT = 4;

	private final ArrayList<Color> colors;

	public Palette() {
		colors = new ArrayList<>();
	}

	/**
	 * Adds a new Color.
	 *
	 * @param color {@link Color} to add in the palette
	 */
	public void addColor(Color color) {
		colors.add(color);
	}

	/**
	 * @param index The position of the {@link Color} to retrieve.
	 * @return The {@link Color} in the specified index.
	 */
	public Color getColor(int index) {
		return colors.get(index);
	}
}
