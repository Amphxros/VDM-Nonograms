package vdm.p1.logic.scenes;

import java.util.ArrayList;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IInput;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.IScene;

public abstract class Scene implements IScene {

    ArrayList<GameObject> mGameObjects_;
    int screen_width;
    int screen_height;
    public Scene( int width, int height){
        this.screen_height=height;
        this.screen_width=width;
        mGameObjects_= new ArrayList<GameObject>();
    }

    @Override
    public ArrayList<GameObject> getGameObjects() {
        return mGameObjects_;
    }
    public void addGameObject(GameObject go){
        mGameObjects_.add(go);
    }
}
