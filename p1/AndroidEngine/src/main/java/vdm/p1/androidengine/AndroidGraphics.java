package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;
import java.util.HashMap;

import vdm.p1.engine.Color;
import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public final class AndroidGraphics implements IGraphics {
	private final SurfaceView surfaceView;
	private final SurfaceHolder surfaceHolder;
	private final Paint paint;
	private final HashMap<String, AndroidImage> loadedImages = new HashMap<>();
	private final HashMap<String, IFont> loadedFonts = new HashMap<>();
	private final AssetManager assetManager;
	private Canvas canvas = null;

	public AndroidGraphics(SurfaceView surfaceView, Context context) {
		this.surfaceView = surfaceView;
		this.surfaceHolder = surfaceView.getHolder();
		this.paint = new Paint();
		this.assetManager = context.getAssets();
		setColor(Color.WHITE);
	}

	/**
	 *
	 * @return an instance of the surface view
	 */
	public SurfaceView getView() {
		return surfaceView;
	}


	/**
	 *
	 * @return an instance of the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}


	/**
	 *
	 * set the canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}


	/**
	 *
	 * @return an instance of the surface holder
	 */
	public SurfaceHolder getSurfaceHolder() {
		return surfaceHolder;
	}


	/**
	 *
	 * clears the render and sets the background color
	 */
	@Override
	public void clear(Color color) {
		clear(color.getARGB());
	}

	/**
	 *
	 * clears the render and sets the background color
	 */
	@Override
	public void clear(int color) {
		canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(color); // ARGB
	}

	/**
	 *
	 * presents the render
	 */
	public void present() {
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	/**
	 *
	 * @return Whether or not the surface is valid.
	 */
	public boolean surfaceValid() {
		return surfaceHolder.getSurface().isValid();
	}

	/**
	 *
	 * @param name route of the image
	 * @return an instance of an {@link AndroidImage}
	 */
	@Override
	public AndroidImage newImage(String name) {
		Bitmap image;
		try {
			InputStream in = assetManager.open(name);
			image = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		AndroidImage aImage = new AndroidImage(image);
		loadedImages.put(name, aImage);
		return aImage;
	}

	/**
	 *
	 * @param name route of the image
	 * @return an instance of an {@link AndroidFont}
	 */
	@Override
	public AndroidFont newFont(String name, int size, boolean isBold) {
		AndroidFont aFont = new AndroidFont(this, name, assetManager, size, isBold);
		loadedFonts.put(name, aFont);
		return aFont;
	}

	/**
	 *
	 * @param font Font to write with
	 * @param string Message to write
	 * @return the size of that text
	 */
	@Override
	public Dimension getTextDimensions(IFont font, String string) {
		Typeface tf = ((AndroidFont) font).getUnderlyingFont();
		Paint paint = new Paint();
		paint.setTextSize(font.getSize());
		paint.setTypeface(tf);

		Rect result = new Rect();
		paint.getTextBounds(string, 0, string.length(), result);
		return new Dimension(result.width(), result.height());
	}

	/**
	 *
	 * @param image The image to draw.
	 * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
	 */
	@Override
	public void drawImage(IImage image, int x, int y) {
		canvas.drawBitmap(((AndroidImage) image).getUnderlyingImage(), x, y, paint);
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
		Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
		Rect dst = new Rect(x, y, x + width, y + height);
		canvas.drawBitmap(((AndroidImage) image).getUnderlyingImage(), src, dst, paint);
	}

	/**
	 *
	 * @param text The text to draw in the destination canvas.
	 * @param x    The x-axis coordinates from where to draw the text.
	 * @param y    The y-axis coordinates from where to draw the text.
	 */
	@Override
	public void drawText(String text, int x, int y) {
		canvas.drawText(text, x, y, paint);
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
		canvas.drawRect(x, y, x + width, y + height, paint);
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
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x + width, y + height, paint);
		paint.setStyle(Paint.Style.FILL);
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
		canvas.drawLine(initX, initY, endX, endY, paint);
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void setResolution(int width, int height) {
	}

	/**
	 *
	 * @param color Sets the current color with a raw RGBA integer.
	 */
	@Override
	public void setColor(int color) {
		paint.setColor(Color.rgbaToARGB(color));
	}

	/**
	 *
	 * @param color Sets the current color with a {@link Color} instance.
	 */
	@Override
	public void setColor(Color color) {
		paint.setColor(color.getARGB());
	}

	/**
	 * sets the writing font
	 * @param font {@link AndroidFont}
	 */
	@Override
	public void setFont(IFont font) {
		AndroidFont aFont = (AndroidFont) font;
		paint.setTypeface(aFont.getFont());
		paint.setTextSize(aFont.getSize());
	}


	@Override
	public void translate(int x, int y) {

	}

	@Override
	public void scale(double x, double y) {

	}

	@Override
	public void save() {
		canvas.save();
	}

	@Override
	public void restore() {
		canvas.restore();
	}

	/**
	 *
	 * @return the width of the surface
	 */
	@Override
	public int getWidth() {
		return surfaceView.getWidth();
	}

	/**
	 *
	 * @return the height of the surface
	 */
	@Override
	public int getHeight() {
		return surfaceView.getHeight();
	}
}
