package es.ucm.vdm.pcengine;

import es.ucm.vdm.engine.IFont;

public class Font implements IFont {
    private final java.awt.Font font;

    public Font(java.awt.Font font) {
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

    /**
     * Gets the underlying {@link java.awt.Font} this instance hosts.
     *
     * @return The underlying {@link java.awt.Font}.
     */
    public java.awt.Font getFont() {
        return font;
    }
}
