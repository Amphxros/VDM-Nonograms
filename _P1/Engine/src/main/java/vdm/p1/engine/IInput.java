package vdm.p1.engine;

import java.util.List;

public interface IInput {
	/**
	 * @return the list of the touch events
	 */
	List<TouchEvent> getTouchEvents();
}
