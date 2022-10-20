package es.ucm.vdm.aengine;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;

import es.ucm.vdm.engine.IAudio;
import es.ucm.vdm.engine.ISound;

public class AAudio implements IAudio {
    private final Context context;

    public AAudio(Context context) {
        this.context = context;
    }

    @Override
    public ISound createSound(String filename) {
        MediaPlayer player = MediaPlayer.create(context, Uri.fromFile(new File(filename)));
        return new ASound(player);
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
