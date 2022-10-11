package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.Graphics;

public abstract class Scene {

    Graphics mGraphics; //the scene have the Graphics instance
    ArrayList<GameObject> mGameObjects_;
    public Scene(Graphics graphics){
        this.mGraphics= graphics;
    }
    //updates all the GO in this Scene with the time since the last update
    void update(double t){
        for(GameObject g: mGameObjects_){

        }
    }

    //render the GO in their positions
    void render(){
        for(GameObject g: mGameObjects_){
            g.render(mGraphics);
        }
    }

    //handle the input
    void handleInput(){

    }

    void addGameObject(GameObject go){
        mGameObjects_.add(go);
    }
}
