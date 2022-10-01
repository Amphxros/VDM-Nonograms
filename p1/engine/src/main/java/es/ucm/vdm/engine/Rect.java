package es.ucm.vdm.engine;


public class Rect {

    // Px where the elem begins
    int mRight_;    //right side
    int mLeft_;     //left side
    int mTop_;      //top side
    int mBottom_;   //bottom side

    //position
    int x_;
    int y_;
    //width and height
    int mWidth_;
    int mHeight_;

    // Constructor
    // Set all the values: Left
    public Rect(int l,int r,int t,int b){
        mLeft_=l;
        mRight_=r;
        mTop_=t;
        mBottom_=b;

        mWidth_=mRight_-mLeft_;
        mHeight_=mBottom_-mTop_;

    }

    public int getLeft() {
        return mLeft_;
    }

    public void setLeft(int left) {
        this.mLeft_ = left;
        mWidth_=mRight_-mLeft_;
    }

    public int getRight() {
        return mRight_;
    }

    public void setRight(int right) {
        this.mRight_ =right;
        mWidth_=mRight_-mLeft_;
    }

    public int getTop() {
        return mTop_;
    }

    public void setmTop_(int top) {
        this.mTop_ = top;
        mHeight_=mBottom_-mTop_;
    }

    public int getBottom() {
        return mBottom_;
    }

    public void setBottom(int bottom) {
        this.mBottom_ = bottom;
        mHeight_=mBottom_-mTop_;
    }

    //width and height

    public int getWidth() {
        return mWidth_;
    }

    public void setmWidth_(int width) {
        this.mWidth_ = width;
    }

    public int getHeight() {
        return mHeight_;
    }

    public void setHeight(int height) {
        this.mHeight_ = height;
    }

    //position


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
