package es.ucm.vdm.logic;

import java.util.ArrayList;
import java.util.List;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;
import es.ucm.vdm.engine.TouchEvent;

public class Scene implements IScene{
    protected ArrayList<GameObject> mGameObjects_;
    public Scene(){
       mGameObjects_= new ArrayList<GameObject>();
    }
    public ArrayList<GameObject> getGameObjects(){
        return mGameObjects_;
    }

    public void addGameObject(GameObject go){
        mGameObjects_.add(go);
    }

    @Override
    public void update(double deltaTime) {
        for(GameObject go: mGameObjects_){
            go.update(deltaTime);
        }
    }

    @Override
    public void render(IGraphics g) {
        for(GameObject go: mGameObjects_){
            go.render(g);
        }
    }

    @Override
    public void handleInput(IInput input) {
        for(GameObject go: mGameObjects_){
            for(TouchEvent ev: input.getTouchEvents()) //CAMBIAR ESTO EN ALGUN MOMENTO
            go.handleInput(ev);
        }
    }
}