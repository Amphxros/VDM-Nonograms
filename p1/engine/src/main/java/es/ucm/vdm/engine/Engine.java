package es.ucm.vdm.engine;

public abstract class Engine implements IEngine{
 protected IGraphics mGraphics_;
 protected IAudio mAudio_;
 protected IInput mInput_;

 protected long mLastFrameTime_;
 protected volatile boolean mRunning_=false;
 private Thread mThread_;

 private int mWidth_, mHeight_;

 public Engine(){

 }
 public Engine(IGraphics graphics, IAudio audio, IInput input){
   this.mGraphics_=graphics;
   this.mAudio_=audio;
   this.mInput_=input;
   mLastFrameTime_=0;

 }

 @Override
 public IGraphics getGraphics() {
  return mGraphics_;
 }
 public void setGraphics(IGraphics graphics){
  mGraphics_=graphics;
 }

 @Override
 public IAudio getAudio() {
  return mAudio_;
 }
 public void setAudio(IAudio audio){
  mAudio_=audio;
 }
 @Override
 public IInput getInput(){
    return mInput_;
 }

 public void setInput(IInput input){
   mInput_=input;
 }
 @Override
 public double getDeltaTime() {
  return 0;
 }

 @Override
 public int getWidth() {
  return mWidth_;
 }

 public void setWidth(int width){
   mWidth_=width;
 }

 @Override
 public int getHeight() {
   return mHeight_;
 }

 public void setHeight(int height){
   mHeight_=height;
 }


}