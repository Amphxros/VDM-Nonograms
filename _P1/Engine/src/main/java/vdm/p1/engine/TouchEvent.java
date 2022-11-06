package vdm.p1.engine;

public class TouchEvent {
    //private attributes
    float x_, y_; //coordinates

    int index;
    EventType mEvent_; //type of event
    String mKey_;

    public TouchEvent(float x, float y, EventType event){
        this.x_=x;
        this.y_=y;
        this.mEvent_=event;
        mKey_=null;
    }

    public TouchEvent(float x, float y, EventType event, String key){
        this.x_=x;
        this.y_=y;
        this.mEvent_=event;
        this.mKey_=key;
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
    public String getKey(){return mKey_;}
    public int getIndex(){return index;}
    public void setIndex(int ind){this.index=ind;}

}
