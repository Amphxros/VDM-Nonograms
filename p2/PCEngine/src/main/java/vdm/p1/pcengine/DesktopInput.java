package vdm.p1.pcengine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public final class DesktopInput extends Input implements MouseInputListener, KeyListener {
	private final JFrame frame;
	private boolean fullscreen = false;

	public DesktopInput(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
	}

	/**
	 * Mouse released event
	 * @param mouseEvent current event
	 */
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
			addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.PRIMARY));
		} else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
			addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.SECONDARY));
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Listen to the "F" key from the keyboard.
		if (e.getKeyCode() == KeyEvent.VK_F) {
			// Get the screen devices, then access the screen devices:
			GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

			// Defensive: If there is at least one device, then set the frame as fullscreen, otherwise unset it:
			if (devices.length != 0) {
				devices[0].setFullScreenWindow(fullscreen ? null : frame);
				fullscreen = !fullscreen;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
	}
}
