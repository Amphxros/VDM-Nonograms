package vdm.p1.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import vdm.p1.engine.Engine;

public final class AndroidEngine extends Engine implements Runnable {
	private Thread thread;
	private boolean running;

	public AndroidEngine(SurfaceView surfaceView, Context context) {
		AndroidGraphics graphics = new AndroidGraphics(surfaceView, context);
		AndroidInput input = new AndroidInput();
		AndroidAudio audio = new AndroidAudio(context);

		surfaceView.setOnTouchListener(input);

		setGraphics(graphics);
		setInput(input);
		setAudio(audio);
	}

	@Override
	public AndroidGraphics getGraphics() {
		return (AndroidGraphics) super.getGraphics();
	}

	@Override
	public int getWidth() {
		return getGraphics().getWidth();
	}

	@Override
	public int getHeight() {
		return getGraphics().getHeight();
	}

	/**
	 * Main loop of the program
	 */
	@Override
	public void run() {
		if (thread != Thread.currentThread()) {
			// (Defensive Programming)
			// Makes it so runnable can only be called from this class
			throw new RuntimeException("run() should not be called directly");
		}

		// Waits for the view to be initialized (The thread could be faster than the initialization)
		while (running && getGraphics().getWidth() == 0) ;

		getLogic().initLogic();
		long lastFrameTime = System.nanoTime();

		// MAIN GAME LOOP
		while (running) {
			long currentTime = System.nanoTime();
			long nanoElapsedTime = currentTime - lastFrameTime;
			lastFrameTime = currentTime;

			// Frames Per Second
			double elapsedTime = (double) nanoElapsedTime / 1.0E9;

			handleEvents();
			update(elapsedTime);
			render();
		}

	}

	private void render() {
		AndroidGraphics graphics = getGraphics();

		// Waits for an invalid surface
		while (!graphics.surfaceValid()) ;

		graphics.clear(0xFFFFFFFF); // ARGB
		getLogic().render();
		graphics.present();
	}

	private void update(double delta) {
		getLogic().update(delta);
	}

	private void handleEvents() {
		getLogic().handleEvents();
	}

	/**
	 * Resumes the API, creating the execution thread
	 */
	public void resume() {
		if (!running) {
			// Only if we weren't doing anything yet
			// (Defensive programming at its best)
			running = true;
			// run() is "running" in a new thread
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Pause the API, deletes the execution thread
	 */
	public void pause() {
		if (running) {
			running = false;
			while (true) {
				try {
					thread.join();
					thread = null;
					break;
				} catch (InterruptedException ie) {
					// Something went REALLY wrong
				}
			}
		}
	}
}
