package vdm.p1.engine;

public interface IGraphics {
    IImage newImage(String name);

    IFont newFont(String name, int size, boolean isBold);

    /**
     * Draws an image with the original size at a point of the canvas.
     *
     * @param image The image to draw.
     * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     */
    void drawImage(IImage image, int x, int y);

    /**
     * Draws an image with the specified size at a point of the canvas.
     *
     * @param image  The image to draw.
     * @param x      The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y      The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param width  The width to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     * @param height The height to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     */
    void drawImage(IImage image, int x, int y, int width, int height);

    /**
     * Draws a text using the active font.
     *
     * @param text The text to draw in the destination canvas.
     * @param x    The x-axis coordinates from where to draw the text.
     * @param y    The y-axis coordinates from where to draw the text.
     * @see IGraphics#setFont
     */
    void drawText(String text, int x, int y);

    /**
     * Draws a filled square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    void fillRectangle(int x, int y, int side);

    /**
     * Draws a filled rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    void fillRectangle(int x, int y, int width, int height);

    /**
     * Draws a square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    void drawRectangle(int x, int y, int side);

    /**
     * Draws a rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    void drawRectangle(int x, int y, int width, int height);

    /**
     * Draws a line from (initX, initY) to (endX, endY).
     *
     * @param initX The x-axis coordinate of the starting point of the line.
     * @param initY The y-axis coordinate of the starting point of the line.
     * @param endX  The x-axis coordinate of the ending point of the line.
     * @param endY  The y-axis coordinate of the ending point of the line.
     */
    void drawLine(int initX, int initY, int endX, int endY);

    void setResolution(int width, int height);

    void setColor(int color);

    void setFont(IFont font);

    void present();

    void clear(int color);

    void translate(int x, int y);

    void scale(double x, double y);

    void save();

    void restore();

    int getWidth();

    int getHeight();

}
