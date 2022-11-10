package vdm.p1.pcengine;

import java.awt.image.BufferedImage;

import vdm.p1.engine.IImage;

public final class DesktopImage implements IImage {
	private final BufferedImage image;

	public DesktopImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Gets the source width of the image.
	 *
	 * @return The source width of the image.
	 */
	@Override
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Gets the source height of the image.
	 *
	 * @return The source height of the image.
	 */
	@Override
	public int getHeight() {
		return image.getHeight(null);
	}

	/**
	 * Gets the underlying image.
	 *
	 * @return The buffered image.
	 */
	public BufferedImage getUnderlyingImage() {
		return image;
	}
}
