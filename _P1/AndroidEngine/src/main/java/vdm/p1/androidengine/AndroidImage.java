package vdm.p1.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import vdm.p1.engine.IImage;

public class AndroidImage implements IImage {

    private Bitmap mImage_;

    public AndroidImage(String route, AssetManager mngr) {
        try {
            InputStream in = mngr.open(route);
            mImage_ = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImage() {
        return mImage_;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
