package es.ucm.vdm.logic;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;
import es.ucm.vdm.engine.TouchEvent;

public abstract class GameObject { //base of all the elements of the game

    protected Vector2D position_;
    int mWidth_;
    int mHeight_;

    public GameObject() {
        position_ = new Vector2D(0, 0);
    }

    public GameObject(int x, int y, int w, int h){
        position_= new Vector2D(x,y);
        this.mWidth_=w;
        this.mHeight_=h;
    }

    public void setPos(Vector2D pos) {
        position_.x = pos.x;
        position_.y = pos.y;
    }
    public int getWidth(){
        return mWidth_;
    }
    public int getHeight_(){
        return mHeight_;
    }

    public void update(double t){

    }

    public void render(IGraphics graphics){

    }
    public void handleInput(TouchEvent event){

    }

}