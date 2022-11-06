package vdm.p1.engine;

public abstract class Engine implements IEngine{
    protected IGraphics mGraphics_;
    protected IAudio mAudio_;
    protected Input mInput_;
    protected ILogic mLogic_;

    public Engine(){

    }
    /**
     * @return instance of the logic
     */
    @Override
    public ILogic getLogic() {
        return mLogic_;
    }

    @Override
    public void setLogic(ILogic logic) {
        this.mLogic_ = logic;
    }

    /**
     * @return instance of graphics
     */
    @Override
    public IGraphics getGraphics(){
        return mGraphics_;
    }

    @Override
    public void setGraphics(IGraphics mGraphics) {
        this.mGraphics_ = mGraphics;
    }

    /**
     * @return instance of inputhandler
     */
    @Override
    public Input getInput() {
        return mInput_;
    }

    @Override
    public void setInput(Input input) {
        this.mInput_ = input;
    }
    /**
     * @return instance of audiomanager
     */
    @Override
    public IAudio getAudio() {
        return mAudio_;
    }

    @Override
    public void setAudio(IAudio mAudio_) {
        this.mAudio_ = mAudio_;
    }
}
