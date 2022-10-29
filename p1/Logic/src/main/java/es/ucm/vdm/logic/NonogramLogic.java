package es.ucm.vdm.logic;

import es.ucm.vdm.engine.Engine;

public class NonogramLogic implements es.ucm.vdm.engine.Logic {
    protected Engine mEngine_;
    protected Scene mCurrentScene_;


    public NonogramLogic(Engine engine){
        this.mEngine_=engine;
    }
    @Override
    public void initLogic(){
        mCurrentScene_= new MenuScene(this);
    }
    @Override
    public void setEngine(Engine eng){
        this.mEngine_=eng;
    }
    @Override
    public void update(double t){

        mCurrentScene_.update(t);
    }
    @Override
    public void handleEvents(){

        mCurrentScene_.handleInput(mEngine_.getInput());
    }
    @Override
    public void render(){
        mCurrentScene_.render(mEngine_.getGraphics());
    }

    public void setCurrScene(Scene scene){
        mCurrentScene_=scene;
    }

}