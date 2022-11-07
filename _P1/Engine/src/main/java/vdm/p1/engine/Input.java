package vdm.p1.engine;

import java.util.LinkedList;

public abstract class Input implements IInput {
    protected final LinkedList<TouchEvent> mTouchEvents_; //could be a queue too

    public Input() {
        mTouchEvents_ = new LinkedList<TouchEvent>();
    }

    /**
     * @return Returns a list of all the queued events, clears the queue upon call.
     */
    @Override
    public synchronized LinkedList<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> tmp;

        synchronized (this) {
            // Copy the queued events into a list:
            tmp = new LinkedList<>(mTouchEvents_);
            mTouchEvents_.clear();
        }

        return tmp;
    }

    protected synchronized void addEvent(TouchEvent event) {
        mTouchEvents_.add(event);

    }
}
