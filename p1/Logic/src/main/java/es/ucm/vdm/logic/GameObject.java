package es.ucm.vdm.logic;

import es.ucm.vdm.logic.Vector2D;

public abstract class GameObject{ //base of all the elements of the game

    protected Vector2D position_;

    public GameObject(){
        position_ = new Vector2D(0, 0);
    }

    public void setPos(Vector2D pos){
        position_.x=pos.x;
        position_.y= pos.y;
    }


}