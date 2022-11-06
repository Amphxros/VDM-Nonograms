package vdm.p1.androidengine;

import android.media.MediaPlayer;

import vdm.p1.engine.ISound;

public class AndroidSound implements ISound {

    MediaPlayer player;

    public AndroidSound(MediaPlayer player) {
        this.player = player;
    }

    /**
     * Plays the sound.
     */
    @Override
    public void play() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

    /**
     * Stops playing the sound.
     */
    @Override
    public void stop() {
        if (player.isPlaying()) {
            player.stop();
        }
    }

    public void dispose() {
        player.release();
    }
}
