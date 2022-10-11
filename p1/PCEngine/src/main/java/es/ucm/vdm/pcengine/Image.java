package es.ucm.vdm.pcengine;

import java.awt.image.BufferedImage;

import es.ucm.vdm.engine.IImage;

public class Image implements IImage {
    private final BufferedImage image;

    public Image(BufferedImage image) {
        this.image = image;
    }

    /**
     * Gets the source width of the image.
     * @return The source width of the image.
     */
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Gets the source height of the image.
     * @return The source height of the image.
     */
    @Override
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Gets the loaded buffered image.
     * @return The buffered image.
     */
    public BufferedImage getImage() {
        return image;
    }
}
