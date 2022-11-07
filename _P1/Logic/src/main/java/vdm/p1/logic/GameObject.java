package vdm.p1.logic;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public abstract class GameObject implements IGameObject{
    Vector2D mPosition_;
    int mWidth_;
    int mHeight_;

    public GameObject(int x, int y, int w, int h){
        this.mPosition_= new Vector2D(x,y);
        this.mWidth_=w;
        this.mHeight_=h;
    }

    @Override
    public Vector2D getPosition() {
        return mPosition_;
    }

    @Override
    public void setPosition(int x, int y) {
        mPosition_.setX(x);
        mPosition_.setY(y);
    }

    @Override
    public int getWidth() {
        return mWidth_;
    }

    @Override
    public int getHeight() {
        return mHeight_;
    }

    @Override
    public void render(IGraphics graphics){}

    @Override
    public void update(double t){}

    @Override
    public boolean handleInput(TouchEvent event) {return false;}
}
