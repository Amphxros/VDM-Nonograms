package es.ucm.vdm.aengine;

import android.view.MotionEvent;
import android.view.View;


import es.ucm.vdm.engine.EventType;
import es.ucm.vdm.engine.Input;
import es.ucm.vdm.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent t= new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.CLICKED);
        mTouchEvents_.add(t);
        return false;
    }
}
