package vdm.p2.logic;

import java.util.LinkedList;

public abstract class Input implements IInput {
	// Could be a queue too
	protected final LinkedList<TouchEvent> events = new LinkedList<>();

	/**
	 * @return Returns a list of all the queued events, clears the queue upon call.
	 */
	@Override
	public synchronized LinkedList<TouchEvent> getTouchEvents() {
		LinkedList<TouchEvent> tmp;

		synchronized (this) {
			// Copy the queued events into a list:
			tmp = new LinkedList<>(events);
			events.clear();
		}

		return tmp;
	}

	protected synchronized void addEvent(TouchEvent event) {
		events.add(event);
	}
}
