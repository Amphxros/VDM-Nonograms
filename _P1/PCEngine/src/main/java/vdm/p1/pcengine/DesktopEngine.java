package vdm.p1.pcengine;

import javax.swing.JFrame;

import vdm.p1.engine.Engine;

public final class DesktopEngine extends Engine {
	private final JFrame frame;

	public DesktopEngine(final JFrame frame) {
		this.frame = frame;

		setAudio(new DesktopAudio());
		setGraphics(new DesktopGraphics(frame));

		DesktopInput input = new DesktopInput(frame);
		setInput(input);

		frame.addMouseListener(input);
		frame.addKeyListener(input);
	}

	@Override
	public int getWidth() {
		return frame.getWidth();
	}

	@Override
	public int getHeight() {
		return frame.getHeight();
	}
}
