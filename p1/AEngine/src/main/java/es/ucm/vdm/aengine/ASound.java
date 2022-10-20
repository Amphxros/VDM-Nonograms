package es.ucm.vdm.aengine;

import android.media.MediaPlayer;

import es.ucm.vdm.engine.ISound;

public class ASound implements ISound {
    private final MediaPlayer player;

    public ASound(MediaPlayer player) {
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
