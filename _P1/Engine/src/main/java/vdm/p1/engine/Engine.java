package vdm.p1.engine;

public class Engine {
    protected IGraphics mGraphics_;
    protected IAudio mAudio_;
    protected IInput mInput_;
    protected ILogic mLogic_;

    public Engine(){

    }

    /**
     * @return instance of the logic
     */
    public ILogic getLogic() {
        return mLogic_;
    }

    public void setLogic(ILogic logic) {
        this.mLogic_ = logic;
    }

    /**
     * @return instance of graphics
     */
    public IGraphics getGraphics(){
        return mGraphics_;
    }

    public void setGraphics(IGraphics mGraphics) {
        this.mGraphics_ = mGraphics;
    }
    /**
     * @return instance of inputhandler
     */
    public IInput getInput() {
        return mInput_;
    }

    public void setInput(IInput input) {
        this.mInput_ = input;
    }
    /**
     * @return instance of audiomanager
     */
    public IAudio getAudio() {
        return mAudio_;
    }

    public void setAudio(IAudio mAudio_) {
        this.mAudio_ = mAudio_;
    }
}
