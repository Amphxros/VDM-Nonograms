package vdm.p1.pcengine;

import javax.swing.JFrame;

import vdm.p1.engine.Color;
import vdm.p1.engine.Engine;

public final class DesktopEngine extends Engine implements Runnable {
	private final JFrame renderView;
	public boolean running;
	public int FPS = 60;

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

		this.getLogic().initLogic();

		long nanoStartT = System.nanoTime();
		long nanoFPST = 1000000000 / FPS;    // Desired time per frame
		long nanoCurrentT = 0, nanoElapsedT = 0, nanoLastT = 0;

		while (running) {
			// Doesnt update unlees it has to (We dont have enough resources to waste)
			nanoElapsedT = System.nanoTime() - nanoLastT;

			if (nanoElapsedT > nanoFPST) {
				nanoLastT = System.nanoTime();

				this.getGraphics().clear(Color.WHITE);
				double delta = 0;
				this.getLogic().handleEvents();
				this.getLogic().update(delta);
				this.getLogic().render();
				this.getGraphics().present();
			}
		}
	}
}
