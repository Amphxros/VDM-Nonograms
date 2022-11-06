package vdm.p1.logic;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public class Button extends GameObject{
    String text_;
    int mColor_;
    public Button(int x, int y, int w, int h, String msg, int color) {
        super(x, y, w, h);
        this.text_=msg;
        this.mColor_=color;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(mColor_);
        graphics.drawRectangle(mPosition_.getX(), mPosition_.getY(), mWidth_,mHeight_);
        graphics.setColor(0xFFFFFFFF);
        graphics.drawText(text_, mPosition_.getX() + mWidth_/3, mPosition_.getY() + mHeight_/2);
    }

    @Override
    public void update(float t) {

    }
    public boolean handleInput(TouchEvent event) {
        return false;
    }


}
