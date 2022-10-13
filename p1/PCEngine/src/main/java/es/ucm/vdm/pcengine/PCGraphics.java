package es.ucm.vdm.pcengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import es.ucm.vdm.engine.IFont;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IImage;

public class PCGraphics implements IGraphics {
    private final JFrame view;
    private BufferStrategy buffer;
    private Graphics2D graphics2D;
    private Stack<Graphics2D> states;

    public PCGraphics() {
        view = new JFrame();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setIgnoreRepaint(true);
        view.setVisible(true);

        int retries = 100;
        while (retries-- > 0) {
            try {
                view.createBufferStrategy(2);
                break;
            } catch (Exception e) {
                // NOP
            }
        }

        if (retries == 0) {
            System.err.println("The BufferStrategy couldn't be created.");
            return;
        }

        buffer = view.getBufferStrategy();
        graphics2D = (Graphics2D) buffer.getDrawGraphics();
        states = new Stack<>();
    }

    @Override
    public Image newImage(String name) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(name));
        } catch (Exception ignored) {
            return null;
        }

        return new Image(image);
    }

    @Override
    public Font newFont(String name, int size, boolean isBold) {
        java.awt.Font font;
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File(name));
        } catch (Exception ignored) {
            return null;
        }

        font.deriveFont(size);
        font.deriveFont(isBold ? java.awt.Font.BOLD : java.awt.Font.PLAIN);
        return new Font(font);
    }

    /**
     * Draws an image with the original size at a point of the canvas.
     *
     * @param image The image to draw.
     * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     */
    @Override
    public void drawImage(IImage image, int x, int y) {
        graphics2D.drawImage(((Image) image).getImage(), x, y, null);
    }

    /**
     * Draws an image with the specified size at a point of the canvas.
     *
     * @param image  The image to draw.
     * @param x      The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y      The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param width  The width to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     * @param height The height to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     */
    @Override
    public void drawImage(IImage image, int x, int y, int width, int height) {
        graphics2D.drawImage(((Image) image).getImage(), x, y, width, height, null);
    }

    /**
     * Draws a text using the active font.
     *
     * @param text The text to draw in the destination canvas.
     * @param x    The x-axis coordinates from where to draw the text.
     * @param y    The y-axis coordinates from where to draw the text.
     * @see IGraphics#setFont
     */
    @Override
    public void drawText(String text, int x, int y) {
        graphics2D.drawString(text, x, y);
    }

    /**
     * Draws a filled square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int side) {
        fillRectangle(x, y, side, side);
    }

    /**
     * Draws a filled rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        graphics2D.fillRect(x, y, width, height);
    }

    /**
     * Draws a square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    /**
     * Draws a rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        graphics2D.drawRect(x, y, width, height);
    }

    /**
     * Draws a line from (initX, initY) to (endX, endY).
     *
     * @param initX The x-axis coordinate of the starting point of the line.
     * @param initY The y-axis coordinate of the starting point of the line.
     * @param endX  The x-axis coordinate of the ending point of the line.
     * @param endY  The y-axis coordinate of the ending point of the line.
     */
    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        graphics2D.drawLine(initX, initY, endX, endY);
    }

    @Override
    public void setResolution(int width, int height) {
        view.setSize(width, height);
    }

    @Override
    public void setColor(int color) {
        graphics2D.setColor(new Color(color));
    }

    @Override
    public void setFont(IFont font) {
        graphics2D.setFont(((Font) font).getFont());
    }

    @Override
    public void clear(int color) {
        graphics2D.setColor(new Color(color));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void translate(int x, int y) {
        graphics2D.translate(x, y);
    }

    @Override
    public void scale(double x, double y) {
        graphics2D.scale(x, y);
    }

    @Override
    public void save() {
        Graphics2D clone = (Graphics2D) graphics2D.create();
        states.push(graphics2D);
        graphics2D = clone;
    }

    @Override
    public void restore() {
        if (states.empty()) return;
        graphics2D.dispose();
        graphics2D = states.pop();
    }

    @Override
    public int getWidth() {
        return view.getWidth();
    }

    @Override
    public int getHeight() {
        return view.getHeight();
    }

    public BufferStrategy getBuffer() {
        return buffer;
    }
}
