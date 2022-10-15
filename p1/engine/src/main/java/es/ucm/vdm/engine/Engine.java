package es.ucm.vdm.engine;

public class Engine implements IEngine{
 protected IGraphics mGraphics_;
 protected IAudio mAudio_;
 protected IInput mInput_;

 protected long mLastFrameTime_;
 protected  volatile boolean mRunning_=false;
 private Thread mThread_;


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

 @Override
 public IAudio getAudio() {
  return mAudio_;
 }

 @Override
 public IInput getInput(){
    return mInput_;
 }

 @Override
 public double getDeltaTime() {
  return 0;
 }

 @Override
 public int getWidth() {
  return 0;
 }

 @Override
 public int getHeight() {
  return 0;
 }

 @Override
 public void run() {

 }

 @Override
 public void resume() {

 }

 @Override
 public void pause() {

 }

 @Override
 public void update() {

 }

 @Override
 public void render() {

 }

 @Override
 public void closeGame() {

 }


}