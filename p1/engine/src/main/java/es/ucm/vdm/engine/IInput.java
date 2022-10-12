package es.ucm.vdm.engine;

import java.util.List;

public interface IInput {
    class TouchEvent{

        public enum EventType {
            PRESSED_DOWN,
            PRESSED_UP,
            MOVED,
            CLICKED,
            KEY_DOWN,
            KEY_UP
        }

        //private attributes
        int x_, y_; //coordinates
        EventType mEvent_; //type of event


        public TouchEvent(int x, int y, EventType event){
            this.x_=x;
            this.y_=y;
            this.mEvent_=event;
        }
        public int getX(){
            return x_;
        }
        public int getY(){
            return y_;
        }

        public EventType getType(){
            return mEvent_;
        }


    }
    List<TouchEvent> getTouchEvents();
}
