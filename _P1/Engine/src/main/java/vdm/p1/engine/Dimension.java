package vdm.p1.engine;

public final class Dimension<T> {
    public T width;
    public T height;

    public Dimension(T width, T height) {
        this.width = width;
        this.height = height;
    }

    public T getWidth() {
        return width;
    }

    public void setHeight(T height) {
        this.height = height;
    }

    public T getHeight() {
        return height;
    }

    public void setWidth(T width) {
        this.width = width;
    }
}
