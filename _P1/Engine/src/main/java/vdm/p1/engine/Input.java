package vdm.p1.engine;

import java.util.LinkedList;


public abstract class Input implements IInput {

    protected LinkedList<TouchEvent> mTouchEvents_; //could be a queue too

    public Input(){
        mTouchEvents_= new LinkedList<TouchEvent>();
    }
    //returns the list of all the events, and cleans it for future use
    @Override
    public synchronized LinkedList<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> tmp= new LinkedList<>();

        synchronized (this) {
            tmp = new LinkedList<TouchEvent>(mTouchEvents_); //copys the content in tmp
            mTouchEvents_.clear();
        }
        return tmp;


    }

    protected synchronized void addEvent(TouchEvent event) {
        mTouchEvents_.add(event);

    }
}
