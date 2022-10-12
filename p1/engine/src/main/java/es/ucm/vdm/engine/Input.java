package es.ucm.vdm.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Input implements IInput{

    protected List<TouchEvent> mTouchEvents_; //could be a queue too

    //returns the list of all the events, and cleans it for future use
    @Override
    public List<TouchEvent> getTouchEvents() {
        List<TouchEvent> tmp;
        synchronized (this){
            tmp=new ArrayList<TouchEvent>(mTouchEvents_); //copys the content in tmp
            mTouchEvents_.clear();
        }
        return tmp;

    }

    protected synchronized void addEvent(TouchEvent event){
        synchronized (this){
            mTouchEvents_.add(event);
        }
    }
}
