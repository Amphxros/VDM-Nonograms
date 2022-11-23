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

	public Dimension getTextDimensions(IFont font, String string) {
		FontMetrics metrics = canvas.getFontMetrics(((DesktopFont) font).getUnderlyingFont());
		return new Dimension(metrics.stringWidth(string), metrics.getHeight());
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
		canvas.drawString(text, x, y);
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
		canvas.fillRect(0, 0, getWidth(), getHeight());
	}

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

	@Override
	public int getWidth() {
		return window.getWidth();
	}

	@Override
	public int getHeight() {
		return window.getHeight();
	}
}
