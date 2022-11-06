package vdm.p1.androidengine;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;

import vdm.p1.engine.IAudio;
import vdm.p1.engine.ISound;


public class AndroidAudio implements IAudio {
    private final Context context;
    public AndroidAudio(Context context) {
        this.context = context;
    }

    @Override
    public ISound createSound(String filename) {
        MediaPlayer player = MediaPlayer.create(context, Uri.fromFile(new File(filename)));
        return new AndroidSound(player);
    }


    @Override
    public void playSound(ISound s) {
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.stop();
    }
}
