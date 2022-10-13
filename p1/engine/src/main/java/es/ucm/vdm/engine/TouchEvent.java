package es.ucm.vdm.engine;

public class TouchEvent{

    //private attributes
    float x_, y_; //coordinates
    EventType mEvent_; //type of event


    public TouchEvent(float x, float y, EventType event){
        this.x_=x;
        this.y_=y;
        this.mEvent_=event;
    }
    public float getX(){
        return x_;
    }
    public float getY(){
        return y_;
    }

    public EventType getType(){
        return mEvent_;
    }


}