package vdm.p1.pcengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import vdm.p1.engine.GraphicsTransformer;
import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public final class DesktopGraphics implements IGraphics {
	private final JFrame window;
	private final BufferStrategy buffer;
	private Graphics2D canvas;
	private HorizontalAlignment textAlignment;
	private final GraphicsTransformer transformer = new GraphicsTransformer();

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
	 * Sets the text alignment for text.
	 *
	 * @param alignment The alignment to use.
	 */
	@Override
	public void setTextAlignment(HorizontalAlignment alignment) {
		textAlignment = alignment;
	}

	@Override
	public void drawImage(IImage image, int x, int y) {
		canvas.drawImage(((DesktopImage) image).getUnderlyingImage(), x, y, null);
	}

	@Override
	public void drawImage(IImage image, int x, int y, int width, int height) {
		canvas.drawImage(((DesktopImage) image).getUnderlyingImage(), x, y, width, height, null);
	}

	@Override
	public void drawText(String text, int x, int y) {
		int outX = x;
		if (textAlignment == HorizontalAlignment.CENTRE) {
			outX -= canvas.getFontMetrics().stringWidth(text) / 2;
		} else if (textAlignment == HorizontalAlignment.RIGHT) {
			outX -= canvas.getFontMetrics().stringWidth(text);
		}

		canvas.drawString(text, outX, y);
	}

	@Override
	public void fillRectangle(int x, int y, int side) {
		fillRectangle(x, y, side, side);
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		canvas.fillRect(x, y, width, height);
	}

	@Override
	public void drawRectangle(int x, int y, int side) {
		drawRectangle(x, y, side, side);
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		canvas.drawRect(x, y, width, height);
	}

	@Override
	public void drawLine(int initX, int initY, int endX, int endY) {
		canvas.drawLine(initX, initY, endX, endY);
	}

	@Override
	public void setResolution(int width, int height) {
		window.setSize(width, height);
		transformer.setSize(width, height);
	}

	@Override
	public void setColor(int color) {
		canvas.setColor(new Color(color, true));
	}

	@Override
	public void setColor(vdm.p1.engine.Color color) {
		canvas.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
	}

	@Override
	public void setFont(IFont font) {
		canvas.setFont(((DesktopFont) font).getUnderlyingFont());
	}

	@Override
	public void present() {
		// Show the buffer, then dispose the current canvas and replace with an updated one.
		buffer.show();
		canvas.dispose();
		canvas = (Graphics2D) buffer.getDrawGraphics();
	}

	@Override
	public void clear(vdm.p1.engine.Color color) {
		setColor(color);
		canvas.fillRect(0, 0, window.getWidth(), window.getHeight());
		updateTransformParameters();
	}

	@Override
	public void clear(int color) {
		setColor(color);
		canvas.fillRect(0, 0, window.getWidth(), window.getHeight());
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

	@Override
	public int getWidth() {
		return transformer.getWidth();
	}

	@Override
	public int getHeight() {
		return transformer.getHeight();
	}

	/**
	 * Transforms the window X-axis point into a value within 0 and {@link #getWidth()}, returns -1
	 * if the resulting value is out of bounds.
	 *
	 * @param x The window X-axis point to transform.
	 * @return The scene X-axis point, -1 if invalid.
	 */
	@Override
	public int getLogicPointX(int x) {
		return transformer.getTransformedX(x);
	}

	/**
	 * Transforms the window Y-axis point into a value within 0 and {@link #getHeight()}, returns -1
	 * if the resulting value is out of bounds.
	 *
	 * @param y The window Y-axis point to transform.
	 * @return The scene Y-axis point, -1 if invalid.
	 */
	@Override
	public int getLogicPointY(int y) {
		return transformer.getTransformedY(y);
	}

	/**
	 * Updates the transform parameters, internally calls {@link #translate(int, int)} and
	 * {@link #scale(double, double)} internally.
	 */
	private void updateTransformParameters() {
		Insets insets = window.getInsets();
		int contentW = window.getWidth() - insets.left - insets.right;
		int contentH = window.getHeight() - insets.top - insets.bottom;

		transformer.setInset(insets.top, insets.left, insets.bottom, insets.right);
		transformer.update(contentW, contentH);
		transformer.transform(this);
	}
}
