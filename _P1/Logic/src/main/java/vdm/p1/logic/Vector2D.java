package vdm.p1.logic;

public class Vector2D {
    int x_; //x axis
    private int y_; //y axis

    public Vector2D(int x, int y) {
        this.x_ = x;
        this.y_ = y;
    }

    public int getX() {
        return x_;
    }

    public void setX(int x) {
        this.x_ = x;
    }

    public int getY() {
        return y_;
    }

    public void setY(int y) {
        this.y_ = y;
    }
}
