package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.TouchEvent;

public class CheckSolutionButton extends Button{
    Table mTable_;
    public CheckSolutionButton(Table t,int x, int y, int w, int h, String msg, Color color) {
        super(x, y, w, h,msg,color);
        this.mTable_=t;

    }
    public boolean handleInput(TouchEvent event){
        if(event.getX()>=mPosition_.getX() && event.getX()<=mPosition_.getX() + mWidth_ &&
                event.getY()>=mPosition_.getY() && event.getY()<=mPosition_.getY() + mHeight_)
        {
            this.mTable_.checkSolutions();
            return true;
        }

        return false;
    }
}
