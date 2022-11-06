package vdm.p1.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Input implements IInput {

    protected List<TouchEvent> mTouchEvents_; //could be a queue too

    public Input(){
        mTouchEvents_= new ArrayList<TouchEvent>();
    }
    //returns the list of all the events, and cleans it for future use
    @Override
    public List<TouchEvent> getTouchEvents() {
        List<TouchEvent> tmp;

        tmp = new ArrayList<TouchEvent>(mTouchEvents_); //copys the content in tmp
        mTouchEvents_.clear();

        return tmp;

    }

    protected synchronized void addEvent(TouchEvent event) {
            event.setIndex(mTouchEvents_.size());
            mTouchEvents_.add(event);

    }
}
