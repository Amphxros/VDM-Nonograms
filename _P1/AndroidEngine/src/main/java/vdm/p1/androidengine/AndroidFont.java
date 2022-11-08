package vdm.p1.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import vdm.p1.engine.IFont;

public class AndroidFont implements IFont {

    private Typeface mFont_;
    private int size_;

    public AndroidFont(String route, AssetManager mngr, int size, boolean isBold){
        this.size_=size;
        mFont_=Typeface.create(Typeface.createFromAsset(mngr,route),size,isBold);
    }
    public Typeface getFont(){
        return mFont_;
    }

    @Override
    public int getSize() {
        return size_;
    }

    @Override
    public boolean isBold() {
        return mFont_.isBold();
    }
}
