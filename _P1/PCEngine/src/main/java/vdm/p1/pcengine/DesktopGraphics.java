package vdm.p1.pcengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public final class DesktopGraphics implements IGraphics {
	private final JFrame window;
	private final BufferStrategy buffer;
	private Graphics2D canvas;

	public DesktopGraphics(JFrame window) {
		this.window = window;
		int attempts = 10;

		while (attempts > 0) {
			try {
				this.window.createBufferStrategy(2);
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			attempts--;
		}

		this.buffer = this.window.getBufferStrategy();
		this.canvas = (Graphics2D) this.buffer.getDrawGraphics();
	}

	/**
	 *
	 * @param name route of the image
	 * @return an instance of {@link DesktopImage}
	 */
	@Override
	public IImage newImage(String name) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File("Assets/" + name));
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}

		return new DesktopImage(image);
	}


	/**
	 *
	 * @param name route of the font
	 * @return an instance of {@link DesktopFont}
	 */
	@Override
	public IFont newFont(String name, int size, boolean isBold) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/" + name));
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}

		font = font.deriveFont(isBold ? Font.BOLD : Font.PLAIN, (float) size);
		return new DesktopFont(this, font);
	}

	/**
	 *
	 * @param font to write with
	 * @param string message to write
	 * @return size of that message
	 */
	public Dimension getTextDimensions(IFont font, String string) {
		FontMetrics metrics = canvas.getFontMetrics(((DesktopFont) font).getUnderlyingFont());
		return new Dimension(metrics.stringWidth(string), metrics.getHeight());
	}

	/**
	 *
	 * @param image The image to draw.
	 * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 */
	@Override
	public void drawImage(IImage image, int x, int y) {
		canvas.drawImage(((DesktopImage) image).getUnderlyingImage(), x, y, null);
	}

	/**
	 *
	 * @param image  The image to draw.
	 * @param x      The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 * @param y      The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 * @param width  The width to raw the `image` in the destination canvas. This allows scaling of the drawn image.
	 * @param height The height to raw the `image` in the destination canvas. This allows scaling of the drawn image.
	 */
	@Override
	public void drawImage(IImage image, int x, int y, int width, int height) {
		canvas.drawImage(((DesktopImage) image).getUnderlyingImage(), x, y, width, height, null);
	}

	/**
	 *
	 * @param text The text to draw in the destination canvas.
	 * @param x    The x-axis coordinates from where to draw the text.
	 * @param y    The y-axis coordinates from where to draw the text.
	 */
	@Override
	public void drawText(String text, int x, int y) {
		canvas.drawString(text, x, y);
	}

	/**
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
	 *
	 * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
	 * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
	 * @param width  The width of the rectangle to draw.
	 * @param height The height of the rectangle to draw.
	 */
	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		canvas.fillRect(x, y, width, height);
	}

	/**
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
	 *
	 * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
	 * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
	 * @param width  The width of the rectangle to draw.
	 * @param height The height of the rectangle to draw.
	 */
	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		canvas.drawRect(x, y, width, height);
	}

	/**
	 *
	 * @param initX The x-axis coordinate of the starting point of the line.
	 * @param initY The y-axis coordinate of the starting point of the line.
	 * @param endX  The x-axis coordinate of the ending point of the line.
	 * @param endY  The y-axis coordinate of the ending point of the line.
	 */
	@Override
	public void drawLine(int initX, int initY, int endX, int endY) {
		canvas.drawLine(initX, initY, endX, endY);
	}

	/**
	 *
	 * @param width of the window
	 * @param height of the window
	 */
	@Override
	public void setResolution(int width, int height) {
		window.setSize(width, height);
	}

	/**
	 *
	 * @param color Sets the current color with a raw RGBA integer.
	 */
	@Override
	public void setColor(int color) {
		canvas.setColor(new Color(color, true));
	}

	/**
	 *
	 * @param color Sets the current color with a {@link Color} instance.
	 */
	@Override
	public void setColor(vdm.p1.engine.Color color) {
		canvas.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
	}

	/**
	 * sets the font to write
	 * @param font
	 */
	@Override
	public void setFont(IFont font) {
		canvas.setFont(((DesktopFont) font).getUnderlyingFont());
	}

	/**
	 * Presents the render
	 */
	@Override
	public void present() {
		// Show the buffer, then dispose the current canvas and replace with an updated one.
		buffer.show();
		canvas.dispose();
		canvas = (Graphics2D) buffer.getDrawGraphics();
	}

	/**
	 * clears the render
	 * @param color of the background
	 */
	@Override
	public void clear(vdm.p1.engine.Color color) {
		setColor(color);
		canvas.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * clears the render
	 * @param color of the background
	 */
	@Override
	public void clear(int color) {
		setColor(color);
		canvas.fillRect(0, 0, getWidth(), getHeight());
	}


	@Override
	public void translate(int x, int y) {
		canvas.translate(x, y);
	}

	@Override
	public void scale(double x, double y) {
		canvas.scale(x, y);
	}

	@Override
	public void save() {

	}

	@Override
	public void restore() {

	}

	/**
	 *
	 * @return the width of the window
	 */
	@Override
	public int getWidth() {
		return window.getWidth();
	}

	/**
	 *
	 * @return the height of the window
	 */
	@Override
	public int getHeight() {
		return window.getHeight();
	}
}
