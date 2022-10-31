package es.ucm.vdm.engine;

public class Engine implements IEngine, Runnable{
 protected IGraphics mGraphics_;
 protected IAudio mAudio_;
 protected IInput mInput_;
 protected Logic mLogic_;

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
   mWidth_= mGraphics_.getWidth();
   mHeight_= mGraphics_.getHeight();

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

 @Override
 public Logic getLogic() {
  return mLogic_;
 }

 @Override
 public void setLogic(Logic logic) {
    mLogic_=logic;
 }


 @Override
 public void run() {
   if(mThread_==Thread.currentThread()){
      while (mRunning_){
       mLastFrameTime_=System.nanoTime();
       update();
       render();
      }
  }
 }

 @Override
 public void resume() {
  if(!mRunning_){
   mRunning_=true;
   mThread_= new Thread(this);
   mThread_.start();
  }

 }

 @Override
 public void pause() {
  if(mRunning_){
     mRunning_=false;
     try{
       mThread_.join();
       mThread_=null;
     }
     catch (Exception e){
     }
  }

 }

 @Override
 public void update() {
 mLogic_.update(getDeltaTime());
 }

 @Override
 public void render() {
  mLogic_.render();
 }

 public void setInput(IInput input){
   mInput_=input;
 }
 @Override
 public double getDeltaTime() {
  return mLastFrameTime_;
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

 @Override
 public boolean openGame() {
  return false;
 }

 @Override
 public boolean closeGame() {
  return false;
 }

}