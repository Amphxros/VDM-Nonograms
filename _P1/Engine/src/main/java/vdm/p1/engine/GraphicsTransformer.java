package vdm.p1.engine;

public final class GraphicsTransformer {
	private int width = 600;
	private int height = 800;
	private double ratio = width / (double) height;
	private int contentWidth = 0;
	private int contentHeight = 0;
	private double contentScale = 1;
	private int contentOffsetX = 0;
	private int contentOffsetY = 0;
	private int contentInsetT = 0;
	private int contentInsetL = 0;
	private int contentInsetB = 0;
	private int contentInsetR = 0;

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.ratio = width / (double) height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setInset(int top, int left, int bottom, int right) {
		contentInsetT = top;
		contentInsetL = left;
		contentInsetB = bottom;
		contentInsetR = right;
	}

	public void update(int contentWidth, int contentHeight) {
		boolean changed = false;

		if (contentWidth != this.contentWidth) {
			this.contentWidth = contentWidth;
			changed = true;
		}

		if (contentHeight != this.contentHeight) {
			this.contentHeight = contentHeight;
			changed = true;
		}

		if (!changed) return;

		double contentRatio = contentWidth / (double) contentHeight;
		if (ratio >= contentRatio) {
			contentScale = contentWidth / (double) width;
			contentOffsetX = 0;
			contentOffsetY = (int) ((contentHeight - height * contentScale) / 2.0);
		} else {
			contentScale = contentHeight / (double) height;
			contentOffsetX = (int) ((contentWidth - width * contentScale) / 2.0);
			contentOffsetY = 0;
		}
	}

	public void transform(IGraphics graphics) {
		graphics.translate(contentInsetL, contentInsetT);
		graphics.translate(contentOffsetX, contentOffsetY);
		graphics.scale(contentScale, contentScale);
	}

	public int getTransformedX(int x) {
		int out = (int) ((x - contentInsetL - contentOffsetX) / contentScale);
		return out < 0 || out > width ? -1 : out;
	}

	public int getTransformedY(int y) {
		int out = (int) ((y - contentInsetT - contentOffsetY) / contentScale);
		return out < 0 || out > height ? -1 : out;
	}
}
