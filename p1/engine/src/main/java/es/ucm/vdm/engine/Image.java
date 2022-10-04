package es.ucm.vdm.engine;

public class Image implements IImage{

    Rect mRect_;

    public Image(String filename,int l, int r, int t, int b){
        mRect_= new Rect(l,r,t,b);
    }

    @Override
    public Rect getRect() {
        return mRect_;
    }
}
