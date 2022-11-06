package vdm.p1.androidengine;

import android.view.MotionEvent;
import android.view.View;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener{

    public AndroidInput(){
        super();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent t= new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.CLICKED);
        mTouchEvents_.add(t);
        return true;
    }
}
