package vdm.p1.pcengine;

import javax.swing.JFrame;

import vdm.p1.engine.Color;
import vdm.p1.engine.Engine;

public final class DesktopEngine extends Engine implements Runnable {
	private final JFrame renderView;
	public boolean running;

	public DesktopEngine() {
		renderView = new JFrame("Nonogramas");

		renderView.setSize(1000, 1000);
		renderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		renderView.setIgnoreRepaint(true);
		renderView.setVisible(true);

		setAudio(new DesktopAudio());
		setGraphics(new DesktopGraphics(renderView));

		DesktopInput input = new DesktopInput(renderView);
		setInput(input);
		running = true;

		renderView.addMouseListener(input);
		renderView.addKeyListener(input);
	}

	@Override
	public int getWidth() {
		return renderView.getWidth();
	}

	@Override
	public int getHeight() {
		return renderView.getHeight();
	}

	@Override
	public void run() {
		getLogic().initLogic();

		long lastFrameTime = System.nanoTime();
		while (running) {
			long currentTime = System.nanoTime();
			long nanoElapsedTime = currentTime - lastFrameTime;
			lastFrameTime = currentTime;

			// Frames Per Second
			double elapsedTime = (double) nanoElapsedTime / 1.0E9;

			getLogic().handleEvents();
			getLogic().update(elapsedTime);

			getGraphics().clear(Color.WHITE);
			getLogic().render();
			getGraphics().present();
		}
	}
}
