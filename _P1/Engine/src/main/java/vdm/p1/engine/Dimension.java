package vdm.p1.engine;

public final class Dimension<T> {
	private T width;
	private T height;

	public Dimension(T width, T height) {
		this.width = width;
		this.height = height;
	}

	public T getWidth() {
		return width;
	}

	public void setWidth(T width) {
		this.width = width;
	}

	public T getHeight() {
		return height;
	}

	public void setHeight(T height) {
		this.height = height;
	}
}
