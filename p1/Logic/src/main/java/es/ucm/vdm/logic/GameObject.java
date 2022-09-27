

public abstract class GameObject{ //base of all the elements of the game
    protected Vector2D position_;

    public GameObject(){

    }
    public void setPos(Vector2D pos){
        position_.x=pos.x;
        position_.y= pos.y;
    }


}