package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public class Button extends GameObject{
    String text_;
    Color color;

    public Button(int x, int y, int w, int h, String msg, Color color) {
        super(x, y, w, h);
        this.text_=msg;
        this.color =color;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        graphics.drawRectangle(mPosition_.getX(), mPosition_.getY(), mWidth_,mHeight_);
        graphics.setColor(Color.WHITE);
        graphics.drawText(text_, mPosition_.getX() + mWidth_/3, mPosition_.getY() + mHeight_/2);
    }

    @Override
    public void update(double t) {

    }
    public boolean handleInput(TouchEvent event) {
        return false;
    }


}
