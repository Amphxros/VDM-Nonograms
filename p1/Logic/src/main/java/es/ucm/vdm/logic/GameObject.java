package es.ucm.vdm.logic;

import Cell;

public abstract class GameObject{ //base of all the elements of the game

    protected Vector2D position_;
    private GameObject parent=null;

    public GameObject(){
        position_.x=0;
        position_.y=0;
    }

    public void setPos(Vector2D pos){
        position_.x=pos.x;
        position_.y= pos.y;
    }

    public void setParent(GameObject p){
        parent=p;
    }


}