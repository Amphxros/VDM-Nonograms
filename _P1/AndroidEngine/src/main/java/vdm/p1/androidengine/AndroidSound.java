package vdm.p1.androidengine;

import android.media.MediaPlayer;

import vdm.p1.engine.ISound;

public final class AndroidSound implements ISound {
	private final MediaPlayer player;
	
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

	@Override
	public void setLoop() {
		player.setLooping(true);
	}

	// Frees the memory allocated
	public void dispose() {
		player.release();
	}
}
