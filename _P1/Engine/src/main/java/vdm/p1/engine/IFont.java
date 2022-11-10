package vdm.p1.engine;

public interface IFont {
	/**
	 * Gets the source size of the font.
	 *
	 * @return The source size of the font.
	 */
	int getSize();

	/**
	 * Gets whether or not the font is bold.
	 *
	 * @return Whether or not the font is bold.
	 */
	boolean isBold();

	IGraphics getGraphics();
}
