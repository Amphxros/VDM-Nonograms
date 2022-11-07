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
    public MediaPlayer player;

    @Override
    public ISound createSound(String filename) {
        player = MediaPlayer.create(context, Uri.fromFile(new File(filename)));
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

    @Override   // Must be called before playing the sound
    public void setLoop() {  this.player.setLooping(true);   }
}
