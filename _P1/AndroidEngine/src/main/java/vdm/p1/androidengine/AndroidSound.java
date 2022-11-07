package vdm.p1.androidengine;

import android.media.MediaPlayer;
import vdm.p1.engine.ISound;

public class AndroidSound implements ISound {

    MediaPlayer player;

    public AndroidSound(MediaPlayer player) {
        this.player = player;
    }

    @Override
    public void play() {
        if (!player.isPlaying())
            player.start();
    }

    @Override
    public void stop() {
        if (player.isPlaying()) {
            player.stop();
        }
    }

    // Frees the memory allocated
    public void dispose() {
        player.release();
    }
}