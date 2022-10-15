package es.ucm.vdm.logic;

import es.ucm.vdm.engine.Engine;

public class Logic {
    protected Engine mEngine_;
    protected IScene mCurrentScene_;

    public Logic(Engine engine){
        this.mEngine_=engine;
    }
    public void initLogic(){

    }
    public void closeGame(){

    }
    public void update(double t){
        mCurrentScene_.update(t);
    }
    public void render(){
        mCurrentScene_.render(mEngine_.getGraphics());
    }

}