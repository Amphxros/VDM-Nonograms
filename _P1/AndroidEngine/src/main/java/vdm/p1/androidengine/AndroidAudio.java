package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import vdm.p1.engine.IAudio;
import vdm.p1.engine.ISound;

public class AndroidAudio implements IAudio {

    private final Context context;
    public MediaPlayer player;

    public AndroidAudio(Context context) {
        this.context = context;
        player = new MediaPlayer();
    }

    @Override
    public AndroidSound createSound(String filename) {

        try {
            AssetFileDescriptor afd = context.getAssets().openFd(filename + ".ogg");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AndroidSound(player);
    }

    @Override
    public void playSound(ISound s) {
        player.start();
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.stop();
    }

    @Override   // Must be called before playing the sound
    public void setLoop() {
        this.player.setLooping(true);
    }
}