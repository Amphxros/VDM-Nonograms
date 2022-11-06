package vdm.p1.logic;

import vdm.p1.engine.IGraphics;

public class Button extends GameObject{
    String text_;
    Logic mLogic_;
    int mColor_;
    public Button(Logic logic, int x, int y, int w, int h, String msg, int color) {
        super(x, y, w, h);
        this.text_=msg;
        this.mLogic_=logic;
        this.mColor_=color;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(mColor_);
        graphics.drawRectangle(getPosition().getX(), getPosition().getY(), mWidth_,mHeight_);
        graphics.setColor(0xFFFFFFFF);
        graphics.drawText(text_, mPosition_.x_ + mWidth_/3, mPosition_.getY() + mHeight_/2);
    }

    @Override
    public void update(float t) {

    }

    @Override
    public void handleInput() {

    }
}
