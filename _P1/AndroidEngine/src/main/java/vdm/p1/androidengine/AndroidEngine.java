package vdm.p1.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import java.util.ArrayList;

import vdm.p1.engine.Engine;
import vdm.p1.engine.INotificationHandler;
import vdm.p1.engine.Notification;

public final class AndroidEngine extends Engine implements Runnable {
	private Thread thread;
	private boolean running;



	public AndroidEngine(SurfaceView surfaceView, Context context) {
		setGraphics(new AndroidGraphics(surfaceView, context));
		setAudio(new AndroidAudio(context));
		setFileManager(new AndroidFileManager(context));
		setShareIntent(new AndroidShareIntent(context));
		setSensors(new AndroidSensors(context));
		setNotificationHandler(new AndroidNotificationHandler(context));

		AndroidInput input = new AndroidInput();
		surfaceView.setOnTouchListener(input);
		setInput(input);
	}

	@Override
	public AndroidNotificationHandler getNotificationHandler() {
		return (AndroidNotificationHandler)super.getNotificationHandler();
	}

	@Override
	public AndroidGraphics getGraphics() {
		return (AndroidGraphics) super.getGraphics();
	}

	@Override
	public ArrayList<Notification> getNotifications() {
		return getNotificationHandler().getNotifications();
	}

	@Override
	public int getWidth() {
		return getGraphics().getWidth();
	}

	@Override
	public int getHeight() {
		return getGraphics().getHeight();
	}

	@Override
	public void run() {
		if (thread != Thread.currentThread()) {
			// (Defensive Programming)
			// Makes it so runnable can only be called from this class
			throw new RuntimeException("run() should not be called directly");
		}

		// Waits for the view to be initialized (The thread could be faster than the initialization)
		while (running && getGraphics().getWidth() == 0) ;

		long lastFrameTime = System.nanoTime();

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
		getLogic().render(getGraphics());
		graphics.present();
	}

	private void update(double delta) {
		getLogic().update(delta);
	}

	private void handleEvents() {
		getLogic().handleEvents(getInput());
	}

	public void resume() {
		if (!running) {
			// Only if we weren't doing anything yet
			// (Defensive programming at its best)
			running = true;

			// run() is "running" in a new thread
			thread = new Thread(this);
			thread.start();

			getSensors().register();
		}
	}

	public void pause() {
		Notification notification= new Notification("Nonograms", "sub", "content", 30);
		getNotificationHandler().addNotification(notification);
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

			getSensors().unregister();

		}

	}
}
