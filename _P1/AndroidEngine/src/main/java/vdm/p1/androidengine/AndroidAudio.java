package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import vdm.p1.engine.IAudio;
import vdm.p1.engine.ISound;

public final class AndroidAudio implements IAudio {
    private final Context context;

    public AndroidAudio(Context context) {
        this.context = context;
    }

    @Override
    public AndroidSound createSound(String filename) {
        MediaPlayer player = new MediaPlayer();
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(filename + ".ogg");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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